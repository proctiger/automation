package uol.collective.offer.service.test.offer.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uol.collective.offer.commons.test.dao.ConfigurationDAO;
import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ConfigurationTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.util.HttpHelper;
import uol.collective.offer.service.test.AbstractBase;

public class OfferLocationTest extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( OfferLocationTest.class );

    @Autowired
    protected ConfigurationDAO configurationDAO;

    @Before
    public void initialize()
    {
        logger.info( "Preparando a base de dados..." );
        configurationDAO.deleteAllCascade();
        logger.info( "Preparacao finalizada!" );
    }

    
    @Test
    public void selectAllTopSites()
    {
        ListTestResponse<ConfigurationTestResponse> confs = new ListTestResponseImpl<ConfigurationTestResponse>( ConfigurationTestResponse.class );
        confs.addEntities( configurationDAO.insertActiveTopSiteBlock( 5 ) );
        Assert.assertTrue( executeListAllAndValidate( confs ) );
    }

    
    @Test
    public void selectAllRotatedTopSites()
    {
        ListTestResponse<ConfigurationTestResponse> confs = new ListTestResponseImpl<ConfigurationTestResponse>( ConfigurationTestResponse.class );
        confs.addEntities( configurationDAO.insertActiveRotatedTopSiteBlock( 5 ) );
        Assert.assertTrue( executeListAndValidate( "/rotated", confs, null ) );
    }

    
    @Test
    public void testRotationWithEmptyRotatedConfiguration()
    {
        try
        {
            List<ConfigurationTestResponse> confs = configurationDAO.insertActiveTopSiteBlock( 5 );
            Assert.assertNotNull( "Falha ao executar rotacao, nao foram inseridos os blocos", confs );
            Assert.assertTrue( "Falha ao executar rotacao, nao foram inseridos os blocos", ( confs.size() > 0 ) );

            String url = confs.get( 0 )
                .getEntityRootUrl() + "/rotate";
            HttpHelper.doGet( url, null );

            List<ConfigurationTestResponse> rotatedConfs = configurationDAO.getRotatedTopSiteBlock( "1" );
            Assert.assertNotNull( "Falha ao executar rotacao, nao foram inseridos os blocos rotacionados", rotatedConfs );
            Assert.assertTrue( "Falha ao executar rotacao, nao foram inseridos os blocos rotacionados", ( rotatedConfs.size() > 0 ) );

            Assert.assertTrue( assertEqualsConfs( confs, rotatedConfs ) );
        } catch ( Exception e )
        {
            Assert.fail( "Falha ao executar rotacao, erro: " + e.getMessage() );
        }
    }

    
    @Test
    public void testRotationWithDataConfiguration()
    {
        try
        {
            List<ConfigurationTestResponse> confs = configurationDAO.insertActiveTopSiteBlock( 1 );
            Assert.assertNotNull( "Falha ao executar rotacao, nao foram inseridos os blocos", confs );
            Assert.assertTrue( "Falha ao executar rotacao, nao foram inseridos os blocos", ( confs.size() > 0 ) );

            List<ConfigurationTestResponse> expectedRotatedConfs = configurationDAO.insertActiveRotatedTopSiteBlock( 1 );
            Assert.assertNotNull( "Falha ao executar rotacao, nao foram inseridos os blocos rotacionados", expectedRotatedConfs );
            Assert.assertTrue( "Falha ao executar rotacao, nao foram inseridos os blocos rotacionados", ( expectedRotatedConfs.size() > 0 ) );

            String url = confs.get( 0 )
                .getEntityRootUrl() + "/rotate";
            HttpHelper.doGet( url, null );

            List<ConfigurationTestResponse> rotatedConfs = configurationDAO.getRotatedTopSiteBlock( "1" );
            List<String> expectedRotated = parse( expectedRotatedConfs.get( 0 )
                .getValue() );
            Collections.rotate( expectedRotated, -1 );
            String expectedRotatedString = StringUtils.join( expectedRotated, "," );
            expectedRotatedConfs.get( 0 )
                .setValue( expectedRotatedString );

            Assert.assertNotNull( "Falha ao executar rotacao, nao foram inseridos os blocos rotacionados", rotatedConfs );
            Assert.assertTrue( "Falha ao executar rotacao, nao foram inseridos os blocos rotacionados", ( rotatedConfs.size() > 0 ) );

            Assert.assertTrue( assertEqualsConfs( expectedRotatedConfs, rotatedConfs ) );

        } catch ( Exception e )
        {
            Assert.fail( "Falha ao executar rotacao, erro: " + e.getMessage() );
        }
    }

    private boolean assertEqualsConfs(List<ConfigurationTestResponse> confs1, List<ConfigurationTestResponse> confs2)
    {
        if ( confs1 != null && confs2 != null )
        {
            for ( ConfigurationTestResponse conf1 : confs1 )
            {
                for ( ConfigurationTestResponse conf2 : confs2 )
                {
                    if ( conf1.getKey()
                        .replace( ".rotated", "" )
                        .equals( conf2.getKey()
                            .replace( ".rotated", "" ) ) && !conf1.getValue()
                        .equals( conf2.getValue() ) )
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private List<String> parse(String siteList)
    {
        try
        {
            if ( !StringUtils.isEmpty( siteList ) )
            {
                return Arrays.asList( siteList.split( "," ) );
            }
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao realizar parse da Lista de Sites (%s), ERRO: %s", siteList, ex.getMessage() ), ex );
        }
        return new ArrayList<String>();
    }

}
