package uol.collective.offer.commons.test.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ConfigurationDAO;
import uol.collective.offer.commons.test.model.impl.ConfigurationTestResponse;


@Repository
@SuppressWarnings("unchecked")
public class ConfigurationDAOImpl  extends GenericDAOImpl<ConfigurationTestResponse> implements ConfigurationDAO
{

    protected static final String KEY = "sites.vip.page.";
    protected static final String VALUE = "3710,3720,3730,3740,3750";
    protected static final String ACTIVE = "1";
    private static final String ROTATED_KEY = "sites.vip.page.rotated.";

    @Override
    public void deleteAllCascade()
    {
        deleteAll();
    }

    @Override
    public List<ConfigurationTestResponse> insertActiveTopSiteBlock(int quantity)
    {
        return insertActiveTopSiteBlock(quantity, KEY);
    }

    @Override
    public List<ConfigurationTestResponse> insertActiveRotatedTopSiteBlock(int quantity)
    {
        return insertActiveTopSiteBlock(quantity, ROTATED_KEY);
    }

    
    private List<ConfigurationTestResponse> insertActiveTopSiteBlock(int quantity, String key)
    {
        List<ConfigurationTestResponse> confs = new ArrayList<ConfigurationTestResponse>();
        
        ConfigurationTestResponse conf = null;
        for (int i=1; i<=quantity; i++){
            conf = new ConfigurationTestResponse( null, key + i, VALUE.replace( "0", String.valueOf( i ) ), ACTIVE );
            confs.add( saveOrUpdate( conf ) );
        }

        return confs;
    }

    
    @Override
    public List<ConfigurationTestResponse> getRotatedTopSiteBlock(String status)
    {
        ConfigurationTestResponse conf = new ConfigurationTestResponse( null, ROTATED_KEY, null, status);
        Example example = Example.create( conf );
        example.enableLike( MatchMode.ANYWHERE );
        Criteria cri = createCriteria();
        cri.add( example );
        return cri.list();
    }
    
    
}
