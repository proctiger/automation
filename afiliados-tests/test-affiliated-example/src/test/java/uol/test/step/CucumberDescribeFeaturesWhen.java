package uol.test.step;

import cucumber.api.java.pt.Quando;


public class CucumberDescribeFeaturesWhen  extends CucumberAbstractSteps {


	/**Exemplo:
	
    @Quando("^$")
	public void setupPreCondition()
	{
				 
	}
    
    
    */
    

	@Quando("^um determinado evento ocorrer$")
	public void callTestingThing() throws Throwable {
		System.out.println("> Quando um determinado evento ocorrer");
		System.out.println(String.format("=====================result = [%s]====================", result));
		result = new StringBuilder(result.toString().toUpperCase());
		System.out.println("< Quando um determinado evento ocorrer");
	}

	@Quando("^esse evento for afetado por outro fator$")
	public void esse_evento_for_afetado_por_outro_fator() throws Throwable {
		System.out.println("> Quando esse evento for afetado por outro fator");
		result.reverse();
		System.out.println("< Quando esse evento for afetado por outro fator");
	}	
}