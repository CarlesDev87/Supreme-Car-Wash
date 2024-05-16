package supremecarwash.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteRequestDto {

    private int id;

    private String nombre;

    private String apellido;

    private String direccion;

    private String telefono;

    private String email;

    private String password;
}
