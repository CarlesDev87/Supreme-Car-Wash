package supremecarwash.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Cliente;
import supremecarwash.model.dto.ClienteRequestDto;
import supremecarwash.model.dto.ClienteResponseDto;
import supremecarwash.repository.IClienteRepository;
import java.util.List;


@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Cliente> listarCLientes() {
        return repo.findAll();
    }

    @Override
    public Cliente insertarCliente(ClienteRequestDto cliente) {
        Cliente clienteDto = modelMapper.map(cliente, Cliente.class);
        return repo.save(clienteDto);
    }

    @Override
    public ClienteResponseDto obtenerClientePorId(Integer id) {
        Cliente cliente = repo.buscarClientePorId(id);
        return modelMapper.map(cliente, ClienteResponseDto.class);
    }
}
