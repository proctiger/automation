package uol.bt.cruncher.test.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uol.bt.cruncher.test.domain.BtEvent;
import uol.bt.cruncher.test.domain.cookies.CookiesDocument;
import uol.bt.cruncher.test.domain.profiles.ProfileDocument;
import uol.bt.cruncher.test.domain.segments.SegmentsDocument;
import uol.bt.cruncher.test.domain.views.ViewsDocument;

/**
 *
 * @author dvrocha
 *
 */
public class BaseStep {

    protected final Logger logger;

    protected static ViewsDocument viewsDocument;
    protected static ProfileDocument profileDocument;
    protected static SegmentsDocument segmentsDocument;
    protected static CookiesDocument cookiesDocument;
    protected static List<BtEvent> events;

    public BaseStep() {
        logger = LoggerFactory.getLogger(getClass());
    }
}
