package impl.object;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractSyncedDomainObject implements SyncedDomainObject {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "hash_code", nullable = true)
    private Integer hash;

    public String getId() {

        return id;
    }

    public void setId(final String id) {

        this.id = id;
    }

    @Override
    public Integer getHash() {
        return hash;
    }

    @Override
    public void setHash(Integer hash) {
        this.hash = hash;
    }

    @Override
    public final boolean isNewObject() {
        return getId() == null;
    }
}
