package impl.object;

import impl.dao.IBaseDao;

public interface IRepositoryDao extends IBaseDao<IdentifiedObject> {
    void flush();

    void commit();

    <T> T findById(Class<T> var1, Object var2);

    void clear();

    void insertEntity(Object var1);
}
