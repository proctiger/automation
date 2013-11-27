package uol.bt.mongokeeper.test.util;

import java.util.Map;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.mongokeeper.test.reconf.Config;

public class Constants {
	public static final Config config = ConfigurationRepositoryFactory.get(Config.class);
	public static final String REMOTE_ADMIN_TEST_DOMAIN = config.getRemoteAdminTestDomain();
	public static final Map<String, Integer> REMOTE_ADMIN_TEST_AMOUNT_MONTH_KEEP_RECORDS = config.getRemoteAdminMonthToKeep();
}
