package uol.bt.cruncher.test.reconf;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import reconf.client.annotations.ConfigurationItem;
import reconf.client.annotations.ConfigurationRepository;
import reconf.client.annotations.UpdateFrequency;

@ConfigurationRepository(product="bt", component="bt-cruncher-test")
@UpdateFrequency(interval=5, timeUnit=TimeUnit.MINUTES)
public interface Config {

    @ConfigurationItem(value="remote.admin.test.domain")
    String getRemoteAdminTestDomain();

    @ConfigurationItem(value="scanner.filename.pattern.whitelist", product="bt", component="bt-cruncher")
    List<String> getCruncherFilenamePatterns();

    @ConfigurationItem(value="cruncher.batch.size", product="bt", component="bt-cruncher")
    int getCruncherBatchSize();

    @ConfigurationItem(value="urls.groups", product="bt", component="bt-cruncher")
    Map<String, Set<String>> getCruncherUrlGroups();

    @ConfigurationItem(value="partners.cookie.parameters", product="bt", component="bt-cruncher")
    Map<String, Map<String, String>> getCruncherCookieParameters();
}
