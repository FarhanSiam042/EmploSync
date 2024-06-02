import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReviewFeedbackFrame extends JFrame {
    private JTextField idField;
    private JTextArea commentArea;
    private JTextArea viewCommentsArea;
    private FileHandler fileHandler;

    public ReviewFeedbackFrame() {
        fileHandler = new FileHandler();

        setTitle("Review & Feedback");
        setSize(600, 400);
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
        gbc.gridwidth = 2;
        add(new JLabel("Add Comment:"), gbc);

        commentArea = new JTextArea(5, 20);
        JScrollPane commentScrollPane = new JScrollPane(commentArea);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        add(commentScrollPane, gbc);

        JButton addButton = new JButton("Add Comment");
        addButton.setBackground(new Color(255, 253, 208)); // Cream color for button
        addButton.setForeground(Color.BLACK);
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(addButton, gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("View Comments:"), gbc);

        viewCommentsArea = new JTextArea(10, 30);
        viewCommentsArea.setEditable(false);
        JScrollPane viewCommentsScrollPane = new JScrollPane(viewCommentsArea);
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(viewCommentsScrollPane, gbc);

        JButton viewButton = new JButton("View Comments");
        viewButton.setBackground(new Color(255, 253, 208));
        viewButton.setForeground(Color.BLACK);
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(viewButton, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String comment = commentArea.getText();
                if (id.isEmpty() || comment.isEmpty()) {
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Employee ID and Comment must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    fileHandler.addCommentsToFile(id, comment);
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Comment added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    commentArea.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Error saving comment", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Employee ID must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String comments = fileHandler.viewComments(id);
                    viewCommentsArea.setText(comments);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Error loading comments", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}