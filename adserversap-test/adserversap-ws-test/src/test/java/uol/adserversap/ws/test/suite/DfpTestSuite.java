package uol.adserversap.ws.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import uol.adserversap.ws.test.run.dfp.GetOrderForApprovalInconsistentsTest;
import uol.adserversap.ws.test.run.dfp.GetOrderForApprovalTest;
import uol.adserversap.ws.test.run.dfp.GetOrderForReprogramationTest;
import uol.adserversap.ws.test.run.dfp.PostOrderForApprovalInconsistentTest;
import uol.adserversap.ws.test.run.dfp.PostOrderForApprovalTest;
import uol.adserversap.ws.test.run.dfp.PostOrderForApprovalWithPONumberTest;

/**
 *
 * @author dvrocha
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{GetOrderForApprovalInconsistentsTest.class, GetOrderForApprovalTest.class, GetOrderForReprogramationTest.class, PostOrderForApprovalInconsistentTest.class, PostOrderForApprovalTest.class, PostOrderForApprovalWithPONumberTest.class})
public class DfpTestSuite {
}
