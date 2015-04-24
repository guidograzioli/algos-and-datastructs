package com.undebugged.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

	private final Set<Integer> vertices = new HashSet<Integer>();

	private final Map<Integer, Set<Integer>> adjacencyMap = new HashMap<Integer, Set<Integer>>();

	public Graph() {

	}

	public void addVertex(Integer v) {
		vertices.add(v);
		adjacencyMap.put(v, new HashSet<Integer>());
	}

	public void addEdge(Edge edge) {
		if (!adjacencyMap.containsKey(edge.getFrom())) {
			adjacencyMap.put(edge.getFrom(), new HashSet<Integer>());
		}
		adjacencyMap.get(edge.getFrom()).add(edge.getTo());
		if (!adjacencyMap.containsKey(edge.getTo())) {
			adjacencyMap.put(edge.getTo(), new HashSet<Integer>());
		}
		adjacencyMap.get(edge.getTo()).add(edge.getFrom());
	}

	public Set<Integer> getAdjacentVertices(Integer v) {
		return adjacencyMap.get(v);
	}

	public long size() {
		return vertices.size();
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (Integer v : vertices) {
			buffer.append(v).append(": ");
			for (Integer w : adjacencyMap.get(v)) {
				buffer.append("[ ").append(v).append(" - ").append(w).append(" ]");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

}
