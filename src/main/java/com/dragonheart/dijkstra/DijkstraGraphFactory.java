package com.dragonheart.dijkstra;

public class DijkstraGraphFactory {
	/**
	 * Create a dijkstra graph from a boolean[][] where true is walkable tiles and you can choose if you allow diagonally movement
	 * @param boolmap is a boolean[][] containing data on what tiles are walkable
	 * @param costtoenter is a positive Double that represents the cost to move in a direction
	 * @param allowdiagonal
	 * @param emtpygraph is a DijkstraGraph which will be the constructed graph
	 * @return Point[][]
	 */
	public static DijkstraPoint[][] dijkstraGraphFrom2DBoolArray(boolean[][] boolmap, Double costtoenter, boolean allowdiagonal, DijkstraGraph emptygraph) {
		emptygraph.clear();
		if(costtoenter > 0) {
			int width = boolmap.length, height = boolmap[0].length;
			DijkstraPoint[][] pointmap = new DijkstraPoint[width][height];
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(boolmap[x][y]) {
						pointmap[x][y].setEntryCost(costtoenter);
						emptygraph.addPoint(pointmap[x][y]);
						if(allowdiagonal) {
							if(x != 0 && y != 0 && boolmap[x - 1][y - 1]) {
								emptygraph.addConnection(pointmap[x][y], pointmap[x - 1][y - 1]);
							}
							if(x != width - 1 && y != 0 && boolmap[x + 1][y - 1]) {
								emptygraph.addConnection(pointmap[x][y], pointmap[x + 1][y - 1]);
							}
						}
						if(x != 0 && boolmap[x - 1][y]) {
							emptygraph.addConnection(pointmap[x][y], pointmap[x - 1][y]);
						}
						if(y != 0 && boolmap[x][y - 1]) {
							emptygraph.addConnection(pointmap[x][y], pointmap[x][y - 1]);
						}
					}
				}
			}
			return pointmap;
		}
		return null;
	}
}
