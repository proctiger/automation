package uol.admanager.consolidator.test.steps.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.consolidator.test.reconf.RemoteConfig;
import uol.admanager.entity.Dimension;
import uol.admanager.entity.View;
import uol.admanager.entity.ViewLog;
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

    @Dado("^que existe apenas um arquivo de impressões <(Legacy|Novo)> a ser processado vazio$")
    public void createViewEmptyFile(String fileType) throws Exception{
    	String repoType = "";
    	if (fileType.equalsIgnoreCase("Legacy")){
    		repoType = "legacy-views";
    	}else{
    		repoType = "views";
    	}
    	createdViewsFileNameList.add(TestPrepare.createGzipFile("", repoType));
    	expectedNumberOfParserErrors = 1l;
    }

    @Dado("^que existe apenas um arquivo de impressões <(Legacy|Novo)> a ser processado sem permissão de leitura$")
    public void viewFileLegacyWithoutPermission(String fileType) throws Exception {
        expectedNumberOfParserErrors = 1L;
    	if (fileType.equalsIgnoreCase("Legacy")){
        createdViewsFileName = TestPrepare.createViewsLegacyFile(Collections.singleton(dimensionForView));
    		TestPrepare.setPermissionDeniedGzipFile(createdViewsFileName, "legacy-views");
    	}else{
    		createdViewsFileName = TestPrepare.createViewsFile(Collections.singleton(dimensionForView));
    		TestPrepare.setPermissionDeniedGzipFile(createdViewsFileName, "views");
    	}
        createdViewsFileNameList.add(createdViewsFileName);
    }

    @Dado("^que nao existe arquivo de impressões <(Legacy|Novo)> a ser processado$")
    public void viewFileNotFound(String fileType) throws Exception {
        TestPrepare.deleteGzipFiles();
    }

    @Dado("^que o arquivo de impressões ainda não foi processado$")
    public void viewFileNotProcessed() throws Exception {
        List<ViewLog> viewLogList = TestPrepare.getViewLogList(createdViewsFileNameList);
        if (viewLogList.size() > 0) {
            TestPrepare.deleteViewLogList(createdViewsFileNameList);
        }
    }

    @Dado("^que existe um arquivo de impressões com <(APENAS UM|MAIS DE UM)> um anuncio a ser processado$")
    public void createViewFileWithhMoreThanOneLine(String qtdLines) throws Exception {
        TestPrepare.deleteGzipFiles();
        if (qtdLines.equals("APENAS UM")) {
            dimensionForView.setQuantity(1L);
            createdViewsFileName = TestPrepare.createViewsFile(Collections.singleton(dimensionForView));
        } else {
            dimensionForClick.setQuantity(TestPrepare.getRandomQuantity());
            createdViewsFileName = TestPrepare.createViewsFile(Collections.singleton(dimensionForView));
        }
        createdViewsFileNameList.add(createdViewsFileName);
    }

    @Dado("^que existe algum arquivo de impressões <Legacy> a ser processado$")
    public void createLegacyViewsFile() throws Exception {
        TestPrepare.createViewsLegacyFile(Collections.singleton(dimensionForView));
    }

    @Dado("^que o arquivo de impressões <Legacy> ainda não foi processado$")
    public void viewLegacyFileNotProcessed() throws Exception {
        List<ViewLog> viewLogList = TestPrepare.getViewLogList(createdViewsFileNameList);
        for (ViewLog viewLog : viewLogList) {
            Assert.assertNull("Arquivo de impressao já foi processado: " + createdViewsFileName, viewLog);
        }
    }

    @Dado("^que existe apenas um arquivo de impressões <(Legacy|Novo)> a ser processado$")
    public void createOneLegacyViewsFile(String fileType) throws Exception {
    	
        TestPrepare.deleteGzipFiles();
        Set<Dimension> singleton = Collections.singleton(dimensionForView);
        expectedDimension = new ArrayList<>(singleton).get(0);

    	if (fileType.equals("Legacy")){
        createdViewsFileName = TestPrepare.createViewsLegacyFile(singleton);
    	}else{
    		createdViewsFileName = TestPrepare.createViewsFile(singleton);
    	}

        createdViewsFileNameList.add(createdViewsFileName);
    }

    @Dado("^que existe um arquivo de impressões <Legacy> com <(APENAS UM|MAIS DE UM)> um anuncio a ser processado$")
    public void createLegacyViewFilehMoreThanOneLine(String qtdLines) throws Exception {
        TestPrepare.deleteGzipFiles();
        if (qtdLines.equals("APENAS UM")) {
            dimensionForView.setQuantity(1L);
            createdViewsFileName = TestPrepare.createViewsLegacyFile(Collections.singleton(dimensionForView));
        } else {
            dimensionForClick.setQuantity(TestPrepare.getRandomQuantity());
            createdViewsFileName = TestPrepare.createViewsLegacyFile(Collections.singleton(dimensionForView));
        }
        createdViewsFileNameList.add(createdViewsFileName);
    }

    @Dado("^que existe mais de um arquivo de impressões <(Legacy|Novo)> a ser processado$")
    public void createLegacyViewFileMoreThanOneFile(String fileType) throws Exception {
    	
        Set<Dimension> singleton = Collections.singleton(dimensionForView);
        expectedDimension = new ArrayList<>(singleton).get(0);
        Long randomQuantity = TestPrepare.getRandomQuantity();
        if (fileType.equalsIgnoreCase("Legacy")){
            for (int i = 0; i < randomQuantity; i++) {
                createdViewsFileNameList.add(TestPrepare.createViewsLegacyFile(singleton));
            }
        }else{
            for (int i = 0; i < randomQuantity; i++) {
                createdViewsFileNameList.add(TestPrepare.createViewsFile(singleton));
            }
        }
    }

    @Dado("^que existe algum arquivo de impressões <Legacy> a ser processado com problema no <(.*)> com valor <(.*)>$")
    public void createLegacyViewsFileWithParameterProblems(String parameterDescription, String parameterProblem) throws Exception {
        final String parameterName = parameterDescriptionToName(parameterDescription);

        switch (parameterProblem) {
        case "invalido":
            TestPrepare.createViewsLegacyFileWithInvalidParameter(Collections.singleton(dimensionForView), parameterName);
            break;
        case "vazio":
            TestPrepare.createViewsLegacyFileWithEmptyParameter(Collections.singleton(dimensionForView), parameterName);
            break;
        default:
            throw new IllegalArgumentException("problema nao mapeado: " + parameterProblem);
        }

        dimensionForView.setValid(false);
    }

    @Dado("^que existe algum arquivo de impressões <Legacy> a ser processado que nao possui o parametro <(.*)>$")
    public void createLegacyViewsFileWithParameterMissing(String parameterDescription) throws Exception {
        final String parameterName = parameterDescriptionToName(parameterDescription);
        TestPrepare.createViewsLegacyFileWithoutParameter(Collections.singleton(dimensionForView), parameterName);
        dimensionForView.setValid(false);
    }

    @Dado("^que existe algum arquivo de impressões a ser processado$")
    public void createViewsFile() throws Exception {
        TestPrepare.createViewsFile(Collections.singleton(dimensionForView));
    }

    @Dado("^que existe algum arquivo de impressões a ser processado com problema no <(.*)> com valor <(.*)> da trinca <(.*)> da URL$")
    public void createViewsFileWithParameterProblems(String parameterDescription, String parameterProblem, String position) throws Exception {
        final String parameterName = parameterDescriptionToName(parameterDescription);
        final Dimension dimensionWithProblem = dimensionForView.copy();
        final Collection<Dimension> dimensions;

        dimensionWithProblem.setValid(false);
        expectedNumberOfParserErrors += dimensionWithProblem.getQuantity();

        switch (position) {
        case "1":
            dimensions = Arrays.asList(dimensionWithProblem, dimensionForView);
            break;
        case "2":
            dimensions = Arrays.asList(dimensionForView, dimensionWithProblem);
            break;
        case "1 e 2":
            dimensionForView.setValid(false);
            dimensions = Arrays.asList(dimensionForView, dimensionWithProblem);
            break;
        default:
            throw new RuntimeException("posicao da trinca invalida na URL nao mapeada: " + position);
        }

        switch (parameterProblem) {
        case "invalido":
            TestPrepare.createViewsFileWithInvalidParameter(dimensions, parameterName);
            break;
        case "vazio":
            TestPrepare.createViewsFileWithEmptyParameter(dimensions, parameterName);
            break;
        default:
            throw new RuntimeException("problema nao mapeado: " + parameterProblem);
        }
    }

    @Dado("^que existe algum arquivo de impressões a ser processado sem o parametro <(.*)> da trinca <(.*)> da URL$")
    public void createViewsFileWithMissingParameter(String parameterDescription, String position) throws Exception {
        final String parameterName = parameterDescriptionToName(parameterDescription);
        final Dimension dimensionWithProblem = dimensionForView.copy();
        final Collection<Dimension> dimensions;

        dimensionWithProblem.setValid(false);
        expectedNumberOfParserErrors += dimensionWithProblem.getQuantity();

        switch (position) {
        case "1":
            dimensions = Arrays.asList(dimensionWithProblem, dimensionForView);
            break;
        case "2":
            dimensions = Arrays.asList(dimensionForView, dimensionWithProblem);
            break;
        case "1 e 2":
            dimensionForView.setValid(false);
            dimensions = Arrays.asList(dimensionForView, dimensionWithProblem);
            break;
        default:
            throw new RuntimeException("posicao da trinca invalida na URL nao mapeada: " + position);
        }

        TestPrepare.createViewsFileWithoutParameter(dimensions, parameterName);
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

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com impressões para um dia diferente$")
    public void createViewDataForAnotherDay() throws Exception {
        final Date originalDay = dimensionForView.getDate();
        final Date anotherDay = TestPrepare.getAnotherDay(originalDay);

        dimensionForView.setDate(anotherDay);
        createViewData();
        dimensionForView.setDate(originalDay);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com impressões para um slot diferente$")
    public void createViewDataForAnotherSlot() throws Exception {
        final Long originalSlot = dimensionForView.getSlotId();
        final Long anotherSlot = TestPrepare.getAnotherSlotId(originalSlot);

        dimensionForView.setSlotId(anotherSlot);
        createViewData();
        dimensionForView.setSlotId(originalSlot);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com impressões para uma venda diferente$")
    public void createViewDataForAnotherSale() throws Exception {
        final Long originalSale = dimensionForView.getPackSaleId();
        final Long anotherSale = TestPrepare.getAnotherPackSaleId(originalSale);

        dimensionForView.setPackSaleId(anotherSale);
        createViewData();
        dimensionForView.setPackSaleId(originalSale);
    }

    @Dado("^que já existe um registro no banco para o anúncio a ser processado com impressões para uma hora diferente$")
    public void createViewDataForAnotherHour() throws Exception {
        final Integer originalHour = dimensionForView.getHour();
        final Integer anotherHour = TestPrepare.getAnotherHour(originalHour);

        dimensionForView.setHour(anotherHour);
        createViewData();
        dimensionForView.setHour(originalHour);
    }

    @Dado("^que o arquivo de impressões <(.+)> já foi processado anteriormente$")
    public void oneLegacyLogProcessed(String fileType) throws Exception {
        String repo = "";
    	if (fileType.equals("Legacy")){
        	repo = RemoteConfig.getInstance().getConsolidatorRepositories().get("legacy-views").get(0);
        }else{
        	repo = RemoteConfig.getInstance().getConsolidatorRepositories().get("views").get(0);
        }
    	
        TestPrepare.insertViewLog(repo + createdViewsFileName + ".gz");
    }

    @Dado("^que algum arquivo de impressões <(Legacy|Novo)> já foi processado anteriormente$")
    public void someLegacyLogProcessed(String fileType) throws Exception {
        for (int i = 0; i < createdViewsFileNameList.size() - 1; i++) {
        	String repoType = "";
        	if (fileType.equalsIgnoreCase("Legacy")){
        		repoType = "legacy-views";
        	} else{
        		repoType = "views";
        	}
            String repo = RemoteConfig.getInstance().getConsolidatorRepositories().get(repoType).get(0);
            TestPrepare.insertViewLog(repo + createdViewsFileNameList.get(i) + ".gz");
        }
    }

    @Dado("^que todos os arquivos de impressões <(Legacy|Novo)> já foram processados anteriormente$")
    public void allViewLegacyFileProcessed(String fileType) throws Exception {
    	String repoType = "";
    	if (fileType.equalsIgnoreCase("Novo")){
    		repoType = "views";
    	}else{
    		repoType = "legacy-views";
    	}
    		
        for (int i = 0; i < createdViewsFileNameList.size() ; i++) {
            String repo = RemoteConfig.getInstance().getConsolidatorRepositories().get(repoType).get(0);
            TestPrepare.insertViewLog(repo + createdViewsFileNameList.get(i) + ".gz");
        }
    }

    @Dado("^que nao existem anuncios processados para os arquivos de impressao$")
    public void deleteViews() throws Exception {
        TestPrepare.deleteAllDimensionData();
        TestPrepare.deleteAllViewData();
    }
}
