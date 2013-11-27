package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.TransactionEventTestModel;


public interface TransactionEventDAO extends GenericDAO<TransactionEventTestModel>
{

    TransactionEventTestModel getRandom();

    TransactionEventTestModel getLastTransactionByClickEvent(String clickEventId);
  
} 
