package Calculations;

import com.google.gson.JsonObject;

// Command Interface
public interface CalculationInterface {
	public Result<JsonObject> calculate(); // Execute
}
