import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Employee ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Days Present:"));
        daysPresentField = new JTextField();
        add(daysPresentField);

        add(new JLabel("Days Absent:"));
        daysAbsentField = new JTextField();
        add(daysAbsentField);

        JButton calculateButton = new JButton("Calculate");
        add(calculateButton);

        attendanceLabel = new JLabel("Attendance: ");
        add(attendanceLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                int daysPresent = Integer.parseInt(daysPresentField.getText());
                int daysAbsent = Integer.parseInt(daysAbsentField.getText());
                int attendance = calculateAttendance(daysPresent, daysAbsent);
                attendanceLabel.setText("Attendance: " + attendance + "%");
                try {
                    fileHandler.updateEmployeeAttendance(id, daysPresent, daysAbsent);
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
        return (daysPresent * 100) / totalDays;
    }
}