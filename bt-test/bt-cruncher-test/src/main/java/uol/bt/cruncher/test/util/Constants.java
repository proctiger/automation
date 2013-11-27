package uol.bt.cruncher.test.util;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.commons.test.helper.RemoteTestHelper;
import uol.bt.cruncher.test.reconf.Config;

public class Constants {
    public static final Config config = ConfigurationRepositoryFactory.get(Config.class);

    public static final String REMOTE_ADMIN_TEST_DOMAIN = config.getRemoteAdminTestDomain();

    public static final String CRUNCHER_ARQUIVED_EVENTS_DIR;
    static {
        try {
            CRUNCHER_ARQUIVED_EVENTS_DIR = RemoteTestHelper
                    .shell(REMOTE_ADMIN_TEST_DOMAIN, "ls -d -1 /export/bt/archived-cruncher-events/*/ | head -n 1")
                    .assertOk()
                    .getBodyAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
