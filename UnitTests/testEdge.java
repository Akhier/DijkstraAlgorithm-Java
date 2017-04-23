package UnitTests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import DijkstraAlgorithm.Edge;
import DijkstraAlgorithm.Vector2D;

/**
 * @author Dragonheart
 */
public class testEdge {
	private Edge testEdge;
	private static Vector2D a, b;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a = new Vector2D(0, 0, false);
		b = new Vector2D(1, 1, false);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testEdge = new Edge(a, b, 1);
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Edge#edgeId()}.
	 */
	@Test
	public final void testEdgeId() {
		int firstid = testEdge.edgeId();
		testEdge = new Edge(a, b, 1);
		assertNotEquals(firstid, testEdge.edgeId());
		assertEquals(firstid + 1, testEdge.edgeId());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Edge#pointA()}.
	 */
	@Test
	public final void testPointA() {
		assertEquals(a.vectorId(), testEdge.pointA().vectorId());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Edge#pointB()}.
	 */
	@Test
	public final void testPointB() {
		assertEquals(b.vectorId(), testEdge.pointB().vectorId());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Edge#getOtherVector(DijkstraAlgorithm.Vector2D)}.
	 */
	@Test
	public final void testGetOtherVector() {
		Vector2D c = new Vector2D(5, 5, false);
		assertNull(testEdge.getOtherVector(c));
		assertEquals(b.vectorId(), testEdge.getOtherVector(a).vectorId());
		assertEquals(a.vectorId(), testEdge.getOtherVector(b).vectorId());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Edge#compareTo(DijkstraAlgorithm.Edge)}.
	 */
	@Test
	public final void testCompareTo() {
		Edge otherEdge = new Edge(a, b, 1);
		assertEquals(0, testEdge.compareTo(otherEdge));
		otherEdge.cost = 2;
		assertTrue(testEdge.compareTo(otherEdge) < 0);
		otherEdge.cost = 0;
		assertTrue(testEdge.compareTo(otherEdge) > 0);
	}
}
