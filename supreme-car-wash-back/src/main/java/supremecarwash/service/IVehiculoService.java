package supremecarwash.service;

import supremecarwash.model.Vehiculo;
import supremecarwash.model.dto.VehiculoResponseDto;

import java.util.List;

public interface IVehiculoService {

    List<Vehiculo> listarVehiculos();

    void insertarVehiculo(Vehiculo vehiculo);

    List<VehiculoResponseDto> buscarVehiculosPorCliente(Integer idCliente);
}
