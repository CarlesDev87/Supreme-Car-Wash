package supremecarwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import supremecarwash.model.Vehiculo;

import java.util.List;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {


    @Query("SELECT vehiculo FROM Vehiculo vehiculo WHERE vehiculo.cliente.id = :clienteId")
    List<Vehiculo> vehiculosPorCliente(Integer clienteId);

    Vehiculo findVehiculoById(Integer id);


}
