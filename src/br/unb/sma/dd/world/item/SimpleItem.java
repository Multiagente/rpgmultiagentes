package br.unb.sma.dd.world.item;


/*
 * This class has a simply organizational meaning. Serves only to classify a
 * given set of items as a more general category.
 */
public abstract class SimpleItem extends Item {
	
	public SimpleItem( String denomination, double totalWeight ) {
		super( denomination, totalWeight );
	}
}
