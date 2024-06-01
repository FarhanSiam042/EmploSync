import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AttendanceFrame extends JFrame {
    private JTextField idField;
    private JTextField daysPresentField;
    private JTextField daysAbsentField;
    private JLabel attendanceLabel;
    private FileHandler fileHandler;

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

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Days Present:"), gbc);
        daysPresentField = new JTextField();
        gbc.gridx = 1;
        add(daysPresentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Days Absent:"), gbc);
        daysAbsentField = new JTextField();
        gbc.gridx = 1;
        add(daysAbsentField, gbc);

        JButton loadButton = new JButton("Load Data");
        loadButton.setPreferredSize(new Dimension(100, 30));
        loadButton.setFont(new Font("Arial", Font.BOLD, 12));
        loadButton.setBackground(Color.LIGHT_GRAY);
        loadButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loadButton, gbc);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(100, 30));
        calculateButton.setFont(new Font("Arial", Font.BOLD, 12));
        calculateButton.setBackground(Color.MAGENTA);
        calculateButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(calculateButton, gbc);

        attendanceLabel = new JLabel("Attendance: ");
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(attendanceLabel, gbc);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (!id.isEmpty()) {
                    Map<String, String> attendanceData = loadAttendanceData(id);
                    if (attendanceData != null) {
                        daysPresentField.setText(attendanceData.get("daysPresent"));
                        daysAbsentField.setText(attendanceData.get("daysAbsent"));
                    } else {
                        JOptionPane.showMessageDialog(AttendanceFrame.this, "Employee ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AttendanceFrame.this, "Please enter an Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty() || daysPresentField.getText().isEmpty() || daysAbsentField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AttendanceFrame.this, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int daysPresent = Integer.parseInt(daysPresentField.getText());
                    int daysAbsent = Integer.parseInt(daysAbsentField.getText());
                    int attendance = calculateAttendance(daysPresent, daysAbsent);
                    attendanceLabel.setText("Attendance: " + attendance + "%");
                    fileHandler.updateEmployeeAttendance(id, daysPresent, daysAbsent);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AttendanceFrame.this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AttendanceFrame.this, "Error updating attendance", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private int calculateAttendance(int daysPresent, int daysAbsent) {
        int totalDays = daysPresent + daysAbsent;
        return (int) Math.round(((double) daysPresent / totalDays) * 100);
    }

    private Map<String, String> loadAttendanceData(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employeedetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[1].equals(id)) {
                    Map<String, String> data = new HashMap<>();
                    data.put("daysPresent", details[4]);
                    data.put("daysAbsent", details[5]);
                    return data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}