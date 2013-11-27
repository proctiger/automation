package uol.admanager.consolidator.test.steps.click;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.consolidator.test.reconf.RemoteConfig;
import uol.admanager.consolidator.test.steps.view.TestStep;
import uol.admanager.entity.ClickLog;
import uol.admanager.entity.Dimension;
import uol.admanager.entity.View;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

public class GivenTestStep extends TestStep {

    @Before
    public void beforeEachTest() throws Exception {
        dimensionForView = null;
        dimensionForClick = null;
        expectedView = new View();
        expectedClick = null;
        expectedDimension = null;
        expectedNumberOfParserErrors = 0L;
        createdViewsFileName = null;
        createdViewsFileNameList = new ArrayList<String>();
        createdClickFileName = null;
        createdClickFileNameList = new ArrayList<String>();

        if (self) {
            return;
        }

        initDimensions();

        TestPrepare.stopConsolidator();
        TestPrepare.deleteConsolidatorLogs();
        TestPrepare.deleteGzipFiles();
    }

    private void initDimensions() throws Exception {
        dimensionForView = TestPrepare.newBasicDimension();
        dimensionForView.setQuantity(TestPrepare.getRandomQuantity());

        dimensionForClick = dimensionForView.copy();
        dimensionForClick.setQuantity(TestPrepare.getRandomQuantity());
    }
    
    private String parameterDescriptionToName(String description) {
        switch (description) {
        case "type":
            return "ty";
        case "timestamp":
            return "t";
        case "slot":
            return "s";
        case "customer":
            return "c";
        case "id do sales package":
            return "sa";
        case "id do anuncio":
            return "ad";
        case "id do canal":
            return "ch";
        default:
            throw new IllegalArgumentException("parametro nao mapeado: " + description);
        }
    }
    
    @Dado("^que existe apenas um arquivo de cliques a ser processado$")
    public void que_existe_apenas_um_arquivo_de_cliques_a_ser_processado() throws Throwable {
    	TestPrepare.deleteGzipFiles();
    	Set<Dimension> singleton = Collections.singleton(dimensionForClick);
    	expectedDimension = new ArrayList<>(singleton).get(0);
    	createdClickFileName = TestPrepare.createClicksFile(singleton);
    	createdClickFileNameList.add(createdClickFileName);
    }    
    
    @Dado("^que o arquivo de cliques já foi processado anteriormente$")
    public void que_o_arquivo_foi_processado_anteriormente() throws Throwable {
      	String repo = RemoteConfig.getInstance().getConsolidatorRepositories().get("clicks").get(0);
        TestPrepare.insertClickLog(repo + createdClickFileName + ".gz", 1);
    }  
    
    @Dado("^que existe mais de um arquivo de cliques a ser processado$")
    public void createClickFileMoreThanOneFile() throws Exception {
        Set<Dimension> singleton = Collections.singleton(dimensionForClick);
        expectedDimension = new ArrayList<>(singleton).get(0);
        Long randomQuantity = TestPrepare.getRandomQuantity();
        
        for (int i = 0; i < randomQuantity; i++) {
        	createdClickFileNameList.add(TestPrepare.createClicksFile(singleton));
	    }
	}
    
    @Dado("^que nao existem anuncios processados para os arquivos de cliques$")
    public void deleteClicks() throws Exception {
        TestPrepare.deleteAllDimensionData();
        TestPrepare.deleteAllClickData();
    }
    
    @Dado("^que algum arquivo de cliques já foi processado anteriormente$")
    public void someClickLogProcessed() throws Exception {
        for (int i = 0; i < createdClickFileNameList.size() - 1; i++) {
            String repo = RemoteConfig.getInstance().getConsolidatorRepositories().get("clicks").get(0);
            TestPrepare.insertClickLog(repo + createdClickFileNameList.get(i) + ".gz",1);
        }
    }
    
    @Dado("^que todos os arquivos de cliques já foram processados anteriormente$")
    public void allClicksFileProcessed() throws Exception {
    	 for (int i = 0; i < createdClickFileNameList.size(); i++) {
             String repo = RemoteConfig.getInstance().getConsolidatorRepositories().get("clicks").get(0);
             TestPrepare.insertClickLog(repo + createdClickFileNameList.get(i) + ".gz",1);
         }
    }
    
    @Dado("^que existe apenas um arquivo de cliques a ser processado vazio$")
    public void createClickEmptyFile() throws Exception{
    	createdViewsFileNameList.add(TestPrepare.createGzipFile("", "clicks"));
    	expectedNumberOfParserErrors = 1l;
    }
    
    @Dado("^que existe apenas um arquivo de cliques a ser processado sem permissão de leitura$")
    public void clickFileWithoutPermission() throws Exception {
    	expectedNumberOfParserErrors = 1L;
    	
    	createdClickFileName = TestPrepare.createClicksFile(Collections.singleton(dimensionForView));
    	TestPrepare.setPermissionDeniedGzipFile(createdClickFileName, "clicks");
    	
        createdClickFileNameList.add(createdClickFileName);
    }
    
    @Dado("^que nao existe arquivo de cliques a ser processado$")
    public void clickFileNotFound() throws Exception {
        TestPrepare.deleteGzipFiles();
    }
    
    @Dado("^que existe algum arquivo de cliques a ser processado que nao possui o parametro <(.*)>$")
    public void createClicksFileWithParameterMissing(String parameterDescription) throws Exception {
        final String parameterName = parameterDescriptionToName(parameterDescription);
        TestPrepare.createClicksFileWithoutParameter(Collections.singleton(dimensionForClick), parameterName);
        dimensionForClick.setValid(false);
    }

    @Dado("^que existe um arquivo de cliques com <(APENAS UM|MAIS DE UM)> clique a ser processado$")
    public void createClickFile(String qtdLines) throws Exception {
        TestPrepare.deleteGzipFiles();
        if (qtdLines.equals("APENAS UM")) {
            dimensionForClick.setQuantity(1L);
            createdClickFileName = TestPrepare.createClicksFile(Collections.singleton(dimensionForClick));
        } else {
            dimensionForClick.setQuantity(TestPrepare.getRandomQuantity());
            createdClickFileName = TestPrepare.createClicksFile(Collections.singleton(dimensionForClick));
        }
        createdClickFileNameList.add(createdClickFileName);
    }

    @Dado("^que o arquivo de cliques ainda não foi processado$")
    public void clickFileNotProcessed() throws Exception {
        List<ClickLog> clickLogList = TestPrepare.getClickLogList(createdClickFileNameList);
        if (clickLogList.size() > 0) {
            TestPrepare.deleteClickLogList(createdClickFileNameList);
        }
    }
    
    @Dado("^que existe algum arquivo de cliques a ser processado$")
    public void createClicksFile() throws Exception {
        TestPrepare.createClicksFile(Collections.singleton(dimensionForClick));
    }

    @Dado("^que existe algum arquivo de cliques a ser processado com problema no parametro <(.*)> com valor <(.*)>$")
    public void createClicksFileWithParameterProblems(String parameterDescription, String parameterProblem) throws Exception {
        final String parameterName = parameterDescriptionToName(parameterDescription);

        switch (parameterProblem) {
        case "invalido":
            TestPrepare.createClicksFileWithInvalidParameter(Collections.singleton(dimensionForClick), parameterName);
            break;
        case "vazio":
            TestPrepare.createClicksFileWithEmptyParameter(Collections.singleton(dimensionForClick), parameterName);
            break;
        default:
            throw new IllegalArgumentException("problema nao mapeado: " + parameterProblem);
        }

        dimensionForClick.setValid(false);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com cliques para um dia diferente$")
    public void createClickDataForAnotherDay() throws Exception {
        final Date originalDay = dimensionForClick.getDate();
        final Date anotherDay = TestPrepare.getAnotherDay(originalDay);

        dimensionForClick.setDate(anotherDay);
        createClickData();
        dimensionForClick.setDate(originalDay);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com cliques para um slot diferente$")
    public void createClickDataForAnotherSlot() throws Exception {
        final Long originalSlot = dimensionForClick.getSlotId();
        final Long anotherSlot = TestPrepare.getAnotherSlotId(originalSlot);

        dimensionForClick.setSlotId(anotherSlot);
        createClickData();
        dimensionForClick.setSlotId(originalSlot);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com cliques para uma venda diferente$")
    public void createClickDataForAnotherSale() throws Exception {
        final Long originalSale = dimensionForClick.getPackSaleId();
        final Long anotherSale = TestPrepare.getAnotherPackSaleId(originalSale);

        dimensionForClick.setPackSaleId(anotherSale);
        createClickData();
        dimensionForClick.setPackSaleId(originalSale);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com cliques para uma hora diferente$")
    public void createClickDataForAnotherHour() throws Exception {
        final Integer originalHour = dimensionForClick.getHour();
        final Integer anotherHour = TestPrepare.getAnotherHour(originalHour);

        dimensionForClick.setHour(anotherHour);
        createClickData();
        dimensionForClick.setHour(originalHour);
    }
}
