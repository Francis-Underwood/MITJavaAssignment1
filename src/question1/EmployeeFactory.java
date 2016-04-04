package question1;

// factory pattern
public final class EmployeeFactory {
	public static final String SALESPERSON = "sales";
	public static final String OTHERSTAFF = "other staff";
	public Employee createEmployee(String type, String eid, String fname, String lname, Customers clist) {
		switch (type) {
			case SALESPERSON:
				return new SalesPerson(eid, fname, lname, clist);
			case OTHERSTAFF:
				//break;
			default:
				return new OtherStaff(eid, fname, lname);
		}
	}
}
