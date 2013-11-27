package uol.test.step;

import org.hamcrest.core.Is;
import org.junit.Assert;

import cucumber.api.java.pt.Entao;


public class CucumberDescribeFeaturesThen extends CucumberAbstractSteps {


	/** Exemplo:
	 * 
	 * 
				@Entao("^$")
				public void setupPreCondition()
				{
				 
				}
	*/

	@Entao("^o resultado desse evento deveria ser o esperado, geralmente baseado em uma saida <(.+)>$")
	public void verifyOutput(String saida) throws Throwable {
		System.out.println(String.format("> Entao o resultado desse evento deveria ser o esperado, geralmente baseado em uma saida %s", saida));
		Assert.assertThat(result.toString(), Is.is(saida));
		System.out.println(String.format("< Entao o resultado desse evento deveria ser o esperado, geralmente baseado em uma saida %s", saida));
	}
}