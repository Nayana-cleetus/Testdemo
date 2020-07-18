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

import gov.ifms.budget.entity.BudgetMsDemandEntity;
import gov.ifms.budget.entity.BudgetMsDetailHeadEntity;
import gov.ifms.budget.entity.BudgetMsItemEntity;
import gov.ifms.budget.entity.BudgetMsMajorHeadEntity;
import gov.ifms.budget.entity.BudgetMsMinorHeadEntity;
import gov.ifms.budget.entity.BudgetMsSubHeadEntity;
import gov.ifms.budget.entity.BudgetMsSubMajorHeadEntity;
import gov.ifms.edp.entity.BaseEntity;
import gov.ifms.edp.entity.EDPMsDepartmentEntity;
import gov.ifms.common.util.Constant;

@Entity
@Table(name = "TGRA_AD_SUBHEADWISE_D", schema = Constant.GRANT_SCHEMA)
public class GrantAdSubHeadWiseDEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRA_AD_SUBHEADWISE_ID")
	private Long budgetUtilSmryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GRANT_ID",referencedColumnName="GRANT_ID")
	private GrantHdrEntity grantHeaderEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FROM_DEPARTMENT_ID",referencedColumnName="DEPARTMENT_ID")
	private EDPMsDepartmentEntity fromDepartmentEntity;
	
	@Column(name = "CHARGED_OR_VOTED")
	private String chargedVoted;
	
	@Column(name = "GRANT_RELEASE_TYPE")
	private String grantReleaseType;
	
	@Column(name = "ESTIMATE_TYPE")
	private String estimateType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEMAND_ID",referencedColumnName="DEMAND_ID")
	private BudgetMsDemandEntity demandEntity;
	
	@Column(name = "DEMAND_CODE")
	private String demandCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAJORHEAD_ID",referencedColumnName="MAJORHEAD_ID")
	private BudgetMsMajorHeadEntity majorHeadEntity;
	
	@Column(name = "MAJORHEAD_CODE")
	private String majorHeadCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBMAJORHEAD_ID",referencedColumnName="SUBMAJORHEAD_ID")
	private BudgetMsSubMajorHeadEntity subMajorHeadEntity;
	
	@Column(name = "SUBMAJORHEAD_CODE")
	private String subMajorHeadCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MINORHEAD_ID",referencedColumnName="MINORHEAD_ID")
	private BudgetMsMinorHeadEntity minorHeadEntity;
	
	@Column(name = "MINORHEAD_CODE")
	private String minorHeadCode;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBHEAD_ID",referencedColumnName="SUBHEAD_ID")
	private BudgetMsSubHeadEntity subHeadEntity;
	
	@Column(name = "SUBHEAD_CODE")
	private String subHeadCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILHEAD_ID",referencedColumnName="DETAILHEAD_ID")
	private BudgetMsDetailHeadEntity detailHeadEntity;
	
	@Column(name = "DETAILHEAD_CODE")
	private String detailHeadCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID",referencedColumnName="ITEM_ID")
	private BudgetMsItemEntity itemEntity;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TO_DEPARTMENT_ID",referencedColumnName="DEPARTMENT_ID")
	private EDPMsDepartmentEntity toDepartmentEntity;
	
	
	@Column(name = "FROM_CO_NAME")
	private String fromCOName;
	
	@Column(name = "FROM_DDO_NAME")
	private String fromDDOName;
	
	@Column(name = "TO_CO_NAME")
	private String toCOName;
	
	@Column(name = "To_DDO_NAME")
	private String toDDOName;
	
	
	
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;

	@Column(name = "CREATED_DT", updatable = false)
	private Date createdDate;

	@Column(name = "CREATED_BY_POST", updatable = false)
	private String createdByPost;
	
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "MODIFIED_DT")
	private Date modifiedDate;

	@Column(name = "MODIFIED_BY_POST")
	private String modifiedByPost;

	public Long getBudgetUtilSmryId() {
		return budgetUtilSmryId;
	}

	public void setBudgetUtilSmryId(Long budgetUtilSmryId) {
		this.budgetUtilSmryId = budgetUtilSmryId;
	}

	public GrantHdrEntity getGrantHeaderEntity() {
		return grantHeaderEntity;
	}

	public void setGrantHeaderEntity(GrantHdrEntity grantHeaderEntity) {
		this.grantHeaderEntity = grantHeaderEntity;
	}

	public EDPMsDepartmentEntity getFromDepartmentEntity() {
		return fromDepartmentEntity;
	}

	public void setFromDepartmentEntity(EDPMsDepartmentEntity fromDepartmentEntity) {
		this.fromDepartmentEntity = fromDepartmentEntity;
	}

	public String getChargedVoted() {
		return chargedVoted;
	}

	public void setChargedVoted(String chargedVoted) {
		this.chargedVoted = chargedVoted;
	}

	public String getGrantReleaseType() {
		return grantReleaseType;
	}

	public void setGrantReleaseType(String grantReleaseType) {
		this.grantReleaseType = grantReleaseType;
	}

	public String getEstimateType() {
		return estimateType;
	}

	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}

	public BudgetMsDemandEntity getDemandEntity() {
		return demandEntity;
	}

	public void setDemandEntity(BudgetMsDemandEntity demandEntity) {
		this.demandEntity = demandEntity;
	}

	public String getDemandCode() {
		return demandCode;
	}

	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}

	public BudgetMsMajorHeadEntity getMajorHeadEntity() {
		return majorHeadEntity;
	}

	public void setMajorHeadEntity(BudgetMsMajorHeadEntity majorHeadEntity) {
		this.majorHeadEntity = majorHeadEntity;
	}

	public String getMajorHeadCode() {
		return majorHeadCode;
	}

	public void setMajorHeadCode(String majorHeadCode) {
		this.majorHeadCode = majorHeadCode;
	}

	public BudgetMsSubMajorHeadEntity getSubMajorHeadEntity() {
		return subMajorHeadEntity;
	}

	public void setSubMajorHeadEntity(BudgetMsSubMajorHeadEntity subMajorHeadEntity) {
		this.subMajorHeadEntity = subMajorHeadEntity;
	}

	public String getSubMajorHeadCode() {
		return subMajorHeadCode;
	}

	public void setSubMajorHeadCode(String subMajorHeadCode) {
		this.subMajorHeadCode = subMajorHeadCode;
	}

	public BudgetMsMinorHeadEntity getMinorHeadEntity() {
		return minorHeadEntity;
	}

	public void setMinorHeadEntity(BudgetMsMinorHeadEntity minorHeadEntity) {
		this.minorHeadEntity = minorHeadEntity;
	}

	public String getMinorHeadCode() {
		return minorHeadCode;
	}

	public void setMinorHeadCode(String minorHeadCode) {
		this.minorHeadCode = minorHeadCode;
	}

	public BudgetMsSubHeadEntity getSubHeadEntity() {
		return subHeadEntity;
	}

	public void setSubHeadEntity(BudgetMsSubHeadEntity subHeadEntity) {
		this.subHeadEntity = subHeadEntity;
	}

	public String getSubHeadCode() {
		return subHeadCode;
	}

	public void setSubHeadCode(String subHeadCode) {
		this.subHeadCode = subHeadCode;
	}

	public BudgetMsDetailHeadEntity getDetailHeadEntity() {
		return detailHeadEntity;
	}

	public void setDetailHeadEntity(BudgetMsDetailHeadEntity detailHeadEntity) {
		this.detailHeadEntity = detailHeadEntity;
	}

	public String getDetailHeadCode() {
		return detailHeadCode;
	}

	public void setDetailHeadCode(String detailHeadCode) {
		this.detailHeadCode = detailHeadCode;
	}

	public BudgetMsItemEntity getItemEntity() {
		return itemEntity;
	}

	public void setItemEntity(BudgetMsItemEntity itemEntity) {
		this.itemEntity = itemEntity;
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

	public EDPMsDepartmentEntity getToDepartmentEntity() {
		return toDepartmentEntity;
	}

	public void setToDepartmentEntity(EDPMsDepartmentEntity toDepartmentEntity) {
		this.toDepartmentEntity = toDepartmentEntity;
	}

	public String getFromCOName() {
		return fromCOName;
	}

	public void setFromCOName(String fromCOName) {
		this.fromCOName = fromCOName;
	}

	public String getFromDDOName() {
		return fromDDOName;
	}

	public void setFromDDOName(String fromDDOName) {
		this.fromDDOName = fromDDOName;
	}

	public String getToCOName() {
		return toCOName;
	}

	public void setToCOName(String toCOName) {
		this.toCOName = toCOName;
	}

	public String getToDDOName() {
		return toDDOName;
	}

	public void setToDDOName(String toDDOName) {
		this.toDDOName = toDDOName;
	}
	
	
	
}
	
	
	
	
	
	
	
