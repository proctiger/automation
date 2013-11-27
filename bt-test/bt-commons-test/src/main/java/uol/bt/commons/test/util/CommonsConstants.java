package uol.bt.commons.test.util;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.commons.test.reconf.CommonsConfig;

public class CommonsConstants {

	private static final CommonsConfig config = ConfigurationRepositoryFactory.get(CommonsConfig.class);

	public static final String MONGO_ROUTER_HOST = config.getMongoRouterHost();
	public static final int MONGO_ROUTER_PORT = config.getMongoRouterPort();
	public static final String MONGO_DB = "bt_adm";

	public static final boolean MONGO_OPT_AUTO_CONN_RETRY = true;
	public static final int MONGO_OPT_CONN_PER_HOST = 10;
	public static final int MONGO_OPT_CONN_TIMEOUT = 1000;
	public static final int MONGO_OPT_MAX_WAIT_TIME = 1000;
	public static final int MONGO_OPT_SOCKET_TIMEOUT = 100000;
	public static final int MONGO_OPT_THREADS_ALLOWED_BLOCK = 2;

}
