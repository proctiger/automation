package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.CPCTestResponse;



public interface CPCDAO extends GenericDAO<CPCTestResponse>
{

    public CPCTestResponse getRandom();

    public CPCTestResponse insertRandomCPC();
    
} 
