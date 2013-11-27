package uol.bt.mongokeeper.test.step;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import junit.framework.TestCase;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uol.bt.commons.test.helper.MongoHelper;
import uol.bt.mongokeeper.test.util.ConfigWsHttp;
import uol.bt.mongokeeper.test.util.Constants;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import cucumber.api.java.pt.Dado;



public class BaseStep extends TestCase{
	public static DB db ;
	public static ArrayList<String> listCollectionsCreated = new ArrayList<String>();
	public static ArrayList<String> listRecordsId = new ArrayList<String>();
	public static Date dateTest;
	public static String collection;
	public int countRecordsElegibleToBePurgedBefore; 
	protected final Logger logger;
	public Set<String> tablesBefore;
	public Set<String> tablesAfter;
	public String nameOfCollection = "";

	public BaseStep() {
	     logger = LoggerFactory.getLogger(getClass());
	}
	
	
	
	public void createCollection(ArrayList<String> nameOfCollections) throws Exception {
		db = MongoHelper.connect();
		
		for (String collectionName : nameOfCollections) {
			MongoHelper.createCollection(collectionName);
			logger.info(collectionName);
			listCollectionsCreated.add(collectionName);
		}
		
		Set<String> collectionNames = db.getCollectionNames();
		for (String collectionToBePurged : listCollectionsCreated) {
			Assert.assertTrue("Coleção " + collectionToBePurged + " não foi criado no Mongo!",
					collectionNames.contains(collectionToBePurged));
		}
	}
	
	public void insertRecordsMongoDb(String collectionName, Calendar updatedRecord) throws Exception {
		collection = collectionName;
		dateTest = updatedRecord.getTime();
		String recordId = UUID.randomUUID().toString();
		listRecordsId.add(recordId);
		
		logger.info("--------------------------------------------------------_");
		logger.info(collectionName);
		logger.info(updatedRecord.getTime().toString());
		logger.info(recordId);
		logger.info("--------------------------------------------------------_");
		
		DBObject updateObject = new BasicDBObject();
		updateObject.put( "name",    "testeMongoKeeper" );
		updateObject.put( "age",   30);
		updateObject.put( "updated", dateTest);
		
		WriteResult upsertIntoCollection = MongoHelper.upsertIntoCollection(recordId, new BasicDBObject("$set", updateObject), collection);
		Assert.assertTrue( upsertIntoCollection.getN() > 0 );
    }
	
	public ArrayList<String> randomNameValidMonthsToBePurged(int qtOfNames, String prefix){
		ArrayList<String> collectionList = new ArrayList<>();
		
		for (int i = 0; i < qtOfNames; i++){
			 
		    Calendar now = Calendar.getInstance();
		    now = Calendar.getInstance();
		    StringBuilder st = new StringBuilder();
		    Random r = new Random();

		    int randomMonth = r.nextInt(24) + 3;
		    
		    now.add(Calendar.MONTH, - (randomMonth) + 1);
		    st.append(now.get(Calendar.MONTH) + 1);
		    st.append(now.get(Calendar.YEAR));
		    
		    if (st.length() < 6){
		    	nameOfCollection = prefix + "0" + st;
		    }else{
		    	nameOfCollection = prefix + st + "";
		    }
		    collectionList.add(nameOfCollection);
		   }
		return collectionList;
	}
	
	public ArrayList<String> lastValidMonthsToBePurged(int qtOfNames, String prefix){
		ArrayList<String> collectionList = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		now = Calendar.getInstance();
		String nameOfCollection = "";
		now.add(Calendar.MONTH, -2);
		
		for (int i = 0; i < qtOfNames; i++){
			 
			    now.add((Calendar.MONTH), -1);
				StringBuilder st = new StringBuilder();
			    st.append(now.get(Calendar.MONTH)+1);
			    st.append(now.get(Calendar.YEAR));
			    
			    
			    if (st.length() < 6){
			    	nameOfCollection = prefix + "0" + st;
			    }else{
			    	nameOfCollection = prefix + st + "";
			    }
			    collectionList.add(nameOfCollection);
		}
		return collectionList;
	}
	
	
	public ArrayList<String> lastInValidMonthToBePurged(String prefix){
		ArrayList<String> collectionList = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		now = Calendar.getInstance();
		String nameOfCollection = "";
		
		StringBuilder st = new StringBuilder();
		st.append(now.get(Calendar.MONTH));
		st.append(now.get(Calendar.YEAR));
			    
		if (st.length() < 6){
		   	nameOfCollection = prefix + "0" + st;
		}else{
		   	nameOfCollection = prefix + st + "";
		}
		
		collectionList.add(nameOfCollection);
		return collectionList;
	}
	
	public ArrayList<String> actualInValidMonthToBePurged(String prefix){
		ArrayList<String> collectionList = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		now = Calendar.getInstance();
		String nameOfCollection = "";
		
		StringBuilder st = new StringBuilder();
		st.append(now.get(Calendar.MONTH)+1);
		st.append(now.get(Calendar.YEAR));
			    
		if (st.length() < 6){
		   	nameOfCollection = prefix + "0" + st;
		}else{
		   	nameOfCollection = prefix + st + "";
		}
		
		collectionList.add(nameOfCollection);
		return collectionList;
	}

	public DBCursor checkRecordsElegibleToBePurged(String collection) throws Exception{
		BasicDBObject dateQueryObj = new BasicDBObject("updated",  new BasicDBObject("$lt", getDateRemoteConfigured(collection)));
		DB connect = MongoHelper.connect();
		DBCursor find = connect.getCollection(collection).find(dateQueryObj);
		for (DBObject dbObject : find) {
			logger.info("Document elegivel para purga na collection <"+collection+">: "+dbObject.toString());
		}
		return find;
	}
	
	public Date getDateRemoteConfigured(String collection){
		Calendar now = Calendar.getInstance();
		int amountMonthToKeep = Constants.REMOTE_ADMIN_TEST_AMOUNT_MONTH_KEEP_RECORDS.get(collection);
		now.add(Calendar.MONTH, -amountMonthToKeep);
		return (now.getTime());
	}
}
