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
import javax.swing.table.*;
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
	private List<SaveEmployeeListener> saveEmpyListeners = new ArrayList<SaveEmployeeListener>();
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
		// single row selection
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
			int rowInd = table.getSelectedRow();
			customerModel.removeRow(rowInd);
			this.rowInd = -1;
		}
		else if ("edit" == atnEvt.getActionCommand()) {
			this.rowInd = table.getSelectedRow();
			Customer c = this.custList.get(this.rowInd); // should be synchronized with table model's list
			custCidTxtF.setText(c.getCid());
			custCidTxtF.setEditable(false);
			custNameTxtF.setText(c.getCname());
			payMethdCombox.setSelectedItem(c.getPaymentMethod());
			payMethdCombox.setEnabled(false);
			Object[] message = {
				    "Customer Id:", custCidTxtF,
				    "Customer name:", custNameTxtF,
				    "Payment method:", payMethdCombox
				};
			int option = JOptionPane.showConfirmDialog(null, message, "Customer Info", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				customerModel.setValueAt(custNameTxtF.getText(), this.rowInd, 1);
			} else {}
			this.rowInd = -1;
			custCidTxtF.setText("");
			custCidTxtF.setEditable(true);
			custNameTxtF.setText("");
			payMethdCombox.setEnabled(true);
		}
		else if ("create" == atnEvt.getActionCommand()) {
			this.rowInd = -1;
			custCidTxtF.setText("");
			custCidTxtF.setEditable(true);
			custNameTxtF.setText("");
			payMethdCombox.setEnabled(true);
			Object[] message = {
				    "Customer Id:", custCidTxtF,
				    "Customer name:", custNameTxtF,
				    "Payment method:", payMethdCombox
				};
			int option = JOptionPane.showConfirmDialog(null, message, "Customer Info", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				Customer tempCust1 = customerFactory.createCustomer(custCidTxtF.getText(), custNameTxtF.getText(), 
						payMethdCombox.getSelectedItem().toString());
				customerModel.addRow(tempCust1);
			} else {}
			custCidTxtF.setText("");
			custCidTxtF.setEditable(true);
			custNameTxtF.setText("");
			payMethdCombox.setEnabled(true);
		}
		else if ("save employee" == atnEvt.getActionCommand()) {	// manage things differently, not locally
			if (empEidTxtF.isEditable()) {	// create new
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
			else {	// edit
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
			System.out.println("should never reach here: " + atnEvt.getActionCommand());
		}
	}
	
	public void addSaveEmployeeListener(SaveEmployeeListener seLtner) {
		saveEmpyListeners.add(seLtner);
    }
	
	private void goSaveEmployee(SaveEmployeeEvent seEvt) {
		for (SaveEmployeeListener hl : saveEmpyListeners) {
            hl.saveEmpoyee(seEvt);
		}
	}
	
}


class CustomerTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2724810585683461282L;

	private static final String columnNames[] = {"Customer Id", "Customer Name", "Payment Method"};

	private ArrayList<Customer> custList;

	public CustomerTableModel(ArrayList<Customer> custList) {
	    this.custList = custList;
	}
	
	@Override
	public void setValueAt(Object val, int row, int col) {
		if (row < 0 || row >= getRowCount()) {
    		return;
    	}
		Customer c = this.custList.get(row);
		switch (col) {
	      case 1: c.setCname(val.toString());
	    }
		this.fireTableCellUpdated(row, col);
	}
	
	@Override
	public int getColumnCount() {
	    return columnNames.length;
	}
	
	@Override
    public int getRowCount() {
        return custList.size();
    }
	
	@Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
	
	@Override
    public Object getValueAt(int row, int col) {
    	if (row < 0 || row >= getRowCount()) {
    		return "";
    	}
    	Customer c = this.custList.get(row);
    	switch (col) {
	      case 0: return c.getCid();
	      case 1: return c.getCname();
	      case 2: return c.getPaymentMethod();
	    }
    	return "";
    }
	
	public void removeRow(int index) {
		custList.remove(index);
		this.fireTableRowsDeleted(index, index);
	}
	
	public void addRow(Customer cust) {
		custList.add(cust);
		this.fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}
	
	public String getTitle() {
		return "Employee Editing Page";
	}
	
}