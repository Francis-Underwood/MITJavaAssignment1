package question1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
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
	private List<IEditEmployeeListener> editEmpyListeners = new ArrayList<IEditEmployeeListener>();
	private List<IDeleteEmployeeListener> delEmpyListeners = new ArrayList<IDeleteEmployeeListener>();
	private List<ICreateEmployeeListener> crteEmpyListeners = new ArrayList<ICreateEmployeeListener>();
	
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			if (-1 != rowInd) {
				String empyKey = employeeModel.getValueAt(rowInd, 0).toString();
				employeeModel.removeRow(rowInd);
				//  inform main
				DeleteEmployeeEvent dee = new DeleteEmployeeEvent(this, empyKey);
				goDeleteEmployee(dee);
			}
		}
		else if ("edit" == atnEvt.getActionCommand()) {
			int rowInd = table.getSelectedRow();
			if (-1 != rowInd) {
				String empyKey = employeeModel.getValueAt(rowInd, 0).toString();
				// inform main
				EditEmployeeEvent eee = new EditEmployeeEvent(this, empyKey);
				goEditEmployee(eee);
			}
		}
		else if ("create" == atnEvt.getActionCommand()) {
			CreateEmployeeEvent cee = new CreateEmployeeEvent(this);
			goCreateEmployee(cee);
		}
		else {
			System.out.println("should never reach here");
		}
	}
	
	public void addEditEmployeeListener(IEditEmployeeListener eeLtner) {
        editEmpyListeners.add(eeLtner);
    }
	
	public void removeEditEmployeeListener(IEditEmployeeListener eeLtner) {
        editEmpyListeners.remove(eeLtner);
    }
	
	public void addDeleteEmployeeListener(IDeleteEmployeeListener deLtner) {
		delEmpyListeners.add(deLtner);
    }
	
	public void removeDeleteEmployeeListener(IDeleteEmployeeListener deLtner) {
		delEmpyListeners.remove(deLtner);
    }
	
	public void addCreateEmployeeListener(ICreateEmployeeListener ceLtner) {
        crteEmpyListeners.add(ceLtner);
    }
	
	public void removeCreateEmployeeListener(ICreateEmployeeListener toRemove) {
        crteEmpyListeners.remove(toRemove);
    }
	
	private void goEditEmployee(EditEmployeeEvent eeEvt) {
		for (IEditEmployeeListener eel : editEmpyListeners) {
            eel.editEmpoyee(eeEvt);
		}
	}
	
	private void goDeleteEmployee(DeleteEmployeeEvent deEvt) {
		for (IDeleteEmployeeListener del : delEmpyListeners) {
            del.deleteEmpoyee(deEvt);
		}
	}
	
	private void goCreateEmployee(CreateEmployeeEvent ceEvt) {
		for (ICreateEmployeeListener cel : crteEmpyListeners) {
            cel.createEmpoyee(ceEvt);
		}
	}
	
}