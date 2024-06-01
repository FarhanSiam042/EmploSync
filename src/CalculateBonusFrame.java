import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalculateBonusFrame extends JFrame {
    private JTextField idField;
    private JLabel bonusLabel;
    private FileHandler fileHandler;

    public CalculateBonusFrame() {
        fileHandler = new FileHandler();

        setTitle("Calculate Bonus and Fines");
        setSize(400, 200);
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
        calculateButton.setPreferredSize(new Dimension(100, 30));
        calculateButton.setFont(new Font("Arial", Font.BOLD, 12));
        calculateButton.setBackground(Color.CYAN);
        calculateButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(calculateButton, gbc);

        bonusLabel = new JLabel("Bonus/Fines: ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(bonusLabel, gbc);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String bonusFines = calculateBonusFines(id);
                bonusLabel.setText("Bonus/Fines: " + bonusFines);
            }
        });

        setVisible(true);
    }

    private String calculateBonusFines(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employeedetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[1].equals(id)) {
                    int daysPresent = Integer.parseInt(details[4]);
                    int daysAbsent = Integer.parseInt(details[5]);
                    double baseSalary = Double.parseDouble(details[3]);
                    double attendancePercentage = (double) daysPresent / (daysPresent + daysAbsent) * 100;
                    double fines = daysAbsent * 0.01 * baseSalary;
                    double bonus = attendancePercentage == 100 ? 0.02 * baseSalary : 0;
                    return String.format("Bonus: %.2f, Fines: %.2f", bonus, fines);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Employee not found";
    }
}