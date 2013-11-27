package uol.collective.offer.service.test.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.DayAvailabilityTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;


public class ScheduleAvailableDaysTest extends ScheduleBase
{

    private static final String LIST_AVAILABLE_DAYS = "/available-days";
    
    @Before
    public void initialize()
    {
        super.initialize();
    }

    @Test   
    public void selectWithoutAnyParameters()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12000", "Slot not found", defineFields( "slot" ) ) );
        expectedErrors.addError( new ErrorTestResponse( "11666", "Date range is not valid", defineFields( "startDate/endDate" ) ) );

        Map<String, String> parameters = new HashMap<String, String>();

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, expectedErrors, new ScheduleTestResponse(), parameters ) );
    }

    @Test   
    public void selectWithoutSlotAlias()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12000", "Slot not found", defineFields( "slot" ) ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, expectedErrors, new ScheduleTestResponse(), parameters ) );
    }

    @Test   
    public void selectWithoutDateStart()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();

        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );

        expectedErrors.addError( new ErrorTestResponse( "11666", "Date range is not valid", defineFields( "startDate/endDate" ) ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, expectedErrors, new ScheduleTestResponse(), parameters ) );
    }

    @Test   
    public void selectSchedulesStartDateInvalid()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields( "startDate" ) ) );

        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", "X" );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );
        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, expectedErrors, new ScheduleTestResponse(), parameters ) );
    }

    @Test   
    public void selectSchedulesEndDateInvalid()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields( "endDate" ) ) );

        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 7 ), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", "X" );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, expectedErrors, new ScheduleTestResponse(), parameters ) );
    }
    
    @Test 
    public void selectSchedulesStartDateAfterEndDate()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );
        
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 7 ), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );
        
        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class ), new ScheduleTestResponse(), parameters ) );        
        Assert.assertTrue( executeListAllAndValidate( new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class ), parameters ) );
    }    

    @Test   
    public void todayNotAvailable()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>( DayAvailabilityTestResponse.class );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "false" ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }

    @Test   
    public void todayAvailable()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "4" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>( DayAvailabilityTestResponse.class );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "true" ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }

    @Test   
    public void fiveDaysAvailable()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "4" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>(DayAvailabilityTestResponse.class);
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 3 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }
    
    @Test   
    public void fiveDaysOneNotAvailable()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>(DayAvailabilityTestResponse.class);
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "false" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 3 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }    
    
    @Test   
    public void threeDaysNotAvailable()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>(DayAvailabilityTestResponse.class);
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "false" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ) ), "false" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ), "false" ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }      
    
    @Test  
    public void threeDaysAvailableForSpecificSlot()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "3" );
        SlotTestResponse slot2 = slotBase.insertNextSlot( "2" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>(DayAvailabilityTestResponse.class);
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ), "true" ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot1.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }        
    
    @Test  
    public void threeDaysOneNotAvailableForSpecificSlot()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "3" );
        SlotTestResponse slot2 = slotBase.insertNextSlot( "2" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 0, "A" );
        
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );
        insertSomeSchedules( slot1, DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ), 0, "A" );

        ListTestResponse<DayAvailabilityTestResponse> dayAvailables = new ListTestResponseImpl<DayAvailabilityTestResponse>(DayAvailabilityTestResponse.class);
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), "false" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 1 ), Calendar.DAY_OF_MONTH ) ), "true" ) );
        dayAvailables.addEntity( new DayAvailabilityTestResponse( DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ), "true" ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "slotAlias", slot2.getAlias() );
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 2 ), Calendar.DAY_OF_MONTH ) ) );

        Assert.assertTrue( executeListAndValidate( LIST_AVAILABLE_DAYS, dayAvailables, new ScheduleTestResponse(), parameters ) );
    }     
}
