public class TestPunto {
    public static void main(String[] args){
        Punto p1 = new Punto(1,1);
        Punto p2 = new Punto(0,0);
        System.out.println("La distancia entre los puntos es: "
                + p1.distancia(p2));
        System.out.println("Trasladamos el primer punto (2,3)");
        p1.trasladar(2, 3);
        System.out.println("Ahora La distancia entre los puntos es: "
                + p1.distancia(p2));
    }
}