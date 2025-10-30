import java.util.Arrays;

public class TarifaTelefonica {

	private int tarifaPlana, permanencia, tarifaMegas;
	private int[] estimacion;
	private int[] pago;// Pago mínimo a realizar durante los meses de i...n
	
	// decisionesOptimas[i] guardará la elección inicial óptima en el mes i (0: MB, 1: Plana)
	private int[] decisionesOptimas;

	public TarifaTelefonica(int tp, int p, int tm, int[] est) {
		tarifaPlana = tp;
		permanencia = p;
		tarifaMegas = tm;
		estimacion = est;
		pago = null;
	}

	public int resolverBottomUp() {
		final int n = estimacion.length;
		pago = new int[n + 1]; 
		decisionesOptimas = new int[n];
		pago[n] = 0;
		
		for (int i = n - 1; i >= 0; i--) {
		    int costoMB = estimacion[i] * tarifaMegas + pago[i + 1];
		    pago[i] = costoMB;
		    decisionesOptimas[i] = 0;
		    
		    if (i + permanencia <= n) {
		        int costoPlana = permanencia * tarifaPlana + pago[i + permanencia];
		        
		        if (costoPlana < pago[i]) {
		            pago[i] = costoPlana;
		            decisionesOptimas[i] = 1;
		        }
		    }
		}
		return pago[0];
	}

	public int[] reconstruirSol() {
		if (pago == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		final int n = estimacion.length;
		int[] tarifasSeleccionadas = new int[n];
		
		int i = 0;
		while (i < n) {
		    int decisionMes = decisionesOptimas[i];
		    
		    if (decisionMes == 0) {
		        tarifasSeleccionadas[i] = 0;
		        i++;
		    } else {
		        for (int j = 0; j < permanencia; j++) {
		            if (i + j < n) {
		                tarifasSeleccionadas[i + j] = 1;
		            }
		        }
		        i+= permanencia;
		    }
		}
		return tarifasSeleccionadas;
	}

	public void imprimeVectorSolucion() {
		System.out.println(Arrays.toString(pago));
	}

}