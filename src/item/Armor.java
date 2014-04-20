package item;

/* This class is a specialization of the abstract class Item. Is protective items for 
 * both the player characters and for the NPCs. Can be classified into common, uncommon, 
 * rare and unique (legendary) items. */
public class Armor extends Item {

	private int maxArmorBonus = 0;
	private int penaltyDexterity = 0;
	private int armorFailure = 0;
	private int displacementCapacity = 0;
	private double rateArcaneSpellFailure = 0;
	
	public Armor(String denomination, double totalWeight) {
		super(denomination, totalWeight);
	}

	public int getMaxArmorBonus() {
		return maxArmorBonus;
	}
	
	public int getPenaltyDexterity() {
		return penaltyDexterity;
	}
	
	public int getArmorFailure() {
		return armorFailure;
	}
	
	public int getDisplacementCapacity() {
		return displacementCapacity;
	}
	
	public double getRateArcaneSpellFailure() {
		return rateArcaneSpellFailure;
	}

	public void setMaxArmorBonus(int maxArmorBonus) {
		this.maxArmorBonus = maxArmorBonus;
	}

	public void setPenaltyDexterity(int penaltyDexterity) {
		this.penaltyDexterity = penaltyDexterity;
	}

	public void setArmorFailure(int armorFailure) {
		this.armorFailure = armorFailure;
	}

	public void setDisplacementCapacity(int displacementCapacity) {
		this.displacementCapacity = displacementCapacity;
	}

	public void setRateArcaneSpellFailure(double rateArcaneSpellFailure) {
		this.rateArcaneSpellFailure = rateArcaneSpellFailure;
	}
}
