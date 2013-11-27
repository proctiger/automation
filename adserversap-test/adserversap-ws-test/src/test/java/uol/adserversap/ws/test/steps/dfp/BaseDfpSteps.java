package uol.adserversap.ws.test.steps.dfp;

import uol.adserversap.ws.test.domain.InconsistenceDomain;
import uol.adserversap.ws.test.domain.LineItemDomain;
import uol.adserversap.ws.test.domain.OrderDomain;
import uol.adserversap.ws.test.steps.commons.BaseCommonsSteps;


public class BaseDfpSteps extends BaseCommonsSteps {

    protected static OrderDomain order;
    protected static OrderDomain actualOrder;
    protected static LineItemDomain lastLineItem;
    protected static InconsistenceDomain[] inconsistenceList;
}
