package question1.systemEvents;

import java.util.EventListener;

public interface ISaveEmployeeListener extends EventListener {
	public void saveEmpoyee(SaveEmployeeEvent evt);
}
