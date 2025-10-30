import java.util.Arrays;

public class AlumnoAventajado {
    public static class Res{
		public int sumaNotas;
		public int[] sol;

		public Res(int sumaNotas, int[] sol) {
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
		int[][] dp = new int[n + 1][H + 1];
		int[][] horasAsignadas = new int[n + 1][H + 1];
		int INF = Integer.MIN_VALUE / 2;
		Res r = new Res(-1, new int[n]);
		
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], INF);
		}
		dp[0][0] = 0;
		
		for (int i = 1; i <= n; i++) {
			int maxHorasAsignatura = C[i-1].length;
			
			for (int h = 0; h <= H; h++) {
				for (int j = 0; j < maxHorasAsignatura; j++) {
					int horasRequeridas = j;
					
					if (h >= horasRequeridas) {
						int horasAnteriores = h - horasRequeridas;
						
						if (dp[i - 1][horasAnteriores] != INF) {
							int notaObtenida = C[i - 1][j];
							int nuevaNota = dp[i - 1][horasAnteriores] + notaObtenida;
							
							if (nuevaNota > dp[i][h]) {
								dp[i][h] = nuevaNota;
								horasAsignadas[i][h] = j; 
							}
						}
					}
				}
			}
		}
		
		int maxNota = dp[n][H];
		int horasRestantes = H;
		int[] solucionHoras = new int[n];
		
        for (int h = 0; h <= H; h++) {
            if (dp[n][h] > maxNota) {
                maxNota = dp[n][h];
                horasRestantes = h;
            }
        }

		if (maxNota != INF) {
            for (int i = n; i >= 1; i--) {
    			int hAsignadas = horasAsignadas[i][horasRestantes];
    			solucionHoras[i - 1] = hAsignadas;
    			horasRestantes = horasRestantes - hAsignadas;
    		}
    		r = new Res(maxNota, solucionHoras);
        }
		
		return r;
	}
}//end AlumnoAventajado