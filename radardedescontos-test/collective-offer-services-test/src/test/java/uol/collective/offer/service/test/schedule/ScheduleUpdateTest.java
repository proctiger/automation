package uol.collective.offer.service.test.schedule;

import junit.framework.Assert;

import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;


public class ScheduleUpdateTest extends ScheduleBase
{
    


    

    @Test
    public void successfullyCanceling()
    {
        ListTestResponse<ScheduleTestResponse> schedulesToBeCancel = new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class );
        /** Schedules que deverao ser canceladas */
        schedulesToBeCancel.addEntities( insertSomeSchedules( SLOT, START_DATE, SCHEDULING_DAYS, "A" ) );
        
        /** Schedules que nao deverao ser canceladas */
        insertSomeSchedules( SLOT, START_DATE, SCHEDULING_DAYS, "A" );

        Assert.assertTrue( executeUpdateAndValidate( schedulesToBeCancel, ADD_PARAMS ) );
    }

    @Test
    public void tryToCancelUnexisting()
    {
        ListTestResponse<ScheduleTestResponse> schedulesToBeCancel = new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class );
        /** Schedules que deverao ser canceladas */
        schedulesToBeCancel.addEntity( new ScheduleTestResponse("999999",  
                saleDAO.getRandomActiveAndNonExpired(), DateTimeUtil.getCurrentDateInXmlFormat(), "A" ) );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11002", "Resource schedule not found with id 999999", defineFields( "schedule" ) ) );
        
        boolean result = executeUpdateAndValidate( schedulesToBeCancel, ADD_PARAMS, expectedErrors );
        Assert.assertTrue( result );
    }
    
    @Test
    public void tryToCancelWithInvalidId()
    {
        ListTestResponse<ScheduleTestResponse> schedulesToBeCancel = new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class );
        /** Schedules que deverao ser canceladas */
        schedulesToBeCancel.addEntity( new ScheduleTestResponse("aaaaaa",  
                saleDAO.getRandomActiveAndNonExpired(), DateTimeUtil.getCurrentDateInXmlFormat(), "A" ) );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11002", "Resource schedule not found with id 0", defineFields( "schedule" ) ) );
        
        boolean result = executeUpdateAndValidate( schedulesToBeCancel, ADD_PARAMS, expectedErrors );
        Assert.assertTrue( result );
    }

    
    
        
}
