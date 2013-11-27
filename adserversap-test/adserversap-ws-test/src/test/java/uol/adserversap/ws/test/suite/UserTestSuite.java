package uol.adserversap.ws.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.adserversap.ws.test.run.user.DeleteUserTest;
import uol.adserversap.ws.test.run.user.GetUserListTest;
import uol.adserversap.ws.test.run.user.GetUserTest;
import uol.adserversap.ws.test.run.user.PutUserTest;

/**
 *
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses({
    GetUserTest.class, GetUserListTest.class, DeleteUserTest.class, PutUserTest.class})
public class UserTestSuite {
}
