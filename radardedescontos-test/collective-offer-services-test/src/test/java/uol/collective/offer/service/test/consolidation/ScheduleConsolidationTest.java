package uol.collective.offer.service.test.consolidation;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ConfigurationTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;


public class ScheduleConsolidationTest extends ScheduleConsolidationBase
{

    private static String PREMIUM_CONFIGURATION = "sites.premium.page.1";

    private static String VIP_1_CONFIGURATION = "sites.vip.page.1";

    private static String VIP_2_CONFIGURATION = "sites.vip.page.2";

    @Before
    public void initialize()
    {
        super.initialize();
    }

    
    @Test
    public void consolidatePremiumAllPositions()
    {
        List<ScheduleTestResponse> schedules = createSchedulesTodayForPremium( 12 );

        executeScheduleConsolidation();

        List<String> siteIds = new ArrayList<String>();

        for ( ScheduleTestResponse schedule : schedules )
        {
            siteIds.add( schedule.getSale()
                .getProposal()
                .getSite()
                .getId() );
        }

        String expectedSiteList = StringUtils.join( siteIds, "," );
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.premium.page.1", expectedSiteList, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, PREMIUM_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da premium no banco", configuration );

        Assert.assertTrue( expectedSiteList.equals( configuration.getValue() ) );
    }
    
    
    @Test
    public void consolidateVip1AllPositions()
    {
        List<ScheduleTestResponse> schedules = createSchedulesTodayForVip1( 28 );

        executeScheduleConsolidation();

        List<String> siteIds = new ArrayList<String>();

        for ( ScheduleTestResponse schedule : schedules )
        {
            siteIds.add( schedule.getSale()
                .getProposal()
                .getSite()
                .getId() );
        }

        String expectedSiteList = StringUtils.join( siteIds, "," );
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.vip.page.1", expectedSiteList, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_1_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da vip pagina 1 no banco", configuration );

        Assert.assertTrue( expectedSiteList.equals( configuration.getValue() ) );
    }

    
    @Test
    public void consolidateVip2AllPositions()
    {
        List<ScheduleTestResponse> schedules = createSchedulesTodayForVip2( 28 );

        executeScheduleConsolidation();

        List<String> siteIds = new ArrayList<String>();

        for ( ScheduleTestResponse schedule : schedules )
        {
            siteIds.add( schedule.getSale()
                .getProposal()
                .getSite()
                .getId() );
        }

        String expectedSiteList = StringUtils.join( siteIds, "," );
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.vip.page.2", expectedSiteList, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_2_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da vip pagina 2 no banco", configuration );

        Assert.assertTrue( expectedSiteList.equals( configuration.getValue() ) );
    }

    
    @Test
    public void consolidatePremiumNotAllPositions()
    {
        List<ScheduleTestResponse> schedules = createSchedulesTodayForPremium( 6 );

        executeScheduleConsolidation();

        List<String> siteIds = new ArrayList<String>();

        for ( ScheduleTestResponse schedule : schedules )
        {
            siteIds.add( schedule.getSale()
                .getProposal()
                .getSite()
                .getId() );
        }

        String expectedSiteList = StringUtils.join( siteIds, "," );
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.premium.page.1", expectedSiteList, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, PREMIUM_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da premium no banco", configuration );

        Assert.assertTrue( expectedSiteList.equals( configuration.getValue() ) );
    }

    
    @Test
    public void consolidateVip1NotAllPositions()
    {
        List<ScheduleTestResponse> schedules = createSchedulesTodayForVip1( 14 );

        executeScheduleConsolidation();

        List<String> siteIds = new ArrayList<String>();

        for ( ScheduleTestResponse schedule : schedules )
        {
            siteIds.add( schedule.getSale()
                .getProposal()
                .getSite()
                .getId() );
        }

        String expectedSiteList = StringUtils.join( siteIds, "," );
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.vip.page.1", expectedSiteList, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_1_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da vip pagina 1 no banco", configuration );

        Assert.assertTrue( expectedSiteList.equals( configuration.getValue() ) );
    }

    
    @Test
    public void consolidateVip2NotAllPositions()
    {
        List<ScheduleTestResponse> schedules = createSchedulesTodayForVip2( 14 );

        executeScheduleConsolidation();

        List<String> siteIds = new ArrayList<String>();

        for ( ScheduleTestResponse schedule : schedules )
        {
            siteIds.add( schedule.getSale()
                .getProposal()
                .getSite()
                .getId() );
        }

        String expectedSiteList = StringUtils.join( siteIds, "," );

        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.vip.page.2", expectedSiteList, "1" ) );
        
        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_2_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da vip pagina 2 no banco", configuration );

        Assert.assertTrue( expectedSiteList.equals( configuration.getValue() ) );
    }
    
    
    @Test
    public void consolidatePremiumNoPositions()
    {
        executeScheduleConsolidation();
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.premium.page.1", null, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, PREMIUM_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da premium no banco", configuration );

        Assert.assertNull( configuration.getValue() );
    }

    
    @Test
    public void consolidateVip1NoPositions()
    {
        executeScheduleConsolidation();
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.vip.page.1", null, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_1_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da vip pagina 1 no banco", configuration );

        Assert.assertNull( configuration.getValue() );
    }

    
    @Test
    public void consolidateVip2NoPositions()
    {
        executeScheduleConsolidation();
        
        configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "sites.vip.page.2", null, "1" ) );

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_2_CONFIGURATION, null, null ) );

        Assert.assertNotNull( "Falha ao buscar configuracao da vip pagina 2 no banco", configuration );

        Assert.assertNull( configuration.getValue() );
    }

    
    @Test
    public void consolidatePremiumSwitchOff()
    {
        setConsolidationSwitchOff();

        executeScheduleConsolidation();

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, PREMIUM_CONFIGURATION, null, null ) );

        Assert.assertNull( configuration );
    }

    
    @Test
    public void consolidateVip1SwitchOff()
    {
        setConsolidationSwitchOff();

        executeScheduleConsolidation();

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_1_CONFIGURATION, null, null ) );

        Assert.assertNull( configuration );
    }

    
    @Test
    public void consolidateVip2SwitchOff()
    {
        setConsolidationSwitchOff();

        executeScheduleConsolidation();

        ConfigurationTestResponse configuration = getConfiguration( new ConfigurationTestResponse( null, VIP_2_CONFIGURATION, null, null ) );

        Assert.assertNull( configuration );
    }

}