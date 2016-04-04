package question1.systemEvents;

import java.util.EventListener;

public interface DeleteEmployeeListener extends EventListener {
	  public void deleteEmpoyee(DeleteEmployeeEvent evt);
}
