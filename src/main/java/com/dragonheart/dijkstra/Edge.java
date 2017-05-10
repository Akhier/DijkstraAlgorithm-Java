package com.dragonheart.dijkstra;

/**
 * "Edge" is a class made to represent the connection between 2 nodes in a dijkstra map
 * @author Akhier Dragonheart
 */
public class Edge {
	public Point A, B;

	public Edge(Point a, Point b) {
		this.A = a;
		this.B = b;
	}

	/**
	 * Returns the other Point on the Edge or null if given a Point not on the Edge
	 * @param basepoint
	 * @return either null if given a Point not on the Edge or the other Point of the given one
	 */
	public Point getOtherPoint(Point basepoint) {
		if(basepoint == A) {
			return B;
		} else if (basepoint == B) {
			return A;
		} else {
			return null;
		}
	}
}
