package modelos;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"viajeros","hotel", "habitacion"})
@ToString
public class Reserva {
    private String codigo;
    private Hotel hotel;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numDias;
    private Double precioTotal;
    private List<Viajero> viajeros;


}
