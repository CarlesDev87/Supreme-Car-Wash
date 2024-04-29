package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Lavado;
import supremecarwash.service.ILavadoService;


import java.util.List;

@RestController
@RequestMapping("/lavados")
public class LavadoController {

    @Autowired
    private ILavadoService service;

    @GetMapping()
    public List<Lavado> getLavados() {

        return service.listarLavados();
    }

    @PostMapping
    public void addLavado(@RequestBody Lavado lavado) {
        service.insertarLavado(lavado);
    }

}
