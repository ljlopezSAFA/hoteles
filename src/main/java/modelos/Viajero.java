package modelos;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Viajero {
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;

}
