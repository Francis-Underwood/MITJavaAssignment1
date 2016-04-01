package question1;

import java.util.Hashtable;

public abstract class EntityService<K, V> extends Hashtable<K, V> {
	public abstract void add(K key, V val);
	//public abstract V get(K key);	// it is provided by Hashtable
	//public abstract void update(K key, V val);
	//public abstract void delete(K key, V val);
}
