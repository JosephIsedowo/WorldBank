package Graphs;

import com.google.gson.JsonObject;


//Strategy 
public abstract class Graph {
    protected JsonObject data;
    protected int startyr;
    protected int endyr;
    
    public Graph(JsonObject data, int startyr, int endyr) {
        this.data = data;
        this.startyr = startyr;
        this.endyr = endyr;
    }
    public abstract void createGraph();
}
