package com.dragonheart.dijkstra;

import java.util.ArrayList;
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

	/**
	 * Add a Point onto the list of sources with a starting cost
	 * @param point is a Point that is the source you want to add
	 * @param cost is a Double with what this source starts at
	 */
	public void addSource(Point point, Double cost) {
		if(listOfPoints.contains(point)) {
			point.aggregateCost = cost;
			sourcePoints.add(point);
		}
	}

	/**
	 * Add a list of Points to the list of sources all with the specified starting cost
	 * @param points is a List<Point> containing the points you want to add to your sources
	 * @param cost is a Double with what these sources start at
	 */
	public void addSources(List<Point> points, Double cost) {
		for(Point point : points) {
			addSource(point, cost);
		}
	}

	/**
	 * Sets your source Points to this Point and cost
	 * @param point is the Point that is the source you want
	 * @param cost is a Double with what this source starts at
	 */
	public void setSource(Point point, Double cost) {
		sourcePoints = new ArrayList<Point>();
		addSource(point, cost);
	}

	/**
	 * Sets your source Points to these Points and cost
	 * @param points is a List<Point> containing the points you want to set your sources to
	 * @param cost is a Double with what these sources start at
	 */
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

	/**
	 * processes the whole graph
	 * @return If no sourcePoints return false, otherwise return true
	 */
	public boolean processGraph() {
		if(sourcePoints.isEmpty()) {
			return false;
		}
		performCalculationForAllPoints();
		Point.TotalVisited = 0;
		return true;
	}

	/**
	 * Gets the path from the target to the closest source
	 * @param targetpoint is the Point you want the path to go from
	 * @return List<Point> with the path to the closest source
	 */
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