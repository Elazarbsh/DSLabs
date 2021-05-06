
public interface Matrix {
	
	final int MAX_SIZE = 100;

	int getSize();
	
	double get(int i, int j);
	
	void put(int i, int j, double x);
	
	void transpose();
	
	void multByConstant(int c);
	
}
