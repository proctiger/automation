package uol.bt.cruncher.test.step;

import org.apache.commons.collections.Predicate;
import org.junit.Assert;

import uol.bt.cruncher.test.domain.BtEvent;
import uol.bt.cruncher.test.domain.MongoDocument;
import uol.bt.cruncher.test.helper.TestPrepare;
import uol.bt.cruncher.test.util.CollectionUtils;
import uol.bt.cruncher.test.util.Constants;

import com.mongodb.DBObject;

import cucumber.api.java.pt.Então;

public class ThenStep extends BaseStep {

    @Então("^a collection de impressões do mês atual será criada$")
    public void checkCurrViewsCollectionExistance() throws Exception{
        checkCollectionExistance(TestPrepare.getCurrViewsCollection());
    }
    
    @Então("^a collection de impressões do mês atual NÃO será criada$")
    public void checkCurrViewsCollectionNotExistance() throws Exception{
    	checkCollectionNotExistance(TestPrepare.getCurrViewsCollection());
    }

    @Então("^a collection de segmentos do mês atual será criada$")
    public void checkCurrSegmentsCollectionExistance() throws Exception {
        checkCollectionExistance(TestPrepare.getCurrSegmentsCollection());
    }
    
    @Então("^a collection de segmentos do mês atual NÃO será criada$")
    public void checkCurrSegmentsCollectionNotExistance() throws Exception {
    	checkCollectionNotExistance(TestPrepare.getCurrSegmentsCollection());
    }

    private void checkCollectionExistance(String collectionName) throws Exception {
        final boolean exists = TestPrepare.collectionExists(collectionName);
        Assert.assertTrue(String.format("a collection %s nao foi criada: ", collectionName), exists);
    }
    
    private void checkCollectionNotExistance(String collectionName) throws Exception {
        final boolean notExists = !TestPrepare.collectionExists(collectionName);
        Assert.assertTrue(String.format("a collection %s foi criada: ", collectionName), notExists);
    }

    @Então("^os eventos processados serão inseridos na collection de impressões do mês atual" +
            "|o sistema processa todas as linhas que não possuem erro" +
            "|os eventos processados não serão inseridos na collection de impressões do mês atual" +
            "|o sistema não processa nenhuma linha" +
            "|o sistema não processa o arquivo$")
    public void checkProcessedViewSignals() throws Exception{
        compareDocuments(viewsDocument, TestPrepare.getCurrViewsCollection());
    }
    
    @Então("^os eventos processados NÃO serão inseridos na collection de impressões do mês atual$")
    public void checkNotProcessedViewSignals() throws Exception{
    	assertEventsNotProcessed(viewsDocument, TestPrepare.getCurrViewsCollection());
    }

    @Então("^os eventos processados serão inseridos na collection de perfil" +
            "|os eventos processados não serão inseridos na collection de perfil$")
    public void checkProcessedProfileSignals() throws Exception {
        compareDocuments(profileDocument, TestPrepare.getProfileCollection());
    }

    @Então("^os eventos processados não serão inseridos na collection perfil$")
    public void checkNotProcessedProfileSignals() throws Exception {
        assertEventsNotProcessed(profileDocument, TestPrepare.getProfileCollection());
    }
    
    @Então("^os eventos processados serão inseridos na collection de segmentos do mês atual$")
    public void checkProcessedSegmentsSignals() throws Exception {
        compareDocuments(segmentsDocument, TestPrepare.getCurrSegmentsCollection());
    }
    
    @Então("^os eventos processados NÃO serão inseridos na collection de segmentos do mês atual$")
    public void checkNotProcessedSegmentsSignals() throws Exception {
    	assertEventsNotProcessed(segmentsDocument, TestPrepare.getCurrSegmentsCollection());
    }

    @Então("^os eventos processados serão inseridos na collection de cookies" +
            "|o campo updated será atualizado na collection de cookies$")
    public void checkProcessedCookies() throws Exception {
        compareDocuments(cookiesDocument, TestPrepare.getCookiesCollection());
    }
    
    @Então("^os eventos processados não serão inseridos na collection de cookies$")
    public void checkNotProcessedCookies() throws Exception {
    	assertEventsNotProcessed(viewsDocument, TestPrepare.getCurrViewsCollection());
    }

    private void compareDocuments(MongoDocument expectedDocument, String collection) throws Exception {
        final DBObject mongoDocument = TestPrepare.findOneInCollection(expectedDocument.getId(), collection);
        Assert.assertEquals(String.format("documento da collection %s difere do esperado", collection), expectedDocument, mongoDocument);
    }
    
    private void assertEventsNotProcessed(MongoDocument expectedDocument, String collection) throws Exception {
        final DBObject mongoDocument = TestPrepare.findOneInCollection(expectedDocument.getId(), collection);
        Assert.assertNull(String.format("evento da collection %s foi processado", collection), mongoDocument);
    }

    @Então("^o sistema registra em log os erros existentes no arquivo$")
    public void checkMissedEvents() throws Exception {
        final int numOfMissedEvents = TestPrepare.countMissedEvents(viewsDocument.getId());
        final int expectedMissedEvents = CollectionUtils.countMatches(events, new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                return !((BtEvent) object).isValid();
            }
        });
        Assert.assertEquals("Numero de eventos perdidos difere do esperado", expectedMissedEvents, numOfMissedEvents);
    }

    @Então("^o sistema não processa nenhum arquivo$")
    public void checkNoFilesProcessed() throws Exception {
        final int processedFiles = TestPrepare.countProcessedFiles();
        Assert.assertEquals("cruncher processou algum arquivo", 0, processedFiles);
    }

    @Então("^o sistema processa todos os arquivos" +
            "|o sistema processa os arquivos existentes na whitelist$")
    public void checkAllFilesProcessed() throws Exception {
        final int notProcessedFiles = TestPrepare.countArchivedFiles();
        Assert.assertEquals("restaram arquivos a serem processados", 0, notProcessedFiles);
    }

    @Então("^o sistema processa a quantidade máxima de arquivos permitida$")
    public void checkMaxFilesProcessed() throws Exception {
        final int expectedProcessedFiles = Constants.config.getCruncherBatchSize();
        final int actualProcessedFiles = TestPrepare.countProcessedFiles();
        Assert.assertEquals("cruncher nao processou a quantidade maxima de arquivos", expectedProcessedFiles, actualProcessedFiles);
    }

    @Então("^o sistema registra em log que não há arquivos a serem processados$")
    public void checkNotProcessedFilesLogMessage() throws Exception {
        final boolean existsLogMessage = TestPrepare.notExistsArchivedFiles();
        Assert.assertTrue("mensagem de arquivos nao encontrados nao foi logada", existsLogMessage);
    }

    @Então("^o sistema registra em log que algum arquivo a ser processado não está na whitelist$")
    public void checkInvalidFilesLogMessage() throws Exception {
        final boolean existsLogMessage = TestPrepare.existsArchivedFilesNotWhitelist();
        Assert.assertTrue("mensagem de arquivos invalidos nao foi logada", existsLogMessage);
    }
}
