package uol.collective.offer.service.test.slot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;


public class SlotSelectTest extends SlotBase
{
    


    @Before
    public void initialize(){
        super.initialize();
        insertSomeSlots( 5 );
    }


    @Test
    public void successfullySelect() {
        SlotTestResponse slot = getAnySlot();

        if( slot == null ){
            Assert.fail( "Erro ao testar um select com sucesso, nao foi possivel retornar um exemplo." );
        }else{
            Assert.assertTrue( executeSelectAndValidate( new SlotTestResponse( slot.getId() ) , slot ) );
        }
    }
    

    @Test
    public void selectInformingNonexistingId() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12000", "Resource slot not found with id 999999", defineFields("slot") ) );

        Assert.assertTrue( executeSelectAndValidate( new SlotTestResponse( "999999" ) , expectedErrors ) );
    }

    
    @Test
    public void selectInformingInvalidId() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "An invalid client request was made", defineFields("/slot/teste") ) );

        Assert.assertTrue( executeSelectAndValidate( new SlotTestResponse( "teste" ), expectedErrors ) );
    }
    
    @Test
    public void selectAll() {
        ListTestResponse<SlotTestResponse> listFromDataBase = 
                new ListTestResponseImpl<SlotTestResponse>( slotDAO.findAll(), SlotTestResponse.class );
        
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase ) );
    }    
    
    
    
        
}
