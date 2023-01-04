package Calculations;

import com.google.gson.JsonObject;

// Concrete Command (Ratio)
public class RatioCalculation implements CalculationInterface {
	
	private Calculate calculate;
	
	JsonObject input1 = new Result<JsonObject>().getData();
	JsonObject input2 = new Result<JsonObject>().getData();
	
	public RatioCalculation(Calculate calculate) {
		super();
		this.calculate = calculate;
	}
	
	public Result calculate() {
		return calculate.Ratio(input1, input2);
	}
	public void setData(JsonObject data1, JsonObject data2) {
		this.input1 = data1;
		this.input2 = data2;
	}
}
