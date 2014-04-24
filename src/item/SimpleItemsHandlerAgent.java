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

	/* This behavior is meant to check whether a specific item is missing in a
	 * given location. If it is missing, the item must be replaced.
	 */
	private class ReplacementBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = 1L;

		private static final boolean MISSING_ITEM = false;
		private static final boolean PRESENT_ITEM = true;

		private List<Square> squaresWorld = World.getArrayMap();
		private Map<Square, List<Item>> itemsDictionary = new HashMap<Square, List<Item>>();

		public ReplacementBehaviour(Agent itemHandlerAgent, long period) {
			super(itemHandlerAgent, period);
		}

		@Override
		protected void onTick() {
			searchMissingItems();
		}

		/* Through this method go up around the world (by means of squares).
		 * This way you can analyze each of squares and observe if there are any
		 * missing item. */
		private void searchMissingItems() {

			for (Square currentSquare : squaresWorld) {
				boolean verifiedItem = PRESENT_ITEM;

				verifiedItem = checkForItems(currentSquare);

				if (verifiedItem == PRESENT_ITEM) {
					// Nothing To Do

				} else if (verifiedItem == MISSING_ITEM) {
					replaceItem(currentSquare);

				} else {
					// Nothing To Do
				}
			}
		}

		private boolean checkForItems(Square currentSquare) {

			return PRESENT_ITEM;
		}
		
		private void replaceItem(Square currentSquare) {
			
		}
	}
}
