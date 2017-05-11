package com.dragonheart.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dragonheart.dijkstra.DijkstraGraph;
import com.dragonheart.dijkstra.DijkstraGraphFactory;
import com.dragonheart.dijkstra.DijkstraPoint;

public class testDijkstraGraphFactory {
	private class Point implements DijkstraPoint {
		public Double entryCost;
		public Point(Double cost) {
			this.entryCost = cost;
		}
		@Override
		public Double costToEnter() {
			return entryCost;
		}
		@Override
		public void setEntryCost(Double cost) {
			this.entryCost = cost;
		}
	}
	private Point[][] pointMap;

	@Before
	public void setUp() throws Exception {
		int width = 7, height = 7;
		pointMap = new Point[width][height];
		pointMap[0][0] = new Point(1.0); pointMap[1][0] = new Point(1.0); pointMap[2][0] = null; pointMap[3][0] = new Point(1.0); pointMap[4][0] = new Point(1.0); pointMap[5][0] = new Point(1.0); pointMap[6][0] = new Point(1.0);
		pointMap[0][1] = null; pointMap[1][1] = new Point(1.0); pointMap[2][1] = null; pointMap[3][1] = null; pointMap[4][1] = new Point(1.0); pointMap[5][1] = null; pointMap[6][1] = new Point(1.0);
		pointMap[0][2] = new Point(1.0); pointMap[1][2] = new Point(1.0); pointMap[2][2] = new Point(1.0); pointMap[3][2] = null; pointMap[4][2] = new Point(1.0); pointMap[5][2] = null; pointMap[6][2] = new Point(1.0);
		pointMap[0][3] = new Point(1.0); pointMap[1][3] = null; pointMap[2][3] = new Point(1.0); pointMap[3][3] = null; pointMap[4][3] = new Point(1.0); pointMap[5][3] = null; pointMap[6][3] = new Point(1.0);
		pointMap[0][4] = new Point(1.0); pointMap[1][4] = null; pointMap[2][4] = new Point(1.0); pointMap[3][4] = new Point(1.0); pointMap[4][4] = new Point(1.0); pointMap[5][4] = null; pointMap[6][4] = new Point(1.0);
		pointMap[0][5] = new Point(1.0); pointMap[1][5] = new Point(1.0); pointMap[2][5] = new Point(1.0); pointMap[3][5] = null; pointMap[4][5] = null; pointMap[5][5] = new Point(1.0); pointMap[6][5] = new Point(1.0);
		pointMap[0][6] = null; pointMap[1][6] = null; pointMap[2][6] = null; pointMap[3][6] = null; pointMap[4][6] = null; pointMap[5][6] = null; pointMap[6][6] = new Point(1.0);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraphFactory#dijkstraGraphFrom2DDijkstraPointArray(DijkstraPoint[][], Double, boolean)}
	 */
	@Test
	public final void testDijkstraGraphFrom2DDijkstraPointArray_NoDiagonalMovement() {
		DijkstraGraph graph = DijkstraGraphFactory.dijkstraGraphFrom2DDijkstraPointArray(pointMap, 1.0, false);
		graph.setSource(pointMap[6][6], 0.0);
		assertTrue(graph.processGraph());
		List<DijkstraPoint> path = graph.getPathFrom(pointMap[0][0]);
		DijkstraPoint temp = path.get(1);
		assertTrue(temp == pointMap[1][0]);
		temp = path.get(2);
		assertTrue(temp == pointMap[1][1]);
		temp = path.get(path.size() - 2);
		assertTrue(temp == pointMap[6][5]);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraphFactory#dijkstraGraphFrom2DDijkstraPointArray(DijkstraPoint[][], Double, boolean)}
	 */
	@Test
	public final void testDijkstraGraphFrom2DDijkstraPointArray_DiagonalMovement() {
		DijkstraGraph graph = DijkstraGraphFactory.dijkstraGraphFrom2DDijkstraPointArray(pointMap, 1.0, true);
		graph.setSource(pointMap[6][6], 0.0);
		assertTrue(graph.processGraph());
		List<DijkstraPoint> path = graph.getPathFrom(pointMap[0][0]);
		DijkstraPoint temp = path.get(1);
		assertTrue(temp == pointMap[1][1]);
		temp = path.get(3);
		assertTrue(temp == pointMap[2][3]);
		temp = path.get(path.size() - 2);
		assertTrue(temp == pointMap[5][5]);
	}

	/**
	 * Test method for {@link com.dragonheart.dijkstra.DijkstraGraphFactory#dijkstraGraphFrom2DDijkstraPointArray(DijkstraPoint[][], boolean)}
	 */
	@Test
	public final void testDijkstraGraphFrom2DDijkstraPointArray_PreExistingCosts_WithDiagonalMovement() {
		DijkstraGraph graph = DijkstraGraphFactory.dijkstraGraphFrom2DDijkstraPointArray(pointMap, true);
		graph.setSource(pointMap[6][6], 0.0);
		assertTrue(graph.processGraph());
		List<DijkstraPoint> path = graph.getPathFrom(pointMap[0][0]);
		DijkstraPoint temp = path.get(1);
		assertTrue(temp == pointMap[1][1]);
		temp = path.get(3);
		assertTrue(temp == pointMap[2][3]);
		temp = path.get(path.size() - 2);
		assertTrue(temp == pointMap[5][5]);
	}
}
