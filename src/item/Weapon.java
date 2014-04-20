package item;

/* This class is a specialization of the abstract class Item. Represents attack items for 
 * both the player characters and for the NPCs. There are different types of weapons in the 
 * game world and its greatest distinction is between weapons of melee action and 
 * action at a distance. Can be classified into common, uncommon, rare and 
 * unique (legendary) items.*/
public class Weapon extends Item {

	private int damage = 0;
	private int decisiveIncrease = 0;
	private int incrementOfDistance = 0;
	private String typeOfWeapon = "";
	
	public Weapon(String denomination, double totalWeight) {
		super(denomination, totalWeight);
	}

	public int getDamage() {
		return damage;
	}
	
	public int getDecisiveIncrease() {
		return decisiveIncrease;
	}
	
	public int getIncrementOfDistance() {
		return incrementOfDistance;
	}
	
	public String getTypeOfWeapon() {
		return typeOfWeapon;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setDecisiveIncrease(int decisiveIncrease) {
		this.decisiveIncrease = decisiveIncrease;
	}

	public void setIncrementOfDistance(int incrementOfDistance) {
		this.incrementOfDistance = incrementOfDistance;
	}

	public void setTypeOfWeapon(String typeOfWeapon) {
		this.typeOfWeapon = typeOfWeapon;
	}
}
