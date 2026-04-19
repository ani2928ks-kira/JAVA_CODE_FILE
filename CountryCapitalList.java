package Swings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CountryCapitalList extends JFrame {
    private JList<String> countryList;
    private Map<String, String> countryCapitalMap;
    
    public CountryCapitalList() {
        // Initialize the map with countries and their capitals
        initializeCountryCapitalMap();
        
        // Create the model and add countries
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");
        listModel.addElement("Africa");
        listModel.addElement("Greenland");
        listModel.addElement("Singapore");
        
        // Create the list
        countryList = new JList<>(listModel);
        
        // Add selection listener
        countryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    List<String> selectedCountries = countryList.getSelectedValuesList();
                    for (String country : selectedCountries) {
                        String capital = countryCapitalMap.get(country);
                        if (capital != null) {
                            System.out.println("Capital of " + country + " is: " + capital);
                        }
                    }
                }
            }
        });
        
        // Add scroll pane to the frame
        add(new JScrollPane(countryList));
        
        // Frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Country Capital Selector");
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void initializeCountryCapitalMap() {
        countryCapitalMap = new HashMap<>();
        countryCapitalMap.put("USA", "Washington D.C.");
        countryCapitalMap.put("India", "New Delhi");
        countryCapitalMap.put("Vietnam", "Hanoi");
        countryCapitalMap.put("Canada", "Ottawa");
        countryCapitalMap.put("Denmark", "Copenhagen");
        countryCapitalMap.put("France", "Paris");
        countryCapitalMap.put("Great Britain", "London");
        countryCapitalMap.put("Japan", "Tokyo");
        countryCapitalMap.put("Africa", "Addis Ababa");
        countryCapitalMap.put("Greenland", "Nuuk");
        countryCapitalMap.put("Singapore", "Singapore");
    }
    
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CountryCapitalList();
            }
        });
    }
}