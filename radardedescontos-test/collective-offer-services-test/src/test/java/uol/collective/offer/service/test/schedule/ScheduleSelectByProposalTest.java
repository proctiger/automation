package uol.collective.offer.service.test.schedule;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;




public class ScheduleSelectByProposalTest extends ScheduleBase
{

    private static final String LIST_PATH = "/list-by-proposal";
    
    
    
    @Test
    public void selectAllByProposal(){
        ListTestResponse<ScheduleTestResponse> schedulesFromDao = new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class );
        schedulesFromDao.addEntities( insertSomeSchedules( SLOT, START_DATE, SCHEDULING_DAYS, "A" ) );
        insertSomeSchedules( SLOT, START_DATE, SCHEDULING_DAYS, "C" );
        
        Map<String, String> addParams = new HashMap<String, String>();
        addParams.put( "proposalId", schedulesFromDao.getEntities().get( 0 ).getSale().getProposalId() );
        addParams.put( "scheduleStatus", "A" );
        
        boolean result = executeListAndValidate( LIST_PATH, schedulesFromDao, addParams );
        Assert.assertTrue( result );
    }
    
    
    
    @Test
    public void selectWithUnexistentProposal(){
        ListTestResponse<ScheduleTestResponse> schedulesFromDao = new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class );
        
        Map<String, String> addParams = new HashMap<String, String>();
        addParams.put( "proposalId", "0" );
        addParams.put( "scheduleStatus", "A" );
        
        boolean result = executeListAndValidate( LIST_PATH, schedulesFromDao, addParams );
        Assert.assertTrue( result );
    }
    
    
    @Test
    public void selectWithNullProposalId(){
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "An invalid client request was made", defineFields( "/schedule/list-by-proposal" ) ) );
        
        Map<String, String> addParams = new HashMap<String, String>();
        addParams.put( "proposalId", null );
        addParams.put( "scheduleStatus", "A" );
        
        boolean result = executeListAndValidate( LIST_PATH, expectedErrors, new ScheduleTestResponse(), addParams );
        Assert.assertTrue( result );
    }
    
    @Test
    public void selectWithBlankProposalId(){
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "An invalid client request was made", defineFields( "/schedule/list-by-proposal" ) ) );
        
        Map<String, String> addParams = new HashMap<String, String>();
        addParams.put( "proposalId", "" );
        addParams.put( "scheduleStatus", "A" );
        
        boolean result = executeListAndValidate( LIST_PATH, expectedErrors, new ScheduleTestResponse(), addParams );
        Assert.assertTrue( result );
    }
    
    
    @Test
    public void selectWithUnexistentStatus(){
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0001", "No enum const class uol.collective.offer.model.ScheduleStatus.X", defineFields( "Schedule" ) ) );
        
        Map<String, String> addParams = new HashMap<String, String>();
        addParams.put( "proposalId", "99999" );
        addParams.put( "scheduleStatus", "X" );
        
        boolean result = executeListAndValidate( LIST_PATH, expectedErrors, new ScheduleTestResponse(), addParams  );
        Assert.assertTrue( result );
    }
    
    
}
