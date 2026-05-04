package procesiones;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Procesion {
    private String cofradia;

    private List<Ubicacion> itinerario;

    public Procesion(String cof, String salida) {
        cofradia = cof;
        itinerario = new ArrayList<Ubicacion>();
        itinerario.add(new Ubicacion(salida, 0, 0));
    }

    public String getCofradia() {
        return cofradia;
    }

    public void agregarUbicacion(Ubicacion ubic){
        boolean encontrada = false;

        for (Ubicacion ubicacion : itinerario){
            if (ubicacion.getNombre().equals(ubic.getNombre())){
                ubicacion.setMinutos(ubic.getMinutos());
                ubicacion.setDistancia(ubic.getDistancia());
                encontrada = true;
                break;
            }
        }

        if (!encontrada){
            itinerario.add(ubic);
        }
    }

    public void agregarUbicacion(String infoUbicacion) {
        String[] items = infoUbicacion.split("#");
        if(items.length<3){
            throw new ProcesionesException("Formato incorrecto (faltan datos): " + infoUbicacion);
        }

        String nombre = items[0];
        int tiempo;
        int distancia;

        try {
            tiempo = Integer.parseInt(items[1]);
            distancia = Integer.parseInt(items[2]);
        } catch (NumberFormatException e) {
            throw new ProcesionesException("Formato incorrecto (dato no numérico): " + infoUbicacion);
        }

        if (tiempo < 0 || distancia < 0) {
            throw new ProcesionesException("Formato incorrecto (número negativo): " + infoUbicacion);
        }

        agregarUbicacion(new Ubicacion(nombre, tiempo, distancia));
    }

    public List<String> ubicacionesLejanas(int distancia){
        List<String> lista = new ArrayList<>();

        for(Ubicacion ubicacion : itinerario){
            if(ubicacion.getDistancia() > distancia){
                lista.add(ubicacion.getNombre().toUpperCase());
            }
        }

        return lista;
    }

    public List<String> seleccionarUbicaciones(FiltradoUbicaciones filtro) {
        return filtro.seleccionar(itinerario);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" -> ", "{ ", " }");
        for(Ubicacion ubicacion : itinerario){
            sj.add(ubicacion.toString());
        }
        return cofradia + " @ " + sj;
    }
}
