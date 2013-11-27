package uol.collective.offer.service.test.schedule;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uol.collective.offer.commons.test.dao.ProposalDAO;
import uol.collective.offer.commons.test.dao.SaleDAO;
import uol.collective.offer.commons.test.dao.SlotDAO;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;




public class ScheduleInsertTest extends ScheduleBase
{

    
    @Autowired
    private SlotDAO slotDAO;
    
    @Autowired
    private SaleDAO saleDAO;
    
    @Autowired
    private ProposalDAO proposalDAO;
    
    @Test
    public void teste(){
        scheduleDAO.deleteAll();
        saleDAO.deleteAll();
        slotDAO.deleteAll();
        
        ProposalTestResponse p = proposalDAO.getRandomNonExpired();
        SlotTestResponse s = slotDAO.saveOrUpdate( new SlotTestResponse( null, "description", "alias", "5", "A" ) );
        
        SaleTestResponse sale = new SaleTestResponse( null, p, DateTimeUtil.getCurrentDateInHibernateFormat(),
                DateTimeUtil.getAFarDateFromNowInHibernateFormat(), s, "A", "0" );
        sale = saleDAO.saveOrUpdate( sale );
        
        SaleTestResponse sale2 = new SaleTestResponse( null, p, DateTimeUtil.getCurrentDateInHibernateFormat(),
                DateTimeUtil.getAFarDateFromNowInHibernateFormat(), s, "A", "0" );
        sale2 = saleDAO.saveOrUpdate( sale2 );
        
        scheduleDAO.saveOrUpdate( new ScheduleTestResponse( sale, DateTimeUtil.getCurrentDateInHibernateFormat(), "A" ) );
        scheduleDAO.saveOrUpdate( new ScheduleTestResponse( sale2, DateTimeUtil.getCurrentDateInHibernateFormat(), "A" ) );
        
        ScheduleTestResponse example = new ScheduleTestResponse();
        example.setSale( sale2 );
        List<ScheduleTestResponse> sales = scheduleDAO.findByExample( example );
        if( sales != null && !sales.isEmpty() ){
                System.out.println( "\n\n\n\nsize:\n\n\n\n" + sales.size() );
        }else{
            System.out.println( "\n\n\n\n nenhum \n\n\n\n" );
        }
    }
    
    
}
