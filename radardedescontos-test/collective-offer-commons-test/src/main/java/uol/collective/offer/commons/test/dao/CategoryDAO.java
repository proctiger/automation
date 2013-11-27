package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.CategoryTestResponse;


public interface CategoryDAO extends GenericDAO<CategoryTestResponse>
{

    public CategoryTestResponse getRandomActive();

  
} 
