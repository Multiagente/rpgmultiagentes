package map;

import java.util.ArrayList;
import java.util.List;

public class World {

	private static World instance = null;
	private static List<Square> arrayMap = new ArrayList<Square>();

	private World() {

	}

	public static List<Square> getArrayMap() {

		return arrayMap;
	}

	public static World getMap() {

		if (instance == null) {
			/*
			 * At this point we can setup our default map it will include the
			 * squares (the number is arbitrary) and all the characteristics of
			 * the square, items, description etc. This is being done by the
			 * method createDefaultMap().
			 */
			instance = new World();
			createDefaultMap();
		}

		return instance;
	}

	public static void addSquare(Square square) {
		World.getArrayMap().add(square);
	}

	private static void createDefaultMap() {
		Square firstRoom = new Square(instance, "1- The Start Place",
				"This is where our hero starts, the central park.");
		Square secondRoom = new Square(instance, "2- Starting the adventure",
				"You are now at a store.");
		Square thirdRoom = new Square(instance, "3- Third place you can go.",
				"You are at the edge of the world.");

		firstRoom.setSouth(secondRoom);
		secondRoom.setNorth(firstRoom);
		secondRoom.setSouth(thirdRoom);
		thirdRoom.setNorth(secondRoom);
	}

}
