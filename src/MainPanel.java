import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private ViewEmployeeFrame viewEmployeeFrame;

    public MainPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Font buttonFont = new Font("Arial", Font.BOLD, 16); // Increased font size for better visibility

        JButton addEmployeeButton = createCustomButton("Add Employee", buttonFont, Color.BLUE, Color.WHITE);
        JButton removeEmployeeButton = createCustomButton("Remove Employee", buttonFont, Color.RED, Color.WHITE);
        JButton viewEmployeeButton = createCustomButton("View Employee Details", buttonFont, Color.GREEN, Color.BLACK);
        JButton calculateSalaryButton = createCustomButton("Calculate Salary", buttonFont, Color.ORANGE, Color.BLACK);
        JButton calculateBonusButton = createCustomButton("Calculate Bonus and Fines", buttonFont, Color.CYAN, Color.BLACK);
        JButton calculateAttendanceButton = createCustomButton("Calculate Attendance", buttonFont, Color.MAGENTA, Color.BLACK);
        JButton reviewFeedbackButton = createCustomButton("Review & Feedback", buttonFont, Color.PINK, Color.BLACK);

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(250, 60);
        addEmployeeButton.setPreferredSize(buttonSize);
        removeEmployeeButton.setPreferredSize(buttonSize);
        viewEmployeeButton.setPreferredSize(buttonSize);
        calculateSalaryButton.setPreferredSize(buttonSize);
        calculateBonusButton.setPreferredSize(buttonSize);
        calculateAttendanceButton.setPreferredSize(buttonSize);
        reviewFeedbackButton.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addEmployeeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(removeEmployeeButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(viewEmployeeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(calculateSalaryButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(calculateBonusButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(calculateAttendanceButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(reviewFeedbackButton, gbc);

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