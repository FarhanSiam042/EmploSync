import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddEmployeeFrame extends JFrame {
    private JTextField nameField;
    private JTextField positionField;
    private JTextField salaryField;
    private JTextField daysPresentField;
    private JTextField daysAbsentField;
    private FileHandler fileHandler;

    public AddEmployeeFrame() {
        fileHandler = new FileHandler();

        setTitle("Add Employee");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField();
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Position:"), gbc);
        positionField = new JTextField();
        gbc.gridx = 1;
        add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Salary:"), gbc);
        salaryField = new JTextField();
        gbc.gridx = 1;
        add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Days Present:"), gbc);
        daysPresentField = new JTextField();
        gbc.gridx = 1;
        add(daysPresentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Days Absent:"), gbc);
        daysAbsentField = new JTextField();
        gbc.gridx = 1;
        add(daysAbsentField, gbc);

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 30));
        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        addButton.setBackground(new Color(255, 253, 208));
        addButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String position = positionField.getText();
                String salaryText = salaryField.getText();
                String daysPresentText = daysPresentField.getText();
                String daysAbsentText = daysAbsentField.getText();

                if (name.isEmpty() || position.isEmpty() || salaryText.isEmpty() || daysPresentText.isEmpty() || daysAbsentText.isEmpty()) {
                    JOptionPane.showMessageDialog(AddEmployeeFrame.this, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!name.matches("[a-zA-Z ]+") || !position.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Name and Position must be alphabetic", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double salary = Double.parseDouble(salaryText);
                    int daysPresent = Integer.parseInt(daysPresentText);
                    int daysAbsent = Integer.parseInt(daysAbsentText);

                    String id = generateEmployeeId();
                    Employee employee = new Employee(name, id, position, salary, daysPresent, daysAbsent);
                    fileHandler.addEmployeeToFile(employee);

                    JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Employee added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Salary must be a number and Days Present/Absent must be integers", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Error saving employee", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private String generateEmployeeId() {
        return "E" + System.currentTimeMillis(); // Simple ID generation logic, you can improve this
    }
}