package uol.collective.offer.service.test.cpc;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.impl.CPCTestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;



public class CPCSelectTest extends CPCBase
{

    @Before
    public void initialize()
    {
        super.initialize();
    }

    @Ignore
    @Test  
    public void selectCostPerClickById()
    {
        List<CPCTestResponse> cpcs = insertSomeCPCs( 1 );
        Assert.assertNotNull( cpcs );
        Assert.assertEquals( cpcs.size(), 1 );
        CPCTestResponse cpc = cpcs.get( 0 );
        /** No servico esta como transiente */
        cpc.setCategory( null );
        
        Assert.assertTrue( executeSelectAndValidate( new CPCTestResponse( cpc.getId() ), cpc ) );
    }

    @Ignore
    @Test  
    public void selectListCostPerClick()
    {
        List<CPCTestResponse> cpcs = insertSomeCPCs( 2 );
        Assert.assertNotNull( cpcs );
        Assert.assertEquals( cpcs.size(), 2 );
        /** No servico esta como transiente */
        for( CPCTestResponse cpc : new ArrayList<CPCTestResponse>( cpcs ) ){
            cpcs.get( cpcs.indexOf( cpc ) ).setCategory( null );
        }
        
        ListTestResponse<CPCTestResponse> cpcsList = new ListTestResponseImpl<CPCTestResponse>( cpcs, CPCTestResponse.class );
        
        Assert.assertTrue( executeListAllAndValidate( cpcsList ) );
    }

    
}
