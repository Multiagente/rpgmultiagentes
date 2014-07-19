/*
 * File: 	LevelOfChallenge.java
 * Purpose: Brings the implementation of a list that provides the challenge 
 * levels of players.
 */
package br.unb.sma.dd.world.npc;


/**
 * This enumeration allows programmers to more easily identify the level of
 * challenges that players face. There are five different levels of challenge.
 */
public enum LevelOfChallenge {
	
	BASIC( "Basic" ),		// Beginner level of challenge.
	WILD( "Wild" ), 		
	BRAVE( "Brave" ), 	 
	HEROIC( "Heroic" ), 	 
	EPIC( "Epic" );		// Extreme level of challenge.
	
	private String level;
	
	LevelOfChallenge( String level ) {
		this.level = level;
	}
	
	public String getLevelChallenge() {
		return level;
	}
}
