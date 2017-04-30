package com.dragonheart.dijkstra;

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
