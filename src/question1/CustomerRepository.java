package question1;

import java.util.*;

public class CustomerRepository implements Repository<String, Customer> {

	private CustomerRepository() {}
	
	public Customer select(String key, EntityService<String, Customer> db) {
		return db.get(key);
	}
	
	public void add(String key, Customer val, EntityService<String, Customer> db) {
		db.add(key, val);
	}
	
	public void update(String key, Customer val, EntityService<String, Customer> db) {
		db.update(key, val);
	}
	
	public void delete(String key, EntityService<String, Customer> db) {
		db.delete(key);
	}
	
	public ArrayList<Customer> all(EntityService<String, Customer> db) {
		return db.getAll();
	}
	
	public static Repository<String, Customer> factory() {
		return new CustomerRepository();	
	}
	
}
