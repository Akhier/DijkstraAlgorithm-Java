package com.dragonheart.dijkstra;

/**
 * "Point" is a class made to represent a node on a dijkstra map
 * @author Akhier Dragonheart
 */
public class Point implements Comparable<Point>{
	//the three that will matter
	public Double aggregateCost;
	public Edge edgeWithLowestCost;
	public boolean visited;

	public final int X, Y, Z;
	//Id is purely for debugging as seen with toString being the only place it is called
	public final int pointId;
	private static int pointIdCount = -1;

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
		return String.format("Point Id: %d, X: %d, Y: %d, Z: %d, Aggregate Cost: %f;", pointId, X, Y, Z, aggregateCost);
	}
}
