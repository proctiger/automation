package selenium.suite.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import selenium.test.investments.ManualInvestmentTest;

@RunWith(Suite.class)
@SuiteClasses({ ManualInvestmentTest.class })
public class InvestimentsSuite {
}
