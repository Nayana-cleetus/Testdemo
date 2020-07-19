package gov.ifms.grant.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.edp.entity.EDPMsDepartmentEntity;
import gov.ifms.grant.dto.GrantSurrenderReqParamsDto;
import gov.ifms.grant.entity.GrantAdSubHeadWiseDEntity;

public interface GrantAdSubHeadwiseUtilSmryRepository extends JpaRepository<GrantAdSubHeadWiseDEntity, Long>,
JpaSpecificationExecutor<GrantAdSubHeadWiseDEntity>, GenericDao{
	
	@Query(value="select distinct new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.fromDepartmentEntity.departmentName,shw.releasedStateAmt) from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='AD-AD'")
	List<GrantSurrenderReqParamsDto> getFromDepartmentId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId);

@Query(value="select distinct  new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.fromDepartmentEntity.departmentName,shw.releasedStateAmt) from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='AD-DDO' AND shw.toDDOName=?10")
 List<GrantSurrenderReqParamsDto> getFromADDDOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId, String userName);

@Query(value="select distinct new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.fromCOName,shw.releasedStateAmt) from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='CO-DDO' AND shw.toDDOName=?10")
 List<GrantSurrenderReqParamsDto> getFromCODDOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId ,String userName);

@Query(value="select distinct new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.fromDepartmentEntity.departmentName,shw.releasedStateAmt) from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='AD-CO' AND shw.toCOName=?10")
 List<GrantSurrenderReqParamsDto> getFromADCOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId,String userName);

@Query(value="select distinct new gov.ifms.grant.dto.GrantSurrenderReqParamsDto(shw.fromCOName,shw.releasedStateAmt) from GrantAdSubHeadWiseDEntity shw where shw.chargedVoted =?1 AND shw.toDepartmentEntity.departmentId=?2 AND shw.demandEntity.demandId=?3 AND shw.majorHeadEntity.majorHeadId=?4 AND shw.subMajorHeadEntity.subMajorHeadId=?5 AND shw.minorHeadEntity.minorHeadId=?6 AND shw.subHeadEntity.subHeadId=?7 AND shw.detailHeadEntity.detailHeadId=?8 AND shw.itemEntity.itemId in ?9 AND shw.grantReleaseType='CO-CO' AND shw.toCOName=?10")
 List<GrantSurrenderReqParamsDto> getFromCOCOId(String chargedVoted,Long departmentId,Long demandId,Long majorHeadId,Long subMajorHeadId,Long minorHeadId,Long subHeadId,Long detailHeadId,List <Long> itemId,String userName);


@Query(value="SELECT SUM(entity.GRANT_RELEASE_STATE_TILLDT) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='AD-CO' OR GRANT_RELEASE_TYPE ='AD-AD' OR GRANT_RELEASE_TYPE ='AD-DDO'",nativeQuery=true)
public List<BigDecimal> getgrantreleaseStatetillDate(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
			@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_RELEASE_CENTER_TILLDT) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='AD-CO' OR GRANT_RELEASE_TYPE ='AD-AD' OR GRANT_RELEASE_TYPE ='AD-DDO' ",nativeQuery=true)
public List<BigDecimal> getgrantreleaseCentertillDate(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_AMT_STATE) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='AD-DDO' ",nativeQuery=true)
public BigDecimal getgrantAmountStateADDDO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);

@Query(value="SELECT SUM(entity.GRANT_AMT_STATE) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='CO-DDO' ",nativeQuery=true)
public BigDecimal getgrantAmountStateCODDO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_AMT_CENTER) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='AD-DDO' ",nativeQuery=true)
public BigDecimal getgrantAmountCenterADDDO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_AMT_CENTER) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='CO-DDO' ",nativeQuery=true)
public BigDecimal getgrantAmountCenterCODDO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);

@Query(value="SELECT SUM(entity.GRANT_AMT_CENTER) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='AD-CO' ",nativeQuery=true)
public BigDecimal getgrantAmountCenterADCO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_AMT_CENTER) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='CO-CO' ",nativeQuery=true)
public BigDecimal getgrantAmountCenterCOCO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);

@Query(value="SELECT SUM(entity.GRANT_AMT_STATE) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='AD-CO' ",nativeQuery=true)
public BigDecimal getgrantAmountStateADCO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);


@Query(value="SELECT SUM(entity.GRANT_AMT_STATE) FROM GRANT.TGRA_AD_SUBHEADWISE_D entity WHERE entity.FROM_DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType AND entity.GRANT_RELEASE_TYPE ='CO-CO' ",nativeQuery=true)
public BigDecimal getgrantAmountStateCOCO(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
		@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);
}
