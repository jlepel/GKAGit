package a4_p01_jn_jl.graphCreator;

public class Vertex implements Comparable<Vertex> {

	private String name;
	private int heuristic;
	private int edgeValue;

	public Vertex() {}
	
	public Vertex(String Vname) {

		name = Vname;
	}

	public Vertex(String Vname, int heuValue, int edgeValue) {
		this.edgeValue = edgeValue;
		this.name = Vname;
		this.heuristic = heuValue;
	}

	public Vertex(String Vname, int heuValue) {

		name = Vname;
		heuristic = heuValue;
	}

	public Integer getEdgeValue() {
		return edgeValue;
	}

	public void setEdgeValue(int value) {
		edgeValue = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public String toString() {
		return name;
	}

	public int hashCode() {
		return this.name.hashCode();

	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Vertex))
			return false;
		return this.name.equals(o.toString());
	}

	public int compareTo(Vertex oV) {
		return this.name.compareTo(oV.getName());
	}

}
