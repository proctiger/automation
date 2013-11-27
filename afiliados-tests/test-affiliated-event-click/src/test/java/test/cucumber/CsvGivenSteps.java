package test.cucumber;

import cucumber.annotation.pt.Dado;

public class CsvGivenSteps extends AbstractServicesStep {

    @Dado("^que temos eventos persistidos na tabela AE_EVENT_LOG")
    public void buscandoQuantidadeRegistros() {
        System.out.println("\n --> Dado que temos eventos persistidos na tabela AE_EVENT_LOG \n");
        
        localServiceStepsImpl.checkRecordsQuantityBeforeRequest();
    }
}
