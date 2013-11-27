package uol.collective.offer.service.test.proposal;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;

public class ProposalSelectTest extends ProposalBase
{

    @Before
    public void initialize()
    {
        super.initialize();
        insertSomeNonExpiredProposals( 10 );
    }

    @Test
    public void successfullySelect()
    {
        ProposalTestResponse proposal = proposalDAO.getRandomNonExpired();

        if ( proposal == null )
        {
            Assert.fail( "Erro ao testar um select com sucesso, nao foi possivel retornar um exemplo." );
        } else
        {
            Assert.assertTrue( executeSelectAndValidate( new ProposalTestResponse( proposal.getId() ), proposal ) );
        }
    }

    @Test
    public void selectProposalInformingNonexistingId()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "9002", "Resource proposal not found with id 999999", defineFields( "proposal" ) ) );

        Assert.assertTrue( executeSelectAndValidate( new ProposalTestResponse( "999999" ), expectedErrors ) );
    }

    @Test
    public void selectProposalInformingInvalidId()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "Uma requisição de cliente inválida foi efetuada", defineFields( "/proposal/teste" ) ) );

        Assert.assertTrue( executeSelectAndValidate( new ProposalTestResponse( "teste" ), expectedErrors ) );
    }

    @Test
    public void selectAll()
    {
        ListTestResponse<ProposalTestResponse> listFromDataBase = new ListTestResponseImpl<ProposalTestResponse>( proposalDAO.findAll(), ProposalTestResponse.class );

        Assert.assertTrue( executeListAllAndValidate( listFromDataBase ) );
    }

}
