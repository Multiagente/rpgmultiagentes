package br.unb.sma.dd.world.item;


/*
 * This class is a generalization of the type of weapon known as sword.
 * Basically, it is an attack item with a blade, specializing in slashing
 * damage.
 */
public class Sword extends Weapon {
	
	public Sword( String denomination, double totalWeight,
	      ClassificationOfRarity rarity ) {
		
		super( denomination, totalWeight, rarity );
		
		this.setTypeOfWeapon( CUTTING );
		this.setIncrementOfDistance( WIHTOUT_INCREMENT_OF_DISTANCE );
	}
}
