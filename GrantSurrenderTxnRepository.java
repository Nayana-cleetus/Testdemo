package gov.ifms.grant.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.grant.entity.GrantSurrenderDEntity;
import gov.ifms.grant.entity.GrantSurrenderHdrEntity;

@Repository

@Transactional
public interface GrantSurrenderTxnRepository extends JpaRepository<GrantSurrenderDEntity, Long>,
		JpaSpecificationExecutor<GrantSurrenderDEntity>, GenericDao {

	List<GrantSurrenderDEntity> findByGrantSurrenderHdrId(GrantSurrenderHdrEntity hdrEntity);
	@Query(value="SELECT SUM(entity.SURRENDER_AMT) FROM GRANT.TGRA_GRANT_SURRENDER_D entity WHERE entity.DEPARTMENT_ID = :departmentId AND  entity.DEMAND_ID = :demandId AND entity.MAJORHEAD_ID = :majorHeadId AND entity.SUBMAJORHEAD_ID = :subMajorHeadId AND entity.MINORHEAD_ID= :minorHeadId AND entity.SUBHEAD_ID= :subHeadId AND entity.DETAILHEAD_ID= :deatiledHeadId AND entity.ITEM_ID IN( :itemId) ",nativeQuery=true)
	public BigDecimal findSurrenderAmt(@Param ("departmentId")Long departmentId, @Param("demandId")Long demandId, @Param("majorHeadId")Long majorHeadId,
			@Param("subMajorHeadId")Long subMajorHeadId,@Param("minorHeadId") Long minorHeadId, @Param("subHeadId")Long subHeadId,@Param("deatiledHeadId") Long deatiledHeadId, @Param("itemId") List<Long> itemId);
	
	
	@Query(value="select sum(entity.surrenderAmt) from GrantSurrenderDEntity entity where entity.departmentId=?1")
	BigDecimal getSurrenderAmount(Long deptId);
}
