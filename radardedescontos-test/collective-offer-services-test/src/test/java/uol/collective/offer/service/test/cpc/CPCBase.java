package uol.collective.offer.service.test.cpc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.CPCDAO;
import uol.collective.offer.commons.test.model.impl.CPCTestResponse;
import uol.collective.offer.service.test.AbstractBase;


@Component
public class CPCBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( CPCBase.class );

    
    @Autowired
    protected CPCDAO cpcDAO;

    
    
    @Before
    public void initialize(){
        super.initialize();
        logger.info( "Preparando a base de dados..." );
//        siteDAO.deleteAllCascade();
        logger.info( "Preparacao finalizada!" );
    }
    
    


    
    public List<CPCTestResponse> insertSomeCPCs( int quantity )
    {
        List<CPCTestResponse> cpcs = new ArrayList<CPCTestResponse>();
        for(int i = 0; i < quantity; i++){
            cpcs.add( cpcDAO.insertRandomCPC() );
        }
        return cpcs;
    }


    public CPCTestResponse getRandom()
    {
        return cpcDAO.getRandom();
    }


    
}
