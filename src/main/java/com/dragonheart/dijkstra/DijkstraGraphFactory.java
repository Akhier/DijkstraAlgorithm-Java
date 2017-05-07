package com.dragonheart.dijkstra;

public class DijkstraGraphFactory {
	public static DijkstraGraph dijkstraGraphFrom2DBoolArray(boolean[][] boolmap, Double cardinalcost, Double diagonalcost) {
		DijkstraGraph output = new DijkstraGraph();
		if(cardinalcost > 0) {
			int width = boolmap.length, height = boolmap[0].length;
			Point[][] pointmap = new Point[width][height];
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					if(boolmap[x][y]) {
						pointmap[x][y] = new Point(x, y);
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

	public static DijkstraGraph dijkstraGraphFrom2DBoolArray(boolean[][] boolmap, Double cardinalcost) {
		return dijkstraGraphFrom2DBoolArray(boolmap, cardinalcost, -1.0);
	}
}
