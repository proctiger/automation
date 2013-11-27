package uol.bt.commons.test.reconf;

import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;

@ConfigurationRepository(component="bt-commons-test", product="bt")
@UpdateFrequency(interval=5, timeUnit=TimeUnit.MINUTES)
public interface CommonsConfig {
	
	@ConfigurationItem(value="mongo.router.host")
    String getMongoRouterHost();
	
	@ConfigurationItem(value="mongo.router.port")
    int getMongoRouterPort();
	
}
