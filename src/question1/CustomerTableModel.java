package question1;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel {
	
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