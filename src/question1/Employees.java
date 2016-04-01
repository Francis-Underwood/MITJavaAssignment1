package question1;

public class Employees extends EntityService<String, Employee> {
	
	public void add(String eid, Employee empy) {
		put(eid, empy);
	}
	
	
}
