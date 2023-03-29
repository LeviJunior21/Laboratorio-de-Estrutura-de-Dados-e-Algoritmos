package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		result = calcularSomaArray(array, 0);
		return result;
	}
	
	private int calcularSomaArray(int[] array, int index) {
		if (index >= array.length) {
			return 0;
		}
		return array[index] + calcularSomaArray(array, index + 1);
	}
	
	public long calcularFatorial(int n) {
		if (n > 0) {
			return n * calcularFatorial(n - 1);
		}
		else {
			return 1;
		}
	}

	public int calcularFibonacci(int n) {
		if (n < 3) {
			return 1;
		}
		int result = calcularFibonacci(1, 1, n - 2);
		return result;
	}
	
	private int calcularFibonacci(int anterior, int atual, int n) {
		int soma = (anterior + atual);
		if (n == 1) {
			return soma;
		}
		return calcularFibonacci(atual, soma, n - 1);
	}

	public int countNotNull(Object[] array) {
		int result = countNotNull(array, 0);
		return result ;
	}
	
	private int countNotNull(Object[] array, int index) {
		int count = 0;
		if (index >= array.length) {
			count = 0;
		} else
		if (array[index] != null) {
			count = 1 + countNotNull(array, index + 1);
		}
		else {
			return countNotNull(array, index + 1);
		}
		return count;
	}

	public long potenciaDe2(int expoente) {
		if (expoente <= 0) {
			return 1;
		}
		return 2 * potenciaDe2(expoente - 1);
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		if (n <= 0) {
			return termoInicial;
		}
		return progressaoAritmetica(termoInicial + razao, razao, n - 1);
	}
	
	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		if (n <= 0) {
			return termoInicial;
		} 
		return progressaoGeometrica(termoInicial * razao, razao, n - 1);
	}
	
	
}
