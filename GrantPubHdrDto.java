package gov.ifms.grant.dto;

import java.io.Serializable;

public class GrantPubHdrDto implements Serializable{

/**
	 * 
	 */
private static final long serialVersionUID = 1L;

private Long pubHdrId;
	
private Long  demandId;
	
private Long departmentId;
	
private Long detailHeadId;
	
private String isChargedVoted;
	
private String isRevenueCapital;
	
private Long majorHeadId;
	
private Long minorHeadId;
	
private Long subHeadId;
	
private Long subMajorHeadId;
	
private String budgetEstimateType;


public Long getPubHdrId() {
	return pubHdrId;
}

public void setPubHdrId(Long pubHdrId) {
	this.pubHdrId = pubHdrId;
}

public Long getDemandId() {
	return demandId;
}

public void setDemandId(Long demandId) {
	this.demandId = demandId;
}

public Long getDepartmentId() {
	return departmentId;
}

public void setDepartmentId(Long departmentId) {
	this.departmentId = departmentId;
}

public Long getDetailHeadId() {
	return detailHeadId;
}

public void setDetailHeadId(Long detailHeadId) {
	this.detailHeadId = detailHeadId;
}

public String getIsChargedVoted() {
	return isChargedVoted;
}

public void setIsChargedVoted(String isChargedVoted) {
	this.isChargedVoted = isChargedVoted;
}

public String getIsRevenueCapital() {
	return isRevenueCapital;
}

public void setIsRevenueCapital(String isRevenueCapital) {
	this.isRevenueCapital = isRevenueCapital;
}

public Long getMajorHeadId() {
	return majorHeadId;
}

public void setMajorHeadId(Long majorHeadId) {
	this.majorHeadId = majorHeadId;
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

public Long getSubMajorHeadId() {
	return subMajorHeadId;
}

public void setSubMajorHeadId(Long subMajorHeadId) {
	this.subMajorHeadId = subMajorHeadId;
}

public String getBudgetEstimateType() {
	return budgetEstimateType;
}

public void setBudgetEstimateType(String budgetEstimateType) {
	this.budgetEstimateType = budgetEstimateType;
}

public GrantPubHdrDto() {
	super();
}

public GrantPubHdrDto( String budgetEstimateType) {
	super();
	this.budgetEstimateType = budgetEstimateType;
}


}
