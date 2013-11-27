package uol.test.suite;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cucumber.api.java.After;
import uol.test.feature.LocalServiceTest;
import uol.test.util.BaseFactory;


@RunWith(Suite.class)
@SuiteClasses({ LocalServiceTest.class })
public class SuiteTest {

    @After
	private static void closeConnection() {
    	System.out.println("---------------------- FECHANDO AS CONEXOES COM O BANCO ---------------------- ");
    	BaseFactory.closeConnectionUol3();
    	BaseFactory.closeConnectionUol7();
	}
	
}