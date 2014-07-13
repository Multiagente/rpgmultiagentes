/* File:		TODO: put name of the file.
 * Purpose: TODO: put a text about the purpose of the file.
 * */
package br.unb.sma.dd.infrastructure.dao;


import br.unb.sma.dd.infrastructure.persistenceHelper.DBConnector;
import br.unb.sma.dd.world.character.Character;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;


/** TODO: put a text about the this class. */
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
	
	/** TODO: put a text about this method. */
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
