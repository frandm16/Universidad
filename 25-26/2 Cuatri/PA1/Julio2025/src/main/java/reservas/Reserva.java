package reservas;


import java.util.Objects;

public class Reserva implements Comparable<Reserva>{
    public enum Franja{
        MAÑANA,
        TARDE,
        NOCHE
    }

    private int plazas;
    private Franja franja;

    public Reserva(int plazas, Franja franja){
        if(plazas <= 0){
            throw new ReservasException("El aforo tiene que ser positivo");
        }
        this.plazas = plazas;
        this.franja = franja;
    }

    public int getPlazas() {
        return plazas;
    }

    public Franja getFranja() {
        return franja;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this ||
                (obj instanceof Reserva aux && aux.plazas == this.plazas && aux.franja == this.franja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plazas, franja);
    }

    @Override
    public int compareTo(Reserva o){
        int res = Integer.compare(o.plazas, this.plazas);
        if(res == 0){
            res = this.franja.compareTo(o.franja);
        }
        return res;
    }

    @Override
    public String toString() {
        return "Reserva[plazas=" + this.plazas + ",franja=" + this.franja + "]";
    }
}
