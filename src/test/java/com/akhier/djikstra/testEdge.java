package com.akhier.djikstra;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Dragonheart
 */
public class testEdge {
	private Edge testEdge;
	private static Vector2D vectorA, vectorB;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vectorA = new Vector2D(0, 0, false);
		vectorB = new Vector2D(1, 1, false);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testEdge = new Edge(vectorA, vectorB, 1);
	}

	/**
	 * Test method for {@link Edge#edgeId()}.
	 */
	@Test
	public final void testEdgeId() {
		int firstid = testEdge.edgeId();
		testEdge = new Edge(vectorA, vectorB, 1);
		assertNotEquals(firstid, testEdge.edgeId());
		assertEquals(firstid + 1, testEdge.edgeId());
	}

	/**
	 * Test method for {@link Edge#pointA()}.
	 */
	@Test
	public final void testPointA() {
		assertEquals(vectorA.vectorId(), testEdge.pointA().vectorId());
	}

	/**
	 * Test method for {@link Edge#pointB()}.
	 */
	@Test
	public final void testPointB() {
		assertEquals(vectorB.vectorId(), testEdge.pointB().vectorId());
	}

	/**
	 * Test method for {@link Edge#getOtherVector(Vector2D)}.
	 */
	@Test
	public final void testGetOtherVector() {
		Vector2D c = new Vector2D(5, 5, false);
		assertNull(testEdge.getOtherVector(c));
		assertEquals(vectorB.vectorId(), testEdge.getOtherVector(vectorA).vectorId());
		assertEquals(vectorA.vectorId(), testEdge.getOtherVector(vectorB).vectorId());
	}

	/**
	 * Test method for {@link Edge#compareTo(Edge)}.
	 */
	@Test
	public final void testCompareTo() {
		Edge otherEdge = new Edge(vectorA, vectorB, 1);
		assertEquals(0, testEdge.compareTo(otherEdge));
		otherEdge.cost = 2;
		assertTrue(testEdge.compareTo(otherEdge) < 0);
		otherEdge.cost = 0;
		assertTrue(testEdge.compareTo(otherEdge) > 0);
	}
}
