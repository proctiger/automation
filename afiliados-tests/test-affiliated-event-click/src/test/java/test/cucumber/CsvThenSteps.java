package test.cucumber;

import cucumber.annotation.pt.Entao;

public class CsvThenSteps extends AbstractServicesStep {

    @Entao("^o cookie deve ser plantado")
    public void cookieDeveExistir() {
        System.out.println("\n --> Entao o cookie deve ser plantado");
        
        localServiceStepsImpl.verifyCookieExistence();
    }

    @Entao("^o valor gerado no redis deve ser \"(.+)\"")
    public void redisValue(String expectedValue) {
        System.out.println("\n --> E o valor gerado no redis deve ser " + expectedValue);
        
        localServiceStepsImpl.verifyRedisValue(expectedValue);
    }

    @Entao("^a quantidade de eventos persistidos na tabela AE_EVENT_LOG apos a requisicao deve aumentar em uma unidade")
    public void calculaDiferencaQuantidadeRegistros() {
        System.out.println("\n --> E a quantidade de eventos persistidos na tabela AE_EVENT_LOG apos a requisicao deve aumentar em uma unidade");
        
        localServiceStepsImpl.checkDifferenceOfRecordsAfterRequest();
    }
}
