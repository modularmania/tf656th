package impl.dao;

import impl.object.IdentifiedObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<E extends IdentifiedObject> implements IBaseDao<E> {
    protected Class<E> entityClass;
    protected EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BaseDao() {
        ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        this.entityClass = (Class)genericSuperclass.getActualTypeArguments()[0];
    }

    public BaseDao(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(E entity) {
        if (entity.isNewObject()) {
            this.entityManager.persist(entity);
        } else {
            this.entityManager.merge(entity);
        }

    }

    public void remove(E entity) {
        this.entityManager.remove(entity);
    }

    public void refresh(E entity) {
        this.entityManager.refresh(entity);
    }

    public E findById(Object id) {
        return (E)(this.entityManager.find(this.entityClass, id));
    }

    public void flush() {
        this.entityManager.flush();
    }

    public void clear() {
        this.entityManager.clear();
    }

    public List<E> findAll() {
        Query query = this.entityManager.createQuery(String.format("SELECT h FROM %s h", this.entityClass.getName()));
        Object res = query.getResultList();
        return (List)res;
    }

    protected void saveEntity(IdentifiedObject entity) {
        if (entity.isNewObject()) {
            this.entityManager.persist(entity);
        } else {
            this.entityManager.merge(entity);
        }

    }

    public <T> T findById(Class<T> clazz, Object id) {
        return (T)this.entityManager.find(clazz, id);
    }

    public void deleteEntity(IdentifiedObject entity) {
        this.entityManager.remove(entity);
    }

    public List<E> findAllSortedById() {
        return this.findAllSortedByField("id");
    }

    public List<E> findAllSortedByField(String javaFieldName) {
        return this.findAllSortedByFieldByPartition(javaFieldName, (Integer)null, (Integer)null);
    }

    public List<E> findAllSortedByIdByPartition(Integer partitionSize, Integer partitionNumber) {
        return this.findAllSortedByFieldByPartition("id", partitionSize, partitionSize);
    }

    public List<E> findAllSortedByFieldByPartition(String javaFieldName, Integer partitionSize, Integer partitionNumber) {
        String hql = String.format(" SELECT h FROM %s h ORDER BY h.%s asc", this.entityClass.getName(), javaFieldName);
        Query query = this.entityManager.createQuery(hql);
        if (partitionSize != null && partitionNumber != null) {
            query.setFirstResult(partitionSize * partitionNumber);
            query.setMaxResults(partitionSize);
        }

        Object res = query.getResultList();
        return (List)res;
    }

    protected E findOne(Query query) {
        try {
            Object result = query.getSingleResult();
            return (E)(result);
        } catch (NoResultException var3) {
            return null;
        }
    }
}
