package supremecarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import supremecarwash.model.Vehiculo;
import supremecarwash.service.IVehiculoService;

import java.util.List;


@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService service;

    @GetMapping()
    public List<Vehiculo> getVehiculos() {
        return service.listarVehiculos();
    }

    @PostMapping
    public void addVehiculo(@RequestBody Vehiculo vehiculo) {
        service.insertarVehiculo(vehiculo);
    }

}
