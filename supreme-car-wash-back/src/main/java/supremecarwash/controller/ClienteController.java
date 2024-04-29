package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Cliente;
import supremecarwash.service.IClienteService;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping()
    public List<Cliente> getClientes(){
        return service.listarCLientes();
    }

    @PostMapping
    public void addCliente(Cliente cliente){
        service.insertarCliente(cliente);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String nombre, @RequestParam String password){
        if (service.loginCliente(nombre, password)) {
            return ResponseEntity.ok("Login exitoso");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}
