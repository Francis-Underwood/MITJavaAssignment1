package question1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import question1.systemEvents.*;

public class EmployeePanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -1289644983315427833L;
	private ArrayList<Employee> empList;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel m_title;
	private JPanel btnBar = new JPanel();
	private JButton deleteBtn = new JButton("delete");
	private JButton editBtn = new JButton("edit");
	private JButton createBtn = new JButton("create");
	private EmployeeTableModel employeeModel;
	
	// custom event
	private List<EditEmployeeListener> editEmpyListeners = new ArrayList<EditEmployeeListener>();
	private List<DeleteEmployeeListener> delEmpyListeners = new ArrayList<DeleteEmployeeListener>();
	private List<CreateEmployeeListener> crteEmpyListeners = new ArrayList<CreateEmployeeListener>();
	
	public EmployeePanel (ArrayList<Employee> empList) {
		this.empList = empList;
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
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		scrollPane.setMaximumSize(new Dimension(800, 300));
		scrollPane.getViewport().add(table);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(scrollPane);
		
		btnBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		btnBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		deleteBtn.addActionListener(this);
		editBtn.addActionListener(this);
		createBtn.addActionListener(this);
		
		btnBar.add(deleteBtn);
		btnBar.add(editBtn);
		btnBar.add(createBtn);
		add(btnBar);
		
		setBounds(0, 0, 800, 300);
	}
	
	public void actionPerformed(ActionEvent atnEvt)	{
		if ("delete" == atnEvt.getActionCommand()) {
			int rowInd = table.getSelectedRow();
			String empyKey = employeeModel.getValueAt(rowInd, 0).toString();
			employeeModel.removeRow(rowInd);
			//  inform main
			DeleteEmployeeEvent dee = new DeleteEmployeeEvent(this, empyKey);
			goDeleteEmployee(dee);
		}
		else if ("edit" == atnEvt.getActionCommand()) {
			int rowInd = table.getSelectedRow();
			String empyKey = employeeModel.getValueAt(rowInd, 0).toString();
			// inform main
			EditEmployeeEvent eee = new EditEmployeeEvent(this, empyKey);
			goEditEmployee(eee);
		}
		else if ("create" == atnEvt.getActionCommand()) {
			CreateEmployeeEvent cee = new CreateEmployeeEvent(this);
			goCreateEmployee(cee);
		}
		else {
			System.out.println("should never reach here");
		}
	}
	
	public void addEditEmployeeListener(EditEmployeeListener eeLtner) {
        editEmpyListeners.add(eeLtner);
    }
	
	public void removeEditEmployeeListener(EditEmployeeListener eeLtner) {
        editEmpyListeners.remove(eeLtner);
    }
	
	public void addDeleteEmployeeListener(DeleteEmployeeListener deLtner) {
		delEmpyListeners.add(deLtner);
    }
	
	public void removeDeleteEmployeeListener(DeleteEmployeeListener deLtner) {
		delEmpyListeners.remove(deLtner);
    }
	
	public void addCreateEmployeeListener(CreateEmployeeListener ceLtner) {
        crteEmpyListeners.add(ceLtner);
    }
	
	public void removeCreateEmployeeListener(CreateEmployeeListener toRemove) {
        crteEmpyListeners.remove(toRemove);
    }
	
	private void goEditEmployee(EditEmployeeEvent eeEvt) {
		for (EditEmployeeListener hl : editEmpyListeners) {
            hl.editEmpoyee(eeEvt);
		}
	}
	
	private void goDeleteEmployee(DeleteEmployeeEvent deEvt) {
		for (DeleteEmployeeListener hl : delEmpyListeners) {
            hl.deleteEmpoyee(deEvt);
		}
	}
	
	private void goCreateEmployee(CreateEmployeeEvent ceEvt) {
		for (CreateEmployeeListener hl : crteEmpyListeners) {
            hl.createEmpoyee(ceEvt);
		}
	}
	
}

class EmployeeTableModel extends AbstractTableModel {
	
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