package Calculations;

import com.google.gson.JsonObject;

// Concrete Command (Annual Change)
public class AnnualChangeCalculation implements CalculationInterface {

	private Calculate calculate;
	
	JsonObject input;
	
	public AnnualChangeCalculation(Calculate calculate) {
		super();
		this.calculate = calculate;
	}
	
	public Result calculate() {
		return calculate.AnnualChange(input);
	}
	
	public void setData(JsonObject data) {
		this.input = data;
	}
}
