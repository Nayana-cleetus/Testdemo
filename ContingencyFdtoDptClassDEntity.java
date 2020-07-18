package gov.ifms.grant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gov.ifms.common.util.Constant;
import gov.ifms.grant.util.GrantDbConstants;

@Entity
@Table(name = "TGRA_FD2AD_CNTF_CLASS_D", schema = Constant.GRANT_SCHEMA )
public class ContingencyFdtoDptClassDEntity implements Serializable{
	

	/** The serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRA_FD2AD_CNTF_CLASS_ID")
	private Long fdToAdCntfClassId;

	
	/** The grant hdr id. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = GrantDbConstants.TGRA_GRANT_HDR_ID,referencedColumnName=GrantDbConstants.TGRA_GRANT_HDR_ID)
	private GrantHdrEntity grantHeaderEntity;

	
	/** The grant hdr id. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRA_FD2AD_CNTF_SUBHEAD_ID" ,referencedColumnName="GRA_FD2AD_CNTF_SUBHEAD_ID")
	private ContingencyFdtoDptSubHeadDEntity subHeadEntity;
	
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;

	@Column(name = "CHARGED_OR_VOTED")
	private String chargedVoted;
	
	@Column(name = "GRANT_RELEASE_TYPE")
	private String grantReleseType;

	@Column(name = "ESTIMATE_TYPE")
	private String budgetEstimateType;

	@Column(name = "DEMAND_ID")
	private Long demandId;

	@Column(name = "DEMAND_CODE")
	private String demandCode;

	@Column(name = "MAJORHEAD_ID")
	private Long majorHeadId;

	@Column(name = "MAJORHEAD_CODE")
	private String majorHeadCode;

	@Column(name = "SUBMAJORHEAD_ID")
	private Long subMajorHeadId;
	
	@Column(name = "SUBMAJORHEAD_CODE")
	private String subMajorHeadCode;

	@Column(name = "MINORHEAD_ID")
	private Long minorHeadId;
	
	@Column(name = "MINORHEAD_CODE")
	private String minorHeadCode;
	
	
	@Column(name = "SUBHEAD_ID")
	private Long subHeadId;
	
	
	@Column(name = "SUBHEAD_CODE")
	private String subHeadCode;
	
	@Column(name = "DETAILHEAD_ID")
	private Long detailHeadId;
	
	@Column(name = "DETAILHEAD_CODE")
	private String detailHeadCode;
	
	@Column(name = "ITEM_ID")
	private Long itemId ;
	
	
	@Column(name = "ITEM_CODE")
	private String itemCode;
	
	
	@Column(name = "BE_STATE")
	private BigDecimal beStateAmt;
	
	@Column(name = "BE_CENTER")
	private BigDecimal beCenterAmt;
	
	
	@Column(name = "RE_STATE")
	private BigDecimal reStateAmt;
	@Column(name = "RE_CENTER")
	private BigDecimal reCenterAmt;
	
	@Column(name = "GOI_GRANT_RECEIVED_CFY")
	private BigDecimal receiptGoi;
	
	
	@Column(name = "GRANT_RELEASE_STATE_TILLDT")
	private BigDecimal releasedStateAmt;
	
	
	@Column(name = "GRANT_RELEASE_CENTER_TILLDT")
	private BigDecimal releasedCenterAmt;
	
	@Column(name = "GRANT_AMT_STATE")
	private BigDecimal grantToBeReleasedState;
	
	
	@Column(name = "GRANT_AMT_CENTER")
	private BigDecimal grantToBeReleasedCenter;
	
	
	@Column(name = "OBJECTCLASS_ID")
	private Long objectClassId;
	
	
	@Column(name = "OBJECTCLASS_CODE")
	private String objectClassCode;
	
	
	@Column(name = "CREATED_BY", updatable = false)
	private long createdBy;

	/** The created date. */
	@Column(name = "CREATED_DT", updatable = false)
	private Date createdDate;

	/** The created by post. */
	@Column(name = "CREATED_BY_POST", updatable = false)
	private long createdByPost;
	
	
	/** The modified by. */
	@Column(name = "MODIFIED_BY")
	private long modifiedBy;

	/** The modified date. */
	@Column(name = "MODIFIED_DT")
	private Date modifiedDate;

	/** The modified by post. */
	@Column(name = "MODIFIED_BY_POST")
	private long modifiedByPost;
	

@Column(name = "BUDGET_HEAD")
private String budgetHeadCode;


public Long getFdToAdCntfClassId() {
	return fdToAdCntfClassId;
}


public void setFdToAdCntfClassId(Long fdToAdCntfClassId) {
	this.fdToAdCntfClassId = fdToAdCntfClassId;
}


public GrantHdrEntity getGrantHeaderEntity() {
	return grantHeaderEntity;
}


public void setGrantHeaderEntity(GrantHdrEntity grantHeaderEntity) {
	this.grantHeaderEntity = grantHeaderEntity;
}


public ContingencyFdtoDptSubHeadDEntity getSubHeadEntity() {
	return subHeadEntity;
}


public void setSubHeadEntity(ContingencyFdtoDptSubHeadDEntity subHeadDEntity) {
	this.subHeadEntity = subHeadDEntity;
}


public Long getDepartmentId() {
	return departmentId;
}


public void setDepartmentId(Long departmentId) {
	this.departmentId = departmentId;
}


public String getChargedVoted() {
	return chargedVoted;
}


public void setChargedVoted(String chargedVoted) {
	this.chargedVoted = chargedVoted;
}


public String getGrantReleseType() {
	return grantReleseType;
}


public void setGrantReleseType(String grantReleseType) {
	this.grantReleseType = grantReleseType;
}


public String getBudgetEstimateType() {
	return budgetEstimateType;
}


public void setBudgetEstimateType(String budgetEstimateType) {
	this.budgetEstimateType = budgetEstimateType;
}


public Long getDemandId() {
	return demandId;
}


public void setDemandId(Long demandId) {
	this.demandId = demandId;
}


public String getDemandCode() {
	return demandCode;
}


public void setDemandCode(String demandCode) {
	this.demandCode = demandCode;
}


public Long getMajorHeadId() {
	return majorHeadId;
}


public void setMajorHeadId(Long majorHeadId) {
	this.majorHeadId = majorHeadId;
}


public String getMajorHeadCode() {
	return majorHeadCode;
}


public void setMajorHeadCode(String majorHeadCode) {
	this.majorHeadCode = majorHeadCode;
}


public Long getSubMajorHeadId() {
	return subMajorHeadId;
}


public void setSubMajorHeadId(Long subMajorHeadId) {
	this.subMajorHeadId = subMajorHeadId;
}


public String getSubMajorHeadCode() {
	return subMajorHeadCode;
}


public void setSubMajorHeadCode(String subMajorHeadCode) {
	this.subMajorHeadCode = subMajorHeadCode;
}


public Long getMinorHeadId() {
	return minorHeadId;
}


public void setMinorHeadId(Long minorHeadId) {
	this.minorHeadId = minorHeadId;
}


public String getMinorHeadCode() {
	return minorHeadCode;
}


public void setMinorHeadCode(String minorHeadCode) {
	this.minorHeadCode = minorHeadCode;
}


public Long getSubHeadId() {
	return subHeadId;
}


public void setSubHeadId(Long subHeadId) {
	this.subHeadId = subHeadId;
}


public String getSubHeadCode() {
	return subHeadCode;
}


public void setSubHeadCode(String subHeadCode) {
	this.subHeadCode = subHeadCode;
}


public Long getDetailHeadId() {
	return detailHeadId;
}


public void setDetailHeadId(Long detailHeadId) {
	this.detailHeadId = detailHeadId;
}


public String getDetailHeadCode() {
	return detailHeadCode;
}


public void setDetailHeadCode(String detailHeadCode) {
	this.detailHeadCode = detailHeadCode;
}


public Long getItemId() {
	return itemId;
}


public void setItemId(Long itemId) {
	this.itemId = itemId;
}


public String getItemCode() {
	return itemCode;
}


public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}


public BigDecimal getBeStateAmt() {
	return beStateAmt;
}


public void setBeStateAmt(BigDecimal beStateAmt) {
	this.beStateAmt = beStateAmt;
}


public BigDecimal getBeCenterAmt() {
	return beCenterAmt;
}


public void setBeCenterAmt(BigDecimal beCenterAmt) {
	this.beCenterAmt = beCenterAmt;
}


public BigDecimal getReStateAmt() {
	return reStateAmt;
}


public void setReStateAmt(BigDecimal reStateAmt) {
	this.reStateAmt = reStateAmt;
}


public BigDecimal getReCenterAmt() {
	return reCenterAmt;
}


public void setReCenterAmt(BigDecimal reCenterAmt) {
	this.reCenterAmt = reCenterAmt;
}


public BigDecimal getReceiptGoi() {
	return receiptGoi;
}


public void setReceiptGoi(BigDecimal receiptGoi) {
	this.receiptGoi = receiptGoi;
}


public BigDecimal getReleasedStateAmt() {
	return releasedStateAmt;
}


public void setReleasedStateAmt(BigDecimal releasedStateAmt) {
	this.releasedStateAmt = releasedStateAmt;
}


public BigDecimal getReleasedCenterAmt() {
	return releasedCenterAmt;
}


public void setReleasedCenterAmt(BigDecimal releasedCenterAmt) {
	this.releasedCenterAmt = releasedCenterAmt;
}


public BigDecimal getGrantToBeReleasedState() {
	return grantToBeReleasedState;
}


public void setGrantToBeReleasedState(BigDecimal grantToBeReleasedState) {
	this.grantToBeReleasedState = grantToBeReleasedState;
}


public BigDecimal getGrantToBeReleasedCenter() {
	return grantToBeReleasedCenter;
}


public void setGrantToBeReleasedCenter(BigDecimal grantToBeReleasedCenter) {
	this.grantToBeReleasedCenter = grantToBeReleasedCenter;
}


public Long getObjectClassId() {
	return objectClassId;
}


public void setObjectClassId(Long objectClassId) {
	this.objectClassId = objectClassId;
}


public String getObjectClassCode() {
	return objectClassCode;
}


public void setObjectClassCode(String objectClassCode) {
	this.objectClassCode = objectClassCode;
}


public long getCreatedBy() {
	return createdBy;
}


public void setCreatedBy(long createdBy) {
	this.createdBy = createdBy;
}


public Date getCreatedDate() {
	return createdDate;
}


public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}


public long getCreatedByPost() {
	return createdByPost;
}


public void setCreatedByPost(long createdByPost) {
	this.createdByPost = createdByPost;
}


public long getModifiedBy() {
	return modifiedBy;
}


public void setModifiedBy(long modifiedBy) {
	this.modifiedBy = modifiedBy;
}


public Date getModifiedDate() {
	return modifiedDate;
}


public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
}


public long getModifiedByPost() {
	return modifiedByPost;
}


public void setModifiedByPost(long modifiedByPost) {
	this.modifiedByPost = modifiedByPost;
}


public String getBudgetHeadCode() {
	return budgetHeadCode;
}


public void setBudgetHeadCode(String budgetHeadCode) {
	this.budgetHeadCode = budgetHeadCode;
}

}
