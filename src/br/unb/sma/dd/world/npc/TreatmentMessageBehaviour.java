/*
 * File: 	CheckControllerBehaviour.java
 * Purpose: -
 */
package br.unb.sma.dd.world.npc;


import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class TreatmentMessageBehaviour extends CyclicBehaviour {
	
	private static final long serialVersionUID = 688946409222739002L;
	
	public TreatmentMessageBehaviour() {
		/*! Empty Constructor. */
	}
	
	/* This function checks messages from ControllerNPC with ACL protocol. */
	public void action() {
		ACLMessage message = myAgent.receive();
		
		if( message != null ) {
			String request = message.getContent();
			
			if( request.equalsIgnoreCase( "Born" ) ) {
				/*! Write Instructions Here. */
				
			} else {
				/*! Nothing To Do. */
			}
			
		} else {
			block();		
		}
	}
}
