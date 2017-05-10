package com.dragonheart.dijkstra;

/**
 * "Point" is a class made to represent a node on a dijkstra map
 * @author Akhier Dragonheart
 */
public class Point {
	public Double aggregateCost, cost;
	public Point pointWithLowestCost;
	public boolean visited;

	public Point() {
		this.aggregateCost = null;
		this.cost = null;
		this.visited = false;
		this.pointWithLowestCost = null;
	}
}
