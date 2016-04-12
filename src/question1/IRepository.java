package question1;

import java.util.*;

public interface IRepository<K, V> {
	public V select(K key, EntityService<K, V> db); 
	public void add(K key, V val, EntityService<K, V> db);    
	public void update(K key, V val, EntityService<K, V> db); 
	public void delete(K key, EntityService<K, V> db);
	public ArrayList<V> all(EntityService<K, V> db);
	public boolean containsKey(K key, EntityService<K, V> db);
}