package gov.ifms.grant.service;

import java.util.List;

import gov.ifms.common.exception.CustomException;
import gov.ifms.grant.dto.RequestForGrantInputDto;
import gov.ifms.grant.dto.RequestForGrantOutputDto;

public interface RequestForGrantSrvice {
	
	List<RequestForGrantOutputDto> getRequestForGrantDetails(RequestForGrantInputDto inputDto) throws CustomException;

}
