package model.system;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import model.fetcher.CSVLoader;
import model.fetcher.CSVToolsFacade;
import model.fetcher.CountryNotAllowedException;
import model.fetcher.DateErrorException;

public class DataBase extends Systems {
	 // Initialize finalResult, excludedCountries, countries, countries, and dB 
	private JsonObject finalResult;
	private ArrayList<String> excludedCountries;
	private Map<String, String> countries;
	private static DataBase dB; // created a static variable dataBase.
	
	//DataBase object creation
	private DataBase() { 
		super();
		this.excludedCountries = new ArrayList<String>();
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) { 
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		countries.put("United States of America", "USA");
	}
	//if database is not created this method would create a new one
	public static DataBase getDataBase() {
		if (DataBase.dB == null) {
			DataBase.dB = new DataBase();
		}
		return dB;
	}
	
	//to validate the data can be fetched from the API
	public boolean check(String country, String indicator, String s, String e)  {
		Boolean checker = false;
		if(this.checkCountry(country, indicator, s, e) == true) {
			return checker;
		}if (this.checkDate(s,e) == true) {
			return checker;
		}
		
			for(Entry<String, String> entry: countries.entrySet()) {
				try {
				if(entry.getValue().equals(country)) { 
					if(this.grab(entry.getValue(), indicator,s,e)!= null) {
						checker = true;
						return checker;
						}
					}
				}
				catch(CountryNotAllowedException err){
		    		JPanel panel = new JPanel();
		    		this.addcountriesNotAllowed(entry.getKey());
		    		JOptionPane.showMessageDialog(panel, country + " does not exist in the api", "Error", JOptionPane.ERROR_MESSAGE);
		    		err.printStackTrace();
			    }
				catch(DateErrorException err){
		    		JPanel panel = new JPanel();
		    		JOptionPane.showMessageDialog(panel, err, "Error", JOptionPane.ERROR_MESSAGE);
		    		err.printStackTrace();
			    }
			}
			return checker;
		
	}	
			
	
	public boolean checkCountry(String country,String indicator, String s,String e) {
		Boolean checker = false;
		try {
		for(Entry<String, String> entry: countries.entrySet()) {
			if(entry.getValue().equals(country)) { 
				if(CSVToolsFacade.searchCountryCSV(entry.getKey()) == true) {
					JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "country is blocked from search", "Error", JOptionPane.ERROR_MESSAGE);
					checker = true;
					return checker;
					}
				}
			}
		}
		catch(IOException err) {
			err.printStackTrace();;
		}
		
		catch(HeadlessException err) {
			err.printStackTrace();;
		}
		return checker;
	}
	public boolean checkDate(String s, String e) {
		Integer start = Integer.parseInt(s);
		Integer end = Integer.parseInt(e);
		Boolean checker = false;
		// check date
		if(start > end) {
			checker = true;
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, s + " is greater than " + e, "Error", JOptionPane.ERROR_MESSAGE);
			return checker;
		}
	
		return checker;
		
	}
	//add country to the list of excluded countries
	public void addcountriesNotAllowed(String input) {
		this.excludedCountries.add(input);
	}
	//remove country from the list of excluded countries
	public boolean removecountriesNotAllowed(String input) {
		if(this.excludedCountries.remove(input) == true) {
			this.excludedCountries.remove(input);
			return true;
		}
		else {
			return false;
		}
	}
	//returns a json object containing the data requested from the user
	public JsonObject grab(String country,String Indicator, String string, String string2) throws CountryNotAllowedException, DateErrorException {
		ArrayList<String> noValue = new ArrayList<>();
		String urlString = String.format(
				"https://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json",country,Indicator,string,string2);
		String inline = "";
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
			}
			try {
				JsonObject r = new JsonObject();
				finalResult = new JsonObject();
				
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();// the get(1) will throw and error if it can not fetch and will proceed if it can fetch the index.
				JsonElement date;
				JsonElement stat;
				for (int i = 0; i < sizeOfResults; i++) {
					//play around with this to decide on what you want to fetch from the api
					date = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date");
					stat = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value");
					r.add(date.getAsString(), stat);
					if(stat.isJsonNull()) {
						noValue.add(date.toString());
					}
				}
				this.finalResult = r;
			}
			catch(IllegalStateException e) {
				throw new CountryNotAllowedException("");
			}
		}
		 catch (IOException e) {
			 System.out.println(e);
			}
		if(noValue.size() >= 1) {
			throw new DateErrorException(noValue);
		}
		return this.finalResult;
	}
	//to get the array of countries the user wants to exclude from the program
	public ArrayList<String> getcountriesNotAllowed() {
		return this.excludedCountries;
	}
}
	

