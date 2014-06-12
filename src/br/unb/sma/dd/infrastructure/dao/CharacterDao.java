package br.unb.sma.dd.infrastructure.dao;

import br.unb.sma.dd.infrastructure.persistenceHelper.DBConnector;
import br.unb.sma.dd.world.character.Character;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;

public class CharacterDao {

	private DBConnector dbConnector;
	private static CharacterDao instance = null;
	
	private CharacterDao(){
		dbConnector = DBConnector.getInstance();
	}
	
	public CharacterDao getInstance(){
		if(instance == null){
			instance = new CharacterDao();
		}
		return instance;
	}
	
	public boolean insertCharacter (Character character){
		DBCollection table = dbConnector.getTable("characterTable");
		BasicDBObject document = new BasicDBObject();
//Put Character data here like:  		
//		document.put("name", character.getName());
//		document.put("strength", character.getStrength());
		CommandResult result = table.insert(document).getLastError(); 
		return result.ok();
	}
	
}
