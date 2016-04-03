package question1;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private String eid;
	private String fname;
	private String lname;
	private Customers clist = new Customers();
	
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
