/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info: http://jgrapht.sourceforge.net/
 * Project Creator: Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* ------------------
 * DOTExporter.java
 * ------------------
 * (C) Copyright 2006, by Trevor Harmon.
 *
 * Original Author: Trevor Harmon <trevor@vocaro.com>
 *
 */
package a4_p01_jn_jl.graphic;

import java.io.PrintWriter;
import org.jgrapht.graph.DefaultEdge;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.VertexNameProvider;

import a4_p01_jn_jl.graphCreator.Vertex;

public class DotExporter<V, E> {
	private VertexNameProvider<V> vertexIDProvider;
	private VertexNameProvider<V> vertexLabelProvider;
	private EdgeNameProvider<E> edgeLabelProvider;
	String indent = "";
	String binder = " - ";
	String connector;
	
	public DotExporter() {
		this(new IntegerNameProvider<V>(), null, null);
	}

	public DotExporter(Map<List<String>, Integer> result) {
		this(new IntegerNameProvider<V>(), null, null);
	}

	public DotExporter(IntegerNameProvider<V> integerNameProvider,
			VertexNameProvider<Vertex> vertexLabelProvider,
			EdgeNameProvider<E> edgeLabelProvider) {
		this.vertexIDProvider = (VertexNameProvider<V>) integerNameProvider;
		this.vertexLabelProvider = (VertexNameProvider<V>) vertexLabelProvider;
		this.edgeLabelProvider = edgeLabelProvider;
	}

	public void export(Writer writer,
			Graph<Vertex, org.jgrapht.graph.DefaultEdge> graph, Map<List<DefaultEdge>, Integer> result) {
		PrintWriter out = new PrintWriter(writer);

		List<DefaultEdge> entryList = new ArrayList<DefaultEdge>();
		
		for (List<DefaultEdge> elem : result.keySet()) {
			for (DefaultEdge elm : elem) {
				entryList.add(elm);
			}
		}

		if (graph instanceof DirectedGraph) {
			out.println("digraph G {");
			connector = " -> ";
		} else {
			out.println("graph G {");
			connector = " -- ";
		}

		// Style
		generateStyle(out);

		// Vertexliste für DOT erstellen
		generateVertexOut(graph, out);

		
		// Kanten bauen
		for (DefaultEdge e : graph.edgeSet()) {
			String source = graph.getEdgeSource(e).getName();
			String target = graph.getEdgeTarget(e).getName();
			
			
			if(entryList.contains(e)){			
			out.print(indent + "\""+ source + binder + graph.getEdgeSource(e).getHeuristic() + "\"" + connector + 
					           "\""+ target + binder + graph.getEdgeSource(e).getHeuristic() +  "\""
					+ " [weight=" + (int) graph.getEdgeWeight(e)
					+ " ] " + "[label=" + (int) graph.getEdgeWeight(e)
					+ " ] " + "[color=\"#FF0000\"]" + "[penwidth=3]");
			}
			else{
				out.print(indent + "\""+ source + binder + graph.getEdgeSource(e).getHeuristic() +  "\""+  connector +  
						           "\""+ target + binder + graph.getEdgeSource(e).getHeuristic() + "\""
						+ " [weight=" + (int) graph.getEdgeWeight(e)
						+ " ] " + "[label=" + (int) graph.getEdgeWeight(e)
						+ " ] ");
			}
			
			

			if (edgeLabelProvider != null) {
				out.print(" [label = \"" + edgeLabelProvider.getEdgeName((E) e)
						+ "\"]");
			}
			out.println(";");
		}

		out.println("}");
		out.flush();
	}

	private void generateVertexOut(
			Graph<Vertex, org.jgrapht.graph.DefaultEdge> graph,	PrintWriter out) {

		for (Vertex v : graph.vertexSet()) {

			if (graph instanceof DirectedGraph) {
				out.print("\"" + indent + v.getName().trim() + "\"");
			} else {
				out.print("\"" + indent + v.getName().trim() + binder
						+ v.getHeuristic() + "\"");
			}

			if (vertexLabelProvider != null) {
				out.print(" [label = \""
						+ vertexLabelProvider.getVertexName((V) v.getName())
						+ "\"]");
			}
			out.println(";");
		}
	}

	private void generateStyle(PrintWriter out) {
		out.println("node [fontname=Verdana,fontsize=12]");
		out.println("node [style=filled]");
		out.println("node [fillcolor=\"#CCCCCC\"]");
		out.println("node [color=\"#EEEEEE\"]");
		out.println("edge [color=\"#31CEF0\"]");
	}
}