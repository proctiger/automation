package uol.bt.mongokeeper.test.step;



import org.junit.Assert;

import uol.bt.commons.test.helper.MongoHelper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;

import cucumber.api.java.After;
import cucumber.api.java.pt.Entao;

/**
 * 
 * @author wrodrigues
 * 
 */

public class ThenPurgaStep extends BaseStep {
	
	@After
	public void afterTests() throws Exception{
		
		if (!listRecordsId.isEmpty()){
			listRecordsId.clear();
			DB connect = MongoHelper.connect();
			BasicDBObject dateQueryObj = new BasicDBObject("name", "testeMongoKeeper" );
			DBCursor find = connect.getCollection(collection).find(dateQueryObj);
			for (DBObject document : find) {
				connect.getCollection(collection).remove(document, WriteConcern.SAFE);
			}
		}
		
		if (!listCollectionsCreated.isEmpty()){
			for (String collectionCreated : listCollectionsCreated) {
				MongoHelper.dropCollection(collectionCreated);
			}
			listCollectionsCreated.clear();
		}
	}
	
	@Entao("^verificar purga de registros na collection <(.+)>$")
	public void checkPurgedCollections(String collection) throws Exception {
		StringBuilder msg = new StringBuilder();
		msg.append("Registro nao apagado da collection: "+collection+ ":");

		DBCursor checkRecordsElegibleToBePurged = checkRecordsElegibleToBePurged(collection);
		
		if (checkRecordsElegibleToBePurged.count() > 0){
			for (DBObject dbObject : checkRecordsElegibleToBePurged) {
				msg.append(dbObject);
			}	
		}
		
		Assert.assertTrue(msg.toString(),checkRecordsElegibleToBePurged.count() == 0);
	}
	
	@Entao("^verificar purga não realizada na collection <(.+)>$")
	public void checkRecordNotPurgedCollection(String collection) throws Exception {
		
		DBObject findOneInCollection = MongoHelper.findOneInCollection(listRecordsId.get(0), collection);
		String find = findOneInCollection.get("_id").toString();
		logger.info("Registro nao purgado da collection: "+collection+" : "+find.toString());
		
		Assert.assertTrue("",!find.isEmpty());
	}
	
	@Entao("^verificar purga$")
	public void checkPurgedCollections() {
		try {
			tablesAfter = db.getCollectionNames();
			
			for (String collectionPurged : listCollectionsCreated) {
				Assert.assertFalse("Coleção " + collectionPurged + " não foi apagada!",
						tablesAfter.contains(collectionPurged));
			}
		} catch (Exception e) {

		}
	}
	
	@Entao("^verificar purga não realizada$")
	public void checkPurgedCollectionsNotDone() {
		try {
		
			tablesAfter = db.getCollectionNames();
			
			for (String collectionPurged : listCollectionsCreated) {
				Assert.assertTrue("Coleção " + collectionPurged + " foi apagada!",
						tablesAfter.contains(collectionPurged));
			}
		} catch (Exception e) {

		}
	}
	
}
