/*
 * This class is the unit that forms the map.
 */
package br.unb.sma.dd.world.map;


import jade.core.AID;

import java.util.ArrayList;
import java.util.List;

import br.unb.sma.dd.world.item.Item;

/**
 * This class is the unit that forms the map. While the map defines the world 
 * in its totality, the square represents a portion of this world. 
 * Not necessarily all have the same square size.
 */

public class Square {
	
	private String nameSquare;
	private String descriptionSquare;
	private List<Item> itemsSquare;
	private List<AID> charactersAIDSquare;
	private Square north = null;
	private Square south = null;
	private Square east  = null;
	private Square west  = null;
	
	public Square( String title, String description ) {
		
		charactersAIDSquare = new ArrayList<AID>();
		this.nameSquare = title;
		this.descriptionSquare = description;
		
	}
	
	public String getTitle() {
		return nameSquare;
	}
	
	public void setTitle( String title ) {
		this.nameSquare = title;
	}
	
	public String getDescription() {
		return descriptionSquare;
	}
	
	public void setDescription( String description ) {
		this.descriptionSquare = description;
	}
	
	public Square getNorth() {
		return north;
	}
	
	public void setNorth( Square north ) {
		if( this.north == null )
			this.north = north;
	}
	
	public Square getSouth() {
		return south;
	}
	
	public void setSouth( Square south ) {
		if( this.south == null )
			this.south = south;
	}
	
	public Square getEast() {
		return east;
	}
	
	public void setEast( Square east ) {
		if( this.east == null )
			this.east = east;
	}
	
	public Square getWest() {
		return west;
	}
	
	public void setWest( Square west ) {
		if( this.west == null )
			this.west = west;
	}
	
	/**
	 * This method adds an agent in the square.
	 * 
	 * @param character is a user.
	 * @return void.
	 */
	public synchronized void addAgent( AID character ) {
		charactersAIDSquare.add( character );
	}
	
	/**
	 * This method removes an agent in the square.
	 * 
	 * @param character is a user.
	 * @return void.
	 */
	public synchronized void removeCharacter( AID character ) {
		if( charactersAIDSquare.size() > 0 )
			charactersAIDSquare.remove( character );
	}
	
	/**
	 * This method lists the agent in the square.
	 * 
	 * @return List<AID> is a agent's list.
	 */	
	public synchronized List<AID> charactersInside() {
		return charactersAIDSquare;
	}
	
	/**
	 * This method adds an item in the square.
	 * 
	 * @param Item is a new item. 
	 * @return void.
	 */
	public void addItem( Item item ) {
		itemsSquare.add( item );
	}
	
	/**
	 * This method removes an item in the square.
	 * 
	 * @param Item is a item the to be removed. 
	 * @return void.
	 */
	public void removeItem( Item item ) {
		itemsSquare.remove( item );
	}
	
	public List<Item> getItems() {
		return this.itemsSquare;
	}
}
