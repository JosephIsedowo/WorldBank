package model.system;

import java.io.IOException;

import com.google.gson.JsonObject;

import model.fetcher.CountryNotAllowedException;
import model.fetcher.DateErrorException;

public abstract class Systems {
	//object creation for Systems
	public Systems(){
		super();
		}
	//to validate the data can be fetched from the API
	public abstract boolean check(String country, String Indicator, String startY, String endY) throws CountryNotAllowedException, IOException, DateErrorException;
	//returns a json object containing the data requested from the user
	public abstract JsonObject grab(String country, String Indicator, String startY, String endY) throws CountryNotAllowedException, DateErrorException;
}
