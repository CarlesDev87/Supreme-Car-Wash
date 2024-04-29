package supremecarwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import supremecarwash.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

   public Cliente findByNombreAndPassword(String nombrem, String password);
}
