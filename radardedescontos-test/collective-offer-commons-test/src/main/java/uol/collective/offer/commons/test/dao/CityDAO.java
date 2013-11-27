package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.CityTestResponse;


public interface CityDAO extends GenericDAO<CityTestResponse>
{

    public CityTestResponse getLastActiveCityInserted();
  
} 
