package Swings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseOperationDemo extends JFrame implements MouseListener {

    private JButton actionButton;
    private JLabel statusLabel;

    public MouseOperationDemo() {
        // Basic Frame Setup
        setTitle("Mouse Operations Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Instructions Label
        JLabel instructionLabel = new JLabel("Interact with the button below:", JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        add(instructionLabel, BorderLayout.NORTH);

        // The Button we will monitor
        actionButton = new JButton("Mouse Target");
        actionButton.setFocusable(false); // Keeps the UI clean
        actionButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        // IMPORTANT: Attach the MouseListener to the button
        actionButton.addMouseListener(this);
        
        add(actionButton, BorderLayout.CENTER);

        // Status Label to display what happened
        statusLabel = new JLabel("Waiting for mouse action...", JLabel.CENTER);
        statusLabel.setPreferredSize(new Dimension(400, 50));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // --- MouseListener Implementation Methods ---

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickCount = e.getClickCount();
        if (clickCount == 2) {
            statusLabel.setText("Result: Double Click detected!");
            statusLabel.setBackground(Color.CYAN);
        } else {
            statusLabel.setText("Result: Single Click detected.");
            statusLabel.setBackground(Color.WHITE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        statusLabel.setText("Action: Mouse Down (Pressed)");
        statusLabel.setBackground(Color.ORANGE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        statusLabel.setText("Action: Mouse Up (Released)");
        statusLabel.setBackground(Color.YELLOW);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        actionButton.setBackground(Color.GREEN);
        statusLabel.setText("Mouse entered the button area.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        actionButton.setBackground(null); // Reset color
        statusLabel.setText("Mouse left the button area.");
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new MouseOperationDemo());
    }
}