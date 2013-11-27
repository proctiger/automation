package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.OfferTestResponse;

public interface OfferDAO extends GenericDAO<OfferTestResponse>
{

    public OfferTestResponse getLastActiveOfferInserted();
    
    public OfferTestResponse insertRandomOffer();

}
