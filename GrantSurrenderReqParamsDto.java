package gov.ifms.grant.dto;

public class GrantSurrenderReqParamsDto {
	
	private Long id;
	private String name;
	private String code;
	private String rc;
	
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
