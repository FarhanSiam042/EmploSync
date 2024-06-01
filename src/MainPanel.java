import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private ViewEmployeeFrame viewEmployeeFrame;
    private Image backgroundImage;

    public MainPanel() {
        // Load the background image
        backgroundImage = new ImageIcon("background.png").getImage(); // Provide the path to your main panel image file

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color creamColor = new Color(255, 228, 196);

        JButton addEmployeeButton = createCustomButton("Add Employee", buttonFont, creamColor, Color.BLACK);
        JButton removeEmployeeButton = createCustomButton("Remove Employee", buttonFont, creamColor, Color.BLACK);
        JButton viewEmployeeButton = createCustomButton("View Employee Details", buttonFont, creamColor, Color.BLACK);
        JButton calculateSalaryButton = createCustomButton("Calculate Salary", buttonFont, creamColor, Color.BLACK);
        JButton calculateBonusButton = createCustomButton("Calculate Bonus and Fines", buttonFont, creamColor, Color.BLACK);
        JButton calculateAttendanceButton = createCustomButton("Calculate Attendance", buttonFont, creamColor, Color.BLACK);
        JButton reviewFeedbackButton = createCustomButton("Review & Feedback", buttonFont, creamColor, Color.BLACK);

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(200, 60);
        addEmployeeButton.setPreferredSize(buttonSize);
        removeEmployeeButton.setPreferredSize(buttonSize);
        viewEmployeeButton.setPreferredSize(buttonSize);
        calculateSalaryButton.setPreferredSize(buttonSize);
        calculateBonusButton.setPreferredSize(buttonSize);
        calculateAttendanceButton.setPreferredSize(buttonSize);
        reviewFeedbackButton.setPreferredSize(buttonSize);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(addEmployeeButton, gbc);

        gbc.gridx = 1;
        add(removeEmployeeButton, gbc);

        gbc.gridx = 2;
        add(viewEmployeeButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(calculateSalaryButton, gbc);

        gbc.gridx = 1;
        add(calculateBonusButton, gbc);

        gbc.gridx = 2;
        add(calculateAttendanceButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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