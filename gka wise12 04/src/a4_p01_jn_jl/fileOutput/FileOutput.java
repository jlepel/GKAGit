package a4_p01_jn_jl.fileOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.WeightedPseudograph;

import a4_p01_jn_jl.graphCreator.Vertex;

public class FileOutput {

	
	public static void save(Graph<Vertex, DefaultEdge> testGraph, String fileName) throws IOException
	{
		fileName = fileName.concat(".gka");

		File file = new File(fileName);
		FileWriter writer = new FileWriter(file);
		Set<DefaultEdge> edges = testGraph.edgeSet();
		
		List<String> result = new ArrayList<String>();
		
		if (testGraph instanceof DirectedGraph){
			result.add(0,"#gerichtet");
		}
		else
		{ 
			result.add(0,"#ungerichtet");
		}
		
		if(testGraph instanceof WeightedPseudograph){
			result.add(1,"#gewichtet#attributiert");
			
			for(DefaultEdge elem : edges){
				Vertex startV = testGraph.getEdgeSource(elem);
				Vertex endV = testGraph.getEdgeTarget(elem);
				result.add(startV.getName() + "," + startV.getHeuristic()+ "," + endV.getName() + "," + endV.getHeuristic() + "," + (int)testGraph.getEdgeWeight(elem));
				}
			}
			else{
				for(DefaultEdge elem : edges){
					Vertex startV = testGraph.getEdgeSource(elem);
					Vertex endV = testGraph.getEdgeTarget(elem);
					result.add(startV.getName() + ","+ endV.getName());	
				}
		
			}

		for(String elem : result){
			writer.append(elem);
			writer.append('\n');
		}
		writer.flush();
		writer.close();
		
	}
	
}
