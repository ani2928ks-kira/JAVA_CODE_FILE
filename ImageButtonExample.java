package Swings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageButtonExample {

    JLabel messageLabel;

    ImageButtonExample() {

        JFrame frame = new JFrame("Image Button Example");

        // Label to display message
        messageLabel = new JLabel("Click a button", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        messageLabel.setBounds(50, 20, 300, 30);

        // Define button dimensions
        int btnWidth = 250;
        int btnHeight = 250;

        // Load and Scale Digital Clock Image
        ImageIcon digitalIconRaw = new ImageIcon("digital_clock.png");
        Image digitalImg = digitalIconRaw.getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        ImageIcon digitalIcon = new ImageIcon(digitalImg);

        // Load and Scale Hourglass (Bogen Clock) Image
        ImageIcon hourglassIconRaw = new ImageIcon("hourglass.png");
        Image hourglassImg = hourglassIconRaw.getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        ImageIcon hourglassIcon = new ImageIcon(hourglassImg);

        // Buttons with scaled images
        JButton digitalBtn = new JButton(digitalIcon);
        JButton hourglassBtn = new JButton(hourglassIcon);

        digitalBtn.setBounds(50, 80, btnWidth, btnHeight);
        hourglassBtn.setBounds(350, 80, btnWidth, btnHeight);

        // Optional: Remove button borders/margins for a cleaner look
        digitalBtn.setMargin(new Insets(0, 0, 0, 0));
        hourglassBtn.setMargin(new Insets(0, 0, 0, 0));

        // Event handling
        digitalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Digital Clock is pressed");
            }
        });

        hourglassBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Hour Glass is pressed");
            }
        });

        // Add components
        frame.add(messageLabel);
        frame.add(digitalBtn);
        frame.add(hourglassBtn);

        frame.setSize(700, 500); // Adjusted size for better framing
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ImageButtonExample();
    }
}