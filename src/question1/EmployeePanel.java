package question1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class EmployeePanel extends JPanel implements ActionListener 
{
	JScrollPane scrollPane;
	JTable table;
	JLabel bl;
	
	public EmployeePanel (ArrayList<Employee> empList) 
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		table = new JTable(new EmployeeTableModel(empList));
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));
		scrollPane = new JScrollPane();
		//table.setFillsViewportHeight(true);
		//scrollPane.setSize(600, 500);
		scrollPane.getViewport().add(table);
		add(scrollPane);
		
		//bl = new JLabel("border");
		//add(bl);
		//setBounds(0, 0, 800, 300);
	}
	
	public void actionPerformed(ActionEvent atnEvt)
	{
		
	}
	
}

class EmployeeTableModel extends AbstractTableModel 
{
	
	static final public String columnNames[] = {"Employee Id", "Employee First Name", "Employee Last Name", 
												"# of Customers"};
	protected ArrayList<Employee> empList;
	
	public EmployeeTableModel(ArrayList<Employee> empList) 
	{
	    this.empList = empList;
	}
	
	@Override
	public int getColumnCount() 
	{
	    return columnNames.length;
	}
	
	@Override
    public int getRowCount() 
	{
        return empList.size();
    }
	
	 @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
	 
	@Override
    public Object getValueAt(int row, int col) 
	{
    	if (row < 0 || row >= getRowCount()) 
    	{
    	      return "";
    	}

    	Employee e = this.empList.get(row);
    	switch (col) 
    	{
	      case 0: return e.getEid();
	      case 1: return e.getFname();
	      case 2: return e.getLname();
	      case 3: return e.getCustomers().size();
	    }
    	
    	return "";
    }
	
	
	public String getTitle() 
	{
		return "Employee List";
	}
	
}