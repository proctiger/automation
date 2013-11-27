package uol.collective.offer.click.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.CityDAO;
import uol.collective.offer.commons.test.dao.ClickConsolidationDAO;
import uol.collective.offer.commons.test.dao.ClickEventDAO;
import uol.collective.offer.commons.test.dao.OfferDAO;
import uol.collective.offer.commons.test.dao.SiteDAO;

@Component
public class ClickBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( ClickBase.class );

    @Autowired
    protected ClickEventDAO clickEventDAO;
    
    @Autowired
    protected ClickConsolidationDAO clickConsolidationDAO;
    
    @Autowired
    protected OfferDAO offerDAO;
    
    @Autowired
    protected CityDAO cityDAO;
    
    @Autowired
    protected SiteDAO siteDAO;    

    @Before
    public void initialize()
    {
        logger.info( "Preparando a base de dados..." );
        clickEventDAO.deleteAllCascade();
        clickConsolidationDAO.deleteAll();
        logger.info( "Preparacao finalizada!" );
    }

}