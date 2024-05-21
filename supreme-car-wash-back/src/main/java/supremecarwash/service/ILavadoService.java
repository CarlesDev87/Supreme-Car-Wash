package supremecarwash.service;

import supremecarwash.model.Lavado;
import supremecarwash.model.dto.LavadoRequestDto;
import supremecarwash.model.dto.LavadoResponseDto;

import java.util.List;

public interface ILavadoService {

     List<Lavado> listarLavados();

     Lavado insertarLavado(LavadoRequestDto lavado);

     List<Lavado> getTipoLavados();

     List<LavadoResponseDto> buscarLavadosPorVehiculo(Integer idVehiculo);

}
