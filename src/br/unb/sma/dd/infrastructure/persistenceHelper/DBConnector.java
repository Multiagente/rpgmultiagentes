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

	private static final String NAME_OF_DATABASE = "rpgMultiagentesDb";
	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	
	private static DBConnector instanceDBConnector = null;
	private MongoClient mongo;
	
	private DBConnector() {
		try {
			mongo = new MongoClient( HOST, PORT );
			
		} catch( UnknownHostException e ) {
			e.printStackTrace();
		}
	}
	
	public static DBConnector getInstance() {
		if( instanceDBConnector == null ) {
			instanceDBConnector = new DBConnector();
			
		} else {
			/*! Nothing To Do. */
		}
		
		return instanceDBConnector;
	}
	
	/**
	 * Returns the table in the database specified by name.
	 * 
	 * @param tableName the name of the table.
	 * @return DBCollection the table that has been requested.
	 */
	public DBCollection getTable( String tableName ) {
		DB database = mongo.getDB( NAME_OF_DATABASE );
		DBCollection table = database.getCollection( tableName );
		
		return table;
	}
}
