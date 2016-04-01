package question1;

public class Customers extends EntityService<String, Customer> {
	
	public void add(String key, Customer cust) {
		put(key, cust);
	}
	/*
	public Customer get(String key) {
		Customer cust = (Customer) get(key);
		return cust;
	}
	*/
	
}