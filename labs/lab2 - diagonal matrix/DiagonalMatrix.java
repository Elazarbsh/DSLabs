
public class DiagonalMatrix implements Matrix {

	private int n;
	private int size;
	private double[] matrix;
	private int scaler = 1;

	public DiagonalMatrix() {
		this(MAX_SIZE);
	}

	public DiagonalMatrix(int n) {
		if (n > 0) {
			this.n = n;
		} else {
			this.n = MAX_SIZE;
		}
		size = (2 * n) - 1;
		matrix = new double[size];
	}

	@Override
	public int getSize() {
		return n;
	}

	@Override
	public double get(int i, int j) {
		if ((i + j) >= size) {
			System.out.println("index out of bounds");
			return -1;
		}
		return (scaler * matrix[i + j]);
	}

	@Override
	public void put(int i, int j, double x) {
		if ((i + j) >= size) {
			System.out.println("index out of bounds");
			return;
		}
		matrix[i + j] = x / scaler;
	}

	@Override
	public void transpose() {
		return;
	}

	@Override
	public void multByConstant(int c) {
		if (c <= 0) {
			System.out.println("invalid scaler");
			return;
		}
		scaler *= c;
	}

	public String toString() {
		String string = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				string += String.valueOf(this.get(i, j)) + "\t";
			}
			string += '\n';
		}
		return string;
	}
}
