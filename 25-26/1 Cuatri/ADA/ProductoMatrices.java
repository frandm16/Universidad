public class ProductoMatrices {
	
	public static int productoMatrices_BottomUp(int[] dim) {
	    int n = dim.length - 1;
        int[][] m = new int[n + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
		
		for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + dim[i - 1] * dim[k] * dim[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                    }
                }
            }
        }
		
		return m[1][n];
	}
}//end class ProductoMatrices