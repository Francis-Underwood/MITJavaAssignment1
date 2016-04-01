package humanres;

import java.util.*;
import java.io.*;

class asg1model {
	public static void main (String[] x) {
		String[][][] cu = {
			{{"1001","MIT"},{"1002","ABC Corp"},{"1003","Datacom"}},
			{{"1004","Unitec"},{"1005","Spark LTD"}},
			{{"1006","Fletcher Building"},{"1007","Canon NZ LTD"}}
		};
		
		String[][] em = {
			{"E101","Sam","Sub"},
			{"E102","Mona","Alex"},
			{"E103","Alice","Thomas"}
		};
		
		Employees elist = new Employees();
		// Create employees
		for (int i=0; i<em.length; i++) {
			elist.addEmployee(em[i][0],em[i][1],em[i][2]);
		}	
		
		/* 
		   (1) Get employees from the employee list (Employees)
		   (2) Create customer list for the employee
		   (3) Pass the customer list to the employee
		*/
		Employee e2 = null;
		for (int i=0; i<em.length; i++) {
			e2 = elist.getEmployee(em[i][0]);
			Customers clist = new Customers();
			for (int j=0; j<cu[i].length; j++) {
				clist.addCustomer(cu[i][j][0],cu[i][j][1]);
			}
			e2.setCustomers(clist);
		}
		
		// Test code
		CifesObject cif = new CifesObject();
		cif.ppSerial(elist,"salesdb.txt");
		
		Enumeration e1 = elist.elements();
		while(e1.hasMoreElements()) {
			Employee e = (Employee) e1.nextElement();
			System.out.println("Employee: "+e.getEid()+" "+e.getFname()+" "+e.getLname());
			Customers clist = e.getCustomers();
			Enumeration en = clist.elements();
			while (en.hasMoreElements()) {
				Customer cust = (Customer) en.nextElement();
				System.out.println(cust.getCid()+" "+cust.getCname());
			}
		}
	}
}

class Customers extends Hashtable {
	public void addCustomer (String cid, String cname) {
		Customer cust = new Customer(cid,cname);
		put(cid,cust);
	}
	
}

class Employees extends Hashtable<String, Employee> {

	public void addEmployee (String eid, String fname, String lname) {
		Employee emp = new Employee(eid,fname,lname);
		put(eid,emp);
	}
	
	public Employee getEmployee(String eid) {
		Employee e = (Employee) get(eid);
		return e;
	}
}

class Employee  implements Serializable {	
	private String eid;
	private String fname;
	private String lname;
	private Customers clist;
	
	public Employee (String eid, String fname, String lname) {
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
	}
	
	public void setCustomers (Customers clist) {
		this.clist = clist;
	}
	
	public Customers getCustomers () {
		return clist;
	}
	
	public String getEid() {
		return eid;	
	}
	
	public String getFname() {
		return fname;	
	}
	
	public String getLname() {
		return lname;	
	}
	
	public void setFname(String fname) {
		this.fname = fname;	
	}
	
	public void setLname(String lname) {
		this.lname = lname;	
	}
}

class Customer  implements Serializable {
	private String cid;
	private String cname;
	
	public Customer (String cid, String cname) {
		this.cid = cid;
		this.cname = cname;		
	}
	
	public String getCid () {
		return cid;
	}
	
	public String getCname () {
		return cname;
	}
	
	public void setName(String cname) {
		this.cname = cname;
	}
}
