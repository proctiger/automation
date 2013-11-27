package uol.collective.offer.service.test.consolidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.ConfigurationDAO;
import uol.collective.offer.commons.test.dao.ScheduleDAO;
import uol.collective.offer.commons.test.dao.SiteDAO;
import uol.collective.offer.commons.test.dao.SlotDAO;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.model.impl.ConfigurationTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.HttpHelper;
import uol.collective.offer.service.test.AbstractBase;
import uol.collective.offer.service.test.proposal.ProposalBase;
import uol.collective.offer.service.test.schedule.ScheduleBase;

@Component
public class ScheduleConsolidationBase extends AbstractBase
{

    @Autowired
    private ScheduleBase scheduleBase;

    @Autowired
    private SiteDAO siteDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Autowired
    private SlotDAO slotDAO;

    @Autowired
    protected ConfigurationDAO configurationDAO;

    @Autowired
    private ProposalBase proposalBase;

    private static SlotTestResponse PREMIUM_SLOT;
    
    private static SlotTestResponse VIP_SLOT_1;
    
    private static SlotTestResponse VIP_SLOT_2;
    
    private static ConfigurationTestResponse SWITCH_CONFIGURATION;
    
    private static final Logger logger = Logger.getLogger( ScheduleBase.class );

    protected void initialize()
    {
        logger.info( "Preparando a base de dados..." );

        scheduleDAO.deleteAllCascade();

        configurationDAO.deleteAllCascade();

        SWITCH_CONFIGURATION = configurationDAO.saveOrUpdate( new ConfigurationTestResponse( "consolidation.switch", "1", "1" ) );

        PREMIUM_SLOT = slotDAO.saveOrUpdate( new SlotTestResponse( "Ofertas Premium", "PRM1", "12", "A" ) );
        
        VIP_SLOT_1 = slotDAO.saveOrUpdate( new SlotTestResponse("Página 1", "VIP1", "28", "A") );
        
        VIP_SLOT_2 = slotDAO.saveOrUpdate( new SlotTestResponse("Página 2", "VIP2", "28", "A") );

        logger.info( "Preparacao finalizada!" );
    }

    protected List<ScheduleTestResponse> createSchedulesTodayForPremium(Integer size)
    {
        List<ScheduleTestResponse> schedulesInserted = new ArrayList<ScheduleTestResponse>();
        for ( int i = 0; i < Integer.valueOf( size ); i++ )
        {
            siteDAO.insertRandomSite();
            proposalBase.insertSomeNonExpiredProposals( 1 );
            schedulesInserted.addAll( scheduleBase.insertSomeSchedules( PREMIUM_SLOT, DateUtils.truncate( new Date(), Calendar.DATE ), 0, "A" ) );
        }
        return schedulesInserted;
    }
    
    protected List<ScheduleTestResponse> createSchedulesTodayForVip1(Integer size)
    {
        List<ScheduleTestResponse> schedulesInserted = new ArrayList<ScheduleTestResponse>();
        for ( int i = 0; i < Integer.valueOf( size ); i++ )
        {
            siteDAO.insertRandomSite();
            proposalBase.insertSomeNonExpiredProposals( 1 );
            schedulesInserted.addAll( scheduleBase.insertSomeSchedules( VIP_SLOT_1, DateUtils.truncate( new Date(), Calendar.DATE ), 0, "A" ) );
        }
        return schedulesInserted;
    }
    
    protected List<ScheduleTestResponse> createSchedulesTodayForVip2(Integer size)
    {
        List<ScheduleTestResponse> schedulesInserted = new ArrayList<ScheduleTestResponse>();
        for ( int i = 0; i < Integer.valueOf( size ); i++ )
        {
            siteDAO.insertRandomSite();
            proposalBase.insertSomeNonExpiredProposals( 1 );
            schedulesInserted.addAll( scheduleBase.insertSomeSchedules( VIP_SLOT_2, DateUtils.truncate( new Date(), Calendar.DATE ), 0, "A" ) );
        }
        return schedulesInserted;
    }

    protected void executeScheduleConsolidation()
    {
        String url = TestResponse.ENTITY_ROOT_RADAR + "/scheduleConsolidation/execute";
        try
        {
            HttpHelper.doGet( url, null );
        } catch ( IOException e )
        {
            throw new RuntimeException( "Falha ao executar consolidacao de schedules" );
        }

    }

    protected ConfigurationTestResponse getConfiguration(ConfigurationTestResponse configuration)
    {
        ConfigurationTestResponse configurationDB = configurationDAO.getByExampleForSimpleFieldsFirstFromList( configuration );
        if ( configurationDB == null )
        {
            // servico de consolidacao roda de forma assincrona
            try
            {
                Thread.sleep( 500 );
            } catch ( InterruptedException e )
            {
                throw new RuntimeException( "Erro ao dar sleep na thread" );
            }
            configurationDB = configurationDAO.getByExampleForSimpleFieldsFirstFromList( configuration );
        }
        return configurationDB;
    }

    protected void setConsolidationSwitchOff()
    {
        configurationDAO.delete( SWITCH_CONFIGURATION );
    }

}
