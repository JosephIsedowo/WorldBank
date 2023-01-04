package Viewer;
//import required classes and packages  
import javax.swing.*;

import model.fetcher.CSVToolsFacade;
import model.fetcher.CredentialsCSVLoader;

import java.awt.*;  
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;  
   
// Class extends JFrame to create a window 
// Class implements ActionListener to perform an action on button click  
class CreateLoginForm extends JFrame implements ActionListener  
{  
    // Initialize button, panel, label, and text field  
    JButton submit;  
    JPanel newPanel;  
    JLabel userLabel, passLabel;  
    final JTextField userTextField, passTextField;  
    
   
      
    // Constructor  
    CreateLoginForm()  
    {     
          
        // Create label for username   
        userLabel = new JLabel();  
        userLabel.setText("Username: ");      // Set label value for textField1  
          
        // Create text field to get username from the user  
        userTextField = new JTextField(15);    // Set length of the text  
  
        // Create label for password  
        passLabel = new JLabel();  
        passLabel.setText("Password: ");      // Set label value for textField2  
          
        // Create text field to get password from the user  
        passTextField = new JPasswordField(15);    // Set length for the password  
          
        // Create submit button  
        submit = new JButton("Submit!"); // Set label to button  
        
        // Create page format
        newPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Create panel to put form elements  
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        newPanel.add(userLabel, gbc);    // set username label to panel  
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        newPanel.add(userTextField, gbc);   // set user text field to panel  
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        newPanel.add(passLabel, gbc);    // set password label to panel  
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        newPanel.add(passTextField, gbc);   // set password text field to panel  
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        newPanel.add(submit, gbc);           // set button to panel  

        //set border to panel   
        add(newPanel, BorderLayout.CENTER);  
        
        //perform action on button click   
        submit.addActionListener(this);     //add action listener to button
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);	// Terminate application if window is closed
        setTitle("Login");         //set title to the login form  
    }  
      
    // Define abstract method actionPerformed() which will be called on button click   
    public void actionPerformed(ActionEvent ae) { // Pass action listener as a parameter  
        
    	String userValue = userTextField.getText(); // get user entered username from the textField1  
        String passValue = passTextField.getText(); // get user entered pasword from the textField2  
          
        // Check whether the credentials are authentic or not  
        try {
			if (CSVToolsFacade.searchCredCSV(userValue, passValue)) {  // If authentic, navigate user to a new page  
				
				newPanel.setVisible(false);
				dispose();
				
				JFrame mainUI = MainUI.getInstance();
				mainUI.setSize(900, 600);
				mainUI.pack();
				mainUI.setVisible(true);
			    
			}  
			else {  
				
				// Create instance of the NewPage  
			    ErrorPopup error = new ErrorPopup();  
			    
			    // Make error page visible to the user
			    error.setVisible(true);  
			    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
}  

// Create the main class (Manual Testing/Digital Login Page & Error Page with mainUI page) 
class LoginFormDemo  {  
	
    // main() method start  
    public static void main(String arg[])  {  
        try  {
            //create instance of the CreateLoginForm  
            CreateLoginForm form = new CreateLoginForm();  
            form.setSize(250, 100);  //set size of the frame  
            form.setVisible(true);  //make form visible to the user  
            
        }
        catch(Exception e)  {     
            // handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }
    
}