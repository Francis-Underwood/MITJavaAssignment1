package question1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import question1.systemEvents.*;

public class StartUp {

	private final static String feelNLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private static String status = "e";
	// components
	private static JFrame frame = new JFrame();
	private static Container con;
	private static EmployeePanel empPanl;
	private static CustomerPanel custPanl;
	private static JToolBar tb = new JToolBar();;
	private static JButton createEmpyBtn = new JButton(new ImageIcon("img/Insert24.gif"));
	private static JButton saveDBBtn = new JButton(new ImageIcon("img/Save24.gif"));
	
	// data access
	private static DB db = new DB();
	private static Employees elist;
	private static Repository<String, Employee> empyRepo = EmployeeRepository.factory();
	
	// listeners
	private static ActionListener saveDataMenuLstn;
	private static ActionListener createEmpyBtnLstn;
	private static EditEmployeeListener editEmpyLstn;
	private static DeleteEmployeeListener delEmpyLstn;
	private static SaveEmployeeListener saveEmpyLstn;
	
	public static void main(String[] args) {
		
		/*************************************************
		 * create listeners
		 *************************************************/
		
		// tool bar listeners
		createEmpyBtnLstn = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ("e" == status) {
					con.remove(empPanl);
					custPanl = new CustomerPanel(null);
					custPanl.addSaveEmployeeListener(saveEmpyLstn);
					con.add(custPanl, BorderLayout.WEST);
					frame.revalidate();
					frame.repaint();
					status = "c";
				}
			}
		};
		saveDataMenuLstn = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeDataInToDB();
			}
		};
		
		// employee-list-page components listeners
		editEmpyLstn = new EditEmployeeListener() {
			public void editEmpoyee(EditEmployeeEvent evt) {
				String eid = evt.getEmployeeId();
				Employee empy = empyRepo.select(eid, elist);
				if ("e" == status) {
					con.remove(empPanl);
					custPanl = new CustomerPanel(empy);
					custPanl.addSaveEmployeeListener(saveEmpyLstn);
					con.add(custPanl, BorderLayout.WEST);
					frame.revalidate();
					frame.repaint();
					status = "c";
					//System.out.println("Halo!"+ elist.get("E002").getEid());
				}
			}
		};
		delEmpyLstn = new DeleteEmployeeListener() {
			public void deleteEmpoyee(DeleteEmployeeEvent evt) {
				String eid = evt.getEmployeeId();
				empyRepo.delete(eid, elist);
				System.out.println("#: " + elist.size());
			}
		};
		
		// employee-edit-page components listeners
		saveEmpyLstn = new SaveEmployeeListener() {
			public void saveEmpoyee(SaveEmployeeEvent evt) {
				System.out.println("reach here: " );
				Employee e = evt.getEmployee();
				if (empyRepo.containsKey(e.getEid(), elist)) {
					empyRepo.update(e.getEid(), e, elist);
				}
				else {
					empyRepo.add(e.getEid(), e, elist);
				}
				// update the user interface
				if ("c"==status) {
					
					con.remove(custPanl);
					custPanl = null;
					empPanl = new EmployeePanel(new ArrayList<Employee>(empyRepo.all(elist)));
					empPanl.addEditEmployeeListener(editEmpyLstn);
					empPanl.addDeleteEmployeeListener(delEmpyLstn);
					con.add(empPanl, BorderLayout.WEST);
					frame.revalidate();
					frame.repaint();
					status = "e";
				}
				//System.out.println("#: " + elist.size());
			}
		};

		///////////////////////////////////////////////////////
		
		// root container
		frame.setTitle("Customer Master");
		frame.setBounds(120,60,1000,600);
		con = frame.getContentPane();
		
		// tool bar
		tb.setFloatable(false);
		tb.add(createEmpyBtn);
		tb.add(saveDBBtn);
		createEmpyBtn.addActionListener(createEmpyBtnLstn);
		saveDBBtn.addActionListener(saveDataMenuLstn);
		con.add(tb, BorderLayout.NORTH);
		
		
		///////////////////////////////////////////////////////
	
		
		// load data from db
		loadDataFromDB();
		
		// test against data validality
		Employee empy = empyRepo.select("E003", elist);
		//Customer c = clist.get("1003");
		System.out.println("Employee: " + empy.getFname());
		// end
		
		///////////////////////////////////////////////////////
		
		// create employee-list-page
		empPanl = new EmployeePanel(new ArrayList<Employee>(empyRepo.all(elist)));
		// new copies of the employee, change on table model won't affect the original ones
		
		empPanl.addEditEmployeeListener(editEmpyLstn);
		empPanl.addDeleteEmployeeListener(delEmpyLstn);
		con.add(empPanl, BorderLayout.WEST);
		
		
		// set style
		try {
			UIManager.setLookAndFeel(feelNLook);
			SwingUtilities.updateComponentTreeUI(frame);
		} 
		catch (UnsupportedLookAndFeelException ex1) {
		      System.err.println("Unsupported LookAndFeel: " + feelNLook);
	    }
	    catch (ClassNotFoundException ex2) {
	      System.err.println("LookAndFeel class not found: " + feelNLook);
	    }
	    catch (InstantiationException ex3) {
	      System.err.println("Could not load LookAndFeel: " + feelNLook);
	    }
	    catch (IllegalAccessException ex4) {
	      System.err.println("Cannot use LookAndFeel: " + feelNLook);
	    }
		
		frame.setVisible(true);
		
	}
	
	private static void initializeEmployeeListPanel() {
		
	}
	
	private static void loadDataFromDB() {
		elist = (Employees)db.loadDatabase("customersdb.txt");
	}
	
	private static void writeDataInToDB() {
		//System.out.println("#: " + elist.size());
		db.saveDatabase(elist,"customersdb.txt");
	}
}


class DB {
	public Object loadDatabase(String f) {
		Object ob = null;
		try {
	        FileInputStream fis = new FileInputStream(f);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ob = (Object)ois.readObject();
	        ois.close();
	        fis.close();
	     }  catch(Exception e) {e.printStackTrace();}
	     return ob;  
	}
	public void saveDatabase(Object o, String f) {
		try {
		       FileOutputStream fos = new FileOutputStream(f);
		       ObjectOutputStream oos = new ObjectOutputStream(fos);
		       oos.writeObject(o);
		       oos.flush();
		       oos.close();
		       fos.close();
		} catch(Exception e ){e.printStackTrace();}
	}
}