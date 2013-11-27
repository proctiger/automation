package uol.test.step;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;


public class CucumberDescribeFeaturesGiven extends CucumberAbstractSteps {
 
	
	@Before
	public void startTest() throws Exception
	{
		result = new StringBuilder(1000);
	}
	
	@Dado("^uma determinada configuracao inicial, geralmente baseada em uma entrada <(.+)>$")
	public void firstConfig(String entrada) throws Throwable {
	    System.out.println(String.format("> Dada uma determinada configuracao inicial, geralmente baseada em uma entrada %s", entrada));
		result.append(entrada);
		System.out.println(String.format("< Dada uma determinada configuracao inicial, geralmente baseada em uma entrada %s", entrada));
	}

	@Dado("^que essa configuracao envolva outro fator$")
	public void secondConfig() throws Throwable {
		System.out.println("> Dado que essa configuracao envolva outro fator");
		result.trimToSize();
		System.out.println("< Dado que essa configuracao envolva outro fator");
	}
}