package Viewer;

import javax.swing.JPanel;

import Graphs.Bar;
import Graphs.GraphDeployment;
import Graphs.Line;
import Graphs.Pie;
import Graphs.Scatter;
import Graphs.Timeseries;
import Graphs.ViewController;
import model.Analysis.Analysis;

public class AddViewer implements AddRemoveState{
	private JPanel west;
	private Analysis data;
public AddViewer(JPanel west, Analysis data) {
	this.west = west;
	this.data = data;
}
	@Override
	public void performState() {
		// TODO Auto-generated method stub
		MainUI mainUI = MainUI.getInstance();
		String analysisMethod = mainUI.getAnalysisMethod().get(mainUI.getmethodList().getSelectedIndex());
	    String choice = mainUI.getViewList().getSelectedItem().toString();
	    if(choice == "Pie Chart") {
	        Integer start = Integer.valueOf(mainUI.getYears().get(mainUI.fromList().getSelectedIndex()));
            Integer end = Integer.valueOf(mainUI.getYears().get(mainUI.toList().getSelectedIndex()));
          
            Pie g = new Pie(analysisMethod, data.getResult(),start.intValue(),end.intValue());
            GraphDeployment<Pie> graph = new GraphDeployment<Pie>(g);
            graph.execute();
            ViewController.displayGraphs(west);
	    }
	    else if(choice == "Line Chart") {
	    	Integer start = Integer.valueOf(mainUI.getYears().get(mainUI.fromList().getSelectedIndex()));
	        Integer end = Integer.valueOf(mainUI.getYears().get(mainUI.toList().getSelectedIndex()));
          
            Line g = new Line(analysisMethod, data.getResult(),start.intValue(),end.intValue());
            GraphDeployment<Line> graph = new GraphDeployment<Line>(g);
            graph.execute();
            ViewController.displayGraphs(west);
	    }
	    else if(choice == "Bar Chart") {	        
	    	 Integer start = Integer.valueOf(mainUI.getYears().get(mainUI.fromList().getSelectedIndex()));
	         Integer end = Integer.valueOf(mainUI.getYears().get(mainUI.toList().getSelectedIndex()));
          
            Bar g = new Bar(analysisMethod, data.getResult(),start.intValue(),end.intValue());
            GraphDeployment<Bar> graph = new GraphDeployment<Bar>(g);
            graph.execute();
            ViewController.displayGraphs(west);
	    }
	    else if (choice == "Scatter Chart") {
	    	Integer start = Integer.valueOf(mainUI.getYears().get(mainUI.fromList().getSelectedIndex()));
	        Integer end = Integer.valueOf(mainUI.getYears().get(mainUI.toList().getSelectedIndex()));
          
            Scatter g = new Scatter(analysisMethod, data.getResult(),start.intValue(),end.intValue());
            GraphDeployment<Scatter> graph = new GraphDeployment<Scatter>(g);
            graph.execute();
            ViewController.displayGraphs(west);
	    }
	    else if (choice == "timeseries chart") {
	    	Integer start = Integer.valueOf(mainUI.getYears().get(mainUI.fromList().getSelectedIndex()));
	        Integer end = Integer.valueOf(mainUI.getYears().get(mainUI.toList().getSelectedIndex()));
          
            Timeseries g = new Timeseries(analysisMethod, data.getResult(),start.intValue(),end.intValue());
            GraphDeployment<Timeseries> graph = new GraphDeployment<Timeseries>(g);
            graph.execute();
            ViewController.displayGraphs(west);
	    }
	    else if (choice == "Report") {
	        ViewController.displayReport(west);
	    }
	    else {
	        System.out.println("no more");
	    }
	}
	
}
