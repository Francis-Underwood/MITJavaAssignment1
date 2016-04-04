package question1.systemEvents;

import java.util.EventObject;

public class EditEmployeeEvent extends EventObject {
	
	private static final long serialVersionUID = 3222342804606715140L;
	private String employeeId;
	
	public EditEmployeeEvent(Object source, String eid) {
	    super(source);
	    this.employeeId = eid;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
}



