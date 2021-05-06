
public class HashTableData {
	
	private String key; //last name
	private int id;
	private String name;
	private int age;
	private float avg;
	
	public HashTableData(String key, String name, int id, int age, float avg) {
		this.key = key;
		this.name = name;
		this.id = id;
		this.age = age;
		this.avg = avg;
	}
	
	public String getKey() {
		return key;
	}
	
	
	public String toString(){
		return "key: " + key + ", id: " + id;
	}
	
	public boolean compare(HashTableData other) {
		if(other != null && key.equals(other.key) && id == other.id) {
			return true;
		}
		return false;
	}
}
