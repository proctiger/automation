package uol.collective.offer.service.test.sale;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;


public class SaleSelectTest extends SaleBase
{
    


    @Before
    public void initialize(){
        super.initialize();
        insertSomeSales( 2, SLOT, new Date(), SCHEDULING_DAYS, "A" );
    }


    @Test
    public void successfullySelect() {
        SaleTestResponse sale = saleDAO.getRandomActiveAndNonExpired();

        if( sale == null ){
            Assert.fail( "Erro ao testar um select com sucesso, nao foi possivel retornar um exemplo." );
        }else{
            Assert.assertTrue( executeSelectAndValidate( new SaleTestResponse( sale.getId() ) , sale ) );
        }
    }
    

    @Test
    public void selectInformingNonexistingId() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "10002", "Resource sale not found with id 999999", defineFields("sale") ) );

        Assert.assertTrue( executeSelectAndValidate( new SaleTestResponse( "999999" ) , expectedErrors ) );
    }

    
    @Test
    public void selectInformingInvalidId() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "An invalid client request was made", defineFields("/sale/teste") ) );

        Assert.assertTrue( executeSelectAndValidate( new SaleTestResponse( "teste" ), expectedErrors ) );
    }
    
    @Test
    public void selectAll() {
        ListTestResponse<SaleTestResponse> listFromDataBase = 
                new ListTestResponseImpl<SaleTestResponse>( saleDAO.findAll(), SaleTestResponse.class );
        
        Assert.assertTrue( executeListAllAndValidate( listFromDataBase ) );
    }    
    
    
    
        
}
