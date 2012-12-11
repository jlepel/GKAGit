package a4_p01_jn_jl.fileInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a4_p01_jn_jl.interfaces.InputFormatInterface;

public class InputFormatImpl implements InputFormatInterface {

	private List<String> resultLines;

	public InputFormatImpl(String inputFile) throws IOException {
		resultLines = new ArrayList<String>();
		FileReader file = new FileReader(inputFile);
		BufferedReader br = new BufferedReader(file);
		String line = null;

		while ((line = br.readLine()) != null) {
			resultLines.add(line);
		}

		br.close();
	}


	@Override
	public void output() {
		for (String elem : resultLines) {
			System.out.println(elem);
		}
	}

	@Override
	public boolean isDirected() {
		if (this.resultLines.get(0).equals("#gerichtet")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUndirected() {
		return !isDirected();
	}

	@Override
	public boolean isWeighted() {
		if (resultLines.get(1).startsWith("#gewichtet")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isUnweighted() {
		return !isWeighted();
	}
	
	@Override
	public boolean hasHeuristics() {
		if (this.resultLines.get(1).contains("#attributiert")) {
			return true;
		}
		return false;
	}

	@Override
	public Set<String> getEdges() {
		Set<String> edges = new HashSet<String>();
		if (this.isUnweighted()) {

			for (String elem : resultLines) {
				if (!elem.startsWith("#")) {
					edges.add(elem);
				}
			}
		} else {
			for (String elem : resultLines) {
				if (!elem.startsWith("#")) {
					String[] subelem = elem.split(",");
					edges.add(subelem[0] + " " + subelem[2]);
				}
			}
		}
		return edges;
	}

	public Set<List<String>> getEdgesInArray() {
		Set<List<String>> edges = new HashSet<List<String>>();

		for (String elem : resultLines) {
			if (!elem.startsWith("#")) {
				List<String> tmp = new ArrayList<String>();

				String[] subelem = elem.split(",");
				if (this.isUnweighted()) {
					tmp.add(subelem[0]);
					tmp.add(subelem[1]);
					edges.add(tmp);
				} else if (this.isWeighted() && this.hasHeuristics()) {
					tmp.add(subelem[0].trim());
					tmp.add(subelem[2].trim());
					tmp.add(subelem[4].trim());
					edges.add(tmp);
				}
				else  {
					tmp.add(subelem[0]);
					tmp.add(subelem[1]);
					tmp.add(subelem[2]);
					edges.add(tmp);
				}
				
			}
		}
		
		return edges;
	}

	@Override
	public Double getWeight(String edge) {
		if (isWeighted()) {
			for (String elem : resultLines) {
				if (!elem.startsWith("#")) {
					String[] subelem = elem.split(",");
					if (edge.contains(subelem[0]) && edge.contains(subelem[2])) {
						return Double.parseDouble(subelem[4]);
					}
				}
			}
		}
		return 0.0;
	}

	@Override
	public Map<String, Integer> getAllHeuristic(){
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		
			for (String elem : resultLines) {
				if (!elem.startsWith("#")) {
					String[] subelem = elem.split(",");
					if(!result.containsKey(subelem[0])){
						result.put(subelem[0], Integer.parseInt(subelem[1].trim()));
					}
					if(!result.containsKey(subelem[2])){
						result.put(subelem[2], Integer.parseInt(subelem[3].trim()));
					}
				}
			}
		return result;	
	}
	@Override
	public int getHeuristic(String v){
		Map<String, Integer> list = new HashMap<String, Integer>();
		list.putAll(getAllHeuristic());
		return list.get(v);
	}
	
	@Override
	public Set<String> getVertex() {
		Set<String> vertex = new HashSet<String>();

		for (String elem : resultLines) {
			if (!elem.startsWith("#")) {
				String[] subelem = elem.split(",");
				if (this.isUnweighted()) {
					vertex.add(subelem[0]);
					vertex.add(subelem[1]);
				} else if(this.isWeighted() && this.hasHeuristics()) {
					vertex.add(subelem[0].trim());
					vertex.add(subelem[2].trim());
					
				}
				else{
					vertex.add(subelem[0].trim());
					vertex.add(subelem[1].trim());
				}
			}
		}
		return vertex;
	}

}
