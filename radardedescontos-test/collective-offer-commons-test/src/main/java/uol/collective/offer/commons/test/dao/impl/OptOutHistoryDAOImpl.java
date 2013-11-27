package uol.collective.offer.commons.test.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.OptOutHistoryDAO;
import uol.collective.offer.commons.test.model.impl.OptOutHistoryTestResponse;

@Repository
@SuppressWarnings("unchecked")
public class OptOutHistoryDAOImpl extends GenericDAOImpl<OptOutHistoryTestResponse> implements OptOutHistoryDAO
{
    
    @Override
    public List<OptOutHistoryTestResponse> getOptOutEntriesByDateRange(String startDate, String endDate)
    {
        Criteria cri = createCriteria();

        if ( !StringUtils.isEmpty( startDate ) )
        {
            cri.add( Restrictions.ge( "optOutDate", startDate ) );
        }
        if ( !StringUtils.isEmpty( endDate ) )
        {
            cri.add( Restrictions.le( "optOutDate", endDate ) );
        }

        return cri.list();
    }
}
