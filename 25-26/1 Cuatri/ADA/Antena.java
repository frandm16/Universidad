import java.util.Arrays;
/*
 * Se desea construir una antena de telefonía de M metros de altura. 
 * Para ello se dispone de un suministro finito de bloques de armazón de n tipos distintos, cada uno de altura a[i] y peso p[i] . 
 * El objetivo es determinar cuántos bloques hay que emplear de cada tipo, de manera que la antena mida exactamente M metros y tenga el peso mínimo. 
 * 
 * */
public class Antena {
    public static class Res{
		public int peso;
		public int[] sol;

		public Res(int peso, int[] sol) {
			this.peso = peso;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return peso +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.peso == aux.peso;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.peso);
		}
	}
	
	//Precondición: peso.length = altura.length = cantidad.length
	public static Res antena(int[] peso, int [] altura, int[] cantidad, int M){
	    int n = peso.length;
        int INF = Integer.MAX_VALUE; 
    
        int[][] dp = new int[n + 1][M + 1];
        int[][] usado = new int[n + 1][M + 1];
        int[][] anterior = new int[n + 1][M + 1];
        Res r = new Res(-1, new int[n]);
    
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = INF;
                usado[i][j] = 0;
                anterior[i][j] = j;
            }
        }
        dp[0][0] = 0;
    
        for (int i = 1; i <= n; i++) {
            int h = altura[i - 1];
            int w = peso[i - 1];
            int c = cantidad[i - 1];
            
            for (int j = 0; j <= M; j++) {
                dp[i][j] = dp[i - 1][j];
                anterior[i][j] = j; 
                usado[i][j] = 0;
            }
            
            for (int j = 1; j <= M; j++) { 
                for (int k = 1; k <= c; k++) {
                    if (j - k * h >= 0) {
                        int alturaAnterior = j - k * h;
                        if (dp[i - 1][alturaAnterior] != INF) {
                            int nuevoPeso = dp[i - 1][alturaAnterior] + k * w;
                            
                            if (nuevoPeso < dp[i][j]) { 
                                dp[i][j] = nuevoPeso;
                                anterior[i][j] = alturaAnterior;
                                usado[i][j] = k;
                            }
                        }
                    }
                }
            }
        }
        
        if (dp[n][M] != INF) {
            int[] sol = new int[n];
            int h_actual = M;
            for (int i = n; i >= 1; i--) {
                int k = usado[i][h_actual];
                sol[i - 1] = k;
                h_actual = anterior[i][h_actual];
            }
            r = new Res(dp[n][M], sol);
        }
    
        return r;
	}
}//end Antena