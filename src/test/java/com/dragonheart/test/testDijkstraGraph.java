package com.dragonheart.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dragonheart.dijkstra.DijkstraGraph;
import com.dragonheart.dijkstra.Edge;
import com.dragonheart.dijkstra.Point;

public class testDijkstraGraph {
	private Point a, b, c, d, e, f;
	private Edge A, B, C, D, E, F, G, H;
	private DijkstraGraph graph;

	@Before
	public void setUp() throws Exception {
		a = new Point(2, 0);
		b = new Point(0, 2);
		c = new Point(2, 2);
		d = new Point(5, 2);
		e = new Point(2, 5);
		f = new Point(0, 5);
		A = new Edge(a, b, 1.0);
		B = new Edge(a, c, 3.0);
		C = new Edge(a, d, 5.0);
		D = new Edge(b, c, 1.0);
		E = new Edge(b, e, 3.0);
		F = new Edge(c, e, 1.0);
		G = new Edge(d, e, 1.0);
		H = new Edge(b, f, 1.0);
		graph = new DijkstraGraph();
		graph.addPoint(a);
		graph.addPoint(b);
		graph.addPoint(c);
		graph.addPoint(d);
		graph.addPoint(e);
		graph.addPoint(f);
		graph.addEdge(A);
		graph.addEdge(B);
		graph.addEdge(C);
		graph.addEdge(D);
		graph.addEdge(E);
		graph.addEdge(F);
		graph.addEdge(G);
		graph.addEdge(H);
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
		graph.setSource(a, 0.0);
		assertTrue(graph.processGraph());
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#processGraph()}
	 */
	@Test
	public final void testProcessGraph_BasicResults() {
		graph.setSource(e, 0.0);
		graph.processGraph();
		List<Point> path = graph.getPathFrom(a);
		assertTrue(path.get(0).aggregateCost == 3);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#processGraph()}
	 */
	@Test
	public final void testProcessGraph_GenericFleeResults() {
		graph = new DijkstraGraph();
		int width = 7, height = 7;
		Point[][] points = new Point[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				points[x][y] = new Point(x, y);
				graph.addPoint(points[x][y]);
				if(x != 0) { graph.addEdge(new Edge(points[x][y], points[x - 1][y], 1.0)); }
				if(y != 0) { graph.addEdge(new Edge(points[x][y], points[x][y - 1], 1.0)); }
				if(x != 0 && y != 0) {
					graph.addEdge(new Edge(points[x][y], points[x - 1][y - 1], 1.2));
				}
				if(x != width - 1 && y != 0) {
					graph.addEdge(new Edge(points[x][y], points[x + 1][y - 1], 1.2));
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
			assertTrue(p.X == x-- && p.Y == y);
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
		graph.setSource(e, 0.0);
		graph.processGraph();
		List<Point> path = graph.getPathFrom(a);
		assertTrue(path.get(0) == a);
		assertTrue(path.get(1) == b);
		assertTrue(path.get(2) == c);
		assertTrue(path.get(3) == e);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraph#getPathFrom(Point)}
	 */
	@Test
	public final void testGetPathFrom_WithMultipleSourceNodes() {
		ArrayList<Point> nodes = new ArrayList<Point>();
		nodes.add(e);
		nodes.add(f);
		graph.setSources(nodes, 0.0);
		graph.processGraph();
		List<Point> path = graph.getPathFrom(a);
		assertTrue(path.get(0) == a);
		assertTrue(path.get(1) == b);
		assertTrue(path.get(2) == f);
	}
}
