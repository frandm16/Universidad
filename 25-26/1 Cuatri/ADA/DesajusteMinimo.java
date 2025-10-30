public class DesajusteMinimo {
    
	public static int desajusteMinimo(int[]p){
		int n = p.length;
		
		int sumaTotal = 0;
		for (int i = 0; i < n; i++) {
		    sumaTotal += p[i];
		}
		
		int objetivo = sumaTotal / 2;
		boolean[][] dp = new boolean[n + 1][objetivo + 1];
		
		for (int i = 0; i <= n; i++) {
		    dp[i][0] = true;
		}
		
		for (int i = 1; i <= n; i++) {
		    for (int j = 1; j <= objetivo; j++) {
		        dp[i][j] = dp[i - 1][j];
		        if (j >= p[i - 1]) {
		            dp[i][j] = dp[i][j] || dp[i - 1][j - p[i - 1]];
		        }
		    }
		}
		
		int resultado = 0;
		int j = objetivo;
		boolean encontrado = false;
		while (j >= 0 && !encontrado) {
		    if (dp[n][j]) {
		        resultado = sumaTotal - 2 * j;
		        encontrado = true;
		    }
		    j--;
		}
		
		return resultado;
	}
}//end class DesajusteMinimo