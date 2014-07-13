/* File:		DBConnector.java.
 * Purpose: This file brings the implementation of the class DBConnector.
 * */
package br.unb.sma.dd.infrastructure.persistenceHelper;


import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


/** TODO: put a text about the this class. */
public class DBConnector {
	
	private static DBConnector instance = null;
	private MongoClient mongo;
	
	private DBConnector() {
		try {
			mongo = new MongoClient( "localhost", 27017 );
			
		} catch( UnknownHostException e ) {
			e.printStackTrace();
		}
	}
	
	public static DBConnector getInstance() {
		if( instance == null ) {
			instance = new DBConnector();
			
		} // TODO: put the default behavior.
		
		return instance;
	}

	/** TODO: put a text about this method. */
	public DBCollection getTable( String tableName ) {
		DB database = mongo.getDB( "rpgMultiagentesDb" );
		DBCollection table = database.getCollection( tableName );
		return table;
	}
}
