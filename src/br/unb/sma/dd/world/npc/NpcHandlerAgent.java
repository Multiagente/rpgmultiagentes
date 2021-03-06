/*
 * File:		NpcHandlerAgent.java
 * Purpose: Brings the implementation of agent responsible for controlling 
 * the appearance of NPCs.
 */
package br.unb.sma.dd.world.npc;


import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * This agent is responsible for controlling the appearance of NPCs. 
 * This agent analyzes the existence of NPCs in the world. If there is a 
 * fault it should produce a new NPC to supply the lack.
 */
public class NpcHandlerAgent extends Agent {
	
	private static final long serialVersionUID = 5845864484667102758L;
	
	public void setup() {
		
		DFAgentDescription dfNPC = new DFAgentDescription();
		dfNPC.setName( getAID() );
		
		/* Listing service in the yellow pages. */
		ServiceDescription serviceNPC = new ServiceDescription();
		
		serviceNPC.setType( "Criar NPC" );
		serviceNPC.setName( "Born_NPC" );

		dfNPC.addServices( serviceNPC );
		
		try {
			DFService.register( this, dfNPC );
			
		} catch( FIPAException e ) {
			e.printStackTrace();
		}
	}
}
