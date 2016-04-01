package question1;

public class StartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerUI ui = new CustomerUI();
		
		Customers clist = new Customers();
		clist.add("1001", new Customer("1001", "Recho"));
		clist.add("1002", new Customer("1002", "Cannon"));
		clist.add("1003", new Customer("1003", "Nikon"));
		
		Customer c = clist.get("1002");
		System.out.println("Customer: " + c.getCname());
		
	}

}
