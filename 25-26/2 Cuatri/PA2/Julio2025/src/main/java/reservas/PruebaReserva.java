package reservas;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PruebaReserva {
    public static void main(String[] args) {
        try {
            Reserva r1 = new Reserva(6, Reserva.Franja.MAÑANA);
            Reserva r2 = new Reserva(4, Reserva.Franja.TARDE);
            Reserva r3 = new Reserva(2, Reserva.Franja.NOCHE);
            Reserva r4 = new Reserva(5, Reserva.Franja.TARDE);
            Reserva r5 = new Reserva(2, Reserva.Franja.NOCHE);
            Reserva r6 = new Reserva(Integer.parseInt(args[0]), Reserva.Franja.valueOf(args[1]));

            List<Reserva> lista = new ArrayList<>(List.of(r1, r2, r3, r4, r5, r6));
            SortedSet<Reserva> reservas = new TreeSet<>(new OrdenAlternativoReservas());
            reservas.addAll(lista);

            System.out.println(reservas);
        } catch (ArrayIndexOutOfBoundsException x){
            System.err.println("Error: Faltan datos");
        } catch (NumberFormatException n){
            System.err.println("Error: El numero de plazas debe ser un numero");
        } catch (IllegalArgumentException x){
            System.err.println("Error: La franja horaria debe ser MAÑANA, TARDE o NOCHE");
        } catch (ReservasException res){
            System.err.println("Error: " + res.getMessage());
        }
    }
}
