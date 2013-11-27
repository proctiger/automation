package uol.test.step.das;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.pt.Entao;


public class DASServiceThenSteps extends AbstractDASServiceSteps{
	
	@Entao("^verifique se o resultado obtido pela variavel <(.+)> para a assinatura <(.+)> e igual a <(.+)>$")
	public void assertEqualsData(String key, Long idtInscription, String value) throws Exception {
		System.out.println("\n ->Entao verifique se o resultado obtido pela variavel <"+key+"> para a assinatura <"+idtInscription+"> e igual a <"+value+"> \n");
		List<Map<String, Object>> data = subscriptionService.getExtraInscriptionData(idtInscription);
		System.out.println("Buscando os dados da Inscription: "+idtInscription);
		Assert.assertNotNull("Nao foi retornado valor pelo servico DAS", data);
		Assert.assertFalse("A lista retornada pelo servico DAS esta vazia", data.isEmpty());
		for(Map<String, Object> map: data){
			/*
			  {idtInscription=2098417, 
			   desInscAdditInfoValue=IM A FAKE, 
			   namInscAdditInfoType=affiliatedInfo
			  }
			*/
			if(key.equals(map.get(INFO_TYPE))){
				Assert.assertEquals(value, map.get("desInscAdditInfoValue"));
				System.out.println("\n <- Entao verifique se o resultado obtido pela variavel <"+key+"> para a assinatura <"+idtInscription+"> e igual a <"+value+"> \n");
				return;
			}
		}
		Assert.fail("Nao foi encontrado o Map com chave + valor esperados.");
		System.out.println("\n <- Entao verifique se o resultado obtido pela variavel <"+key+"> para a assinatura <"+idtInscription+"> e igual a <"+value+"> \n");
	}
}