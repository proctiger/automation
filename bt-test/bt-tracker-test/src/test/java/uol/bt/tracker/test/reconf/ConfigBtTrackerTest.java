package uol.bt.tracker.test.reconf;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;

@ConfigurationRepository(component="bt-tracker-test", product="bt")
@UpdateFrequency(interval=5, timeUnit=TimeUnit.MINUTES)
public interface ConfigBtTrackerTest {

    @ConfigurationItem(value="remote.admin.test.domain")
    String getRemoteAdminTestDomain();
    
    @ConfigurationItem(value="tracker.domain")
    String getTrackerDomain();
    
    @ConfigurationItem(value="profile.user")
    Map<String, String> getProfileUser();
}
