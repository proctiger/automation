package uol.collective.offer.service.test.sale;

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
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.service.test.AbstractBase;
import uol.collective.offer.service.test.proposal.ProposalBase;
import uol.collective.offer.service.test.schedule.ScheduleBase;
import uol.collective.offer.service.test.slot.SlotBase;



@Component
public class SaleBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( SaleBase.class );

    protected static ProposalTestResponse NON_EXPIRED_PROPOSAL;
    protected static SlotTestResponse SLOT;
    protected static final int SCHEDULING_DAYS = 30;
    protected static final String START_DATE_XML_FORMAT = DateTimeUtil.getCurrentDateInXmlFormat();
    protected static final String END_DATE_XML_FORMAT = DateTimeUtil.getADateFromNowInXmlFormat( SCHEDULING_DAYS );
    protected static final Map<String, String> ADD_PARAMS = new HashMap<String, String>();

    
    @Autowired
    protected SaleDAO saleDAO;

    @Autowired
    protected ProposalBase proposalUtils;

    @Autowired
    protected SlotBase slotUtils;

    @Autowired
    protected ScheduleBase scheduleUtils;

    @Before
    public void initialize()
    {
        super.initialize();
        logger.info( "Preparando a base de dados..." );
        saleDAO.deleteAllCascade();
        logger.info( "Preparacao finalizada!" );
        NON_EXPIRED_PROPOSAL = proposalUtils.getNonExpiredProposal();
        SLOT = slotUtils.getAnySlot();
        ADD_PARAMS.put( "cancellationComment", "comentario-de-cancelamento-via-teste-automatizado" );
    }

     protected List<SaleTestResponse> insertSomeSales( int quantity, SlotTestResponse slot, Date startDate, int schedulingDays, String status )
     {
         List<SaleTestResponse> sales = new ArrayList<SaleTestResponse>();
         Date currentDay = DateUtils.truncate( startDate, Calendar.DATE );
         for( int i = 0; i < quantity; i++ ){
             insertSomeSale( slot, currentDay, schedulingDays, status );
             currentDay = DateUtils.addDays( currentDay, ( SCHEDULING_DAYS + 1 ) );
         }
         return sales;
     }

    protected SaleTestResponse insertSomeSale(SlotTestResponse slot, Date startDate, int schedulingDays, String status)
    {
        try
        {
            ProposalTestResponse prop = proposalUtils.getNonExpiredProposal();

            startDate = DateUtils.truncate( startDate, Calendar.DATE );
            Date endDate = DateUtils.addDays( startDate, schedulingDays - 1 );

            SaleTestResponse sale = new SaleTestResponse( null, prop, DateTimeUtil.formatDateToStringHibernate( startDate ), DateTimeUtil.formatDateToStringHibernate( endDate ), slot, status );

            sale = saleDAO.saveOrUpdate( sale );

            List<ScheduleTestResponse> newSchedules = new ArrayList<ScheduleTestResponse>();
            for ( ScheduleTestResponse sc : ScheduleTestResponse.valueOf( sale, "A" ) )
            {
                sc.setId( String.valueOf( saleDAO.getNextIdBySequence( ScheduleTestResponse.class ) ) );
                newSchedules.add( sc );
            }

            sale.setSchedules( newSchedules );
            return saleDAO.saveOrUpdate( sale );
        } catch ( Exception e )
        {
            String messageError = String.format( "Falha ao inserir Sale, ERRO: %s", e.getMessage() );
            logger.error( messageError, e );
            Assert.fail( messageError );
        }
        return null;
    }


}
