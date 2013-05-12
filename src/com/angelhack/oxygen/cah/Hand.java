package com.angelhack.oxygen.cah;

import java.util.ArrayList;
import java.util.HashMap;

public class Hand {
	HashMap<String,WhiteCard> hand;
	
	public Hand(ArrayList<WhiteCard> whitecards){
		hand = new HashMap<String,WhiteCard>();
		
		for(WhiteCard wc : whitecards){
			hand.put(wc.getPhrase(),wc);
		}
	}
	
	public HashMap<String,WhiteCard> getHand(){
		return hand;
	}
	public WhiteCard removeCardFromHand(String phrase){
		return hand.remove(phrase);
	}
	
	public void addToHand(WhiteCard wc){
		hand.put(wc.getPhrase(), wc);
	}
}
