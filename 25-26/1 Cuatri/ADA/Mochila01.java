import java.util.Arrays;

/*
 * Se dispone de n objetos diferentes, cada uno con un peso w_i y un beneficio v_i. 
 * También se dispone de una mochila en la que se pueden meter dichos objetos, con una capacidad de peso máximo M. 
 * Sin perdida de generalidad se supondrá que todos los valores son > 0. 
 * El objetivo consiste en maximizar el valor de los objetos incluidos en la mochila,
 * respetando la limitación de capacidad máxima M.
 * 
 * */
public class Mochila01 {
    public static class Res{
		public int beneficio;
		public int[] sol;

		public Res(int beneficio, int[] sol) {
			this.beneficio = beneficio;
			this.sol = Arrays.copyOf(sol, sol.length);
		}

		@Override
		public String toString() {
			return beneficio +" "+Arrays.toString(sol);
		}
		
		@Override
		public boolean equals(Object obj) {
			boolean ok = this == obj;
			if (!ok && obj instanceof Res) {
				Res aux = (Res)obj;
				
				ok = this.beneficio == aux.beneficio;
				
			}
			return ok;
		}
		
		@Override
		public int hashCode() {
			return Integer.hashCode(this.beneficio);
		}
	}
	public static Res mochila01(Integer[] peso, Integer[] beneficio, int W) {
		final int N = peso.length;
		int M = W;
		int[][] T = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
		 	int p_i = peso[i - 1];
		 	int v_i = beneficio[i - 1]; 
		 	
		 	for (int w = 1; w <= M; w++) {
		 		int opcionNoIncluir = T[i - 1][w];
		 		if (p_i <= w) {
		 			int opcionIncluir = v_i + T[i - 1][w - p_i];
					T[i][w] = Math.max(opcionNoIncluir, opcionIncluir);
		 		} else {
		 			T[i][w] = opcionNoIncluir;
		 		}
		 	}
		}
		
		int maxBeneficio = T[N][M];
		int[] sol = new int[N];
		int w = W;
		
		for (int i = N; i > 0; i--) {
            if (T[i][w] != T[i - 1][w]) {
                sol[i - 1] = 1;
                w -= peso[i - 1];
            }
        }
		return new Res(maxBeneficio, sol);
	}
}//end class Mochila01