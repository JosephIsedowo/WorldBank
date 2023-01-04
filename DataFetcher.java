package model.fetcher;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.JsonObject;


import model.system.DataBase;
import model.system.Systems;

public class DataFetcher {
	 // Initialize sy
	private static Systems sy;
	
	//object creation for DataFetcher
	public DataFetcher() {
		super();
		sy = DataBase.getDataBase();
	}
	//to query the api for information
	public JsonObject selection(String country, String Indicator, String startY, String endY) {
		JsonObject result = null;
		try {
			if(sy.check(country,Indicator,startY,endY) == true) {
				System.out.println(country + " is fetchable");
				result = sy.grab(country, Indicator, startY, endY);
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CountryNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DateErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//to fetch the DataBase that belongs to DataFetcher 
	public static DataBase getDataBase() {
		return (DataBase)sy;
		
	}
}

