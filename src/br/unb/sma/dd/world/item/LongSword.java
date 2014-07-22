/*
 * File: LongSword.java 
 * Purpose: This file brings the implementation of the Long Sword.
 */
package br.unb.sma.dd.world.item;


/**
 * It is a type of sword. It is characterized by having a relatively long blade,
 * reaching 80 centimeters. Their damage is considerable and is a common weapon
 * that most of the characters is capable of handling.
 */
public class LongSword extends Sword {
	private static final int DAMAGE_LONG_SWORD = 8;
	
	public LongSword( String denomination, double totalWeight,
	      ClassificationOfRarity rarity ) {
		
		super( denomination, totalWeight, rarity );
		
		this.setDamage( DAMAGE_LONG_SWORD );
	}
}
