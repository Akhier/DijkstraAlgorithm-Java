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
}
