import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AttendanceFrame extends JFrame {
    private FileHandler fileHandler;
    private JTextField idField;
    private JTextField presentField;
    private JTextField absentField;
    private JLabel resultLabel;

    public AttendanceFrame() {
        fileHandler = new FileHandler();

        setTitle("Calculate Attendance");
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

        JButton loadButton = new JButton("Load");
        loadButton.setBackground(new Color(255, 253, 208)); // Cream color for button
        loadButton.setForeground(Color.BLACK);
        gbc.gridx = 2;
        add(loadButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Days Present:"), gbc);
        presentField = new JTextField();
        gbc.gridx = 1;
        add(presentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Days Absent:"), gbc);
        absentField = new JTextField();
        gbc.gridx = 1;
        add(absentField, gbc);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(255, 253, 208)); // Cream color for button
        calculateButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(calculateButton, gbc);

        resultLabel = new JLabel("Attendance: ");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(resultLabel, gbc);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadEmployeeAttendance();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAttendance();
            }
        });

        setVisible(true);
    }

    private void loadEmployeeAttendance() {
        String id = idField.getText();
        Employee employee = fileHandler.getEmployeeById(id);
        if (employee != null) {
            presentField.setText(String.valueOf(employee.getDaysPresent()));
            absentField.setText(String.valueOf(employee.getDaysAbsent()));
        } else {
            JOptionPane.showMessageDialog(this, "Employee not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateAttendance() {
        String id = idField.getText();
        try {
            int daysPresent = Integer.parseInt(presentField.getText());
            int daysAbsent = Integer.parseInt(absentField.getText());

            fileHandler.updateEmployeeAttendance(id, daysPresent, daysAbsent);

            int totalDays = daysPresent + daysAbsent;
            int attendancePercentage = (int) (((double) daysPresent / totalDays) * 100);

            resultLabel.setText(String.format("Attendance: %d%%", attendancePercentage));
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            resultLabel.setText("Error calculating attendance");
        }
    }
}