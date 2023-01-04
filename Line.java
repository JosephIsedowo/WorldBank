package Graphs;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.google.gson.JsonObject;



public class Line extends Graph {

    private String analysis;
    private static ViewController vcontroller;
    public Line(String analysis,JsonObject data,int startyr, int endyr) {
       super(data,startyr,endyr);
       this.analysis = analysis;
       vcontroller = ViewController.getViewController();
    }
    @Override 
    public void createGraph() {
       //code for creating graph with data
        JsonObject data = this.data;
       
        if(analysis == "Mortality") {
            XYSeries series1 = new XYSeries("Mortality/1000 births");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       Integer value = Integer.valueOf(j);
                       series1.add(value.doubleValue(), dataR.get(value.toString()).getAsDouble());
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
            
        }
       
        else if(analysis == "Mortality vs Expenses") {
            XYSeries series1 = new XYSeries("Mortality/1000 births");
            XYSeries series2 = new XYSeries("Expenditure(% of GDP)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        else if (analysis == "Mortality vs GDP") {

            XYSeries series1 = new XYSeries("Mortality/1000 births");
            XYSeries series2 = new XYSeries("GDP(current US$)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
        
        }
        else if (analysis == "Unemployment AnnualChange") {

            XYSeries series1 = new XYSeries("Unemployment(% of total labor force)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr-1; j++) {
                       if(i == 1) {
                	   Integer year = Integer.valueOf(j);
                       Integer year2 = Integer.valueOf(j+1);
                       String diff = year2.toString() + "-" + year.toString();
                       series1.add(year.doubleValue(),dataR.get(diff).getAsDouble());
                       series1.add(year2.doubleValue(),dataR.get(diff).getAsDouble());
                       
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
        }

        else if(analysis == "Unemployment vs GDP ratio(%)") {  

            XYSeries series1 = new XYSeries("Unemployment vs GDP ratio(%)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
            	   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(value.doubleValue(), dataR.get(value.toString()).getAsDouble());
                       }    
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
       
        else if(analysis == "Unemployment vs Expenses"){

            XYSeries series1 = new XYSeries("Unemployment(% of total labor force)");
            XYSeries series2 = new XYSeries("Expenditure(% of GDP)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
        
        }
        else if(analysis == "Unemployment vs Mortality") {

            XYSeries series1 = new XYSeries("Unemployment(% of total labor force)");
            XYSeries series2 = new XYSeries("Mortality/1000 births");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            vcontroller.lineView(analysis, dataset);      
            ViewController.createReport(data.toString());
        }
        else if(analysis == "Mortality vs Expenses & Hospital Beds") {
            XYSeries series1 = new XYSeries("Mortality/1000 births");
            XYSeries series2 = new XYSeries("Expenditure(% of GDP)");
            XYSeries series3 = new XYSeries("Hospital Beds/1000 people");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                           Integer value = Integer.valueOf(j);
                           series1.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                       else if (i == 3) {
                           Integer value = Integer.valueOf(j);
                           series3.add(value.doubleValue(),dataR.get(value.toString()).getAsDouble());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            dataset.addSeries(series3);
            vcontroller.lineView(analysis, dataset);
            ViewController.createReport(data.toString());
        
        }
        else {
            System.out.println("No other analysis method to construct");
        }
           
    }
}