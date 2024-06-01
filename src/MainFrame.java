import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel);
    }
}