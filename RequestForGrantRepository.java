package gov.ifms.grant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.grant.entity.GrantBudgetHeadWiseUtilSmryEntity;
@Repository
public interface RequestForGrantRepository extends JpaRepository<GrantBudgetHeadWiseUtilSmryEntity,Long> ,
JpaSpecificationExecutor<GrantBudgetHeadWiseUtilSmryEntity>, GenericDao{

	 @Query(value="SELECT * FROM GRANT.TGRA_BUDGET_UTIL_HEADWISE_SMRY entity WHERE entity.FIN_YEAR= :finYr AND entity.DEMAND_ID= :demandId AND entity.MAJORHEAD_ID= :majorHeadId AND entity.RC_FLAG= :rcFlag  AND entity.SUBMAJORHEAD_ID= :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :detailedHeadId AND entity.CHARGED_VOTED= :chargedVoted AND entity.ITEM_ID= :itemId AND entity.DEPARTMENT_ID= :departmentId AND GRANT_TYPE='SURRENDER' AND OBJECTCLASS_ID IS NOT NULL",nativeQuery=true) 
	public List<GrantBudgetHeadWiseUtilSmryEntity> getRequestForGrantDetail(@Param ("finYr")String finYr, @Param ("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId, @Param ("rcFlag")String rcFlag,@Param ("subMajorHeadId") Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId,@Param("subHeadId") Long subHeadId,@Param("detailedHeadId") Long detailedHeadId,@Param("chargedVoted") String chargedVoted,@Param("itemId") Long itemId,@Param("departmentId") Long departmentId);

	 @Query(value="SELECT * FROM GRANT.TGRA_BUDGET_UTIL_HEADWISE_SMRY entity WHERE entity.FIN_YEAR= :finYr AND entity.DEMAND_ID= :demandId AND entity.MAJORHEAD_ID= :majorHeadId AND entity.RC_FLAG= :rcFlag  AND entity.SUBMAJORHEAD_ID= :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :detailedHeadId AND entity.CHARGED_VOTED= :chargedVoted AND entity.DEPARTMENT_ID= :departmentId AND GRANT_TYPE='SURRENDER' AND OBJECTCLASS_ID IS NOT NULL",nativeQuery=true) 
		public List<GrantBudgetHeadWiseUtilSmryEntity> getRequestForGrantDetailWithoutItem(@Param ("finYr")String finYr, @Param ("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId, @Param ("rcFlag")String rcFlag,@Param ("subMajorHeadId") Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId,@Param("subHeadId") Long subHeadId,@Param("detailedHeadId") Long detailedHeadId,@Param("chargedVoted") String chargedVoted,@Param("departmentId") Long departmentId);
}
