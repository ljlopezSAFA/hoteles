package modelos;


import enumerados.TipoHabitacion;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Habitacion {
    private String codigo;
    private Hotel hotel;
    private TipoHabitacion tipoHabitacion;
    private List<Reserva> reservas;

}
