package uol.collective.offer.service.test.sale;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.commons.test.util.ResponseValidator;



public class SaleUpdateTest extends SaleBase
{
    


    

    @Test
    public void successfullyCanceling()
    {
        SaleTestResponse saleToBeCancel = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        saleToBeCancel.setStatus( "D" );
        SaleTestResponse saleToBeSend = SaleTestResponse.parseToXmlFormat( saleToBeCancel );
        saleToBeCancel.setCancelDate( DateTimeUtil.formatDateToStringXML( new Date() ) );

        boolean result = executeUpdateAndValidate( saleToBeSend, saleToBeCancel, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );

        // Validando se os agendamentos foram cancelados
        List<ScheduleTestResponse> scs = scheduleUtils.getSchedulesBySale( "C", saleToBeSend );
        Assert.assertNotNull( scs );
        Assert.assertEquals( "Numero de agendamentos cancelados diferente do esperado!", SCHEDULING_DAYS, scs.size() );
    }

    @Test
    public void tryToCancelUnexistingSale()
    {
        SaleTestResponse nonExistingSale = new SaleTestResponse( "99999", NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );

        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "10002", "Resource sale not found with id 99999", defineFields( "sale" ) ) );
        
        boolean result = executeUpdateAndValidate( nonExistingSale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }

    @Test
    public void tryToCancelWithInvalidId()
    {
        SaleTestResponse nonExistingSale = new SaleTestResponse( "aaaaaa", NON_EXPIRED_PROPOSAL, START_DATE_XML_FORMAT, END_DATE_XML_FORMAT, SLOT, "A" );

        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "An invalid client request was made", defineFields( "/sale/aaaaaa" ) ) );
        
        boolean result = executeUpdateAndValidate( nonExistingSale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }

    
    

    @Test   @Ignore
    public void updateWithoutProposal()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.proposalId" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setProposal( null );

        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithNonExistentProposal()
    {
        ProposalTestResponse nonExistentProposal = new ProposalTestResponse( );
        nonExistentProposal.setId( "999999" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "9002", "Resource proposal not found with id 999999", defineFields( "proposal" ) ) );

        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setProposal( nonExistentProposal );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    @Test   @Ignore
    public void updateWithInvalidProposal()
    {
        ProposalTestResponse invalidProposal = new ProposalTestResponse( );
        invalidProposal.setId( "aaaaa" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]*", defineFields( "Sale.proposalId" ) ) );

        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setProposal( invalidProposal );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithExpiredProposal()
    {
        try
        {
            ProposalTestResponse expiredProposal = proposalUtils.insertExpiredProposal( );
            
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            //pq Offer.siteId a infra de erros e testes esta engessada, rever essa estrutura
            expectedErrors.addError( new ErrorTestResponse( "9004", "Proposal is expired", defineFields( "Sale.proposal.expirationDate" ) ) );

            SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
            sale = SaleTestResponse.parseToXmlFormat( sale );
            sale.setProposal( expiredProposal );
            
            boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
            Assert.assertTrue( result );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir a proposta, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
    
    

    @Test   @Ignore
    public void updateWithoutStartDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.startDate" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStartDate( null );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithBlankStartDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Sale.startDate" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStartDate( " " );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithInvalidStartDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields( "Sale.startDate" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStartDate( "invalid-date" );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithoutEndDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.endDate" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setEndDate( null );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithBalnkEndDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Sale.endDate" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setEndDate( " " );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithInvalidEndDate()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0004", "Invalid date format", defineFields( "Sale.endDate" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setEndDate( "invalid-date" );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithStartDatePremature()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0010", "The date range has logical mistake", defineFields( "Sale.startDate, Sale.endDate" ) ) );
        
        String startDate = DateTimeUtil.getAnOldDateFromNowInXmlFormat();
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStartDate( startDate );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    @Test   @Ignore
    public void updateWithEndDatePremature()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0010", "The date range has logical mistake", defineFields( "Sale.startDate, Sale.endDate" ) ) );
        
        String endDate = DateTimeUtil.getAnOldDateFromNowInXmlFormat();
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setEndDate( endDate );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    

    @Test   @Ignore
    public void updateWithoutSlot()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.slotId" ) ) );
        
        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setSlot( null );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithNonExistentSlot()
    {
        SlotTestResponse nonExistentSlot = new SlotTestResponse( );
        nonExistentSlot.setId( "999999" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12000", "Resource slot not found with id 999999", defineFields( "slot" ) ) );

        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setSlot( nonExistentSlot );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    @Test   @Ignore
    public void updateWithInvalidSlot()
    {
        SlotTestResponse invalidSlot = new SlotTestResponse( );
        invalidSlot.setId( "aaaaa" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]*", defineFields( "Sale.slotId" ) ) );


        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setSlot( invalidSlot );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    
    
    
    @Test   @Ignore
    public void updateWithoutStatus()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.status" ) ) );


        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStatus( null );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }

    
    @Test   @Ignore
    public void updateWithBlankStatus()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.status" ) ) );


        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStatus( " " );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    @Test   @Ignore
    public void updateWithInvalidStatus()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Sale.status" ) ) );


        SaleTestResponse sale = insertSomeSale( SLOT, new Date(), SCHEDULING_DAYS, "A" );
        sale = SaleTestResponse.parseToXmlFormat( sale );
        sale.setStatus( "INVALID-STATUS" );
        
        boolean result = executeUpdateAndValidate( sale, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }
    
    
    
    
    
        
}
