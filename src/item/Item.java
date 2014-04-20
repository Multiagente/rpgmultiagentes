package item;

/* This class represents the basic and essential attributes of any item in the world of 
 * adventure game. Through this model can form more specialized items, like weapons and armor. 
 * So it is an abstract class. It only intention is to model items does not exist concretely. */
public abstract class Item {

	private String name = "";
	private double weight = 0;
	private double cost = 0;

	public Item(String denomination, double totalWeight) {	
		this.setName(denomination);
		this.setWeight(totalWeight);
	}

	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double value) {
		this.cost = value;
	}

	private void setName(String denomination) {
		this.name = denomination;
	}

	private void setWeight(double totalWeight) {
		this.weight = totalWeight;
	}
}
