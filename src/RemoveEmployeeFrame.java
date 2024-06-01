import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RemoveEmployeeFrame extends JFrame {
    private JTextField idField;
    private ViewEmployeeFrame viewEmployeeFrame;
    private FileHandler fileHandler;

    public RemoveEmployeeFrame(ViewEmployeeFrame viewEmployeeFrame) {
        this.viewEmployeeFrame = viewEmployeeFrame;
        this.fileHandler = new FileHandler();

        setTitle("Remove Employee");
        setSize(400, 150);
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

        JButton removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(100, 30));
        removeButton.setFont(new Font("Arial", Font.BOLD, 12));
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(removeButton, gbc);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                boolean removed = false;
                try {
                    removed = fileHandler.removeEmployeeFromFile(id);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(RemoveEmployeeFrame.this, "Error removing employee", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (removed) {
                    JOptionPane.showMessageDialog(RemoveEmployeeFrame.this, "Employee removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    if (viewEmployeeFrame != null) {
                        viewEmployeeFrame.loadEmployeeDetails();
                    }
                } else {
                    JOptionPane.showMessageDialog(RemoveEmployeeFrame.this, "Employee ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        setVisible(true);
    }
}