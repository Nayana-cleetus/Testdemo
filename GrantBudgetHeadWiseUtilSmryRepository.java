package gov.ifms.grant.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto;
import gov.ifms.grant.entity.GrantBudgetHeadWiseUtilSmryEntity;

public interface GrantBudgetHeadWiseUtilSmryRepository extends JpaRepository<GrantBudgetHeadWiseUtilSmryEntity,Long> , 
JpaSpecificationExecutor<GrantBudgetHeadWiseUtilSmryEntity>, GenericDao { 
	
	
	 @Query(value ="select entity from GrantBudgetHeadWiseUtilSmryEntity entity   where  entity.finYr=?1 and entity.rcFlag=?2 and entity.departmentId=?3 and entity.demandId=?4 and entity.majorHeadId=?5 and entity.subMajorHeadId=?6"
			  + " and entity.minorHeadId=?7 and entity.subHeadId=?8 " +" and entity.detailHeadId=?9 and entity.chargedVoted=?10 and entity.itemId in ?11  and entity.grantType='FD2AD-CSS' and entity.objectClassId is null")
			public List<GrantBudgetHeadWiseUtilSmryEntity> getSubHeadWiseDetails(String finYr, String rcFlag, Long departmentId, Long demandId, Long majorHeadId, 
					Long subMajorHeadId, Long minorHeadId, Long subHead, Long deatiledHead, String chargedVoted, List<Long> itemId);
	
	
	 @Query(value ="select entity from GrantBudgetHeadWiseUtilSmryEntity entity   where  entity.finYr=?1 and entity.rcFlag=?2 and entity.departmentId=?3 and entity.demandId=?4 and entity.majorHeadId=?5 and entity.subMajorHeadId=?6"
			  + " and entity.minorHeadId=?7 and entity.subHeadId=?8 " +" and entity.detailHeadId=?9 and entity.chargedVoted=?10 and entity.itemId in ?11  and entity.grantType='FD2AD-CSS' and entity.objectClassId is not null")
			public List<GrantBudgetHeadWiseUtilSmryEntity> getItemClassWiseDetails(String finYr, String rcFlag, Long departmentId, Long demandId, Long majorHeadId, 
					Long subMajorHeadId, Long minorHeadId, Long subHead, Long deatiledHead, String chargedVoted, List<Long> itemId);
	 
	 
	 
	 
//	 @Query(value="select data from GrantBudgetHeadWiseUtilSmryEntity data where data.finYr=?1 and data.rcFlag=?2 and data.demandId=?3 "
//	 + "and data.majorHeadId=?4 and data.subMajorHeadId=?5 and data.minorHeadId=?6 and data.subHeadId=?7 and "
//	 + "data.detailHeadId=?8 and data.chargedVoted=?9 and data.itemId in ?10 and data.grantType='SURRENDER' and data.objectClassId is not null")
//	 public List<GrantBudgetHeadWiseUtilSmryEntity> getSurrenderItemClassWiseDetails(String finYr, String rcFlag, Long demandId, Long majorHeadId,
//	Long subMajorHeadId, Long minorHeadId, Long subHeadId, Long deatiledHeadId, String chargedVoted, List<Long> itemId);
//	 
	 
	 @Query(value="select distinct new gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto(util.beStatePercent,util.beCenterPercent) from GrantBudgetHeadWiseUtilSmryEntity util where util.finYr=?1 and util.demandId=?2 and util.majorHeadId=?3 and util.subMajorHeadId=?4 and util.minorHeadId=?5 and util.subHeadId=?6 and util.detailHeadId=?7 and util.chargedVoted=?8")
	 public GrantBudgetHeadWiseUtilSmryDto getStateCenterPercent(String finYr, Long demandId, Long majorHeadId,
	Long subMajorHeadId, Long minorHeadId, Long subHeadId, Long detailHeadId,String chargedVoted);
	 
	 @Query(value="SELECT * FROM GRANT.TGRA_BUDGET_UTIL_HEADWISE_SMRY entity WHERE entity.FIN_YEAR = :finYr AND entity.RC_FLAG = :rcFlag AND entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID = :minorHeadId AND entity.SUBHEAD_ID = :subHeadId AND entity.DETAILHEAD_ID = :deatiledHeadId AND entity.CHARGED_VOTED = :chargedVoted AND entity.ITEM_ID IN( :itemId) AND GRANT_TYPE='SURRENDER' AND OBJECTCLASS_ID IS NOT NULL",nativeQuery=true)
	 public List<GrantBudgetHeadWiseUtilSmryEntity> getSurrenderItemClassWiseDetail(@Param ("finYr")String finYr, @Param("rcFlag")String rcFlag, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
				@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId,@Param("chargedVoted") String chargedVoted,@Param("itemId") List<Long> itemId);
	 
	 
	 
}
