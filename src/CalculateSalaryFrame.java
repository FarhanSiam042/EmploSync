import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class CalculateSalaryFrame extends JFrame {
    private FileHandler fileHandler;
    private JTextField idField;
    private JLabel resultLabel;

    public CalculateSalaryFrame() {
        fileHandler = new FileHandler();

        setTitle("Calculate Salary");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Employee ID:"), gbc);
        idField = new JTextField();
        gbc.gridx = 1;
        add(idField, gbc);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(255, 253, 208)); // Cream color for button
        calculateButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(calculateButton, gbc);

        resultLabel = new JLabel("Salary: ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(resultLabel, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSalary();
            }
        });

        setVisible(true);
    }

    private void calculateSalary() {
        String id = idField.getText();

        try {
            Employee employee = fileHandler.getEmployeeById(id);
            if (employee != null) {
                double salary = employee.getSalary();
                double attendancePercentage = ((double) employee.getDaysPresent() / (employee.getDaysPresent() + employee.getDaysAbsent())) * 100;
                if (attendancePercentage == 100) {
                    salary += salary * 0.02; // 2% bonus for perfect attendance
                }
                salary -= salary * (0.01 * employee.getDaysAbsent()); // 1% deduction per day absent
                resultLabel.setText(String.format("Salary: %.2f", salary));
            } else {
                resultLabel.setText("Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error calculating salary");
        }
    }
}