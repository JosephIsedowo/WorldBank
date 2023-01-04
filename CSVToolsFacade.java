package model.fetcher;

import java.io.IOException;

//CSV facade
public class CSVToolsFacade {
	private static CredentialsCSVLoader cred = new CredentialsCSVLoader();
	private static CSVLoader countryCSV = new CSVLoader(); 
	

	public static boolean searchCountryCSV(String country) throws IOException {
	       countryCSV.load();
	       return countryCSV.Search(country);
	     
	    }
	public static void updateCSV() throws Exception {
	       countryCSV.load();
	       countryCSV.update();    
	     }
	public static boolean searchCredCSV(String user,String pass) throws IOException {
		cred.load();
		return cred.Search(user, pass);
	}
}
