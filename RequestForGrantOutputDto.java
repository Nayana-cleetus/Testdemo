package gov.ifms.grant.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestForGrantOutputDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String budgetHeadCode;
	private String schemeType;
	private String  itemWorkName;
	private String Stateflag;
	private List<GrantBudgetHeadWiseUtilSmryDto> itemList;
	
	
	

	public String getStateFlag() {
		return Stateflag;
	}

	public void setStateFlag(String stateFlag) {
		this.Stateflag = stateFlag;
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

	public String getItemWorkName() {
		return itemWorkName;
	}

	public void setItemWorkName(String itemWorkName) {
		this.itemWorkName = itemWorkName;
	}

	public List<GrantBudgetHeadWiseUtilSmryDto> getItemList() {
		return itemList;
	}

	public void setItemList(List<GrantBudgetHeadWiseUtilSmryDto> itemList) {
		this.itemList = itemList;
	}
	
	

}
