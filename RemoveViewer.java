package Viewer;

import javax.swing.JPanel;

import Graphs.ViewController;

public class RemoveViewer implements AddRemoveState{
	private JPanel panel;
	public RemoveViewer(JPanel west) {
		this.panel = west;
	}
	@Override
	public void performState() {
		// TODO Auto-generated method stub
		MainUI mainUI = MainUI.getInstance();
		String choice = mainUI.getViewList().getSelectedItem().toString();
         if(choice == "Pie Chart") {
             System.out.println("piechart removed");
             panel.remove(ViewController.getPanelForRemoval("pie"));
             ViewController.displayGraphs(panel);
         }
         else if(choice == "Line Chart") {
         	System.out.println("Linechart removed");
            panel.remove(ViewController.getPanelForRemoval("line"));
            ViewController.displayGraphs(panel);
         }
         else if(choice == "Bar Chart") {    
         	System.out.println("Barchart removed");
             panel.remove(ViewController.getPanelForRemoval("bar"));
             ViewController.displayGraphs(panel);
         }
         else if (choice == "Scatter Chart") {
         	System.out.println("Scatterchart removed");
             panel.remove(ViewController.getPanelForRemoval("scatter"));
             ViewController.displayGraphs(panel);
         }
       else if (choice == "timeseries chart") {
     	  System.out.println("timeseries removed");
           panel.remove(ViewController.getPanelForRemoval("pie"));
           ViewController.displayGraphs(panel);
       }
       else if(choice == "Report") {
     	  System.out.println("report removed");
           panel.remove(ViewController.getReport());
           ViewController.displayGraphs(panel);
       }
         else {
             System.out.println("no more");
         }
	}
	
}
