package test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceDelayUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PersistenceDelayUtil.class);

    private static final Integer DELAY = 5000; // 5s

    /**
     * Aguarda o event-click enviar as informacoes para o persistence.
     *
     * @throws Exception
     */
    public static void waitForPersistence() throws Exception {
        LOG.info("Aguardando event-click enviar informacoes para event-persistence");
        Thread.sleep(DELAY);
    }
}
