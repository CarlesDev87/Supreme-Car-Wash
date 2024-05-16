package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Vehiculo;
import supremecarwash.model.dto.VehiculoResponseDto;
import supremecarwash.service.IVehiculoService;

import java.util.List;


@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<VehiculoResponseDto>> getVehiculosPorCliente(@PathVariable Integer id) {
        List<VehiculoResponseDto> vehiculos = service.buscarVehiculosPorCliente(id);

        if (!vehiculos.isEmpty()) {
            return new ResponseEntity<>(vehiculos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public void addVehiculo(@RequestBody Vehiculo vehiculo) {
        service.insertarVehiculo(vehiculo);
    }

}
