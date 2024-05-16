package supremecarwash.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import supremecarwash.model.Vehiculo;

import java.time.LocalDate;

public class LavadoRequestDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaLavado;

    private String tipoLavado;

    private String descripcion;

    private Integer precio;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
}
