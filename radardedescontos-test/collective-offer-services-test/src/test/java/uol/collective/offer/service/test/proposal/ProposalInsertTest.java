package uol.collective.offer.service.test.proposal;

import org.junit.Assert;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SiteTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;





public class ProposalInsertTest extends ProposalBase
{
    
	
    @Test
    public void successfullyInsert()
    {
        ProposalTestResponse proposalTestResponse = new ProposalTestResponse( 
                null, SITE_ACTIVE, DEFAULT_VALUE, DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
        
        boolean result = executeInsertAndValidate( proposalTestResponse, proposalTestResponse );
        Assert.assertTrue( result );
    }
    
	
    @Test
    public void insertProposalWithoutSite()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.siteId" ) ) );
        
        ProposalTestResponse newProposal = new ProposalTestResponse( null, null, DEFAULT_VALUE, DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
        
        boolean b = executeInsertAndValidate( newProposal, expectedErrors );
        Assert.assertTrue( b );
    }
    
	
    @Test
    public void insertProposalWithNonexistentSite()
    {
        SiteTestResponse inactiveSite = new SiteTestResponse( "999999", "Site Inexistente", "A", null, null, null, null, null, null, null, null, "A" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "3002", "Resource site not found with id 999999", defineFields( "site" ) ) );

        ProposalTestResponse newProposal = new ProposalTestResponse( null, inactiveSite, DEFAULT_VALUE, DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
        
        Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
    }
    
	
    @Test
    public void insertProposalWithInvalidSite()
    {
        SiteTestResponse invalidSite = new SiteTestResponse( "aaaaa", "Site Inexistente", "A", null, null, null, null, null, null, null, null, "A" );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "6003", "must match the following regular expression: [0-9]*", defineFields( "Proposal.siteId" ) ) );

        ProposalTestResponse newProposal = new ProposalTestResponse( null, invalidSite, DEFAULT_VALUE, DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
        
        Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
    }
    
	
    @Test
    public void insertProposalWithInactiveSite()
    {
        try
        {
            SiteTestResponse inactiveSite = new SiteTestResponse( "Site Inativo", "I" );
            inactiveSite = siteUtils.saveOrUpdate( inactiveSite );
            
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            //pq Offer.siteId a infra de erros e testes esta engessada, rever essa estrutura
            expectedErrors.addError( new ErrorTestResponse( "3007", "Site está inativo no momento", defineFields( "Offer.siteId" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, inactiveSite, DEFAULT_VALUE, DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
	
    @Test
    public void insertProposalWithoutValue()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.value" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, null, DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
	
    @Test
    public void insertProposalWithBlankValue()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Proposal.value" ) ) );
            expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,10}(\\.[0-9]{1,2})?", defineFields( "Proposal.value" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, "", DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
	
    @Test
    public void insertProposalWithInvalidValue()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,10}(\\.[0-9]{1,2})?", defineFields( "Proposal.value" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, "aaaaa", DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
	
    @Test
    public void insertProposalWithZeroValue()
    {
        try{
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, "0.0", DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, newProposal ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
	
    @Test
    public void insertProposalWithNegativeValue()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,10}(\\.[0-9]{1,2})?", defineFields( "Proposal.value" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, "-10.55", DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
	
    @Test
    public void insertProposalWithBlankExpirationDate()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Proposal.expirationDate" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, DEFAULT_VALUE, "", DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
    
    @Test
    public void insertProposalWithInvalidExpirationDate()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "0004", "Formato de data inválido", defineFields( "Proposal.expirationDate" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, DEFAULT_VALUE, "aaaaa", DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
    
    
    @Test
    public void insertProposalWithExpiredExpirationDate()
    {
        try{
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "9004", "Proposta está expirada", defineFields( "Proposal.expirationDate" ) ) );
            
            ProposalTestResponse newProposal = new ProposalTestResponse( null, SITE_ACTIVE, DEFAULT_VALUE, DateTimeUtil.getAnOldDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );
            
            Assert.assertTrue( executeInsertAndValidate( newProposal, expectedErrors ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }
}
