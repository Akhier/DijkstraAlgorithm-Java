package com.dragonheart.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class DijkstraGraph {
	private ArrayList<Edge> listOfEdges;
	private ArrayList<Point> listOfPoints, sourcePoints;

	public DijkstraGraph() {
		this.listOfEdges = new ArrayList<Edge>();
		this.listOfPoints = new ArrayList<Point>();
		this.sourcePoints = new ArrayList<Point>();
	}

	public void addPoint(Point point) {
		listOfPoints.add(point);
	}

	public void addEdge(Edge edge) {
		listOfEdges.add(edge);
	}

	public void addSource(Point point, Double cost) {
		if(listOfPoints.contains(point)) {
			point.aggregateCost = cost;
			sourcePoints.add(point);
		}
	}

	public void addSources(List<Point> points, Double cost) {
		for(Point point : points) {
			addSource(point, cost);
		}
	}

	public void setSource(Point point, Double cost) {
		sourcePoints = new ArrayList<Point>();
		addSource(point, cost);
	}

	public void setSources(List<Point> points, Double cost) {
		sourcePoints = new ArrayList<Point>();
		addSources(points, cost);
	}
}
