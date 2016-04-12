package question1.systemEvents;

import java.util.EventListener;

public interface IDeleteEmployeeListener extends EventListener {
	  public void deleteEmpoyee(DeleteEmployeeEvent evt);
}
