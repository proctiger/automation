package uol.collective.offer.commons.test.dao.impl;

import java.util.Random;

import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.CityDAO;
import uol.collective.offer.commons.test.model.impl.CityTestResponse;

@Repository
public class CityDAOImpl extends GenericDAOImpl<CityTestResponse> implements CityDAO
{

    public CityTestResponse getLastActiveCityInserted()
    {
        String sqlQuery = "select max(idt_city) as maxId from radar_adm.city where ind_status = 'A'";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();

        if ( maxId == null )
        {
            return saveOrUpdate( new CityTestResponse( String.format( "random-name-%s", new Random().nextInt( 100000 ) ), "A", "PE" ) );
        } else
        {
            return load( new CityTestResponse( (String) maxId ) );
        }
    }
}
