import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewEmployeeFrame extends JFrame {
    private FileHandler fileHandler;
    private JTextArea employeeDetailsArea;

    public ViewEmployeeFrame() {
        fileHandler = new FileHandler();

        setTitle("View Employee Details");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        employeeDetailsArea = new JTextArea();
        employeeDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(employeeDetailsArea);

        add(scrollPane, BorderLayout.CENTER);

        loadEmployeeDetails();

        setVisible(true);
    }

    private void loadEmployeeDetails() {
        try {
            List<Employee> employees = fileHandler.getEmployees();
            StringBuilder details = new StringBuilder();
            for (Employee emp : employees) {
                details.append("Name: ").append(emp.getName()).append("\n")
                        .append("ID: ").append(emp.getId()).append("\n")
                        .append("Position: ").append(emp.getPosition()).append("\n")
                        .append("Salary: ").append(emp.getSalary()).append("\n")
                        .append("Days Present: ").append(emp.getDaysPresent()).append("\n")
                        .append("Days Absent: ").append(emp.getDaysAbsent()).append("\n\n");
            }
            employeeDetailsArea.setText(details.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeList() {
        loadEmployeeDetails();
    }
}