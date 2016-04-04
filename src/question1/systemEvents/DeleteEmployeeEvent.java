package question1.systemEvents;

import java.util.EventObject;

public class DeleteEmployeeEvent extends EventObject {
	
	private String employeeId;
	
	public DeleteEmployeeEvent(Object source, String eid) {
	    super(source);
	    this.employeeId = eid;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
}
