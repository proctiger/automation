package uol.collective.offer.service.test.sale;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;



public class SaleInsertTest extends SaleBase
{
    


    

    @Test
    public void successfullyInsertWithNoScheduleOnDataBase()
    {
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );

        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );

        boolean result = executeInsertAndValidate( sales, sales );
        Assert.assertTrue( result );

        // Validando se os agendamentos foram inseridos
        List<ScheduleTestResponse> scs = scheduleUtils.getSchedulesBySale( "A", sale );
        Assert.assertNotNull( scs );
        Assert.assertEquals( SCHEDULING_DAYS, scs.size() );
    }

    @Test
    public void successfullyInsertWithAvaliableDates()
    {
        insertSomeSale( SLOT, DateUtils.addDays( new Date(), 60 ), SCHEDULING_DAYS, "A" );

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );

        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );

        boolean result = executeInsertAndValidate( sales, sales );
        Assert.assertTrue( result );

        // Validando se os agendamentos foram inseridos
        sale.setId( String.valueOf( saleDAO.getNextIdBySequence( SaleTestResponse.class ) - 1 ) );
        List<ScheduleTestResponse> scs = scheduleUtils.getSchedulesBySale( "A", sale );
        Assert.assertNotNull( scs );
        Assert.assertEquals( SCHEDULING_DAYS, scs.size() );
    }

    @Test
    public void insertWithNoAvaliableDates()
    {
        String startDate1 = DateTimeUtil.formatDateToStringDayMonthYear( new Date() );
        String endDate1 = DateTimeUtil.formatDateToStringDayMonthYear( DateTimeUtil.getADateFromNow( SCHEDULING_DAYS ) );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        String description = String.format( "Conflito de datas ao salvar os agendamentos para a Venda [ %s, '%s' a '%s' ]", SLOT.getDescription(), startDate1, endDate1 );
        expectedErrors.addError( new ErrorTestResponse( "0002", description, defineFields() ) );

        int quantity = Integer.valueOf( SLOT.getQuantity() );
        for( int i = 0; i < quantity ; i++ ){
            insertSomeSale( SLOT, DateUtils.addDays( new Date(), 15 ), SCHEDULING_DAYS, "A" );
        }

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );

        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );

        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }

    
    

    @Test
    public void insertWithoutProposal()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.proposalId" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, null, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithNonExistentProposal()
    {
        ProposalTestResponse nonExistentProposal = new ProposalTestResponse( );
        nonExistentProposal.setId( "999999" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "9002", "Resource proposal not found with id 999999", defineFields( "proposal" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, nonExistentProposal, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    @Test
    public void insertWithInvalidProposal()
    {
        ProposalTestResponse invalidProposal = new ProposalTestResponse( );
        invalidProposal.setId( "aaaaa" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]*", defineFields( "Sale.proposalId" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, invalidProposal, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithExpiredProposal()
    {
        try
        {
            ProposalTestResponse expiredProposal = proposalUtils.insertExpiredProposal( );
            
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            //pq Offer.siteId a infra de erros e testes esta engessada, rever essa estrutura
            expectedErrors.addError( new ErrorTestResponse( "9004", "Proposal is expired", defineFields( "Sale.proposal.expirationDate" ) ) );
            
            SaleTestResponse sale = new SaleTestResponse( null, expiredProposal, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );
            ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
            sales.addEntity( sale );
            
            Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir a proposta, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
    
    

    @Test
    public void insertWithoutStartDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.startDate" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, null, END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithBlankStartDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Sale.startDate" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, " ", END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithInvalidStartDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields( "Sale.startDate" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, "invalid-date", END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithoutEndDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.endDate" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, null, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithBalnkEndDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Sale.endDate" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, " ", SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithInvalidEndDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields( "Sale.endDate" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, "invalid-date", SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithStartDatePremature()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0010", "The date range has logical mistake", defineFields( "Sale.startDate, Sale.endDate" ) ) );
        
        String startDate = DateTimeUtil.getAnOldDateFromNowInXmlFormat();
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, startDate, END_DATE_XML_FORMAT, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    @Test
    public void insertWithEndDatePremature()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0010", "The date range has logical mistake", defineFields( "Sale.startDate, Sale.endDate" ) ) );
        
        String endDate = DateTimeUtil.getAnOldDateFromNowInXmlFormat();
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, endDate, SLOT, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    

    @Test
    public void insertWithoutSlot()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.slotId" ) ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, null, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithNonExistentSlot()
    {
        SlotTestResponse nonExistentSlot = new SlotTestResponse( );
        nonExistentSlot.setId( "999999" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12000", "Resource slot not found with id 999999", defineFields( "slot" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, nonExistentSlot, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    @Test
    public void insertWithInvalidSlot()
    {
        SlotTestResponse invalidSlot = new SlotTestResponse( );
        invalidSlot.setId( "aaaaa" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]*", defineFields( "Sale.slotId" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, invalidSlot, "A" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    
    
    
    @Test
    public void insertWithoutStatus()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.status" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, null );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }

    
    @Test
    public void insertWithBlankStatus()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.status" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithInvalidStatus()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.status" ) ) );

        SaleTestResponse sale = new SaleTestResponse( null, NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "INVALID-STATUS" );
        ListTestResponse<SaleTestResponse> sales = new ListTestResponseImpl<SaleTestResponse>( SaleTestResponse.class );
        sales.addEntity( sale );
        
        Assert.assertTrue( executeInsertAndValidate( sales, expectedErrors ) );
    }
    
    
    
    
    
        
}
