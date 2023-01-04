package Viewer;

import javax.swing.*;  
import java.awt.*;  
  
//create NewPage class to create a new page on which user will navigate  
class ErrorPopup extends JFrame  
{  
	JButton ok = new JButton("Ok");
	JLabel error_label;
	JPanel errorPanel;
	
    // Constructor  
    ErrorPopup()
    {  
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);  
        setTitle("ERROR");  
        setSize(400, 200);
        
        errorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        Font font = new Font("Courier", Font.BOLD,12);
        
        // Create a error label and set it to the new page  
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        error_label = new JLabel("Error: ");
        error_label.setFont(font);
        errorPanel.add(error_label, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        JLabel error_info = new JLabel("Provided Credentials does not exist or is incorrect.");
        errorPanel.add(error_info, gbc);
        
        add(errorPanel, BorderLayout.CENTER);
    }  
}