package uol.collective.offer.commons.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ClickEventDAO;
import uol.collective.offer.commons.test.model.impl.ClickEventTestModel;

@Repository
public class ClickEventDAOImpl extends GenericDAOImpl<ClickEventTestModel> implements ClickEventDAO
{

    private static Logger logger = Logger.getLogger( ClickEventDAO.class );

    @Override
    public ClickEventTestModel getLastClick()
    {
        String sqlQuery = "select max(idt_click_event) as maxId from radar_adm.click_event";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();
        return load( new ClickEventTestModel( (String) maxId ) );
    }

    @Override
    public void deleteAllCascade()
    {
        SQLQuery sqlQ = getSession().createSQLQuery( " Delete From radar_adm.click_image_roimeter " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Click_image_roimeter  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.click_consolidation " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Click_consolidation  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.transaction_event " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Transaction_event  ", sqlQ.executeUpdate() ) );        
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.click_event " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Click_event  ", sqlQ.executeUpdate() ) );
    }

    @Override
    public ClickEventTestModel insertDefaultClickEvent()
    {

        ClickEventTestModel clickEvent = new ClickEventTestModel( null, "1", null, "A", "127.0.0.1", "42.0", "42.0", "42.0", "0", "W", "123456", "0" );
        clickEvent = saveOrUpdate( clickEvent );
        return clickEvent;
    }
}