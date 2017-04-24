package DijkstraAlgorithm;
/**
 * "Graph" is the actual Dijkstra map combined with the logic to calculate the path
 * @author Akhier Dragonheart
 * @version 1.2.2
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	private ArrayList<Vector2D> listOfNodes;
	private HashMap<Vector2D, Integer> sourceNodes;
	private ArrayList<Edge> listOfEdges;
	/**
	 * getter for listOfNodes
	 * @return ArrayList<Vector2D>
	 */
	public ArrayList<Vector2D> allNodes() {
		return listOfNodes;
	}
	/**
	 * getter for sourceNodes
	 * @return HashMap<Vector2D, Integer>
	 */
	public HashMap<Vector2D, Integer> sourceVectorMap() {
		return sourceNodes;
	}
	/**
	 * getter for sourceNodes keys
	 * @return ArrayList<Vector2D>
	 */
	public ArrayList<Vector2D> sourceVectors() {
		if(sourceNodes == null) {
			return null;
		}
		ArrayList<Vector2D> output = new ArrayList<Vector2D>();
		for(Vector2D key : sourceNodes.keySet()) {
			output.add(key);
		}
		return output;
	}
	/**
	 * Sets the sourceNode which is were the map is calculated to travel to
	 * @param value is the Vector2D you want to calculate routes to
	 */
	public void sourceVector(Vector2D value) {
		ArrayList<Vector2D> temp = new ArrayList<Vector2D>();
		temp.add(value);
		sourceVectors(temp);
	}
	/**
	 * Sets the sourceNodes were the map is calculated to travel to
	 * @param values is a list of Vector2D
	 */
	public void sourceVectors(ArrayList<Vector2D> values) {
		sourceNodes = new HashMap<Vector2D, Integer>();
		for(int i = 0; i < listOfNodes.size(); i++) {
			Vector2D node = listOfNodes.get(i);
			if(values.contains(node)) {
				sourceNodes.put(node, 0);
				values.remove(node);
			}
			if(values.isEmpty()) {
				break;
			}
		}
	}
	/**
	 * Sets the sourceNodes and starting values were the map is calculated to travel to
	 * @param values is a HashMap<Vector2D, Integer>
	 */
	public void sourceVectors(HashMap<Vector2D, Integer> values) {
		sourceNodes = new HashMap<Vector2D, Integer>();
		for(int i = 0; i < listOfNodes.size(); i++) {
			Vector2D node = listOfNodes.get(i);
			if(values.containsKey(node)) {
				sourceNodes.put(node, values.get(node));
				values.remove(node);
			}
			if(values.isEmpty()) {
				break;
			}
		}
	}
	/**
	 * Sets the value for a sourceNode
	 * @param node is a Vector2D you want to set the value for
	 * @param value is the value to set node to
	 */
	public void sourceSetVectorsValue(Vector2D node, Integer value) {
		if(sourceNodes.containsKey(node)) {
			sourceNodes.put(node, value);
		}
	}

	public Graph() {
		listOfEdges = new ArrayList<Edge>();
		listOfNodes = new ArrayList<Vector2D>();
		sourceNodes = null;
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
		Vector2D currentNode = null;
		for(Vector2D node : sourceNodes.keySet()) {
			node.setVisited();
		}
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
		if(sourceNodes == null) {
			return false;
		}
		reset();
		for(Map.Entry<Vector2D, Integer> node : sourceNodes.entrySet()) {
			node.getKey().aggregateCost = node.getValue();
		}
		performCalculationForAllNodes();
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
