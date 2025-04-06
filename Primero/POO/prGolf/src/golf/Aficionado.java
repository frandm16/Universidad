package golf;

public class Aficionado extends Jugador {
    private int handicap;

    public Aficionado(String nombre, int handicap) {
        super(nombre);

        this.handicap = handicap;
        if (handicap < 1 || handicap > 54) {
            throw new RuntimeException("Handicap no valido");
        }


    }

    @Override
    public int puntuacion() {
        return super.puntuacion() - handicap;
    }

    @Override
    public String toString() {
        return super.toString() + ", handicap= " + handicap;
    }
}
