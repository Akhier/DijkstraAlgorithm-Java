package UnitTests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import DijkstraAlgorithm.Graph;
import DijkstraAlgorithm.GraphMaker;
import DijkstraAlgorithm.Vector2D;

/**
 * @author Dragonheart
 */
public class checkGraph {
	private boolean[][] map;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		map = new boolean[7][7];
		map[0][0] = true; map[1][0] = true; map[2][0] = false; map[3][0] = true; map[4][0] = true; map[5][0] = true; map[6][0] = true;
		map[0][1] = false; map[1][1] = true; map[2][1] = false; map[3][1] = false; map[4][1] = true; map[5][1] = false; map[6][1] = true;
		map[0][2] = true; map[1][2] = true; map[2][2] = true; map[3][2] = false; map[4][2] = true; map[5][2] = false; map[6][2] = true;
		map[0][3] = true; map[1][3] = false; map[2][3] = true; map[3][3] = false; map[4][3] = true; map[5][3] = false; map[6][3] = true;
		map[0][4] = true; map[1][4] = false; map[2][4] = true; map[3][4] = true; map[4][4] = true; map[5][4] = false; map[6][4] = true;
		map[0][5] = true; map[1][5] = true; map[2][5] = true; map[3][5] = false; map[4][5] = false; map[5][5] = true; map[6][5] = true;
		map[0][6] = false; map[1][6] = false; map[2][6] = false; map[3][6] = false; map[4][6] = false; map[5][6] = false; map[6][6] = true;
	}

	@Test
	public final void test() {
		Graph graph = GraphMaker.rectangularMap(map, 1, -1);
		Graph graph2 = GraphMaker.rectangularMap(map, 1, 1);
		ArrayList<Vector2D> nodes = graph.allNodes();
		ArrayList<Vector2D> nodes2 = graph2.allNodes();
		graph.sourceVector(nodes.get(0));
		graph2.sourceVector(nodes2.get(0));
		graph.calculateShortestPath();
		graph2.calculateShortestPath();
		int[][] test = new int[7][7];
		int[][] test2 = new int[7][7];
		int[][] test3 = new int[7][7];
		int[][] test4 = new int[7][7];
		for(int y = 0; y < 7; y++) {
			for(int x = 0; x < 7; x++) {
				test[x][y] = -1;
				test2[x][y] = -1;
				test3[x][y] = -1;
				test4[x][y] = -1;
			}
		}
		for(Vector2D node : nodes) {
			test[node.X][node.Y] = node.aggregateCost;
		}
		for(Vector2D node2 : nodes2) {
			test2[node2.X][node2.Y] = node2.aggregateCost;
		}
		HashMap<Vector2D, Integer> sources = new HashMap<Vector2D, Integer>();
		sources.put(nodes.get(0), 0);
		sources.put(nodes.get(nodes.size() - 1), 3);
		graph.sourceVectors(sources);
		graph.calculateShortestPath();
		for(Vector2D node : nodes) {
			test3[node.X][node.Y] = node.aggregateCost;
		}
		ArrayList<Vector2D> sources2 = new ArrayList<Vector2D>();
		sources2.add(nodes2.get(0));
		sources2.add(nodes2.get(nodes2.size() - 1));
		graph2.sourceVectors(sources2);
		graph2.calculateShortestPath();
		for(Vector2D node : nodes2) {
			test4[node.X][node.Y] = node.aggregateCost;
		}
		System.out.printf("┌──┬──┬──┬──┬──┬──┬──┐   ┌──┬──┬──┬──┬──┬──┬──┐   ┌──┬──┬──┬──┬──┬──┬──┐   ┌──┬──┬──┬──┬──┬──┬──┐\n");
		for(int y = 0; y < 7; y++) {
			System.out.printf("│");
			for(int x = 0; x < 7; x++) {
				if(test[x][y] == -1) {
					System.out.printf("▓▓│");
				} else {
					System.out.printf("%2d│", test[x][y]);
				}
			}
			System.out.printf("   │");
			for(int x = 0; x < 7; x++) {
				if(test2[x][y] == -1) {
					System.out.printf("▓▓│");
				} else {
					System.out.printf("%2d│", test2[x][y]);
				}
			}
			System.out.printf("   │");
			for(int x = 0; x < 7; x++) {
				if(test3[x][y] == -1) {
					System.out.printf("▓▓│");
				} else {
					System.out.printf("%2d│", test3[x][y]);
				}
			}
			System.out.printf("   │");
			for(int x = 0; x < 7; x++) {
				if(test4[x][y] == -1) {
					System.out.printf("▓▓│");
				} else {
					System.out.printf("%2d│", test4[x][y]);
				}
			}
			if(y < 6) {
				System.out.printf("\n├──┼──┼──┼──┼──┼──┼──┤   ├──┼──┼──┼──┼──┼──┼──┤   ├──┼──┼──┼──┼──┼──┼──┤   ├──┼──┼──┼──┼──┼──┼──┤\n");
			} else {
				System.out.printf("\n└──┴──┴──┴──┴──┴──┴──┘   └──┴──┴──┴──┴──┴──┴──┘   └──┴──┴──┴──┴──┴──┴──┘   └──┴──┴──┴──┴──┴──┴──┘\n");
			}
		}
	}
}
