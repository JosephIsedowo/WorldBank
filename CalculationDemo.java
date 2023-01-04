package Calculations;

import com.google.gson.JsonObject;

// Take and execute commands
public class CalculationDemo {

	private static CalculationInvoker i = new CalculationInvoker();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculate r = new Calculate();
		
		
		// Test ratio calculation
		i.setCommand(new RatioCalculation(r));
		System.out.println("Result: \n" + i.calculate().getData().toString());
		
		// Test annual change calculation
		i.setCommand(new AnnualChangeCalculation(r));
		System.out.println("Result: \n" + i.calculate().getData().toString());
	}

}
