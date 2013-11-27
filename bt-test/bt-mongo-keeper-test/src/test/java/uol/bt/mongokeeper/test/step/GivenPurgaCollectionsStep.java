package uol.bt.mongokeeper.test.step;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import uol.bt.mongokeeper.test.util.ConfigWsHttp;

import cucumber.api.java.pt.Dado;

/**
 * 
 * @author wrodrigues
 * 
 */
public class GivenPurgaCollectionsStep extends BaseStep {

	@Dado("^criar collections <(.+)> para purga$")
	public void createOneCollectionToBePurgged(String collectionType) {
		try {
			ArrayList<String> listCollectionsNames = randomNameValidMonthsToBePurged(1,collectionType+"_");
			createCollection(listCollectionsNames);
		} catch (Exception e) {
		}
	}

	@Dado("^criar <(.+)> collections <(.+)> para purga$")
	public void createAlotCollectionToBePurgged(int qtd, String collectionType) {
		try {
			ArrayList<String> listCollectionsNames = lastValidMonthsToBePurged(qtd, collectionType+"_");
			createCollection(listCollectionsNames);
		} catch (Exception e) {
		}
	}
	
	@Dado("^criar collections <(.+)> mês atual$")
	public void createCollectionThisMonthToBePurgged(String collectionType) {
		try {
			ArrayList<String> listCollectionsNames = super.actualInValidMonthToBePurged(collectionType +"_");
			createCollection(listCollectionsNames);
		} catch (Exception e) {
		}
	}
	
	@Dado("^criar collections <(.+)> mês anterior$")
	public void createCollectionLastMonthToBePurgged(String collectionType) {
		try {
			ArrayList<String> nameOfCollections = super.lastInValidMonthToBePurged(collectionType +"_");
			createCollection(nameOfCollections);
		} catch (Exception e) {
		}
	}

}
