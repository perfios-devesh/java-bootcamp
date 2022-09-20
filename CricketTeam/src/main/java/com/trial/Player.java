package com.trial;

import java.util.Comparator;

class PlayerAverageScoreComparator implements Comparator<Player>{

    @Override
    public int compare(Player player1, Player player2) {
        return (int) (player2.getAverageScore() - player1.getAverageScore());
    }
}


public class Player {
	private int id;
	private String name;
	private int matches;
	private int totalScore;
	private int ducks;
	private int wickets;
	private double averageScore;
	private String playerType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getDucks() {
		return ducks;
	}

	public void setDucks(int ducks) {
		this.ducks = ducks;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	
	

}
