package Calculations;

import com.google.gson.JsonObject;

// Result class
public class Result<T> {
	private JsonObject data;
	
	public void setData(JsonObject data) {
		this.data = data;
	}
	
	public JsonObject getData() {
		return data;
	}
}
