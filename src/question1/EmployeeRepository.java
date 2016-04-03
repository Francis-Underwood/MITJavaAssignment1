package question1;

import java.util.*;

public class EmployeeRepository implements Repository<String, Employee> {
	
	private EmployeeRepository() {}
	
	public Employee select(String key, EntityService<String, Employee> db) {
		return db.get(key);
	}
	
	public void add(String key, Employee val, EntityService<String, Employee> db) {
		db.add(key, val);
	}
	
	public void update(String key, Employee val, EntityService<String, Employee> db) {
		db.update(key, val);
	}
	
	public void delete(String key, EntityService<String, Employee> db) {
		db.delete(key);
	}
	
	public ArrayList<Employee> all(EntityService<String, Employee> db) {
		return db.getAll();
	}
	
	public static Repository<String, Employee> factory() {
		return new EmployeeRepository();	
	}
	
}