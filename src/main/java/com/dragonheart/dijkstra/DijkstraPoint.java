package com.dragonheart.dijkstra;

public interface DijkstraPoint {
	/**
	 * Returns the cost to enter the point
	 * @return Double with the entry cost
	 */
	public Double costToEnter();
	public void setEntryCost(Double cost);
}
