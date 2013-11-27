package uol.tm.filechecker.test.suite;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import uol.tm.filechecker.test.feature.FeatureTest;
import uol.tm.filechecker.test.http.RemoteAdminConfigWs;

/**
 *
 * @author dvrocha
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FeatureTest.class})
public class TestSuite {

    @AfterClass
    public static void afterClass() throws Exception {
        RemoteAdminConfigWs.disableSvnCommit();
    }
}
