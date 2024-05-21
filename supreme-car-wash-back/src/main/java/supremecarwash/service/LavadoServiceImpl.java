package supremecarwash.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supremecarwash.model.Lavado;
import supremecarwash.model.dto.LavadoRequestDto;
import supremecarwash.model.dto.LavadoResponseDto;
import supremecarwash.repository.ILavadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LavadoServiceImpl implements ILavadoService {

    @Autowired
    private ILavadoRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Lavado> listarLavados() {
        return repo.findAll();
    }

    @Override
    public Lavado insertarLavado(LavadoRequestDto lavado) {
        Lavado lavadoDto = modelMapper.map(lavado, Lavado.class);
        return repo.save(lavadoDto);

    }

    @Override
    public List<LavadoResponseDto> buscarLavadosPorVehiculo(Integer idVehiculo) {
        List<Lavado> lavados = repo.lavadosPorVehiculo(idVehiculo);

        return lavados.stream().map(lvs ->modelMapper.map(lvs, LavadoResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<Lavado> getTipoLavados() {
        return repo.encontrarTiposLavados();
    }
}
