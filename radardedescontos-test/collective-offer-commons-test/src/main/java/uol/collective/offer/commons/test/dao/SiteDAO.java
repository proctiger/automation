package uol.collective.offer.commons.test.dao;

import uol.collective.offer.commons.test.model.impl.SiteTestResponse;


public interface SiteDAO extends GenericDAO<SiteTestResponse>
{

    public void deleteAllCascade();

    public SiteTestResponse getRandomActive();

    public SiteTestResponse getRandomActiveDiferentFrom(SiteTestResponse siteTestResponse);

    public SiteTestResponse insertRandomSite();
    
    public SiteTestResponse getLastActiveSiteInserted();
  
} 
