package uol.admanager.editor.test.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dvrocha
 */
public class BaseStep {

    protected final Logger logger;

    public BaseStep() {
        logger = LoggerFactory.getLogger(getClass());
    }
}
