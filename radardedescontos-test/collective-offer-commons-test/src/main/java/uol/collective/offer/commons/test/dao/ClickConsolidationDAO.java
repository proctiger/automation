package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.ClickConsolidationTestModel;
import uol.collective.offer.commons.test.model.impl.OfferTestResponse;


public interface ClickConsolidationDAO extends GenericDAO<ClickConsolidationTestModel>
{
    ClickConsolidationTestModel getLastStatistic();

    ClickConsolidationTestModel getStatisticByOffer(OfferTestResponse offer);
}
