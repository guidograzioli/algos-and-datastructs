package com.undebugged.trees;

import java.util.HashSet;
import java.util.Set;

public class WeightedNode {

	private long id;
	private long weight;
	private long parent;
	private final Set<WeightedNode> children = new HashSet<WeightedNode>();

	public WeightedNode(long id, long parent, long weight) {
		this.id = id;
		this.parent = parent;
		this.weight = weight;
	}

	public WeightedNode(long id) {
		this.id = id;
		this.parent = 0;
		this.weight = 0;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof WeightedNode) {
			if (((WeightedNode) obj).getId() == getId()) {
				return true;
			}
		}
		return false;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public long getParent() {
		return parent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public Set<WeightedNode> getChildren() {
		return children;
	}

	public void addChild(WeightedNode node) {
		children.add(node);
	}

	public void addChildren(Set<WeightedNode> nodeSet) {
		children.addAll(nodeSet);
	}
}
