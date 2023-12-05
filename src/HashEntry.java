

public class HashEntry<K,V> {
 

    K key;
    V value;
	private HashEntry<K, V> next;

   
    public HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
    
    public void setValue(V value) {
		this.value = value;
	}	

	public HashEntry<K, V> getNext() {
		return this.next;
	}

	public void setNext(HashEntry<K, V> next) {
		this.next = next;
	}	

}
