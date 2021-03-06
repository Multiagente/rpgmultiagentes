package br.unb.sma.dd.world.npc;

import java.util.List;
import java.util.Random;

import br.unb.sma.dd.world.map.Map;
import br.unb.sma.dd.world.map.Square;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Wolf extends NpcAgent {

	private static final long serialVersionUID = 6562721606710446899L;

	Random random = new Random();

	// This reference is necessary cuz i cannot acces this instance inside inner
	// classes

	public void setup() {
		// Initialize the map
		Map map = Map.getMap();
		// Define the initial position to square 0
		this.setActualPosition(map.getArrayMap().get(0));
		// Add a reference of the Npc to the square
		//TODO fazer mapa funcionar para npcs
		//map.getArrayMap().get(0).addNpc(this);
		addBehaviour(new MoveBehaviour(this, 3000));
	}

	private class MoveBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = -3495647878417450828L;

		public MoveBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			whereIm();

			if (random.nextInt(2) > 0) {
				if (mySelf.getActualPosition().getSouth() != null) {
					mySelf.setActualPosition(getActualPosition().getSouth());
				} else {
					mySelf.setActualPosition(getActualPosition().getNorth());
				}
			} else {
				if (mySelf.getActualPosition().getNorth() != null) {
					mySelf.setActualPosition(getActualPosition().getNorth());
				} else {
					mySelf.setActualPosition(getActualPosition().getSouth());
				}
			}

		}
	}

	public void whereIm() {
		System.out.println(this.getActualPosition().getTitle());
		System.out.println(this.getActualPosition().getDescription());
		System.out.println("-----------------------");

	}
	
	@Override
	public List<Square> getPositions(List<Square> arrayMap) {
		
		//temp logic here:
		List<Square> options = arrayMap.subList(0, 2);
		return options;
	}
}
