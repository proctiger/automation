package uol.test.step.service;

import cucumber.api.java.pt.Quando;

public class LocalServiceWhenSteps extends AbstractLocalServiceSteps {

    @Quando("^efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados <(.+)>$")
    public void notifyEvent(final String dadosComplementares) {
        LOGGER.info("Dados de Cadastro: "+dadosComplementares); 
        super.notifyEvent(dadosComplementares);
    }
}