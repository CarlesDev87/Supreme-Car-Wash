package supremecarwash.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column
    private String matricula;

    @Column
    private String color;

    @Column
    private String anyo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}