package run;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import jade.tools.*;

import java.util.Iterator;

import map.Map;
import map.Square;

public class Game {

	public static void main(String[] args) {
		
		Map map = Map.getMap();
		
		map.initMap();
		
		Iterator<Square> i = map.getArrayMap().iterator();
		while (i.hasNext()) {
			System.out.println(i.next().getTitle());
		}
		
		Runtime rt = Runtime.instance();
		
		Profile p = new ProfileImpl(null, 30000, null);
		AgentContainer cont = rt.createMainContainer(p);
		try {
			AgentController rma = cont.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
			rma.start();
			
			AgentController character1 = cont.createNewAgent("player1", "character.Character" , null);
			character1.start();
			AgentController character2 = cont.createNewAgent("player2", "character.Character" , null);
			character2.start();
			AgentController character3 = cont.createNewAgent("player3", "character.Character" , null);
			character3.start();
			AgentController character4 = cont.createNewAgent("player4", "character.Character" , null);
			character4.start();
			
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}