package com.angelhack.oxygen.cah;

import java.util.ArrayList;
import java.util.Random;

public class WhiteDeck {
	private ArrayList<WhiteCard> whitecards;
	
	public WhiteDeck(String stringFromFile){
		whitecards = new ArrayList<WhiteCard>();
		String[] phrases = stringFromFile.split("<>");
		
		for(int i = 0; i < phrases.length; i++){
			whitecards.add(new WhiteCard(phrases[i]));
		}
	}
	
	public ArrayList<WhiteCard> getWhiteCards(){
		return whitecards;
	}
	
	public ArrayList<WhiteCard> shuffleDeck(ArrayList<WhiteCard> deck){
		ArrayList<WhiteCard> shuffledDeck = new ArrayList<WhiteCard>();
		Random random = new Random();
		while(!deck.isEmpty()){
			shuffledDeck.add(deck.remove(random.nextInt(deck.size())));
		}
		
		return shuffledDeck;
	}
	
	public WhiteCard getCard() {
		WhiteCard wc = whitecards.remove(whitecards.size()-1);
		return wc;
	}
	
	public void returnCard(WhiteCard wc){
		whitecards.add(wc);
		this.shuffleDeck(whitecards);
	}
	
	public void setWhiteCardsArray(ArrayList<WhiteCard> deck){
		whitecards.clear();
		whitecards = (ArrayList<WhiteCard>) deck.clone();
	}
	
	public static void main(String[] args){
		WhiteDeck whitedeck = new WhiteDeck("TSA guidelines now prohibit __________ on airplanes.<>It's a pity that kids these days are all getting involved with __________.<>In 1,000 years, when paper money is but a distant memory, __________ will be our currency.<>Major League Baseball has banned __________ for giving players an unfair advantage.<>What is Batman's guilty pleasure?<>Next from J.K. Rowling: Harry Potter and the Chamber of __________.<>I'm sorry, Professor, but I couldn't complete my homework because of __________.<>What did I bring back from Mexico?<>__________? There's an app for that.<>__________. Betcha can't have just one!<>What's my anti-drug?<>While the United States raced the Soviet Union to the moon, the Mexican government funneled millions of pesos into research on __________.<>In the new Disney Channel Original Movie, Hannah Montana struggles with __________ for the first time. <>What's my secret power?<>What's the new fad diet?<>What did Vin Diesel eat for dinner?<>When Pharaoh remained unmoved, Moses called down a Plague of __________.<>How am I maintaining my relationship status?<>What's the crustiest?<>In L.A. County Jail, word is you can trade 200 cigarettes for __________.<>After the earthquake, Sean Penn brought __________ to the people of Haiti.<>Instead of coal, Santa now gives the bad children __________.<>Life for American Indians was forever changed when the White Man introduced them to __________.<>What's Teach for America using to inspire inner city students to succeed?<>Maybe she's born with it. Maybe it's __________.<>In Michael Jackson's final moments, he thought about __________.<>White people like __________.<>Why do I hurt all over?<>A romantic, candlelit dinner would be incomplete without __________.<>What will I bring back in time to convince people that I am a powerful wizard?<>BILLY MAYS HERE FOR __________.<>The class field trip was completely ruined by __________.<>What's a girl's best friend?<>Dear Abby, I'm having some trouble with __________ and would like your advice.<>When I am President of the United States, I will create the Department of __________.<>What are my parents hiding from me?<>What never fails to liven up the party?<>What gets better with age?<>__________: Good to the last drop.<>I got 99 problems but __________ ain't one.<>__________. It's a trap!<>MTV's new reality show features eight washed-up celebrities living with __________.<>What would grandma find disturbing, yet oddly charming?<>What's the most emo?<>During sex, I like to think about __________.<>What ended my last relationship?<>What's that sound?<>__________. That's how I want to die.<>Why am I sticky?<>What's the next Happy Meal toy?<>What's there a ton of in heaven?<>I do not know with what weapons World War III will be fought, but World War IV will be fought with __________.<>What will always get you laid?<>__________: Kid-tested, mother-approved.<>Why can't I sleep at night?<>What's that smell?<>What helps Obama unwind?<>This is the way the world ends / This is the way the world ends / Not with a bang but with __________.<>Coming to Broadway this season, __________: The Musical.<>Anthropologists have recently discovered a primitive tribe that worships __________.<>But before I kill you, Mr. Bond, I must show you __________.<>Studies show that lab rats navigate mazes 50% faster after being exposed to __________.<>Next on ESPN2: The World Series of __________.<>When I am a billionaire, I shall erect a 50-foot statue to commemorate __________.<>In an attempt to reach a wider audience, the Smithsonian Museum of Natural History has opened an interactive exhibit on __________.<>War! What is it good for?<>What gives me uncontrollable gas?<>What do old people smell like?<>What am I giving up for Lent?<>Alternative medicine is now embracing the curative powers of __________.<>What did the US airdrop to the children of Afghanistan?<>What does Dick Cheney prefer?<>During Picasso's often-overlooked Brown Period, he produced hundreds of paintings of __________.<>What don't you want to find in your Chinese food?<>I drink to forget __________.<>__________. High five, bro.<>He who controls __________ controls the world.<>The CIA now interrogates enemy agents by repeatedly subjecting them to __________.<>In Rome, there are whisperings that the Vatican has a secret room devoted to __________.<>Science will never explain the origin of __________.<>When all else fails, I can always masturbate to __________.<>I learned the hard way that you can't cheer up a grieving friend with __________.<>In its new tourism campaign, Detroit proudly proclaims that it has finally eliminated __________.<>The socialist governments of Scandinavia have declared that access to __________ is a basic human right.<>In his new self-produced album, Kanye West raps over the sounds of __________.<>What's the gift that keeps on giving?<>This season on Man vs. Wild, Bear Grylls must survive in the depths of the Amazon with only __________ and his wits. <>When I pooped, what came out of my butt?<>In the distant future, historians will agree that __________ marked the beginning of America's decline.<>What has been making life difficult at the nudist colony?<>And I would have gotten away with it, too, if it hadn't been for __________.<>What brought the orgy to a grinding halt?");
		
		for(WhiteCard wc : whitedeck.getWhiteCards()){
			System.out.println(wc.getPhrase());
		}
		
		whitedeck.setWhiteCardsArray(whitedeck.shuffleDeck(whitedeck.getWhiteCards()));
		
		System.out.println("");
		System.out.println("");
		System.out.println("Testing Shuffling...");
		for(WhiteCard wc : whitedeck.getWhiteCards()){
			System.out.println(wc.getPhrase());
		}
	}
	
}

