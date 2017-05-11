package com.dragonheart.test;

import com.dragonheart.dijkstra.DijkstraPoint;

class Point implements DijkstraPoint{
	public double entryCost;
	public Point(double entrycost) {
		this.entryCost = entrycost;
	}
	@Override
	public double costToEnter() {
		return entryCost;
	}
	@Override
	public void setEntryCost(double cost) {
		this.entryCost = cost;
	}
}
