package gov.ifms.grant.dto;

import java.math.BigDecimal;
import java.util.List;

public class SurrenderDto {

	
private static final long serialVersionUID = 1L;
	
	private Long officeId;
	
	private Long demandId;
    
    private Long majorHeadId;
    
    private Long subMajorHeadId;
    
    private Long minorHeadId;
    
    private Long subHeadId;
    
    private Long detailHeadId;
    
    private String rc;
    
    private String officeType;
    
    private int isCo;
    
    private String estimateType;
    
    private String budgetHeadCode;
    
    private String schemeType;
    
    private List<Long> itemId;

    private String finYear;
	
    private Long id;
    
//    private Long departmentId;

	private Long deptId;
    private String chargedVotedFlag;
    
    private String departmentName;
    
    private String grantReleseType;
    
    private String initialHeadCode;
  
    private BigDecimal availableGrantAmt;
    
    private List<Integer> roleId;
    
    private String stateFlag;
    
	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
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

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public int getIsCo() {
		return isCo;
	}

	public void setIsCo(int isCo) {
		this.isCo = isCo;
	}

	public String getEstimateType() {
		return estimateType;
	}

	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}

	public String getBudgetHeadCode() {
		return budgetHeadCode;
	}

	public void setBudgetHeadCode(String budgetHeadCode) {
		this.budgetHeadCode = budgetHeadCode;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public List<Long> getItemId() {
		return itemId;
	}

	public void setItemId(List<Long> itemId) {
		this.itemId = itemId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getChargedVotedFlag() {
		return chargedVotedFlag;
	}

	public void setChargedVotedFlag(String chargedVotedFlag) {
		this.chargedVotedFlag = chargedVotedFlag;
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

	public String getInitialHeadCode() {
		return initialHeadCode;
	}

	public void setInitialHeadCode(String initialHeadCode) {
		this.initialHeadCode = initialHeadCode;
	}

	public BigDecimal getAvailableGrantAmt() {
		return availableGrantAmt;
	}

	public void setAvailableGrantAmt(BigDecimal availableGrantAmt) {
		this.availableGrantAmt = availableGrantAmt;
	}

	public List<Integer> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	

	public String getStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}

	@Override
	public String toString() {
		return "SurrenderDto [officeId=" + officeId + ", demandId=" + demandId + ", majorHeadId=" + majorHeadId
				+ ", subMajorHeadId=" + subMajorHeadId + ", minorHeadId=" + minorHeadId + ", subHeadId=" + subHeadId
				+ ", detailHeadId=" + detailHeadId + ", rc=" + rc + ", officeType=" + officeType + ", isCo="
				+ isCo + ", estimateType=" + estimateType + ", budgetHeadCode=" + budgetHeadCode
				+ ", schemeType=" + schemeType + ", itemId=" + itemId + ", finYear=" + finYear + ", id=" + id
				+ ", deptId=" + deptId + ", chargedVotedFlag=" + chargedVotedFlag + ", departmentName=" + departmentName
				+ ", grantReleseType=" + grantReleseType + ", initialHeadCode=" + initialHeadCode
				+ ", availableGrantAmt=" + availableGrantAmt + ", roleId=" + roleId + "]";
	}

		
	
	
}
