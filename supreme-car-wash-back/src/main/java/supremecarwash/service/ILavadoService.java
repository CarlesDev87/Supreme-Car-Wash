package supremecarwash.service;

import supremecarwash.model.Lavado;

import java.util.List;

public interface ILavadoService {

     List<Lavado> listarLavados();

     void insertarLavado(Lavado lavado);

     List<Lavado> getTipoLavados();
}
