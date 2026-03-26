package festivales;

import java.util.ArrayList;
import java.util.List;

public class Festival {
    private String nombre;
    private String ciudad;
    private List<Actuacion> actuaciones;
    private List<Actuacion> noProgramadas;
    public Festival(String n, String c) {
        this.nombre = n;
        this.ciudad = c;
        actuaciones = new ArrayList<>();
        noProgramadas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }
    public String getCiudad() {
        return  ciudad;
    }

    public int numActuaciones() {
        return actuaciones.size();
    }

    protected boolean seSolapa(Actuacion unaActuacion, Actuacion otraActuacion) {
        boolean seSolapan = false;
        if (unaActuacion.getHoraInicio() == otraActuacion.getHoraFin() ||
                otraActuacion.getHoraInicio() == unaActuacion.getHoraFin() ||
                (unaActuacion.getHoraInicio() <= otraActuacion.getHoraInicio() && unaActuacion.getHoraFin() >= otraActuacion.getHoraInicio()) ||
                (otraActuacion.getHoraInicio() <= unaActuacion.getHoraInicio() && otraActuacion.getHoraFin() >= unaActuacion.getHoraInicio())) {
            seSolapan = true;
        }
        return seSolapan;
    }
    public boolean seSolapa(Actuacion nuevaActuacion){
        boolean seSolapa=false;

        for(Actuacion a: actuaciones){
            if(!seSolapa){
                seSolapa=seSolapa(a, nuevaActuacion);
            }
        }

        return seSolapa;
    }

    private int buscarActuacion(String grupo) {
        boolean encontrado = false;

        int i = 0;
        while (!encontrado && i < actuaciones.size()) {
            encontrado = grupo.equalsIgnoreCase(actuaciones.get(i).getGrupo());
            i++;
        }
        return encontrado ? i - 1 : -1;
    }


    public void agregarActuacion(String grupo, String escenario, int horarioInicio, int duracion) {
        Actuacion nuevaActuacion = new Actuacion(grupo, escenario, horarioInicio, duracion);

        int idx = buscarActuacion(grupo);

        if (idx != -1) {
            actuaciones.remove(idx);
        }

        if(seSolapa(nuevaActuacion)){
            noProgramadas.add(nuevaActuacion);
        } else {
            actuaciones.add(nuevaActuacion);
        }
    }

    public void eliminarActuacion(String grupo){
        int idx;

        if ((idx = buscarActuacion(grupo)) != -1) {
            actuaciones.remove(idx);
        }
    }

    @Override
    public String toString() {
        return nombre + " en " + ciudad + ", Programadas; " + actuaciones.toString() + ", No programadas: " + noProgramadas.toString();
    }
}
