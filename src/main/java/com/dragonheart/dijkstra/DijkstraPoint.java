package com.dragonheart.dijkstra;

public interface DijkstraPoint {
	/**
	 * Returns the cost to enter the point
	 * @return Double with the entry cost
	 */
	public double costToEnter();
	/**
	 * Sets the cost to enter the point
	 * @param cost is a Double of how much it costs to enter the point
	 */
	public void setEntryCost(Double cost);
}
