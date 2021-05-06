
public class SparseMatrix implements Matrix {

	private SLinkedList<SparseMatrixEntry> list;
	private int size;
	private double defaultValue;
	private int scaler = 1;
	private boolean transpose = false;
	private final int defSize = 3;

	public SparseMatrix(int size, double defaultValue) {
		if(size > 0) {
			this.size = size;
		}else
			this.size = defSize;
		this.list = new SLinkedList<SparseMatrixEntry>();
		this.defaultValue = defaultValue;
		list.insert(new SparseMatrixEntry(defaultValue, -1, -1));
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public double get(int i, int j) {

		if (transpose == true) {
			i = i + j;
			j = i - j;
			i = i - j;
		}

		if (i > size || j > size || i <= 0 || j <= 0) {
			return -1;
		}

		list.gotoBeginning();
		do {
			if (((SparseMatrixEntry) list.getCursor()).getI() == i
					&& ((SparseMatrixEntry) list.getCursor()).getJ() == j) {
				return scaler * ((SparseMatrixEntry) list.getCursor()).getValue();
			}
		} while (list.gotoNext() == true);

		return scaler * defaultValue;
	}

	@Override
	public void put(int i, int j, double x) {
		if (i > size || j > size || i <= 0 || j <= 0) {
			return;
		}

		if (transpose == true) {
			i = i + j;
			j = i - j;
			i = i - j;
		}

		list.gotoBeginning();
		do {
			if (((SparseMatrixEntry) list.getCursor()).getI() == i
					&& ((SparseMatrixEntry) list.getCursor()).getJ() == j) {
				if (x == defaultValue) {
					list.remove();
					return;
				} else {
					((SparseMatrixEntry) list.getCursor()).setValue(x / scaler);
					return;
				}
			}
		} while (list.gotoNext() == true);

		list.insert(new SparseMatrixEntry(x / scaler, i, j));
		((SparseMatrixEntry) list.getCursor()).getJ();
	}


	@Override
	public void transpose() {
		if (this.transpose == true) {
			this.transpose = false;
		}
		this.transpose = true;
	}

	@Override
	public void multByConstant(int c) {
		if (c <= 0) {
			System.out.println("invalid scaler");
			return;
		}
		scaler *= c;
	}


	//we need to iterate both this linked list and the other matrix linked list so time complexity will be O(n*m)
	public void Add(SparseMatrix other) {
		int i, j;
		double otherVal;

		list.gotoBeginning();
		list.gotoNext();
		do {
			i = ((SparseMatrixEntry) list.getCursor()).getI(); // add the indices from the other list
			j = ((SparseMatrixEntry) list.getCursor()).getJ();
			if (transpose == true) {
				i = i + j;
				j = i - j;
				i = i - j;
			}
			otherVal = other.get(i, j);
			put(i, j, otherVal + this.get(i, j));
		} while (list.gotoNext() == true);

		other.list.gotoBeginning();
		other.list.gotoNext();
		do {
			i = ((SparseMatrixEntry) other.list.getCursor()).getI(); // add the indices from the other list
			j = ((SparseMatrixEntry) other.list.getCursor()).getJ();

			otherVal = this.get(i, j);
			if (other.get(i, j) != other.defaultValue * other.scaler && this.get(i, j) != this.defaultValue * scaler) {
				continue;
			}
			put(i, j, otherVal + other.get(i, j));
		} while (other.list.gotoNext() == true);
		
		this.defaultValue += other.defaultValue/scaler;
	}

	public void Sub(SparseMatrix other) {
		int i, j;
		double otherVal;

		list.gotoBeginning();
		list.gotoNext();
		do {
			i = ((SparseMatrixEntry) list.getCursor()).getI();
			j = ((SparseMatrixEntry) list.getCursor()).getJ();
			if (transpose == true) {
				i = i + j;
				j = i - j;
				i = i - j;
			}
			otherVal = other.get(i, j);
			put(i, j,  this.get(i, j) - otherVal);
		} while (list.gotoNext() == true);

		other.list.gotoBeginning();
		other.list.gotoNext();
		do {
			i = ((SparseMatrixEntry) other.list.getCursor()).getI(); // add the indices from the other list
			j = ((SparseMatrixEntry) other.list.getCursor()).getJ();

			otherVal = this.get(i, j);
			if (other.get(i, j) != other.defaultValue * other.scaler && this.get(i, j) != this.defaultValue * scaler) {
				continue;
			}
			put(i, j,  other.get(i, j) - otherVal);
		} while (other.list.gotoNext() == true);
		
		this.defaultValue -= other.defaultValue/scaler;
	}


	public String toString() {
		String string = "";
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				string += this.get(i, j);
				string += "\t";
			}
			string += "\n";
		}
		return string;
	}

}
