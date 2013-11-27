package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;


public interface ProposalDAO extends GenericDAO<ProposalTestResponse>
{

    public void deleteAllCascade();

    public ProposalTestResponse getRandomNonExpired();
  
} 
