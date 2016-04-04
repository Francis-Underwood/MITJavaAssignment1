package question1.systemEvents;

import java.util.EventObject;
import question1.*;

public class SaveEmployeeEvent extends EventObject {
	
	private Employee employee;
	
	public SaveEmployeeEvent(Object source, Employee empy) {
	    super(source);
	    this.employee = empy;
	}

	public Employee getEmployee() {
		return employee;
	}
	
}
