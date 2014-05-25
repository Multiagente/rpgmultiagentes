package map;

import java.util.ArrayList;
import java.util.List;

public class Map {

	private static Map instance = null;
	private List<Square> arrayMap;

	private Map() {
		arrayMap = new ArrayList<Square>();
	}

	public synchronized List<Square> getArrayMap() {

		return arrayMap;
	}

	public static Map getMap() {

		if (instance == null) {
			/*
			 * At this point we can setup our default map it will include the
			 * squares (the number is arbitrary) and all the characteristics of
			 * the square, items, description etc.
			 */
			instance = new Map();
		}

		return instance;
	}

	public void addSquare(Square square) {
		getArrayMap().add(square);
	}

	public void initMap(){

		Square firstRoom = new Square("The Start Place",
				"This is where our hero starts, the central park.");
		Square secondRoom = new Square("Starting the adventure",
				"You are now at a store.");
		Square thirdRoom = new Square("Third place you can go.",
				"You are at the edge of the world.");
		Square fourthRoom = new Square("ocean.",
				"You are at the final destination.");
		
		addSquare(firstRoom);
		addSquare(secondRoom);
		addSquare(thirdRoom);
		addSquare(fourthRoom);
		
		firstRoom.setNorth(secondRoom);
		secondRoom.setSouth(firstRoom);
		secondRoom.setEast(thirdRoom);
		thirdRoom.setWest(secondRoom);
		thirdRoom.setSouth(fourthRoom);
		fourthRoom.setNorth(firstRoom);
		firstRoom.setSouth(fourthRoom);
		
	}
	
}
