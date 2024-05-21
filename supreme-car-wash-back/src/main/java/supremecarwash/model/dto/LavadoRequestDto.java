package supremecarwash.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import supremecarwash.model.Vehiculo;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LavadoRequestDto {


    private LocalDate fechaLavado;

    private String tipoLavado;

    private String descripcion;

    private Integer precio;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
}
