package question1.systemEvents;

import java.util.EventObject;
import question1.*;

public class SaveEmployeeEvent extends EventObject {
	
	private static final long serialVersionUID = 7914562408834137078L;
	private Employee employee;
	
	public SaveEmployeeEvent(Object source, Employee empy) {
	    super(source);
	    this.employee = empy;
	}

	public Employee getEmployee() {
		return employee;
	}
	
}
