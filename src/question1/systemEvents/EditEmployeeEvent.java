package question1.systemEvents;

import java.util.EventListener;
import java.util.EventObject;


public class EditEmployeeEvent extends EventObject {
	
	private String employeeId;
	
	public EditEmployeeEvent(Object source, String eid) {
	    super(source);
	    this.employeeId = eid;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
}



