package test.cucumber;

import cucumber.annotation.pt.Quando;

public class CsvWhenSteps extends AbstractServicesStep {

    @Quando("^efetuar requisicao para evento de clique com idturl \"(.+)\", label \"([^\"]*)\" e source \"(.+)\"")
    public void efetuaRequisicaoClique(String idtUrl,
                                       String idtLabel,
                                       String source) {
        System.out.println(String.format("\n --> Quando efetuar requisicao para evento de clique com idturl %s, label %s e source %s", idtUrl, idtLabel, source ));
        
        localServiceStepsImpl.makeRequestToClickEvent(idtUrl, idtLabel, source);
    }

    @Quando("^efetuar requisicao para evento de indicacao com caf \"(.+)\" label \"([^\"]*)\" e source \"(.+)\"")
    public void efetuaRequisicaoIndicacao(String caf,
                                          String idtLabel,
                                          String source) {
        System.out.println(String.format("\n --> Quando efetuar requisicao para evento de indicacao com caf %s, label %s e source %s", caf, idtLabel, source ));
        
        localServiceStepsImpl.makeRequestToIndicationEvent(caf, idtLabel, source);
    }
}