package com.dragonheart.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dragonheart.dijkstra.Point;

public class testPoint {
	private Point a, b;

	@Before
	public void setUp() throws Exception {
		a = new Point(0, 0);
		b = new Point(0, 0);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.Point#compareTo(Point)}
	 */
	@Test
	public final void testPointCompareTo() {
		a.aggregateCost = 0.0;
		b.aggregateCost = 1.0;
		assertTrue(a.compareTo(a) == 0);
		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
	}
}
