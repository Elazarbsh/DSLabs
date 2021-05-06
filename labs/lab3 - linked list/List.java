
public interface List<T> {
	void insert(T newElement);
	
	void remove();
	
	void replace(T newElement);
	
	void clear();
	
	boolean isEmpty();
	
	boolean gotoBeginning();
	
	boolean gotoEnd();
	
	boolean gotoNext();
	
	boolean gotoPrior();
	
	T getCursor();
	
}
