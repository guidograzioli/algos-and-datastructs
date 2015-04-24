package com.undebugged.trees;

import java.util.HashMap;
import java.util.Map;

public class SubTreeWeight {

	private final Map<Long, WeightedNode> treeMap = new HashMap<Long, WeightedNode>();

	public SubTreeWeight(String filename) {
		EdgeWeightTreeParser parser = new EdgeWeightTreeParser(filename);
		while (parser.hasNext()) {
			WeightedNode node = parser.next();
			if (treeMap.containsKey(node.getId())) {
				node.addChildren(treeMap.get(node.getId()).getChildren());
			}
			treeMap.put(Long.valueOf(node.getId()), node);
			WeightedNode parent = new WeightedNode(node.getParent());
			if (parent.getId() != node.getId()) {
				if (!treeMap.containsKey(node.getParent())) {
					parent.addChild(node);
					treeMap.put(Long.valueOf(node.getParent()), parent);
				} else {
					treeMap.get(Long.valueOf(node.getParent())).addChild(node);
				}
			}

		}
	}

	public long subTreeWeight(long id) {
		WeightedNode node = treeMap.get(id);
		if (node == null) {
			throw new IllegalArgumentException("not existent node");
		}
		long weight = node.getWeight();
		for (WeightedNode childNode : node.getChildren()) {
			weight += subTreeWeight(childNode.getId());
		}
		return weight;
	}

	public String printWeights() {
		StringBuilder printout = new StringBuilder();
		for (Map.Entry<Long, WeightedNode> nodeEntry : treeMap.entrySet()) {
			printout.append(String.format("ID: %s PAR: %s CHILDREN: %s WEIGHT: %s SUBTREE: %s\n", nodeEntry.getKey(),
					nodeEntry.getValue().getParent(), nodeEntry.getValue().getChildren(), nodeEntry.getValue()
							.getWeight(), subTreeWeight(nodeEntry.getKey())));
		}
		return printout.toString();
	}

	public static void main(String[] args) {
		SubTreeWeight subTreeWeight = new SubTreeWeight(
				"/home/guido/workspace/eduFx/programming-exercises/src/main/resources/weightedtree.csv");
		System.out.println(subTreeWeight.printWeights());
	}
}
