package uol.collective.offer.commons.test.dao;

import java.util.List;

import uol.collective.offer.commons.test.model.impl.ConfigurationTestResponse;


public interface ConfigurationDAO extends GenericDAO<ConfigurationTestResponse>
{

    void deleteAllCascade();

    List<ConfigurationTestResponse> insertActiveTopSiteBlock(int quantity);

    List<ConfigurationTestResponse> insertActiveRotatedTopSiteBlock(int quantity);

    List<ConfigurationTestResponse> getRotatedTopSiteBlock(String string);

}
