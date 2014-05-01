package item;

/* This class is a specialization of the abstract class Item. Represents attack items for 
 * both the player characters and for the NPCs. There are different types of weapons in the 
 * game world and its greatest distinction is between weapons of melee action and 
 * action at a distance. Can be classified into common, uncommon, rare and 
 * unique (legendary) items. */
public abstract class Weapon extends Item {
	
	// Types of weapons.
	protected static final String CUTTING = "Cortante";
	protected static final String PIERCING = "Perfurante";
	protected static final String CONCUSSION = "Concussão";

	// Standard distances for increment of weapons ranged attack.
	protected static final int WIHTOUT_INCREMENT_OF_DISTANCE = 0;
	protected static final int THREE_METERS = 3;
	protected static final int NINE_METERS = 9;
	protected static final int EIGHTEEN_METERS = 18;
	protected static final int TWENTY_ONE_METERS = 20;
	protected static final int TWENTY_FOUR_METERS = 24;
	protected static final int THIRTY_METERS = 30;
	protected static final int THIRTY_THREE_METERS = 33;
	protected static final int THIRTY_SIX_METERS = 36;

	private int damage = 0;
	private int decisiveIncrease = 0;
	private int incrementOfDistance = 0; // in meters.
	private int weaponRarityBonus = 0;
	private String weaponType = "";

	public Weapon(String denomination, double totalWeight,
			ClassificationOfRarity rarity) {
		
		super(denomination, totalWeight);
		
		this.setWeaponRarityBonus(rarity);
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
		return weaponType;
	}

	public int getWeaponRarityBonus() {
		return weaponRarityBonus;
	}

	protected void setDamage(int damage) {
		this.damage = damage;
	}

	protected void setDecisiveIncrease(int decisiveIncrease) {
		this.decisiveIncrease = decisiveIncrease;
	}

	protected void setIncrementOfDistance(int incrementOfDistance) {
		this.incrementOfDistance = incrementOfDistance;
	}

	protected void setTypeOfWeapon(String typeOfWeapon) {
		this.weaponType = typeOfWeapon;
	}

	private void setWeaponRarityBonus(ClassificationOfRarity rarity) {
		switch(rarity) {
			case COMMON:
				this.weaponRarityBonus = 0;
				break;

			case UNCOMMON:
				this.weaponRarityBonus = 2;
				break;

			case RARE:
				this.weaponRarityBonus = 3;
				break;

			case UNIQUE:
				this.weaponRarityBonus = 5;
				break;
			
			default:
				this.weaponRarityBonus = 0;
		}
	}
}
