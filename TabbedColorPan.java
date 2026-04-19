package Swings;

import java.awt.Color;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedColorPan extends JFrame {
    private JTabbedPane tabbedPane;
    private JLabel statusLabel;
    
    public TabbedColorPan() {
        // Set up the frame
        this.setTitle("Tabbed Color Pane");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Create the tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Create panels for each color
        JPanel cyanPanel = new JPanel();
        cyanPanel.setBackground(Color.CYAN);
        JLabel cyanLabel = new JLabel("This is the Cyan Tab");
        cyanLabel.setForeground(Color.BLACK);
        cyanPanel.add(cyanLabel);
        
        JPanel magentaPanel = new JPanel();
        magentaPanel.setBackground(Color.MAGENTA);
        JLabel magentaLabel = new JLabel("This is the Magenta Tab");
        magentaLabel.setForeground(Color.WHITE);
        magentaPanel.add(magentaLabel);
        
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        JLabel yellowLabel = new JLabel("This is the Yellow Tab");
        yellowLabel.setForeground(Color.BLACK);
        yellowPanel.add(yellowLabel);
        
        // Add panels to the tabbed pane with appropriate titles
        tabbedPane.addTab("Cyan", cyanPanel);
        tabbedPane.addTab("Magenta", magentaPanel);
        tabbedPane.addTab("Yellow", yellowPanel);
        
        // Create status label to display selected color
        statusLabel = new JLabel("Select a tab to display the color", SwingConstants.CENTER);
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setFont(statusLabel.getFont().deriveFont(14.0f));
        
        // Add change listener to detect tab selection
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                String selectedTab = tabbedPane.getTitleAt(selectedIndex);
                
                // Display the selected color on console
                System.out.println("Selected color: " + selectedTab);
                
                // Also display in the status label
                statusLabel.setText("Selected color: " + selectedTab);
                
                // Change status label background based on selected color
                switch (selectedTab) {
                    case "Cyan":
                        statusLabel.setBackground(Color.CYAN);
                        statusLabel.setForeground(Color.BLACK);
                        break;
                    case "Magenta":
                        statusLabel.setBackground(Color.MAGENTA);
                        statusLabel.setForeground(Color.WHITE);
                        break;
                    case "Yellow":
                        statusLabel.setBackground(Color.YELLOW);
                        statusLabel.setForeground(Color.BLACK);
                        break;
                }
            }
        });
        
        // Add components to the frame
        this.add(tabbedPane, java.awt.BorderLayout.CENTER);
        this.add(statusLabel, java.awt.BorderLayout.SOUTH);
        
        // Make the frame visible
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TabbedColorPan();
            }
        });
    }
}