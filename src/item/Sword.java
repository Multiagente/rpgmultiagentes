package item;

public class Sword extends Weapon {

	public Sword(String denomination, double totalWeight,
			ClassificationOfRarity rarity) {
		
		super(denomination, totalWeight, rarity);
		
		this.setTypeOfWeapon(CUTTING);
		this.setIncrementOfDistance(WIHTOUT_INCREMENT_OF_DISTANCE);
	}
}
