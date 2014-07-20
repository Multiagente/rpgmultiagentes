/*
 * File:		NpcAgent.java
 * Purpose: Brings the implementation of agent that represents a generalization 
 * of non-player characters in the world.
 */
package br.unb.sma.dd.world.npc;


import jade.core.Agent;

import java.util.List;
import java.util.Random;

import br.unb.sma.dd.world.map.Map;
import br.unb.sma.dd.world.map.Square;


/**
 * This class is a generalization of non-player characters.
 */
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
	
	public abstract List<Square> getPositions( List<Square> arrayMap );
	
	public void create() {
		
		List<Square> optionsSquare = getPositions( map.getArrayMap() );
		Random randomGenerator = new Random();
		int selectedPosition = randomGenerator.nextInt( optionsSquare.size() );
		
		/* TODO: Organize the insertion of NPCs separately from characters. */
		
		optionsSquare.get( selectedPosition ).addAgent( getAID() );
		
		System.out.println( "npc created at "
		      + optionsSquare.get( selectedPosition ).getTitle() );
	}
	
	protected void setup() {
		addBehaviour( new TreatmentMessageBehaviour() );
		
	}
}
