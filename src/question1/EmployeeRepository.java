package question1;

import java.util.*;

public class EmployeeRepository implements IRepository<String, Employee> {
	
	private static EmployeeRepository employeeRepository;	//singleton pattern
	
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

	public boolean containsKey(String key, EntityService<String, Employee> db) {
		return db.containsKey(key);
	}
	
	public static IRepository<String, Employee> getInstance() {
		if (null==employeeRepository) {
			employeeRepository = new EmployeeRepository();
		}
		else {}
		return employeeRepository;
	}
	
}