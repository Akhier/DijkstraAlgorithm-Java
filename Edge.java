/**
 * "Edge" represents a connection between one point to another including the cost of traversing this connection
 * @author Akhier Dragonheart
 * @version 1.0.1
 */

public class Edge {
	public Integer cost;
	private static int edgeIdCount = -1;

	private int edgeId;
	public int edgeId() {
		return edgeId;
	}

	private Vector2D pointA, pointB;
	/**
	 * The first point this Edge is connected to
	 * @return Vector2D 'pointA'
	 */
	public Vector2D pointA() {
		return pointA;
	}
	/**
	 * The second point this Edge is connected to
	 * @return Vector2D 'pointB
	 */
	public Vector2D pointB() {
		return pointB;
	}

	/**
	 * @param pointa is a Vector2D for the first point the Edge connects to
	 * @param pointb is a Vector2D for the second point the Edge connects to
	 * @param cost is how much it costs to traverse this Edge
	 */
	public Edge(Vector2D pointa, Vector2D pointb, int cost) {
		this.cost = cost;
		pointA = pointa;
		pointB = pointb;
		edgeId = ++edgeIdCount;
	}

	/**
	 * Gets the Vector2D of the other side of the Edge to the provided Vector2D
	 * @param basevector is the starting Vector2D
	 * @return Vector2D that is either the other Vector2D to the edge or null if the provided Vector2D is not in the Edge
	 */
	public Vector2D getOtherVector(Vector2D basevector) {
		if(basevector == pointA) {
			return pointB;
		} else if (basevector == pointB) {
			return pointA;
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "Edge ID: " + edgeId + " - Connected to vectors " + pointA.vectorId() + " and " + pointB.vectorId() + " at a cost of " + cost;
	}

	/**
	 * @param otheredge is the Edge to compare the current Edge to
	 * @return int which is 0 if the Edge costs are equal, less if this Edge's cost is lower, and more if this Edge's cost is higher
	 */
	public int compareTo(Edge otheredge) {
		return cost.compareTo(otheredge.cost);
	}
}
