package uol.admanager.consolidator.test.steps.commons;

import org.junit.Assert;

import uol.admanager.consolidator.test.prepare.TestPrepare;
import uol.admanager.consolidator.test.steps.view.TestStep;
import cucumber.api.java.pt.Entao;

public class ThenTestStep extends TestStep{
	
	  @Entao("^o sistema exibe o erro no arquivo de log$")
	    public void validateParseErrorLogging() throws Exception {
	        final Long actualNumberOfParseErrors = TestPrepare.countParseErrors();
	        Assert.assertTrue("erros nao gravados no arquivo de log", actualNumberOfParseErrors > 0);
	    }

	  @Entao("^o sistema <nao> exibe o erro no arquivo de log$")
	    public void validateNonParseErrorLogging() throws Exception {
	        final Long actualNumberOfParseErrors = TestPrepare.countParseErrors();
	        Assert.assertTrue("erros gravados no arquivo de log", actualNumberOfParseErrors == 0 );
	    }
	  
	  @Entao("^o sistema exibe o erro <(.*)> no arquivo de log$")
	    public void ckeckParseErrorLogging(String error) throws Exception {
	        final Long actualNumberOfParseErrors = TestPrepare.countParseErrors(error);
	        Assert.assertEquals("numero de erros de parser difere", expectedNumberOfParserErrors, actualNumberOfParseErrors);
	    }
}
