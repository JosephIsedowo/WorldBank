package Graphs;

import org.jfree.data.category.DefaultCategoryDataset;


import com.google.gson.JsonObject;



// "Simple Bar Graph"
public class Bar extends Graph {
private String analysis;

private static ViewController vcontroller;
    public Bar(String analysis,JsonObject data,int startyr, int endyr) {
       super(data,startyr,endyr);
       this.analysis = analysis;
       vcontroller = ViewController.getViewController();
    }
 
    @Override 
    public void createGraph() {
       //code for creating graph with data
        DefaultCategoryDataset dataset;
        JsonObject data = this.data;
        if(analysis == "Mortality") {
            dataset = new DefaultCategoryDataset();
          for(int i = 1; i <= data.size(); i++) {
              JsonObject dataR = (JsonObject) data.get("result"+i); 
             if(dataR != null) {
                 for(int j = this.startyr; j<=this.endyr; j++) {
                     Integer value = Integer.valueOf(j);
                     dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Mortality", value.toString());
                 }
             }
             else {
                 System.out.println("result" + i + " is null");
             }
          }
          vcontroller.barView(analysis, dataset);
          ViewController.createReport(data.toString());
          
        }
        else if (analysis == "Unemployment AnnualChange") {
        	dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr-1; j++) {
                       if(i == 1) {
                	   Integer year = Integer.valueOf(j);
                       Integer year2 = Integer.valueOf(j+1);
                       String diff = year2.toString() + "-" + year.toString();
                       dataset.setValue(dataR.get(diff).getAsDouble(), "Unemployment AnnualChange",year2);
                         
                       }
                   }
               }
               else {
              	 System.out.println("result" + i + " is null");
              }
          }
            vcontroller.barView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
        
       
        else if(analysis == "Mortality vs Expenses") {
            dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Mortality", value.toString());
                   }
                       else if (i == 2) {
                           Integer value = Integer.valueOf(j);
                           dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Expenses", value.toString());
                       }
                   }
               }
              
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            vcontroller.barView(analysis, dataset);
            ViewController.createReport(data.toString());
           
        }
        else if (analysis == "Mortality vs GDP") {
            dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Mortality", value.toString());
                   }
                       else if (i == 2) {
                           Integer value = Integer.valueOf(j);
                           dataset.setValue(dataR.get(value.toString()).getAsDouble(), "GDP", value.toString());
                       }
                   }
               }
              
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            vcontroller.barView(analysis, dataset);
            ViewController.createReport(data.toString());
           
        }
        else if(analysis == "Unemployment vs GDP ratio(%)") {  
            dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Unemployment vs GDP ratio(%)", value.toString());
                   }
                   }        
                   vcontroller.barView(analysis, dataset);
                   ViewController.createReport(data.toString());
                   
               } 
               else {
                   System.out.println("result" + i + " is null");
               }
            } 
            vcontroller.barView(analysis, dataset);
            ViewController.createReport(data.toString());
        }
       
        else if(analysis == "Mortality vs Expenses & Hospital Beds"){
            dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Mortality", value.toString());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Expenses", value.toString());
                       }
                       else if(i == 3) {
                           Integer value = Integer.valueOf(j);
                           dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Hospital Beds", value.toString());
                       }
                   }
               }
              
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            vcontroller.barView(analysis, dataset);
            ViewController.createReport(data.toString());
            
        }
        else if(analysis == "Unemployment vs Mortality") {
            dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Unemployment", value.toString());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Mortality", value.toString());
                       }
                   }
               }
              
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            vcontroller.barView(analysis,dataset);
            ViewController.createReport(data.toString());
        }
        else if(analysis == "Unemployment vs Expenses") {
            dataset = new DefaultCategoryDataset();
            for(int i = 1; i <= data.size(); i++) {
                JsonObject dataR = (JsonObject) data.get("result"+i); 
               if(dataR != null) {
                   for(int j = this.startyr; j<=this.endyr; j++) {
                       if(i == 1) {
                       Integer value = Integer.valueOf(j);
                       dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Unemployment", value.toString());
                       }
                       else if(i == 2) {
                           Integer value = Integer.valueOf(j);
                           dataset.setValue(dataR.get(value.toString()).getAsDouble(), "Expenses", value.toString());
                       }
                   }
               }
              
               else {
                   System.out.println("result" + i + " is null");
               }
            }
            vcontroller.barView(analysis, dataset);
            ViewController.createReport(dataset.toString());
        }
        else {
            System.out.println("No other analysis method to construct");
        }
            
    }
    public JsonObject getData() {
        return this.data;
    }
}
