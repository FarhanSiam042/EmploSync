import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ViewEmployeeFrame extends JFrame {
    private JTextArea textArea;
    private FileHandler fileHandler;

    public ViewEmployeeFrame() {
        fileHandler = new FileHandler();

        setTitle("View Employee Details");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        loadEmployeeDetails();
        setVisible(true);
    }

    public void loadEmployeeDetails() {
        textArea.setText(""); // Clear existing content

        try {
            List<String> employeeDetails = fileHandler.readEmployeeDetails();
            for (String detail : employeeDetails) {
                textArea.append(detail + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}