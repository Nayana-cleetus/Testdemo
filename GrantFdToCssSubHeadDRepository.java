package gov.ifms.grant.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.grant.entity.GrantFdToCssSubHeadDEntity;

public interface GrantFdToCssSubHeadDRepository extends JpaRepository<GrantFdToCssSubHeadDEntity,Long> , 
JpaSpecificationExecutor<GrantFdToCssSubHeadDEntity>, GenericDao {

	@EntityGraph(attributePaths= {"itemClassList"})
	List<GrantFdToCssSubHeadDEntity> findByGrantHeaderEntityGrantId(Long grantId);
	
	@Query(value="SELECT SUM(entity.GRANT_AMT_STATE) FROM GRANT.TGRA_FD2AD_CSS_SUBHEAD_D entity WHERE entity.DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType ",nativeQuery=true)
	public BigDecimal findGrantAmtState(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
			@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);
	
	
	
	@Query(value="SELECT SUM(entity.GRANT_AMT_CENTER) FROM GRANT.TGRA_FD2AD_CSS_SUBHEAD_D entity WHERE entity.DEPARTMENT_ID = :departmentId AND entity.CHARGED_OR_VOTED = :chargedVoted AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) AND entity.ESTIMATE_TYPE= :budgetEstimateType ",nativeQuery=true)
	public BigDecimal findGrantAmtCenter(@Param ("departmentId")Long departmentId, @Param("chargedVoted")String chargedVoted, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
			@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId,@Param("budgetEstimateType")String budgetEstimateType);
	
}
