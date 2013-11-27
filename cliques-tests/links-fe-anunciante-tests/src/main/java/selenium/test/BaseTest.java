package selenium.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

//@RunWith(SpringJUnit4ClassRunner.class)
//
//@ContextConfiguration("/springContext.xml")
//@ActiveProfiles(Environment.QA)
public class BaseTest {

//	@Autowired
	protected String login = "staging@bol.com.br";
//	@Autowired
	protected String password = "uol123";

	@BeforeClass
	public static void beforeClass() {

	}

	@AfterClass
	public static void afterClass() {

	}
}
