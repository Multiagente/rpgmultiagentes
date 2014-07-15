/* 
 * File:	Character.java
 * Purpose: This is a agent that represents a character in the game.
 */
package br.unb.sma.dd.world.character;

import jade.core.AID;
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

import br.unb.sma.dd.world.map.Map;
import br.unb.sma.dd.world.map.Square;

/*
 *This class is a specialization of the abstract class Agent. 
 *It holds the static and behavioral properties of the game's character. 
 */

public class CharacterAgent extends Agent {
	private static final long serialVersionUID = 3380309901133173161L;
	public CharacterAgent mySelf = this;
	public Map map = null;
	private Square positionNow = null;
	private Square nextPosition = null;
	private int healthPoints = 100;
	private int defensePoints = 2;
	private int attackPoints = 20;
	private int experiencePoints = 10;

	public void setPosition( Square sqr ) {
		this.positionNow = sqr;
	}

	public Square getPosition() {
		return this.positionNow;
	}

	public int getHealthPoints() {
		return this.healthPoints;
	}
	
	public void setHealthPoints( int value ){
		this.healthPoints = value;
	}
	
	public Square getNextPosition() {
		return nextPosition;
	}

	public void setNextPosition( Square nextPosition ) {
		this.nextPosition = nextPosition;
	}
	
	public CharacterAgent getChar() {
		return this.mySelf;
	}

	public void setup() {
		map = Map.getMap();
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		
		setPosition( map.getArrayMap().get( 0 ) );
		dfd.setName( getAID() );
		sd.setType( "MessageService" );
		sd.setName( "Mail" );
		dfd.addServices( sd );
		getPosition().addAgent( this.getAID() );
		try {
			DFService.register( this, dfd );

		} catch( FIPAException fe ) {
			fe.printStackTrace();
		}
		//addBehaviour(new Move(this,500));
		//addBehaviour(new HandleMoveRequest());
		addBehaviour( new LookForFight( this, 2000 ) );
		addBehaviour( new HandleFightRequest() );
	}

	protected void takeDown() {
		try {
			DFService.deregister( this );
		} catch ( FIPAException fe ) {
			fe.printStackTrace();
		}
	}
		
	private void exitRoom() {
		ACLMessage msg = new ACLMessage( ACLMessage.REQUEST );
		List<AID> listChars = getPosition().charactersInside();
		
		if( listChars.size() > 1 ){
			Iterator<AID> i = listChars.iterator();
			while( i.hasNext() ){
				AID receiver = i.next();
				if( this.getAID()!=receiver )
					msg.addReceiver( receiver );
			}
		}
		msg.setProtocol( FIPANames.InteractionProtocol.FIPA_REQUEST );
		msg.setReplyByDate( new Date( System.currentTimeMillis() + 1000 ) );
		msg.setContent( "leave" );
		this.send( msg );
		addBehaviour( new AchieveREInitiator( this, msg ) {
			private static final long serialVersionUID = 1202650982398071473L;
			boolean canLeave = true;
			
			protected void handleInform( ACLMessage inform ) {
				System.out.println( "Agent " + inform.getSender().getName() + 
						" opose your left." );
				canLeave = false;
			}

			protected void handleRefuse( ACLMessage refuse ) {
				System.out.println( "Agent " + refuse.getSender().getName() + 
						" refused " + myAgent.getName() + " msg." );
			}

			protected void handleFailure( ACLMessage failure ) {
				if ( failure.getSender().equals( myAgent.getAMS() )) {
					System.out.println( "This guy might be dead." );
				} else {
					System.out.println( "Agent " + failure.getSender().getName()
							+ " failed to perform the requested action" );
				}
			}

			protected void handleAllResultNotifications( Vector notifications ) {
				System.out.println( "Timeout expired: missing "
						+ ( notifications.size() ) + " responses" );
				if( canLeave ){
					try {
						updatePosition( nextMove() );
					} catch ( FIPAException e ) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void fight( AID target ) {
		ACLMessage msg = new ACLMessage( ACLMessage.REQUEST );
		AID receiver = target;
		
		if( this.getAID()!=receiver )
			msg.addReceiver( receiver );
		msg.setProtocol( FIPANames.InteractionProtocol.FIPA_REQUEST );
		msg.setReplyByDate( new Date( System.currentTimeMillis() + 1000 ) );
		msg.setContent( String.valueOf( getAttackPoints() ) );
		this.send( msg );
		
		addBehaviour(new AchieveREInitiator( this, msg ) {
			private static final long serialVersionUID = 1202650982398071473L;
			
			protected void handleInform( ACLMessage inform ) {
				/* Receive experience points in the end of each attack.*/
				
				int exp =( int ) ( Integer.parseInt( inform.getContent() ) / getExperiencePoints() );
				System.out.println( "["+getLocalName()+"]: got "+ exp +
						" experience points from [" + inform.getSender().getLocalName() + "]" );
				updateExperiencePoints( exp );
			}

			protected void handleAllResultNotifications( Vector notifications ) {
				//System.out.println("[FIGHT:"+getLocalName()+"] Timeout expired: missing "	+ (notifications.size()) + " responses");
			}
		});
	}
	
	private int getAttackPoints() {
		return this.attackPoints;
	}

	private int getExperiencePoints() {
		return this.experiencePoints;
	}

	private void updateExperiencePoints( int value ) {
		this.experiencePoints += value;		
	}
	
	private int getDefensePoints() {
		return this.defensePoints;
	}
	
	private void setDefensePoints( int value ) {
		this.defensePoints = value;
	}
	
	private void updatePosition( Square newPos ) throws FIPAException {
		Square sqr = getPosition();
		sqr.removeCharacter( this.getAID() );
		setPosition( newPos );
		sqr = getPosition();
		sqr.addAgent( this.getAID() );
		System.out.println( newPos.getDescription() );
	}
	
	private void damageHealthPoints( int value ) {
		if( getHealthPoints() < 0 ){
			setHealthPoints( 0 );
			getPosition().removeCharacter( this.getAID() );
			this.doDelete();
		}
		else
			setHealthPoints( getHealthPoints() - ( value-defensePoints ) );
	}

	private Square nextMove() {
		Square sqr = getPosition();
		List<Square> possibleMoves = new ArrayList<Square>();

		if (sqr.getNorth() != null){
			possibleMoves.add( sqr.getNorth() );
		}
		if (sqr.getSouth() != null){
			possibleMoves.add( sqr.getSouth() );
		}
		if (sqr.getEast() != null){
			possibleMoves.add( sqr.getEast() );
		}
		if (sqr.getWest() != null){
			possibleMoves.add( sqr.getWest() );
		}
		Square newPos = possibleMoves.get( ( (int) Math.random()*100)
				% possibleMoves.size() );
		return newPos;
	}

	private class Move extends TickerBehaviour{
		private static final long serialVersionUID = -679586132985982801L;

		public Move( Agent a, long period ) {
			super( a, period );
		}

		@Override
		protected void onTick() {
			exitRoom();
		}	
	}

	private class LookForFight extends TickerBehaviour{
		private static final long serialVersionUID = 4461404912449987804L;
		
		public LookForFight( Agent a, long period ) {
			super( a, period );
		}

		@Override
		protected void onTick() {
			AID id;
			List<AID> listChars = getPosition().charactersInside();
			id = listChars.get( (int) ( Math.random() * 1000 ) % listChars.size() );
			fight( id );
		}
	}
	
	private class HandleMoveRequest extends OneShotBehaviour{
		private static final long serialVersionUID = 6230563887514017233L;
		
		@Override
		public void action() {
			MessageTemplate template = MessageTemplate.and(
			  		MessageTemplate.MatchProtocol( FIPANames.InteractionProtocol.FIPA_REQUEST ),
			  		MessageTemplate.MatchPerformative( ACLMessage.REQUEST ) );
			mySelf.addBehaviour( new AchieveREResponder( mySelf, template ) {
				private static final long serialVersionUID = 1L;

				protected ACLMessage prepareResponse( ACLMessage request ) 
						throws NotUnderstoodException, RefuseException {
					System.out.println( "Agent " + getLocalName() + ": REQUEST received from " +
					request.getSender().getName() + ". Action is " + request.getContent() );
					if ( checkAction() ) {
						/*
						 * We agree to perform the action. Note that in the FIPA-Request
						 * protocol the AGREE message is optional. Return null if you
					 	 * don't want to send it.
						*/
						
						System.out.println( "Agent " + getLocalName() + ": Agree" );
						ACLMessage agree = request.createReply();
						agree.setPerformative( ACLMessage.AGREE );
						return agree;
					}
					else {
						// We refuse to perform the action
						System.out.println( "Agent " + getLocalName() + ": Refuse" );
						throw new RefuseException( "check-failed" );
					}
				}
				
				protected ACLMessage prepareResultNotification( ACLMessage request, 
						ACLMessage response ) throws FailureException {
					if ( performAction() ) {
						System.out.println( "Agent " + getLocalName() + ": Action successfully performed" );
						ACLMessage inform = request.createReply();
						inform.setPerformative( ACLMessage.INFORM );
						return inform;
					}
					else {
						System.out.println( "Agent " + getLocalName() + ": Action failed" );
						throw new FailureException( "unexpected-error" );
					}	
				}
			} );
		}

		private boolean checkAction() {
		   /* Adicionar verificação para as possíveis ações dos agentes, casos estejam ocupados.
			*  Simulate a check by generating a random number.
			*/
			
			return ( Math.random() > 0.2 );
		}

		private boolean performAction() {
		  	/* Simulate action execution by generating a random number.*/
			
		  	return ( Math.random() > 0.2 );
		}
	}
	
	private class HandleFightRequest extends OneShotBehaviour{
		private static final long serialVersionUID = 6230563887514017233L;
		
		@Override
		public void action() {
			MessageTemplate template = MessageTemplate.and(
			  		MessageTemplate.MatchProtocol( FIPANames.InteractionProtocol.FIPA_REQUEST ),
			  		MessageTemplate.MatchPerformative( ACLMessage.REQUEST ) );
			  		
					mySelf.addBehaviour( new AchieveREResponder( mySelf, template ) {
						private static final long serialVersionUID = 1L;

						protected ACLMessage prepareResponse( ACLMessage request ) 
								throws NotUnderstoodException, RefuseException {
							return null;
						}

						protected ACLMessage prepareResultNotification( ACLMessage request, 
								ACLMessage response ) throws FailureException {
							/* Quanto de dano esse agente perde.*/
							
							int atk = Integer.parseInt( request.getContent() );
							damageHealthPoints( atk );
							System.out.println( "[" + getLocalName() +
									"]{HP:" + getHealthPoints() + "} Received a hit from ["
									+ request.getSender().getLocalName() + "]{HIT:" + request.getContent() + "}" );
							ACLMessage inform = request.createReply();
							inform.setPerformative( ACLMessage.INFORM );
							int exp = getHealthPoints() - ( atk - getDefensePoints() );
							inform.setContent( String.valueOf( exp ) );
							return inform;
						}
					} );
			  }
	}

}