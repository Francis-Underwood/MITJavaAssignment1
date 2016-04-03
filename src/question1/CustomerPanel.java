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
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import layout.*;

public class CustomerPanel extends JPanel implements ActionListener {
	
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JLabel m_title;
	
	protected JPanel btnBar;
	protected JButton deleteBtn;
	protected JButton editBtn;
	
	protected JPanel txtFidBar;
	protected JTextField empEidTxtF;
	protected JTextField empFNameTxtF;
	protected JTextField empLNameTxtF;
	
	protected CustomerTableModel customerModel;
	
	public CustomerPanel(Employee empy) 
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		empFNameTxtF = new JTextField();
		empFNameTxtF.setMaximumSize(new Dimension(20, 10));
		empLNameTxtF = new JTextField();
		empLNameTxtF.setMaximumSize(new Dimension(20, 10));
		empEidTxtF = new JTextField();
		empEidTxtF.setMaximumSize(new Dimension(20, 10));
		
		
		ArrayList<Customer> custList;
		if (null != empy) {	// edit
			if (null != empy.getCustomers()) {
				System.out.println("here: ");
				custList = new ArrayList<Customer>(empy.getCustomers().getAll());
				System.out.println("here 2: ");
			}
			else {
				custList = new ArrayList<Customer>();
			}
			empEidTxtF.setText(empy.getEid());
			empFNameTxtF.setText(empy.getFname());
			empLNameTxtF.setText(empy.getLname());
		}
		else {	// new
			custList = new ArrayList<Customer>();
		}
		
		
		System.out.println("custList: " + custList.size());
		
		customerModel = new CustomerTableModel(custList);
		
		m_title = new JLabel(customerModel.getTitle());
		m_title.setFont(new Font("Helvetica", Font.PLAIN, 24));
		m_title.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(m_title);
		
		
		//
		txtFidBar = new JPanel();
		SpringLayout layout = new SpringLayout();
		txtFidBar.setLayout(layout);
		txtFidBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtFidBar.setPreferredSize(new Dimension(200, 100));
		
		
		
		txtFidBar.add(new JLabel("Employee Id: "));
		txtFidBar.add(empEidTxtF);
		txtFidBar.add(new JLabel("Employee First Name: "));
		txtFidBar.add(empFNameTxtF);
		txtFidBar.add(new JLabel("Employee Last Name: "));
		txtFidBar.add(empLNameTxtF);
		
		//txtFidBar.revalidate();
		//txtFidBar.repaint();
		
		SpringUtilities.makeGrid(txtFidBar,
                3, 2, //rows, cols
                5, 5, //initialX, initialY
                5, 5);//xPad, yPad
		
		add(txtFidBar);
		
		
		
		table = new JTable(customerModel);
		// single row selection
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(800, 300));
		scrollPane.setMaximumSize(new Dimension(800, 300));
		scrollPane.getViewport().add(table);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(scrollPane);
		
		btnBar = new JPanel();
		btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		deleteBtn = new JButton("delete");
		deleteBtn.addActionListener(this);
		editBtn = new JButton("edit");
		editBtn.addActionListener(this);
		btnBar.add(deleteBtn);
		btnBar.add(editBtn);
		add(btnBar);
		
		setBounds(0, 0, 800, 300);
		
		revalidate();
		repaint();
		
	}
	
	public void actionPerformed(ActionEvent atnEvt) {
		
		if ("delete" == atnEvt.getActionCommand()) {
			int rowInd = table.getSelectedRow();
			String custKey = customerModel.getValueAt(rowInd, 0).toString();
			
			customerModel.removeRow(rowInd);
			// inform main
		}
		else if ("edit" == atnEvt.getActionCommand()) {
			int rowInd = table.getSelectedRow();
			String custKey = customerModel.getValueAt(rowInd, 0).toString();
		}
		else {
			System.out.println("should never reach here");
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
		this.fireTableRowsInserted(custList.size()-1, custList.size()-1);
	}
	
	public String getTitle() {
		return "Employee Editing Page";
	}
	
}