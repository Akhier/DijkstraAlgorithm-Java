package com.dragonheart.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.dragonheart.dijkstra.Point;

public class testPoint {
	private Point a, b;

	@Before
	public void setUp() throws Exception {
		a = new Point(0, 0, 0.0);
		b = new Point(0, 0, 1.0);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.Point#pointId}
	 */
	@Test
	public final void testPointIdIncrement() {
		assertTrue(a.pointId == b.pointId - 1);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.Point#TotalVisited}
	 */
	@Test
	public final void testTotalVisited() {
		int temp = Point.TotalVisited;
		a.setVisited();
		assertTrue(temp == Point.TotalVisited - 1);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.Point#compareTo(Point)}
	 */
	@Test
	public final void testCompareTo() {
		assertTrue(a.compareTo(a) == 0);
		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
	}
}
