package question1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;

public class CustomerUI extends JFrame implements ActionListener{
	JTextField txtCustId, txtCustName;
	JButton btnAdd;
	
	public CustomerUI () {
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
		
		
	
		JTable table = new JTable(new MyTableModel());
		
		
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));
		//
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		
		
		JPanel p = new JPanel();
		p.add(scrollPane);
		p.setBounds(0, 200, 600, 300);
		
		con.add(p);
		
		
  
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
	}
}


class MyTableModel extends AbstractTableModel  {
    String[] columnNames = {"First Name",
                                "Last Name",
                                "Sport",
                                "# of Years",
                                "Vegetarian"};
 
    Object[][] data = {
        {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
        {"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
        {"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
        {"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
        {"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
        };
        
  
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
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





