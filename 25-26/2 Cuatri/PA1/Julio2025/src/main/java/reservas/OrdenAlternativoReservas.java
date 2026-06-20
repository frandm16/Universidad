package reservas;

import java.util.Comparator;

public class OrdenAlternativoReservas implements Comparator<Reserva> {
    @Override
    public int compare(Reserva r1, Reserva r2) {
        int res = r1.getFranja().compareTo(r2.getFranja());
        if(res == 0){
            res = Integer.compare(r1.getPlazas(), r2.getPlazas());
        }
        return res;
    }
}
