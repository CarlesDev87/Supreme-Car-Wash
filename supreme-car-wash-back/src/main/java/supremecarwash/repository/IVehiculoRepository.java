package supremecarwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supremecarwash.model.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}
