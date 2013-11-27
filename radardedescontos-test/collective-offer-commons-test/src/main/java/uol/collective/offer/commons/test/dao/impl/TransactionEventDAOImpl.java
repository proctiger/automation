package uol.collective.offer.commons.test.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.TransactionEventDAO;
import uol.collective.offer.commons.test.model.impl.TransactionEventTestModel;

@Repository
public class TransactionEventDAOImpl extends GenericDAOImpl<TransactionEventTestModel> implements TransactionEventDAO
{

    @Override
    public TransactionEventTestModel getRandom()
    {
        TransactionEventTestModel str = new TransactionEventTestModel();
        return getByExampleForSimpleFieldsFirstFromList( str );
    }

    @Override
    public TransactionEventTestModel getLastTransactionByClickEvent(String clickEventId)
    {
        String sqlQuery = "select * from radar_adm.transaction_event where idt_click_Event = :clickEventId order by dat_transaction desc";
        Query query = getSession().createSQLQuery( sqlQuery ).addEntity( TransactionEventTestModel.class ).setParameter( "clickEventId", clickEventId );
        List<TransactionEventTestModel> transactions = query.list();
        return transactions == null || transactions.isEmpty() ? null : transactions.get( 0 );
    }
}
