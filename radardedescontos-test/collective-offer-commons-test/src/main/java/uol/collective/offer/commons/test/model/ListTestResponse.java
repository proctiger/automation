package uol.collective.offer.commons.test.model;

import java.io.Serializable;
import java.util.List;


public interface ListTestResponse<T> extends Serializable
{

    
    public abstract List<T> getEntities();
    public abstract void addEntities(List<T> entities);
    public abstract void addEntity(T entity);
    public abstract int size();
    public abstract boolean isEmpty();
    public abstract Class<T> getPersistentClass();
    
}
