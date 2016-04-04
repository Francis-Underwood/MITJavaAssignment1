package question1.systemEvents;

import java.util.EventListener;

public interface CreateEmployeeListener extends EventListener {
	public void createEmpoyee(CreateEmployeeEvent evt);
}
