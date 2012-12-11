package a4_p01_jn_jl.graphCreator;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import a4_p01_jn_jl.graphCreator.Vertex;
import a4_p01_jn_jl.interfaces.InputFormatInterface;

public class GraphCreator {

	private GraphCreator() {
	}

	public static Graph<Vertex, DefaultEdge> createGraph(
			InputFormatInterface resultList) {
		
		
		
		if (resultList.isDirected()) {

			DirectedGraph<Vertex, DefaultEdge> graph1 = new DefaultDirectedGraph<Vertex, DefaultEdge>(
					DefaultEdge.class);

			for (String node : resultList.getVertex()) {
				Vertex tmp = new Vertex(node);
				graph1.addVertex(tmp);
			}
			


			for (List<String> edge : resultList.getEdgesInArray()) {
				Vertex startKnoten = new Vertex(edge.get(0));
				Vertex  zielKnoten = new Vertex(edge.get(1));
				graph1.addEdge(startKnoten, zielKnoten);
			}

			return graph1;
		}

		else {
		
			
			UndirectedGraph<Vertex, DefaultEdge> graph2 = new Pseudograph<Vertex, DefaultEdge>(
					DefaultEdge.class);

			WeightedPseudograph<Vertex, DefaultEdge> graphW = new WeightedPseudograph<Vertex, DefaultEdge>(
					DefaultWeightedEdge.class);
		
			
			if (resultList.isWeighted() && resultList.hasHeuristics()) {
				
				
				for (String node : resultList.getVertex()) {
					Vertex tmp = new Vertex(node, resultList.getHeuristic(node));
					graphW.addVertex(tmp);
				}

				for (List<String> edge : resultList.getEdgesInArray()) {
					Vertex startKnoten = new Vertex(edge.get(0));
					Vertex  zielKnoten = new Vertex(edge.get(1));
					
					DefaultEdge e1 = graphW.addEdge(startKnoten, zielKnoten);
					graphW.setEdgeWeight(e1, Double.parseDouble(edge.get(2)));
				}

				return graphW;
			}
			if (resultList.isWeighted() && !resultList.hasHeuristics()) {
				for (String node : resultList.getVertex()) {
					Vertex tmp = new Vertex(node);
					
					graphW.addVertex(tmp);
				}

				for (List<String> edge : resultList.getEdgesInArray()) {
					Vertex startKnoten = new Vertex(edge.get(0));
					Vertex  zielKnoten = new Vertex(edge.get(1));
					
					if (!graphW.containsEdge(startKnoten, zielKnoten)) {
					DefaultEdge e1 = graphW.addEdge(startKnoten, zielKnoten);
					graphW.setEdgeWeight(e1, Double.parseDouble(edge.get(2)));
					}
				}

				return graphW;
				
				
			

			} else {

				for (String node : resultList.getVertex()) {
					Vertex tmp = new Vertex(node);
					graph2.addVertex(tmp);
				}

				for (List<String> edge : resultList.getEdgesInArray()) {
					Vertex startKnoten = new Vertex(edge.get(0));
					Vertex  zielKnoten = new Vertex(edge.get(1));
					
					if (!graph2.containsEdge(startKnoten, zielKnoten)) {
						graph2.addEdge(startKnoten, zielKnoten);
					}
				}

				return graph2;
			}

		}
	}

}
