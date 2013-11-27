package uol.admanager.consolidator.test.steps.click;

import java.util.List;

import org.junit.Assert;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.consolidator.test.steps.view.TestStep;
import uol.admanager.entity.Click;
import uol.admanager.entity.ClickLog;
import uol.admanager.entity.Dimension;
import cucumber.api.java.pt.Entao;

public class ThenTestStep extends TestStep {

	@Entao("^o sistema não registra o processamento dos arquivos de cliques processados$")
    public void validateNotProcessedAgainClickLogFiles() throws Exception {
  	
  	List<ClickLog> clickLogList = TestPrepare.getClickLogList(createdClickFileNameList);
      Assert.assertTrue(clickLogList.size() == createdClickFileNameList.size());
  	
      for (ClickLog clickLog : clickLogList) {
			Assert.assertTrue("Arquivo foi processado novamente", Integer.parseInt(clickLog.getNumLines()) == 1);
		}
    }
    
    @Entao("^o sistema <nao> contabiliza cliques e dimension do arquivo$")
    public void validateNotProcessedClickFiles() throws Exception {
        final Dimension actualDimension = TestPrepare.getDimensionData(dimensionForClick);
        final Click actualClick = TestPrepare.getClickData(actualDimension, dimensionForClick.getTruncatedDate());
        Assert.assertEquals("conteudo da tabela ad_dimension difere", null, actualDimension);
        Assert.assertEquals("conteudo da tabela ad_click difere", null, actualClick);
    }    
   
    @Entao("^o sistema contabiliza os cliques do arquivo|" +
            "o sistema <nao> contabiliza os cliques do arquivo$")
    public void validateProcessedClickFiles() throws Exception {
        final Dimension actualDimension = TestPrepare.getDimensionData(dimensionForClick);
        final Click actualClick = TestPrepare.getClickData(actualDimension, dimensionForClick.getTruncatedDate());

        Assert.assertEquals("conteudo da tabela ad_dimension difere", expectedDimension, actualDimension);
        Assert.assertEquals("conteudo da tabela ad_click difere", expectedClick, actualClick);
    }

     @Entao("^o sistema registra o processamento dos arquivos de cliques$")
    public void validateProcessedClickLogFiles() throws Exception {
        List<ClickLog> actualClickLogList = TestPrepare.getClickLogList(createdClickFileNameList);
        for (String createdClickFile : createdClickFileNameList) {
            boolean contains = false;
            for (ClickLog actualClickLog : actualClickLogList) {

                if (actualClickLog.getDesUrl().indexOf(createdClickFile) > 0){
                    contains = true;
                    break;
                }
            }
            if (!contains){
                Assert.fail("Conteudo da tabela ad_click_log nao possui arquivo gz " + createdClickFile);
            }
        }
    }
}
