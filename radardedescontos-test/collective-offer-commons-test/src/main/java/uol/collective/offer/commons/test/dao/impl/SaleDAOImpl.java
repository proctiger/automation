package uol.collective.offer.commons.test.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ProposalDAO;
import uol.collective.offer.commons.test.dao.SaleDAO;
import uol.collective.offer.commons.test.dao.SlotDAO;
import uol.collective.offer.commons.test.model.impl.SaleTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;


@Repository
public class SaleDAOImpl extends GenericDAOImpl<SaleTestResponse> implements SaleDAO
{

    private static final Logger logger = Logger.getLogger( SaleDAO.class );

    @Autowired
    private ProposalDAO proposalDAO;

    @Autowired
    private SlotDAO slotDAO;

    @Override
    public void deleteAllCascade()
    {
        SQLQuery sqlQ = getSession().createSQLQuery( " Delete From radar_adm.schedule " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Schedule  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.sale " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Sale  ", sqlQ.executeUpdate() ) );
    }

    @Override
    public SaleTestResponse getRandomActiveAndNonExpired()
    {
        String sqlQuery = "select max(idt_sale) as maxId from radar_adm.sale where trunc(dat_end) > trunc(sysdate) and ind_status = 'A'";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();

        if ( maxId == null )
        {
            Date startDate = DateUtils.truncate( new Date(), Calendar.DATE );
            Date endDate = DateUtils.addDays( startDate, 30 );
            return saveOrUpdate( new SaleTestResponse( null, proposalDAO.getRandomNonExpired(), 
                    DateTimeUtil.formatDateToStringHibernate( startDate ), DateTimeUtil.formatDateToStringHibernate( endDate ), 
                    slotDAO.getByExampleForSimpleFieldsFirstFromList( new SlotTestResponse() ), "A" ) );
        } else
        {
            return load( new SaleTestResponse( (String) maxId ) );
        }
    }

}
