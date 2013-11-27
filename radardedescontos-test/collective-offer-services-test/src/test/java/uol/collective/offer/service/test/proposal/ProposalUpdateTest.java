package uol.collective.offer.service.test.proposal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SiteTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.commons.test.util.ResponseValidator;


public class ProposalUpdateTest extends ProposalBase
{

    @Before
    public void initialize()
    {
        super.initialize();
        insertSomeNonExpiredProposals( 10 );
    }

    @Test
    public void successfullyUpdate()
    {
        ProposalTestResponse oldProp = proposalDAO.getRandomNonExpired();
        ProposalTestResponse newProp = new ProposalTestResponse();
        newProp.setId( oldProp.getId() );
        newProp.setSite( siteUtils.getRandomActiveDiferentFrom( oldProp.getSite() ) );
        double value = Double.valueOf( oldProp.getValue() );
        value += 0.01;
        newProp.setValue( String.valueOf( value ) );
        newProp.setExpirationDate( DateTimeUtil.getADateFromNowInXmlFormat( 15 ) );

        Assert.assertTrue( executeUpdateAndValidate( newProp, oldProp, ResponseValidator.UPDATE_VALIDATOR ) );
    }

    @Test
    public void updateProposalWithoutSite()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.siteId" ) ) );

        ProposalTestResponse p = proposalDAO.getRandomNonExpired();
        p.setSite( null );

        Assert.assertTrue( executeUpdateAndValidate( p, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }

    @Test
    public void updateProposalWithNonexistentSite()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "3002", "Resource site not found with id 999999", defineFields( "site" ) ) );

        ProposalTestResponse p = proposalDAO.getRandomNonExpired();
        p.setSite( new SiteTestResponse( "999999", "Site Inexistente", "A", null, null, null, null, null, null, null, null, "A" ) );

        Assert.assertTrue( executeUpdateAndValidate( p, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }

    @Test
    public void updateProposalWithInvalidSite()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "6003", "must match the following regular expression: [0-9]*", defineFields( "Proposal.siteId" ) ) );

        ProposalTestResponse p = proposalDAO.getRandomNonExpired();
        p.setSite( new SiteTestResponse( "aaaaaa", "Site com ID Ivalido", "A", null, null, null, null, null, null, null, null, "A" ) );

        Assert.assertTrue( executeUpdateAndValidate( p, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }

    @Test
    public void updateProposalWithInactiveSite()
    {
        try
        {
            SiteTestResponse inactiveSite = new SiteTestResponse( "Site Inativo", "I" );
            inactiveSite = siteUtils.saveOrUpdate( inactiveSite );

            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            // pq Offer.siteId a infra de erros e testes esta engessada, rever essa estrutura
            expectedErrors.addError( new ErrorTestResponse( "3007", "Site está inativo no momento", defineFields( "Offer.siteId" ) ) );

            ProposalTestResponse newProposal = proposalDAO.getRandomNonExpired();
            newProposal.setSite( inactiveSite );

            Assert.assertTrue( executeUpdateAndValidate( newProposal, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }

    @Test
    public void updateProposalWithoutValue()
    {
        try
        {
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.value", "Proposal.siteId" ) ) );

            ProposalTestResponse newProposal = proposalDAO.getRandomNonExpired();
            newProposal.setValue( null );

            Assert.assertTrue( executeUpdateAndValidate( newProposal, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }

    @Test
    public void updateProposalWithBlankValue()
    {
        try
        {
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Proposal.value" ) ) );
            expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,10}(\\.[0-9]{1,2})?", defineFields( "Proposal.value" ) ) );
            expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.siteId" ) ) );

            ProposalTestResponse newProposal = proposalDAO.getRandomNonExpired();
            newProposal.setValue( "" );

            Assert.assertTrue( executeUpdateAndValidate( newProposal, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }

    @Test
    public void updateProposalWithInvalidValue()
    {
        try
        {
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,10}(\\.[0-9]{1,2})?", defineFields( "Proposal.value" ) ) );
            expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.siteId" ) ) );

            ProposalTestResponse newProposal = proposalDAO.getRandomNonExpired();
            newProposal.setValue( "aaaaa" );

            Assert.assertTrue( executeUpdateAndValidate( newProposal, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }

    @Test
    public void updateProposalWithZeroValue()
    {
        try
        {
            ProposalTestResponse oldProp = proposalDAO.getRandomNonExpired();
            ProposalTestResponse newProp = new ProposalTestResponse( oldProp.getId(), siteUtils.getRandomActiveDiferentFrom( oldProp.getSite() ), "0.0", DateTimeUtil.getAFarDateFromNowInXmlFormat(), DateTimeUtil.getCurrentDateInXmlFormat() );

            Assert.assertTrue( executeUpdateAndValidate( newProp, oldProp, ResponseValidator.UPDATE_VALIDATOR ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }

    @Test
    public void updateProposalWithNegativeValue()
    {
        try
        {
            ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
            expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,10}(\\.[0-9]{1,2})?", defineFields( "Proposal.value" ) ) );
            expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Proposal.siteId" ) ) );

            ProposalTestResponse newProposal = proposalDAO.getRandomNonExpired();
            newProposal.setValue( "-0.1" );

            Assert.assertTrue( executeUpdateAndValidate( newProposal, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
        } catch ( Exception ex )
        {
            Assert.fail( String.format( "Falha persistir site, ERRO:%s ", ex.getMessage() ) );
        }
    }

}
