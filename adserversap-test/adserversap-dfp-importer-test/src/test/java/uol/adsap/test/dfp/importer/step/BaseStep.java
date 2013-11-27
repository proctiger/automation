package uol.adsap.test.dfp.importer.step;

import java.util.*;
import org.slf4j.*;

/**
 *
 * @author dvrocha
 *
 */
public class BaseStep {

	protected final Logger logger;

	public static List<String> csv = new ArrayList<>();

	public BaseStep() {
		logger = LoggerFactory.getLogger(getClass());
	}
}
