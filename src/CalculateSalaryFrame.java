import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculateSalaryFrame extends JFrame {
    private JTextField idField;
    private JLabel salaryLabel;
    private FileHandler fileHandler;

    public CalculateSalaryFrame() {
        fileHandler = new FileHandler();

        setTitle("Calculate Salary");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        JButton calculateButton = new JButton("Calculate");
        add(calculateButton);

        salaryLabel = new JLabel("Salary: ");
        add(salaryLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String salary = calculateSalary(id);
                salaryLabel.setText("Salary: " + salary);
            }
        });

        setVisible(true);
    }

    private String calculateSalary(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employeedetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[1].equals(id)) {
                    double baseSalary = Double.parseDouble(details[3]);
                    int daysPresent = Integer.parseInt(details[4]);
                    int daysAbsent = Integer.parseInt(details[5]);
                    double attendancePercentage = (double) daysPresent / (daysPresent + daysAbsent) * 100;
                    double deductions = daysAbsent * 0.01 * baseSalary;
                    double bonus = attendancePercentage == 100 ? 0.02 * baseSalary : 0;
                    double finalSalary = baseSalary - deductions + bonus;
                    return String.format("%.2f", finalSalary);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Employee not found";
    }
}