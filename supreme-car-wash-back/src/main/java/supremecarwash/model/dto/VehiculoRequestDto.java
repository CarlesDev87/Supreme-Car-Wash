package supremecarwash.model.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import supremecarwash.model.Cliente;

public class VehiculoRequestDto {

    private String marca;

    private String modelo;

    private String matricula;

    private String color;

    private String anyo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
