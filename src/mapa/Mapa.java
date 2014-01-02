package mapa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Mapa implements Serializable{

	public static void main(String args[]){
		Mapa mapa;
		mapa = Mapa.getInstance();
		for(int i = 0; i< mapa.x; i++){
			for(int j = 0; j< mapa.y; j++){
				System.out.println(mapa.minimundo[i][j]);
				System.out.println(mapa.npcs[i][j]);
				System.out.println(mapa.itens[i][j]);
				System.out.println(mapa.personagens[i][j]);
			}
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -732436113324526494L;
	public static final int TAMANHO = 4;
	public int x = 0;
	public int y = 0;
	public int personagens[][];
	public int npcs[][];
	public int itens[][];
	public int minimundo[][];

	private final String CAMINHO = "../D&D/mapa.txt";
	private static Mapa mapa = null;

	private Mapa(int x, int y) {

		this.x = x;
		this.y = y;
		this.minimundo = new int[x][y];
		this.npcs = new int[x][y];
		this.itens = new int[x][y];
		this.personagens = new int[x][y];
		
		try {
			initMapa();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Mapa getInstance() {

		if (mapa == null) {

			mapa = new Mapa(TAMANHO, TAMANHO);
		}
		return mapa;
	}
	public void initMapa() throws IOException {

		boolean arquivoAberto = true;
		BufferedReader arquivo = null;

		try {
			arquivo = new BufferedReader(new FileReader(CAMINHO));

		} catch (FileNotFoundException e) {
			arquivoAberto = false;
		}
		if (arquivoAberto) {
			int i;
			for (i = 0; i < this.x; i++) {
				int j;
				for (j = 0; j < this.y; j++) {
					String valor = String.valueOf(arquivo.read() - 48);				 
					if (valor == String.valueOf(-49)) {
						break;
					} else {
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						minimundo[i][j] = Integer.parseInt(valor);
						valor = "";
	
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						npcs[i][j] = Integer.parseInt(valor);
						valor = "";
						
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						itens[i][j] = Integer.parseInt(valor);	
						valor = "";
						
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						valor += String.valueOf(arquivo.read() - 48);
						personagens[i][j] = Integer.parseInt(valor);
					}
				}
			}
			arquivo.close();
		}
	}
}
