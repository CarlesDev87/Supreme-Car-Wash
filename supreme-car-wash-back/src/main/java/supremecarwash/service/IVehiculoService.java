package supremecarwash.service;

import supremecarwash.model.Vehiculo;

import java.util.List;

public interface IVehiculoService {

    List<Vehiculo> listarVehiculos();

    void insertarVehiculo(Vehiculo vehiculo);
}
