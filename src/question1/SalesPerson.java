package question1;

public class SalesPerson extends Employee {
	
	private static final long serialVersionUID = 8429706973332154556L;
	
	private Customers clist = new Customers();
	
	public SalesPerson (String eid, String fname, String lname) {
		super(eid, fname, lname);
	}
	
	public SalesPerson (String eid, String fname, String lname, Customers clist) {
		super(eid, fname, lname);
		this.clist = clist;
	}

	public void setCustomers (Customers clist) {
		this.clist = clist;
	}
	
	public Customers getCustomers () {
		return clist;
	}
	
}
