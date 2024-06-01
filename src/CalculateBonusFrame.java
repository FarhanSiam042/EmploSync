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
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        JButton calculateButton = new JButton("Calculate");
        add(calculateButton);

        bonusLabel = new JLabel("Bonus/Fines: ");
        add(bonusLabel);

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