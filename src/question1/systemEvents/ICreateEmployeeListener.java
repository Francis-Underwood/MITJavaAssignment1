package question1.systemEvents;

import java.util.EventListener;

public interface ICreateEmployeeListener extends EventListener {
	public void createEmpoyee(CreateEmployeeEvent evt);
}
