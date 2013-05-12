package com.angelhack.oxygen.cah;

public class Player {
	private Integer score;
	private String name;
	private Hand hand;
	
	public Player(String name, Integer score, Hand hand){
		this.name = name;
		this.score = score;
		this.hand = hand;
	}
	
	public Player(String name, Hand hand){
		this(name, 0, hand);
	}
	
	public Integer getScore(){
		return score;
	}
	
	public void setScore(Integer newScore){
		score = newScore;
	}
	
	public void increaseScore(Integer howMuch){
		score += howMuch;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = new String(newName);
	}
	
	public Hand getHand(){
		return hand;
	}
	
}
