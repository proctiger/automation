package selenium.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import selenium.test.advertising.AdvertisingListTest;
import selenium.test.campaign.CampaignListTest;
import selenium.test.creative.CreativeListTest;
import selenium.test.flight.FlightListTest;
import selenium.test.incoming.LoginTest;
import selenium.test.investments.ManualInvestmentTest;


@RunWith(Suite.class)
@SuiteClasses({
	LoginTest.class,
	CampaignListTest.class,
	AdvertisingListTest.class,
	CreativeListTest.class,
	FlightListTest.class,
	ManualInvestmentTest.class
})
public class AllTestSuite {

}
