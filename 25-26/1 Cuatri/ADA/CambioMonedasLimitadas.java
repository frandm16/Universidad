public class CambioMonedasLimitadas {
	private int M;
	private int [] d; //Valores de las monedas
	private int [] q; //Cantidad de monedas de cada tipo.
	private int [][] tabla; //Mínimo número de monedas. Como la calidad siempre es positiva
							//se usará -1 para representar infinito.
	private int [][] opcion; //k elegido para la primera decisión del subproblema.
	
	public CambioMonedasLimitadas(int M, int [] d, int[] q) {
		this.M = M;
		this.d = d;
		this.q = q;
		tabla = null;
		opcion = null;
	}
	
	/**
	 * Método para rellenar la tabla 
	 */
	public int resolverBottomUp() {
		tabla = new int [d.length][M+1];
		opcion = new int [d.length][M+1]; 
		//Rellenamos la tabla de arriba a abajo y de izquierda a derecha
		for (int i = 0; i<d.length; i++) {
			for (int j = 0; j<= M; j++) {
				if (j == 0) {
					tabla[i][j] = 0;
					opcion[i][j] = 0; //Se usan 0 monedas
				}else if (i == 0) {
					if (j%d[i]!= 0 || j/d[i] > q[i]) {
						tabla[i][j] = -1;
						opcion[i][j] = -1; //Se usan infinitas monedas
					}else {//j%d[i]== 0 && j/d[i] <= q[i]
						tabla[i][j] = j/d[i];
						opcion[i][j] = j/d[i];
					}
				}else {//i> 0
					//En M[i,j] vamos a ir guardando el mínimo encontrado
					int minimo= tabla[i-1][j]; //0+M(i-1,j-0)
					opcion[i][j] = 0;
					int maxMonedas = (int) Math.min(q[i], Math.floor(j/d[i]));
					for(int k = 1; k <= maxMonedas; k++) {
						int alternativa = sumaInf(k, tabla[i-1][j-k*d[i]]);
						if (menorInf(alternativa, minimo)) {
							minimo = alternativa; //mejor opcion hasta el momento
							opcion[i][j] = k;
						}//if alternativa
					}//for k
					tabla[i][j] = minimo;
				}//else
			}//for j
		}//for i
		return tabla[d.length-1][M];
	}
	/**
	 * Devuelve la suma de dos números, teniendo en cuenta que -1 significa infinito.
	 */
	private int sumaInf(int a, int b) {
		int suma = -1;
		if (a != -1 && b!=-1) {
			suma = a+b;
		}
		return suma;
	}
	/**
	 * Devuelve a < b, teniendo en cuenta que -1 representa infinito
	 */
	private boolean menorInf(int a, int b) {
		boolean res = false;
		if (a!= -1) {
			if (b == -1) {
				res = true;
			}else {//ninguno es infinito
				res = a < b;
			}
		}
		return res;
	}
	
	
	/**
	 * Reconstruimos la solución utilizando la información en la tabla "opcion"
	 * @return una lista s que para cada moneda de tipo i devuelve el número de monedas de ese tipo utilizadas s[i] 
	 */
	public int [] reconstruirSol() {
		if (opcion == null) {
			throw new RuntimeException("Se debe resolver el problema primero");
		}
		
		int []s = new int[d.length]; //Inicialmente 0 monedas de cada tipo
		
		int i = d.length - 1;
		int j = M;
		while (i >= 0) {
			int k = opcion[i][j];
			s[i] = k;
			if (k == -1) {
				i = -1;
			} else {
				j-= k*d[i];
				i--;
			}
		}

		return s;
	}

	
	public void mostrarDatos() {
		System.out.println("Número Mínimo de monedas");
		for (int i = 0; i< tabla.length; i++) {
			for (int j = 0; j< tabla[i].length; j++) {
				System.out.print(tabla[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Opciones");
		for (int i = 0; i< opcion.length; i++) {
			for (int j = 0; j< opcion[i].length; j++) {
				System.out.print(opcion[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
