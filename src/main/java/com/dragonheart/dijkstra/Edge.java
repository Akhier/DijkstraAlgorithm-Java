package com.dragonheart.dijkstra;

/**
 * "Edge" is a class made to represent the connection between 2 nodes in a dijkstra map
 * @author Akhier Dragonheart
 */
public class Edge implements Comparable<Edge>{
	public Point A, B;
	public Double cost;
	public final int edgeId;
	private static int edgeIdCount = -1;

	public Edge(Point a, Point b, Double cost) {
		this.A = a;
		this.B = b;
		this.cost = cost;
		this.edgeId = ++edgeIdCount;
	}

	/**
	 * Returns the other Point on the Edge or null if given a Point not on the Edge
	 * @param basepoint
	 * @return either null if given a Point not on the Edge or the other Point of the given one
	 */
	public Point otherPoint(Point basepoint) {
		if(basepoint == A) {
			return B;
		} else if (basepoint == B) {
			return A;
		} else {
			return null;
		}
	}

	@Override
	public int compareTo(Edge otheredge) {
		return cost.compareTo(otheredge.cost);
	}

	@Override
	public String toString() {
		return String.format("Edge Id: %d connected to Point A(%d) and Point B(%d) at cost %f", edgeId, A.pointId, B.pointId, cost);
	}
}
