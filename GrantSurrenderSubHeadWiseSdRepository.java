package gov.ifms.grant.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.ifms.budget.dto.BudgetMsDemandDto;
import gov.ifms.budget.dto.BudgetMsDetailHeadDto;
import gov.ifms.budget.dto.BudgetMsItemDto;
import gov.ifms.budget.dto.BudgetMsMinorHeadDto;
import gov.ifms.budget.dto.BudgetMsSubHeadDto;
import gov.ifms.budget.dto.BudgetMsSubMajorHeadDto;
import gov.ifms.common.dao.GenericDao;
import gov.ifms.grant.dto.GrantPubHdrDto;
import gov.ifms.grant.dto.GrantSurrenderReqParamsDto;
import gov.ifms.grant.entity.GrantAdSubHeadWiseDEntity;

public interface GrantSurrenderSubHeadWiseSdRepository extends JpaRepository<GrantAdSubHeadWiseDEntity, Long>,
		JpaSpecificationExecutor<GrantAdSubHeadWiseDEntity>, GenericDao {

	@Query(value = "SELECT UNIQUE md.DEMAND_ID,md.DEMAND_NAME,md.DEMAND_CODE FROM MASTER_V1.MS_DEMAND md ,MASTER_V1.LK_DEPT_DEMAND ldd WHERE md.IS_COMMON_DEMAND_ID = 2 OR md.DEMAND_ID IN "
			+ "(SELECT ldd2.DEMAND_ID FROM MASTER_V1.LK_DEPT_DEMAND ldd2 WHERE ldd2.DEPARTMENT_ID IN (SELECT mo.DEPARTMENT_ID FROM MASTER_V1.MS_OFFICE mo WHERE mo.OFFICE_ID = :officeId))", nativeQuery = true)
	List getAdDemandList(@Param("officeId") long officeId);

	@Query(value = "SELECT MD.DEMAND_ID,MD.DEMAND_NAME,MD.DEMAND_CODE FROM MASTER_V1.MS_DEMAND md WHERE MD.DEMAND_CODE != 999 OR MD.IS_COMMON_DEMAND_ID =2", nativeQuery = true)
	List getAllDemandList();

	@Query(value = "select new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(bmde.majorHeadId,bmde.majorHeadName, bmde.majorHeadCode,bmde.majorHeadSubType.lookUpInfoName) from BudgetMsMajorHeadEntity bmde   where bmde.budgetMsDemandEntities.demandId=?1")
	public List<GrantSurrenderReqParamsDto> getAdMajorHeadList(Long demandId);

	@Query(value = "select distinct new gov.ifms.budget.dto.BudgetMsDemandDto(shw.demandEntity.demandId,shw.demandEntity.demandName,shw.demandEntity.demandCode) from GrantAdSubHeadWiseDEntity shw where shw.grantReleaseType=?1 OR shw.grantReleaseType=?2")
	List<BudgetMsDemandDto> getDemandList(String releaseType1, String releaseType2);

	/*
	 * @Query(value =
	 * "select distinct new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.majorHeadEntity.majorHeadId,shw.majorHeadEntity.majorHeadName,shw.majorHeadEntity.majorHeadCode,shw.grantHeaderEntity.revenueCapital) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND (shw.grantReleaseType=?2 OR shw.grantReleaseType=?3)"
	 * ) List<GrantSurrenderReqParamsDto> getMajorHeadList(Long demandId, String
	 * releaseType1, String releaseType2);
	 */
	@Query(value="select distinct new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.majorHeadEntity.majorHeadId,shw.majorHeadEntity.majorHeadName,shw.majorHeadEntity.majorHeadCode,shw.majorHeadEntity.majorHeadSubType.lookUpInfoName) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND (shw.grantReleaseType=?2 OR shw.grantReleaseType=?3)")
	List<GrantSurrenderReqParamsDto> getMajorHeadList(Long demandId,String releaseType1,String releaseType2);


	@Query(value = "select distinct new gov.ifms.budget.dto.BudgetMsSubMajorHeadDto(shw.subMajorHeadEntity.subMajorHeadId,shw.subMajorHeadEntity.submajorHeadName,shw.subMajorHeadEntity.subMajorHeadCode) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND shw.majorHeadEntity.majorHeadId=?2 AND (shw.grantReleaseType=?3 OR shw.grantReleaseType=?4)")
	List<BudgetMsSubMajorHeadDto> getSubMajorHeadList(Long demandId, Long majorHeadId, String releaseType1,
			String releaseType2);

	@Query(value = "select distinct new gov.ifms.budget.dto.BudgetMsMinorHeadDto(shw.minorHeadEntity.minorHeadId,shw.minorHeadEntity.minorHeadName,shw.minorHeadEntity.minorHeadCode) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND shw.majorHeadEntity.majorHeadId=?2 AND shw.subMajorHeadEntity.subMajorHeadId=?3 AND (shw.grantReleaseType=?4 OR shw.grantReleaseType=?5)")
	List<BudgetMsMinorHeadDto> getMinorHeadList(Long demandId, Long majorHeadId, Long subMajorHeadId,
			String releaseType1, String releaseType2);

	@Query(value = "select distinct new gov.ifms.budget.dto.BudgetMsSubHeadDto(shw.subHeadEntity.subHeadId,shw.subHeadEntity.subHeadName,shw.subHeadEntity.subHeadCode) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND shw.majorHeadEntity.majorHeadId=?2 AND shw.subMajorHeadEntity.subMajorHeadId=?3 AND shw.minorHeadEntity.minorHeadId=?4 AND (shw.grantReleaseType=?5 OR shw.grantReleaseType=?6)")
	List<BudgetMsSubHeadDto> getSubHeadList(Long demandId, Long majorHeadId, Long subMajorHeadId, Long minorHeadId,
			String releaseType1, String releaseType2);

	@Query(value = "select distinct new gov.ifms.budget.dto.BudgetMsDetailHeadDto(shw.detailHeadEntity.detailHeadId,shw.detailHeadEntity.detailHeadName,shw.detailHeadEntity.detailHeadCode) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND shw.majorHeadEntity.majorHeadId=?2 AND shw.subMajorHeadEntity.subMajorHeadId=?3 AND shw.minorHeadEntity.minorHeadId=?4 AND shw.subHeadEntity.subHeadId=?5 AND (shw.grantReleaseType=?6 OR shw.grantReleaseType=?7)")
	List<BudgetMsDetailHeadDto> getDetailHeadList(Long demandId, Long majorHeadId, Long subMajorHeadId,
			Long minorHeadId, Long subHeadId, String releaseType1, String releaseType2);
	
	
	@Query(value="select distinct new gov.ifms.grant.dto.GrantPubHdrDto(shw.estimateType) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND shw.majorHeadEntity.majorHeadId=?2 AND shw.subMajorHeadEntity.subMajorHeadId=?3 AND shw.minorHeadEntity.minorHeadId=?4 AND shw.subHeadEntity.subHeadId=?5 AND shw.detailHeadEntity.detailHeadId=?6 AND shw.chargedVoted=?7 AND (shw.grantReleaseType=?8 OR shw.grantReleaseType=?9)")
	List<GrantPubHdrDto> getBudgetEstimateList(Long demandId, Long majorHeadId, Long subMajorHeadId,
			Long minorHeadId, Long subHeadId,Long detailedHeadId,String chargedVotedFlag, String releaseType1, String releaseType2);
	
	
	@Query(value="select distinct new gov.ifms.budget.dto.BudgetMsItemDto(shw.itemEntity.itemId, shw.itemEntity.itemName, shw.itemEntity.itemCode) from GrantAdSubHeadWiseDEntity shw where shw.demandEntity.demandId=?1 AND shw.majorHeadEntity.majorHeadId=?2 AND shw.subMajorHeadEntity.subMajorHeadId=?3 AND shw.minorHeadEntity.minorHeadId=?4 AND shw.subHeadEntity.subHeadId=?5 AND shw.detailHeadEntity.detailHeadId=?6 AND shw.estimateType=?7 AND (shw.grantReleaseType=?8 OR shw.grantReleaseType=?9)")
	List<BudgetMsItemDto> getItemList(Long demandId, Long majorHeadId, Long subMajorHeadId,
			Long minorHeadId, Long subHeadId,Long detailedHeadId, String estimateType, String releaseType1, String releaseType2);
	
	
	
	@Query(value="select distinct shw.releasedStateAmt from GrantAdSubHeadWiseDEntity shw where shw.toDepartmentEntity.departmentId=?1 and shw.grantReleaseType=?2")
	BigDecimal getAvailableGrantFromAd(Long deptId,String grantReleaseType);


	@Query(value="select distinct shw.releasedStateAmt from GrantAdSubHeadWiseDEntity shw where shw.fromDepartmentEntity.departmentId=?1 AND (shw.grantReleaseType=?2 OR shw.grantReleaseType=?3 OR shw.grantReleaseType=?4)")
	List<BigDecimal> getReleasedGrantToOfficers(Long deptId,String releaseType1, String releaseType2,String releaseType3);

}
