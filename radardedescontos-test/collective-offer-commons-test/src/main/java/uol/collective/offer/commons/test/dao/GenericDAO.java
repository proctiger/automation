package uol.collective.offer.commons.test.dao;

import java.util.List;


/**
 * Para os metodos genericos as transacoes estao sendo comitadas na saida
 * de cada um, pois, quando em comunicacao com os servicos a entidade a ser
 * enviada ja devera estar na base de dados, isso podera ser sobrescrito
 * em cada metodo de acordo com a necessidade do momento, utilizando a 
 * anotacao <code>@Transactional</code> e parametrizando da forma necessaria.
 * 
 * */
public interface GenericDAO<T>
{

    
    public T saveOrUpdate(T entity);

    public void delete(final T entity);
    
    public void deleteAll();

    public T load(T entity);

    public List<T> findAll();

    public List<T> findByExample(final T example);

    public List<T> getByExampleForSimpleFields(T example);

    public T getByExampleForSimpleFieldsFirstFromList(T example);

    public Long getNextIdBySequence(Class<?> persistentClass);


    
}
