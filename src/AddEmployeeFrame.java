import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

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
        setSize(300, 300);
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
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String position = positionField.getText();
                String salary = salaryField.getText();
                String daysPresent = daysPresentField.getText();
                String daysAbsent = daysAbsentField.getText();

                if (name.isEmpty() || position.isEmpty() || salary.isEmpty() || daysPresent.isEmpty() || daysAbsent.isEmpty()) {
                    JOptionPane.showMessageDialog(AddEmployeeFrame.this, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String id = generateRandomID();
                    try {
                        fileHandler.addEmployeeToFile(name, id, position, salary, daysPresent, daysAbsent);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(AddEmployeeFrame.this, "Error saving employee details", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private String generateRandomID() {
        Random rand = new Random();
        int id = rand.nextInt(9000) + 1000; // Generates a random 4-digit number
        return String.valueOf(id);
    }
}