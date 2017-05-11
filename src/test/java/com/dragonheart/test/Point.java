package com.dragonheart.test;

import com.dragonheart.dijkstra.DijkstraPoint;

class Point implements DijkstraPoint{
	public Double entryCost;
	public Point(Double entrycost) {
		this.entryCost = entrycost;
	}
	@Override
	public double costToEnter() {
		return entryCost;
	}
	@Override
	public void setEntryCost(Double cost) {
		this.entryCost = cost;
	}
}
