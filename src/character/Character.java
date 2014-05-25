package character;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREInitiator;
import jade.proto.AchieveREResponder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import map.Map;
import map.Square;

public class Character extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3380309901133173161L;

	private Square positionNow = null;
	private Square nextPosition = null;
	public Character mySelf = this;

	Map map = null;

	public void setPosition(Square sqr) {
		this.positionNow = sqr;
	}

	public Square getPosition() {
		return this.positionNow;
	}

	public Character getChar() {
		return this.mySelf;
	}

	public void setup() {

		map = Map.getMap();
		
		setPosition(map.getArrayMap().get(0));
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("MessageService");
		sd.setName("Mail");
		dfd.addServices(sd);
		getPosition().addChar(this.getAID());

		try {
			DFService.register(this, dfd);

		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		
		addBehaviour(new Move(this,500));
		addBehaviour(new HandleRequest());
	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	private class Move extends TickerBehaviour{


		/**
		 * 
		 */
		private static final long serialVersionUID = -679586132985982801L;

		public Move(Agent a, long period) {
			super(a, period);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onTick() {
			exitRoom();
		}	
	}
	
	private class HandleRequest extends OneShotBehaviour{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6230563887514017233L;
		
		@Override
		public void action() {
			MessageTemplate template = MessageTemplate.and(
			  		MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
			  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
			  		
					mySelf.addBehaviour(new AchieveREResponder(mySelf, template) {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException {
							System.out.println("Agent "+getLocalName()+": REQUEST received from "+request.getSender().getName()+". Action is "+request.getContent());
							if (checkAction()) {
								// We agree to perform the action. Note that in the FIPA-Request
								// protocol the AGREE message is optional. Return null if you
								// don't want to send it.
								System.out.println("Agent "+getLocalName()+": Agree");
								ACLMessage agree = request.createReply();
								agree.setPerformative(ACLMessage.AGREE);
								return agree;
							}
							else {
								// We refuse to perform the action
								System.out.println("Agent "+getLocalName()+": Refuse");
								throw new RefuseException("check-failed");
							}
						}
						
						protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
							if (performAction()) {
								System.out.println("Agent "+getLocalName()+": Action successfully performed");
								ACLMessage inform = request.createReply();
								inform.setPerformative(ACLMessage.INFORM);
								return inform;
							}
							else {
								System.out.println("Agent "+getLocalName()+": Action failed");
								throw new FailureException("unexpected-error");
							}	
						}
					} );
			  }

		private boolean checkAction() {
			//TODO adicionar verificação para as possíveis ações dos agentes, casos estejam ocupados.
			// Simulate a check by generating a random number
			return (Math.random() > 0.2);
		}

		private boolean performAction() {
		  	// Simulate action execution by generating a random number
		  	return (Math.random() > 0.2);
		  }
			}
		
	private void exitRoom() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		List<AID> listChars = getPosition().charsInside();
		if(listChars.size()>1){
			Iterator<AID> i = listChars.iterator();
			while(i.hasNext()){
				AID receiver = i.next();
				if(this.getAID()!=receiver)
					msg.addReceiver(receiver);
			}
		}
		msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msg.setReplyByDate(new Date(System.currentTimeMillis() + 1000));
		msg.setContent("leave");
		this.send(msg);
		System.out.println("Agent successfully performed the requested action");
		addBehaviour(new AchieveREInitiator(this, msg) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1202650982398071473L;
			
			boolean canLeave = true;
			
			protected void handleInform(ACLMessage inform) {
				System.out.println("Agent " + inform.getSender().getName() + "opose.");
				//TODO iniciar ação de luta.
				canLeave = false;
			}

			protected void handleRefuse(ACLMessage refuse) {
				System.out.println("Agent " + refuse.getSender().getName()+ " refused "+ myAgent.getName()+" msg." );
			}

			protected void handleFailure(ACLMessage failure) {
				if (failure.getSender().equals(myAgent.getAMS())) {
					System.out.println("This guy might be dead.");
				} else {
					System.out.println("Agent " + failure.getSender().getName()
							+ " failed to perform the requested action");
				}
			}

			protected void handleAllResultNotifications(Vector notifications) {
				System.out.println("Timeout expired: missing "
						+ (notifications.size()) + " responses");
				//TODO
				if(canLeave){
					try {
						updatePosition(nextMove());
					} catch (FIPAException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void updatePosition(Square newPos) throws FIPAException {

		Square sqr = getPosition();
		sqr.removeChar(this.getAID());
		setPosition(newPos);
		sqr = getPosition();
		sqr.addChar(this.getAID());
		System.out.println(newPos.getDescription());
	}

	private Square nextMove() {

		Square sqr = getPosition();
		List<Square> possibleMoves = new ArrayList<Square>();

		if (sqr.getNorth() != null){
			possibleMoves.add(sqr.getNorth());
		}
		if (sqr.getSouth() != null){
			possibleMoves.add(sqr.getSouth());
		}
		if (sqr.getEast() != null){
			possibleMoves.add(sqr.getEast());
		}
		if (sqr.getWest() != null){
			possibleMoves.add(sqr.getWest());
		}

		Square newPos = possibleMoves.get(((int) Math.random()*100)
				% possibleMoves.size());

		
		return newPos;
	}

	public Square getNextPosition() {
		return nextPosition;
	}

	public void setNextPosition(Square nextPosition) {
		this.nextPosition = nextPosition;
	}

}