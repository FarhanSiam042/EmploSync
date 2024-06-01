import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private ViewEmployeeFrame viewEmployeeFrame;

    public MainPanel() {
        setLayout(null); // Use absolute positioning

        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        JButton addEmployeeButton = createCustomButton("Add Employee", buttonFont, Color.BLUE, Color.WHITE);
        JButton removeEmployeeButton = createCustomButton("Remove Employee", buttonFont, Color.RED, Color.WHITE);
        JButton viewEmployeeButton = createCustomButton("View Employee Details", buttonFont, Color.GREEN, Color.BLACK);
        JButton calculateSalaryButton = createCustomButton("Calculate Salary", buttonFont, Color.ORANGE, Color.BLACK);
        JButton calculateBonusButton = createCustomButton("Calculate Bonus and Fines", buttonFont, Color.CYAN, Color.BLACK);
        JButton calculateAttendanceButton = createCustomButton("Calculate Attendance", buttonFont, Color.MAGENTA, Color.BLACK);
        JButton reviewFeedbackButton = createCustomButton("Review & Feedback", buttonFont, Color.PINK, Color.BLACK);

        addEmployeeButton.setBounds(50, 50, 150, 50);
        removeEmployeeButton.setBounds(250, 50, 150, 50);
        viewEmployeeButton.setBounds(450, 50, 150, 50);
        calculateSalaryButton.setBounds(50, 150, 150, 50);
        calculateBonusButton.setBounds(250, 150, 150, 50);
        calculateAttendanceButton.setBounds(450, 150, 150, 50);
        reviewFeedbackButton.setBounds(250, 250, 150, 50);

        add(addEmployeeButton);
        add(removeEmployeeButton);
        add(viewEmployeeButton);
        add(calculateSalaryButton);
        add(calculateBonusButton);
        add(calculateAttendanceButton);
        add(reviewFeedbackButton);

        addEmployeeButton.addActionListener(e -> new AddEmployeeFrame());
        removeEmployeeButton.addActionListener(e -> {
            if (viewEmployeeFrame == null || !viewEmployeeFrame.isShowing()) {
                new RemoveEmployeeFrame(null);
            } else {
                new RemoveEmployeeFrame(viewEmployeeFrame);
            }
        });
        viewEmployeeButton.addActionListener(e -> {
            if (viewEmployeeFrame == null || !viewEmployeeFrame.isShowing()) {
                viewEmployeeFrame = new ViewEmployeeFrame();
            } else {
                viewEmployeeFrame.toFront();
            }
        });
        calculateSalaryButton.addActionListener(e -> new CalculateSalaryFrame());
        calculateBonusButton.addActionListener(e -> new CalculateBonusFrame());
        calculateAttendanceButton.addActionListener(e -> new AttendanceFrame());
        reviewFeedbackButton.addActionListener(e -> new ReviewFeedbackFrame());
    }

    private JButton createCustomButton(String text, Font font, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
}