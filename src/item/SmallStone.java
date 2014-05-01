package item;

/* This class is a specialization of the abstract class Item. Represents the most mundane items 
 * in the game world. Are items such as backpacks, torches, clothing, ropes, tents and food.*/
public class SmallStone extends SimpleItem {
	
	private static final String NAME_OF_STONE = "Small Stone";
	private static final double STONE_WEIGHT = 0.250;
	
	private static SmallStone instanceOfSmallStone = null;
	
	private SmallStone(String denomination, double totalWeight) {
		super(denomination, totalWeight);
		
		this.setItemRarity(ClassificationOfRarity.COMMON);
	}
	
	public SmallStone getInstance() {
		
		if(instanceOfSmallStone != null) {
			// Nothing To Do
			
		} else {
			instanceOfSmallStone = new SmallStone(NAME_OF_STONE, STONE_WEIGHT);
		}
		
		return instanceOfSmallStone;
	}
}
