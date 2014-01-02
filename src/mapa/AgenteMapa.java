package mapa;

import java.io.IOException;
import java.util.Vector;

public interface AgenteMapa {
	
	
	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	
	public final int TAMANHO = 3;
	
	public abstract int atualizaMadeira(int valor,int posX, int posY);
	
	public abstract int atualizaOuro(int valor,int posX, int posY);
	
	public abstract int atualizaComida(int valor,int posX, int posY);
	
	public abstract int atualizaPedra(int valor,int posX, int posY);
	
	public abstract int atualizaMinimundo(int tipo, int i, int minY);

	void initMapa() throws IOException;

	public abstract Vector<Integer> getTerritorio(int civilizacao);

	public abstract int verificaOcupacao(int min, int max, int orientacao, int deslocamento);
	
	public abstract int verificaRecursos(int min, int max, int orientacao, int deslocamento);
	
	public abstract int ocupaRegiao(int tipo, int min, int max, int orientacao, int deslocamento);
}
