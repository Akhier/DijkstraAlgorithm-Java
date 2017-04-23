/**
 * "Vector2D" is a class to represent a point on a 2d map and it's connections to other points
 * @author Akhier Dragonheart
 * @version 1.0
 */
public class Vector2D {
	public static int INFINITY = -1;
	public int X, Y, aggregateCost;
	public boolean deadend, visited;
	private static int vectorIdCount = -1;
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
}
