package uol.collective.offer.commons.test.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uol.collective.offer.commons.test.dao.ProposalDAO;
import uol.collective.offer.commons.test.dao.SiteDAO;
import uol.collective.offer.commons.test.model.impl.ProposalTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;


@Repository
public class ProposalDAOImpl extends GenericDAOImpl<ProposalTestResponse> implements ProposalDAO
{

    private static final Logger logger = Logger.getLogger( ProposalDAO.class );
    
    
    @Autowired
    private SiteDAO siteDAO;
    
    
    @Override
    public void deleteAllCascade(){
        SQLQuery sqlQ = getSession().createSQLQuery( " Delete From radar_adm.schedule " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Schedule  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.sale " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Sale  ", sqlQ.executeUpdate() ) );
        sqlQ = getSession().createSQLQuery( " Delete From radar_adm.proposal " );
        logger.info( String.format( "%s registro(s) apagado(s) da tabela Proposal  ", sqlQ.executeUpdate() ) );
    }

    @Override
    public ProposalTestResponse getRandomNonExpired()
    {
        String sqlQuery = "select max(idt_proposal) as maxId from radar_adm.proposal where trunc(dat_expiration) > trunc(sysdate)";
        SQLQuery query = getSession().createSQLQuery( sqlQuery );
        query.addScalar( "maxId", new StringType() );
        Object maxId = query.uniqueResult();
        
        if( maxId == null ){
            return saveOrUpdate( 
                    new ProposalTestResponse( null, siteDAO.getRandomActive(), "100.0", 
                            DateTimeUtil.getAFarDateFromNowInHibernateFormat(), DateTimeUtil.getCurrentDateInHibernateFormat() ) );
        }else{
            return load( new ProposalTestResponse( (String) maxId ) );
        }
    }
    
}
