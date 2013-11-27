package uol.bt.mongokeeper.test.reconf;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;

@ConfigurationRepository(product="bt", component="bt-mongo-keeper")
@UpdateFrequency(interval=5, timeUnit=TimeUnit.MINUTES)
public interface Config {

	@ConfigurationItem(value="collection.purge.records.month.threshold")
    Map<String,Integer> getRemoteAdminMonthToKeep();
	
	@ConfigurationItem(value="remote.admin.test.domain", product="bt",component="bt-mongo-keeper-test")
//    @ConfigurationItem(value="remote.admin.test.domain", product="bt",component="bt-cruncher-test")
    String getRemoteAdminTestDomain();
}
