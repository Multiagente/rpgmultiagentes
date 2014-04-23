package npc;

import java.util.Random;

import map.World;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Wolf extends Npc {

	private static final long serialVersionUID = 6562721606710446899L;

	Random random = new Random();

	// This reference is necessary cuz i cannot acces this instance inside inner
	// classes
	public Npc mySelf = this;

	public void setup() {
		// Initialize the map
		World.getMap();
		// Define the initial position to square 0
		this.setActualPosition(World.getArrayMap().get(0));
		// Add a reference of the Npc to the square
		World.getArrayMap().get(0).addNpc(this);
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
}
