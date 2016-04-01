package question1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class StartUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		CustomerUI ui = new CustomerUI(new ArrayList<Customer>());
		
		Customers clist = new Customers();
		clist.add("1001", new Customer("1001", "Recho"));
		clist.add("1002", new Customer("1002", "Cannon"));
		clist.add("1003", new Customer("1003", "Nikon"));
		
		Customer c = clist.get("1002");
		System.out.println("Customer: " + c.getCname());
		
		DB db = new DB();
		db.saveDatabase(clist,"customersdb.txt");
		*/
		
		
		DB db = new DB();
		Customers clist = (Customers)db.loadDatabase("customersdb.txt");
		Customer c = clist.get("1003");
		System.out.println("Customer: " + c.getCname());
		
		ArrayList<Customer> customerList = new ArrayList<Customer>(clist.getAll());
		CustomerUI ui = new CustomerUI(customerList);
		
		
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