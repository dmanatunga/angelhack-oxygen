package com.angelhack.oxygen.cah;

public class BlackCard {
	
	private String phrase;
	private Integer numBlanks;
	
	public BlackCard(){
		phrase = "";
		numBlanks = 0;
	}

	public BlackCard(String readString){
		phrase = readString;
		//numBlanks =  phrase.split("_++").length-1;
		numBlanks = countBlanks(phrase);
	}
	
	public Integer countBlanks(String stringToCheck){
		Integer count = 0;
	
		while(stringToCheck.contains("_")){
			count++;
			stringToCheck = stringToCheck.replaceFirst("_++", "");
		}
		return count;
	}
	
	public Integer getNumBlanks(){
		return numBlanks;
	}
	
	public String getPhrase(){
		return phrase;
	}
	public static void main(String[] args){
		System.out.println("Starting Tests");
		
		BlackCard bcTest1 = new BlackCard("This is a _______");
		System.out.println("Number of blanks in \""+bcTest1.getPhrase() + "\": " +  bcTest1.getNumBlanks());
		
		BlackCard bcTest2 = new BlackCard("_______, High Five bro");
		System.out.println("Number of blanks in \""+bcTest2.getPhrase() + "\": " +  bcTest2.getNumBlanks());
		
		BlackCard bcTest3 = new BlackCard("And the academy award for ________, goes to _______");
		System.out.println("Number of blanks in \""+bcTest3.getPhrase() + "\": " +  bcTest3.getNumBlanks());
		
		BlackCard bcTest4 = new BlackCard("____________");
		System.out.println("Number of blanks in \""+bcTest4.getPhrase() + "\": " +  bcTest4.getNumBlanks());
		
		BlackCard bcTest5 = new BlackCard("");
		System.out.println("Number of blanks in \""+bcTest5.getPhrase() + "\": " +  bcTest5.getNumBlanks());
	}
}
