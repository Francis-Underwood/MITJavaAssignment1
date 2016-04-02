package question1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CustomerUI ui = new CustomerUI(new ArrayList<Customer>());
		
		// generate init test data
		Employees elist = new Employees();
		
		// first employ
		SalesPerson sp1 = new SalesPerson("E001", "Oliver", "Queen");
		
		Customers clist1 = new Customers();
		clist1.add("C0001", new CustomerPayCash("C0001", "Merlyn"));
		clist1.add("C0002", new CustomerPayWithCreditCard("C0002", "Count Vertigo"));
		clist1.add("C0003", new CustomerPayCash("C0003", "Brick"));
		
		sp1.setCustomers(clist1);
		
		// put it into hashtable
		elist.add(sp1.getEid(), sp1);
		
		
		// second employ
		SalesPerson sp2 = new SalesPerson("E002", "Hal", "Jordan");
		
		Customers clist2 = new Customers();
		clist2.add("C0004", new CustomerPayCash("C0004", "Sky Pirate"));
		clist2.add("C0005", new CustomerPayCash("C0005", "Knodar"));
		clist2.add("C0006", new CustomerPayCash("C0006", "Vandal Savage"));
		
		sp2.setCustomers(clist2);
		
		// put it into hashtable
		elist.add(sp2.getEid(), sp2);
		
		
		// third employ
		SalesPerson sp3 = new SalesPerson("E003", "Jay", "Garrick");
		
		Customers clist3 = new Customers();
		clist3.add("C0007", new CustomerPayWithCreditCard("C0007", "Peek-a-Boo"));
		clist3.add("C0008", new CustomerPayWithCreditCard("C0008", "Magenta"));
		clist3.add("C0009", new CustomerPayWithCreditCard("C0009", "Savitar"));
		
		sp3.setCustomers(clist3);
		
		// put it into hashtable
		elist.add(sp3.getEid(), sp3);
		
		
		
		// third employ
		SalesPerson sp4 = new SalesPerson("E004", "Barry", "Allen");
		
		Customers clist4 = new Customers();
		clist4.add("C0010", new CustomerPayWithCreditCard("C0010", "Spectro"));
		clist4.add("C0011", new CustomerPayWithCreditCard("C0011", "Imperiex"));
		clist4.add("C0012", new CustomerPayWithCreditCard("C0012", "Marvin Noronsa"));
		
		sp4.setCustomers(clist4);
		
		// put it into hashtable
		elist.add(sp4.getEid(), sp4);
		
		
		
		
		
		
		
		Employee e = elist.get("E002");
		System.out.println("Employee: " + e.getFname() + " " + e.getLname());
		
		Customer c = e.getCustomers().get("C0006");
		System.out.println("Customer: " + c.getCname() + ", pay with " + c.getPaymentMethod());
		
		
		
		
		DB db = new DB();
		db.saveDatabase(clist1,"customersdb.txt");
		
		
		/*
		DB db = new DB();
		Customers clist = (Customers)db.loadDatabase("customersdb.txt");
		
		Repository custRepo = CustomerRepository.factory();
		Customer c = (Customer)custRepo.select("1003", clist);
		
		
		//Customer c = clist.get("1003");
		System.out.println("Customer: " + c.getCname());
		
		ArrayList<Customer> customerList = new ArrayList<Customer>(clist.getAll());
		CustomerUI ui = new CustomerUI(customerList);
		*/
		
	}

}

class DB {
	public Object loadDatabase(String f) {
		Object ob = null;
		try {
	        FileInputStream fis = new FileInputStream(f);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ob = (Object)ois.readObject();
	        ois.close();
	        fis.close();
	     }  catch(Exception e) {e.printStackTrace();}
	     return ob;  
	}
	public void saveDatabase(Object o, String f) {
		try {
		       FileOutputStream fos = new FileOutputStream(f);
		       ObjectOutputStream oos = new ObjectOutputStream(fos);
		       oos.writeObject(o);
		       oos.flush();
		       oos.close();
		       fos.close();
		} catch(Exception e ){e.printStackTrace();}
	}
}