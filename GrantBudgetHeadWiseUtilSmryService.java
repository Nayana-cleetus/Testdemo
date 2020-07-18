package gov.ifms.grant.service;

import java.util.List;
import java.util.Map;

import gov.ifms.common.exception.CustomException;
import gov.ifms.grant.dto.FdToCssDto;
import gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto;
import gov.ifms.grant.dto.SurrenderDto;
import gov.ifms.grant.dto.SurrenderGrantOutputDto;

public interface GrantBudgetHeadWiseUtilSmryService {
	 List<GrantBudgetHeadWiseUtilSmryDto> getSubHeadWiseDetails(FdToCssDto grantDto) throws CustomException;
	List<GrantBudgetHeadWiseUtilSmryDto> getItemClassWiseDetails(FdToCssDto grantDto) throws CustomException;
	 
	 List<GrantBudgetHeadWiseUtilSmryDto> getSubHeadWiseDetailscontingency(FdToCssDto grantDto) throws CustomException;
	 
	 List<Map<String, List<GrantBudgetHeadWiseUtilSmryDto>>> getItemClassWiseGroupByHead(FdToCssDto grantDto) throws CustomException ;
	
	List<SurrenderGrantOutputDto> getSurrenderSubHeadWiseDetails(SurrenderDto grantDto) throws CustomException;
	
}
