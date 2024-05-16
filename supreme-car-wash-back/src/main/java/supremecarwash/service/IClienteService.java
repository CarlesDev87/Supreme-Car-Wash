package supremecarwash.service;

import supremecarwash.model.Cliente;
import supremecarwash.model.dto.ClienteResponseDto;

import java.util.List;

public interface IClienteService {

    List<Cliente> listarCLientes();

    void insertarCliente(Cliente cliente);

    ClienteResponseDto obtenerClientePorId(Integer id);

}
