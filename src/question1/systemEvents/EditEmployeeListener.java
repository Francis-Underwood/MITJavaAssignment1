package question1.systemEvents;

import java.util.EventListener;

public interface EditEmployeeListener extends EventListener {
	  public void editEmpoyee(EditEmployeeEvent evt);
}