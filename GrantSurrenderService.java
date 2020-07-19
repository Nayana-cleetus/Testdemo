package gov.ifms.grant.service;

import java.math.BigDecimal;
import java.util.List;

import gov.ifms.budget.dto.BudgetMsDemandDto;
import gov.ifms.budget.dto.BudgetMsDetailHeadDto;
import gov.ifms.budget.dto.BudgetMsItemDto;
import gov.ifms.budget.dto.BudgetMsMinorHeadDto;
import gov.ifms.budget.dto.BudgetMsSubHeadDto;
import gov.ifms.budget.dto.BudgetMsSubMajorHeadDto;
import gov.ifms.common.base.PagebleDTO;
import gov.ifms.common.exception.CustomException;
import gov.ifms.common.pagination.PageDetails;
import gov.ifms.edp.dto.EDPMsFinancialYearDto;
import gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto;
import gov.ifms.grant.dto.GrantPubHdrDto;
import gov.ifms.grant.dto.GrantSurrenderHdrDto;
import gov.ifms.grant.dto.GrantSurrenderReqParamsDto;
import gov.ifms.grant.dto.GrantSurrenderSaveRequestDto;
import gov.ifms.grant.dto.SurrenderDto;
import gov.ifms.grant.dto.SurrenderListDto;
import gov.ifms.grant.entity.GrantSurrenderHdrEntity;

public interface GrantSurrenderService {

	GrantSurrenderHdrDto saveAsDraftGrantSurrender(GrantSurrenderSaveRequestDto dto) throws CustomException;

	GrantSurrenderHdrDto submitGrantSurrender(GrantSurrenderSaveRequestDto dto) throws CustomException;

	GrantSurrenderHdrEntity findById(Long id);

	GrantSurrenderHdrEntity generateGrantSurrenderRefNumber(GrantSurrenderHdrEntity entity) throws CustomException;

	GrantSurrenderHdrEntity saveOrUpdateGrantSurrenderHdr(GrantSurrenderHdrEntity entity) throws CustomException;

	public PagebleDTO<SurrenderListDto> getSurrnederSearchList(PageDetails pageDetails) throws CustomException;

	List<EDPMsFinancialYearDto> getFinancialYearList() throws CustomException;

	GrantSurrenderSaveRequestDto getData(Long id) throws CustomException;

	//List<BudgetMsDemandDto> getAllDemandList() throws CustomException;

	List getDemandList(SurrenderDto dto) throws CustomException;

	List<GrantSurrenderReqParamsDto> getMajorHeadList(SurrenderDto dto) throws CustomException;

	List<BudgetMsSubMajorHeadDto> getSubMajorHeadList(SurrenderDto dto) throws CustomException;

	List<BudgetMsMinorHeadDto> getMinorHeadList(SurrenderDto dto) throws CustomException;

	List<BudgetMsSubHeadDto> getSubHeadList(SurrenderDto dto) throws CustomException;

	List<BudgetMsDetailHeadDto> getDetailHeadList(SurrenderDto dto) throws CustomException;
	
	List<GrantPubHdrDto> getBudgetEstimateList(SurrenderDto dto) throws CustomException;

	List<BudgetMsItemDto> getItemList(SurrenderDto dto) throws CustomException;
	
	public String getSchemeType(SurrenderDto dto) throws CustomException;
	
	BigDecimal getAvailableGrantState(SurrenderDto dto) throws CustomException;
	
	public void deleteData(Long id) throws CustomException;
}
