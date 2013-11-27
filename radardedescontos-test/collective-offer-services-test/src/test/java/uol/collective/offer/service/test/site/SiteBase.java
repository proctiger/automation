package uol.collective.offer.service.test.site;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.SiteDAO;
import uol.collective.offer.commons.test.model.impl.SiteTestResponse;
import uol.collective.offer.commons.test.util.ResponseValidator;
import uol.collective.offer.service.test.AbstractBase;



@Component
public class SiteBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( SiteBase.class );
    
    protected static final String SITE_DESCRIPTION = "Default Site";
    protected static final String PAGSEGURO_EMAIL = "cad_exavier@uolinc.com";
    protected static final String URL_LOGO = "http://teste.com.br/image-01.jpg";
    protected static final String XML_PATH = "site-teste";
    protected static final String PAGSEGURO_PRIVATE_KEY = "pagseguro-private-key";
    protected static final String PAGSEGURO_PUBLIC_KEY = "pagseguro-public-key";
    protected static final String SITE_URL = "http://uol.com.br";
    protected static final String IDT_PERSON = "9001";
    protected static final String CONTACT_NAME = "CONTACT-NAME";

    private static final String[] FIELDS_TO_AVOID_VALIDATION = new String[] { "id", "creationDate", "pagseguroPrivateKey", "pagseguroPublicKey" };


    
    @Autowired
    protected SiteDAO siteDAO;
    
    
    @Before
    public void initialize(){
        super.initialize();
        logger.info( "Preparando a base de dados..." );
        siteDAO.deleteAllCascade();
        logger.info( "Preparacao finalizada!" );
    }
    
    

    protected static SiteTestResponse getSiteInstance()
    {
        return new SiteTestResponse( null, SITE_DESCRIPTION, URL_LOGO, XML_PATH, PAGSEGURO_EMAIL, IDT_PERSON, CONTACT_NAME, SITE_URL, "A", PAGSEGURO_PRIVATE_KEY, PAGSEGURO_PUBLIC_KEY, "A" );
    }
    

    public SiteTestResponse getRandomActiveDiferentFrom( SiteTestResponse site )
    {
        SiteTestResponse resultSite = siteDAO.getRandomActiveDiferentFrom( site );
        
        if( resultSite == null ){
            resultSite = insertSomeActiveSites( 1 ).get( 0 );
        }
        
        return resultSite;
    }

    private List<SiteTestResponse> insertSomeActiveSites( int quantity )
    {
        List<SiteTestResponse> sites = new ArrayList<SiteTestResponse>();
        SiteTestResponse site = null;
        for(int i = 0; i < quantity; i++){
            site = new SiteTestResponse( "", "A" );
            site.setName( String.format( "Site:%s", System.currentTimeMillis() ) );
            sites.add( siteDAO.saveOrUpdate( site ) );
        }
        return sites;
    }


    public SiteTestResponse getRandomActive()
    {
        return siteDAO.getRandomActive();
    }

    public SiteTestResponse getLastActiveSiteInserted()
    {
        return siteDAO.getLastActiveSiteInserted();
    }


    public SiteTestResponse saveOrUpdate(SiteTestResponse inactiveSite)
    {
        return siteDAO.saveOrUpdate( inactiveSite );
    }


    protected boolean executeInsertAndValidateForSite( Object site, Object expectedObject )
    {
        return executePostServiceAndValidateResult( site, null, expectedObject, FIELDS_TO_AVOID_VALIDATION, ResponseValidator.DEFAULT_VALIDATOR );
    }

    
}
