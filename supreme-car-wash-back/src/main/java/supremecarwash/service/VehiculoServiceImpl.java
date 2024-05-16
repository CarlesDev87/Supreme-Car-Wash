package supremecarwash.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Vehiculo;
import supremecarwash.model.dto.VehiculoResponseDto;
import supremecarwash.repository.IVehiculoRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepository repo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Vehiculo> listarVehiculos() {
        return repo.findAll();
    }

    public void insertarVehiculo(Vehiculo vehiculo) {
        repo.save(vehiculo);
    }

    @Override
    public List<VehiculoResponseDto> buscarVehiculosPorCliente(Integer idCliente) {
        List<Vehiculo> listaVehiculos = repo.vehiculosPorCliente(idCliente);
        return listaVehiculos.stream().map(vehiculos ->modelMapper.map(vehiculos, VehiculoResponseDto.class)).collect(Collectors.toList());
    }
}
