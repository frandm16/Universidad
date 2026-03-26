package festivales;

public class FestivalConcurrente extends Festival{
    public FestivalConcurrente(String n, String c) {
        super(n, c);
    }

    @Override
    protected boolean seSolapa(Actuacion unaActuacion, Actuacion otraActuacion) {
        boolean seSolapa = false;
        if(otraActuacion.getEscenario().equals(unaActuacion.getEscenario())){
            seSolapa = super.seSolapa(unaActuacion, otraActuacion);
        }
        return seSolapa;
    }

    @Override
    public String toString() {
        return numActuaciones() + " actuaciones en " + super.toString();
    }
}
