package uol.collective.offer.commons.test.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ClickConsolidationDAO;
import uol.collective.offer.commons.test.model.impl.ClickConsolidationTestModel;
import uol.collective.offer.commons.test.model.impl.OfferTestResponse;

@Repository
public class ClickConsolidationDAOImpl extends GenericDAOImpl<ClickConsolidationTestModel> implements ClickConsolidationDAO
{

    @Override
    public ClickConsolidationTestModel getLastStatistic()
    {
        String sqlQuery = "select max(idt_click_consolidation) as maxId from radar_adm.click_consolidation";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();
        return load( new ClickConsolidationTestModel( (String) maxId ) );
    }

    @Override
    public ClickConsolidationTestModel getStatisticByOffer(OfferTestResponse offer)
    {
        String sqlQuery = "select max(idt_click_consolidation) as maxId from radar_adm.click_consolidation where idt_offer = :offerId";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.setParameter( "offerId", offer.getId() );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();
        return load( new ClickConsolidationTestModel( (String) maxId ) );
    }
}