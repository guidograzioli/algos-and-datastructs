package com.undebugged.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GraphDFS {

	private static Random random = new Random();

	private final List<Integer> visitOrder;
	private final Graph graph;

	public GraphDFS(Graph graph) {
		visitOrder = new LinkedList<Integer>();
		this.graph = graph;
		visitOrder.add(0);
		dfs(0);
	}

	private void dfs(Integer v) {
		Set<Integer> adjVertices = graph.getAdjacentVertices(v);
		for (Integer w : adjVertices) {
			if (!visitOrder.contains(w)) {
				visitOrder.add(w);
				dfs(w);
			}
		}
	}

	public List<Integer> getVisitOrder() {
		return visitOrder;
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		for (int i = 0; i < 16; i++) {
			graph.addVertex(i);
		}
		for (int i = 0; i < 16; i++) {
			graph.addEdge(new Edge(random.nextInt(16), random.nextInt(16)));
		}
		System.out.println(graph.toString());
		GraphDFS dfs = new GraphDFS(graph);
		System.out.println(dfs.getVisitOrder());
	}
}
