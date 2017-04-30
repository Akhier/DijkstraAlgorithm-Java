package com.dragonheart.dijkstra;

import java.util.ArrayList;

public class DijkstraGraph {
	private ArrayList<Edge> listOfEdges;
	private ArrayList<Point> listOfPoints, sourceNodes;

	public DijkstraGraph() {
		listOfEdges = new ArrayList<Edge>();
		listOfPoints = new ArrayList<Point>();
		sourceNodes = new ArrayList<Point>();
	}
}
