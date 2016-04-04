package question1;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = -5084269557961748965L;
	private String eid;
	private String fname;
	private String lname;
	
	public Employee (String eid, String fname, String lname) {
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
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
