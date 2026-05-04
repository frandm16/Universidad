package procesiones;

import java.util.ArrayList;
import java.util.List;

public class TiempoMenor implements FiltradoUbicaciones {

    private int tiempoMaximo;

    public TiempoMenor(int tm) {
        this.tiempoMaximo = tm;
    }

    @Override
    public List<String> seleccionar(List<Ubicacion> ubicaciones) {
        List<String> seleccionadas = new ArrayList<>();

        for (Ubicacion ubic : ubicaciones) {
            if (ubic.getMinutos() <= tiempoMaximo) {
                seleccionadas.add(ubic.getNombre().toUpperCase());
            }
        }

        return seleccionadas;
    }
}