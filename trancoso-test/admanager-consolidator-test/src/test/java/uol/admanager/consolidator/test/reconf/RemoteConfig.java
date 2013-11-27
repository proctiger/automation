package uol.admanager.consolidator.test.reconf;

import reconf.client.proxy.ConfigurationRepositoryFactory;

public class RemoteConfig {

    private static final AdmanagerConsolidatorTestConfig instance = ConfigurationRepositoryFactory.get(AdmanagerConsolidatorTestConfig.class);

    public static AdmanagerConsolidatorTestConfig getInstance() {
        return instance;
    }
}
