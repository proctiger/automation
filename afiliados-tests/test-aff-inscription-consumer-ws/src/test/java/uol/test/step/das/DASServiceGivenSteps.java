package uol.test.step.das;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.pt.Dado;

public class DASServiceGivenSteps extends AbstractDASServiceSteps {

    @Dado("^que a variavel <(.+)>, codigo <(.+)>, esteja vazia para a assinatura <(.+)>$")
    public void rescue(String chave, Integer codigo, Long idtInscription) throws Exception {
    	System.out.println("\n  -> Dado que a variavel <"+ chave +"> , codigo <"+ codigo +">, esteja vazia para a assinatura <"+ idtInscription +"> \n");
        try {
            List<Map<String, Object>> data = subscriptionService.getExtraInscriptionData(idtInscription);
            if (data != null && data.size() > 0) {
                for (Map<String, Object> map : data) {
                    if (chave.equals(map.get(INFO_TYPE))) {
                        subscriptionService.deleteExtraInscriptionData(idtInscription, codigo);
                        System.out.println("Deletando Inscription: "+idtInscription);
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail(String.format("Falha na verificacao/exclusao da variavel %s, devido a exception: %s", chave, e.getLocalizedMessage()));
        }
        System.out.println("\n  <- Dado que a variavel <"+ chave +"> , codigo <"+ codigo +">, esteja vazia para a assinatura <"+ idtInscription +"> \n");
    }
}