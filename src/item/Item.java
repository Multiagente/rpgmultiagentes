package item;

/* This class represents the basic and essential attributes of any item in the world of 
 * adventure game. Through this model can form more specialized items, like weapons and armor. 
 * So it is an abstract class. It only intention is to model items does not exist concretely. */
public abstract class Item {
	
	/* This enumeration helps organize the types of items in relation 
	 * to its rarity in the game world. */
	public enum ClassificationOfItemRarity {
		COMMON("Common"),
		UNCOMMON("Uncommon"),
		RARE("Rare"),
		UNIQUE("Unique");
		
		private final String rarityValue;
		
		ClassificationOfItemRarity(String rarity) {
			this.rarityValue = rarity;
		}
		
		/* Case for viewing in "words" type of rarity of the item is necessary */
		public String getRarityValue() {
			return this.rarityValue;
		}
	}
	
	private ClassificationOfItemRarity itemRarity = ClassificationOfItemRarity.COMMON;
	private String itemName = "";
	private double itemWeight = 0;	// The weight is measured in kilograms
	private double itemCost = 0;	// The cost is measured in Gold Pieces

	public Item(String denomination, double totalWeight) {	
		this.setName(denomination);
		this.setWeight(totalWeight);
	}
	
	public ClassificationOfItemRarity getItemRarity() {
		return itemRarity;
	}

	public String getName() {
		return itemName;
	}
	
	public double getWeight() {
		return itemWeight;
	}
	
	public double getCost() {
		return itemCost;
	}
	
	public void setItemRarity(ClassificationOfItemRarity itemRarity) {
		this.itemRarity = itemRarity;
	}
	
	public void setCost(double value) {
		this.itemCost = value;
	}

	private void setName(String denomination) {
		this.itemName = denomination;
	}

	private void setWeight(double totalWeight) {
		this.itemWeight = totalWeight;
	}
}
