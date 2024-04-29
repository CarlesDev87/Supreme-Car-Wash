package supremecarwash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Cliente;
import supremecarwash.repository.IClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository repo;

    @Override
    public List<Cliente> listarCLientes() {
        return repo.findAll();
    }

    @Override
    public void insertarCliente(Cliente cliente) {
        repo.save(cliente);
    }

    @Override
    public boolean loginCliente(String nombre, String password) {
        Cliente cliente = repo.findByNombreAndPassword(nombre, password);
        return cliente != null && cliente.getPassword().equals(password);
    }


}
