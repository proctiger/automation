package uol.bt.mongokeeper.test.step;

import java.util.Calendar;

import cucumber.api.java.pt.Dado;

/**
 * 
 * @author wrodrigues
 * 
 */
public class GivenPurgaRecordsStep extends BaseStep {
			
	@Dado("^criar registros na collection <(.+)> para purga: 1 dia passado$")
	public void createOneRecordToBePurgedOnePastDay(String collection) {
		try {
			Calendar updatedRecord = Calendar.getInstance();
			updatedRecord.setTime(getDateRemoteConfigured(collection));
			updatedRecord.add(Calendar.DAY_OF_MONTH, -1);
			
			insertRecordsMongoDb(collection, updatedRecord);
			
			int countRecordsElegibleToBePurgedBefore = checkRecordsElegibleToBePurged(collection).count();
			logger.info("Qtdade registros elegiveis de purga: "+countRecordsElegibleToBePurgedBefore);

		} catch (Exception e) {
		}
	}
	
	@Dado("^criar registros na collection <(.+)> para purga: 1 mês passado$")
	public void createOneRecordToBePurgedOnePastMonth(String collection) {
		try {
			Calendar updatedRecord = Calendar.getInstance();
			updatedRecord.setTime(getDateRemoteConfigured(collection));
			updatedRecord.add(Calendar.MONTH, -1);
			
			insertRecordsMongoDb(collection, updatedRecord);
			
			countRecordsElegibleToBePurgedBefore = checkRecordsElegibleToBePurged(collection).count();
			logger.info("Qtdade registros elegiveis de purga: "+countRecordsElegibleToBePurgedBefore);

		} catch (Exception e) {
		}
	}
	
	@Dado("^criar registros na collection <(.+)> para purga: 1 ano passado$")
	public void createOneRecordToBePurgedOnePastYear(String collection) {
		try {
			Calendar updatedRecord = Calendar.getInstance();
			updatedRecord.setTime(getDateRemoteConfigured(collection));
			updatedRecord.add(Calendar.YEAR, -1);
			
			insertRecordsMongoDb(collection, updatedRecord);
			
			countRecordsElegibleToBePurgedBefore = checkRecordsElegibleToBePurged(collection).count();
			logger.info("Qtdade registros elegiveis de purga: "+countRecordsElegibleToBePurgedBefore);

		} catch (Exception e) {
		}
	}
	
	@Dado("^criar <(.+)> registros na collection <(.+)> para purga$")
	public void createRecordsToBePurged(int qtdade, String collection) {
		try {

			for (int i = 0; i < qtdade; i++){
				Calendar updatedRecord = Calendar.getInstance();
				updatedRecord.setTime(getDateRemoteConfigured(collection));
				updatedRecord.add(Calendar.DAY_OF_MONTH, -1);
				
				insertRecordsMongoDb(collection, updatedRecord);

				countRecordsElegibleToBePurgedBefore = checkRecordsElegibleToBePurged(collection).count();
				logger.info("Qtdade registros elegiveis de purga: "+countRecordsElegibleToBePurgedBefore);
			}

		} catch (Exception e) {
		}
	}

	@Dado("^criar registro na collection <(.+)> mês atual$")
	public void createRecordsToNOTBePurged(String collection) {
		try {
				Calendar updatedRecord = Calendar.getInstance();
				updatedRecord.getTime();

				insertRecordsMongoDb(collection, updatedRecord);
				
				countRecordsElegibleToBePurgedBefore = checkRecordsElegibleToBePurged(collection).count();
				logger.info("Qtdade registros elegiveis de purga: "+countRecordsElegibleToBePurgedBefore);
		} catch (Exception e) {
		}
	}
}
















