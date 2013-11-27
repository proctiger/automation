package uol.adserversap.ws.test.prepare;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.ads.common.lib.auth.ClientLoginTokens;
import com.google.api.ads.dfp.axis.factory.DfpServices;
import com.google.api.ads.dfp.lib.client.DfpSession;

public class DfpServiceFactory {

    private Configuration cfg = new BaseConfiguration();
    private DfpSession session;
    private DfpServices dfpServices = new DfpServices();
    private AtomicReference<String> token = new AtomicReference<>();
    private static final Logger alarm = LoggerFactory.getLogger("alarm");

    public DfpServiceFactory(Map<?, ?> arg) {
        for (Entry<?, ?> each : arg.entrySet()) {
            cfg.addProperty(each.getKey().toString(), each.getValue());
        }
        createToken();
        newSession();
    }

    public void createToken(){
        try {
            token = new AtomicReference<>(new ClientLoginTokens.Builder()
                .forApi(ClientLoginTokens.Api.DFP)
                .from(cfg)
                .build()
                .requestToken());
        } catch (Exception e) {
            alarm.error("erro ao obter token do DFP", e);
            throw new RuntimeException(e);
        }
    }

    private void newSession() {
        try {
            this.session = new DfpSession.Builder().from(cfg).withClientLoginToken(token.get()).build();
        } catch (Exception e) {
            alarm.error("erro ao conectar ao DFP", e);
            throw new RuntimeException(e);
        }
    }

    public <T> T newDfpService(Class<T> serviceInterface) {
        return dfpServices.get(this.session, serviceInterface);
    }
}