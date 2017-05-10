package com.dragonheart.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dragonheart.dijkstra.DijkstraGraph;
import com.dragonheart.dijkstra.Point;

public class testDijkstraGraph {
	private Point[][] points;
	private DijkstraGraph graph;

	@Before
	public void setUp() throws Exception {
		points = new Point[6][2];
		graph = new DijkstraGraph();
		for(int y = 0; y < 2; y++) {
			for(int x = 0; x < 6; x++) {
				points[x][y] = new Point(1.0);
				graph.addPoint(points[x][y]);
				if(y != 0) {
					graph.addEdge(points[x][y], points[x][y - 1]);
				}
				if(x != 0) {
					graph.addEdge(points[x][y], points[x - 1][y]);
				}
			}
		}
		points[0][1].costToEnter = 2.0;
		points[2][0].costToEnter = 4.0;
		points[4][1].costToEnter = 4.0;
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#processGraph()}
	 */
	@Test
	public final void testProcessGraph_WhenNoSourcesNodes_ReturnFalse() {
		assertFalse(graph.processGraph());
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#processGraph()}
	 */
	@Test
	public final void testProcessGraph_WithSourceNodes_ReturnTrue() {
		graph.setSource(points[0][0], 0.0);
		assertTrue(graph.processGraph());
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#processGraph()}
	 */
	@Test
	public final void testProcessGraph_BasicResults() {
		graph.setSource(points[5][1], 0.0);
		graph.processGraph();
		List<Point> path = graph.getPathFrom(points[0][0]);
		assertTrue(path.size() == 9);
		assertTrue(path.get(0).aggregateCost == 8.0);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#processGraph()}
	 */
	@Test
	public final void testProcessGraph_GenericFleeResults() {
		graph = new DijkstraGraph();
		int width = 7, height = 7;
		points = new Point[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				points[x][y] = new Point(1.0);
				graph.addPoint(points[x][y]);
				if(x != 0) { graph.addEdge(points[x][y], points[x - 1][y]); }
				if(y != 0) { graph.addEdge(points[x][y], points[x][y - 1]); }
				if(x != 0 && y != 0) {
					graph.addEdge(points[x][y], points[x - 1][y - 1]);
				}
				if(x != width - 1 && y != 0) {
					graph.addEdge(points[x][y], points[x + 1][y - 1]);
				}
			}
		}
		graph.setSource(points[width - 1][0], 0.0);
		graph.processGraph();
		graph.setSource(points[0][0]);
		graph.processGraph(-1.2);
		int x = 6, y = 6;
		List<Point> path = graph.getPathFrom(points[width - 1][height - 1]);
		for(Point p : path) {
			assertTrue(p == points[x--][y]);
			if(x < 0) {
				x = 0;
				y--;
			}
		}
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#getPathFrom(Point)}
	 */
	@Test
	public final void testGetPathFrom_WithSingleSourceNode() {
		graph.setSource(points[5][1], 0.0);
		graph.processGraph();
		List<Point> path = graph.getPathFrom(points[0][0]);
		assertTrue(path.get(0) == points[0][0]);
		assertTrue(path.get(1) == points[1][0]);
		assertTrue(path.get(4) == points[3][1]);
		assertTrue(path.get(7) == points[5][0]);
		assertTrue(path.get(8) == points[5][1]);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#getPathFrom(Point)}
	 */
	@Test
	public final void testGetPathFrom_WithMultipleSourceNodes() {
		ArrayList<Point> nodes = new ArrayList<Point>();
		nodes.add(points[5][1]);
		nodes.add(points[5][0]);
		graph.setSources(nodes, 0.0);
		graph.processGraph();
		List<Point> path = graph.getPathFrom(points[0][0]);
		assertTrue(path.get(0) == points[0][0]);
		assertTrue(path.get(1) == points[1][0]);
		assertTrue(path.get(path.size() - 1) == points[5][0]);
	}
}
