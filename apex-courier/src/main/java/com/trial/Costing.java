package com.trial;

public class Costing {
	private int id;
	private String fromCity;
	private String toCity;
	private int distance;
	private double costPerGram;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public double getCostPerGram() {
		return costPerGram;
	}
	public void setCostPerGram(double costPerGram) {
		this.costPerGram = costPerGram;
	}
}
