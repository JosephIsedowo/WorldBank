package model.Analysis;


import java.util.ArrayList;

import com.google.gson.JsonObject;
//Builder for mortality
public class BuildMortality extends AnalysisBuilder {

	//pass data to the analysis object if size of array is one
	public void setDataSet(ArrayList<JsonObject> result) {
		if(result.size() == 1) {
			analysis.setDataSet(result);
			this.oneDataSet(result.get(0));
		}
		
	}
	//passes one set of data to the analysis object for processing
	public void oneDataSet(JsonObject result) {
		analysis.oneDataSet(result);

		
	}



}
