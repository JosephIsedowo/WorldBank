package model.fetcher;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;


import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import au.com.bytecode.opencsv.CSVReader;




import model.system.DataBase;

public class CSVLoader implements CsvEditor {
	 // Initialize fileName and dB 
	private String fileName;
	private DataBase dB;
	//csv constructor that gets the database from DataFetcher
	public CSVLoader() {
		super();
		this.dB = DataFetcher.getDataBase();
	}
	//to read the the file 
	public void load() throws IOException{
		String home = System.getProperty("user.home");
		this.fileName = home + "\\Downloads\\goodcopy\\Deliverable1\\src\\NotListed.csv";
		CsvReader reader = new CsvReader(new FileReader(fileName)); 
		reader.readHeaders();
	}
	//to search for a country in the csv file
	public Boolean Search(String country) throws FileNotFoundException {
		String home = System.getProperty("user.home");
		this.fileName = home + "\\Downloads\\goodcopy\\Deliverable1\\src\\NotListed.csv";
		CSVReader reader = new CSVReader(new FileReader(fileName));
		try{
		
		  String [] nextLine;

		  //Read one line at a time
		  while ((nextLine = reader.readNext()) != null)
		  {
		    //Use the tokens as required
		    if(Arrays.toString(nextLine).contains(country)) {
		    	System.out.println(country + " is in NotListed.csv file");
		    	return true;
		    }
		  }
		}
		catch (IOException  e) {
		  e.printStackTrace();
		}
		return false;
	}
	//to update the csv file with the changes made by the user
	@Override
	public void update() throws Exception {
		try {
			CsvWriter csvOutput = new CsvWriter(new FileWriter(this.fileName, false), ',');
			//country
			csvOutput.write("Unfetchable Countries");
			csvOutput.endRecord();
			
			for( String c : dB.getcountriesNotAllowed()){
				csvOutput.write(c);
				csvOutput.endRecord();
			}
			csvOutput.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	

