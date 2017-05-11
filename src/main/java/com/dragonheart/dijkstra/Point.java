package com.dragonheart.dijkstra;

/**
 * "Point" is a class made to represent a node on a dijkstra map
 * @author Akhier Dragonheart
 */
public class Point implements DijkstraPoint {
	public Double aggregateCost, entryCost;
	public Point pointWithLowestCost;
	public boolean visited;

	public Point(Double costtoenter) {
		this.aggregateCost = null;
		this.entryCost = costtoenter;
		this.visited = false;
		this.pointWithLowestCost = null;
	}

	@Override
	public Double costToEnter() {
		return entryCost;
	}
}
