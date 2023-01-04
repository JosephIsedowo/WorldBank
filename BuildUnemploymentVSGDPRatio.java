package model.Analysis;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import Calculations.AnnualChangeCalculation;
import Calculations.Calculate;
import Calculations.CalculationInvoker;
import Calculations.RatioCalculation;
//Builder for unemployment vs GDP
public class BuildUnemploymentVSGDPRatio extends AnalysisBuilder {
	//pass data to the analysis object if one
	public void setDataSet(ArrayList<JsonObject> result) {
		if(result.size() == 2) {
			CalculationInvoker i = new CalculationInvoker();
			Calculate r = new Calculate();
			RatioCalculation calcul = new RatioCalculation(r);
			calcul.setData(result.get(0), result.get(1));
			i.setCommand(calcul);
			ArrayList<JsonObject> res = new ArrayList<JsonObject>();
			res.add(i.calculate().getData());
			analysis.setDataSet(res);
			this.oneDataSet(res.get(0));
		}
		
	}
	//passes two sets of data to the analysis object for processing
	public void oneDataSet(JsonObject result) {
		analysis.oneDataSet(result);		
	}
}
