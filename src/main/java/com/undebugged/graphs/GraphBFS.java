package com.undebugged.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GraphBFS {

	private static Random random = new Random();

	private final List<Integer> visitOrder;
	private final Deque<Edge> queue;
	private final Map<Integer, Integer> backtrack;

	public GraphBFS(Graph graph) {
		visitOrder = new LinkedList<Integer>();
		queue = new LinkedList<Edge>();
		backtrack = new HashMap<Integer, Integer>();
		queue.addFirst(new Edge(0, 0));
		while (!queue.isEmpty()) {
			Edge e = queue.pollFirst();
			if (!visitOrder.contains(e.getTo())) {
				Integer v = e.getFrom();
				Integer w = e.getTo();
				visitOrder.add(w);
				if (!v.equals(w)) {
					backtrack.put(w, v);
				}
				for (Integer step : graph.getAdjacentVertices(w)) {
					if (!visitOrder.contains(step)) {
						queue.addLast(new Edge(v, step));
					}
				}
			}
		}
	}

	public List<Integer> getPath(Integer w, Integer v) {
		List<Integer> path = new ArrayList<Integer>();
		path.add(w);
		while (backtrack.containsKey(w)) {
			path.add(backtrack.get(w));
			if (v.equals(w)) {
				break;
			}
			w = backtrack.get(w);
		}
		return path;
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
			graph.addEdge(new Edge(i, random.nextInt(16)));
		}
		System.out.println(graph.toString());
		GraphBFS bfs = new GraphBFS(graph);
		System.out.println(bfs.getVisitOrder());
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.println(i + " to " + j + ":  " + bfs.getPath(i, j));
			}
		}
	}
}
