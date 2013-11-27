package uol.admanager.consolidator.test.reconf;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;

@ConfigurationRepository(product = "admanager", component="admanager-consolidator-test")
@UpdateFrequency(interval = 5, timeUnit = TimeUnit.MINUTES)
public interface AdmanagerConsolidatorTestConfig {

    @ConfigurationItem(value="remote.admin.test.domain")
    public String getRemoteTestDomain();

    @ConfigurationItem(value="admanager.consolidator.domain")
    public String getConsolidatorDomain();

    @ConfigurationItem(value="admanager.consolidator.apacheadm.path")
    public Map<String, String> getApacheAdmPath();

    @ConfigurationItem(value="repositories", product="admanager", component="admanager-consolidator")
    public Map<String, List<String>> getConsolidatorRepositories();
}
