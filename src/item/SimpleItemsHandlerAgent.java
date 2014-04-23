package item;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import map.Square;
import map.World;

/* This agent is an administrator of items. More specifically, It manages simple items, 
 * with little differentiation among his peers. As a stone that does not have many differences 
 * from one another stone. This agent is intended to maintain this type of item always 
 * reappearing on the game world. If an item is withdrawn from the place, or destroyed, 
 * this agent will reset this item. */
public class SimpleItemsHandlerAgent extends Agent {

	private static final long serialVersionUID = 1819175160277518404L;
	private static final long TEN_MINUTES = 600000;
	
	protected void setup() {
		addBehaviour(new ReplacementBehaviour(this, TEN_MINUTES));
	}
	
	/* This behavior is meant to check whether a specific item is missing in a given location. 
	 * If it is missing, the item must be replaced. */
	private class ReplacementBehaviour extends TickerBehaviour {
		
		private static final long serialVersionUID = 1L;
		
		private static final boolean MISSING = false;
		private static final boolean PRESENT = true;
		
		private List<Square> squaresWorld = World.getArrayMap();
		private Map<Square, List<Item>> itemsDictionary = new HashMap();
		
		public ReplacementBehaviour(Agent itemHandlerAgent, long period) {
			super(itemHandlerAgent, period);
		}
		
		@Override
		protected void onTick() {
			// TODO: Check if there are any items missing somewhere Square (somewhere in the world).
			// 	TODO: If the item is missing, identify and replace it.
			//	TODO: If not missing, nothing to do.
		}
		
		private void travelTheWorld() {
			// TODO: Through each square in the world.
			//	TODO: In each square, check for any missing item.
			//	TODO: In each square, identify the missing item.
			//	TODO: For each missing item, replace it.
		}
		
		private boolean checkForItems(Square currentSquare) {
			// TODO: Compare the items in each square with the dictionary items.
			// 	TODO: If the item is missing, return MISSING.
			//	TODO: If the item is present, return PRESENT.
			
			return PRESENT;
		}
		
		private void identifyItem(Square currentSquare) {
			
		}
		
		private void replaceItem(Square currentSquare) {
			// TODO: Replace a copy of the item back to the Square
		}
	}
}
