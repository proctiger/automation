package uol.collective.offer.service.test.slot;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.SlotDAO;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.service.test.AbstractBase;

@Component
public class SlotBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( SlotBase.class );
    
    protected static final String SLOT_DESCRIPTION = "slot description";
    protected static final String SLOT_ALIAS = "slot alias";

    @Autowired
    protected SlotDAO slotDAO;

    @Before
    public void initialize()
    {
        logger.info( "Preparando a base de dados..." );
        slotDAO.deleteAllCascade();
        logger.info( "Preparacao finalizada!" );
    }

    public SlotTestResponse getAnySlot()
    {
        List<SlotTestResponse> slots = slotDAO.findAll();
        if ( slots == null || slots.isEmpty() )
        {
            insertSomeSlots( 1 );
            slots = slotDAO.findAll();
        }
        return ( slots == null || slots.isEmpty() ? null : slots.get( 0 ) );
    }

    public SlotTestResponse insertNextSlot(String slotSize)
    {
        Long nextId = slotDAO.getNextIdBySequence( SlotTestResponse.class );
        return slotDAO.saveOrUpdate( new SlotTestResponse( null, "Description-" + nextId, "Alias-" + nextId, slotSize, "A" ) );
    }

    protected void insertSomeSlots(int quantity)
    {
        SlotTestResponse entity;
        for ( int i = 1; i <= quantity; i++ )
        {
            entity = new SlotTestResponse( null, "Description-" + i, "Alias-" + i, String.valueOf( i * 2 ), "A" );
            slotDAO.saveOrUpdate( entity );
        }
    }

}
