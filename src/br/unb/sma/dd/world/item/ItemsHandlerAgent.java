/*
 * File: ItemsHandlerAgent.java 
 * Purpose: This file brings the implementation of the handler items.
 */
package br.unb.sma.dd.world.item;


import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.HashMap;
import java.util.List;

import br.unb.sma.dd.world.map.Map;
import br.unb.sma.dd.world.map.Square;


/**
 * This agent is an administrator of items. More specifically, It manages simple
 * items, with little differentiation among his peers. As a stone that does not
 * have many differences from one another stone. This agent is intended to
 * maintain this type of item always reappearing on the game world. If an item
 * is withdrawn from the place, or destroyed, this agent will reset this item.
 */
public class ItemsHandlerAgent extends Agent {
	
	private static final long serialVersionUID = 1819175160277518404L;
	private static final long INTERVAL_FOR_CHECKING = 600000; // Ten minutes.
	
	protected void setup() {
		addBehaviour( new ReplacementItemBehaviour( this, INTERVAL_FOR_CHECKING ) );
	}
	
	/**
	 * This behavior is meant to check whether a specific item is missing in a
	 * given location. If it is missing, the item must be replaced.
	 */
	private class ReplacementItemBehaviour extends TickerBehaviour {
		
		private static final long serialVersionUID = 5351217842335272255L;
		
		private static final boolean MISSING_ITEM = false;
		private static final boolean PRESENT_ITEM = true;
		private Map map = Map.getMap();
		
		private List<Square> squaresWorld = map.getArrayMap();
		private java.util.Map<Square, List<Item>> itemsCatalog = new HashMap<Square, List<Item>>();
		
		public ReplacementItemBehaviour( Agent itemHandlerAgent, long period ) {
			super( itemHandlerAgent, period );
		}
		
		@Override
		protected void onTick() {
			searchMissingItems();
		}
		
		/**
		 * Through this method go up around the world (by means of squares). This
		 * way you can analyze each of squares and observe if there are any
		 * missing item.
		 * 
		 * @return void
		 */
		private void searchMissingItems() {
			
			for( Square currentSquare : squaresWorld ) {
				boolean verifiedItem = PRESENT_ITEM;
				
				verifiedItem = checkForItems( currentSquare );
				
				if( verifiedItem == PRESENT_ITEM ) {
					/*! Nothing To Do. */
					
				} else {
					// Replace missing item
					replaceItem( currentSquare );
					
				}
			}
		}
		
		/**
		 * Makes checking if there is an item missing from a square. To do so,
		 * makes use of a collection of objects, a Map. Through this map compares
		 * the content that should be in square and what actually is in it.
		 * 
		 * @param currentSquare is a square that handler is there.
		 * @return boolean is a answer if the item exist or not in the square.
		 */
		private boolean checkForItems( Square currentSquare ) {
			
			List<Item> itemsCurrentSquare = currentSquare.getItems();
			boolean verifiedItem = PRESENT_ITEM;
			
			if( itemsCatalog.get( currentSquare ).equals( itemsCurrentSquare ) ) {
				
				verifiedItem = PRESENT_ITEM;
				
			} else {
				verifiedItem = MISSING_ITEM;
			}
			
			return verifiedItem;
		}
		
		private void replaceItem( Square currentSquare ) {
			
			Item missingItem = identifyItem( currentSquare );
			
			currentSquare.addItem( missingItem );
		}
		
		/* Identifies which item is missing from a square. */
		private Item identifyItem( Square currentSquare ) {
			
			List<Item> correctListItems = itemsCatalog.get( currentSquare );
			List<Item> itemsCurrentSquare = currentSquare.getItems();
			
			Item missingItem = null;
			
			for( Item checkingItem : correctListItems ) {
				
				if( itemsCurrentSquare.contains( checkingItem ) ) {
					/*! Nothing To Do */
					
				} else {
					missingItem = checkingItem;
				}
			}
			
			return missingItem;
		}
	}
}
