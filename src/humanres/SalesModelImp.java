package humanres;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class SalesModelImp {
	public static void main (String[] x) {
		new EmployeeUI();
	}
}

class EmployeeUI extends JFrame implements ActionListener{
	JTextField txtEmpID, txtEmpName;
	Employees db;
	public EmployeeUI () {
		setTitle("Employee Master");
		setLayout(null);
		setBounds(10,10,500,600);
		txtEmpID = new JTextField();
		txtEmpName = new JTextField();
		JButton btnGet = new JButton("Get");
		JButton btnLoad = new JButton("Load");
		txtEmpID.setBounds(20,30,50,20);
		txtEmpName.setBounds(20,55,150,20);
		btnLoad.setBounds(60,85,80,40);
		btnLoad.addActionListener(this);
		btnGet.setBounds(145,85,80,40);
		btnGet.addActionListener(this);
		Container con = getContentPane();
		con.add(txtEmpID);
		con.add(txtEmpName);
		con.add(btnGet);
		con.add(btnLoad);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Load")) {
				CifesObject cif = new CifesObject();
				db = (Employees) cif.ppSerial("salesDB.txt");
		}
		if(ae.getActionCommand().equals("Get")) {
				Sales sales = EmployeeMaster.factory();
				String[] data = sales.select(txtEmpID.getText(),db);
				txtEmpName.setText(data[1]+" "+data[2]);
		}
	}
}

interface Sales {	
	public String[] select (String data, Employees db); 
	public void add    (String[] data);    
	public void update (String[] data); 
	public void delete (String[] data); 
}

class EmployeeMaster implements Sales {
	private EmployeeMaster() {}
	public String[] select(String eid, Employees db)    {
		Employee e = (Employee) db.getEmployee(eid);
		String[] data = new String[3];
		data[0] = e.getEid();
		data[1] = e.getFname();
		data[2] = e.getLname();	
		return data;
	}
	public void add       (String[] data) {}
	public void update    (String[] data) {}
	public void delete    (String[] data) {}
	
	public static Sales factory() {
		return new EmployeeMaster();	
	}
}

class CustomerMaster implements Sales {
	private CustomerMaster(             ) {}
	public String[] select    (String cid, Employees db) {
		return null;
	}
	public void add       (String[] data) {}
	public void update    (String[] data) {}
	public void delete    (String[] data) {}
}

class DB {
	public void loadDatabase() {}
	public void saveDatabase() {}
}
