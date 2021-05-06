
public class StackArray<T> implements Stack<T> {

	private int top = -1;
	private T[] element;

	public StackArray(int size) {
		if (size < 0) {
			element = (T[]) new Object[DEF_MAX_STACK_SIZE];
		} else {
			element = (T[]) new Object[size];
		}
	}

	@Override
	public void push(T newElement) {
		if (!this.isFull()) {
			element[++top] = newElement;
		} else {
			System.out.println("stack is full");
		}
	}

	@Override
	public T pop() {
		if (!this.isEmpty()) {
			return element[top--];
		}else {
			System.out.println("stack is empty");
			return null;
		}
	}

	@Override
	public void clear() {
		top = -1;
	}

	@Override
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(top == element.length) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String string = "";
		for(int i = 0; i <= top ; i++) {
			string += element[i];
			string += "\t";
		}
		return string;
	}


}
