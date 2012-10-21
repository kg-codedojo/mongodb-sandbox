package codedojo.nosql.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created By: KonstantinG
 * Date,time: 10/21/12, 3:21 PM
 */
public class MongoDBService {

    public static final String HOST = "localhost";
    public static final String DBNAME = "dbname";
    public static final String COLLECTION = "collectionname";
    public static final int PORT = 27017;
    private Mongo mongo;
    private DB db;
    private DBCollection collection;

    public MongoDBService() {
        try {
            mongo = new Mongo(HOST, PORT);
            db = mongo.getDB(DBNAME);
            collection = db.getCollection(COLLECTION);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void removeAll(){
        try {
            DBCursor cursor = collection.find();
            while(cursor.hasNext()) {
                collection.remove(cursor.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insert(long id, String data) {
        try {
            BasicDBObject document = new BasicDBObject();
            document.put("_id", id);
            document.put("data", data);
            collection.insert(document);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public DBObject read(long id) {
        DBObject result = null;
        try {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("_id", id);
            DBCursor cursor = collection.find(searchQuery);
            if(cursor.hasNext()){
                result = cursor.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
