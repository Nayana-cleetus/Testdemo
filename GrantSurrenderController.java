package gov.ifms.grant.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.ifms.common.base.PagebleDTO;
import gov.ifms.common.constant.MsgConstant;
import gov.ifms.common.constant.URLConstant;
import gov.ifms.common.dto.ApiResponse;
import gov.ifms.common.exception.CustomException;
import gov.ifms.common.logging.annotation.Trace;
import gov.ifms.common.pagination.PageDetails;
import gov.ifms.common.util.MessageUtil;
import gov.ifms.common.util.ResponseUtil;
import gov.ifms.grant.dto.GrantSurrenderHdrDto;
import gov.ifms.grant.dto.GrantSurrenderSaveRequestDto;
import gov.ifms.grant.dto.SurrenderDto;
import gov.ifms.grant.dto.SurrenderListDto;
import gov.ifms.grant.service.GrantSurrenderService;

@RestController

@RequestMapping(value = URLConstant.URL_GRANT_SURRENDER)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrantSurrenderController {

	/** The logger. */
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());

	/** The service. */
	@Autowired
	private GrantSurrenderService service;

	@Trace
	@PostMapping(value = "getSurrenderList")
	public ResponseEntity<ApiResponse> getSearchList(@RequestBody PageDetails pageDetails) throws CustomException {

		PagebleDTO<SurrenderListDto> surrenderSearchList = service.getSurrnederSearchList(pageDetails);
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.IFMS_MSG_FETCH_LIST, surrenderSearchList);

	}

	@Trace
	@PostMapping(value = "getFinYearList")
	public ResponseEntity<ApiResponse> getFinancialYearList() throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MessageUtil.getMsg("GRANT.FETCH"),
				service.getFinancialYearList());
	}

	@Trace
	@PostMapping(value = "getData/{id}")
	public ResponseEntity<ApiResponse> getData(@PathVariable Long id) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MessageUtil.getMsg("GRANT.FETCH"), service.getData(id));
	}

	@Trace
	@PostMapping(value = "saveasdraft")
	public ResponseEntity<ApiResponse> Saveasdraft(@Valid @RequestBody GrantSurrenderSaveRequestDto dto)
			throws CustomException {
		log.info("inside controller");

		log.info("categ :" + dto.getGrantSurrenderHdrDto().getRevenueCapital() + "fin yr "
				+ dto.getGrantSurrenderHdrDto().getFinYear());
		final GrantSurrenderHdrDto savedHdrDto = service.saveAsDraftGrantSurrender(dto);

		return ResponseUtil.getResponse(HttpStatus.OK, "Grant data saved sucessfully", savedHdrDto);

	}

	@Trace
	@PostMapping(value = "submit")
	public ResponseEntity<ApiResponse> SubmitSurrederGrant(@Valid @RequestBody GrantSurrenderSaveRequestDto dto)
			throws CustomException {
		log.info("inside controller");
		log.info("categ :" + dto.getGrantSurrenderHdrDto().getRevenueCapital() + "fin yr "
				+ dto.getGrantSurrenderHdrDto().getFinYear());
		final GrantSurrenderHdrDto savedHdrDto = service.submitGrantSurrender(dto);
		return ResponseUtil.getResponse(HttpStatus.OK, "Grant data submitted sucessfully", savedHdrDto);

	}

	/**
	 * @use This Method Used for Get Demand List from master
	 * @return
	 */
	/*
	 * @Trace
	 * 
	 * @PostMapping(value = "getAllDemand") public ResponseEntity<ApiResponse>
	 * getAllDemand() throws CustomException { return
	 * ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH,
	 * service.getAllDemandList()); }
	 */

	@Trace
	@PostMapping(value = "getDemandList")
	public ResponseEntity<ApiResponse> getDemandList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getDemandList(dto));
	}

	/*
	 * @Trace
	 * 
	 * @PostMapping(value = "getAllDemandList") public ResponseEntity<ApiResponse>
	 * getAllDemandList() throws CustomException { return
	 * ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH,
	 * service.getAllDemandList()); }
	 */

	/**
	 * @use This Method Used for Get Major Head List for selected Demand
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getMajorHeadList")
	public ResponseEntity<ApiResponse> getMajorHeadList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getMajorHeadList(dto));
	}

	/**
	 * @use This Method Used for Get Sub Major Head List for selected Major head
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getSubMajorHeadList")
	public ResponseEntity<ApiResponse> getSubMajorHeadList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getSubMajorHeadList(dto));
	}

	/**
	 * @use This Method Used for Get Sub Major Head List for selected Major head
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getMinorHeadList")
	public ResponseEntity<ApiResponse> getMinorHeadList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getMinorHeadList(dto));
	}

	/**
	 * @use This Method Used for Get Sub Head List for selected Major head
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getSubHeadList")
	public ResponseEntity<ApiResponse> getSubHeadList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getSubHeadList(dto));
	}

	/**
	 * @use This Method Used for Get Detail Head List for selected Sub Head
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getDetailHeadList")
	public ResponseEntity<ApiResponse> getDetailHeadList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getDetailHeadList(dto));
	}
	
	/**
	 * @use This Method Used for Get Estimate type List for selected BudgetHead
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getBudgetEstimate")
	public ResponseEntity<ApiResponse> getBudgetEstimateList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getBudgetEstimateList(dto));
	}
	
	@Trace
	@PostMapping(value = "getSchemeType")
	public ResponseEntity<ApiResponse> getSchemeType(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getSchemeType(dto));
	}
	
	/**
	 * @use This Method Used for Get Item List for selected BudgetHead and Budget Estimate
	 * @param dto
	 * @return
	 */
	@Trace
	@PostMapping(value = "getItemList")
	public ResponseEntity<ApiResponse> getItemList(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getItemList(dto));
	}
	
	@Trace
	@PostMapping(value = "getAvailableGrantState")
	public ResponseEntity<ApiResponse> getAvailableGrantState(@RequestBody SurrenderDto dto) throws CustomException {
		return ResponseUtil.getResponse(HttpStatus.OK, MsgConstant.GRANT_MSG_FETCH, service.getAvailableGrantState(dto));
	}
	
	@Trace
	@PostMapping(value="deleteData/{id}")
	public ResponseEntity<ApiResponse> deleteData(@PathVariable Long id) throws CustomException{
		service.deleteData(id);
		return ResponseUtil.getResponse(HttpStatus.OK,"Deleted Successfully"); 
	}

}
