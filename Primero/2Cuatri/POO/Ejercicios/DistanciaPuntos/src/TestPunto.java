import java.util.ArrayList;
import java.util.List;

public class TestPunto {
    public static void main(String[] args){
        Punto p1 = new Punto(1,1);
        Punto p2 = new Punto(0,0);
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        System.out.println("La lista antes de eliminar la pos 3: "+lista);
lista.remove(3);
        System.out.println("La lista despues de eliminar la pos 3: "+lista);

        System.out.println("La distancia entre los puntos es: "
                + p1.distancia(p2));
        System.out.println("Trasladamos el primer punto (2,3)");
        p1.trasladar(2, 3);
        System.out.println("Ahora La distancia entre los puntos es: "
                + p1.distancia(p2));
    }
}