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
import gov.ifms.grant.dto.FdToCssDto;
import gov.ifms.grant.dto.SurrenderDto;
import gov.ifms.grant.service.GrantBudgetHeadWiseUtilSmryService;

@RestController
@RequestMapping(value=URLConstant.URL_GRANT_BUDGET_HEADWISE_SMRY)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrantBudgetHeadWiseUtilSmryController {

	
	@Autowired
	GrantBudgetHeadWiseUtilSmryService grantBudgetHeadWiseUtilSmryService;
	
	
	
	/**
	 * @use This Method Used to get Sub Head wise Details
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getSubHead")
	public ResponseEntity<ApiResponse> getSubHeadWiseDetails(@RequestBody FdToCssDto dto) throws CustomException{
		
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH,grantBudgetHeadWiseUtilSmryService.getSubHeadWiseDetails(dto));
		
	}

	
	/**
	 * @use This Method Used to get Item Class Wise Details
	 * @param dto
	 * @return
	 */
	
	  @Trace
	  @PostMapping(value = "getItemClass") 
	  public ResponseEntity<ApiResponse> getListClasswiseDetails(@RequestBody FdToCssDto dto) throws CustomException{
	 return ResponseUtil.getResponse(HttpStatus.OK,
			 MsgConstant.GRANT_MSG_FETCH,grantBudgetHeadWiseUtilSmryService.getItemClassWiseDetails(dto));
	  
	 }
	 
	  

		/**
		 * @use This Method Used to get Sub Head wise Details
		 * @param dto
		 * @return
		 */
		@Trace
		@PostMapping(value = "getSubHeaddata")
		public ResponseEntity<ApiResponse> getSubHeadWiseDetailscontingency(@RequestBody FdToCssDto dto) throws CustomException{
			
			return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH,grantBudgetHeadWiseUtilSmryService.getSubHeadWiseDetailscontingency(dto));
			
		}
	
		@Trace
		@PostMapping(value = "getSurrenderSubHead")
		public ResponseEntity<ApiResponse> getSurrenderSubHeadWiseDetails(@RequestBody SurrenderDto dto) throws CustomException{
			
			return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH,grantBudgetHeadWiseUtilSmryService.getSurrenderSubHeadWiseDetails(dto));
			
		}
	
}
