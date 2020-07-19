package gov.ifms.grant.dto;

import java.math.BigDecimal;

public class GrantSurrenderReqParamsDto {
	
	private Long id;
	private String name;
	private String code;
	private String rc;
	
	private String deptOrOfficer;
	private BigDecimal grantAmount;
	
	 

	public String getDeptOrOfficer() {
		return deptOrOfficer;
	}

	public void setDeptOrOfficer(String deptOrOfficer) {
		this.deptOrOfficer = deptOrOfficer;
	}

	public BigDecimal getGrantAmount() {
		return grantAmount;
	}

	public void setGrantAmount(BigDecimal grantAmount) {
		this.grantAmount = grantAmount;
	}

	public GrantSurrenderReqParamsDto(String deptOrOfficer, BigDecimal grantAmount) {
	super();
	this.deptOrOfficer = deptOrOfficer;
	this.grantAmount = grantAmount;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRc() {
		return rc;
	}
	public void setRc(String rc) {
		this.rc = rc;
	}
	
	public GrantSurrenderReqParamsDto(Long id, String name, String code,String rc) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.rc=rc;
	}

}
