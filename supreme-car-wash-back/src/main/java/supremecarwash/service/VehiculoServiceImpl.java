package supremecarwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Vehiculo;
import supremecarwash.repository.IVehiculoRepository;

import java.util.List;


@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private IVehiculoRepository repo;


    @Override
    public List<Vehiculo> listarVehiculos() {
        return repo.findAll();
    }

    public void insertarVehiculo(Vehiculo vehiculo) {
        repo.save(vehiculo);
    }
}
