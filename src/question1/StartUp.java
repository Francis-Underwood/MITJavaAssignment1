package question1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private static JToolBar toolBar = new JToolBar();
	private static JButton saveDBBtn = new JButton(new ImageIcon("img/Save24.gif"));
	private static JButton openDBBtn = new JButton(new ImageIcon("img/Open24.gif"));
	private static JButton printBtn = new JButton(new ImageIcon("img/Print24.gif"));
	private static JButton goBackBtn = new JButton(new ImageIcon("img/Exit24.gif"));
	
	// data access
	private static Database db = new Database();
	private static Employees elist;
	private static IRepository<String, Employee> empyRepo = EmployeeRepository.getInstance();
	
	// listeners
	private static ActionListener saveDataMenuLstn;
	private static ActionListener goBackMenuLstn;
	private static IEditEmployeeListener editEmpyLstn;
	private static IDeleteEmployeeListener delEmpyLstn;
	private static ISaveEmployeeListener saveEmpyLstn;
	private static ICreateEmployeeListener crteEmpyLstn;
	 
	
	public static void main(String[] args) {
		
		/*************************************************
		 * create listeners
		 *************************************************/
		
		// tool bar listeners
		saveDataMenuLstn = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeDataInToDB();
			}
		};
		goBackMenuLstn = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update the user interface
				if ("c"==status) {
					switchToEmployeePanel();
					status = "e";
				}
			}
		};
		
		// employee-list-page components listeners
		editEmpyLstn = new IEditEmployeeListener() {
			public void editEmpoyee(EditEmployeeEvent evt) {
				String eid = evt.getEmployeeId();
				Employee empy = empyRepo.select(eid, elist);
				if ("e" == status) {
					switchToCustomerPanel(empy);
					status = "c";
				}
			}
		};
		delEmpyLstn = new IDeleteEmployeeListener() {
			public void deleteEmpoyee(DeleteEmployeeEvent evt) {
				String eid = evt.getEmployeeId();
				empyRepo.delete(eid, elist);
				System.out.println("#: " + elist.size());
			}
		};
		crteEmpyLstn = new ICreateEmployeeListener() {
			public void createEmpoyee(CreateEmployeeEvent evt) {
				if ("e" == status) {
					switchToCustomerPanel(null);
					status = "c";
				}
			}
		};
		
		// employee-edit-page components listeners
		saveEmpyLstn = new ISaveEmployeeListener() {
			public void saveEmpoyee(SaveEmployeeEvent evt) {
				Employee e = evt.getEmployee();
				if (empyRepo.containsKey(e.getEid(), elist)) {
					empyRepo.update(e.getEid(), e, elist);
				}
				else {
					empyRepo.add(e.getEid(), e, elist);
				}
				// update the user interface
				if ("c"==status) {
					switchToEmployeePanel();
					status = "e";
				}
			}
		};

		/*************************************************
		 * set up the visual components
		 *************************************************/
		
		// root container
		frame.setTitle("Human Resource Management System");
		frame.setBounds(120,60,900,600);
		con = frame.getContentPane();
		
		// tool bar
		toolBar.setFloatable(false);
		saveDBBtn.setToolTipText("save the data into txt file");
		toolBar.add(saveDBBtn);
		toolBar.add(openDBBtn);
		toolBar.add(printBtn);
		goBackBtn.setToolTipText("go back to main page");
		toolBar.add(goBackBtn);
		saveDBBtn.addActionListener(saveDataMenuLstn);
		goBackBtn.addActionListener(goBackMenuLstn);
		goBackBtn.setEnabled(false);
		con.add(toolBar, BorderLayout.NORTH);
		
		
		/*************************************************
		 * load data
		 *************************************************/
		
		// load data from db
		loadDataFromDB();
		
		// test against data validity
		/*
		Employee empy = empyRepo.select("E003", elist);
		if (null != empy) {
			//Customer c = clist.get("1003");
			System.out.println("Employee: " + empy.getFname());
		}
		*/
		// end
		
		/*************************************************
		 * initialize the default panel
		 *************************************************/
		
		// create employee-list-page
		empPanl = new EmployeePanel(new ArrayList<Employee>(empyRepo.all(elist)));
		// new copies of the employee, change on table model won't affect the original ones
		
		empPanl.addEditEmployeeListener(editEmpyLstn);
		empPanl.addDeleteEmployeeListener(delEmpyLstn);
		empPanl.addCreateEmployeeListener(crteEmpyLstn);
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
		
		UIManager.put("OptionPane.okButtonText", "OK");
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		
		frame.setVisible(true);
		
	}
	
	private static void switchToCustomerPanel(Employee empy) {
		con.remove(empPanl);
		empPanl = null;
		custPanl = new CustomerPanel(empy);
		custPanl.addSaveEmployeeListener(saveEmpyLstn);
		con.add(custPanl, BorderLayout.WEST);
		goBackBtn.setEnabled(true);
		frame.revalidate();
		frame.repaint();
	}
	
	private static void switchToEmployeePanel() {
		con.remove(custPanl);
		custPanl = null;
		empPanl = new EmployeePanel(new ArrayList<Employee>(empyRepo.all(elist)));
		empPanl.addEditEmployeeListener(editEmpyLstn);
		empPanl.addDeleteEmployeeListener(delEmpyLstn);
		empPanl.addCreateEmployeeListener(crteEmpyLstn);
		con.add(empPanl, BorderLayout.WEST);
		goBackBtn.setEnabled(false);
		frame.revalidate();
		frame.repaint();
	}
	
	private static void loadDataFromDB() {
		elist = (Employees)db.loadDatabase("customersdb.txt");
	}
	
	private static void writeDataInToDB() {
		db.saveDatabase(elist,"customersdb.txt");
		showMessageBox("Synchronization with database is done, you are ready to quit.");
	}
	
	private static void showMessageBox(String message) {
		JOptionPane pane = new JOptionPane(message);
		JInternalFrame intframe = pane.createInternalFrame(frame.getLayeredPane(), "Notice");
		frame.getLayeredPane().add(intframe);
		intframe.show();
	}
}