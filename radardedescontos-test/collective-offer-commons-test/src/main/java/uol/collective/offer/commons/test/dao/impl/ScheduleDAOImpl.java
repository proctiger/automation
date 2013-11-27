package uol.collective.offer.commons.test.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ScheduleDAO;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.ScheduleTestResponse;

@Repository
@SuppressWarnings("unchecked")
public class ScheduleDAOImpl extends GenericDAOImpl<ScheduleTestResponse> implements ScheduleDAO
{

    private static final Logger logger = Logger.getLogger( ScheduleDAO.class );

    @Override
    public void deleteAllCascade()
    {
        SQLQuery sqlQ = getSession().createSQLQuery( " Delete From radar_adm.schedule " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Schedule  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.sale " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Sale  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.proposal " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Proposal  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.slot " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Slot  ", sqlQ.executeUpdate() ) );
    }

    @Override
    public List<ScheduleTestResponse> getSchedulesBySale(String status, SaleTestResponse sale)
    {
        Criteria cri = createCriteria();

        if ( !StringUtils.isEmpty( status ) )
        {
            cri.add( Restrictions.eq( "status", status ) );
        }
        if ( sale != null && !StringUtils.isEmpty( sale.getId() ) )
        {
            cri.add( Restrictions.eq( "sale.id", sale.getId() ) );
        }

        return cri.list();
    }

    @Override
    public List<ScheduleTestResponse> getSchedulesByAlias(String status, String slotAlias)
    {
        Criteria cri = createCriteria();

        if ( !StringUtils.isEmpty( status ) )
        {
            cri.add( Restrictions.eq( "status", status ) );
        }
        if ( !StringUtils.isEmpty( slotAlias ) )
        {
            cri.createAlias( "sale.slot", "s" );
            cri.add( Restrictions.eq( "s.alias", slotAlias ) );
        }

        return cri.list();
    }

    @Override
    public List<ScheduleTestResponse> getSchedulesByDateRange(String status, String startDate, String endDate)
    {
        Criteria cri = createCriteria();

        if ( !StringUtils.isEmpty( status ) )
        {
            cri.add( Restrictions.eq( "status", status ) );
        }
        if ( !StringUtils.isEmpty( startDate ) )
        {
            cri.add( Restrictions.ge( "displayDate", startDate ) );
        }
        if ( !StringUtils.isEmpty( endDate ) )
        {
            cri.add( Restrictions.le( "displayDate", endDate ) );
        }

        return cri.list();

    }
}
