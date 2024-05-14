package supremecarwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Lavado;
import supremecarwash.repository.ILavadoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LavadoServiceImpl implements ILavadoService {

    @Autowired
    private ILavadoRepository repo;

    @Override
    public List<Lavado> listarLavados() {
        return repo.findAll();
    }

    @Override
    public void insertarLavado(Lavado lavado) {
        repo.save(lavado);
    }

    @Override
    public List<Lavado> getTipoLavados() {

        return repo.encontrarTiposLavados();
    }
}
