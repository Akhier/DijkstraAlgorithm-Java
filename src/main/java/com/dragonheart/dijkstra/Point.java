package com.dragonheart.dijkstra;

/**
 * "Point" is a class made to represent a node on a dijkstra map
 * @author Akhier Dragonheart
 */
public class Point {
	public Double aggregateCost;
	public Edge edgeWithLowestCost;
	public boolean visited;

	public final int X, Y, Z;

	public Point(int x, int y, int z) {
		this.X = x;
		this.Y = y;
		this.Z = z;
		this.aggregateCost = null;
		this.visited = false;
		this.edgeWithLowestCost = null;
	}

	public Point(int x, int y) {
		this(x, y, 0);
	}
}
