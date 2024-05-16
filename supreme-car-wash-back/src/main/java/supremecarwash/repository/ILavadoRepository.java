package supremecarwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import supremecarwash.model.Lavado;

import java.util.List;

@Repository
public interface ILavadoRepository extends JpaRepository<Lavado, Integer> {

    @Query("SELECT distinct new Lavado(l.tipoLavado, l.descripcion, l.precio) FROM Lavado l")
    List<Lavado> encontrarTiposLavados();

    @Query("SELECT lavado FROM Lavado lavado WHERE lavado.vehiculo.id = :idVehiculo")
    List<Lavado> lavadosPorVehiculo(Integer idVehiculo);

}
