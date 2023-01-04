package model.Analysis;

import java.util.ArrayList;

import com.google.gson.JsonObject;
//Builder for mortality vs expenses
public class BuildMortalityVsExpenses extends AnalysisBuilder {
	//pass data to the analysis object if size of array is two
	public void setDataSet(ArrayList<JsonObject> result) {
		if(result.size() == 2) {
			analysis.setDataSet(result);
			this.twoDataSet(result.get(0),result.get(1));
		}
		
	}
	//passes two sets of data to the analysis object for processing
	public void twoDataSet(JsonObject result,JsonObject result2) {
		analysis.twoDataSet(result,result2);		
	}

}
