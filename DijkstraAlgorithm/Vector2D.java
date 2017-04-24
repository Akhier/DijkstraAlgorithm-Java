package DijkstraAlgorithm;
/**
 * "Vector2D" is a class to represent a point on a 2d map and it's connections to other points
 * @author Akhier Dragonheart
 * @version 1.3.0
 */
public class Vector2D {
	public static int INFINITY = -1;
	public int X, Y, aggregateCost;
	public boolean deadend;
	private boolean visited;
	public boolean visited() {
		return visited;
	}
	public void setVisited() {
		totalVisited++;
		visited = true;
	}
	public void resetVisited() {
		visited = false;
	}

	private static int vectorIdCount = -1;
	public static int totalVisited = 0;
	public Edge edgeWithLowestCost;

	private int vectorId;
	public int vectorId() {
		return vectorId;
	}

	public Vector2D(int x, int y, boolean deadend) {
		visited = false;
		X = x;
		Y = y;
		this.deadend = deadend;
		aggregateCost = INFINITY;
		vectorId = ++vectorIdCount;
		edgeWithLowestCost = null;
	}

	@Override
	public String toString() {
		return "Vector ID: " + vectorId + " X: " + X + " Y: " + Y + ";";
	}

	public boolean compareTo(Vector2D othervector) {
		return vectorId() == othervector.vectorId();
	}
}
