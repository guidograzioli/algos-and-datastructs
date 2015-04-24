package com.undebugged.graphs;

public class Edge {

	private final Integer from;
	private final Integer to;

	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getTo() {
		return to;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Edge) {
			Edge other = (Edge) obj;
			return (from.equals(other.getFrom()) && to.equals(other.getTo()))
					|| (to.equals(other.getFrom()) && from.equals(other.getTo()));
		}
		return false;
	}

	@Override
	public String toString() {
		return "Edge [ " + from + " - " + to + " ]";
	}

}
