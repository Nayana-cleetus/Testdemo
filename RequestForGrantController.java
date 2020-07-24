package gov.ifms.grant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.ifms.common.constant.MsgConstant;
import gov.ifms.common.constant.URLConstant;
import gov.ifms.common.dto.ApiResponse;
import gov.ifms.common.exception.CustomException;
import gov.ifms.common.logging.annotation.Trace;
import gov.ifms.common.util.ResponseUtil;
import gov.ifms.grant.dto.RequestForGrantInputDto;
import gov.ifms.grant.service.RequestForGrantSrvice;

@RestController
@RequestMapping(value=URLConstant.URL_REQUEST_FOR_GRANT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestForGrantController {
	
	@Autowired
	RequestForGrantSrvice requestForGrant;
	
	@Trace
	@PostMapping(value = "getRequestForSubGrant")
	public ResponseEntity<ApiResponse> getRequestForGrantDetails(@RequestBody RequestForGrantInputDto inputDto) throws CustomException{
		System.out.println("Inside Controller...");
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH,requestForGrant.getRequestForGrantDetails(inputDto));
		
	}

}
