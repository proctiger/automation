package selenium.suite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses
({ 
	IncomingSuite.class, 
	InvestimentsSuite.class 
})
public class AllTests {

}
