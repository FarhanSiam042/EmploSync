import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CalculateBonusFrame extends JFrame {
    private FileHandler fileHandler;
    private JTextField idField;
    private JLabel resultLabel;

    public CalculateBonusFrame() {
        fileHandler = new FileHandler();

        setTitle("Calculate Bonus and Fines");
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

        resultLabel = new JLabel("Bonus and Fines: ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(resultLabel, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateBonusFines();
            }
        });

        setVisible(true);
    }

    private void calculateBonusFines() {
        String id = idField.getText();

        try {
            Employee employee = fileHandler.getEmployeeById(id);
            if (employee != null) {
                double salary = employee.getSalary();
                double attendancePercentage = ((double) employee.getDaysPresent() / (employee.getDaysPresent() + employee.getDaysAbsent())) * 100;
                double bonus = 0;
                double fine = 0;

                if (attendancePercentage == 100) {
                    bonus = salary * 0.02; // 2% bonus for perfect attendance
                }
                fine = salary * (0.01 * employee.getDaysAbsent()); // 1% deduction per day absent

                resultLabel.setText(String.format("Bonus: %.2f, Fine: %.2f", bonus, fine));
            } else {
                resultLabel.setText("Employee not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error calculating bonus and fines");
        }
    }
}