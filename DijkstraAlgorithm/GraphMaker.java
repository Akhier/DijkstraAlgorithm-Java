package DijkstraAlgorithm;
/**
 * "GraphMaker" is to automatically make Graphs
 * @author Akhier Dragonheart
 * @version 1.1.1
 */

public class GraphMaker {
	/**
	 * Turns a boolean[][] map into a Graph
	 * @param map is a boolean[][] with true representing walkable tiles
	 * @param cardinalcost is a positive int which if negative will be set to Integer.MAXVALUE
	 * @param diagonalcost is an int which if negative will mean diagonal movements are disallowed
	 * @return Graph of the map
	 */
	public static Graph rectangularMap(boolean[][] map, int cardinalcost, int diagonalcost) {
		if(cardinalcost < 0) {
			cardinalcost = Integer.MAX_VALUE;
		}
		int width = map.length;
		int height = map[0].length;
		Graph output = new Graph();
		Vector2D temp[][] = new Vector2D[width][height];
		for(int y =  0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(map[x][y]) {
					Vector2D here = new Vector2D(x, y, false);
					temp[x][y] = here;
					output.addVector(here);
				}
			}
		}
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(map[x][y]) {
					if(diagonalcost > -1) {
						if(x != 0 && y != 0) {
							if(map[x - 1][y - 1]) {
								output.addEdge(temp[x][y], temp[x - 1][y - 1], diagonalcost);
							}
						}
						if(x != width - 1 && y != 0) {
							if(map[x + 1][y - 1]) {
								output.addEdge(temp[x][y], temp[x + 1][y - 1], diagonalcost);
							}
						}
					}
					if(x != 0) {
						if(map[x - 1][y]) {
							output.addEdge(temp[x][y], temp[x - 1][y], cardinalcost);
						}
					}
					if(y != 0) {
						if(map[x][y - 1]) {
							output.addEdge(temp[x][y], temp[x][y - 1], cardinalcost);
						}
					}
				}
			}
		}
		return output;
	}
}
