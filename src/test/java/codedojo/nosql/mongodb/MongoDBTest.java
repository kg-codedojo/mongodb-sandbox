package codedojo.nosql.mongodb;

import com.mongodb.DBObject;
import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created By: KonstantinG
 */
public class MongoDBTest {
    @Test public void testCRUD(){
        MongoDBService service = new MongoDBService();
        service.insert(101,"hello world mongoDB in Java");
        DBObject dbObject = service.read(101);
        String objectString = dbObject.toString();
        assertThat(objectString).contains("hello world mongoDB in Java");
    }

    @Test public void testPerformance100K(){
        long start = System.currentTimeMillis();
        MongoDBService service = new MongoDBService();
        service.removeAll();
        for(int i=0;i<100000;i++){
            service.insert(i,"hello"+i);
            assertThat(service.read(i).toString()).contains("hello"+i);
        }
        long stop = System.currentTimeMillis();
        //MongoDBTest.testPerformance100K. Total time = 30654
        System.out.println("MongoDBTest.testPerformance100K. Total time = "+(stop-start));
    }
}
