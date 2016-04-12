package question1;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 2735947194297668970L;
	private static final String columnNames[] = {"Employee Id", "Employee First Name", "Employee Last Name", "Category",
												"# of Customers"};
	protected ArrayList<Employee> empList;
	
	public EmployeeTableModel(ArrayList<Employee> empList) {
	    this.empList = empList;
	}
	
	@Override
	public int getColumnCount() {
	    return columnNames.length;
	}
	
	@Override
    public int getRowCount() {
        return empList.size();
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
    	Employee e = this.empList.get(row);
    	switch (col) {
    		case 0: return e.getEid();
    		case 1: return e.getFname();
    		case 2: return e.getLname();
    		case 3: return (e instanceof SalesPerson)?EmployeeFactory.SALESPERSON:EmployeeFactory.OTHERSTAFF;
    		case 4: return (e instanceof SalesPerson)?((SalesPerson)e).getCustomers().size():"N/A";
	    }
    	return "";
    }
	
	public void removeRow(int index) {
		empList.remove(index);
		this.fireTableRowsDeleted(index, index);
	}
	
	public void addRow(Employee emp) {
		empList.add(emp);
		this.fireTableRowsInserted(empList.size()-1, empList.size()-1);
	}
	
	public String getTitle() {
		return "Employee List";
	}
	
}