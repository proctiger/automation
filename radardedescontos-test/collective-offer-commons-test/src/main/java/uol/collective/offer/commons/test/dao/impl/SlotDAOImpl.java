package uol.collective.offer.commons.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.SlotDAO;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;

@Repository
public class SlotDAOImpl extends GenericDAOImpl<SlotTestResponse> implements SlotDAO
{
    
    private static final Logger logger = Logger.getLogger( SlotDAO.class ); 
    



    

    @Override
    public void deleteAllCascade(){
        SQLQuery sqlQ = getSession().createSQLQuery( " Delete From radar_adm.schedule " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Schedule  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.sale " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Sale  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.slot " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Slot  ", sqlQ.executeUpdate() ) );
    }

}
