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

public class StartUp {

	private final static String feelNLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private static String status = "e";
	private static EmployeePanel empPanl;
	private static Employees elist;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("Customer Master");
		frame.setBounds(10,10,1000,600);
		//frame.setSize(300, 200);
		Container con = frame.getContentPane();
		
		// tool bar
		JToolBar tb = new JToolBar();
		tb.setFloatable(false);
		
		JButton bt = new JButton(new ImageIcon("img/Insert24.gif"));
		tb.add(bt);
		
		ActionListener createBtnLstn = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				
				if ("e" == status)
				{
					System.out.println("Halo!");
					con.remove(empPanl);
					frame.revalidate();
					frame.repaint();
					status = "c";
					System.out.println("Halo!"+ elist.get("E002").getEid());
				}
				else
				{
					con.add(empPanl, BorderLayout.WEST);
					frame.revalidate();
					frame.repaint();
					status = "e";
				}
			}
		};
		
		bt.addActionListener(createBtnLstn);
		
		con.add(tb, BorderLayout.NORTH);
		
		/////////////////////////////
	
		
		
		/////////////////////////////
		
		
		
		
		/*
		EmployeePanel empPanl = new EmployeePanel(new ArrayList<Employee>());
		
		// generate init test data
		Employees elist = new Employees();
		
		// first employ
		SalesPerson sp1 = new SalesPerson("E001", "Oliver", "Queen");
		
		Customers clist1 = new Customers();
		clist1.add("C0001", new CustomerPayCash("C0001", "Merlyn"));
		clist1.add("C0002", new CustomerPayWithCreditCard("C0002", "Count Vertigo"));
		clist1.add("C0003", new CustomerPayCash("C0003", "Brick"));
		
		sp1.setCustomers(clist1);
		
		// put it into hashtable
		elist.add(sp1.getEid(), sp1);
		
		
		// second employ
		SalesPerson sp2 = new SalesPerson("E002", "Hal", "Jordan");
		
		Customers clist2 = new Customers();
		clist2.add("C0004", new CustomerPayCash("C0004", "Sky Pirate"));
		clist2.add("C0005", new CustomerPayCash("C0005", "Knodar"));
		clist2.add("C0006", new CustomerPayCash("C0006", "Vandal Savage"));
		
		sp2.setCustomers(clist2);
		
		// put it into hashtable
		elist.add(sp2.getEid(), sp2);
		
		
		// third employ
		SalesPerson sp3 = new SalesPerson("E003", "Jay", "Garrick");
		
		Customers clist3 = new Customers();
		clist3.add("C0007", new CustomerPayWithCreditCard("C0007", "Peek-a-Boo"));
		clist3.add("C0008", new CustomerPayWithCreditCard("C0008", "Magenta"));
		clist3.add("C0009", new CustomerPayWithCreditCard("C0009", "Savitar"));
		
		sp3.setCustomers(clist3);
		
		// put it into hashtable
		elist.add(sp3.getEid(), sp3);
		
		
		
		// third employ
		SalesPerson sp4 = new SalesPerson("E004", "Barry", "Allen");
		
		Customers clist4 = new Customers();
		clist4.add("C0010", new CustomerPayWithCreditCard("C0010", "Spectro"));
		clist4.add("C0011", new CustomerPayWithCreditCard("C0011", "Imperiex"));
		clist4.add("C0012", new CustomerPayWithCreditCard("C0012", "Marvin Noronsa"));
		
		sp4.setCustomers(clist4);
		
		// put it into hashtable
		elist.add(sp4.getEid(), sp4);
		
		
		
		Employee e = elist.get("E002");
		System.out.println("Employee: " + e.getFname() + " " + e.getLname());
		
		Customer c = e.getCustomers().get("C0006");
		System.out.println("Customer: " + c.getCname() + ", pay with " + c.getPaymentMethod());
		
		
		DB db = new DB();
		db.saveDatabase(elist,"customersdb.txt");
		
		*/
		
		
		/////////////////////////
		
		
		
		/**/
		DB db = new DB();
		elist = (Employees)db.loadDatabase("customersdb.txt");
		
		Repository<String, Employee> empyRepo = EmployeeRepository.factory();
		Employee empy = empyRepo.select("E003", elist);
		
		
		//Customer c = clist.get("1003");
		System.out.println("Employee: " + empy.getFname());
		
		ArrayList<Employee> empList = new ArrayList<Employee>(empyRepo.all(elist));	// new copies of the employee, change on table model won't affect the original ones
		
		empPanl = new EmployeePanel(empList);
		
		
		
		
		
		
		/**/
		con.add(empPanl, BorderLayout.WEST);
		
		
		// set style
		try 
		{
			UIManager.setLookAndFeel(feelNLook);
			SwingUtilities.updateComponentTreeUI(frame);
		} 
		catch (UnsupportedLookAndFeelException ex1) 
		{
		      System.err.println("Unsupported LookAndFeel: " + feelNLook);
	    }
	    catch (ClassNotFoundException ex2) 
		{
	      System.err.println("LookAndFeel class not found: " + feelNLook);
	    }
	    catch (InstantiationException ex3) 
		{
	      System.err.println("Could not load LookAndFeel: " + feelNLook);
	    }
	    catch (IllegalAccessException ex4) 
		{
	      System.err.println("Cannot use LookAndFeel: " + feelNLook);
	    }
		
		frame.setVisible(true);
		//System.out.println("X: " + empPanl.getX());
		//System.out.println("Y: " + empPanl.getY());
		
		
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