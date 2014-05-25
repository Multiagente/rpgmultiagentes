package npc;

import map.Square;
import jade.core.Agent;

public abstract class Npc extends Agent {

	private static final long serialVersionUID = 508183949563528426L;

	private Square actualPosition = null;

	public Square getActualPosition() {
		return actualPosition;
	}

	public void setActualPosition(Square actualPosition) {
		this.actualPosition = actualPosition;
	}

}
