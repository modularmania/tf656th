package impl.object;

public interface SyncedDomainObject extends IdentifiedObject {

    String getId();

    Integer getHash();

    void setHash(Integer hash);
}
