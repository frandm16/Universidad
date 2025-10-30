import java.util.Arrays;

public class AlumnoAventajadoRestricciones {
    public static class Res{
		public int sumaNotas;
		public int[] sol;

        public Res(){//Solución inválida
            sumaNotas = -1;
            sol = new int[0];
        }
		public Res(int sumaNotas, int[] sol) {//Solución válida
			this.sumaNotas = sumaNotas;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return sumaNotas +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.sumaNotas == aux.sumaNotas;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.sumaNotas);
		}
	}
    
	
	public static Res nota(int[][] C, int H) {
		int n = C.length;
		int NOTA_MINIMA = 5;
		int INF = Integer.MIN_VALUE / 2;
		int[][] dp = new int[n + 1][H + 1];
		int[][] horasAsignadas = new int[n + 1][H + 1];
		Res r = new Res(-1, new int[n]);
		
		for (int i = 0; i <= n; i++) {
		    for (int j = 0; j <= H; j++) {
		        dp[i][j] = INF;
		    }
		}
		dp[0][0] = 0;
		
		for (int i = 1; i <= n; i++) {
		    int maxHorasAsignatura = C[i-1].length;
		    
		    for (int h = 0; h <= H; h++) {
		        for (int j = 0; j < maxHorasAsignatura; j++) {
		            int horasRequeridas = j;
		            int notaObtenida = C[i - 1][j];
		            
		            if (notaObtenida >= NOTA_MINIMA && h >= horasRequeridas) {
		                int horasAnteriores = h - horasRequeridas;
		                
		                if (dp[i - 1][horasAnteriores] != INF) {
		                    int nuevaNota = dp[i - 1][horasAnteriores] + notaObtenida;
		                    
		                    if (nuevaNota > dp[i][h] || (nuevaNota == dp[i][h] && h > horasAsignadas[i][h])) {
		                        dp[i][h] = nuevaNota;
		                        horasAsignadas[i][h] = j;
		                    }
		                }
		            }
		        }
		    }
		}
		
		int maxNota = INF;
		int horasOptimas = -1;
		
		for (int h = 0; h <= H; h++) {
		    if (dp[n][h] > maxNota) {
		        maxNota = dp[n][h];
		        horasOptimas = h;
		    }
		}
		
		if (maxNota != INF) {
		    int[] solucionHoras = new int[n];
		    int h_actual = horasOptimas;
		    
		    for (int i = n; i >= 1; i--) {
		        int hAsignadas = horasAsignadas[i][h_actual];
		        solucionHoras[i - 1] = hAsignadas;
		        h_actual -= hAsignadas;
		    }
		    
		    int sumaAsignada = 0;
		    for (int i = 0; i < solucionHoras.length; i++) {
		        sumaAsignada += solucionHoras[i];
		    }
		    
		    int sobrante = H - sumaAsignada;
		    if (sobrante > 0) {
		        int i = n - 1;
		        while (i >= 0 && sobrante != 0) {
		            int maxHorasMateria = C[i].length - 1;
		            int posibleAgregar = Math.min(sobrante, maxHorasMateria - solucionHoras[i]);
		            solucionHoras[i] += posibleAgregar;
		            sobrante -= posibleAgregar;
		            i--;
		        }
		    }
		    
		    r = new Res(maxNota, solucionHoras);
		}
		
		return r;
	}
}//end AlumnoAventajado