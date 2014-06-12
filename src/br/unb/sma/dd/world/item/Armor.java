package br.unb.sma.dd.world.item;

/* This class is a specialization of the abstract class Item. Is protective items for 
 * both the player characters and for the NPCs. Can be classified into common, uncommon, 
 * rare and unique (legendary) items. */
public abstract class Armor extends Item {

	private int maxArmorBonus = 0;
	private int penaltyDexterity = 0;
	private int armorFailure = 0;
	private int displacementCapacity = 0;
	private int armorRarityBonus = 0;
	private double rateArcaneSpellFailure = 0;

	public Armor(String denomination, double totalWeight,
			ClassificationOfRarity rarity) {

		super(denomination, totalWeight);

		this.setArmorRarityBonus(rarity);
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

	public int getArmorRarityBonus() {
		return armorRarityBonus;
	}

	protected void setMaxArmorBonus(int maxArmorBonus) {
		this.maxArmorBonus = maxArmorBonus;
	}

	protected void setPenaltyDexterity(int penaltyDexterity) {
		this.penaltyDexterity = penaltyDexterity;
	}

	protected void setArmorFailure(int armorFailure) {
		this.armorFailure = armorFailure;
	}

	protected void setDisplacementCapacity(int displacementCapacity) {
		this.displacementCapacity = displacementCapacity;
	}

	protected void setRateArcaneSpellFailure(double rateArcaneSpellFailure) {
		this.rateArcaneSpellFailure = rateArcaneSpellFailure;
	}

	private void setArmorRarityBonus(ClassificationOfRarity rarity) {
		switch (rarity) {
			case COMMON:
				this.armorRarityBonus = 0;
				break;

			case UNCOMMON:
				this.armorRarityBonus = 2;
				break;

			case RARE:
				this.armorRarityBonus = 3;
				break;

			case UNIQUE:
				this.armorRarityBonus = 5;
				break;
				
			default:
				this.armorRarityBonus = 0;
		}
	}
}
