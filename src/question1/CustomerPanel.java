package question1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import layout.*;
import question1.systemEvents.*;

public class CustomerPanel extends JPanel implements ActionListener {
	
	// component
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JLabel m_title;
	
	protected JPanel btnBar;
	protected JButton deleteCustBtn = new JButton("delete");
	protected JButton editCustBtn = new JButton("edit");
	protected JButton createCustBtn = new JButton("create");
	protected JButton saveCustBtn = new JButton("save");
	
	// employee property panel
	protected JPanel empyPropsBar = new JPanel();
	protected JTextField empEidTxtF = new JTextField();
	protected JTextField empFNameTxtF = new JTextField();
	protected JTextField empLNameTxtF = new JTextField();
	
	// customer property panel
	protected JPanel custPropsBar = new JPanel();
	protected JTextField custCidTxtF = new JTextField();
	protected JTextField custNameTxtF = new JTextField();
	
	//
	protected JButton saveEmpyBtn = new JButton("save employee");
	
	//
	ArrayList<Customer> custList = new ArrayList<Customer>();
	
	// keep track of selected row
	protected int rowInd = -1;
	
	
	protected CustomerTableModel customerModel;
	
	// custom event
	private List<SaveEmployeeListener> saveEmpyListeners = new ArrayList<SaveEmployeeListener>();
	
	public CustomerPanel(Employee empy)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if (null != empy) {	// edit
			if (null != empy.getCustomers()) {
				System.out.println("here: ");
				custList = new ArrayList<Customer>(empy.getCustomers().getAll());
			}
			else {}
			empEidTxtF.setText(empy.getEid());
			empEidTxtF.setEditable(false);
			empFNameTxtF.setText(empy.getFname());
			empLNameTxtF.setText(empy.getLname());
		}
		else {}	// create new employee
		
		
		//System.out.println("custList: " + custList.size());
		
		customerModel = new CustomerTableModel(custList);
		
		m_title = new JLabel(customerModel.getTitle());
		m_title.setFont(new Font("Helvetica", Font.PLAIN, 24));
		m_title.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(m_title);
		
		
		// employee property panel
		SpringLayout layout = new SpringLayout();
		empyPropsBar.setLayout(layout);
		empyPropsBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		empyPropsBar.setPreferredSize(new Dimension(200, 100));
		
		empyPropsBar.add(new JLabel("Employee Id: "));
		empEidTxtF.setMaximumSize(new Dimension(20, 10));
		empyPropsBar.add(empEidTxtF);
		
		empyPropsBar.add(new JLabel("Employee First Name: "));
		empFNameTxtF.setMaximumSize(new Dimension(20, 10));
		empyPropsBar.add(empFNameTxtF);
		
		empyPropsBar.add(new JLabel("Employee Last Name: "));
		empLNameTxtF.setMaximumSize(new Dimension(20, 10));
		empyPropsBar.add(empLNameTxtF);
		
		SpringUtilities.makeGrid(empyPropsBar,
                3, 2, //rows, cols
                5, 5, //initialX, initialY
                5, 5);//xPad, yPad
		
		add(empyPropsBar);
		
		
		// customer list
		table = new JTable(customerModel);
		// single row selection
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 220));
		scrollPane.setMaximumSize(new Dimension(800, 220));
		scrollPane.getViewport().add(table);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(scrollPane);
		
		btnBar = new JPanel();
		btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		deleteCustBtn.addActionListener(this);
		editCustBtn.addActionListener(this);
		createCustBtn.addActionListener(this);
		saveCustBtn.addActionListener(this);
		btnBar.add(deleteCustBtn);
		btnBar.add(editCustBtn);
		btnBar.add(createCustBtn);
		btnBar.add(saveCustBtn);
		add(btnBar);
		// customer list ends
		
		

		// customer property panel
		SpringLayout layout2 = new SpringLayout();
		custPropsBar.setLayout(layout2);
		custPropsBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		custPropsBar.setPreferredSize(new Dimension(200, 100));
		custPropsBar.add(new JLabel("Customer Id: "));
		custCidTxtF.setMaximumSize(new Dimension(20, 10));
		custPropsBar.add(custCidTxtF);
		custPropsBar.add(new JLabel("Customer Name: "));
		custNameTxtF.setMaximumSize(new Dimension(80, 10));
		custPropsBar.add(custNameTxtF);
		
		SpringUtilities.makeGrid(custPropsBar,
                2, 2, //rows, cols
                5, 5,
                5, 5);
		
		add(custPropsBar);
		// customer property panel ends
		
		
		// save the whole page, create an employee object and pass it to main
		saveEmpyBtn.addActionListener(this);
		add(saveEmpyBtn);
		
		setBounds(0, 0, 800, 300);
		
		revalidate();
		repaint();
		
	}
	
	public void actionPerformed(ActionEvent atnEvt) {
		
		if ("delete" == atnEvt.getActionCommand()) {
			int rowInd = table.getSelectedRow();
			String custKey = customerModel.getValueAt(rowInd, 0).toString();
			customerModel.removeRow(rowInd);
			this.rowInd = -1;
		}
		else if ("edit" == atnEvt.getActionCommand()) {
			this.rowInd = table.getSelectedRow();
			//String custKey = customerModel.getValueAt(rowInd, 0).toString();
			Customer c = this.custList.get(this.rowInd); // should be syn with table model's list
			custCidTxtF.setText(c.getCid());
			custCidTxtF.setEditable(false);
			custNameTxtF.setText(c.getCname());
		}
		else if ("create" == atnEvt.getActionCommand()) {
			this.rowInd = -1;
			custCidTxtF.setText("");
			custCidTxtF.setEditable(true);
			custNameTxtF.setText("");
		}
		else if ("save" == atnEvt.getActionCommand()) {
			
			if (-1!=this.rowInd) {	// edit
				customerModel.setValueAt(custNameTxtF.getText(), this.rowInd, 1);
			}
			else {	// create
				customerModel.addRow(new CustomerPayWithCreditCard(custCidTxtF.getText(), custNameTxtF.getText()));
			}
				
			this.rowInd = -1;
			custCidTxtF.setText("");
			custCidTxtF.setEditable(true);
			custNameTxtF.setText("");
		}
		else if ("save employee" == atnEvt.getActionCommand()) {	// manage things differently, not locally
			System.out.println("Bang");
			if (empEidTxtF.isEditable()) {	// create new
				Customers cs = new Customers();
				for (Customer temp : this.custList) {
					cs.add(temp.getCid(), temp);
				}
				Employee e = new SalesPerson(empEidTxtF.getText(), empFNameTxtF.getText(), empLNameTxtF.getText());
				e.setCustomers(cs);
				// inform main
				SaveEmployeeEvent see = new SaveEmployeeEvent(this, e);
				goSaveEmployee(see);
			}
			else {	// edit
				//System.out.println("#: " + this.custList.size());
				Customers cs = new Customers();
				for (Customer temp : this.custList) {
					cs.add(temp.getCid(), temp);
				}
				Employee e = new SalesPerson(empEidTxtF.getText(), empFNameTxtF.getText(), empLNameTxtF.getText());
				e.setCustomers(cs);
				// inform main
				SaveEmployeeEvent see = new SaveEmployeeEvent(this, e);
				goSaveEmployee(see);
			}
		}
		else {
			System.out.println("should never reach here");
		}
	}
	
	public void addSaveEmployeeListener(SaveEmployeeListener toAdd) {
		saveEmpyListeners.add(toAdd);
    }
	
	public void goSaveEmployee(SaveEmployeeEvent seEvt) {
		for (SaveEmployeeListener hl : saveEmpyListeners) {
            hl.saveEmpoyee(seEvt);
		}
	}
	
}

class CustomerTableModel extends AbstractTableModel {
	
	static final public String columnNames[] = {"Customer Id", "Customer Name", "Payment Method"};

	protected ArrayList<Customer> custList;

	public CustomerTableModel(ArrayList<Customer> custList) {
	    this.custList = custList;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= getRowCount()) {
    		return;
    	}
		Customer c = this.custList.get(rowIndex);
		switch (columnIndex) {
	      case 1: c.setCname(aValue.toString());
	    }
		this.fireTableCellUpdated(rowIndex, columnIndex);
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