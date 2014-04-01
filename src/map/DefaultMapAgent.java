package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DefaultMapAgent extends MapAgent {

	// public static void main(String args[]){
	// DefaultMapAgent mapa;
	// mapa = DefaultMapAgent.getInstance();
	// for(int i = 0; i< mapa.x; i++){
	// for(int j = 0; j< mapa.y; j++){
	// System.out.println(mapa.world[i][j]);
	// System.out.println(mapa.npcs[i][j]);
	// System.out.println(mapa.items[i][j]);
	// System.out.println(mapa.characters[i][j]);
	// }
	// }
	// }

	private static final long serialVersionUID = -732436113324526494L;

	private int x = 0;
	private int y = 0;
	private int characters[][];
	private int npcs[][];
	private int items[][];
	private int world[][];

	private final String FILE_PATH = "../D&D/mapa.txt";
	private static final int SIZE = 4;
	private static final int FORMATER_VALUE = 48;

	private static DefaultMapAgent map = null;

	private DefaultMapAgent(int x, int y) {

		this.x = x;
		this.y = y;
		this.world = new int[x][y];
		this.npcs = new int[x][y];
		this.items = new int[x][y];
		this.characters = new int[x][y];

		try {
			initMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static DefaultMapAgent getInstance() {

		if (map == null) {

			map = new DefaultMapAgent(SIZE, SIZE);
		}
		return map;
	}

	public void initMap() throws IOException {

		boolean openedFile = true;
		BufferedReader file = null;

		try {
			file = new BufferedReader(new FileReader(FILE_PATH));

		} catch (FileNotFoundException e) {
			openedFile = false;
		}
		if (openedFile) {
			int i;
			for (i = 0; i < this.x; i++) {
				int j;
				for (j = 0; j < this.y; j++) {
					String value = String.valueOf(file.read() - FORMATER_VALUE);
					if (value == String.valueOf(-FORMATER_VALUE - 1)) {
						break;
					} else {
						value += String.valueOf(file.read() - FORMATER_VALUE);
						value += String.valueOf(file.read() - FORMATER_VALUE);
						value += String.valueOf(file.read() - FORMATER_VALUE);
						value += String.valueOf(file.read() - FORMATER_VALUE);
						world[i][j] = Integer.parseInt(value);
						value = "";

						int npcCount;
						npcCount = Integer
								.valueOf(file.read() - FORMATER_VALUE);
						for (int k = 0; k < npcCount; k++) {
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							npcs[i][j] = Integer.parseInt(value);
							value = "";
						}

						int itemsCount;
						itemsCount = Integer.valueOf(file.read()
								- FORMATER_VALUE);
						for (int k = 0; k < itemsCount; k++) {
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							items[i][j] = Integer.parseInt(value);
							value = "";
						}

						int charCount;
						charCount = Integer.valueOf(file.read()
								- FORMATER_VALUE);
						for (int k = 0; k < charCount; k++) {
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							value += String.valueOf(file.read()
									- FORMATER_VALUE);
							characters[i][j] = Integer.parseInt(value);
							value = "";
						}
					}
				}
			}
			file.close();
		}
	}
}
