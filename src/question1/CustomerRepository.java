package question1;

import java.util.*;

public class CustomerRepository implements Repository<String, Customer> {

	private static CustomerRepository customerRepository;	//singleton pattern
	
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

	public boolean containsKey(String key, EntityService<String, Customer> db) {
		return db.containsKey(key);
	}
	
	public static Repository<String, Customer> factory() {
		if (null==customerRepository) {
			customerRepository = new CustomerRepository();
		}
		else {}
		return customerRepository;
	}
	
}
