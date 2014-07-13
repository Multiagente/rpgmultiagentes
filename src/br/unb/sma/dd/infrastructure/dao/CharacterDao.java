/*
 * File: CharacterDao.java. Purpose: This file brings the implementation of the
 * class CharacterDao.
 */
package br.unb.sma.dd.infrastructure.dao;


import br.unb.sma.dd.infrastructure.persistenceHelper.DBConnector;
import br.unb.sma.dd.world.character.Character;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;


/**
 * This class is responsible for all communications between the game characters
 * and the database, enabling the record in the database of character
 * information.
 */
public class CharacterDao {
	
	private static final String CHARACTERS_TABLE = "characterTable";
	
	private DBConnector dbConnector;
	private static CharacterDao instanceCharacterDao = null;
	
	private CharacterDao() {
		dbConnector = DBConnector.getInstance();
	}
	
	public CharacterDao getInstance() {
		if( instanceCharacterDao == null ) {
			instanceCharacterDao = new CharacterDao();
			
		} else {
			/* ! Nothing To Do. */
		}
		
		return instanceCharacterDao;
	}
	
	/**
	 * Inserts the character passed as a parameter in the database.
	 * 
	 * @param character the character you want to insert in the database.
	 * @return boolean indication of the result of the operation. If there was
	 *         successful returns true. If failure occurred return false.
	 */
	public boolean insertCharacter( Character character ) {
		DBCollection charactersTable = dbConnector.getTable( CHARACTERS_TABLE );
		BasicDBObject charactersDocument = new BasicDBObject();
		
		/*
		 * TODO: Add the values to be stored in the database related to the
		 * 		character data.
		 */
		
		CommandResult result = charactersTable.insert( charactersDocument )
		      .getLastError();
		return result.ok();
	}
}
