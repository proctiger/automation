package uol.tm.stc.test.step;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import uol.tm.stc.test.domain.SigaTag;
import uol.tm.stc.test.utils.Constants;
import uol.tm.stc.test.utils.TestHelper;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;

/**
 * 
 * @author dvrocha
 */
public class SigaStep extends BaseStep {

	private static final int NUM_MAX_UOL_CLIQUES_ADS = 3;

	@Dado("^que o usuário possui o modulo de UOL Cliques habilitado$")
	public void enableUolCliquesModule() {
		tmScript.setName("cliques-custom");
	}

	@Dado("^que o usuário adicionou um anúncio de UOL Cliques na página$")
	public void addUolCliquesTag() {
		TestHelper.addUolCliquesTagInPageContent(pageContent, TestHelper.getDefaultLpTag());
	}
	
	@Dado("^que o usuário adicionou um anúncio padrão de UOL Cliques na página$")
	public void addDefaultUolCliquesTag() {
		addUolCliquesTag();
	}
	
	@Dado("^que o usuário adicionou outro anúncio padrão de UOL Cliques na página$")
	public void addOtherDefaultUolCliquesTag() {
		addUolCliquesTag();
	}
	

	@Dado("^que o usuário adicionou a quantidade máxima de anúncios de UOL Cliques na página$")
	public void addMaxUolCliquesTag() {
		for (int i = 0; i < NUM_MAX_UOL_CLIQUES_ADS; i++) {
			addUolCliquesTag();
		}
	}

	@Dado("^que o usuário adicionou mais anúncios de UOL Cliques que a quantidade máxima permitida$")
	public void addMoreThanMaxUolCliquesTag() {
		for (int i = 0; i <= NUM_MAX_UOL_CLIQUES_ADS; i++) {
			addUolCliquesTag();
		}
	}

	@Dado("^que o usuário adicionou um anúncio de UOL Cliques na página sem a classe 'tm-ads'$")
	public void setUolCliquesTagWithoutClass() {
		SigaTag tag = TestHelper.getDefaultLpTag();
		tag.setClasses("hide");
		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}

	@Dado("^que o usuário adicionou um anúncio de UOL Cliques na página com mais de uma classe$")
	public void setUolCliquesTagWithMoreThanOneClass() {
		SigaTag tag = TestHelper.getDefaultLpTag();
		tag.setClasses(tag.getClasses() + " other");
		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}

	@Dado("^que o usuário informou a tag de UOL Cliques sem o atributo 'siga'$")
	public void setUolCliquesTagWithoutAttributeUolCliques() {
		SigaTag tag = TestHelper.getDefaultLpTag();
		tag.setProperties(null);
		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}

	@Dado("^que o usuário informou a tag de UOL Cliques com o atributo 'siga' sem o parâmetro 'size'$")
	public void setUolCliquesTagWithoutAdSize() {
		SigaTag tag = TestHelper.getDefaultLpTag();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bgColor", "C0CADA");
		tag.setProperties(map);
		
		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}

	@Dado("^que o usuário informou a tag de UOL Cliques com o atributo 'siga' com o parâmetro 'size' inexistente$")
	public void setUolCliquesTagWithInvalidAdSize() {
		SigaTag tag = TestHelper.getDefaultLpTag();

		Map<String, String> map = new HashMap<String, String>();
		map.put("size", "1x1");
		tag.setProperties(map);

		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}

	@Dado("^que o código do usuário para o UOL Cliques não foi definido$")
	public void enableUolCliquesModuleWithoutUserCode() {
		tmScript.setName("cliques");
	}

	@Dado("^que o código do usuário para o UOL Cliques está definido na configuração da página$")
	public void setUserCodeInLpConfig() {
		enableUolCliquesModuleWithoutUserCode();
		TestHelper.addUolCliquesConfig(tmScript, "coddisplaysupplier", Constants.UOL_CLIQUES_AFFILIATED_ID);
	}
	
	@Dado("^que o código do usuário para o UOL Cliques está definido na configuração do Tag Manager$")
	public void setUserCodeInTagManager() {
		enableUolCliquesModule();
	}
	
	@Dado("^que o usuário adicionou um anúncio de UOL Cliques na página com o código do usuário no atributo 'siga'$")
	public void setUolCliquesTagWithUserCode() {
		enableUolCliquesModuleWithoutUserCode();
		SigaTag tag = TestHelper.getDefaultLpTag();
		TestHelper.setMapEntry(tag.getProperties(), "coddisplaysupplier", Constants.UOL_CLIQUES_AFFILIATED_ID);
		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}
	
	@Dado("^que o usuário possui parâmetros de personalização do anúncio de UOL Cliques na configuração da página$")
	public void setCustomAdInLpConfig() {
		TestHelper.addUolCliquesConfig(tmScript, "coddisplaysupplier", Constants.UOL_CLIQUES_AFFILIATED_ID);
		TestHelper.addUolCliquesConfig(tmScript, "borderColor", "0A0A0A");
		TestHelper.addUolCliquesConfig(tmScript, "bgColor", "AAAA00");
		TestHelper.addUolCliquesConfig(tmScript, "urlColor", "00FF00");
		TestHelper.addUolCliquesConfig(tmScript, "altColor", "F1123A");
	}
	
	@Dado("^que o usuário possui parâmetros de personalização do anúncio de UOL Cliques na configuração do Tag Manager$")
	public void setCustomAdInTmConfig() {
		enableUolCliquesModule();
	}
	
	@Dado("^que o usuário adicionou um anúncio de UOL Cliques na página com parâmetros personalizados$")
	public void setUolCliquesTagWithCustomAd() {
		SigaTag tag = TestHelper.getDefaultLpTag();
		TestHelper.setMapEntry(tag.getProperties(), "borderColor", "1111FF");
		TestHelper.setMapEntry(tag.getProperties(), "bgColor", "010101");
		TestHelper.setMapEntry(tag.getProperties(), "urlColor", "123456");
		TestHelper.setMapEntry(tag.getProperties(), "descrColor", "F55503");
		TestHelper.addUolCliquesTagInPageContent(pageContent, tag);
	}

	@Então("^o sistema exibe o[s]? anúncio[s]? de UOL Cliques do usuário$")
	public void checkUolCliques() throws Exception {
		if (pageContent.getUolcliquesTags() != null) {
			int currAd = 0;

			for (SigaTag tag : pageContent.getUolcliquesTags()) {
				SigaTag displayedAd = page.getUolCliquesAdById(tag.getId());

				if (currAd >= NUM_MAX_UOL_CLIQUES_ADS) {
					Assert.assertNull("Anúncio de UOL Cliques foi exibido indevidamente", displayedAd);

				} else {
					validateDefaultProperties(displayedAd);

					Assert.assertEquals("Valor da propriedade adBlockCount é inválido", String.valueOf(currAd),
					        displayedAd.getProperties().get("adBlockCount"));

					Map<String, String> expectedProperties = new HashMap<String, String>();
					incrementMap(expectedProperties, tmScript.getSigaConfig());
					incrementMap(expectedProperties, tag.getProperties());
					incrementMap(expectedProperties, page.getSigaProperties());
					

					for (Map.Entry<String, String> entry : expectedProperties.entrySet()) {
						Assert.assertEquals("Propriedade [" + entry.getKey() + "] possui valor inválido",
						        entry.getValue(), displayedAd.getProperties().get(entry.getKey()));
					}
				}

				currAd++;
			}
		}
	}

	@Então("^o sistema não exibe o anúncio de UOL Cliques do usuário$")
	public void checkInvalidUolCliquesTag() throws Exception {
		Assert.assertTrue("Quantidade de tags do UOL cliques diferente de 1", pageContent.getUolcliquesTags() != null
		        && pageContent.getUolcliquesTags().size() == 1);

		SigaTag tag = pageContent.getUolcliquesTags().get(0);
		Assert.assertNull("Tag do UOL Cliques foi exibida indevidamente", page.getUolCliquesAdById(tag.getId()));
	}

	private void validateDefaultProperties(SigaTag displayedAd) {
		Assert.assertNotNull("Anúncio de UOL Cliques não foi encontrado", displayedAd);

		Assert.assertNotNull("Anúncio de UOL Cliques não possui propriedades", displayedAd.getProperties());

		Assert.assertTrue("Anúncio de UOL Cliques não possui a propriedade numads", displayedAd.getProperties()
		        .containsKey("numads"));

		Assert.assertTrue("Anúncio de UOL Cliques não possui a propriedade urlReferer", displayedAd.getProperties()
		        .containsKey("urlReferer"));

		Assert.assertTrue("Anúncio de UOL Cliques não possui a propriedade adBlockCount", displayedAd.getProperties()
		        .containsKey("adBlockCount"));
	}

	private void incrementMap(Map<String, String> to, Map<String, String> from) {
		if (from != null) {
			for (Map.Entry<String, String> entry : from.entrySet()) {
				to.put(entry.getKey(), entry.getValue());
			}
		}
	}
}
