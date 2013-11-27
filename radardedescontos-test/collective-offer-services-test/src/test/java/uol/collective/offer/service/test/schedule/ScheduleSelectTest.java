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
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;



public class ScheduleSelectTest extends ScheduleBase
{

    @Before
    public void initialize()
    {
        super.initialize();
    }

    @Test  
    public void selectAllActives()
    {
        SlotTestResponse slot = slotBase.insertNextSlot( "5" );
        insertSomeSchedules( slot, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 3, "A" );
        ListTestResponse<ScheduleTestResponse> listFromDataBase = new ListTestResponseImpl<ScheduleTestResponse>( scheduleDAO.getSchedulesByAlias( "A", null ), ScheduleTestResponse.class );

        Assert.assertTrue( executeListAllAndValidate( listFromDataBase ) );
    }

    @Test  
    public void selectAllCanceled()
    {
        SlotTestResponse slot = slotBase.insertNextSlot( "5" );
        insertSomeSchedules( slot, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 3, "C" );
        ListTestResponse<ScheduleTestResponse> listFromDataBase = new ListTestResponseImpl<ScheduleTestResponse>( scheduleDAO.getSchedulesByAlias( "C", null ), ScheduleTestResponse.class );
        Map<String, String> map = new HashMap<String, String>();
        map.put( "status", "C" );
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase, map ) );
    }

    @Test  
    public void selectSchedulesFromSlot()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );
        SlotTestResponse slot2 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 2, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 4, "A" );

        ListTestResponse<ScheduleTestResponse> listFromDataBase = new ListTestResponseImpl<ScheduleTestResponse>( scheduleDAO.getSchedulesByAlias( "A", slot2.getAlias() ), ScheduleTestResponse.class );
        Map<String, String> map = new HashMap<String, String>();
        map.put( "slotAlias", slot2.getAlias() );
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase, map ) );
    }

    @Test  
    public void selectSchedulesFromOneDay()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );
        SlotTestResponse slot2 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 2, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 4, "A" );

        ListTestResponse<ScheduleTestResponse> listFromDataBase = new ListTestResponseImpl<ScheduleTestResponse>( scheduleDAO.getSchedulesByDateRange( "A", DateTimeUtil.formatDateToStringHibernate( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), DateTimeUtil.formatDateToStringHibernate( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) ), ScheduleTestResponse.class );
        Map<String, String> map = new HashMap<String, String>();
        map.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        map.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase, map ) );
    }

    @Test  
    public void selectSchedulesDifferentSchedulesSamePeriod()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );
        SlotTestResponse slot2 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 3, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 3, "A" );

        ListTestResponse<ScheduleTestResponse> listFromDataBase = new ListTestResponseImpl<ScheduleTestResponse>(

        scheduleDAO.getSchedulesByDateRange( "A", DateTimeUtil.formatDateToStringHibernate( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ), DateTimeUtil.formatDateToStringHibernate( DateUtils.truncate( DateUtils.addDays( new Date(), 3 ), Calendar.DAY_OF_MONTH ) ) ), ScheduleTestResponse.class );

        Map<String, String> map = new HashMap<String, String>();
        map.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        map.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 3 ), Calendar.DAY_OF_MONTH ) ) );
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase, map ) );
    }

    @Test  
    public void selectSchedulesDifferentSchedulesDifferentPeriod()
    {
        SlotTestResponse slot1 = slotBase.insertNextSlot( "5" );
        SlotTestResponse slot2 = slotBase.insertNextSlot( "3" );
        insertSomeSchedules( slot1, DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ), 3, "A" );
        insertSomeSchedules( slot2, DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ), 3, "A" );

        ListTestResponse<ScheduleTestResponse> listFromDataBase = new ListTestResponseImpl<ScheduleTestResponse>(

        scheduleDAO.getSchedulesByDateRange( "A", DateTimeUtil.formatDateToStringHibernate( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ), DateTimeUtil.formatDateToStringHibernate( DateUtils.truncate( DateUtils.addDays( new Date(), 7 ), Calendar.DAY_OF_MONTH ) ) ), ScheduleTestResponse.class );

        Map<String, String> map = new HashMap<String, String>();
        map.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );
        map.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 7 ), Calendar.DAY_OF_MONTH ) ) );
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase, map ) );
    }

    @Test 
    public void selectSchedulesForInvalidSlot()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put( "slotAlias", "felipe-sucesso" );
        Assert.assertTrue( executeListAllAndValidate( new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class ), map ) );
    }

    @Test  
    public void selectSchedulesNoData()
    {
        Assert.assertTrue( executeListAllAndValidate( new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class ), null ) );
    }

    @Test  
    public void selectSchedulesStartDateAfterEndDate()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 7 ), Calendar.DAY_OF_MONTH ) ) );
        map.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );
        Assert.assertTrue( executeListAllAndValidate( new ListTestResponseImpl<ScheduleTestResponse>( ScheduleTestResponse.class ), map ) );
    }
    
    @Test   
    public void selectSchedulesStartDateInvalid() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields("startDate") ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "dateStart", "X");
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 4 ), Calendar.DAY_OF_MONTH ) ) );        
        Assert.assertTrue( executeListAllAndValidate( expectedErrors, new ScheduleTestResponse(), null, parameters ) );
    }
    
    @Test 
    public void selectSchedulesEndDateInvalid() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields("endDate") ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( DateUtils.addDays( new Date(), 7 ), Calendar.DAY_OF_MONTH ) ) );        
        parameters.put( "dateEnd", "X");
        
        Assert.assertTrue( executeListAllAndValidate( expectedErrors, new ScheduleTestResponse(), null, parameters ) );
    }
    
    
    @Test  
    public void selectSchedulesNoStartDate() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11666", "Date range is not valid", defineFields("startDate/endDate") ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "dateStart", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        
        Assert.assertTrue( executeListAllAndValidate( expectedErrors, new ScheduleTestResponse(), null, parameters ) );
    }
    
    @Test 
    public void selectSchedulesNoEndDate() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11666", "Date range is not valid", defineFields("startDate/endDate") ) );

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put( "dateEnd", DateTimeUtil.formatDateToStringXML( DateUtils.truncate( new Date(), Calendar.DAY_OF_MONTH ) ) );
        
        Assert.assertTrue( executeListAllAndValidate( expectedErrors, new ScheduleTestResponse(), null, parameters ) );
    }
    
}
