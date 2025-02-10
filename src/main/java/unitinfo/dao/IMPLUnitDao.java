package unitinfo.dao;

import impl.dao.BaseDao;
import jakarta.persistence.TypedQuery;
import unitinfo.enums.AffiliationStatus;
import unitinfo.enums.UnitRank;
import unitinfo.objects.Branch;
import unitinfo.objects.Career;
import unitinfo.objects.Unit;

import java.util.List;

public class IMPLUnitDao extends BaseDao<Unit> implements UnitDao {

    @Override
    public List<Unit> findAllUnitsOrdered() {

        final String sql = """
                SELECT u
                FROM Unit u
                ORDER BY u.uid
                """;

        final TypedQuery<Unit> query = entityManager.createQuery(sql, Unit.class);

        return query.getResultList();
    }

    @Override
    public List<Unit> findUnitsByBranch(Branch branch) {
        return List.of();
    }

    @Override
    public List<Unit> findUnitsByRank(UnitRank rank) {
        return List.of();
    }

    @Override
    public List<Unit> findUnitsByCareer(Career career) {
        return List.of();
    }

    @Override
    public List<Unit> findUnitsByActivity(String lastActive) {
        return List.of();
    }

    @Override
    public List<Unit> findUnitsByAffiliationStatus(AffiliationStatus affiliationStatus) {
        return List.of();
    }

    @Override
    public Unit findUnitByUID(String uid) {
        return null;
    }

    @Override
    public Unit findStaffUnits(String isStaff) {
        return null;
    }

}
