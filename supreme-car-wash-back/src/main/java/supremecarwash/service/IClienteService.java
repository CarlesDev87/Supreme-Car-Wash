package supremecarwash.service;

import supremecarwash.model.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listarCLientes();

    void insertarCliente(Cliente cliente);

    boolean loginCliente(String nombre, String password);
}
