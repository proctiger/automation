package uol.collective.offer.commons.test.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.dao.GenericDAO;
import uol.collective.offer.commons.test.model.TestEntity;


@SuppressWarnings({"rawtypes","unchecked"})
@Transactional(isolation = Isolation.READ_COMMITTED)
public class GenericDAOImpl<T extends TestEntity> extends HibernateDaoSupport implements GenericDAO<T>
{

    private static final Logger logger = Logger.getLogger( GenericDAO.class );
    private Class<T> persistentClass;

    
    
    
    @Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
    
    private Class<T> getPersistentClass() {
        if(this.persistentClass == null){
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.persistentClass;
    }

    @Override
    public T saveOrUpdate(T entity) {
        try {
            /** Workaround no insert por causa do tipo do ID das entidades (String) */
            populateId( entity );
            logger.info( String.format("Persistindo o objeto %s na base, conteudo %s", getPersistentClassName(), entity) );
            getHibernateTemplate().saveOrUpdate(entity);
            logger.info("Objeto persistido com sucesso!");
            return entity;
        } catch (final DataAccessException ex) {
            logger.error(ex);
            throw ex;
        }
    }


    private void populateId(T entity)
    {
        Serializable id = getEntityId( entity );
        if(id == null){
            Long nextId = getNextIdBySequence();
            if(nextId != null){
                setId( entity, nextId.toString() );
            }
        }
    }


    private Long getNextIdBySequence() 
    {
        return getNextIdBySequence( getPersistentClass() );
    }

    @Override
    public Long getNextIdBySequence( Class<?> persistentClass ) 
    {
        SequenceName seqAnn = persistentClass.getAnnotation( SequenceName.class );
        if( seqAnn != null ){
            String sql = String.format( " Select %s%s.NextVal as \"nextId\" From Dual ", 
                    (StringUtils.isBlank( seqAnn.schema() ) ? "" : seqAnn.schema() + "."), seqAnn.name() );
            
            return (Long) getSession().createSQLQuery( sql ).addScalar( "nextId", new LongType() ).uniqueResult();
        }else{
           logger.error( String.format( 
                   "Nenhuma anotacao para sequencia presente na Classe %s", getPersistentClassName()) );
           return null;
        }
    }

    @Override
    public void delete(T entity) {
        try {
            logger.info( String.format( 
                    "Apagando o registro da entidade '%s'", getPersistentClassName() ) );
            getHibernateTemplate().delete(entity);
        } catch (final DataAccessException ex) {
            logger.error(ex);
            throw ex;
        }
    }

    
    @Override
    public void deleteAll() {
        try {
            logger.info( String.format( 
                    "Apagando registros da entidade '%s'", getPersistentClassName() ) );
            List<T> entities = findAll();
            getHibernateTemplate().deleteAll( entities );
            logger.info( String.format( 
                    "Quantidade de registros apagados '%s'", ( entities == null ? 0 : entities.size() ) ) );
        } catch (final DataAccessException ex) {
            logger.error(ex);
            throw ex;
        }
    }
    
    

    @Override
    public T load(T entity) {
        try {
            return getHibernateTemplate().get(getPersistentClass(), getEntityId(entity));
        } catch (final DataAccessException ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<T> findAll() {
        try {
            logger.info( String.format( 
                    "Buscando todos os registros da entidade '%s'", getPersistentClassName() ) );
            List<T> entities = getHibernateTemplate().loadAll(getPersistentClass());
            logger.info( String.format( 
                    "Quantidade de registros selecionados '%s'", ( entities == null ? 0 : entities.size() ) ) );
            return entities;
        } catch (final DataAccessException ex) {
            logger.error(ex);
            throw ex;
        }
    }

    @Override
    public List<T> findByExample(T example) {
        try {
            return getHibernateTemplate().findByExample(example);
        } catch (final DataAccessException ex) {
            logger.error(ex);
            throw ex;
        }
    }


    private Serializable getEntityId(T entity)
    {
        try{
            if(entity != null){
                Class objClass = entity.getClass();
                Method getId = objClass.getMethod("getId");
                Object ret = getId.invoke(entity);
                return (Serializable) ret;
            }
        } catch (IllegalAccessException ex) {
            logger.error(ex);
        } catch (IllegalArgumentException ex) {
            logger.error(ex);
        } catch (InvocationTargetException ex) {
            logger.error(ex);
        } catch (NoSuchMethodException ex) {
            logger.error(ex);
        } catch (SecurityException ex) {
            logger.error(ex);
        }
        return null;
    }
    
    protected Criteria createCriteria()
    {
        return getSession().createCriteria( getPersistentClass() );
    }
    

    private void setId(T entity, String id)
    {
        if(entity != null && id != null){
            try
            {
                Field field = entity.getClass().getDeclaredField( "id" );
                field.setAccessible( true );
                field.set( entity, id );
            } catch (IllegalAccessException ex) {
                logger.error(ex);
            } catch (IllegalArgumentException ex) {
                logger.error(ex);
            } catch (SecurityException ex) {
                logger.error(ex);
            } catch ( NoSuchFieldException ex ) {
                logger.error(ex);
            }
        }
    }
    
    
    private Object getPersistentClassName()
    {
        return getPersistentClass().getSimpleName();
    }
    
    
    @Override
    public List<T> getByExampleForSimpleFields(T example)
    {
        Example criterion = Example.create( example )
                .enableLike(MatchMode.ANYWHERE);  
        Criteria cri = createCriteria().add( criterion );
        return cri.list();
    }

    
    @Override
    public T getByExampleForSimpleFieldsFirstFromList(T example)
    {
        List<T> resultList = getByExampleForSimpleFields( example );
        if( resultList != null && !resultList.isEmpty() ){
            return resultList.get( 0 );
        }
        return null;
    }


    
    
}
