package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Vehiculo;
import supremecarwash.model.dto.VehiculoRequestDto;
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
    public ResponseEntity<Vehiculo> addVehiculo(@RequestBody VehiculoRequestDto vehiculoDto) {
        Vehiculo vehiculo = service.insertarVehiculo(vehiculoDto);

        if(vehiculo != null) {
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        } else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VehiculoResponseDto> getVehiculoPorId(@PathVariable Integer id) {
        VehiculoResponseDto v = service.buscarVehiculoPorId(id);

        if (v != null) {
            return new ResponseEntity<>(v, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
