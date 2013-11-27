package uol.collective.offer.commons.test.dao.impl;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ProposalDAO;
import uol.collective.offer.commons.test.dao.SiteDAO;
import uol.collective.offer.commons.test.model.impl.SiteTestResponse;

@Repository
@SuppressWarnings("unchecked")
public class SiteDAOImpl extends GenericDAOImpl<SiteTestResponse> implements SiteDAO
{

    private static Logger logger = Logger.getLogger( SiteDAO.class );

    @Autowired
    protected ProposalDAO proposalDAO;

    @Override
    public void deleteAllCascade()
    {
        SQLQuery sqlQ = getSession().createSQLQuery( " Delete From radar_adm.imported_offer " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela ImportedOffer  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.offer " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Offer ", sqlQ.executeUpdate() ) );
        proposalDAO.deleteAllCascade();
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.site " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Site ", sqlQ.executeUpdate() ) );
    }

    @Override
    public SiteTestResponse getRandomActive()
    {
        SiteTestResponse str = new SiteTestResponse();
        str.setStatus( "A" );
        return getByExampleForSimpleFieldsFirstFromList( str );
    }

    public SiteTestResponse getLastActiveSiteInserted()
    {
        String sqlQuery = "select max(idt_site) as maxId from radar_adm.site where ind_status = 'A'";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();

        if ( maxId == null )
        {
            return saveOrUpdate( new SiteTestResponse( String.format( "random-name-%s", new Random().nextInt( 100000 ) ), "A" ) );
        } else
        {
            return load( new SiteTestResponse( (String) maxId ) );
        }
    }

    @Override
    public SiteTestResponse getRandomActiveDiferentFrom(SiteTestResponse site)
    {
        Criteria cri = createCriteria();
        List<SiteTestResponse> resultList = (List<SiteTestResponse>) cri.add( Restrictions.eq( "status", "A" ) )
            .add( Restrictions.ne( "id", site.getId() ) )
            .list();
        return ( resultList != null && resultList.isEmpty() ? resultList.get( 0 ) : null );
    }

    @Override
    public SiteTestResponse insertRandomSite()
    {
        SiteTestResponse site = new SiteTestResponse( String.format( "random-%s", new Random().nextInt( 100000 ) ), "A" );
        SiteTestResponse siteDB = getByExampleForSimpleFieldsFirstFromList( site );
        return ( siteDB != null ) ? siteDB : saveOrUpdate( site );
    }
}
