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

public class UtilidadesHotel {


    /**
     * EJERCICIO 1 (1 punto)
     * Devuelve los hoteles en cuyos servicios se encuentran al menos tres de los servicios requeridos
     *
     * @param hoteles
     * @param serviciosRequeridos
     * @return
     */
    public static List<Hotel> getConAlMenos3Servicios(List<Hotel> hoteles, List<ServicioHotel> serviciosRequeridos){

        List<Hotel> hotelesFiltrados = new ArrayList<>();


        return hotelesFiltrados;
    }


    /**
     * EJERCICIO 2 (1 punto)
     * Devuelve el número de reservas por tipo de habitación a parir de una lista de habitaciones.
     *
     * @param habitaciones
     * @return
     */
    public static Map<TipoHabitacion,Integer> getNumReservasPorTipoHabitacion(List<Habitacion> habitaciones){

        Map<TipoHabitacion, Integer> mapa = new HashMap<>();
        return mapa;
    }


    private static  FranjaEdad obtenerFranjaEdadPorAnyo(LocalDate fechaNacimiento){

        return  null;
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

        Map<FranjaEdad,Integer> mapaFinal = new HashMap<>();

        return mapaFinal;
    }




    private static  boolean isBetween(LocalDate fechaReferencia, LocalDate fecha1 , LocalDate fecha2){
        return false;
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

        return false;
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

     return null;
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
        return false;
    }











}
