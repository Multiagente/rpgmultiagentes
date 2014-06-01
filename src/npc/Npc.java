package npc;

import java.util.List;
import java.util.Random;

import map.Map;
import map.Square;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public abstract class Npc extends Agent {

	private static final long serialVersionUID = 50;

	private Square actualPosition = null;
	protected Npc mySelf;
	private LevelOfChallenge level;
	
	
	public Square getActualPosition() {
		return actualPosition;
	}
	
	
	public void setActualPosition(Square actualPosition) {
		this.actualPosition = actualPosition;
	}
	
	public void create(){
		
		List<Square> options = getPositions(Map.getArrayMap());
		Random generator = new Random();
		int position = generator.nextInt(options.size());
		options.get(position).addNpc(mySelf);
		System.out.println("npc created at "+ options.get(position).getTitle());
	}

	public abstract List<Square> getPositions(List<Square> arrayMap);

	/**Start agent */
	protected void  setup() {
		addBehaviour(new CheckController());
		
	}
}
