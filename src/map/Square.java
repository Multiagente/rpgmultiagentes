package map;

import item.Item;
import java.util.ArrayList;
import java.util.List;
import npc.Npc;

public class Square {

	private String title;
	private String description;
	private List<Item> items = new ArrayList<Item>();
	private List<Npc> npcs = new ArrayList<Npc>();
	private Square north, south, east, west;
	
	public Square() {
	
	}

	public Square(Map map, String title, String description) {

		this.title = title;
		this.description = description;
		Map.addSquare(this);
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
		this.north = north;
	}

	public Square getSouth() {
		return south;
	}

	public void setSouth(Square south) {
		this.south = south;
	}

	public Square getEast() {
		return east;
	}

	public void setEast(Square east) {
		this.east = east;
	}

	public Square getWest() {
		return west;
	}

	public void setWest(Square west) {
		this.west = west;
	}

	public void addNpc(Npc npc) {
		npcs.add(npc);
	}

	public void removeNpc(Npc npc) {
		npcs.remove(npc);
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
