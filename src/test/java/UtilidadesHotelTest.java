import enumerados.FranjaEdad;
import enumerados.ServicioHotel;
import enumerados.TipoHabitacion;
import modelos.Habitacion;
import modelos.Hotel;
import modelos.Reserva;
import modelos.Viajero;
import org.junit.Test;
import utilidades.UtilidadesHotel;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

public class UtilidadesHotelTest {


    @Test
    public void testGetConAlMenos3Servicios() {
        // Crear algunos servicios requeridos
        List<ServicioHotel> serviciosRequeridos = Arrays.asList(
                ServicioHotel.WIFI, ServicioHotel.APARCAMIENTO, ServicioHotel.PISCINA, ServicioHotel.SPA
        );

        // Crear hoteles con diferentes combinaciones de servicios
        Hotel hotel1 = new Hotel();
        hotel1.setServicios(Arrays.asList(ServicioHotel.WIFI, ServicioHotel.APARCAMIENTO, ServicioHotel.PISCINA, ServicioHotel.GIMNASIO));

        Hotel hotel2 = new Hotel();
        hotel2.setServicios(Arrays.asList(ServicioHotel.WIFI, ServicioHotel.APARCAMIENTO, ServicioHotel.SPA));

        Hotel hotel3 = new Hotel();
        hotel3.setServicios(Arrays.asList(ServicioHotel.WIFI, ServicioHotel.APARCAMIENTO));

        Hotel hotel4 = new Hotel();
        hotel4.setServicios(Arrays.asList(ServicioHotel.WIFI, ServicioHotel.LAVANDERIA, ServicioHotel.CLIMATIZACION));

        // Crear una lista de hoteles para probar
        List<Hotel> hoteles = Arrays.asList(hotel1, hotel2, hotel3, hotel4);

        // Llamar a la función y obtener el resultado
        List<Hotel> resultado = UtilidadesHotel.getConAlMenos3Servicios(hoteles, serviciosRequeridos);

        // Verificar el resultado esperado
        assertEquals(Arrays.asList(hotel1, hotel2), resultado);
    }


    @Test
    public void testGetNumReservasPorTipoHabitacion() {
        // Crear habitaciones con diferentes tipos
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setTipoHabitacion(TipoHabitacion.INDIVIDUAL);
        habitacion1.setReservas(Arrays.asList(
                new Reserva(), new Reserva()
        ));

        Habitacion habitacion2 = new Habitacion();
        habitacion2.setTipoHabitacion(TipoHabitacion.DOBLE);
        habitacion2.setReservas(Arrays.asList(
                new Reserva(), new Reserva(), new Reserva()
        ));

        Habitacion habitacion3 = new Habitacion();
        habitacion3.setTipoHabitacion(TipoHabitacion.SUITE);
        habitacion3.setReservas(List.of(
                new Reserva()
        ));

        Habitacion habitacion4 = new Habitacion();
        habitacion3.setTipoHabitacion(TipoHabitacion.SUITE);
        habitacion3.setReservas(List.of(
                new Reserva(), new Reserva()
        ));

        // Crear una lista de habitaciones para probar
        List<Habitacion> habitaciones = Arrays.asList(habitacion1, habitacion2, habitacion3,habitacion4);

        // Llamar a la función y obtener el resultado
        Map<TipoHabitacion, Integer> resultado = UtilidadesHotel.getNumReservasPorTipoHabitacion(habitaciones);

        // Verificar el resultado esperado
        Map<TipoHabitacion, Integer> esperado = new HashMap<>();
        esperado.put(TipoHabitacion.INDIVIDUAL, 2);
        esperado.put(TipoHabitacion.DOBLE, 3);
        esperado.put(TipoHabitacion.SUITE, 3);

        assertEquals(esperado, resultado);
    }


    @Test
    public void testGetNumViajerosPorFranjaEdad() {
        // Crear viajeros con diferentes edades
        Viajero viajero1 = new Viajero();
        viajero1.setFechaNacimiento(LocalDate.now().minusYears(2)); // 2 años

        Viajero viajero2 = new Viajero();
        viajero2.setFechaNacimiento(LocalDate.now().minusYears(10)); // 10 años

        Viajero viajero3 = new Viajero();
        viajero3.setFechaNacimiento(LocalDate.now().minusYears(15)); // 15 años

        Viajero viajero4 = new Viajero();
        viajero4.setFechaNacimiento(LocalDate.now().minusYears(30)); // 30 años

        Viajero viajero5 = new Viajero();
        viajero5.setFechaNacimiento(LocalDate.now().minusYears(75)); // 75 años

        Viajero viajero6 = new Viajero();
        viajero6.setFechaNacimiento(LocalDate.now().minusYears(45)); // 45 años

        // Crear una lista de viajeros para probar
        List<Viajero> viajeros = Arrays.asList(viajero1, viajero2, viajero3, viajero4, viajero5,viajero6);

        // Llamar a la función y obtener el resultado
        Map<FranjaEdad, Integer> resultado = UtilidadesHotel.getNumViajerosPorFranjaEdad(viajeros);

        // Verificar el resultado esperado
        Map<FranjaEdad, Integer> esperado = new HashMap<>();
        esperado.put(FranjaEdad.BABY, 1);
        esperado.put(FranjaEdad.INFANTIL, 1);
        esperado.put(FranjaEdad.JUVENIL, 1);
        esperado.put(FranjaEdad.ADULTO, 2);
        esperado.put(FranjaEdad.ANCIANO, 1);

        assertEquals(esperado, resultado);
    }

    @Test
    public void testHabitacionDisponibleFechas() {
        // Crear reservas con diferentes fechas
        Reserva reserva1 = new Reserva();
        reserva1.setFechaInicio(LocalDate.now().plusDays(5)); // En 5 días
        reserva1.setFechaFin(LocalDate.now().plusDays(10)); // En 10 días

        Reserva reserva2 = new Reserva();
        reserva2.setFechaInicio(LocalDate.now().plusDays(15)); // En 15 días
        reserva2.setFechaFin(LocalDate.now().plusDays(20)); // En 20 días

        Reserva reserva3 = new Reserva();
        reserva3.setFechaInicio(LocalDate.now().minusDays(5)); // Hace 5 días
        reserva3.setFechaFin(LocalDate.now().minusDays(1)); // Hace 1 día

        // Crear una habitación con reservas
        Habitacion habitacion = new Habitacion();
        habitacion.setReservas(Arrays.asList(reserva1, reserva2));

        // Llamar a la función con fechas que no coinciden con ninguna reserva
        boolean resultado1 = UtilidadesHotel.habitacionDisponibleFechas(LocalDate.now().minusDays(3), LocalDate.now().minusDays(1), habitacion);
        assertTrue(resultado1);

        // Llamar a la función con fechas que coinciden con una reserva
        boolean resultado2 = UtilidadesHotel.habitacionDisponibleFechas(LocalDate.now().plusDays(8), LocalDate.now().plusDays(12), habitacion);
        assertFalse(resultado2);

        // Llamar a la función con fechas que coinciden con ninguna reserva
        boolean resultado3 = UtilidadesHotel.habitacionDisponibleFechas(LocalDate.now().plusDays(25), LocalDate.now().plusDays(30), habitacion);
        assertTrue(resultado3);
    }


    @Test
    public void testRealizarReserva() {
        // Crear un hotel con precios por tipo de habitación
        Hotel hotel = new Hotel();
        Map<TipoHabitacion, Double> mapa = new HashMap<>();
        mapa.put(TipoHabitacion.INDIVIDUAL, 50.0);
        mapa.put(TipoHabitacion.DOBLE, 80.0);
        mapa.put(TipoHabitacion.SUITE, 120.0);
        hotel.setTipoHabitacionPrecio(mapa);

        // Crear una habitación en el hotel
        Habitacion habitacion = new Habitacion();
        habitacion.setCodigo("HAB001");
        habitacion.setTipoHabitacion(TipoHabitacion.DOBLE);

        // Crear una lista de viajeros
        List<Viajero> viajeros = Arrays.asList(
                new Viajero(), new Viajero(), new Viajero()
        );

        // Llamar a la función para realizar la reserva
        Reserva reserva = UtilidadesHotel.realizarReserva(hotel, habitacion,
                LocalDate.now().plusDays(5), LocalDate.now().plusDays(10), viajeros);

        // Verificar que la reserva se ha creado correctamente
        assertNotNull(reserva);
        assertEquals(hotel, reserva.getHotel());
        assertEquals(habitacion, reserva.getHabitacion());
        assertEquals(LocalDate.now().plusDays(5), reserva.getFechaInicio());
        assertEquals(LocalDate.now().plusDays(10), reserva.getFechaFin());
        assertEquals(Optional.of(6), reserva.getNumDias()); // El rango de fechas tiene 6 días
        assertEquals("CR", reserva.getCodigo().substring(0, 2)); // Comprueba que el código comienza con "CR"
        assertEquals(viajeros, reserva.getViajeros());

        // Calcular el precio esperado
        double precioEsperado = hotel.getTipoHabitacionPrecio().get(habitacion.getTipoHabitacion()) * 6 * viajeros.size();
        assertEquals(Optional.of(precioEsperado), reserva.getPrecioTotal());
    }



    @Test
    public void testReservasCorrectas() {
        // Crear un hotel con límites de huéspedes por tipo de habitación
        Hotel hotel = new Hotel();
        Map<TipoHabitacion, Integer> mapa = new HashMap<>();
        mapa.put(TipoHabitacion.INDIVIDUAL, 1);
        mapa.put(TipoHabitacion.DOBLE, 2);
        mapa.put(TipoHabitacion.SUITE, 4);
        hotel.setTipoHabitacionNumPersonas(mapa);

        Map<TipoHabitacion, Double> precios = new HashMap<>();
        precios.put(TipoHabitacion.INDIVIDUAL, 50.0);
        precios.put(TipoHabitacion.DOBLE, 80.0);
        precios.put(TipoHabitacion.SUITE, 120.0);
        hotel.setTipoHabitacionPrecio(precios);

        // Crear una lista de habitaciones con diferentes tipos
        Habitacion habitacion1 = new Habitacion();
        habitacion1.setTipoHabitacion(TipoHabitacion.INDIVIDUAL);

        Habitacion habitacion2 = new Habitacion();
        habitacion2.setTipoHabitacion(TipoHabitacion.DOBLE);

        Habitacion habitacion3 = new Habitacion();
        habitacion3.setTipoHabitacion(TipoHabitacion.SUITE);

        // Crear una lista de reservas con diferentes precios y números de viajeros
        Reserva reserva1 = new Reserva();
        reserva1.setHotel(hotel);
        reserva1.setHabitacion(habitacion1);
        reserva1.setNumDias(5);
        reserva1.setViajeros(Arrays.asList(new Viajero()));
        reserva1.setPrecioTotal(250.0);

        Reserva reserva2 = new Reserva();
        reserva2.setHotel(hotel);
        reserva2.setHabitacion(habitacion2);
        reserva2.setNumDias(10);
        reserva2.setViajeros(Arrays.asList(new Viajero(), new Viajero()));
        reserva2.setPrecioTotal(1600.0);

        Reserva reservaIncorrecta1 = new Reserva();
        reservaIncorrecta1.setHotel(hotel);
        reservaIncorrecta1.setHabitacion(habitacion3);
        reservaIncorrecta1.setNumDias(1);
        reservaIncorrecta1.setViajeros(Arrays.asList(new Viajero(), new Viajero(), new Viajero(), new Viajero()));
        reservaIncorrecta1.setPrecioTotal(15.0); //precion Incorrecto

        Reserva reservaIncorrecta2 = new Reserva();
        reservaIncorrecta2.setHotel(hotel);
        reservaIncorrecta2.setHabitacion(habitacion3); // Tipo de habitación con capacidad para 4 personas, pero se intentan reservar 5 personas.
        reservaIncorrecta2.setNumDias(10);
        reservaIncorrecta2.setViajeros(Arrays.asList(new Viajero(), new Viajero(), new Viajero(), new Viajero(), new Viajero()));
        reservaIncorrecta2.setPrecioTotal(6000.0);

        // Crear una lista de reservas para probar
        List<Reserva> reservasCorrectos = Arrays.asList(reserva1, reserva2);
        List<Reserva> reservasIncorrectas = Arrays.asList(reservaIncorrecta1, reserva2);
        List<Reserva> reservasIncorrectas2 = Arrays.asList(reservaIncorrecta2, reserva1);


        // Llamar a la función y obtener el resultado
        boolean resultado = UtilidadesHotel.reservasCorrectas(reservasCorrectos);
        boolean resultado1 = UtilidadesHotel.reservasCorrectas(reservasIncorrectas);
        boolean resultado2 = UtilidadesHotel.reservasCorrectas(reservasIncorrectas2);

        // Verificar el resultado esperado
        assertTrue(resultado);
        assertFalse(resultado1);
        assertFalse(resultado2);
    }



}
