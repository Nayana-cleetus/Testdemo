package gov.ifms.grant.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SurrenderGrantOutputDto {

private String budgetHeadCode;
private String schemeType;
private String  itemName;
private List<String> surrenderToDept;
private String stateFlag;
private List<GrantBudgetHeadWiseUtilSmryDto> itemList;



public List<String> getSurrenderToDept() {
	return surrenderToDept;
}

public void setSurrenderToDept(List<String> surrenderToDept) {
	this.surrenderToDept = surrenderToDept;
}

public String getBudgetHeadCode() {
return budgetHeadCode;
}

public void setBudgetHeadCode(String budgetHeadCode) {
this.budgetHeadCode = budgetHeadCode;
}

public List<GrantBudgetHeadWiseUtilSmryDto> getItemList() {
return itemList;
}

public void setItemList(List<GrantBudgetHeadWiseUtilSmryDto> itemList) {
this.itemList = itemList;
}

public String getSchemeType() {
return schemeType;
}

public void setSchemeType(String schemeType) {
this.schemeType = schemeType;
}

public String getItemName() {
return itemName;
}

public void setItemName(String itemName) {
this.itemName = itemName;
}

public String getStateFlag() {
	return stateFlag;
}

public void setStateFlag(String stateFlag) {
	this.stateFlag = stateFlag;
}


}
