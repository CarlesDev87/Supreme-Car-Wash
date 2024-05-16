package supremecarwash.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Cliente;
import supremecarwash.repository.IClienteRepository;

import java.security.Key;


@Service
public class AuthService {

    @Autowired
    private IClienteRepository clienteRepository;

    public String autenticado(String email, String password) {

        Cliente cliente =  clienteRepository.findByEmailAndPassword(email, password);

        if (cliente != null) {

            return generateToken(cliente);
        }

        return null;

    }



    private String generateToken(Cliente cliente) {

        Claims claims = Jwts.claims().setSubject(cliente.getEmail());

        claims.put("id", cliente.getId());
        claims.put("nombre", cliente.getNombre());
        claims.put("apellido", cliente.getApellido());
        claims.put("telefono", cliente.getTelefono());
        claims.put("direccion", cliente.getDireccion());
        claims.put("email", cliente.getEmail());
        claims.put("password", cliente.getPassword());


        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String token = Jwts.builder().setClaims(claims)
                .signWith(key)
                .compact();

        return token;
    }


}
