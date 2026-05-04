package procesiones;

import java.util.ArrayList;
import java.util.List;

public class DistanciaMayor implements FiltradoUbicaciones {

    private int distanciaMinima;

    public DistanciaMayor(int dm) {
        this.distanciaMinima = dm;
    }

    @Override
    public List<String> seleccionar(List<Ubicacion> ubicaciones) {
        List<String> seleccionadas = new ArrayList<>();

        for (Ubicacion ubic : ubicaciones) {
            if (ubic.getDistancia() >= distanciaMinima) {
                seleccionadas.add(ubic.getNombre().toUpperCase());
            }
        }

        return seleccionadas;
    }
}