package a4_p01_jn_jl.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface InputFormatInterface  {

	boolean isDirected();
	
	boolean isUndirected();
    
	Set<String> getEdges();	
	
    Set<String> getVertex();
	
    Set<List<String>> getEdgesInArray();
   
    void output();

	boolean isWeighted();

	boolean isUnweighted();

	Double getWeight(String edge);

	Map<String, Integer> getAllHeuristic();

	int getHeuristic(String node);

	boolean hasHeuristics();    

}
