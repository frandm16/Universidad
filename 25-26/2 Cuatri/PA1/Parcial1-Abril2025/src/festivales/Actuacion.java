package festivales;

public class Actuacion {
    private String grupo;
    private String escenario;
    private int horaInicio;
    private int duracion;

    public Actuacion(String g, String e, int h, int d) {
        if(h<0 || h>23){
            throw new RuntimeException("La hora debe estar en el rango [0,23]");
        }
        if(d<1 || d>3){
            throw new RuntimeException("La duracion debe estar en el rango [1,3]");
        }
        if(h+d >=24){
            throw new RuntimeException("La actuacion no puede empezar tan tarde o durar tanto");
        }
        this.grupo = g;
        this.escenario = e;
        this.horaInicio = h;
        this.duracion = d;
    }

    public String getGrupo(){
        return grupo;
    }
    public String getEscenario(){
        return escenario;
    }
    public int getHoraInicio(){
        return horaInicio;
    }
    public int getHoraFin(){
        return horaInicio+duracion;
    }

    public void setHoraInicio(int nuevaHora){
        if(nuevaHora<0 || nuevaHora>23){
            throw new RuntimeException("La hora debe estar en el rango [0,23]");
        }
        if(nuevaHora+duracion >=24){
            throw new RuntimeException("La actuacion no puede empezar tan tarde o durar tanto");
        }
        horaInicio = nuevaHora;
    }

    @Override
    public String toString() {
        return getGrupo() + "(" + getEscenario() + ") Inicio: " + getHoraInicio() + "h Fin: " + getHoraFin() + "h" ;
    }
}
