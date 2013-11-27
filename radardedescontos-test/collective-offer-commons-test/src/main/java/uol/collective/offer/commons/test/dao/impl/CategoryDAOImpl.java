package uol.collective.offer.commons.test.dao.impl;

import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.CategoryDAO;
import uol.collective.offer.commons.test.model.impl.CategoryTestResponse;

@Repository
public class CategoryDAOImpl extends GenericDAOImpl<CategoryTestResponse> implements CategoryDAO
{

    @Override
    public CategoryTestResponse getRandomActive()
    {
        CategoryTestResponse str = new CategoryTestResponse();
        str.setStatus( "A" );
        return getByExampleForSimpleFieldsFirstFromList( str );
    }

}
