package persistenceHelper;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DBConnector {

	private static DBConnector instance = null;
	
	private MongoClient mongo;
	
	private DBConnector(){
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnector getInstance() {
		if(instance == null){
			instance = new DBConnector();
		}
		return instance;
	}
	
	public DBCollection getTable(String tableName){
		DB database = mongo.getDB("rpgMultiagentesDb");
		DBCollection table = database.getCollection(tableName);
		return table;
	}
}
