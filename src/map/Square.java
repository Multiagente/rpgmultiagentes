package map;

import item.Item;
import jade.core.AID;

import java.util.ArrayList;
import java.util.List;

public class Square {

	private String title;
	private String description;
	private List<Item> items;
	private List<AID> charsAID;
	private Square north, south, east, west = null;

	public Square(String title, String description) {

		charsAID = new ArrayList<AID>();
		this.title = title;
		this.description = description;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Square getNorth() {
		return north;
	}

	public void setNorth(Square north) {
		if (this.north == null)
			this.north = north;
	}

	public Square getSouth() {
		return south;
	}

	public void setSouth(Square south) {
		if (this.south == null)
			this.south = south;
	}

	public Square getEast() {
		return east;
	}

	public void setEast(Square east) {
		if (this.east == null)
			this.east = east;
	}

	public Square getWest() {
		return west;
	}

	public void setWest(Square west) {
		if (this.west == null)
			this.west = west;
	}

	// metodos para adicionar e retirar da lista os players que estão na sala..
	public synchronized void addChar(AID character) {
		charsAID.add(character);
	}

	public synchronized void removeChar(AID character) {
		if(charsAID.size()>0)
			charsAID.remove(character);
	}

	public synchronized List<AID> charsInside() {
		return charsAID;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public List<Item> getItems() {
		return this.items;
	}
}
