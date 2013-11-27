package uol.adserversap.ws.test.config;

import java.util.Map;

import uol.remote.admin.config.client.annotations.ConfigurationRepository;
import uol.remote.admin.config.client.annotations.RemoteConfigurationItem;

@ConfigurationRepository(product="adserversap-test", component="adserversap-ws-test")
public interface RemoteConfig {

    @RemoteConfigurationItem(property="dfp.customfield.billingtypeid.id")
    Long getDfpCustomfieldIdForBillingTypeId();

    @RemoteConfigurationItem(property="dfp.customfield.enterprise.id")
    Long getDfpCustomfieldIdForEnterprise();

    @RemoteConfigurationItem(property="dfp.customfield.sapproduct.id")
    Long getDfpCustomfieldIdForSapProduct();

    @RemoteConfigurationItem(property="dfp.customfield.billingtypeid.options")
    Map<Integer, Long> getDfpCustomfieldOptionsForBillingTypeId();

    @RemoteConfigurationItem(property="dfp.customfield.enterprise.options")
    Map<String, Long> getDfpCustomfieldOptionsForEnterprise();

    @RemoteConfigurationItem(property="dfp.customfield.sapproduct.options")
    Map<String, Long> getDfpCustomfieldOptionsForSapProduct();
    
    @RemoteConfigurationItem(property="remote.admin.test.domain")
    String getAdserverSapDomain(); 
    
    @RemoteConfigurationItem(property="request.timeout")
    int  getRequestTimeout();
    
}
