package supremecarwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import supremecarwash.model.Cliente;
import supremecarwash.service.IClienteService;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {


    Cliente findByEmailAndPassword(@RequestParam ("email") String email, @RequestParam("password") String password);

    @Query("SELECT c from Cliente c WHERE c.id = :clienteId")
    Cliente buscarClientePorId(Integer clienteId);


}
