package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Lavado;
import supremecarwash.model.dto.LavadoRequestDto;
import supremecarwash.model.dto.LavadoResponseDto;
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
    public ResponseEntity<Void> addLavado(@RequestBody LavadoRequestDto lavado) {

        Lavado lav = service.insertarLavado(lavado);

        if (lav != null) {

            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/tipoLavado")
    public List<Lavado> tipoLavado() {

        return service.getTipoLavados();

    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LavadoResponseDto>> lavadosPorVehiculo(@PathVariable Integer id) {
        List<LavadoResponseDto> lavados = service.buscarLavadosPorVehiculo(id);
        if (!lavados.isEmpty()) {
            return new ResponseEntity<>(lavados, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
