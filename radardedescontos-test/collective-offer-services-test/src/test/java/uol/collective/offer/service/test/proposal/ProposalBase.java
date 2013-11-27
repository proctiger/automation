package uol.collective.offer.service.test.proposal;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.ProposalDAO;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SiteTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.service.test.AbstractBase;
import uol.collective.offer.service.test.site.SiteBase;


@Component
public class ProposalBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( ProposalBase.class );

    protected static final String DEFAULT_VALUE = "100.0";

    protected static SiteTestResponse SITE_ACTIVE;

    @Autowired
    protected ProposalDAO proposalDAO;

    @Autowired
    protected SiteBase siteUtils;

    @Before
    public void initialize()
    {
        super.initialize();
        logger.info( "Preparando a base de dados..." );
        proposalDAO.deleteAllCascade();
        logger.info( "Preparacao finalizada!" );
        SITE_ACTIVE = siteUtils.getRandomActive();
    }

    public void insertSomeNonExpiredProposals(int quantity)
    {
        int value = 10;
        SiteTestResponse site = siteUtils.getLastActiveSiteInserted();
        ProposalTestResponse entity;
        for ( int i = 0; i < quantity; i++ )
        {
            value += i;
            entity = new ProposalTestResponse( null, site, String.valueOf( value ), DateTimeUtil.getAFarDateFromNowInHibernateFormat(), DateTimeUtil.getCurrentDateInHibernateFormat() );
            proposalDAO.saveOrUpdate( entity );
        }
    }

    public ProposalTestResponse getNonExpiredProposal()
    {
        ProposalTestResponse proposal = proposalDAO.getRandomNonExpired();
        if ( proposal == null )
        {
            insertSomeNonExpiredProposals( 1 );
            return proposalDAO.getRandomNonExpired();
        }
        return proposal;
    }

    public ProposalTestResponse insertExpiredProposal()
    {
        ProposalTestResponse proposal = new ProposalTestResponse( null, siteUtils.getRandomActive(), DEFAULT_VALUE, DateTimeUtil.getAnOldDateFromNowInHibernateFormat(), DateTimeUtil.getCurrentDateInHibernateFormat() );
        return proposalDAO.saveOrUpdate( proposal );
    }

}
