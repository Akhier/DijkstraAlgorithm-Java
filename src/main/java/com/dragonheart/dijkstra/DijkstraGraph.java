package com.dragonheart.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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

	private List<Point> getListOfVisitedPoints() {
		ArrayList<Point> listOfVisitedPoints = new ArrayList<Point>();
		for(Point point : listOfPoints) {
			if(point.isVisited()) {
				listOfVisitedPoints.add(point);
			}
		}
		return listOfVisitedPoints;
	}

	private PriorityQueue<Edge> getConnectedEdges(Point startpoint) {
		PriorityQueue<Edge> connectedEdges = new PriorityQueue<Edge>();
		for(Edge edge : listOfEdges) {
			Point otherPoint = edge.getOtherPoint(startpoint);
			if(otherPoint != null && !otherPoint.isVisited()) {
				connectedEdges.add(edge);
			}
		}
		return connectedEdges;
	}

	private Point getNextBestPoint() {
		Point nextBestPoint = null;
		for(Point visitedPoint : getListOfVisitedPoints()) {
			PriorityQueue<Edge> connectedEdges = getConnectedEdges(visitedPoint);
			while(connectedEdges.size() > 0) {
				Edge connectedEdge = connectedEdges.remove();
				Point otherPoint = connectedEdge.getOtherPoint(visitedPoint);
				if(otherPoint.aggregateCost == null ||
						(visitedPoint.aggregateCost + connectedEdge.cost) < otherPoint.aggregateCost) {
					otherPoint.aggregateCost = visitedPoint.aggregateCost + connectedEdge.cost;
					otherPoint.edgeWithLowestCost = connectedEdge;
				}
				if(nextBestPoint == null || otherPoint.aggregateCost < nextBestPoint.aggregateCost) {
					nextBestPoint = otherPoint;
				}
			}
		}
		return nextBestPoint;
	}

	private void performCalculationForAllPoints() {
		for(Point point : listOfPoints) {
			point.resetVisited();
			point.edgeWithLowestCost = null;
		}
		for(Point point : sourcePoints) {
			point.setVisited();
		}
		for(Point point : listOfPoints) {
			if(!point.isVisited()) {
				point.aggregateCost = null;
			}
		}
		Point currentPoint = null;
		do {
			currentPoint = getNextBestPoint();
			currentPoint.setVisited();
		} while(Point.TotalVisited < listOfPoints.size());
	}

	public boolean processGraph() {
		if(sourcePoints.isEmpty()) {
			return false;
		}
		performCalculationForAllPoints();
		Point.TotalVisited = 0;
		return true;
	}

	public List<Point> getPathFrom(Point targetpoint) {
		ArrayList<Point> shortestPath = new ArrayList<Point>();
		if(targetpoint != null) {
			Point currentPoint = targetpoint;
			shortestPath.add(currentPoint);
			while(currentPoint.edgeWithLowestCost != null) {
				currentPoint = currentPoint.edgeWithLowestCost.getOtherPoint(currentPoint);
				shortestPath.add(currentPoint);
			}
		}
		return shortestPath;
	}
}
