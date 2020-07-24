package gov.ifms.grant.dto;

import java.io.Serializable;
import java.util.List;

public class RequestForGrantInputDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String finYear;
	private String requestType;
	private Long demandId;
    private Long majorHeadId;
    private String rcFlag;
    private Long subMajorHeadId;
    private Long minorHeadId;
    private Long subHeadId;
    private Long detailHeadId;
    private String chargedVotedFlag;
    private String budgetEstimateType;
    private String schemeType;
    private Long itemId;
    private Long requestOrderNo;
    private Long departmentId;
    private String grantReleseType;
    private String budgetHeadCode;
    private String initialHeadCode;
    
    
    private String Stateflag;
    private String officeType;
    private String roleId;
    private String userName;
    
  
	public String getGrantReleseType() {
		return grantReleseType;
	}
	public void setGrantReleseType(String grantReleseType) {
		this.grantReleseType = grantReleseType;
	}
	public String getBudgetHeadCode() {
		return budgetHeadCode;
	}
	public void setBudgetHeadCode(String budgetHeadCode) {
		this.budgetHeadCode = budgetHeadCode;
	}
	public String getInitialHeadCode() {
		return initialHeadCode;
	}
	public void setInitialHeadCode(String initialHeadCode) {
		this.initialHeadCode = initialHeadCode;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
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
	public String getRcFlag() {
		return rcFlag;
	}
	public void setRcFlag(String rcFlag) {
		this.rcFlag = rcFlag;
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
	public String getChargedVotedFlag() {
		return chargedVotedFlag;
	}
	public void setChargedVotedFlag(String chargedVotedFlag) {
		this.chargedVotedFlag = chargedVotedFlag;
	}
	public String getBudgetEstimateType() {
		return budgetEstimateType;
	}
	public void setBudgetEstimateType(String budgetEstimateType) {
		this.budgetEstimateType = budgetEstimateType;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getRequestOrderNo() {
		return requestOrderNo;
	}
	public void setRequestOrderNo(Long requestOrderNo) {
		this.requestOrderNo = requestOrderNo;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getStateflag() {
		return Stateflag;
	}
	public void setStateflag(String stateflag) {
		Stateflag = stateflag;
	}
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
    
	 
	

}
