package item;

/* This class is a specialization of the abstract class Item. Represents the most mundane items 
 * in the game world. Are items such as backpacks, torches, clothing, ropes, tents and food. */
public class AdventureEquipament extends Item {
	
	public AdventureEquipament(String denomination, double totalWeight) {
		super(denomination, totalWeight);
	}
}
