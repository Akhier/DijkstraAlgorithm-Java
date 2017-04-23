/**
 * "Graph" is the actual Dijkstra map combined with the logic to calculate the path
 * @author Akhier Dragonheart
 * @version 1.1.0
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Graph {
	private Vector2D sourceNode;
	private ArrayList<Vector2D> listOfNodes;
	private ArrayList<Edge> listOfEdges;
	/**
	 * getter for listOfNodes
	 * @return ArrayList<Vector2D>
	 */
	public ArrayList<Vector2D> allNodes() {
		return listOfNodes;
	}
	/**
	 * getter for sourceNode
	 * @return Vector2D
	 */
	public Vector2D sourceVector() {
		return sourceNode;
	}
	/**
	 * Sets the sourceNode which is were the map is calculated to travel to
	 * @param value is the Vector2D you want to calculate routes to
	 */
	public void sourceVector(Vector2D value) {
		for(int i = 0; i < listOfNodes.size(); i++) {
			if(listOfNodes.get(i) == value) {
				sourceNode = value;
				break;
			}
		}
	}

	/**
	 * init for Graph
	 */
	public Graph() {
		listOfEdges = new ArrayList<Edge>();
		listOfNodes = new ArrayList<Vector2D>();
		sourceNode = null;
	}

	/**
	 * Sets all .visited to false, .aggregateCost to Vector2D.INFINITY, and .edgeWithLowestCost to null
	 * Then sets Vector2D.totalVisited to 0
	 */
	private void reset() {
		for(int i = 0; i < listOfNodes.size(); i++) {
			listOfNodes.get(i).resetVisited();
			listOfNodes.get(i).aggregateCost = Vector2D.INFINITY;
			listOfNodes.get(i).edgeWithLowestCost = null;
		}
		Vector2D.totalVisited = 0;
	}

	/**
	 * Adds the provided Edge to the listOfEdges then does reset()
	 */
	public void addEdge(Edge edge) {
		listOfEdges.add(edge);
		reset();
	}

	/**
	 * Adds an Edge created from the provided Vector2Ds with cost and then does reset()
	 * @param pointa is the Vector2D that is meant to be the 'first' point of this Edge
	 * @param pointb is the Vector2D that is meant to be the 'second' point of this Edge
	 * @param cost is how much traversing this edge costs
	 */
	public void addEdge(Vector2D pointa, Vector2D pointb, int cost) {
		listOfEdges.add(new Edge(pointa, pointb, cost));
		reset();
	}

	/**
	 * Adds the provided Vector2D to the listOfNodes then does reset()
	 * @param node
	 */
	public void addVector(Vector2D node) {
		listOfNodes.add(node);
		reset();
	}

	/**
	 * Adds a Vector2D created from the provided x, y, and deadendedness then does reset()
	 * @param x an int that represents it's position on the x axis
	 * @param y an int that represents it's position on the y axis
	 * @param deadend is a boolean of whether this point is a deadend
	 */
	public void addVector(int x, int y, boolean deadend) {
		listOfNodes.add(new Vector2D(x, y, deadend));
		reset();
	}

	private ArrayList<Vector2D> getListOfVisitedNodes() {
		ArrayList<Vector2D> listOfVisitedNodes = new ArrayList<Vector2D>();
		for(Vector2D node : listOfNodes) {
			if(node.visited()) {
				listOfVisitedNodes.add(node);
			}
		}
		return listOfVisitedNodes;
	}

	private PriorityQueue<Edge> getConnectedEdges(Vector2D startnode) {
		PriorityQueue<Edge> connectedEdges = new PriorityQueue<Edge>();
		for(int i = 0; i < listOfEdges.size(); i++) {
			Vector2D otherNode = listOfEdges.get(i).getOtherVector(startnode);
			if(otherNode != null && !otherNode.visited()) {
				connectedEdges.add(listOfEdges.get(i));
			}
		}
		return connectedEdges;
	}

	private void performCalculationForAllNodes() {
		Vector2D currentNode = sourceNode;
		currentNode.setVisited();
		do {
			currentNode = getNextBestNode();
			currentNode.setVisited();
		} while(Vector2D.totalVisited < listOfNodes.size());
	}

	private Vector2D getNextBestNode() {
		Vector2D nextBestNode = null;
		for(Vector2D visitedNode : getListOfVisitedNodes()) {
			PriorityQueue<Edge> connectedEdges = getConnectedEdges(visitedNode);
			while(connectedEdges.size() > 0) {
				Edge connectedEdge = connectedEdges.remove();
				Vector2D otherNode = connectedEdge.getOtherVector(visitedNode);
				if(otherNode.aggregateCost == Vector2D.INFINITY ||
						(visitedNode.aggregateCost + connectedEdge.cost) < otherNode.aggregateCost) {
					otherNode.aggregateCost = visitedNode.aggregateCost + connectedEdge.cost;
					otherNode.edgeWithLowestCost = connectedEdge;
				}
				if(nextBestNode == null || otherNode.aggregateCost < nextBestNode.aggregateCost) {
					nextBestNode = otherNode;
				}
			}
		}
		return nextBestNode;
	}

	/**
	 * @return boolean of if the calculation succeeded
	 */
	public boolean calculateShortestPath() {
		boolean unreachable = false;
		if(sourceNode == null) {
			return false;
		}
		reset();
		sourceNode.aggregateCost = 0;
		performCalculationForAllNodes();
		if(unreachable) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the shortest path from targetnode to sourceNode
	 * @param targetnode is the Vector2D to calculate the route to the sourceNode from
	 * @return ArrayList<Vector2D> with the path to sourceNode from targetnode
	 */
	public ArrayList<Vector2D> retrieveShortestPath(Vector2D targetnode) {
		ArrayList<Vector2D> shortestPath = new ArrayList<Vector2D>();
		if(targetnode != null) {
			Vector2D currentNode = targetnode;
			shortestPath.add(currentNode);
			while(currentNode.edgeWithLowestCost != null) {
				currentNode = currentNode.edgeWithLowestCost.getOtherVector(currentNode);
				shortestPath.add(currentNode);
			}
		}
		Collections.reverse(shortestPath);
		return shortestPath;
	}
}
