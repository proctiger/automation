package uol.collective.offer.service.test.optOutHistory;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.OptOutHistoryDAO;
import uol.collective.offer.commons.test.model.impl.OptOutHistoryTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.service.test.AbstractBase;

@Component
public class OptOutHistoryBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( OptOutHistoryBase.class );
    
    protected static final String SLOT_DESCRIPTION = "slot description";
    protected static final String SLOT_ALIAS = "slot alias";

    @Autowired
    protected OptOutHistoryDAO optOutHistoryDAO;

    @Before
    public void initialize()
    {
        logger.info( "Preparando a base de dados..." );
        optOutHistoryDAO.deleteAll();
        logger.info( "Preparacao finalizada!" );
    }

    protected OptOutHistoryTestResponse getAnyOptOutEntry()
    {
        List<OptOutHistoryTestResponse> optOuts = optOutHistoryDAO.findAll();
        if ( optOuts == null || optOuts.isEmpty() )
        {
            insertNextOptOutEntryToday();
            optOuts = optOutHistoryDAO.findAll();
        }
        return ( optOuts == null || optOuts.isEmpty() ? null : optOuts.get( 0 ) );
    }    
    
    protected OptOutHistoryTestResponse insertNextOptOutEntryToday()
    {
        Long nextId = optOutHistoryDAO.getNextIdBySequence( OptOutHistoryTestResponse.class );
        return optOutHistoryDAO.saveOrUpdate( new OptOutHistoryTestResponse( nextId.toString(), DateTimeUtil.getCurrentDateInHibernateFormat(), String.format( "email%s@uol.com", nextId ), "1", null ) ); 
    }
    
    protected OptOutHistoryTestResponse insertNextOptOutEntry(String date)
    {
        Long nextId = optOutHistoryDAO.getNextIdBySequence( OptOutHistoryTestResponse.class );
        return optOutHistoryDAO.saveOrUpdate( new OptOutHistoryTestResponse( nextId.toString(), date, String.format( "email%s@uol.com", nextId ), "1", null ) ); 
    }
    
    protected List<OptOutHistoryTestResponse> listOptOutEntries(String startDate, String endDate) {
        return optOutHistoryDAO.getOptOutEntriesByDateRange( startDate, endDate ); 
    }
}
