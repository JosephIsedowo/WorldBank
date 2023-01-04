package model.Analysis;

import java.util.ArrayList;
import Calculations.*;

import com.google.gson.JsonObject;
//Builder for unemployment 
public class BuildUnemploymentAnnualChange extends AnalysisBuilder {
	//pass data to the analysis object if size of array is one
	public void setDataSet(ArrayList<JsonObject> result) {
		if(result.size() == 1) {
			CalculationInvoker i = new CalculationInvoker();
			
			Calculate r = new Calculate();
			AnnualChangeCalculation calcul = new AnnualChangeCalculation(r);
			calcul.setData(result.get(0));
			i.setCommand(calcul);
			ArrayList<JsonObject> res = new ArrayList<JsonObject>();
			res.add(i.calculate().getData());
			analysis.setDataSet(res);
			this.oneDataSet(res.get(0));
			System.out.println("Result: \n" + res.get(0));
		}
		
	}
	//passes one sets of data to the analysis object for processing
	public void oneDataSet(JsonObject result) {
		analysis.oneDataSet(result);		
	}

}
