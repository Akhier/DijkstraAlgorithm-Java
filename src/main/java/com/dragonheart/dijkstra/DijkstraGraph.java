package com.dragonheart.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraGraph {
	private ArrayList<Edge> listOfEdges;
	private ArrayList<Point> listOfPoints, sourcePoints;
	private int totalVisited = 0;

	public DijkstraGraph() {
		this.listOfEdges = new ArrayList<Edge>();
		this.listOfPoints = new ArrayList<Point>();
		this.sourcePoints = new ArrayList<Point>();
	}

	protected void clear() {
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
	 * Add a Point onto the list
	 * @param point is a Point that is the source you want to add
	 */
	public void addSource(Point point) {
		if(listOfPoints.contains(point)) {
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
	 * Sets your source Point to this Point and cost
	 * @param point is the Point that is the source you want
	 * @param cost is a Double with what this source starts at
	 */
	public void setSource(Point point, Double cost) {
		sourcePoints = new ArrayList<Point>();
		addSource(point, cost);
	}

	/**
	 * Sets your source Point to this Point
	 * @param point is the Point that is the source you want
	 */
	public void setSource(Point point) {
		sourcePoints = new ArrayList<Point>();
		addSource(point, point.aggregateCost);
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
			if(point.visited) {
				listOfVisitedPoints.add(point);
			}
		}
		return listOfVisitedPoints;
	}

	private PriorityQueue<Point> getConnectedEdges(Point startpoint) {
		PriorityQueue<Point> connectedPoints = new PriorityQueue<Point>();
		for(Edge edge : listOfEdges) {
			Point otherPoint = edge.getOtherPoint(startpoint);
			if(otherPoint != null && !otherPoint.visited) {
				connectedPoints.add(otherPoint);
			}
		}
		return connectedPoints;
	}

	private Point getNextBestPoint() {
		Point nextBestPoint = null;
		for(Point visitedPoint : getListOfVisitedPoints()) {
			PriorityQueue<Edge> connectedEdges = getConnectedEdges(visitedPoint);
			while(connectedEdges.size() > 0) {
				Edge connectedEdge = connectedEdges.remove();
				Point otherPoint = connectedEdge.getOtherPoint(visitedPoint);
				if(otherPoint.aggregateCost == null ||
						(visitedPoint.aggregateCost + otherPoint.costToEnter) < otherPoint.aggregateCost) {
					otherPoint.aggregateCost = visitedPoint.aggregateCost + otherPoint.costToEnter;
					otherPoint.pointWithLowestCost = visitedPoint;
				}
				if(nextBestPoint == null || otherPoint.aggregateCost < nextBestPoint.aggregateCost) {
					nextBestPoint = otherPoint;
				}
			}
		}
		return nextBestPoint;
	}

	private void performCalculationForAllPoints() {
		Point currentPoint = null;
		do {
			currentPoint = getNextBestPoint();
			currentPoint.visited = true;
			totalVisited++;
		} while(totalVisited < listOfPoints.size());
	}

	/**
	 * processes the whole graph
	 * @return If no sourcePoints return false, otherwise return true
	 */
	public boolean processGraph() {
		if(sourcePoints.isEmpty()) {
			return false;
		}
		for(Point point : listOfPoints) {
			point.visited = false;
			point.pointWithLowestCost = null;
		}
		totalVisited = 0;
		for(Point point : sourcePoints) {
			point.visited = true;
			totalVisited++;
		}
		for(Point point : listOfPoints) {
			if(!point.visited) {
				point.aggregateCost = null;
			}
		}
		performCalculationForAllPoints();
		return true;
	}

	/**
	 * processes the whole graph while keeping any pre-existing aggregateCosts
	 * @param multiplierforaggregatecosts will be a value that the aggregateCost of all points will be multiplied by
	 * @return If no sourcePoints return false, otherwise return true
	 */
	public boolean processGraph(double multiplierforaggregatecosts) {
		if(sourcePoints.isEmpty()) {
			return false;
		}
		totalVisited = 0;
		for(Point point : listOfPoints) {
			point.pointWithLowestCost = null;
			point.visited = false;
			if(sourcePoints.contains(point)) {
				point.visited = true;
				totalVisited++;
			}
		}
		for(Point point : listOfPoints) {
			point.aggregateCost = point.aggregateCost * multiplierforaggregatecosts;
		}
		performCalculationForAllPoints();
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
			while(!sourcePoints.contains(currentPoint)) {
				if(currentPoint.pointWithLowestCost != null) {
					currentPoint = currentPoint.pointWithLowestCost;
					shortestPath.add(currentPoint);
				} else {
					ArrayList<Point> connectedPoints = new ArrayList<Point>();
					for(Edge edge : listOfEdges) {
						Point otherPoint = edge.getOtherPoint(currentPoint);
						if(otherPoint != null && !shortestPath.contains(otherPoint)) {
							connectedPoints.add(otherPoint);
						}
					}
					if(connectedPoints.isEmpty()) {
						break;
					}
					Point newPoint = connectedPoints.get(0);
					connectedPoints.remove(0);
					for(Point point : connectedPoints) {
						if(point.aggregateCost < newPoint.aggregateCost) {
							newPoint = point;
						}
					}
					currentPoint = newPoint;
					shortestPath.add(currentPoint);
				}
			}
		}
		return shortestPath;
	}
}
