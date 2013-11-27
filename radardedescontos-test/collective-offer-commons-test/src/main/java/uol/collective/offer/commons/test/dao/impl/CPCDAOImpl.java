package uol.collective.offer.commons.test.dao.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.CPCDAO;
import uol.collective.offer.commons.test.dao.CategoryDAO;
import uol.collective.offer.commons.test.model.impl.CPCTestResponse;
import uol.collective.offer.commons.test.model.impl.CategoryTestResponse;

@Repository
public class CPCDAOImpl extends GenericDAOImpl<CPCTestResponse> implements CPCDAO
{


    @Autowired
    private CategoryDAO categoryDAO;
    

    @Override
    public CPCTestResponse getRandom()
    {
        CPCTestResponse str = new CPCTestResponse();
        return getByExampleForSimpleFieldsFirstFromList( str );
    }


    @Override
    public CPCTestResponse insertRandomCPC()
    {
        CPCTestResponse cpc = getRandom();
        if(cpc == null){
            CategoryTestResponse category = categoryDAO.getRandomActive();
            if( category != null ){
                Double d = ( new Random() ).nextDouble();
                cpc = new CPCTestResponse( null, d.toString(), category, null );
                cpc = saveOrUpdate( cpc );
            }
        }
        return cpc;
    }
}
