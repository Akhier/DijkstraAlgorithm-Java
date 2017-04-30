package com.dragonheart.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.dragonheart.dijkstra.Point;
import com.dragonheart.dijkstra.Edge;
import com.dragonheart.dijkstra.DijkstraGraph;

public class testDijkstraGraph {
	private Point a, b, c, d, e;
	private Edge A, B, C, D, E, F, G;
	private DijkstraGraph graph;

	@Before
	public void setUp() throws Exception {
		a = new Point(2, 0);
		b = new Point(0, 2);
		c = new Point(2, 2);
		d = new Point(5, 2);
		e = new Point(2, 5);
		A = new Edge(a, b, 1.0);
		B = new Edge(a, c, 3.0);
		C = new Edge(a, d, 5.0);
		D = new Edge(b, c, 1.0);
		E = new Edge(b, e, 3.0);
		F = new Edge(c, e, 1.0);
		G = new Edge(d, e, 1.0);
		graph = new DijkstraGraph();
		graph.addPoint(a);
		graph.addPoint(b);
		graph.addPoint(c);
		graph.addPoint(d);
		graph.addPoint(e);
		graph.addEdge(A);
		graph.addEdge(B);
		graph.addEdge(C);
		graph.addEdge(D);
		graph.addEdge(E);
		graph.addEdge(F);
		graph.addEdge(G);
	}

	@Test
	public final void testProcessGraph_WhenNoSourcesNodes_ReturnFalse() {
		assertFalse(graph.processGraph);
	}
}
