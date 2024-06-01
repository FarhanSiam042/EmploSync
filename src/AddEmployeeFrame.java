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
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        add(new JLabel("Days Present:"));
        daysPresentField = new JTextField();
        add(daysPresentField);

        add(new JLabel("Days Absent:"));
        daysAbsentField = new JTextField();
        add(daysAbsentField);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        add(addButton);
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