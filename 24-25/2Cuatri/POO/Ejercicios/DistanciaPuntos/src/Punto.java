
public class Punto {
    private double x, y;
    public Punto(double a, double b) { x = a; y = b; }
    public void trasladar(double a, double b) {
        x += a; y += b;
    }
    public double distancia(Punto pto) {
        return Math.sqrt(Math.pow(x - pto.x, 2) +
                Math.pow(y - pto.y, 2));
    }
}