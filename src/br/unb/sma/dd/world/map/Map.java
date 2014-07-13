/*
 * File: Map.java 
 * Purpose: This file refers to the map.
 */
package br.unb.sma.dd.world.map;


import java.util.ArrayList;
import java.util.List;


/**
 * This class is a representation of the world. Through it, it's possible get
 * around and set the position of the objects in the world.
 */
public class Map {
	
	private static Map instanceMap = null;
	private List<Square> arrayMap;
	
	private Map() {
		arrayMap = new ArrayList<Square>();
	}
	
	public synchronized List<Square> getArrayMap() {
		return arrayMap;
	}
	
	public static Map getMap() {
		
		if( instanceMap == null ) {
			/*
			 * At this point we can setup our default map it will include the
			 * squares (the number is arbitrary) and all the characteristics of the
			 * square, items, description etc.
			 */
			instanceMap = new Map();
		}
		
		return instanceMap;
	}
	
	/**
	 * This method adds a square on the map.
	 * 
	 * @param square is a new square on the map.
	 * @return void.
	 */
	public void addSquare( Square square ) {
		getArrayMap().add( square );
	}
	
	/**
	 * This method initializes the map.
	 * 
	 * @return void.
	 */
	public void initializeMap() {
		
		Square firstRoom = new Square( "The Start Place",
		      "This is where our hero starts, the central park." );
		Square secondRoom = new Square( "Starting the adventure",
		      "You are now at a store." );
		Square thirdRoom = new Square( "Third place you can go.",
		      "You are at the edge of the world." );
		Square fourthRoom = new Square( "ocean.",
		      "You are at the final destination." );
		
		addSquare( firstRoom );
		addSquare( secondRoom );
		addSquare( thirdRoom );
		addSquare( fourthRoom );
		
		firstRoom.setNorth( secondRoom );
		secondRoom.setSouth( firstRoom );
		secondRoom.setEast( thirdRoom );
		thirdRoom.setWest( secondRoom );
		thirdRoom.setSouth( fourthRoom );
		fourthRoom.setNorth( firstRoom );
		firstRoom.setSouth( fourthRoom );
		
	}
	
}
