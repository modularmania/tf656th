package impl.dao;

import impl.object.IdentifiedObject;

import java.util.List;

public interface IBaseDao<E> {
    void save(E var1);

    void remove(E var1);

    void refresh(E var1);

    E findById(Object var1);

    void flush();

    List<E> findAll();

    void clear();

    <T> T findById(Class<T> var1, Object var2);

    void deleteEntity(IdentifiedObject var1);

    List<E> findAllSortedById();

    List<E> findAllSortedByField(String var1);

    List<E> findAllSortedByIdByPartition(Integer var1, Integer var2);

    List<E> findAllSortedByFieldByPartition(String var1, Integer var2, Integer var3);
}
