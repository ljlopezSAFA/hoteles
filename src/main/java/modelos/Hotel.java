package modelos;

import enumerados.Categoria;
import enumerados.ServicioHotel;
import enumerados.TipoHabitacion;
import lombok.*;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Hotel {
    private String codigo;
    private String nombreCompleto;
    private String ubicacion;
    private Categoria categoria;
    private Integer numHabitaciones;
    private Integer estrellas;
    private Map<TipoHabitacion, Double> tipoHabitacionPrecio;
    private Map<TipoHabitacion, Integer> tipoHabitacionNumPersonas;
    private List<ServicioHotel> servicios;

}
