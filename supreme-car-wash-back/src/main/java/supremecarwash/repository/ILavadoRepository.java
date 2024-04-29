package supremecarwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supremecarwash.model.Lavado;

@Repository
public interface ILavadoRepository extends JpaRepository<Lavado, Integer> {
}
