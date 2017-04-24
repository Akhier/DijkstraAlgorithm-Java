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
		map = new boolean[7][7];
		map[0][0] = true; map[1][0] = true; map[2][0] = false; map[3][0] = true; map[4][0] = true; map[5][0] = true; map[6][0] = true;
		map[0][1] = false; map[1][1] = true; map[2][1] = false; map[3][1] = false; map[4][1] = true; map[5][1] = false; map[6][1] = true;
		map[0][2] = true; map[1][2] = true; map[2][2] = true; map[3][2] = false; map[4][2] = true; map[5][2] = false; map[6][2] = true;
		map[0][3] = true; map[1][3] = false; map[2][3] = true; map[3][3] = false; map[4][3] = true; map[5][3] = false; map[6][3] = true;
		map[0][4] = true; map[1][4] = false; map[2][4] = true; map[3][4] = true; map[4][4] = true; map[5][4] = false; map[6][4] = true;
		map[0][5] = true; map[1][5] = true; map[2][5] = true; map[3][5] = false; map[4][5] = false; map[5][5] = true; map[6][5] = true;
		map[0][6] = false; map[1][6] = false; map[2][6] = false; map[3][6] = false; map[4][6] = false; map[5][6] = false; map[6][6] = true;
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
