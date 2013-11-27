package uol.bt.tracker.test.reconf;

import java.util.List;
import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;

@ConfigurationRepository(component = "bt-tracker", product = "bt")
@UpdateFrequency(interval = 5, timeUnit = TimeUnit.MINUTES)
public interface ConfigBtTracker {

	@ConfigurationItem(value = "minimum.seconds.from.last.visit")
	int getMinimumSecoundsFromLastVisit();

	@ConfigurationItem(value = "referers.blacklist")
	List<String> getReferersBlacklist();
}
