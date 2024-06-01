import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ReviewFeedbackFrame extends JFrame {
    private JTextField idField;
    private JTextArea commentArea;
    private FileHandler fileHandler;

    public ReviewFeedbackFrame() {
        fileHandler = new FileHandler();

        setTitle("Review & Feedback");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        JPanel idPanel = new JPanel(new BorderLayout());
        idPanel.add(new JLabel("Employee ID:"), BorderLayout.WEST);
        idField = new JTextField();
        idPanel.add(idField, BorderLayout.CENTER);
        topPanel.add(idPanel);

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit Comment");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String comments = commentArea.getText();
                if (!id.isEmpty() && !comments.isEmpty()) {
                    try {
                        fileHandler.addCommentsToFile(id, comments);
                        commentArea.setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Error saving comments", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "ID and Comment must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(submitButton);

        JButton viewCommentsButton = new JButton("View Comments");
        viewCommentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (!id.isEmpty()) {
                    try {
                        String comments = fileHandler.viewComments(id);
                        JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, comments, "Comments for ID: " + id, JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "Error loading comments", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ReviewFeedbackFrame.this, "ID must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(viewCommentsButton);

        topPanel.add(buttonPanel);
        add(topPanel, BorderLayout.NORTH);

        commentArea = new JTextArea();
        commentArea.setBorder(BorderFactory.createTitledBorder("Comments"));
        add(new JScrollPane(commentArea), BorderLayout.CENTER);

        setVisible(true);
    }
}