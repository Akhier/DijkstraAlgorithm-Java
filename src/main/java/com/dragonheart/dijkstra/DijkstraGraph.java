package com.dragonheart.dijkstra;

import java.util.ArrayList;

public class DijkstraGraph {
	private ArrayList<Edge> listOfEdges;
	private ArrayList<Point> listOfPoints, sourceNodes;

	public DijkstraGraph() {
		this.listOfEdges = new ArrayList<Edge>();
		this.listOfPoints = new ArrayList<Point>();
		this.sourceNodes = new ArrayList<Point>();
	}

	public void addPoint(Point point) {
		listOfPoints.add(point);
	}

	public void addEdge(Edge edge) {
		listOfEdges.add(edge);
	}
}
