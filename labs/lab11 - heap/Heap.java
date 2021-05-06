import java.io.IOException;

public class Heap {

	private static final int DEF_MAX_HEAP_SIZE = 10; // Default maximum heap size
	private int size = 0;
	private HeapData[] heap;
	private int maxSize;

	public Heap() {
		this(DEF_MAX_HEAP_SIZE);
	}
	
	public Heap(int maxSize) {
		if (maxSize > 1) {
			this.maxSize = maxSize;
			heap = new HeapData[maxSize];
		} else {
			this.maxSize = DEF_MAX_HEAP_SIZE;
			heap = new HeapData[maxSize];
		}
		heap[0] = new HeapData(Integer.MIN_VALUE);
	}
	

	public void insert(HeapData newElement) {
		if (isFull()) {
			return;
		}
		size++;
		int index = size;

		while (newElement.getPriority() < heap[index/2].getPriority()) {
			heap[index] = heap[index / 2];
			index = index / 2;
		}
		heap[index] = newElement;
	}
	
	

	public HeapData removeMin() {
		if(isEmpty()) {
			return null;
		}
		int index = 1;
		HeapData removedElem = heap[index];
		
		while(true) {
			int left = index*2;
			int right = index*2+1;
			
			//if node has 2 children
			if(left <= size && right <= size) {
				if(heap[left].getPriority() < heap[right].getPriority()) {
					heap[index] = heap[left];
					index = index*2;
				}else {
					heap[index] = heap[right];
					index = (index*2)+1;
				}
				continue;
			}
			
			//if node only has a left child
			if(left <= size && right > size) {
				heap[index] = heap[left];
				index = index*2;
				continue;
			}
			
			//if node only has a right child
			if(right <= size && left > size) {
				heap[index] = heap[right];
				index = (index*2)+1;
				continue;
			}
			
			//if node is a leaf
			if(isLeaf(index)) {
				heap[index] = heap[size];
				size--;
				return removedElem;
			}
		}
	}


	public boolean isFull() {
		return (size >= maxSize);
	}

	public void clear() {
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public String toString() {
		String string = "";
		
		for(int i = 1 ; i <=size ; i++) {
			string += heap[i].getPriority() + " "; 
		}
		string += "\n";
		return string;
	}
	
	
	private boolean isLeaf(int i) {
		if(i > size/2 && i <= size) {
			return true;
		}
		return false;
	}

}
