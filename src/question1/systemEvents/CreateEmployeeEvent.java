package question1.systemEvents;

import java.util.EventObject;

public class CreateEmployeeEvent extends EventObject {

	private static final long serialVersionUID = -1059637109042114715L;

	public CreateEmployeeEvent(Object source) {
	    super(source);
	}
	
}
