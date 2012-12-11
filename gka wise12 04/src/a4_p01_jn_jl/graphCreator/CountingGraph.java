package a4_p01_jn_jl.graphCreator;

import java.util.Collection;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;

public class CountingGraph implements Graph {
	private Graph graph;
	private int accessCounter = 0;

	public CountingGraph(Graph graph) {
		super();
		this.graph = graph;
	}

	public Set getAllEdges(Object sourceVertex, Object targetVertex) {
		accessCounter++;
		return graph.getAllEdges(sourceVertex, targetVertex);
	}

	public Object getEdge(Object sourceVertex, Object targetVertex) {
		accessCounter++;
		return graph.getEdge(sourceVertex, targetVertex);
	}

	public EdgeFactory getEdgeFactory() {
		return graph.getEdgeFactory();
	}

	public Object addEdge(Object sourceVertex, Object targetVertex) {
		return graph.addEdge(sourceVertex, targetVertex);
	}

	public boolean addEdge(Object sourceVertex, Object targetVertex, Object e) {
		return graph.addEdge(sourceVertex, targetVertex, e);
	}

	public boolean addVertex(Object v) {
		return graph.addVertex(v);
	}

	public boolean containsEdge(Object sourceVertex, Object targetVertex) {
		return graph.containsEdge(sourceVertex, targetVertex);
	}

	public boolean containsEdge(Object e) {
		accessCounter++;
		return graph.containsEdge(e);
	}

	public boolean containsVertex(Object v) {
		accessCounter++;
		return graph.containsVertex(v);
	}

	public Set edgeSet() {
		accessCounter++;
		return graph.edgeSet();
	}

	public Set edgesOf(Object vertex) {
		accessCounter++;
		return graph.edgesOf(vertex);
	}

	public boolean removeAllEdges(Collection edges) {
		return graph.removeAllEdges(edges);
	}

	public Set removeAllEdges(Object sourceVertex, Object targetVertex) {
		return graph.removeAllEdges(sourceVertex, targetVertex);
	}
	

	public boolean removeAllVertices(Collection vertices) {
		return graph.removeAllVertices(vertices);
	}

	public Object removeEdge(Object sourceVertex, Object targetVertex) {
		return graph.removeEdge(sourceVertex, targetVertex);
	}

	public boolean removeEdge(Object e) {
		return graph.removeEdge(e);
	}

	public boolean removeVertex(Object v) {
		return graph.removeVertex(v);
	}

	public Set vertexSet() {
		return graph.vertexSet();
	}

	public Object getEdgeSource(Object e) {
		accessCounter++;
		return graph.getEdgeSource(e);
	}

	public Object getEdgeTarget(Object e) {
		accessCounter++;
		return graph.getEdgeTarget(e);
	}

	public double getEdgeWeight(Object e) {
		return graph.getEdgeWeight(e);
	}


	public int getAccessCounter() {
		return accessCounter;
	}



}
