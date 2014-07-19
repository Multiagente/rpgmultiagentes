package br.unb.sma.dd.world.npc;


import jade.core.Agent;

import java.util.List;
import java.util.Random;

import br.unb.sma.dd.world.map.Map;
import br.unb.sma.dd.world.map.Square;


public abstract class NpcAgent extends Agent {
	
	private static final long serialVersionUID = 50;
	
	private Square actualPosition = null;
	protected NpcAgent mySelf;
	private Map map = Map.getMap();
	
	public Square getActualPosition() {
		return actualPosition;
	}
	
	public void setActualPosition( Square actualPosition ) {
		this.actualPosition = actualPosition;
	}
	
	public void create() {
		
		List<Square> options = getPositions( map.getArrayMap() );
		Random generator = new Random();
		int position = generator.nextInt( options.size() );
		// TODO: organizar a inserção de npcs separadamente dos characters
		options.get( position ).addAgent( getAID() );
		System.out.println( "npc created at "
		      + options.get( position ).getTitle() );
	}
	
	public abstract List<Square> getPositions( List<Square> arrayMap );
	
	/** Start agent */
	protected void setup() {
		addBehaviour( new CheckController() );
		
	}
}
