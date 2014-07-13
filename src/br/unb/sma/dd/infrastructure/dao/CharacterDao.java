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
	
	private DBConnector dbConnector;
	private static CharacterDao instance = null;
	
	private CharacterDao() {
		dbConnector = DBConnector.getInstance();
	}
	
	public CharacterDao getInstance() {
		if( instance == null ) {
			instance = new CharacterDao();
			
		} // TODO: put the default behavior.
		
		return instance;
	}
	
	/**
	 * Inserts the character passed as a parameter in the database.
	 * 
	 * @param character the character you want to insert in the database.
	 * @return boolean indication of the result of the operation. If there was
	 *         successful returns true. If failure occurred return false.
	 */
	public boolean insertCharacter( Character character ) {
		DBCollection table = dbConnector.getTable( "characterTable" );
		BasicDBObject document = new BasicDBObject();
		// Put Character data here like:
		// document.put("name", character.getName());
		// document.put("strength", character.getStrength());
		CommandResult result = table.insert( document ).getLastError();
		return result.ok();
	}
}
