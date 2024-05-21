package supremecarwash.service;

import supremecarwash.model.Vehiculo;
import supremecarwash.model.dto.VehiculoRequestDto;
import supremecarwash.model.dto.VehiculoResponseDto;

import java.util.List;

public interface IVehiculoService {

    List<Vehiculo> listarVehiculos();

    Vehiculo insertarVehiculo(VehiculoRequestDto vehiculo);

    List<VehiculoResponseDto> buscarVehiculosPorCliente(Integer idCliente);

    VehiculoResponseDto buscarVehiculoPorId(Integer id);
}
