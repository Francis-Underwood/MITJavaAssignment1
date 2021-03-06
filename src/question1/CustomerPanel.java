package question1;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import layout.*;
import question1.systemEvents.*;

public class CustomerPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 7135447414170251912L;
	// component
	private  JScrollPane scrollPane;
	private  JTable table;
	private  JLabel m_title;
	// buttons to interact with the table
	private  JPanel btnBar;
	private  JButton deleteCustBtn = new JButton("delete");
	private  JButton editCustBtn = new JButton("edit");
	private  JButton createCustBtn = new JButton("create");
	// employee property panel
	private  JPanel empyPropsBar = new JPanel();
	private  JTextField empEidTxtF = new JTextField();
	private  JTextField empFNameTxtF = new JTextField();
	private  JTextField empLNameTxtF = new JTextField();
	private  JComboBox<String> empTypeCombox = new JComboBox<String>(
													new String[] {
															EmployeeFactory.SALESPERSON, 
															EmployeeFactory.OTHERSTAFF
													}
												);
	// customer property panel
	private  JTextField custCidTxtF = new JTextField();
	private  JTextField custNameTxtF = new JTextField();
	private  JComboBox<String> payMethdCombox = new JComboBox<String>(
																	new String[] {
																		CustomerFactory.PAYMENTMETHOD_CASH, 
																		CustomerFactory.PAYMENTMETHOD_CREDITCARD
																	}
																	);
	private Object[] customerEditCtrls = {
										    "Customer Id:", custCidTxtF,
										    "Customer name:", custNameTxtF,
										    "Payment method:", payMethdCombox
										};
	// page navigation
	private  JButton saveEmpyBtn = new JButton("save employee");
	
	// other properties
	private ArrayList<Customer> custList = new ArrayList<Customer>();
	// keep track of selected row
	private  int rowInd = -1;
	private  CustomerTableModel customerModel;
	private CustomerFactory customerFactory = new CustomerFactory();
	private EmployeeFactory employeeFactory = new EmployeeFactory();
	// custom event
	private List<ISaveEmployeeListener> saveEmpyListeners = new ArrayList<ISaveEmployeeListener>();
	private String empType = "";
	
	public CustomerPanel(Employee empy)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if (null != empy) {	// edit
			
			if (empy instanceof SalesPerson) {	// sales
				if (null != ((SalesPerson)empy).getCustomers()) {
					custList = new ArrayList<Customer>(((SalesPerson)empy).getCustomers().getAll());
					empType = EmployeeFactory.SALESPERSON;
				}
				else {} 
			}
			else {	// other staff
				empType = EmployeeFactory.OTHERSTAFF;
			}
			empEidTxtF.setText(empy.getEid());
			empEidTxtF.setEditable(false);
			empFNameTxtF.setText(empy.getFname());
			empLNameTxtF.setText(empy.getLname());
			empTypeCombox.setSelectedItem(empType);
			empTypeCombox.setEnabled(false);
		}
		else {	// create new employee
			empType = EmployeeFactory.SALESPERSON;
			empTypeCombox.setSelectedItem(empType);
		}	
		
		customerModel = new CustomerTableModel(custList);
		
		m_title = new JLabel(customerModel.getTitle());
		m_title.setFont(new Font("Helvetica", Font.PLAIN, 24));
		m_title.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(m_title);
		
		
		// employee property panel
		SpringLayout layout = new SpringLayout();
		empyPropsBar.setLayout(layout);
		empyPropsBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		empyPropsBar.setPreferredSize(new Dimension(200, 140));
		empyPropsBar.add(new JLabel("employee Id: "));
		empEidTxtF.setMaximumSize(new Dimension(20, 10));
		empyPropsBar.add(empEidTxtF);
		empyPropsBar.add(new JLabel("employee first name: "));
		empFNameTxtF.setMaximumSize(new Dimension(20, 10));
		empyPropsBar.add(empFNameTxtF);
		empyPropsBar.add(new JLabel("employee last name: "));
		empLNameTxtF.setMaximumSize(new Dimension(20, 10));
		empyPropsBar.add(empLNameTxtF);
		empyPropsBar.add(new JLabel("employee type: "));
		empTypeCombox.setMaximumSize(new Dimension(60, 10));
		empyPropsBar.add(empTypeCombox);
		empyPropsBar.add(new JLabel("click to save: "));
		// save the whole page, create an employee object and pass it to main
		saveEmpyBtn.addActionListener(this);
		empyPropsBar.add(saveEmpyBtn);
		
		SpringUtilities.makeGrid(empyPropsBar,
                5, 2, //rows, cols
                5, 5, //initialX, initialY
                5, 5);//xPad, yPad
		add(empyPropsBar);
		// employee property panel ends
		
		
		// add margin space
		add(Box.createVerticalStrut(10));	
		
		
		// customer list
		table = new JTable(customerModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 220));
		scrollPane.setMaximumSize(new Dimension(800, 220));
		scrollPane.getViewport().add(table);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		
		// buttons to interact with customer table
		btnBar = new JPanel();
		btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		deleteCustBtn.addActionListener(this);
		editCustBtn.addActionListener(this);
		createCustBtn.addActionListener(this);
		btnBar.add(deleteCustBtn);
		btnBar.add(editCustBtn);
		btnBar.add(createCustBtn);
		// customer list ends
		
		
		// add the components
		if (empType == EmployeeFactory.OTHERSTAFF) {
			
		}
		else if (empType == EmployeeFactory.SALESPERSON) {
			add(scrollPane);
			add(btnBar);
			if (null == empy) { // create
				// add listeners
				empTypeCombox.addActionListener(this);
			}
		}
		
		setBounds(0, 0, 800, 300);
		revalidate();
		repaint();
		
	}
	
	public void actionPerformed(ActionEvent atnEvt) {
		if ("delete" == atnEvt.getActionCommand()) {
			this.rowInd = table.getSelectedRow();
			if (-1 != this.rowInd) {
				customerModel.removeRow(this.rowInd);
				this.rowInd = -1;
			}
		}
		else if ("edit" == atnEvt.getActionCommand()) {
			this.rowInd = table.getSelectedRow();
			if (-1 != this.rowInd) {
				Customer c = this.custList.get(this.rowInd); // should be synchronized with table model's list
				custCidTxtF.setText(c.getCid());
				custCidTxtF.setEditable(false);
				custNameTxtF.setText(c.getCname());
				payMethdCombox.setSelectedItem(c.getPaymentMethod());
				payMethdCombox.setEnabled(false);
				int option = JOptionPane.showConfirmDialog(null, customerEditCtrls, "Customer Info", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					customerModel.setValueAt(custNameTxtF.getText(), this.rowInd, 1);
				} else {}
				this.rowInd = -1;
				resetCustEditCtrls();
			}
		}
		else if ("create" == atnEvt.getActionCommand()) {
			this.rowInd = -1;
			resetCustEditCtrls();
			int option = JOptionPane.showConfirmDialog(null, customerEditCtrls, "Customer Info", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				Customer tempCust1 = customerFactory.createCustomer(custCidTxtF.getText(), custNameTxtF.getText(), 
						payMethdCombox.getSelectedItem().toString());
				customerModel.addRow(tempCust1);
			} else {}
			resetCustEditCtrls();
		}
		else if ("save employee" == atnEvt.getActionCommand()) {	// manage things differently, not locally
			if (empEidTxtF.isEditable()) {	// create new employee
				Customers cs = new Customers();
				for (Customer temp : this.custList) {
					cs.add(temp.getCid(), temp);
				}
				Employee e = employeeFactory.createEmployee(empTypeCombox.getSelectedItem().toString(), empEidTxtF.getText(), 
												empFNameTxtF.getText(), empLNameTxtF.getText(), cs);
				// inform main
				SaveEmployeeEvent see = new SaveEmployeeEvent(this, e);
				goSaveEmployee(see);
			}
			else {	// edit employee
				Customers cs = new Customers();
				for (Customer temp : this.custList) {
					cs.add(temp.getCid(), temp);
				}
				Employee e = employeeFactory.createEmployee(empTypeCombox.getSelectedItem().toString(), empEidTxtF.getText(), 
													empFNameTxtF.getText(), empLNameTxtF.getText(), cs);
				// inform main
				SaveEmployeeEvent see = new SaveEmployeeEvent(this, e);
				goSaveEmployee(see);
			}
		}
		else if ("comboBoxChanged" == atnEvt.getActionCommand()) {
			empType = empTypeCombox.getSelectedItem().toString();
			if (EmployeeFactory.OTHERSTAFF == empType) {
				remove(scrollPane);
				remove(btnBar);
			}
			else {
				add(scrollPane);
				add(btnBar);
			}
			revalidate();
			repaint();
		}
		else {
			System.out.println("should never reach here: ");
		}
	}
	
	public void addSaveEmployeeListener(ISaveEmployeeListener seLtner) {
		saveEmpyListeners.add(seLtner);
    }
	
	private void goSaveEmployee(SaveEmployeeEvent seEvt) {
		for (ISaveEmployeeListener sel : saveEmpyListeners) {
            sel.saveEmpoyee(seEvt);
		}
	}
	
	private void resetCustEditCtrls() {
		custCidTxtF.setText("");
		custCidTxtF.setEditable(true);
		custNameTxtF.setText("");
		payMethdCombox.setEnabled(true);
	}
	
}