package uol.tm.stc.test.step;

import uol.tm.stc.test.domain.PageContent;
import uol.tm.stc.test.domain.TmScript;
import uol.tm.stc.test.page.TestPage;
import uol.tm.stc.test.web.BasicWebTest;

/**
 * 
 * @author dvrocha
 */
public class BaseStep {

	protected static TestPage page = new TestPage(BasicWebTest.firefoxDriver);
	protected static String template;
	protected static TmScript tmScript;
	protected static PageContent pageContent;
}
