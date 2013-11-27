package uol.collective.offer.service.test.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.SaleDAO;
import uol.collective.offer.commons.test.dao.ScheduleDAO;
import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.commons.test.util.HttpHelper;
import uol.collective.offer.commons.test.util.HttpHelper.Response;
import uol.collective.offer.commons.test.util.ResponseValidator;
import uol.collective.offer.service.test.AbstractBase;
import uol.collective.offer.service.test.proposal.ProposalBase;
import uol.collective.offer.service.test.slot.SlotBase;

@Component
public class ScheduleBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( ScheduleBase.class );

    protected static final int SCHEDULING_DAYS = 30;

    protected static final Date START_DATE = DateUtils.truncate( new Date(), Calendar.DATE );

    protected static final Map<String, String> ADD_PARAMS = new HashMap<String, String>();

    protected static SlotTestResponse SLOT;

    @Autowired
    protected ScheduleDAO scheduleDAO;

    @Autowired
    protected ProposalBase proposalUtils;

    @Autowired
    protected SaleDAO saleDAO;

    @Autowired
    protected SlotBase slotBase;

    // protected static ProposalTestResponse NON_EXPIRED_PROPOSAL;

    static
    {
        ADD_PARAMS.put( "comments", "comentario-de-cancelamento" );
    }

    @Before
    public void initialize()
    {
        logger.info( "Preparando a base de dados..." );
        scheduleDAO.deleteAllCascade();
        // NON_EXPIRED_PROPOSAL = proposalUtils.getNonExpiredProposal();
        SLOT = slotBase.insertNextSlot( "5" );
        logger.info( "Preparacao finalizada!" );
    }

    public List<ScheduleTestResponse> insertSomeSchedules(SlotTestResponse slot, Date startDate, int schedulingDays, String statusSchedule)
    {
        try
        {
            ProposalTestResponse prop = proposalUtils.getNonExpiredProposal();

            Date endDate = DateUtils.addDays( startDate, schedulingDays );

            SaleTestResponse sale = new SaleTestResponse( null, prop, DateTimeUtil.formatDateToStringHibernate( startDate ), DateTimeUtil.formatDateToStringHibernate( endDate ), slot, "A", "0" );

            sale = saleDAO.saveOrUpdate( sale );

            List<ScheduleTestResponse> newSchedules = new ArrayList<ScheduleTestResponse>();
            for ( ScheduleTestResponse sc : ScheduleTestResponse.valueOf( sale, statusSchedule ) )
            {
                sc.setId( String.valueOf( saleDAO.getNextIdBySequence( ScheduleTestResponse.class ) ) );
                newSchedules.add( sc );
            }

            sale.setSchedules( newSchedules );
            sale = saleDAO.saveOrUpdate( sale );
            return sale.getSchedules();
        } catch ( Exception e )
        {
            String messageError = String.format( "Falha ao inserir Sale, ERRO: %s", e.getMessage() );
            logger.error( messageError, e );
            Assert.fail( messageError );
        }
        return null;
    }

    public List<ScheduleTestResponse> getSchedulesBySale(String status, SaleTestResponse sale)
    {
        return scheduleDAO.getSchedulesBySale( status, sale );
    }

    protected boolean executeUpdateAndValidate(ListTestResponse<ScheduleTestResponse> schedules, Map<String, String> additionalParameters)
    {
        try
        {
            if ( schedules != null && !schedules.isEmpty() )
            {

                String urlPost = getUrlPost( additionalParameters );
                String xml = HttpHelper.parse( schedules );
                Response resp = HttpHelper.doPost( urlPost, xml );

                if ( resp != null && ( resp.getCode() == 200 || resp.getCode() == 204 ) )
                {
                    SaleTestResponse sale = schedules.getEntities()
                        .get( 0 )
                        .getSale();
                    List<ScheduleTestResponse> scsFromDB = scheduleDAO.getSchedulesBySale( "C", sale );
                    return scsFromDB.size() == schedules.size();
                }
            }
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        logger.error( "Falha ao executar e validar o servico." );
        return false;
    }

    protected boolean executeUpdateAndValidate(ListTestResponse<ScheduleTestResponse> schedules, Map<String, String> additionalParameters, Object expectedObject)
    {
        try
        {
            if ( schedules != null && !schedules.isEmpty() )
            {

                String urlPost = getUrlPost( additionalParameters );
                String xml = HttpHelper.parse( schedules );
                Response resp = HttpHelper.doPost( urlPost, xml );

                if ( resp != null )
                {
                    String expectedXml = HttpHelper.parse( expectedObject );
                    boolean result = ResponseValidator.validateXmlResult( expectedXml, resp.getContent(), expectedObject.getClass(), null, ResponseValidator.DEFAULT_VALIDATOR );

                    return ( result ? true : printLogError( expectedXml, resp.getContent() ) );
                }
            }
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        logger.error( "Falha ao executar e validar o servico." );
        return false;
    }

    protected String getUrlPost(Map<String, String> additionalParameters)
    {
        try
        {
            String urlPost = ( (TestResponse) ScheduleTestResponse.class.newInstance() ).getEntityRootUrl();

            return String.format( "%s/cancel?login=%s&ip=%s%s", urlPost, HttpHelper.LOGIN, HttpHelper.IP, HttpHelper.parseAdditionalParams( additionalParameters ) );
        } catch ( Exception e )
        {
        }
        return null;
    }


}