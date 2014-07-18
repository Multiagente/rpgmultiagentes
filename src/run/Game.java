package run;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.Iterator;

import br.unb.sma.dd.world.map.Map;
import br.unb.sma.dd.world.map.Square;


public class Game {
	private static final String CHARACTER_AGENT_CLASS = 
			"br.unb.sma.dd.world.character.CharacterAgent";
	
	private static final String RMA_CLASS = "jade.tools.rma.rma";
	
	public static void main( String[] args ) {
		Map map = Map.getMap();
		
		map.initializeMap();
		
		Iterator<Square> iteratorSquares = map.getArrayMap().iterator();
		
		while( iteratorSquares.hasNext() ) {
			System.out.println( iteratorSquares.next().getTitle() );
		}
		
		Runtime gameRunTime = Runtime.instance();
		Profile profile = new ProfileImpl( null, 31000, null );
		AgentContainer mainContainer = gameRunTime.createMainContainer( profile );
		
		try {
			AgentController rma = mainContainer.createNewAgent( "rma",
			      RMA_CLASS, new Object[ 0 ] );
			
			rma.start();
			
			AgentController character1 = mainContainer.createNewAgent( "player1",
			      CHARACTER_AGENT_CLASS, null );
			
			character1.start();
			
			AgentController character2 = mainContainer.createNewAgent( "player2",
					CHARACTER_AGENT_CLASS, null );
			
			character2.start();
			
			AgentController character3 = mainContainer.createNewAgent( "player3",
					CHARACTER_AGENT_CLASS, null );
			
			character3.start();
			
			AgentController character4 = mainContainer.createNewAgent( "player4",
					CHARACTER_AGENT_CLASS, null );
			
			character4.start();
			
		} catch( StaleProxyException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
