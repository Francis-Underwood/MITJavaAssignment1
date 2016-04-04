package question1.systemEvents;

import java.util.EventListener;

public interface SaveEmployeeListener extends EventListener {
	public void saveEmpoyee(SaveEmployeeEvent evt);
}
