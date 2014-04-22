package item;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/* This agent is an administrator of items. More specifically, It manages simple items, 
 * with little differentiation among his peers. As a stone that does not have many differences 
 * from one another stone. This agent is intended to maintain this type of item always 
 * reappearing on the map. If an item is withdrawn from the place, or destroyed, 
 * this agent will reset this item. */
public class SimpleItemsHandlerAgent extends Agent {

	private static final long serialVersionUID = 1819175160277518404L;
	
	private static final long TEN_MINUTES = 600000;
	
	protected void setup() {
		addBehaviour(new ReplacementBehaviour(this, TEN_MINUTES));
	}
	
	private class ReplacementBehaviour extends TickerBehaviour {
		
		private static final long serialVersionUID = 1L;
		
		public ReplacementBehaviour(Agent itemHandlerAgent, long period) {
			super(itemHandlerAgent, period);
		}
		
		@Override
		protected void onTick() {
			// TODO: implement this		
		}
	}
}
