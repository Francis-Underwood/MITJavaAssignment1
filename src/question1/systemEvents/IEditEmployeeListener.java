package question1.systemEvents;

import java.util.EventListener;

public interface IEditEmployeeListener extends EventListener {
	  public void editEmpoyee(EditEmployeeEvent evt);
}