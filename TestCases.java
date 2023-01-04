package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import com.google.gson.JsonObject;

import model.Analysis.Analysis;
import model.Analysis.AnalysisBuilder;
import model.Analysis.AnalysisDistributor;
import model.Analysis.BuildMortality;
import model.Analysis.BuildMortalityVSExpensesVSHospitalbeds;
import model.Analysis.BuildMortalityVsExpenses;
import model.Analysis.BuildUnemploymentVSGDPRatio;
import model.fetcher.CSVLoader;
import model.fetcher.CountryNotAllowedException;
import model.fetcher.DataFetcher;
public class TestCases {
	//UC2:
	//querying database with correct country name,indicator,starting year and end year combination
	@Test
	public void fetchCountry() throws IOException, CountryNotAllowedException {
		Map<String, String> countries;
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }    
		DataFetcher dF = new DataFetcher();
		JsonObject result = dF.selection(countries.get("Germany"),"SP.DYN.IMRT.IN","2010","2011");
		assertEquals("{\"2011\":3.4,\"2010\":3.5}",result.toString());
	}
	//remove country from the existing list of countries 
	@Test
	public void removeCountry() throws IOException, CountryNotAllowedException {
		DataFetcher dF = new DataFetcher();
		CSVLoader csv = new CSVLoader();
		DataFetcher.getDataBase().addcountriesNotAllowed("France");
		try {
			csv.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean result = csv.Search("France");
		assertTrue(result);
		
	}
	//add country to the existing list of countries 
	@Test
	public void addCountry() throws IOException, CountryNotAllowedException {
		DataFetcher dF = new DataFetcher();
		CSVLoader csv = new CSVLoader();
		DataFetcher.getDataBase().removecountriesNotAllowed("Jamaica");
		try {
			csv.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean result = csv.Search("Jamaica");
		assertFalse(result);
	}
	//sets thrown to true if country in the api does not exist
	@Test
	public void fetchCountrNotInAPI() throws IOException, CountryNotAllowedException {
		Map<String, String> countries;
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		countries.put("USA", "USA");
		DataFetcher dF = new DataFetcher();
		Boolean thrown = false;
		 dF.selection(countries.get("Taiwan"),"SP.DYN.IMRT.IN","2010","2011");
		 assertTrue(thrown);
	}
	//UC3:
	//Fetch jsonObject containing one set of data using the type analysis mortality
	@Test
	public void fetchMortality() throws IOException, CountryNotAllowedException {
		Map<String, String> countries;
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		 JsonObject r1;
		 ArrayList<JsonObject> arrayOfResults = new ArrayList<JsonObject>();
		DataFetcher dF = new DataFetcher();
		r1 = dF.selection(countries.get("Ghana"),"SP.DYN.IMRT.IN","2010", "2011");
    	arrayOfResults.add(r1);
    	AnalysisBuilder Mortality = new BuildMortality();
    	AnalysisDistributor dis = new AnalysisDistributor();
    	dis.setAnalysisBuilder(Mortality);
    	dis.constructAnalysis(1,arrayOfResults);
    	Analysis analysisResult = dis.getAnalysis();		
    	assertEquals("{\"result1 \":{\"2011\":45.2,\"2010\":47}}",analysisResult.getResult().toString());
	}
	//Fetch jsonObject containing 2 sets of data using the type analysis mortality VS expenses
	@Test
	public void fetchMortalityVsExpenses() throws IOException, CountryNotAllowedException {
		Map<String, String> countries;
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		 JsonObject r1;
		 JsonObject r2;
		 ArrayList<JsonObject> arrayOfResults = new ArrayList<JsonObject>();
		DataFetcher dF = new DataFetcher();
		r1 = dF.selection(countries.get("Ghana"),"SP.DYN.IMRT.IN","2010", "2011");
		r2 = dF.selection(countries.get("Ghana"),"SH.XPD.CHEX.GD.ZS","2010", "2011");
    	arrayOfResults.add(r1);
    	arrayOfResults.add(r2);
    	AnalysisBuilder MortalityVsExpenses = new BuildMortalityVsExpenses();
    	AnalysisDistributor dis = new AnalysisDistributor();
    	dis.setAnalysisBuilder(MortalityVsExpenses);
    	dis.constructAnalysis(2,arrayOfResults);
    	Analysis analysisResult = dis.getAnalysis();		
    	assertEquals("{\"result1 \":{\"2011\":45.2,\"2010\":47},\"result2 \":{\"2011\":4.71008253,\"2010\":4.25117254}}",analysisResult.getResult().toString());
	}
	//Fetch data containing 3 sets of data using the type analysis Expenses VS hospital beds
	@Test
	public void fetchMortalityVsExpensesVsHospitalBeds() throws IOException, CountryNotAllowedException {
		Map<String, String> countries;
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		 JsonObject r1;
		 JsonObject r2;
		 JsonObject r3;
		 ArrayList<JsonObject> arrayOfResults = new ArrayList<JsonObject>();
		DataFetcher dF = new DataFetcher();
		r1 = dF.selection(countries.get("Ghana"),"SP.DYN.IMRT.IN","2010", "2011");
		r2 = dF.selection(countries.get("Ghana"),"SH.XPD.CHEX.GD.ZS","2010", "2011");
		r3 = dF.selection(countries.get("Ghana"),"SH.MED.BEDS.ZS","2010", "2011");
    	arrayOfResults.add(r1);
    	arrayOfResults.add(r2);
    	arrayOfResults.add(r3);
    	AnalysisBuilder MEH = new BuildMortalityVSExpensesVSHospitalbeds();
    	AnalysisDistributor dis = new AnalysisDistributor();
    	dis.setAnalysisBuilder(MEH);
    	dis.constructAnalysis(3,arrayOfResults);
    	Analysis analysisResult = dis.getAnalysis();		
    	assertEquals("{\"result1 \":{\"2011\":45.2,\"2010\":47},"
    				+ "\"result2 \":{\"2011\":4.71008253,\"2010\":4.25117254},"
    				+ "\"result3 \":{\"2011\":0.9,\"2010\":null}}",
    				analysisResult.getResult().toString());
	}
	//fetch data containing 2 sets of data using the type analysis unemployment VS GDP
	@Test
	public void fetchUnemploymentVSGDP() throws IOException, CountryNotAllowedException {
		Map<String, String> countries;
		countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {
			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(), iso);
	     }     
		 JsonObject r1;
		 JsonObject r2;
		 ArrayList<JsonObject> arrayOfResults = new ArrayList<JsonObject>();
		DataFetcher dF = new DataFetcher();
		r1 = dF.selection(countries.get("Ghana"),"SL.UEM.TOTL.ZS","2010", "2011");
		r2 = dF.selection(countries.get("Ghana"),"NY.GDP.PCAP.CD","2010", "2011");
    	arrayOfResults.add(r1);
    	arrayOfResults.add(r2);
    	AnalysisBuilder uVsGSP = new BuildUnemploymentVSGDPRatio();
    	AnalysisDistributor dis = new AnalysisDistributor();
    	dis.setAnalysisBuilder(uVsGSP);
    	dis.constructAnalysis(2,arrayOfResults);
    	Analysis analysisResult = dis.getAnalysis();		
    	assertEquals("{\"result1 \":{\"2011\":5.59600019454956,\"2010\":5.38000011444092},"
    				+ "\"result2 \":{\"2011\":1549.46271883345,\"2010\":1299.34521164062}}",
    				analysisResult.getResult().toString());
	}
	
}
