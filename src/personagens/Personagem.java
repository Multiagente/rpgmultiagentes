package personagens;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Vector;

import mapa.Mapa;

public abstract class Personagem extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3380309901133173161L;

	public final int CUSTO_DE_GUERRA = 1;

	// tipos de civilização
	public final int INGLES = 4;
	public final int JAPONES = 5;
	public final int BRASILEIRO = 6;

	public final int CIMA = 0;
	public final int DIREITA = 1;
	public final int BAIXO = 2;
	public final int ESQUERDA = 3;

	public final String MENSAGEM_DE_GUERRA = "Mensagem de Guerra";
	public final String MENSAGEM_DE_TROCA = "Mensagem de troca de recursos";

	public int comida = 0;
	public int madeira = 0;
	public int pedra = 0;
	public int ouro = 0;
	public Mapa mapa = Mapa.getInstance();
	public int populacao = 0;
	public int social = 0;
	public int militar = 0;
	public Vector<Integer> territorio;
	public int tipo;

	public String nomeDeus = "Ares";

	private AID[] deuses;

	public void setup() {

		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Serviço Postal");
		sd.setName("Cartas");
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);

		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		// Behaviours para coleta de recursos TickerBehaviours

		addBehaviour(new ColetarOuro(this, 3000));
		addBehaviour(new ColetarMadeira(this, 3000));
		addBehaviour(new ColetarPedra(this, 3000));
		addBehaviour(new ColetarComida(this, 3000));
		addBehaviour(new Adorar(this, 2000));

		// CyclicBehaviours para espera de mensagens
		addBehaviour(new EsperarMensagem());

		// TrocarRecurso troca = new TrocarRecurso();
		// addBehaviour(troca);
		// FazerAlianca ally = new FazerAlianca();
		// addBehaviour(ally);
		// Trocas de recursos

	}

	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	private class Adorar extends TickerBehaviour {

		public Adorar(Agent a, long period) {
			super(a, period);
		}

		private static final long serialVersionUID = -7671735486702389034L;

		@Override
		protected void onTick() {
			DFAgentDescription adoracao = new DFAgentDescription();
			ServiceDescription servico = new ServiceDescription();
			servico.setType("adoracao");
			adoracao.addServices(servico);
			try {
				DFAgentDescription[] resultado = DFService.search(myAgent,
						adoracao);
				deuses = new AID[resultado.length];
				for (int i = 0; i < resultado.length; ++i) {
					deuses[i] = resultado[i].getName();

					if (deuses[i].getName().contains(nomeDeus)) {
						ACLMessage mensagem = new ACLMessage(ACLMessage.CFP);
						mensagem.addReceiver(deuses[i]);
						mensagem.setContent(String.valueOf(tipo));
						myAgent.send(mensagem);
					}
					MessageTemplate mt = MessageTemplate
							.MatchPerformative(ACLMessage.CFP);
					ACLMessage msd = myAgent.receive(mt);
					if (!msd.getContent().equals("")) {
						militar += Integer.parseInt(msd.getContent());
					}
				}
				System.out.println("Civilizacao " + tipo + " adorando deus "
						+ nomeDeus);
				System.out.println("Militar : " + militar);
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}
		}

	}

	private class ColetarComida extends TickerBehaviour {

		private static final long serialVersionUID = -3495647878417450828L;

		public ColetarComida(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {

			int comida = 0;

			for (int i = 0; i < territorio.size(); i++) {
				// comida += mapa.atualizaComida(-1, i, ++i);
				System.out.println("Valor da Comida: " + comida);
			}
			if (comida == 0) {
				// TODO implementar solução para falta de comida.
				System.out.println("A comida acabou!\n");
			}

		}
	}

	private class ColetarPedra extends TickerBehaviour {

		private static final long serialVersionUID = -3495647878417450828L;

		public ColetarPedra(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {

			int pedra = 0;

			for (int i = 0; i < territorio.size(); i++) {
				// pedra += mapa.atualizaPedra(-1, i, ++i);
				System.out.println("Valor de Pedra: " + pedra);
			}
			if (pedra == 0) {
				// TODO implementar solução para falta de pedra.
				System.out.println("A pedra acabou!\n");
			}
		}
	}

	private class ColetarOuro extends TickerBehaviour {

		private static final long serialVersionUID = -3495647878417450828L;

		public ColetarOuro(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {

			int ouro = 0;

			for (int i = 0; i < territorio.size(); i++) {
				// ouro += mapa.atualizaOuro(-1, i, ++i);
				System.out.println("Valor de Ouro: " + ouro);
			}
			if (ouro == 0) {
				// TODO implementar solução para falta de ouro.
			}
		}
	}

	private class ColetarMadeira extends TickerBehaviour {

		private static final long serialVersionUID = -3495647878417450828L;

		public ColetarMadeira(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {

			int madeira = 0;
			for (int i = 0; i < territorio.size(); i++) {
				// madeira += mapa.atualizaMadeira(-1, i, ++i);
				System.out.println("Valor de Madeira: " + madeira);
			}
			if (madeira == 0) {
				// TODO implementar solução para falta de madeira DEUSES.
				System.out.println("A madeira acabou!\n");
			}
		}
	}

	// Espera uma mensagem do serviço postal
	private class EsperarMensagem extends CyclicBehaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5141707877178025731L;

		@Override
		public void action() {
		}
	}
}
