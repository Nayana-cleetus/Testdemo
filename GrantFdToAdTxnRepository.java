package gov.ifms.grant.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.ifms.common.dao.GenericDao;
import gov.ifms.grant.entity.GrantFdToAdHdrEntity;
import gov.ifms.grant.entity.GrantFdToAdSaveEntity;
import gov.ifms.grant.entity.GrantFdToAdTxnEntity;
import gov.ifms.grant.entity.GrantHdrEntity;



@Repository

@Transactional
public interface GrantFdToAdTxnRepository
		extends JpaRepository<GrantFdToAdTxnEntity, Long> ,
JpaSpecificationExecutor<GrantFdToAdTxnEntity>, GenericDao{

	
	/**
	 * Find by grand hdr id.
	 *
	 * @param hdrEntity the hdr entity
	 * @return the list
	 */
	List<GrantFdToAdTxnEntity> findByGrantId(GrantFdToAdHdrEntity hdrEntity);
	
	@Modifying
	  @Query(value = "update GrantFdToAdTxnEntity set beStateAmt = :beStateAmt , beStatePercent = :beStatePercent , beCenterPercent = :beCenterPercent ,"
	  		+ " reStateAmt = :reStateAmt, reStatePercent = :reStatePercent , reCenterPercent = :reCenterPercent ,"
	  		+ " addnlAuthStateAmt = :addnlAuthStateAmt, addnlAuthCenterAmt = :addnlAuthCenterAmt, releasedStateAmt = :releasedStateAmt ,"
	  		+ " releasedStateAmtwithAddAuth = :releasedStateAmtwithAddAuth,availableGrant = :availableGrant,grantToBeRelease = :grantToBeRelease"
	  		+ " where grantId.grantId = :grantId and departmentId.departmentId = :departmentId")
	  public int updateByGrantIdAndDepartmentId(BigDecimal beStateAmt, BigDecimal beStatePercent, BigDecimal beCenterPercent,
			  BigDecimal reStateAmt,BigDecimal reStatePercent,BigDecimal reCenterPercent,BigDecimal addnlAuthStateAmt,
			  BigDecimal addnlAuthCenterAmt, BigDecimal releasedStateAmt, BigDecimal releasedStateAmtwithAddAuth,
			  BigDecimal availableGrant,BigDecimal grantToBeRelease,
			  Long grantId, Long departmentId);
	
	
	@Query(value = "SELECT distinct entity.releasedStateAmt FROM GrantFdToAdTxnEntity entity WHERE entity.departmentId.departmentId =?1")
	public BigDecimal getReleasedGrantFromFd(Long departmentId);
	
}
