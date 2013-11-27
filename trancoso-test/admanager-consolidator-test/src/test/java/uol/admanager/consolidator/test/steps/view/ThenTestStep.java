package uol.admanager.consolidator.test.steps.view;

import java.util.List;

import org.junit.Assert;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.entity.Dimension;
import uol.admanager.entity.View;
import uol.admanager.entity.ViewLog;
import cucumber.api.java.pt.Entao;

public class ThenTestStep extends TestStep {
   
    @Entao("^o sistema contabiliza as impressões do arquivo|" +
            "o sistema contabiliza as impressões do arquivo das demais trincas da URL|" +
            "o sistema <nao> contabiliza as impressões do arquivo|$")
    public void validateProcessedViewFiles() throws Exception {
        final Dimension actualDimension = TestPrepare.getDimensionData(dimensionForView);
        final View actualView = TestPrepare.getViewData(actualDimension, dimensionForView.getTruncatedDate());

        Assert.assertEquals("conteudo da tabela ad_dimension difere", expectedDimension, actualDimension);
        Assert.assertEquals("conteudo da tabela ad_view difere", expectedView, actualView);
    }

    @Entao("^o sistema <nao> contabiliza view e dimension do arquivo$")
    public void validateNotProcessedViewFiles() throws Exception {
        final Dimension actualDimension = TestPrepare.getDimensionData(dimensionForView);
        final View actualView = TestPrepare.getViewData(actualDimension, dimensionForView.getTruncatedDate());
        Assert.assertEquals("conteudo da tabela ad_dimension difere", null, actualDimension);
        Assert.assertEquals("conteudo da tabela ad_view difere", null, actualView);
    }

    @Entao("^o sistema registra o processamento dos arquivos de impressões$")
    public void validateProcessedViewLogFiles() throws Exception {
        List<ViewLog> actualViewLogList = TestPrepare.getViewLogList(createdViewsFileNameList);
        for (String createdViewFile : createdViewsFileNameList) {
            boolean contains = false;
            for (ViewLog actualViewLog : actualViewLogList) {

                if (actualViewLog.getDesUrl().indexOf(createdViewFile) > 0){
                    contains = true;
                    break;
                }
            }
            if (!contains){
                Assert.fail("Conteudo da tabela ad_view_log nao possui arquivo gz " + createdViewFile);
            }
        }
    }

    @Entao("^o sistema não registra o processamento dos arquivos de impressões$")
    public void validateNotProcessedViewLogFiles() throws Exception {
        List<ViewLog> actualViewLogList = TestPrepare.getViewLogList(createdViewsFileNameList);

        Assert.assertNull(actualViewLogList);
    }

    @Entao("^o sistema não registra o processamento dos arquivos de impressões processados$")
    public void validateNotProcessedAgainViewLogFiles() throws Exception {

        List<ViewLog> viewLogList = TestPrepare.getViewLogList(createdViewsFileNameList);
        Assert.assertTrue(viewLogList.size() == createdViewsFileNameList.size());

        for (ViewLog viewLog : viewLogList) {
            Assert.assertTrue("Qtade de linhas do arquivo processado maior que 1", Integer.parseInt(viewLog.getNumLines()) == 1);
        }
    }

    @Entao("^o sistema não registra o processamento do arquivo de impressões$")
    public void validateViewLogNotProcessed() throws Exception {
        List<ViewLog> viewLogList = TestPrepare.getViewLogList(createdViewsFileNameList);
        Assert.assertTrue(viewLogList.size() == createdViewsFileNameList.size());
    }
}
