package com.dragonheart.dijkstra;

/**
 * "Point" is a class made to represent a node on a dijkstra map
 * @author Akhier Dragonheart
 */
public class Point implements Comparable<Point>{
	public final int X, Y, Z;
	public Double aggregateCost;
	public final int pointId;
	public static int TotalVisited = 0;
	private static int pointIdCount = -1;
	public Edge edgeWithLowestCost;
	private boolean visited;
	public boolean isVisited() {
		return visited;
	}
	public void setVisited() {
		visited = true;
		TotalVisited++;
	}
	public void resetVisited() {
		visited = false;
	}

	public Point(int x, int y, int z) {
		this.X = x;
		this.Y = y;
		this.Z = z;
		this.aggregateCost = null;
		this.pointId = ++pointIdCount;
		this.visited = false;
		this.edgeWithLowestCost = null;
	}

	public Point(int x, int y) {
		this(x, y, 0);
	}

	@Override
	public int compareTo(Point otherpoint) {
		return aggregateCost.compareTo(otherpoint.aggregateCost);
	}

	@Override
	public String toString() {
		return String.format("Point Id: %d, X: %d, Y: %d, Z: %d, Aggregate Cost: %f, Total Visited: %d;", pointId, X, Y, Z, aggregateCost, TotalVisited);
	}
}
