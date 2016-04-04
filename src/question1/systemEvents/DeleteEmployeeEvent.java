package question1.systemEvents;

import java.util.EventObject;

public class DeleteEmployeeEvent extends EventObject {
	
	private static final long serialVersionUID = 5089956634920336298L;
	private String employeeId;
	
	public DeleteEmployeeEvent(Object source, String eid) {
	    super(source);
	    this.employeeId = eid;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
}
