package Calculations;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// Request/Receiver Class
public class Calculate {
	
	private boolean calculate = false;
//	private JsonObject data;
	JsonObject jsonResult = new JsonObject();
	JsonElement value;
	double ratio, valueA, valueB;
	String currentYear, strRatio;
	
	String strAnnualChange; // currentMonth = current month's growth, previousYear = previous year growth
//	JsonElement value;
	double annualChange;
//	JsonObject result;
	int count = 0, index = 0;
	
//	public Result computation() {
//		System.out.println("Perform Calculation");
//		calculate = true;
//		Result<JsonObject> result = new Result<JsonObject>();
//		result.setData(data);
//		return result;
//	}
//	
//	public Result noComputation() {
//		System.out.println("No Calculation");
//		calculate = false;
//		Result<JsonObject> result = new Result<JsonObject>();
//		result.setData(data);
//		return result;
//	}
	
	public Result Ratio(JsonObject dataA, JsonObject dataB) {
		
		for (String key : dataA.keySet()) {
			// Retrieve dataA value
			value = dataA.get(key);
			valueA = value.getAsDouble();
			
			// Retrieve dataB value
			value = dataB.get(key);
			valueB = value.getAsDouble();
			
			ratio = valueA / valueB;
			if(ratio < 0) {
				ratio = 0;
			}
			strRatio = ratio + "";
			
			
			JsonElement strValue = new JsonParser().parse(strRatio);
			currentYear = key;
			
			jsonResult.add(currentYear, strValue);
		}
		
		Result<JsonObject> result = new Result<JsonObject>();
		result.setData(jsonResult);
		
		return result;
	}
	
	public Result AnnualChange(JsonObject data) {
		
		// Length of JsonObject data
		for (int k = 0; k<data.keySet().size(); k++) {
			count++;
		}
		
		// Array year values
		double[] yearValues = new double[count];
		// Array year labels
		String[] year = new String[count];
		
		// Receive JsonObject values
		for (String key: data.keySet()) {
			// store year values
			value = data.get(key);
			yearValues[index] = value.getAsDouble();
			
			// store year label
			year[index] = key;
			
			index++;
		}
		
		// Calculate annual change with formula and store into JsonObject result
		
		for (int i = 0; i < yearValues.length-1; i++) {

			annualChange = (yearValues[i] - yearValues[i + 1]) / yearValues[i + 1] * 100;
			if(annualChange < 0) {
				annualChange = 0;
			}
			strAnnualChange = annualChange + "";
			JsonElement strValue = new JsonParser().parse(strAnnualChange);
			jsonResult.add(year[i] + "-" + year[i + 1], strValue);
		}
		
		Result<JsonObject> result = new Result<JsonObject>();
		result.setData(jsonResult);
		
		return result;
	}
}
