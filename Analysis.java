package model.Analysis;
//import required classes and packages
import java.util.ArrayList;
import com.google.gson.JsonObject;

public class Analysis {
	 // Initialize oneview,twoview,threeview 
	private JsonObject oneview;
	private JsonObject twoview;
	private JsonObject threeview;
	private int analysis;
	//constructor for Analysis
	public Analysis() {
		super();
	}
	//assign the # of sets of data in the array to analysis
	public void setDataSet(ArrayList<JsonObject> result) {
		if(result.size() == 1) {
			analysis = 1;
		}
		else if(result.size() == 2) {
			analysis = 2;
		}
		else if(result.size() == 3) {
			analysis = 3;
		}
	}	
	//if one then add 1 sets of data to jsonObject
	public void oneDataSet(JsonObject result) {
		JsonObject jO = new JsonObject();
		jO.add("result1", result);
		this.oneview = jO;
	}
	//if two then add 2 sets of data to jsonObject
	public void twoDataSet(JsonObject result1, JsonObject result2) {
		JsonObject jO = new JsonObject();
		jO.add("result1", result1);
		jO.add("result2", result2);
		this.twoview = jO;
	}
	//if three then add 3 sets of data to jsonObject
	public void threeDataSet(JsonObject result1, JsonObject result2, JsonObject result3) {
		JsonObject jO = new JsonObject();
		jO.add("result1", result1);
		jO.add("result2", result2);
		jO.add("result3", result3);
		this.threeview = jO;
		
	}
	//to get the data based on the # of sets of data assigned to analysis 
	public JsonObject getResult() {
		if(analysis == 1) {
			return this.oneview;
		}
		else if(analysis == 2) {
			return this.twoview;
		}
		else if(analysis == 3) {
			return this.threeview;
		}
		else {
			System.out.println("no other option");
			return null;
		}
	}
	
	
}
	

