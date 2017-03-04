public class Edge {
	public Integer cost;
	private static int edgeIdCount = -1;

	private int edgeId;
	public int edgeId() {
		return edgeId;
	}

	private Vector2D pointA, pointB;
	public Vector2D pointA() {
		return pointA;
	}
	public Vector2D pointB() {
		return pointB;
	}

	public Edge(Vector2D pointa, Vector2D pointb, int cost) {
		this.cost = cost;
		pointA = pointa;
		pointB = pointb;
		edgeId = ++edgeIdCount;
	}

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

	public int compareTo(Edge otheredge) {
		return cost.compareTo(otheredge.cost);
	}
}
