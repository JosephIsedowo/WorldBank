package model.Analysis;

import java.util.ArrayList;

import com.google.gson.JsonObject;
//Builder for mortality VS expenses VS hospital beds
public class BuildMortalityVSExpensesVSHospitalbeds extends AnalysisBuilder {
	//pass data to the analysis object if size of array is three
	public void setDataSet(ArrayList<JsonObject> result) {
		if(result.size() == 3) {
			analysis.setDataSet(result);
			this.threeDataSet(result.get(0),result.get(1),result.get(2));
		}
		
	}
	//passes three sets of data to the analysis object for processing
	public void threeDataSet(JsonObject result,JsonObject result2,JsonObject result3) {
		analysis.threeDataSet(result, result2, result3);
		

		
	}
}
