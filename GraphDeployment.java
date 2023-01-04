package Graphs;

//Graph deployment
public class GraphDeployment<g extends Graph> {
private Graph gStrategy;

public GraphDeployment(g graph) {
this.gStrategy = graph;
}

public void setStrategy(g graph) {
this.gStrategy = graph;
}
public void execute() {
gStrategy.createGraph();
	}
}