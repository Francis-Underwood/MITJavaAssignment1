package question1;

public class GenerateFakeDog {

	public static void main(String[] args) {
		
		// generate init test data
		Employees elist = new Employees();
		
		// first employee
		SalesPerson sp1 = new SalesPerson("E001", "Oliver", "Queen");
		// its customers
		Customers clist1 = new Customers();
		clist1.add("C0001", new CustomerPayCash("C0001", "Merlyn"));
		clist1.add("C0002", new CustomerPayWithCreditCard("C0002", "Count Vertigo"));
		clist1.add("C0003", new CustomerPayCash("C0003", "Brick"));
		sp1.setCustomers(clist1);
		// put it into hashtable
		elist.add(sp1.getEid(), sp1);
		
		// second employee
		SalesPerson sp2 = new SalesPerson("E002", "Hal", "Jordan");
		// its customers
		Customers clist2 = new Customers();
		clist2.add("C0004", new CustomerPayCash("C0004", "Sky Pirate"));
		clist2.add("C0005", new CustomerPayCash("C0005", "Knodar"));
		clist2.add("C0006", new CustomerPayCash("C0006", "Vandal Savage"));
		sp2.setCustomers(clist2);
		// put it into hashtable
		elist.add(sp2.getEid(), sp2);
		
		// third employee
		OtherStaff sp3 = new OtherStaff("E003", "Jay", "Garrick");
		// put it into hashtable
		elist.add(sp3.getEid(), sp3);
		
		// fourth employee
		SalesPerson sp4 = new SalesPerson("E004", "Barry", "Allen");
		Customers clist4 = new Customers();
		clist4.add("C0010", new CustomerPayWithCreditCard("C0010", "Spectro"));
		clist4.add("C0011", new CustomerPayWithCreditCard("C0011", "Imperiex"));
		clist4.add("C0012", new CustomerPayWithCreditCard("C0012", "Marvin Noronsa"));
		sp4.setCustomers(clist4);
		// put it into hashtable
		elist.add(sp4.getEid(), sp4);
		
		
		// test
		Employee e = elist.get("E002");
		System.out.println("Employee: " + e.getFname() + " " + e.getLname());
		if (e instanceof SalesPerson) {
			Customer c = ((SalesPerson)e).getCustomers().get("C0006");
			System.out.println("Customer: " + c.getCname() + ", pay with " + c.getPaymentMethod());
		}
		
		
		// test ends
		
		DB db = new DB();
		db.saveDatabase(elist,"customersdb.txt");
		
	}

}
