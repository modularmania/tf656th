package unitinfo.dao;

import impl.dao.IBaseDao;
import unitinfo.enums.AffiliationStatus;
import unitinfo.enums.UnitRank;
import unitinfo.objects.Branch;
import unitinfo.objects.Career;
import unitinfo.objects.Unit;

import java.util.List;

public interface UnitDao extends IBaseDao<Unit> {

    List<Unit> findAllUnitsOrdered();

    List<Unit> findUnitsByBranch(Branch branch);

    List<Unit> findUnitsByRank(UnitRank rank);

    List<Unit> findUnitsByCareer(Career career);

    List<Unit> findUnitsByActivity(String lastActive);

    List<Unit> findUnitsByAffiliationStatus(AffiliationStatus affiliationStatus);

    Unit findUnitByUID(String uid);

    Unit findStaffUnits(String isStaff);
}
