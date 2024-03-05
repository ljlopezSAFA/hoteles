package utilidades;

import enumerados.FranjaEdad;
import enumerados.ServicioHotel;
import enumerados.TipoHabitacion;
import modelos.Habitacion;
import modelos.Hotel;
import modelos.Reserva;
import modelos.Viajero;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesHotel1 {


    /**
     * EJERCICIO 1 (1 punto)
     * Devuelve los hoteles en cuyos servicios se encuentran al menos tres de los servicios requeridos
     *
     * @param hoteles
     * @param serviciosRequeridos
     * @return
     */
    public static List<Hotel> getConAlMenos3Servicios(List<Hotel> hoteles, List<ServicioHotel> serviciosRequeridos){

        List<Hotel> hotelesFiltro = new ArrayList<>();

        hoteles.forEach(h->{
            List<ServicioHotel> coindicendes = new ArrayList<>(h.getServicios());
            coindicendes.retainAll(serviciosRequeridos);
            if(coindicendes.size() >=3){
                hotelesFiltro.add(h);
            }
        });

        return hotelesFiltro;
    }


    /**
     * EJERCICIO 2 (1 punto)
     * Devuelve el número de reservas por tipo de habitación a partir de una lista de habitaciones.
     *
     * @param habitaciones
     * @return
     */
    public static Map<TipoHabitacion,Integer> getNumReservasPorTipoHabitacion(List<Habitacion> habitaciones){
        return habitaciones
                .stream()
                .collect(Collectors.groupingBy(Habitacion::getTipoHabitacion, Collectors.summingInt(h->h.getReservas().size())));
    }


    /**
     * EJERCICIO 3 (1.5 puntos)
     *
     * Devuelve un mapa con el número de viajeros por franja de edad, teniendo en cuenta su fecha de nacimiento,
     * las franjas de edad son las siguientes:
     * BABY -> 0-5 años
     * INFANTIL -> 6-12 años
     * JUVENIL -> 13-17 años
     * ADULTO -> 18-70 años
     * ANCIANO -> +70 años
     *
     * @param viajeros
     * @return
     */
    public static Map<FranjaEdad,Integer> getNumViajerosPorFranjaEdad(List<Viajero> viajeros){

        Map<FranjaEdad, Integer> mapaFinal = new HashMap<>();

        for(Viajero v : viajeros){

            int edadViajero = Period.between(v.getFechaNacimiento(),LocalDate.now()).getYears();
            FranjaEdad franjaEdad = edadViajero<6? FranjaEdad.BABY : edadViajero<13?  FranjaEdad.INFANTIL: edadViajero <18? FranjaEdad.JUVENIL: edadViajero<71? FranjaEdad.ADULTO: FranjaEdad.ANCIANO;

            if(mapaFinal.containsKey(franjaEdad)){
                mapaFinal.put(franjaEdad, mapaFinal.get(franjaEdad)+1);
            }else{
                mapaFinal.put(franjaEdad, 1);
            }
        }


        return mapaFinal;
    }


    private static boolean isBetween(LocalDate fecha1 , LocalDate fechaBetween , LocalDate fecha2){

        return fechaBetween.isAfter(fecha1) && fechaBetween.isBefore(fecha2);
    }


    /**
     * EJERCICIO 4 (1.5 puntos)
     * Devuelve true si la habitación que se pasa como parámetro no tiene ninguna reserva en el rango de días que se pasa
     * como parámetro a través de la fecha inicio y fin que se pasa
     *
     * @param fechaInicio
     * @param fechaFin
     * @param habitacion
     * @return
     */
    public static boolean habitacionDisponibleFechas(LocalDate fechaInicio , LocalDate fechaFin, Habitacion habitacion){


        for(Reserva r : habitacion.getReservas()){

            if((r.getFechaInicio().isBefore(fechaInicio) && isBetween(fechaInicio, r.getFechaFin(), fechaFin))
                    || (isBetween(fechaInicio, r.getFechaInicio(), fechaFin) && isBetween(fechaInicio, r.getFechaFin(), fechaFin))
                    || (isBetween(fechaInicio, r.getFechaInicio(), fechaFin) && r.getFechaFin().isAfter(fechaFin))
                    || r.getFechaInicio().isBefore(fechaInicio) && r.getFechaFin().isAfter(fechaFin)){

                return false;
            }
        }
        return true;
    }


    /**
     * EJERCICIO 5 (2.5 puntos)
     * Crea una reserva teniendo en cuenta los siguientes aspectos:
     * - El código de la reserva de rellenará con un código aleatorio único que empiece por "CR"
     * - El hotel de la reserva es el hotel pasado como parámetro
     * - La fecha de inicio de la reserva es la fechaInicio pasada como parámetro
     * - La fecha de fin de la reserva es la fechaFin pasada como parámetro
     * - La habitación de la reserva es la habitación que se pasa como parámetro
     * - El número de días de la reserva se calcula hayando el número de días que hay entre la fecha inicio y la fechaFin
     * - El precio de la reserva se calcula multiplicando el precio del tipo habitación de la habitacion seleccionada para el hotel de la reserva,
     *     multiplicado por el número de días y el número de viajeros.
     *
     * @param hotel
     * @param habitacion
     * @param fechaInicio
     * @param fechaFin
     * @param viajeros
     * @return
     */
    public static Reserva realizarReserva(Hotel hotel, Habitacion habitacion,
                                          LocalDate fechaInicio, LocalDate fechaFin, List<Viajero> viajeros){

        Reserva r = new Reserva();
        r.setHotel(hotel);
        r.setHabitacion(habitacion);
        r.setViajeros(viajeros);
        r.setFechaInicio(fechaInicio);
        r.setFechaFin(fechaFin);
        r.setCodigo("CR"+ r.hashCode());
        r.setNumDias(Period.between(fechaInicio,fechaFin).getDays());
        r.setPrecioTotal(hotel.getTipoHabitacionPrecio().get(habitacion.getTipoHabitacion())*r.getNumDias()* viajeros.size());
        return r;
    }


    /**
     * EJERCICIO 6 (2.5 puntos)
     *
     * Devuelve true si todas las reservas que se pasan como parámetro tienen su precio bien calculado, según la fórmula
     *  del ejercicio anterior, y además el número de viajeros de la reserva no supera el máximo de huéspedes de ese tipo de habitación para el hotel de la reserva.
     *  Si cualquiera de las reservas que se pasa como parámetro no cumple las conciones este método devolverá false
     *
     * @param reservas
     * @return
     */
    public static boolean reservasCorrectas(List<Reserva> reservas){

        return reservas.stream().noneMatch(
                r-> !r.getPrecioTotal().equals(r.getViajeros().size()* r.getNumDias()* r.getHotel().getTipoHabitacionPrecio().get(r.getHabitacion().getTipoHabitacion()))
                || (r.getViajeros().size() > r.getHotel().getTipoHabitacionNumPersonas().get(r.getHabitacion().getTipoHabitacion()))
        );
    }











}
