package model.Analysis;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public abstract class AnalysisBuilder {
	 // Initialize analysis
	protected Analysis analysis;
	//to get the analysis 
	public Analysis getAnalysis() {
		return this.analysis;
	}
	//to create a analysis
	public void createNewAnalysis() {
		analysis = new Analysis();
	}	
	//to set the analysis 
	public abstract void setDataSet(ArrayList<JsonObject> result);
}
