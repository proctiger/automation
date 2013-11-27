package selenium.suite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import selenium.test.incoming.LoginTest;

@RunWith(Suite.class)
@SuiteClasses({LoginTest.class})
public class IncomingSuite {

}
