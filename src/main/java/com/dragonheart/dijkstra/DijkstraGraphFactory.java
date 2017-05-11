package com.dragonheart.dijkstra;

public class DijkstraGraphFactory {
	/**
	 * Create a DijkstraGraph from a 2D array of points that implements DijkstraPoint where null in the array means a tile that isn't walkable
	 * @param pointmap is a 2D array of points that implements DijkstraPoint
	 * @param costtoenter is a Double representing how much tiles cost to enter
	 * @param allowdiagonal is a boolean of if diagonal movement is allowed
	 * @return DijkstraGraph
	 */
	public static DijkstraGraph dijkstraGraphFrom2DDijkstraPointArray(DijkstraPoint[][] pointmap, Double costtoenter, boolean allowdiagonal) {
		DijkstraGraph graph = new DijkstraGraph();
		if(costtoenter > 0) {
			int width = pointmap.length, height = pointmap[0].length;
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(pointmap[x][y] != null) {
						pointmap[x][y].setEntryCost(costtoenter);
						graph.addPoint(pointmap[x][y]);
						if(allowdiagonal) {
							if(x != 0 && y != 0 && pointmap[x - 1][y - 1] != null) {
								graph.addConnection(pointmap[x][y], pointmap[x - 1][y - 1]);
							}
							if(x != width - 1 && y != 0 && pointmap[x + 1][y - 1] != null) {
								graph.addConnection(pointmap[x][y], pointmap[x + 1][y - 1]);
							}
						}
						if(x != 0 && pointmap[x - 1][y] != null) {
							graph.addConnection(pointmap[x][y], pointmap[x - 1][y]);
						}
						if(y != 0 && pointmap[x][y - 1] != null) {
							graph.addConnection(pointmap[x][y], pointmap[x][y - 1]);
						}
					}
				}
			}
		}
		return graph;
	}

	/**
	 * Create a DijkstraGraph from a 2D array of points that implements DijkstraPoint with pre-existing entry cost where null means the point isn't walkable
	 * @param pointmap is a 2D array of points that implements DijkstraPoint and the points have a pre-set entry cost to them
	 * @param allowdiagonal is a boolean of if diagonal movement is allowed
	 * @return DijkstraGraph
	 */
	public static DijkstraGraph dijkstraGraphFrom2DDijkstraPointArray(DijkstraPoint[][] pointmap, boolean allowdiagonal) {
		DijkstraGraph graph = new DijkstraGraph();
		int width = pointmap.length, height = pointmap[0].length;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(pointmap[x][y] != null && pointmap[x][y].costToEnter() > 0) {
					graph.addPoint(pointmap[x][y]);
					if(allowdiagonal) {
						if(x != 0 && y != 0 && pointmap[x - 1][y - 1] != null) {
							graph.addConnection(pointmap[x][y], pointmap[x - 1][y - 1]);
						}
						if(x != width - 1 && y != 0 && pointmap[x + 1][y - 1] != null) {
							graph.addConnection(pointmap[x][y], pointmap[x + 1][y - 1]);
						}
					}
					if(x != 0 && pointmap[x - 1][y] != null) {
						graph.addConnection(pointmap[x][y], pointmap[x - 1][y]);
					}
					if(y != 0 && pointmap[x][y - 1] != null) {
						graph.addConnection(pointmap[x][y], pointmap[x][y - 1]);
					}
				}
			}
		}
		return graph;
	}
}
