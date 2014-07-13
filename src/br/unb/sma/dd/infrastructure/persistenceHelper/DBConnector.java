/*
 * File: DBConnector.java. Purpose: This file brings the implementation of the
 * class DBConnector.
 */
package br.unb.sma.dd.infrastructure.persistenceHelper;


import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


/**
 * Class responsible for creating and maintaining the connection to the
 * database.
 */
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
	
	/**
	 * Returns the table in the database specified by name.
	 * 
	 * @param tableName the name of the table.
	 * @return DBCollection the table that has been requested.
	 */
	public DBCollection getTable( String tableName ) {
		DB database = mongo.getDB( "rpgMultiagentesDb" );
		DBCollection table = database.getCollection( tableName );
		return table;
	}
}
