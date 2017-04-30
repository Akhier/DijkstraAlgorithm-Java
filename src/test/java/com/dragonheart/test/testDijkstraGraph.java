package com.dragonheart.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.dragonheart.dijkstra.Point;
import com.dragonheart.dijkstra.Edge;
import com.dragonheart.dijkstra.DijkstraGraph;

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

	@Test
	public final void testProcessGraph_WhenNoSourcesNodes_ReturnFalse() {
		assertFalse(graph.processGraph());
	}

	@Test
	public final void testProcessGraph_WithSourceNodes_ReturnTrue() {
		graph.setSourceNode(a);
		assertTrue(graph.processGraph());
	}

	@Test
	public final void testGetPathFrom_WithSingleSourceNode() {
		graph.setSourceNode(e);
		graph.processGraph();
		ArrayList<Point> path = graph.getPathFrom(a);
		assertTrue(path.get(0) == a);
		assertTrue(path.get(1) == b);
		assertTrue(path.get(2) == c);
		assertTrue(path.get(3) == e);
	}

	@Test
	public final void testGetPathFrom_WithMultipleSourceNodes() {
		ArrayList<Point> nodes = new ArrayList<Point>();
		nodes.add(e);
		nodes.add(f);
		graph.setSourceNodes(nodes);
		graph.processGraph()
		ArrayList<Point> path = graph.getPathFrom(a);
		assertTrue(path.get(0) == a);
		assertTrue(path.get(1) == b);
		assertTrue(path.get(2) == f);
	}
}
