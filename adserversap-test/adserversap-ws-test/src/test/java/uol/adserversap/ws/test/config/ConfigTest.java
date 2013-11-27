package uol.adserversap.ws.test.config;

import uol.remote.admin.config.client.proxy.ProxyFactory;

public class ConfigTest{
		protected static final RemoteConfig config = ProxyFactory.newInstance(RemoteConfig.class);
		public static final String ADSERVERSAP_WS_DOMAIN = config.getAdserverSapDomain();
		public static final int REQUEST_TIMEOUT = config.getRequestTimeout();
}
