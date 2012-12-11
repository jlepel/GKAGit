package a4_p01_jn_jl.graphic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import a4_p01_jn_jl.graphCreator.Vertex;

public class GraphPrinter {

	
	
	
	private Graph<Vertex, DefaultEdge> graph;
	private Map<List<DefaultEdge>, Integer> returnValueMap;
	private List<DefaultEdge> returnList;

	

	
	public GraphPrinter(Graph<Vertex, DefaultEdge> graph) {
		super();
		this.graph = graph;
		returnValueMap = new HashMap<List<DefaultEdge>, Integer>();
	}

	public GraphPrinter(Graph<Vertex, DefaultEdge> graph, Map<List<DefaultEdge>, Integer> result) {
		super();
		this.graph = graph;
		returnValueMap = result;
	}


	
	
	public void print(String fileName) {
		toDot(fileName);
        //createToPNGScript(fileName);
	}
	
	protected void createToPNGScript(String filename) {
	    File dotFile= new File(filename);

        //dot -Tpng RawCFG11_exe2_III_I.dot > file.png
        assert(dotFile.exists() && !dotFile.isDirectory());

        try {
            String[] cmd = {"dot", "-Tpng","-o" + dotFile.getAbsolutePath() + ".png", dotFile.getAbsolutePath()};
            Runtime.getRuntime().exec(cmd);

        } catch (IOException e) {
            System.out.println("Problem while generating a graph for a dotFile:" + e.toString());
           
        }
    }


	protected void toDot(String filename) {
		try {
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			if (!graph.vertexSet().isEmpty()) {
				
				
				
				DotExporter<Vertex, DefaultEdge> bla = new DotExporter<Vertex, DefaultEdge>();
				bla.export(out, graph, returnValueMap);
				

				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
