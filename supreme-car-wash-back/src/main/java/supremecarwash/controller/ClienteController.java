package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Cliente;
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
    public void addCliente(Cliente cliente){

        service.insertarCliente(cliente);

    }


    /*
    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){

        if (service.loginCliente(email, password)) {
            return ResponseEntity.ok("Login exitoso");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
    */

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


}
