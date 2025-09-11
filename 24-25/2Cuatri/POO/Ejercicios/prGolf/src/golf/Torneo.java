package golf;

import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private List<Jugador> inscritos;
    private int parCampo;

    public Torneo(List<Jugador> inscritos, int parCampo) {
        this.inscritos = new ArrayList<>();
        this.parCampo = parCampo;

    }


    private int busca(String nombre) {

        int pos = -1;
        for (int i = 0; i < inscritos.size() && pos == -1; i++) {

            if (inscritos.get(i).getNombre().equals(nombre)) {
                pos = i;
            }
        }
        return pos;
    }

    void agregaGolpes(String nombre, int glp) {

        int num = busca(nombre);
        if (num == -1) {
            throw new RuntimeException("No existe ese jugador");
        }
        inscritos.get(num).agregaGolpes(glp);


    }

    public int tarjetaDe(String nombre) {
        int diferencia = 0;
        int num = busca(nombre);
        if (num == -1) {
            throw new RuntimeException("No existe ese jugador");
        }
        diferencia = inscritos.get(num).puntuacion() - parCampo;

        return diferencia;
    }

    private int mejorTarjeta(List<String> aux) {
        int mejor = tarjetaDe(aux.get(0));
        int posicionMejor = 0;

        for (int i = 1; i < aux.size(); i++) {
            if (tarjetaDe(aux.get(i)) < mejor) {
                mejor = tarjetaDe(aux.get(i));
                posicionMejor = i;
            }
        }
        return posicionMejor;
    }

    public List<Jugador> clasificacion() {
        List<Jugador> clasificacion = new ArrayList<>();
        List<String> aux = new ArrayList<>();

        for (int i = 0; i < inscritos.size(); i++) {
            aux.set(i, inscritos.get(i).getNombre());
        }

        while (!aux.isEmpty()) {
            int pos = mejorTarjeta(aux);
            clasificacion.add(inscritos.get(busca(aux.get(pos))));
            aux.remove(pos);
        }

        return clasificacion;
    }

}
