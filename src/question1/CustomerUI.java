package question1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;


public class CustomerUI extends JFrame implements ActionListener {
	String feelNLook = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	JTextField txtCustId, txtCustName;
	JButton btnAdd;
	
	public CustomerUI (ArrayList<Customer> customerList) {
		//
		setTitle("Customer Master");
		//setLayout(null);
		setBounds(10,10,1000,600);

		txtCustId = new JTextField();
		txtCustName = new JTextField();
		txtCustId.setBounds(20,30,50,20);
		txtCustName.setBounds(20,55,150,20);
		
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(145,85,80,40);
		
		Container con = getContentPane();
		
		con.add(btnAdd);
		
		
	
		JTable table = new JTable(new CustomerTableModel(customerList));
		
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));
		//
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		
		
		JPanel p = new JPanel();
		p.add(scrollPane);
		p.setBounds(0, 200, 600, 300);
		
		con.add(p);
		
		
		try {
			UIManager.setLookAndFeel(feelNLook);
			SwingUtilities.updateComponentTreeUI(this);
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
	    
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
	}
}


class CustomerTableModel extends AbstractTableModel {
	static final public String[] columnNames = {"Customer Id", "Customer Name"};
	protected ArrayList<Customer> customerList;
    
	public CustomerTableModel(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
    
    public void setCustomerList(ArrayList<Customer> customerList) {
    	this.customerList = customerList;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return customerList.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
    	if (row < 0 || row>=getRowCount())
    	      return "";
    	/*
    	    StockData row = (StockData)m_vector.elementAt(row);
    	    switch (col) {
    	      case 0: return row.m_symbol;
    	      case 1: return row.m_name;
    	      case 2: return row.m_last;
    	      case 3: return row.m_open;
    	      case 4: return row.m_change;
    	      case 5: return row.m_changePr;
    	      case 6: return row.m_volume;
    	    }
    	   */
    	Customer c = this.customerList.get(row);
    	switch (col) {
	      case 0: return c.getCid();
	      case 1: return c.getCname();
	    }
    	
    	    return "";
    }
/*
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
  */
}





