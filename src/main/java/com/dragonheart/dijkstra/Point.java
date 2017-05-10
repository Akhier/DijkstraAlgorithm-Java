package com.dragonheart.dijkstra;

/**
 * "Point" is a class made to represent a node on a dijkstra map
 * @author Akhier Dragonheart
 */
public class Point {
	public Double aggregateCost, costToEnter;
	public Point pointWithLowestCost;
	public boolean visited;

	public Point(Double costtoenter) {
		this.aggregateCost = null;
		this.costToEnter = null;
		this.visited = false;
		this.pointWithLowestCost = null;
	}
}
