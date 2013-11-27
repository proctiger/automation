package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.SlotTestResponse;



public interface SlotDAO extends GenericDAO<SlotTestResponse>
{

    public void deleteAllCascade();


  
} 
