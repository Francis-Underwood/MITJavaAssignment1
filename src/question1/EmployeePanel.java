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

public class EmployeePanel extends JPanel implements ActionListener 
{
	protected JScrollPane scrollPane;
	protected JTable table;
	protected JLabel m_title;
	protected JPanel btnBar;
	protected JButton deleteBtn;
	protected JButton editBtn;
	protected EmployeeTableModel employeeModel;
	
	public EmployeePanel (ArrayList<Employee> empList) 
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		employeeModel = new EmployeeTableModel(empList);
		m_title = new JLabel(employeeModel.getTitle());
		m_title.setFont(new Font("Helvetica", Font.PLAIN, 24));
		m_title.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(m_title);
		
		
		table = new JTable(employeeModel);
		// single row selection
		//table.set
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));
		scrollPane = new JScrollPane();
		//table.setFillsViewportHeight(true);
		
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
	}
	
	public void actionPerformed(ActionEvent atnEvt)
	{
		if ("delete" == atnEvt.getActionCommand())
		{
			int rowInd = table.getSelectedRow();
			String empyKey = employeeModel.getValueAt(rowInd, 0).toString();
			
			employeeModel.removeRow(rowInd);
			// inform main
		}
		else if ("edit" == atnEvt.getActionCommand())
		{
			int rowInd = table.getSelectedRow();
			String empyKey = employeeModel.getValueAt(rowInd, 0).toString();
		}
		else
		{
			System.out.println("should never reach here");
		}
	}
	
	public void insertEmployee(Employee emp)
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
	
	public void removeRow(int index)
	{
		empList.remove(index);
		this.fireTableRowsDeleted(index, index);
		//System.out.println("here");
	}
	
	public void addRow(Employee emp)
	{
		empList.add(emp);
		this.fireTableRowsInserted(empList.size()-1, empList.size()-1);
	}
	
	public String getTitle() 
	{
		return "Employee List";
	}
	
}