package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.SaleTestResponse;


public interface SaleDAO extends GenericDAO<SaleTestResponse>
{

    public void deleteAllCascade();

    public SaleTestResponse getRandomActiveAndNonExpired();



  
} 
