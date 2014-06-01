package npc;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

public class CheckController extends CyclicBehaviour {

	/**
	 * Classe que irá conter os comportamentos do NPC
	 */
	private static final long serialVersionUID = 688946409222739002L;

	
	public CheckController() {}

	public void action() {
		/* 
		 * This function checks messages from ControllerNPC
		 * with ACL protocol
		 * */
			
			ACLMessage message = myAgent.receive();
			if (message != null){
				String request = message.getContent();
					if (request.equalsIgnoreCase("Born")) {
					
					}
					
					
					
				}else
					block();
			
			
		}
		
		
	}

