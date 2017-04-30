package com.dragonheart.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.dragonheart.dijkstra.Point;
import com.dragonheart.dijkstra.Edge;

public class testEdge {
	private Point a, b;
	private Edge A, B;

	@Before
	public void setUp() throws Exception {
		a = new Point(0, 0);
		b = new Point(1, 1);
		A = new Edge(a, b, 1.0);
		B = new Edge(a, b, 2.0);
	}

	@Test
	public final void testEdgeIdIncrement() {
		asserTrue(A.edgeId == B.edgeId - 1);
	}

	@Test
	public final void testOtherEdge_WhenGivenPointNotOnEdge_ReturnNull() {
		Point c = new Point(0, 0);
		assertNull(A.otherEdge(c));
	}

	@Test
	public final void testOtherEdge_ReturnOtherEdge() {
		assertTrue(A.otherEdge(a) == b);
	}

	@Test
	public final void testEdgeCompareTo() {
		assertTrue(A.compareTo(A) == 0);
		assertTrue(A.compareTo(B) < 0);
		assertTrue(B.compareTo(A) > 0);
	}
}
