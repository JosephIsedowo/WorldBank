package model.Analysis;

import java.util.ArrayList;

import com.google.gson.JsonObject;

public class AnalysisDistributor {
	 // Initialize analysisBuilder
	private AnalysisBuilder analysisBuilder;
	
	//you can modify the data passed to each builder 
	public void setAnalysisBuilder(AnalysisBuilder ab) {
		analysisBuilder = ab;
	}
	//to get the analysis object
	public Analysis getAnalysis() {
		return analysisBuilder.getAnalysis();
	}
	//to construct the analysis using the number of sets of data in the given array
	public void constructAnalysis(int value,ArrayList<JsonObject> result) {
		analysisBuilder.createNewAnalysis();
        switch (value) {
            case 1:  analysisBuilder.setDataSet(result);
                     break;
            case 2:  analysisBuilder.setDataSet(result);;
                     break;
            case 3:  analysisBuilder.setDataSet(result);;
                     break;
            default: System.out.println("no analysis for: " + value);
            break;
        }
	}
}
