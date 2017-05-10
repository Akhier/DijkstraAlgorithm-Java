package com.dragonheart.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dragonheart.dijkstra.Edge;
import com.dragonheart.dijkstra.Point;

public class testEdge {
	private Point a, b;
	private Edge A;

	@Before
	public void setUp() throws Exception {
		a = new Point();
		b = new Point();
		A = new Edge(a, b, 1.0);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.Edge#getOtherPoint(Point)}
	 */
	@Test
	public final void testOtherPoint_WhenGivenPointNotOnEdge_ReturnNull() {
		Point c = new Point();
		assertNull(A.getOtherPoint(c));
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.Edge#getOtherPoint(Point)}
	 */
	@Test
	public final void testOtherPoint_ReturnOtherEdge() {
		assertTrue(A.getOtherPoint(a) == b);
	}
}
