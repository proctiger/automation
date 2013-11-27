package uol.test.step.das;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.pt.Quando;

public class DASServiceWhenSteps extends AbstractDASServiceSteps{
	@Quando("^salvar o valor <(.+)> na variavel <(.+)> para a assinatura <(.+)>$")
    public void persist(String value, String key, Long idtInscription) throws Exception {
    	System.out.println("\n -> Quando salvar o valor <"+value+"> na variavel <"+key+"> para a assinatura <"+idtInscription+">\n");
		Map<String, String> map = new HashMap<String, String>(1);
    	map.put(key, value);
    	subscriptionService.persistExtraInscriptionData(idtInscription, map);    
    	System.out.println("\n <- Quando salvar o valor <"+value+"> na variavel <"+key+"> para a assinatura <"+idtInscription+">\n");
	}
}