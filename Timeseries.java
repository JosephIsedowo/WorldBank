package Graphs;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import com.google.gson.JsonObject;



public class Timeseries extends Graph{

    private String analysis;
    private static ViewController vcontroller;
    public Timeseries(String analysis,JsonObject data,int startyr, int endyr) {
       super(data,startyr,endyr);
       this.analysis = analysis;
       vcontroller = ViewController.getViewController();
    }
    @Override 
    public void createGraph() {
       //code for creating graph with data
        JsonObject data = this.data;
       
        if(analysis == "Mortality") {
          TimeSeries series1 = new TimeSeries("Mortality/1000 births");
          for(int i = 1; i <= data.size(); i++) {
              JsonObject dataR = (JsonObject) data.get("result"+i); 
             if(dataR != null) {
                 for(int j = this.startyr; j<= this.endyr; j++) {
                     
                     Integer value = Integer.valueOf(j);
                     series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                 }
             }
             else {
                 System.out.println("result + i" + " is null");
             }
          }
          TimeSeriesCollection dataset = new TimeSeriesCollection();
          dataset.addSeries(series1);  
          vcontroller.timeSeriesView(analysis, dataset);
          ViewController.createReport(data.toString());
        }
        else if (analysis == "Unemployment AnnualChange") {

            TimeSeries series1 = new TimeSeries("Unemployment(% of total labor force)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr-1; j++) {
                       if(i == 1) {
                	   Integer year = Integer.valueOf(j);
                       Integer year2 = Integer.valueOf(j+1);
                       String diff = year2.toString() + "-" + year.toString();
                       series1.add(new Year(year2),dataR.get(diff).getAsInt());
                       
                       
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        
       
        else if(analysis == "Mortality vs Expenses") {
           TimeSeries series1 = new TimeSeries("Mortality/1000 births");
           TimeSeries series2 = new TimeSeries("Expenditure(% of GDP)");
           for(int i = 1; i <= data.size(); i++) {
               JsonObject dataR = (JsonObject) data.get("result"+i); 
              if(dataR != null) {
                  for(int j = this.startyr; j<=this.endyr; j++) {
                      if(i == 1) {
                      Integer value = Integer.valueOf(j);
                      series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                      }
                      else if(i == 2) {
                          Integer value = Integer.valueOf(j);
                          series2.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                      }
                  }
              }
              else {
                  System.out.println("result" + i + " is null");
              }
           }
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        else if (analysis == "Mortality vs GDP") {
           TimeSeries series1 = new TimeSeries("Mortality/1000 births");
           TimeSeries series2 = new TimeSeries("GDP(current US$)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        else if(analysis == "Unemployment vs GDP ratio(%)") {  
            TimeSeries series1 = new TimeSeries("Unemployment vs GDP ratio(%)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
     
        else if(analysis == "Unemployment vs Expenses"){
           TimeSeries series1 = new TimeSeries("Unemployment(% of total labor force)");
            TimeSeries series2 = new TimeSeries("Expenditure(% of GDP)");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        else if(analysis == "Unemployment vs Mortality") {
            TimeSeries series1 = new TimeSeries("Unemployment(% of total labor force)");
            TimeSeries series2 = new TimeSeries("Mortality/1000 births");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        else if(analysis == "Mortality vs Expenses & Hospital Beds") {
           TimeSeries series1 = new TimeSeries("Mortality/1000 births");
           TimeSeries series2 = new TimeSeries("Expenditure(% of GDP)");
            TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                           Integer value = Integer.valueOf(j);
                           series1.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           series2.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                       else if (i == 3) {
                           Integer value = Integer.valueOf(j);
                           series3.add(new Year(value.intValue()),dataR.get(value.toString()).getAsInt());
                       }
                   }
               }
               else {
                   System.out.println("result" + i + " is null");
               }
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            dataset.addSeries(series3);
            vcontroller.timeSeriesView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        else {
            System.out.println("No other analysis method to construct");
        }        
    }
   
}
