package supremecarwash.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lavado {

    public Lavado(String tipoLavado, String descripcion, Integer precio) {
        this.tipoLavado = tipoLavado;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaLavado;

    @Column
    private String tipoLavado;

    @Column
    private String descripcion;

    @Column
    private Integer precio;

    @Column
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

}
