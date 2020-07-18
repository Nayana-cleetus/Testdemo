package gov.ifms.grant.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import gov.ifms.common.dao.GenericDao;
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
	 
	 
	 
	 
	 @Query(value="select data from GrantBudgetHeadWiseUtilSmryEntity data where data.finYr=?1 and data.rcFlag=?2 and data.demandId=?3 "
	 + "and data.majorHeadId=?4 and data.subMajorHeadId=?5 and data.minorHeadId=?6 and data.subHeadId=?7 and "
	 + "data.detailHeadId=?8 and data.chargedVoted=?9 and data.itemId in ?10 and data.grantType='SURRENDER' and data.objectClassId is not null")
	 public List<GrantBudgetHeadWiseUtilSmryEntity> getSurrenderItemClassWiseDetails(String finYr, String rcFlag, Long demandId, Long majorHeadId,
	Long subMajorHeadId, Long minorHeadId, Long subHeadId, Long deatiledHeadId, String chargedVoted, List<Long> itemId);
	 
}
