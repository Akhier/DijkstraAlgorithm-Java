package com.dragonheart.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DijkstraGraph {
	private ArrayList<DijkstraPoint[]> listOfConnections;
	private ArrayList<DijkstraPoint> sourcePoints;
	private HashMap<DijkstraPoint, PointData> listOfPoints;
	private int totalVisited = 0;

	private class PointData {
		public Double aggregateCost;
		public DijkstraPoint pointWithLowestCost;
		public boolean visited;

		private PointData() {
			this.aggregateCost = null;
			this.pointWithLowestCost = null;
			this.visited = false;
		}
	}

	public DijkstraGraph() {
		this.listOfConnections = new ArrayList<DijkstraPoint[]>();
		this.listOfPoints = new HashMap<DijkstraPoint, PointData>();
		this.sourcePoints = new ArrayList<DijkstraPoint>();
	}

	protected void clear() {
		this.listOfConnections = new ArrayList<DijkstraPoint[]>();
		this.listOfPoints = new HashMap<DijkstraPoint, PointData>();
		this.sourcePoints = new ArrayList<DijkstraPoint>();
	}

	public void addPoint(DijkstraPoint point) {
		listOfPoints.put(point, new PointData());
	}

	public void addConnection(DijkstraPoint pointA, DijkstraPoint pointB) {
		DijkstraPoint[] connection = {pointA, pointB};
		DijkstraPoint[] otherConnection = {pointB, pointA};
		if(!listOfConnections.contains(connection) && !listOfConnections.contains(otherConnection)) {
			listOfConnections.add(connection);
		}
	}

	public void removeConnection(DijkstraPoint pointA, DijkstraPoint pointB) {
		DijkstraPoint[] connection = {pointA, pointB};
		listOfConnections.remove(connection);
		DijkstraPoint[] otherConnection = {pointB, pointA};
		listOfConnections.remove(otherConnection);
	}

	/**
	 * Add a Point onto the list of sources with a starting cost
	 * @param point is a Point that is the source you want to add
	 * @param cost is a Double with what this source starts at
	 */
	public void addSource(DijkstraPoint point, Double cost) {
		if(listOfPoints.containsKey(point)) {
			listOfPoints.get(point).aggregateCost = cost;
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

	private Point getOtherPoint(Point basepoint, Point[] connection) {
		if(basepoint == connection[0]) {
			return connection[1];
		} else if (basepoint == connection[1]) {
			return connection[0];
		} else {
			return null;
		}
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

	private Queue<Point> getConnectedPoints(Point startpoint) {
		Queue<Point> connectedPoints = new LinkedList<Point>();
		for(Point[] connection : listOfConnections) {
			Point otherPoint = getOtherPoint(startpoint, connection);
			if(otherPoint != null && !otherPoint.visited) {
				connectedPoints.add(otherPoint);
			}
		}
		return connectedPoints;
	}

	private Point getNextBestPoint() {
		Point nextBestPoint = null;
		for(Point visitedPoint : getListOfVisitedPoints()) {
			Queue<Point> connectedPoints = getConnectedPoints(visitedPoint);
			while(connectedPoints.size() > 0) {
				Point otherPoint = connectedPoints.remove();
				if(otherPoint.aggregateCost == null ||
						(visitedPoint.aggregateCost + otherPoint.costToEnter()) < otherPoint.aggregateCost) {
					otherPoint.aggregateCost = visitedPoint.aggregateCost + otherPoint.costToEnter();
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
					for(Point[] connection : listOfConnections) {
						Point otherPoint = getOtherPoint(currentPoint, connection);
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
