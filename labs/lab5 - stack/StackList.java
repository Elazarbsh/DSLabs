
public class StackList<T> implements Stack<T> {
	
	private SNode<T> top;
	private SLinkedList<T> listOfStackElements;
	
	public StackList(int size) {
		listOfStackElements = new SLinkedList<T>();
	}

	@Override
	public void push(T newElement) {
		if(!this.isFull()) {
			listOfStackElements.insert(newElement);
		}
	}

	@Override
	public T pop() {
		if(!this.isEmpty()) {	
			listOfStackElements.gotoEnd();
			T elem = (T) listOfStackElements.getCursor();
			listOfStackElements.remove();
			return elem;
		}else {
			System.out.println("stack is empty");
			return null;
		}
	}

	@Override
	public void clear() {
		listOfStackElements.clear();
	}

	@Override
	public boolean isEmpty() {
		return listOfStackElements.isEmpty();
	}
	

	@Override
	public boolean isFull() {
		return false;
	}
	
	public String toString() {
		return listOfStackElements.toString();
	}
	
	
}
