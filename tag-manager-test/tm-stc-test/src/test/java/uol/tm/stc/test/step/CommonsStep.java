package uol.tm.stc.test.step;

import org.junit.Assert;

import uol.tm.stc.test.domain.PageContent;
import uol.tm.stc.test.domain.TmScript;
import uol.tm.stc.test.utils.TestHelper;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

/**
 * 
 * @author dvrocha
 */
public class CommonsStep extends BaseStep {

	@Before
	public void before() throws Exception {
		template = null;
		tmScript = new TmScript();
		pageContent = new PageContent();
	}

	@Dado("^que o script do UOL Tag Manager foi inserido no corpo do HTML$")
	public void putTmScriptInHtmlBody() throws Exception {
		template = TestHelper.getTmBody();
	}

	@Dado("^que o script do UOL Tag Manager foi inserido no cabeçalho do HTML$")
	public void putTmScriptInHtmlHead() throws Exception {
		template = TestHelper.getTmHead();
	}

	@Quando("^a página contendo o UOL Tag Manager for carregada$")
	public void loadPage() throws Exception {
		TestHelper.writePage(template, tmScript, pageContent);
		page.load();
	}
	
	@Então("^o sistema rigistra a versão do UOL Tag Manager no console do browser$")
	public void checkTmVersion() throws Exception {
		Assert.assertTrue("Versão não foi registrada em log", page.findLogVersion());
	}

	@Então("^o sistema registra no console do browser a seguinte mensagem de WARN '(.+)'$")
	public void checkLogWarn(String message) throws Exception {
		Assert.assertTrue("Mensagem de WARN não foi registrada em log.", page.findLogWarn(message));
	}
}
