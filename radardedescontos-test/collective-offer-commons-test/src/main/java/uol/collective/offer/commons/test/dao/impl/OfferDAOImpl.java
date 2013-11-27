package uol.collective.offer.commons.test.dao.impl;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.OfferDAO;
import uol.collective.offer.commons.test.dao.SiteDAO;
import uol.collective.offer.commons.test.model.impl.OfferTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;

@Repository
@SuppressWarnings("unchecked")
public class OfferDAOImpl extends GenericDAOImpl<OfferTestResponse> implements OfferDAO
{

    @Autowired
    private SiteDAO siteDAO;

    @Autowired
    public OfferTestResponse getLastActiveOfferInserted()
    {
        String sqlQuery = "select max(idt_offer) as maxId from radar_adm.offer where ind_status = 'A'";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();

        if ( maxId == null )
        {
            return insertRandomOffer();
        } else
        {
            return load( new OfferTestResponse( (String) maxId ) );
        }
    }

    @Autowired
    public OfferTestResponse insertRandomOffer()
    {
        String offerName = String.format( "random-name-%s", new Random().nextInt( 100000 ) );
        String sqlQuery = "select 1 from radar_adm.offer where nam_offer = :offerName";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.setParameter( "offerName", offerName );
        Object entry = query.uniqueResult();

        if ( entry != null )
        {
            offerName = String.format( "random-name-%s%S", new Random().nextInt( 100000 ), new Random().nextInt( 100000 ) );
        }
        return saveOrUpdate( new OfferTestResponse( offerName, "A", siteDAO.getLastActiveSiteInserted(), "www.uol.com.br", "100", "10", DateTimeUtil.formatDateToStringHibernate( DateUtils.addDays( new Date(), -3 ) ), DateTimeUtil.formatDateToStringHibernate( DateUtils.addDays( new Date(), 3 ) ), "0", "1", DateTimeUtil.formatDateToStringHibernate( new Date() ) ) );

    }
}
