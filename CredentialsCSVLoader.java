package model.fetcher;

import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.csvreader.CsvReader;
//import com.csvreader.CsvWriter;

import au.com.bytecode.opencsv.CSVReader;
//import model.system.DataBase;

public class CredentialsCSVLoader {
	
	private String fileName;
	private Boolean userFlag = false, passFlag = false;
//	private DataBase dB;
	
	public CredentialsCSVLoader() {
		super();
//		this.dB = DataFetcher.getDataBase();
	}
	
	// Read the the file 
	public void load() throws IOException {
		String home = System.getProperty("user.home");
		this.fileName = home + "\\Downloads\\Deliverable1\\Deliverable1\\src\\Credentials.csv";
		CsvReader reader = new CsvReader(new FileReader(fileName)); 
		reader.readHeaders();
	}
	
	// Search for a country in the csv file
	public Boolean Search(String username, String password) throws FileNotFoundException {
		
		String home = System.getProperty("user.home");
		this.fileName = home + "\\Downloads\\Deliverable1\\Deliverable1\\src\\Credentials.csv";
		CSVReader reader = new CSVReader(new FileReader(fileName));
		
		try {
		
		  String [] nextLine;

		  System.out.println("Username: " + username);
		  System.out.println("Password: " + password + "\n");
		  
		  //Read one line at a time
		  while ((nextLine = reader.readNext()) != null) {
		    //Use the tokens as required
		    if (Arrays.toString(nextLine).contains(username) && Arrays.toString(nextLine).contains(password)) {
		    	
		    	System.out.println("Both Username ("
		    			+ username +") and Password (" +
		    			password + ") exist in Credentials.csv file and correct combination");
		    	
		    	System.out.println("Successful Login!\n");
		    	
//		    	System.out.println("Username: " + username + " exist in Credentials.csv file");
//		    	System.out.println("Password: " + password + " exist in Credentials.csv file");
		    	return true;
		    }
		    else if (Arrays.toString(nextLine).contains(username) && !Arrays.toString(nextLine).contains(password) && !userFlag) {
		    	System.out.println("Username: " + username + " exist in Credentials");
		    	userFlag = true;
		    }
		    else if (Arrays.toString(nextLine).contains(password) && !Arrays.toString(nextLine).contains(username) && !passFlag) {
		    	System.out.println("Password: " + password + " exist in Credentials.csv file");
		    	passFlag = true;
		    }
		  }
		}
		catch (IOException  e) {
		  e.printStackTrace();
		}
		
		if (userFlag && passFlag) {
			System.out.println("Both Username ("
	    			+ username +") and Password (" +
	    			password + ") exist in Credentials.csv file but are incorrect combination");
			System.out.println("Fail Login!\n");
			return false;
		}
		else if (userFlag && !passFlag) {
			System.out.println("Username (" + username + ") exist in Credentials.csv file but Password (" +
								password + " does not exist in Credentials.csv file");
			System.out.println("Fail Login!\n");
			return false;
		}
		else if (!userFlag && passFlag) {
			System.out.println("Username (" + username + ") does not exist in Credentials.csv file "
					+ "but Password (" + password + ") exist in Credentials.csv file");
			System.out.println("Fail Login!\n");
			return false;
		}
		else {
			System.out.println("Both Username (" + username +") and Password (" +
	    			password + ") does not exist in Credentials.csv file");
			System.out.println("Fail Login!\n");
			return false;
		}
		
//		return false;
	}
	
}
