/*
 * File: FactoryNPC.java Purpose: Brings the implementation of a design pattern,
 * a factory to build the type NPC agents.
 */
package br.unb.sma.dd.world.npc;


/**
 * This class is a factory as the default project, which aims to build the type
 * NPC agents.
 */
public class FactoryNPC {
	
	public static FactoryNPC factory;
	
	protected FactoryNPC() {
	}
	
	public static FactoryNPC getFactory() {
		
		if( factory == null ) {
			factory = new FactoryNPC();
			
		} else {
			/* ! Nothing To Do. */
			
		}
		
		return factory;
	}
}
