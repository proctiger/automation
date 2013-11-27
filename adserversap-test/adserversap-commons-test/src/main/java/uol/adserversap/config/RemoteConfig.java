package uol.adserversap.config;

import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;


@ConfigurationRepository(product="adserversap-test",component="adserversap-ws-test")
@UpdateFrequency(interval=5, timeUnit=TimeUnit.MINUTES)
public interface RemoteConfig {
	
	   @ConfigurationItem(value="sap.db.connection.url")
	    String  getSapUrlConnection();
}
