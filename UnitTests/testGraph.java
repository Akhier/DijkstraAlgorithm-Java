package UnitTests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import DijkstraAlgorithm.Vector2D;
import DijkstraAlgorithm.Edge;
import DijkstraAlgorithm.Graph;

/**
 * @author Dragonheart
 */
public class testGraph {
	private static Vector2D a, b, c, d, e;
	private static Edge A, B, C, D, E, F, G;
	private Graph graph;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a = new Vector2D(3, 0, false);
		b = new Vector2D(0, 3, false);
		c = new Vector2D(3, 3, false);
		d = new Vector2D(5, 3, false);
		e = new Vector2D(3, 5, false);
		A = new Edge(a, b, 1);
		B = new Edge(a, c, 3);
		C = new Edge(a, d, 5);
		D = new Edge(b, c, 1);
		E = new Edge(b, e, 3);
		F = new Edge(c, e, 1);
		G = new Edge(d, e, 1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		graph = new Graph();
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#allNodes()}.
	 */
	@Test
	public final void testAllNodes() {
		assertTrue(graph.allNodes().isEmpty());
		graph.addVector(a);
		assertTrue(graph.allNodes().size() == 1);
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#addVector(DijkstraAlgorithm.Vector2D)}.
	 */
	@Test
	public final void testAddVectorVector2D() {
		graph.addVector(a);
		assertEquals(a.vectorId(), graph.allNodes().get(0).vectorId());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#addVector(int, int, boolean)}.
	 */
	@Test
	public final void testAddVectorIntIntBoolean() {
		graph.addVector(0, 0, false);
		assertTrue(graph.allNodes().size() == 1);
		assertNotNull(graph.allNodes().get(0));
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#sourceVectors()}.
	 */
	@Test
	public final void testSourceVectors() {
		assertNull(graph.sourceVectors());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#sourceVector(DijkstraAlgorithm.Vector2D)}.
	 */
	@Test
	public final void testSourceVectorVector2D() {
		graph.sourceVector(a);
		assertTrue(graph.sourceVectors().isEmpty());
		graph.addVector(a);
		graph.sourceVector(a);
		assertEquals(graph.sourceVectors().get(0), a);
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#sourceVectors(ArrayList)
	 */
	@Test
	public final void testSourceVectorsArrayList() {
		ArrayList<Vector2D> nodes = new ArrayList<Vector2D>();
		nodes.add(a);
		nodes.add(b);
		graph.sourceVectors(nodes);
		assertTrue(graph.sourceVectors().isEmpty());
		graph.addVector(a);
		graph.addVector(b);
		graph.sourceVectors(nodes);
		assertTrue(graph.sourceVectors().contains(a));
		assertTrue(graph.sourceVectors().contains(b));
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#calculateShortestPath()}.
	 */
	@Test
	public final void testCalculateShortestPath() {
		assertFalse(graph.calculateShortestPath());
		graph.addVector(a);
		graph.addVector(b);
		graph.addEdge(A);
		graph.sourceVector(a);
		assertTrue(graph.calculateShortestPath());
		graph.addVector(c);
		graph.addEdge(B);
		assertTrue(graph.calculateShortestPath());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#calculateShortestPath()}.
	 */
	@Test
	public final void testCalculateShortestPathWithMultipleSources() {
		assertFalse(graph.calculateShortestPath());
		graph.addVector(a);
		graph.addVector(b);
		graph.addVector(c);
		graph.addEdge(A);
		graph.addEdge(B);
		ArrayList<Vector2D> nodes = new ArrayList<Vector2D>();
		nodes.add(a);
		nodes.add(b);
		graph.sourceVectors(nodes);
		assertTrue(graph.calculateShortestPath());
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.Graph#retrieveShortestPath(DijkstraAlgorithm.Vector2D)}.
	 */
	@Test
	public final void testRetrieveShortestPath() {
		graph.addVector(a);
		graph.addVector(b);
		graph.addEdge(A);
		graph.sourceVector(a);
		assertTrue(graph.calculateShortestPath());
		graph.addVector(c);
		graph.addVector(d);
		graph.addVector(e);
		graph.addEdge(B);
		graph.addEdge(C);
		graph.addEdge(D);
		graph.addEdge(E);
		graph.addEdge(F);
		graph.addEdge(G);
		assertTrue(graph.calculateShortestPath());
		ArrayList<Vector2D> path = graph.retrieveShortestPath(e);
		assertEquals(4, path.size());
		assertTrue(path.get(0).compareTo(a));
		assertTrue(path.get(1).compareTo(b));
		assertTrue(path.get(2).compareTo(c));
		assertTrue(path.get(3).compareTo(e));
	}
}
