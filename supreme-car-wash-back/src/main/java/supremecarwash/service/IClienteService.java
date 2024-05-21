package supremecarwash.service;

import supremecarwash.model.Cliente;
import supremecarwash.model.dto.ClienteRequestDto;
import supremecarwash.model.dto.ClienteResponseDto;

import java.util.List;

public interface IClienteService {

    List<Cliente> listarCLientes();

    Cliente insertarCliente(ClienteRequestDto cliente);

    ClienteResponseDto obtenerClientePorId(Integer id);

}
