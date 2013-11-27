package uol.test.step.service;

import cucumber.api.java.After;
import cucumber.api.java.pt.Quando;

public class LocalServiceWhenSteps extends AbstractLocalServiceSteps {
	
    @Quando("^efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <(.+)>$")
    public void notifyEvent(final String dadosComplementares) {
        System.out.println("\n  -> Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <"+ dadosComplementares +">\n");
    	super.notifyEvent(dadosComplementares);
    	System.out.println("\n  <- Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <"+ dadosComplementares +">\n");
    }
    
    @Quando("^o componente consumidor estiver inoperante$")
    public void fakeConsumerInoperability() throws Exception{
    	System.out.println("\n -> Quando o componente consumidor estiver inoperante \n ");
    	stopApplication("aff-inscription-consumer", "jboss","a1-jesse-s-prt1");
    	System.out.println("\n <- Quando o componente consumidor estiver inoperante \n ");
    }
    
    @After
    public void reconfigureRC() throws Exception {
    	System.out.println("\n\n --------------------------------- Fim - Cenario --------------------------------- \n\n");
    	if (fakeRC) {
            System.out.println("Tentando reconfigurar o Remote Config.");
            realRC();
        } else {
            System.out.println("Sem necessidade de reconfigurar o Remote Config.");
        }
    }
}