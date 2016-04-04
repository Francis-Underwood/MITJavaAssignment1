package question1;

import java.util.*;
import java.io.Serializable;

public abstract class EntityService<K, V> implements Serializable
{
	
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
	
	public ArrayList<V> getAll() {
		return new ArrayList<V>(entitySet.values());
	}
	
	public int size() {
		if (this.entitySet == null) {
			return 0;
		}
		else {
			return this.entitySet.size();
		}
	}
	
	public boolean containsKey(K key) {
		return this.entitySet.containsKey(key);
	}
	
}
