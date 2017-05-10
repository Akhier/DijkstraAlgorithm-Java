package com.dragonheart.dijkstra;

public class DijkstraGraphFactory {
	/**
	 * Create a dijkstra graph from a boolean[][] where true is walkable tiles and you can move diagonally
	 * @param boolmap is a boolean[][] containing data on what tiles are walkable
	 * @param cardinalcost is a positive Double that represents the cost to move in a cardinal direction
	 * @param diagonalcost is a positive Double that represents the cost to move in a diagonal direction
	 * @return DijkstraGraph
	 */
	public static DijkstraGraph dijkstraGraphFrom2DBoolArray(boolean[][] boolmap, Double cardinalcost, Double diagonalcost) {
		DijkstraGraph output = new DijkstraGraph();
		if(cardinalcost > 0) {
			int width = boolmap.length, height = boolmap[0].length;
			Point[][] pointmap = new Point[width][height];
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(boolmap[x][y]) {
						pointmap[x][y] = new Point();
						output.addPoint(pointmap[x][y]);
						if(diagonalcost > 0) {
							if(x != 0 && y != 0 && boolmap[x - 1][y - 1]) {
								output.addEdge(new Edge(pointmap[x][y], pointmap[x - 1][y - 1], diagonalcost));
							}
							if(x != width - 1 && y != 0 && boolmap[x + 1][y - 1]) {
								output.addEdge(new Edge(pointmap[x][y], pointmap[x + 1][y - 1], diagonalcost));
							}
						}
						if(x != 0 && boolmap[x - 1][y]) {
							output.addEdge(new Edge(pointmap[x][y], pointmap[x - 1][y], cardinalcost));
						}
						if(y != 0 && boolmap[x][y - 1]) {
							output.addEdge(new Edge(pointmap[x][y], pointmap[x][y - 1], cardinalcost));
						}
					}
				}
			}
		}
		return output;
	}

	/**
	 * Create a dijkstra graph from a boolean[][] where true is walkable tiles and you can't move diagonally
	 * @param boolmap is a boolean[][] containing data on what tiles are walkable
	 * @param cardinalcost is a positive Double that represents the cost to move in a cardinal direction
	 * @return DijkstraGraph
	 */
	public static DijkstraGraph dijkstraGraphFrom2DBoolArray(boolean[][] boolmap, Double cardinalcost) {
		return dijkstraGraphFrom2DBoolArray(boolmap, cardinalcost, -1.0);
	}
}
