package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Cliente;
import supremecarwash.model.dto.ClienteRequestDto;
import supremecarwash.model.dto.ClienteResponseDto;
import supremecarwash.service.AuthService;
import supremecarwash.service.IClienteService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @Autowired
    private AuthService authService;


    @GetMapping()
    public List<Cliente> getClientes(){
        return service.listarCLientes();

    }

    @PostMapping
    public ResponseEntity<Cliente> addCliente(ClienteRequestDto cliente){
        Cliente clienteDto = service.insertarCliente(cliente);

        if (clienteDto != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }


    @GetMapping("/validar")

    public ResponseEntity<Map<String, String>> validar(@RequestParam String email, @RequestParam String password){

        String token = authService.autenticado(email, password);

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error","Credenciales incorrectas"));
        }
    }

    @GetMapping("/{id}")
    public ClienteResponseDto getCliente(@PathVariable Integer id){

        return service.obtenerClientePorId(id);

    }


}
