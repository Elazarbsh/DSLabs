
public class SLinkedList<T> implements List {

	private SNode<T> head;
	private SNode<T> cursor;

	public SLinkedList() {
		this.head = null;
		this.cursor = null;
	}

	@Override
	public void insert(Object newElement) {
		SNode<T> newNode = new SNode<T>((T) newElement, null);
		if (head == null) {
			head = newNode;
			cursor = head;
			return;
		}
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		cursor.setNext(newNode);
		cursor = newNode;
	}

	@Override
	public void remove() {
		if (head == null) {
			return;
		}
		if (cursor == head) {
			head = head.getNext();
			cursor = head;
			return;
		}
		SNode<T> current = head;
		while (current.getNext() != cursor) {
			current = current.getNext();
		}
		current.setNext(cursor.getNext());
		if (current.getNext() == null) {
			cursor = head;
		} else {
			cursor = current.getNext();
		}
	}

	@Override
	public void replace(Object newElement) {
		if (cursor != null) {
			cursor.setElement((T) newElement);
		}
	}

	@Override
	public void clear() {
		head = null;
		cursor = null;
	}

	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean gotoBeginning() {
		if (head == null) {
			return false;
		}
		cursor = head;
		return true;
	}

	@Override
	public boolean gotoEnd() {
		if (head != null) {
			SNode<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			cursor = temp;
			return true;
		}
		return false;
	}

	@Override
	public boolean gotoNext() {
		if (head != null && cursor.getNext() != null) {
			cursor = cursor.getNext();
			return true;
		}
		return false;
	}

	@Override
	public boolean gotoPrior() {
		if (head == null || cursor == head) {
			return false;
		}
		SNode<T> current = head;
		while (current.getNext() != cursor) {
			current = current.getNext();
		}
		cursor = current;
		return true;
	}

	@Override
	public Object getCursor() {
		if (cursor != null) {
			return cursor.getElement();
		}
		return null;
	}

	public String toString() {
		String string = "";
		SNode<T> temp = head;
		while (temp != null) {
			string += temp.getElement() + " ";
			temp = temp.getNext();
		}
		return string;
	}

}
