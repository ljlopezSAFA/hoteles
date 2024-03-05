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

        for(Hotel h: hoteles){

            //RETAINALL

            //Sacar los servicios que tiene el hotel
            List<ServicioHotel> serviciosHotel = new ArrayList<>(h.getServicios());

            //Nos quedamos con todos los servicios coincidentes
            serviciosHotel.retainAll(serviciosRequeridos);

            if(serviciosHotel.size() >=3){
                hotelesFiltrados.add(h);
            }
        }


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

        for(Habitacion hab : habitaciones){

            if(mapa.containsKey(hab.getTipoHabitacion())){
                mapa.put(hab.getTipoHabitacion(), mapa.get(hab.getTipoHabitacion()) + hab.getReservas().size());
            }else{
                mapa.put(hab.getTipoHabitacion(),  hab.getReservas().size());
            }
        }
        return mapa;
    }


    private static  FranjaEdad obtenerFranjaEdadPorAnyo(LocalDate fechaNacimiento){

        Integer anyos = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        FranjaEdad franjaEdad;

        if(anyos < 6){
            franjaEdad = FranjaEdad.BABY;
        }else if( anyos < 13 ){
            franjaEdad = FranjaEdad.INFANTIL;
        }else if( anyos < 18 ){
            franjaEdad = FranjaEdad.JUVENIL;
        }else if( anyos < 71 ){
            franjaEdad = FranjaEdad.ADULTO;
        }else{
            franjaEdad = FranjaEdad.ANCIANO;
        }

        return  franjaEdad;
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

        for(Viajero v: viajeros){

            //Franja Edad
            FranjaEdad franjaEdad = obtenerFranjaEdadPorAnyo(v.getFechaNacimiento());

            //Mapa
            if(mapaFinal.containsKey(franjaEdad)){
                mapaFinal.put(franjaEdad, mapaFinal.get(franjaEdad) +1);
            }else{
                mapaFinal.put(franjaEdad, 1);
            }

        }

        return mapaFinal;
    }




    private static  boolean isBetween(LocalDate fechaReferencia, LocalDate fecha1 , LocalDate fecha2){
        return fechaReferencia.isAfter(fecha1) && fechaReferencia.isBefore(fecha2);
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


            if(     (r.getFechaInicio().isBefore(fechaInicio) && isBetween(r.getFechaFin(), fechaInicio,fechaFin) )
                    ||
                    ( isBetween(r.getFechaInicio(), fechaInicio, fechaFin) &&   isBetween(r.getFechaFin(), fechaInicio, fechaFin))
                    ||
                    (isBetween(r.getFechaInicio(), fechaInicio, fechaFin) && r.getFechaFin().isAfter(fechaFin))
                    ||
                    (r.getFechaInicio().isBefore(fechaInicio) && r.getFechaFin().isAfter(fechaFin))
            ){

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

        Reserva reserva = new Reserva();

        //RELLENAR CAMPOS POR DEFECTO
        reserva.setHotel(hotel);
        reserva.setHabitacion(habitacion);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setViajeros(viajeros);

        //CALCULADOS
        reserva.setCodigo("CR"+ reserva.hashCode());
        reserva.setNumDias(Period.between(fechaInicio,fechaFin).getDays());
        reserva.setPrecioTotal( reserva.getNumDias() * reserva.getViajeros().size() * hotel.getTipoHabitacionPrecio().get(habitacion.getTipoHabitacion()) );

        return reserva;
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


        for(Reserva r: reservas){

            //Precio Objetivo / Ideal
            Double precioIdeal = r.getNumDias() * r.getViajeros().size() * r.getHotel().getTipoHabitacionPrecio().get(r.getHabitacion().getTipoHabitacion());
            Double precioReal = r.getPrecioTotal();

            //Numero de Huéspedes
            Integer numHuespedesMaximo = r.getHotel().getTipoHabitacionNumPersonas().get(r.getHabitacion().getTipoHabitacion());
            Integer numReal = r.getViajeros().size();


            if( (! precioIdeal.equals(precioReal))  || numReal> numHuespedesMaximo){
                return false;
            }


        }
        return true;
    }











}
