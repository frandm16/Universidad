package reservas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import reservas.Reserva.Franja;


public class Establecimiento {
    private static int PLAZAS_GRUPO = 15;

    private String nombre;
    private int aforo;
    private Map<String, List<Reserva>> reservas;

    public Establecimiento(String nombre, int aforo) {
        if(aforo <= 0){
            throw new ReservasException("El aforo tiene que ser positivo");
        }
        this.nombre = nombre;
        this.aforo = aforo;
        reservas = new HashMap<>();
    }

    public int getPlazasDisponibles(Franja franja){
         int contador = 0;
        for(List<Reserva> aux : reservas.values()){
            for(Reserva reserva : aux){
                if(reserva.getFranja() == franja){
                    contador += reserva.getPlazas();
                }
            }
        }
        return aforo - contador;
    }

    public void agregarReserva(String cliente, Reserva reserva){
        if(getPlazasDisponibles(reserva.getFranja()) >= reserva.getPlazas()){
            reservas.putIfAbsent(cliente, new ArrayList<>());
            List<Reserva> aux = reservas.get(cliente);
            aux.add(reserva);
            reservas.put(cliente, aux);
        }
    }

    public void agregarReserva(String nombreFichero) throws IOException {
        Path path = Path.of(nombreFichero);

        for(String linea : Files.readAllLines(path)){
            try {
                Scanner sc = new Scanner(linea);
                    sc.useDelimiter("\\s*[:-]\\s*");

                    String cliente = sc.next();
                    int plazas = sc.nextInt();
                    Franja franja = Franja.valueOf(sc.next());

                    agregarReserva(cliente, new Reserva(plazas, franja));

            } catch (NoSuchElementException e) {
                System.err.println("Fichero no encontrado");
            }
        }

    }

    public List<Reserva> reservasDeGrupo(){
        List<Reserva> lista = new ArrayList<>();
        for(List<Reserva> aux : reservas.values()){

            for(Reserva reserva : aux){
                if(reserva.getPlazas() >= PLAZAS_GRUPO){
                    lista.add(reserva);
                }
            }
        }
        return lista;
    }
}
