package gov.ifms.grant.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

public class GrantBudgetHeadWiseUtilSmryDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long budgetUtilSmryId;

	
	private String grantType;

	
	private String finYr;

	
	private String rcFlag;

	
	private BigDecimal beStateAmt;

	
	private BigDecimal beCenterAmt;

	private BigDecimal beStatePercent;

	
	private BigDecimal beCenterPercent;

	
	private BigDecimal reStatePercent;

	
	private BigDecimal reCenterPercent;

	
	private BigDecimal reStateAmt;


	private BigDecimal reCenterAmt;

	
	private BigDecimal releasedStateAmt;

	
	private BigDecimal releasedCenterAmt;

	
	private BigDecimal remainingStateAmt;

	private BigDecimal remainingCenterAmt;

	
	private BigDecimal receiptGoi;

	
	private BigDecimal addnlAuthStateAmt;

	
	private BigDecimal addnlAuthCenterAmt;

	
	private Long departmentId;


	private Long divisionId;

	
	private Long demandId;


	private Long majorHeadId;

	
	private Long subMajorHeadId;

	
	private Long minorHeadId;

	
	private Long subHeadId;

	
	private Long detailHeadId;

	
	private Long itemId;

	
	private Long objectClassId;

	
	private String chargedVoted;


	private String coName;

	
	private Long coId;

	
	private String ddoName;

	
	private Long ddoId;

	
	
	private String budgetHeadCode;
	
	private String objectClassName;
	
	private String objectClassCode;

	private BigDecimal grantToBeReleasedState;

	private BigDecimal grantToBeReleasedCenter;
	
    private String departmentName;
    
    private String grantReleseType;
	
    private String demandCode;
    
    private String budgetEstimateType;
    
    @Value("7900")
    private BigDecimal supplementaryAmt;
    
    private BigDecimal expenditureAsonDtAmt;
    private BigDecimal sanctionGrantAmt;
    private BigDecimal probableExpenditureAmt;
   // private BigDecimal availableGrantAmt;
    private BigDecimal surrenderAmt;
    private BigDecimal finalGrantAfterSurrender;
    private String schemeType;
    private String surrenderReason;
    private Map surrenderDeptTo;
    
	
	public BigDecimal getSupplementaryAmt() {
		return supplementaryAmt;
	}


	public void setSupplementaryAmt(BigDecimal supplementaryAmt) {
		this.supplementaryAmt = supplementaryAmt;
	}


	public BigDecimal getExpenditureAsonDtAmt() {
		return expenditureAsonDtAmt;
	}


	public void setExpenditureAsonDtAmt(BigDecimal expenditureAsonDtAmt) {
		this.expenditureAsonDtAmt = expenditureAsonDtAmt;
	}


	public BigDecimal getSanctionGrantAmt() {
		return sanctionGrantAmt;
	}


	public void setSanctionGrantAmt(BigDecimal sanctionGrantAmt) {
		this.sanctionGrantAmt = sanctionGrantAmt;
	}


	public BigDecimal getProbableExpenditureAmt() {
		return probableExpenditureAmt;
	}


	public void setProbableExpenditureAmt(BigDecimal probableExpenditureAmt) {
		this.probableExpenditureAmt = probableExpenditureAmt;
	}


	public BigDecimal getSurrenderAmt() {
		return surrenderAmt;
	}


	public void setSurrenderAmt(BigDecimal surrenderAmt) {
		this.surrenderAmt = surrenderAmt;
	}


	public BigDecimal getFinalGrantAfterSurrender() {
		return finalGrantAfterSurrender;
	}


	public void setFinalGrantAfterSurrender(BigDecimal finalGrantAfterSurrender) {
		this.finalGrantAfterSurrender = finalGrantAfterSurrender;
	}


	public String getSchemeType() {
		return schemeType;
	}


	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}


	public String getSurrenderReason() {
		return surrenderReason;
	}


	public void setSurrenderReason(String surrenderReason) {
		this.surrenderReason = surrenderReason;
	}

	public Map getSurrenderDeptTo() {
		return surrenderDeptTo;
	}


	public void setSurrenderDeptTo(Map surrenderDeptTo) {
		this.surrenderDeptTo = surrenderDeptTo;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public String getGrantReleseType() {
		return grantReleseType;
	}


	public void setGrantReleseType(String grantReleseType) {
		this.grantReleseType = grantReleseType;
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


	public Long getBudgetUtilSmryId() {
		return budgetUtilSmryId;
	}


	public void setBudgetUtilSmryId(Long budgetUtilSmryId) {
		this.budgetUtilSmryId = budgetUtilSmryId;
	}


	public String getGrantType() {
		return grantType;
	}


	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}


	public String getFinYr() {
		return finYr;
	}


	public void setFinYr(String finYr) {
		this.finYr = finYr;
	}


	public String getRcFlag() {
		return rcFlag;
	}


	public void setRcFlag(String rcFlag) {
		this.rcFlag = rcFlag;
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


	public BigDecimal getBeStatePercent() {
		return beStatePercent;
	}


	public void setBeStatePercent(BigDecimal beStatePercent) {
		this.beStatePercent = beStatePercent;
	}


	public BigDecimal getBeCenterPercent() {
		return beCenterPercent;
	}


	public void setBeCenterPercent(BigDecimal beCenterPercent) {
		this.beCenterPercent = beCenterPercent;
	}


	public BigDecimal getReStatePercent() {
		return reStatePercent;
	}


	public void setReStatePercent(BigDecimal reStatePercent) {
		this.reStatePercent = reStatePercent;
	}


	public BigDecimal getReCenterPercent() {
		return reCenterPercent;
	}


	public void setReCenterPercent(BigDecimal reCenterPercent) {
		this.reCenterPercent = reCenterPercent;
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


	public BigDecimal getRemainingStateAmt() {
		return remainingStateAmt;
	}


	public void setRemainingStateAmt(BigDecimal remainingStateAmt) {
		this.remainingStateAmt = remainingStateAmt;
	}


	public BigDecimal getRemainingCenterAmt() {
		return remainingCenterAmt;
	}


	public void setRemainingCenterAmt(BigDecimal remainingCenterAmt) {
		this.remainingCenterAmt = remainingCenterAmt;
	}


	public BigDecimal getReceiptGoi() {
		return receiptGoi;
	}


	public void setReceiptGoi(BigDecimal receiptGoi) {
		this.receiptGoi = receiptGoi;
	}


	public BigDecimal getAddnlAuthStateAmt() {
		return addnlAuthStateAmt;
	}


	public void setAddnlAuthStateAmt(BigDecimal addnlAuthStateAmt) {
		this.addnlAuthStateAmt = addnlAuthStateAmt;
	}


	public BigDecimal getAddnlAuthCenterAmt() {
		return addnlAuthCenterAmt;
	}


	public void setAddnlAuthCenterAmt(BigDecimal addnlAuthCenterAmt) {
		this.addnlAuthCenterAmt = addnlAuthCenterAmt;
	}


	public Long getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}


	public Long getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}


	public Long getDemandId() {
		return demandId;
	}


	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}


	public Long getMajorHeadId() {
		return majorHeadId;
	}


	public void setMajorHeadId(Long majorHeadId) {
		this.majorHeadId = majorHeadId;
	}


	public Long getSubMajorHeadId() {
		return subMajorHeadId;
	}


	public void setSubMajorHeadId(Long subMajorHeadId) {
		this.subMajorHeadId = subMajorHeadId;
	}


	public Long getMinorHeadId() {
		return minorHeadId;
	}


	public void setMinorHeadId(Long minorHeadId) {
		this.minorHeadId = minorHeadId;
	}


	public Long getSubHeadId() {
		return subHeadId;
	}


	public void setSubHeadId(Long subHeadId) {
		this.subHeadId = subHeadId;
	}


	public Long getDetailHeadId() {
		return detailHeadId;
	}


	public void setDetailHeadId(Long detailHeadId) {
		this.detailHeadId = detailHeadId;
	}


	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Long getObjectClassId() {
		return objectClassId;
	}


	public void setObjectClassId(Long objectClassId) {
		this.objectClassId = objectClassId;
	}


	public String getChargedVoted() {
		return chargedVoted;
	}


	public void setChargedVoted(String chargedVoted) {
		this.chargedVoted = chargedVoted;
	}


	public String getCoName() {
		return coName;
	}


	public void setCoName(String coName) {
		this.coName = coName;
	}


	public Long getCoId() {
		return coId;
	}


	public void setCoId(Long coId) {
		this.coId = coId;
	}


	public String getDdoName() {
		return ddoName;
	}


	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}


	public Long getDdoId() {
		return ddoId;
	}


	public void setDdoId(Long ddoId) {
		this.ddoId = ddoId;
	}

	public String getBudgetHeadCode() {
		return budgetHeadCode;
	}


	public void setBudgetHeadCode(String budgetHeadCode) {
		this.budgetHeadCode = budgetHeadCode;
	}


	public String getObjectClassName() {
		return objectClassName;
	}


	public void setObjectClassName(String objectClassName) {
		this.objectClassName = objectClassName;
	}


	public String getDemandCode() {
		return demandCode;
	}


	public void setDemandCode(String demandCode) {
		this.demandCode = demandCode;
	}


	public String getBudgetEstimateType() {
		return budgetEstimateType;
	}


	public void setBudgetEstimateType(String budgetEstimateType) {
		this.budgetEstimateType = budgetEstimateType;
	}


	public String getObjectClassCode() {
		return objectClassCode;
	}


	public void setObjectClassCode(String objectClassCode) {
		this.objectClassCode = objectClassCode;
	}


	public GrantBudgetHeadWiseUtilSmryDto() {
		super();
	}
	
	
	 public GrantBudgetHeadWiseUtilSmryDto(BigDecimal beStatePercent,BigDecimal beCenterPercent) {
		 
		      super();
		
		       this.beStatePercent=beStatePercent;
		
		      this.beCenterPercent=beCenterPercent;
		
		   }
	
}
