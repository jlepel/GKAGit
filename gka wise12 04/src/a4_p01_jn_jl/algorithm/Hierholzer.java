package a4_p01_jn_jl.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Pseudograph;

import a4_p01_jn_jl.graphCreator.Vertex;

public class Hierholzer {

	public static void start(Graph<Vertex, DefaultEdge> graph) {
	
		
		
		List<Vertex> vertexList = new ArrayList<Vertex>(graph.vertexSet());
		Random randomGenerator = new Random();
		Vertex startV = vertexList.get(randomGenerator.nextInt(vertexList.size()-1));
		
		List<Vertex> neigbour = Graphs.neighborListOf(graph, startV);
	  
		
			UndirectedGraph<String, DefaultEdge> graph2 = new Pseudograph<String,  DefaultEdge>(DefaultEdge.class);
	}
	
	
}
