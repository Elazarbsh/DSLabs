import java.io.IOException;

public class HashTable {

	private static final int DEF_MAX_HASH_SIZE = 10; // Default maximum hash table size
	private SLinkedList<HashTableData>[] hashArray; 
	private int size;
	private int elemCounter = 0;
	
	public HashTable() {
		this.size = DEF_MAX_HASH_SIZE;
		hashArray = new SLinkedList[DEF_MAX_HASH_SIZE];
	}
	
	public HashTable(int size) {
		if(size > 0) {
			this.size = size;
			hashArray = new SLinkedList[size];
		}else {
			this.size = size;
			hashArray = new SLinkedList[DEF_MAX_HASH_SIZE];
		}
	}
	
	
	
	private int HashFunction(int key) {
		return key%size;
	}
	
	private int HashFunction(String key) {
		int asciiSum = 0;
		for(int i = 0 ; i < key.length() ; i++) {
			asciiSum += (int) key.charAt(i);
		}
		return HashFunction(asciiSum);
	}
	
	
	public boolean insert(HashTableData newElem) {
		if(isFull()) {
			return false;
		}
		if(retrieve(newElem) == true) {
			return false;
		}
		int hash = HashFunction(newElem.getKey());
		if(hashArray[hash] == null) {
			hashArray[hash] = new SLinkedList<HashTableData>();
		}
		hashArray[hash].insert(newElem);
		elemCounter++;
		return true;
	}
	
	
	public boolean retrieve(HashTableData searchElem) {
		int hash = HashFunction(searchElem.getKey());
		if(hashArray[hash] == null || hashArray[hash].isEmpty()) {
			return false;
		}
		hashArray[hash].gotoBeginning();
		do{
			if(((HashTableData) hashArray[hash].getCursor()).compare(searchElem) == true) {
				return true;
			}
		}while(hashArray[hash].gotoNext() == true);
		return false;
	}
	
	
	public boolean remove(HashTableData remElem){
		if(retrieve(remElem) == true){
			int hash = HashFunction(remElem.getKey());
			hashArray[hash].remove();
			elemCounter--;
			return true;
		}
		return false;
	}
	
	public void clear() {
		for(int i = 0 ; i < size ; i++) {
			if(hashArray[i] != null) {
				hashArray[i].clear();
			}
		}
		elemCounter = 0;
	}
	
	public boolean isEmpty(){
		if(elemCounter == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public String toString(){
		String table = "";
		
		for(int i = 0 ; i < size ; i++) {
			if(hashArray[i] != null && !hashArray[i].isEmpty()) {
				hashArray[i].gotoBeginning();
				table += i + " - ";
				do{
					table += ((HashTableData) hashArray[i].getCursor()).toString() + " -> ";
				}while(hashArray[i].gotoNext() == true);
				table += "\n";
			}else {
				table += i + " - \n";
			}
		}
		return table;
	}
	
	
}