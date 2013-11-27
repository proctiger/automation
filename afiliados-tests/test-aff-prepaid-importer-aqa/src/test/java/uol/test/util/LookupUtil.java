package uol.test.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

public class LookupUtil {

    private Class<?> clazz;

    private String host;

    private String port;

    private String ejbName;

    public LookupUtil paraClasse(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }

    public LookupUtil comNome(String ejbName) {
        this.ejbName = ejbName;
        return this;
    }

    public LookupUtil paraPorta(String port) {
        this.port = port;
        return this;
    }

    public LookupUtil paraHost(String host) {
        this.host = host;
        return this;
    }

    public <T> T lookup() {
        return makeLookup();
    }

    @SuppressWarnings("unchecked")
    private <T> T makeLookup() {
        try {
            return (T) clazz.cast(getContext(host, port).lookup(ejbName));
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Falha ao recuperar EJB: [%s]", ejbName), ex);
        }
    }

    private Context getContext(String host,
                               String port) throws Exception {
        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        environment.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        environment.put(Context.PROVIDER_URL, "jnp://" + host + ":" + port);
        return new InitialContext(environment);
    }
}
