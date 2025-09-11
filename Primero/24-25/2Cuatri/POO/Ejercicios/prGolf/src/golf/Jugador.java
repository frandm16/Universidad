package golf;

public class Jugador {

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;
    private int numGolpes;

    public String getNombre() {
        return nombre;
    }

    public int puntuacion() {
        return numGolpes;
    }

    public void agregaGolpes(int glp) {
        numGolpes += glp;
    }

    @Override
    public String toString() {
        return "nombre= " + nombre + ", golpes= " + numGolpes;
    }
}
