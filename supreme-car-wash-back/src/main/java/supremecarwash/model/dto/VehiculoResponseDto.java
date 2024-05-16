package supremecarwash.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import supremecarwash.model.Cliente;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoResponseDto {

    private int id;

    private String marca;

    private String modelo;

    private String matricula;

    private String color;

    private String anyo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
