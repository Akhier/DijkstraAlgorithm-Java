package UnitTests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import DijkstraAlgorithm.Graph;
import DijkstraAlgorithm.GraphMaker;
import DijkstraAlgorithm.Vector2D;

/**
 * @author Dragonheart
 */
public class testGraphMaker {
	private static boolean[][] map;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map = new boolean[7][];
		map[0] = new boolean[]{true, true, false, true, true, true, true};
		map[1] = new boolean[]{false, true, false, false, true, false, true};
		map[2] = new boolean[]{true, true, true, false, true, false, true};
		map[3] = new boolean[]{true, false, true, false, true, false, true};
		map[4] = new boolean[]{true, false, true, true, true, false, true};
		map[5] = new boolean[]{true, true, true, false, false, true, true};
		map[6] = new boolean[]{false, false, false, false, false, false, true};
	}

	/**
	 * Test method for {@link DijkstraAlgorithm.GraphMaker#rectangularMap(boolean[][], int, int)}.
	 */
	@Test
	public final void testRectangularMap() {
		Graph graph = GraphMaker.rectangularMap(map, 1, 1);
		ArrayList<Vector2D> nodes = graph.allNodes();
		assertEquals(29, nodes.size());
		graph.sourceVector(nodes.get(0));
		graph.calculateShortestPath();
		ArrayList<Vector2D> path = graph.retrieveShortestPath(nodes.get(nodes.size() - 1));
		assertEquals(8, path.size());

		graph = GraphMaker.rectangularMap(map, 1, -1);
		nodes = graph.allNodes();
		assertEquals(29, nodes.size());
		graph.sourceVector(nodes.get(0));
		graph.calculateShortestPath();
		path = graph.retrieveShortestPath(nodes.get(nodes.size() - 1));
		assertEquals(21, path.size());
	}
}
