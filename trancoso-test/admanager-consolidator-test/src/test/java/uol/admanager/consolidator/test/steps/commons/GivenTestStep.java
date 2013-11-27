package uol.admanager.consolidator.test.steps.commons;

import uol.admanager.consolidator.test.steps.view.TestStep;
import cucumber.api.java.pt.Dado;

public class GivenTestStep extends TestStep{
	
	  @Dado("^que não existe nenhum registro no banco para o anúncio a ser processado$")
	    public void deleteConsolidationData() throws Exception {
	        deleteViewData();
	        deleteClickData();
	        deleteDimension(dimensionForView);
	        deleteDimension(dimensionForClick);
	    }
	  
	  @Dado("^que já existe um registro no banco para o anúncio a ser processado com cliques e impressões$")
	    public void createClickAndViewData() throws Exception {
	        createClickData();
	        createViewData();
	    }

	    @Dado("^que já existe um registro no banco para o anúncio a ser processado apenas com impressões$")
	    public void deleteClickDataAndCreateViewData() throws Exception {
	        deleteClickData();
	        createViewData();
	        deleteDimension(dimensionForClick);
	    }
	    
	    @Dado("^que já existe um registro no banco para o anúncio a ser processado apenas com cliques$")
	    public void deleteViewDataAndCreateClickData() throws Exception {
	        deleteViewData();
	        createClickData();
	        deleteDimension(dimensionForView);
	    }
	  
}
