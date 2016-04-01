package question1;

import java.util.*;
import java.io.Serializable;

public class EntityService<K, V> implements Serializable {
	
	private final Hashtable<K, V> entitySet = new Hashtable<K, V>();
	
	public void add(K key, V val) {
		entitySet.put(key, val);
	}
	
	public V get(K key) {
		return (V)entitySet.get(key);
	}
	
	public void update(K key, V val) {
		entitySet.replace(key, val);
	}
	
	public void delete(K key) {
		entitySet.remove(key);
	}
	
	public Collection<V> getAll() {
		return entitySet.values();
	}
}
