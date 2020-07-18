package gov.ifms.grant.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.edp.entity.EDPMsDepartmentEntity;
import gov.ifms.grant.entity.GrantAdSubHeadWiseDEntity;

public interface GrantAdSubHeadwiseUtilSmryRepository extends JpaRepository<GrantAdSubHeadWiseDEntity, Long>,
JpaSpecificationExecutor<GrantAdSubHeadWiseDEntity>, GenericDao{
	
@Query(value="select distinct shw.fromDepartmentEntity.departmentName from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9")
  public List<String> getFromDepartmentId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId);

@Query(value="select distinct shw.fromDepartmentEntity.departmentName from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='AD-DDO'")
public List<String> getFromADDDOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId);

@Query(value="select distinct shw.fromCOName from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='CO-DDO'")
public List<String> getFromCODDOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId);

@Query(value="select distinct shw.fromDepartmentEntity.departmentName from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='AD-CO'")
public List<String> getFromADCOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId);

@Query(value="select distinct shw.fromCOName from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='CO-CO'")
public List<String> getFromCOCOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId);


@Query(value="SELECT SUM(entity.GRANT_RELEASE_STATE_TILLDT) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType ",nativeQuery=true)
public BigDecimal getgrantreleaseStatetillDate(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
			@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_RELEASE_CENTER_TILLDT) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType ",nativeQuery=true)
public BigDecimal getgrantreleaseCentertillDate(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);

}
