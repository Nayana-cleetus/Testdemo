package gov.ifms.grant.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gov.ifms.budget.dto.BudgetMsDemandDto;
import gov.ifms.budget.dto.BudgetMsDetailHeadDto;
import gov.ifms.budget.dto.BudgetMsItemDto;
import gov.ifms.budget.dto.BudgetMsMinorHeadDto;
import gov.ifms.budget.dto.BudgetMsSubHeadDto;
import gov.ifms.budget.dto.BudgetMsSubMajorHeadDto;
import gov.ifms.budget.repository.BudgetLkDeptDemandRepository;
import gov.ifms.common.base.PagebleDTO;
import gov.ifms.common.dao.NativeQueryResultsMapper;
import gov.ifms.common.exception.CustomException;
import gov.ifms.common.pagination.PageDetails;
import gov.ifms.common.pagination.SearchParam;
import gov.ifms.common.util.Constant;
import gov.ifms.common.util.ErrorResponse;
import gov.ifms.common.util.Utils;
import gov.ifms.edp.dto.EDPMsFinancialYearDto;
import gov.ifms.grant.converter.GrantSurrenderHdrConverter;
import gov.ifms.grant.converter.GrantSurrenderSdConverter;
import gov.ifms.grant.converter.GrantSurrenderTxnConverter;
import gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto;
import gov.ifms.grant.dto.GrantPubHdrDto;
import gov.ifms.grant.dto.GrantSurrenderDtlsDto;
import gov.ifms.grant.dto.GrantSurrenderHdrDto;
import gov.ifms.grant.dto.GrantSurrenderReqParamsDto;
import gov.ifms.grant.dto.GrantSurrenderSaveRequestDto;
import gov.ifms.grant.dto.GrantSurrenderSdDto;
import gov.ifms.grant.dto.SurrenderDto;
import gov.ifms.grant.dto.SurrenderListDto;
import gov.ifms.grant.dto.SurrenderSearchEnum;
import gov.ifms.grant.entity.GrantSurrenderDEntity;
import gov.ifms.grant.entity.GrantSurrenderHdrEntity;
import gov.ifms.grant.entity.GrantSurrenderSDEntity;
import gov.ifms.grant.repository.GrantBudgetHeadWiseUtilSmryRepository;
import gov.ifms.grant.repository.GrantFdToAdSaveRepository;
import gov.ifms.grant.repository.GrantFdToAdTxnRepository;
import gov.ifms.grant.repository.GrantMsDemandRepository;
import gov.ifms.grant.repository.GrantMsDetailHeadRepository;
import gov.ifms.grant.repository.GrantMsFinancialYearRepository;
import gov.ifms.grant.repository.GrantMsItemRepository;
import gov.ifms.grant.repository.GrantMsMajorHeadRepository;
import gov.ifms.grant.repository.GrantMsMinorHeadRepository;
import gov.ifms.grant.repository.GrantMsSubMajorHeadRepository;
import gov.ifms.grant.repository.GrantPubHdrRepository;
import gov.ifms.grant.repository.GrantSubHeadRepository;
import gov.ifms.grant.repository.GrantSurrenderHdrRepository;
import gov.ifms.grant.repository.GrantSurrenderSdRepository;
import gov.ifms.grant.repository.GrantSurrenderSubHeadWiseSdRepository;
import gov.ifms.grant.repository.GrantSurrenderTxnRepository;
import gov.ifms.grant.util.GrantConstant;
import gov.ifms.grant.util.GrantConstants;

@Service
public class GrantSurrenderServiceImpl implements GrantSurrenderService {

	/** The logger. */
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());

	/** The converter. */
	@Autowired
	private GrantSurrenderHdrConverter hdrConverter;

	@Autowired
	private GrantSurrenderSdConverter sdConverter;

	@Autowired
	private GrantSurrenderTxnConverter dConverter;

	/** The hdr repository. */
	@Autowired
	private GrantSurrenderHdrRepository hdrRepository;

	/** The hdr repository. */
	@Autowired
	private GrantSurrenderSdRepository sdRepository;

	@Autowired
	private GrantSurrenderTxnRepository dRepository;
	@Autowired
	BudgetLkDeptDemandRepository budgetLkDeptDemandRepository;

	@Autowired
	GrantMsDemandRepository msDemandrepository;

	@Autowired
	GrantMsMajorHeadRepository msMajorHeadrepository;

	@Autowired
	GrantMsSubMajorHeadRepository msSubMajorHeadrepository;

	@Autowired
	GrantMsMinorHeadRepository msMinorHeadRepository;

	@Autowired
	GrantSubHeadRepository msSubHeadRepository;

	@Autowired
	GrantMsDetailHeadRepository msDetailHeadRepository;

	@Autowired
	GrantSurrenderSubHeadWiseSdRepository subHeadWiseSdRepository;

	@Autowired
	GrantMsItemRepository itemRepository;

	@Autowired
	GrantMsFinancialYearRepository msFinancialYearRepository;
	
	@Autowired
	GrantPubHdrRepository grantPubHdrRepository;
	
	@Autowired
	GrantMsItemRepository grantMsItemRepository;
	
	@Autowired
	GrantBudgetHeadWiseUtilSmryRepository utilRepository;
	
	@Autowired
	GrantFdToAdSaveRepository fdToAdSaveRepository;
	
	@Autowired
	GrantFdToAdTxnRepository fdToAdTxnRepository;
	
	BigDecimal inAd=BigDecimal.valueOf(0);
	BigDecimal inFd=BigDecimal.valueOf(0);
	BigDecimal outAd=BigDecimal.valueOf(0);
	BigDecimal outOfficers=BigDecimal.valueOf(0);
	

	@Override
	@ExceptionHandler
	@Transactional(rollbackFor = CustomException.class)
	public GrantSurrenderHdrDto saveAsDraftGrantSurrender(GrantSurrenderSaveRequestDto dto) throws CustomException {

		GrantSurrenderHdrDto createHdrDto = dto.getGrantSurrenderHdrDto();
		List<GrantSurrenderDtlsDto> requestSaveDtos = dto.getGrantSurrendeDtlsDto();
		// List<GrantSurrenderSdDto> requestSaveDtos = dto.getGrantSurrenderSdDto();
		Long grantSurrenderHdrId = dto.getGrantSurrenderHdrDto().getGrantSurrenderHdrId();
		log.info("in service");
		GrantSurrenderHdrEntity createHdrEntity = null;
		try {
			if ((null != grantSurrenderHdrId) && (grantSurrenderHdrId > 0)) {
				log.info("in service");
				createHdrEntity = findById(grantSurrenderHdrId);
			}

			if (createHdrEntity != null) {
				log.info("in createHdrEntity != null SAVEASDRFT");
				log.info("in " + createHdrEntity.getStatusCd());
				if (createHdrEntity.getStatusCd().equals("Draft")) {
					createHdrEntity.setFinYear(createHdrDto.getFinYear());
					createHdrEntity.setRevenueCapital(createHdrDto.getRevenueCapital());
					createHdrEntity.setModifiedDt(new Date());
					createHdrEntity.setModifiedBy(createHdrDto.getCreatedBy());
					createHdrEntity = hdrRepository.save(createHdrEntity);
				}
			}

			if (createHdrEntity == null) {
				log.info("in service");
				createHdrDto.setCreatedDt(new Date());
				createHdrDto.setCreatedByPost("desig");
				createHdrDto.setModifiedDt(new Date());
				createHdrDto.setModifiedBy(createHdrDto.getCreatedBy());
				createHdrDto.setStatusCd("Draft");
				createHdrDto.setStatusDesc("DRAFT");
				createHdrDto.setStatusDt(new Date());
				createHdrDto.setReferenceNo("DRAFT");
				createHdrDto.setReferenceDt(new Date());

				createHdrEntity = hdrConverter.toEntity(createHdrDto);
				createHdrEntity = hdrRepository.save(createHdrEntity);
			}
			saveOrUpdateSd(requestSaveDtos, createHdrEntity);

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_CREATE);
		}
		return hdrConverter.toDTO(createHdrEntity);

	}

	@Override
	@ExceptionHandler
	@Transactional(rollbackFor = CustomException.class)
	public GrantSurrenderHdrDto submitGrantSurrender(GrantSurrenderSaveRequestDto dto) throws CustomException {
		GrantSurrenderHdrDto createHdrDto = dto.getGrantSurrenderHdrDto();
		List<GrantSurrenderDtlsDto> requestSaveDtos = dto.getGrantSurrendeDtlsDto();
		// List<GrantSurrenderSdDto> requestSaveDtos = dto.getGrantSurrenderSdDto();
		Long grantSurrenderHdrId = dto.getGrantSurrenderHdrDto().getGrantSurrenderHdrId();
		log.info("in service submit");
		GrantSurrenderHdrEntity createHdrEntity = null;
		try {
			if ((null != grantSurrenderHdrId) && (grantSurrenderHdrId > 0)) {
				log.info("in service");
				createHdrEntity = findById(grantSurrenderHdrId);
			}
			if (createHdrEntity != null) {
				log.info("in createHdrEntity != null SAVEASDRFT");
				log.info("in " + createHdrEntity.getStatusCd());
				if (createHdrEntity.getStatusCd().equals("Draft")) {
					createHdrEntity.setFinYear(createHdrDto.getFinYear());
					createHdrEntity.setRevenueCapital(createHdrDto.getRevenueCapital());
					createHdrEntity.setSurrenderNo(createHdrDto.getSurrenderNo());
					createHdrEntity.setStatusCd("Pending");
					createHdrEntity.setStatusDesc("Pending");
					createHdrEntity.setModifiedDt(new Date());
					createHdrEntity.setModifiedBy(createHdrDto.getCreatedBy());
					generateGrantSurrenderRefNumber(createHdrEntity);
				} else {
					createHdrEntity.setModifiedDt(new Date());
					createHdrEntity.setModifiedBy(createHdrDto.getCreatedBy());
					createHdrEntity = hdrRepository.save(createHdrEntity);
				}
			}

			if (createHdrEntity == null) {
				log.info("in service");
				createHdrDto.setCreatedDt(new Date());
				createHdrDto.setCreatedByPost("desig");
				createHdrDto.setModifiedDt(new Date());
				createHdrDto.setModifiedBy(createHdrDto.getCreatedBy());
				createHdrDto.setStatusCd("Pending");
				createHdrDto.setStatusDesc("Pending");
				createHdrDto.setStatusDt(new Date());

				createHdrEntity = hdrConverter.toEntity(createHdrDto);
				createHdrEntity = generateGrantSurrenderRefNumber(createHdrEntity);

			}
			saveOrUpdateSd(requestSaveDtos, createHdrEntity);
			saveOrUpdateTxnData(requestSaveDtos, createHdrEntity);
		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_CREATE);
		}
		return hdrConverter.toDTO(createHdrEntity);
	}

	@Override
	public GrantSurrenderHdrEntity generateGrantSurrenderRefNumber(GrantSurrenderHdrEntity entity)
			throws CustomException {
		if (StringUtils.isEmpty(entity.getReferenceNo()) || StringUtils.isBlank(entity.getReferenceNo())) {
			entity.setReferenceNo(getTransactionNumber(GrantConstant.SUR));
			entity.setReferenceDt(new Date());

		}
		GrantSurrenderHdrEntity newhdrentity = saveOrUpdateGrantSurrenderHdr(entity);
		return newhdrentity;

	}

	public String getTransactionNumber(String eventName) throws CustomException {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(GrantConstant.REF_SCREEN, eventName);
		String procName = Constant.GRANT_SCHEMA.concat(Constant.DOT.concat(GrantConstant.SP_GRANT_REF_NO_GEN));
		List<Object[]> nextSequanceNumber = hdrRepository.callStoredProcedure(procName, map);
		if (!Utils.isEmpty(nextSequanceNumber)) {
			return (String) nextSequanceNumber.get(0)[0];
		} else {
			throw new CustomException(ErrorResponse.REF_GENERATION_FAILED);
		}
	}

	@Override
	public GrantSurrenderHdrEntity saveOrUpdateGrantSurrenderHdr(GrantSurrenderHdrEntity entity)
			throws CustomException {
		// TODO Auto-generated method stub
		try {
			return hdrRepository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new CustomException(ErrorResponse.REFERENCE_NOT_EXISTS);
		} catch (Exception ex) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_CREATE);
		}
	}

	private void saveOrUpdateSd(List<GrantSurrenderDtlsDto> requestSaveDto, GrantSurrenderHdrEntity createHdrEntity)
			throws CustomException {

		final GrantSurrenderHdrEntity finalHdrEntity = createHdrEntity;

		// List<GrantSurrenderDtlsDto> requestSaveDtos
		log.info("in saveOrUpdateSd service");
		try {
			List<GrantSurrenderSDEntity> savedEntities = getAllSaveSurrendersdByHdrId(createHdrEntity);
			log.info("in saveOrUpdateSd service afr getAllSaveSurrendersdByHdrId");

			if (!savedEntities.isEmpty()) {
				for (GrantSurrenderSDEntity grantSurrenderSDEntity : savedEntities) {
					log.info("in mod date");
					grantSurrenderSDEntity.setModifiedDt(new Date());
					grantSurrenderSDEntity.setModifiedBy(finalHdrEntity.getModifiedBy());
					grantSurrenderSDEntity.setModifiedByPost(finalHdrEntity.getModifiedByPost());
				}
			}
			List<GrantSurrenderSdDto> grantSdrSdDto = new ArrayList<>();
			for (GrantSurrenderDtlsDto grantSurrenderDtlsDto : requestSaveDto) {
				List<GrantSurrenderSdDto> requestSaveDtos = new ArrayList<>();
				requestSaveDtos = grantSurrenderDtlsDto.getItemList();
				for (GrantSurrenderSdDto grantSurrenderSdDto : requestSaveDtos) {
					grantSurrenderSdDto.setBudgetHead(grantSurrenderDtlsDto.getBudgetHeadCode());
					grantSurrenderSdDto.setSchemeType(grantSurrenderDtlsDto.getSchemeType());
					grantSurrenderSdDto.setCreatedBy(finalHdrEntity.getCreatedBy());
					grantSurrenderSdDto.setCreatedByPost(finalHdrEntity.getCreatedByPost());
					// grantSurrenderSdDto.setSanctionGrantAmt(new BigDecimal(224));
					// grantSurrenderSdDto.setSupplementaryAmt(new BigDecimal(224));
					// grantSurrenderSdDto.setExpenditureAsonDtAmt(new BigDecimal(224));
					// grantSurrenderSdDto.setProbableExpenditureAmt(new BigDecimal(97));
					//grantSurrenderSdDto.setSurrenderAmt(new BigDecimal(17));
					//grantSurrenderSdDto.setSurrenderReason("grant");
					//grantSurrenderSdDto.setSurrenderTo("HOD");
					// grantSurrenderSdDto.setSchemeType("state");
					grantSdrSdDto.add(grantSurrenderSdDto);
				}

				/*
				 * GrantSurrenderSdDto grantSurrenderSdDto;
				 * requestSaveDtos.add(grantSurrenderSdDto);
				 */
				// List<GrantSurrenderSDEntity> requestSaveEntities =
				// sdConverter.toEntity(requestSaveDtos);
			}

			List<GrantSurrenderSDEntity> requestSaveEntities = sdConverter.toEntity(grantSdrSdDto);
			if (!savedEntities.isEmpty()) {
				for (GrantSurrenderSDEntity saveSdEntity : savedEntities) {
					sdRepository.delete(saveSdEntity);
				}
				requestSaveEntities.forEach(entity -> {
					entity.setGrantSurrenderHdrId(finalHdrEntity);
					entity.setCreatedDt(finalHdrEntity.getCreatedDt());
					entity.setCreatedBy(finalHdrEntity.getCreatedBy());
					entity.setModifiedBy(finalHdrEntity.getModifiedBy());
					entity.setModifiedDt(finalHdrEntity.getModifiedDt());
				});
				sdRepository.saveAll(requestSaveEntities);
				// updateGrantFdToAdSaveDt(requestSaveEntities, savedEntities);
			} else {
				log.info("in else case saveOrUpdateSd service");
				requestSaveEntities.forEach(entity -> {
					entity.setGrantSurrenderHdrId(finalHdrEntity);
					entity.setCreatedDt(new Date());

				});

				log.info("in saveOrUpdateSd service before saving");
				sdRepository.saveAll(requestSaveEntities);
				log.info("in saveOrUpdateSd service aftersaving");
			}

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_CREATE);
		}

	}

	private void saveOrUpdateTxnData(List<GrantSurrenderDtlsDto> requestSaveDto,
			GrantSurrenderHdrEntity createHdrEntity) throws CustomException {
		final GrantSurrenderHdrEntity finalHdrEntity = createHdrEntity;
		log.info("in saveOrUpdate txn service");
		try {
			List<GrantSurrenderDEntity> savedEntities = getAllTxnsSurrendersdByHdrId(createHdrEntity);
			log.info("in saveOrUpdated service afr getAllSaveSurrendersdByHdrId");
			if (!savedEntities.isEmpty()) {
				for (GrantSurrenderDEntity grantSurrenderDEntity : savedEntities) {
					log.info("in mod date");
					grantSurrenderDEntity.setModifiedDt(new Date());
					grantSurrenderDEntity.setModifiedBy(finalHdrEntity.getModifiedBy());
					grantSurrenderDEntity.setModifiedByPost(finalHdrEntity.getModifiedByPost());
				}
			}
			List<GrantSurrenderSdDto> grantSdrSdDto = new ArrayList<>();
			for (GrantSurrenderDtlsDto grantSurrenderDtlsDto : requestSaveDto) {
				List<GrantSurrenderSdDto> requestSaveDtos = new ArrayList<>();
				requestSaveDtos = grantSurrenderDtlsDto.getItemList();
				for (GrantSurrenderSdDto grantSurrenderSdDto : requestSaveDtos) {
					grantSurrenderSdDto.setBudgetHead(grantSurrenderDtlsDto.getBudgetHeadCode());
					grantSurrenderSdDto.setSchemeType(grantSurrenderDtlsDto.getSchemeType());
					grantSurrenderSdDto.setCreatedBy(finalHdrEntity.getCreatedBy());
					grantSurrenderSdDto.setCreatedByPost(finalHdrEntity.getCreatedByPost());
					// grantSurrenderSdDto.setSanctionGrantAmt(new BigDecimal(224));
					// grantSurrenderSdDto.setSupplementaryAmt(new BigDecimal(224));
					// grantSurrenderSdDto.setExpenditureAsonDtAmt(new BigDecimal(224));
					// grantSurrenderSdDto.setProbableExpenditureAmt(new BigDecimal(97));
					//grantSurrenderSdDto.setSurrenderAmt(new BigDecimal(17));
					//grantSurrenderSdDto.setSurrenderReason("grant");
					//grantSurrenderSdDto.setSurrenderTo("HOD");
					// grantSurrenderSdDto.setSchemeType("state");
					grantSdrSdDto.add(grantSurrenderSdDto);
				}
			}
			List<GrantSurrenderDEntity> requestSaveEntities = dConverter.toEntity(grantSdrSdDto);
			if (!savedEntities.isEmpty()) {
				for (GrantSurrenderDEntity savedEntity : savedEntities) {
					dRepository.delete(savedEntity);
				}
				requestSaveEntities.forEach(entity -> {
					entity.setGrantSurrenderHdrId(finalHdrEntity);
					entity.setCreatedDt(finalHdrEntity.getCreatedDt());
					entity.setCreatedBy(finalHdrEntity.getCreatedBy());
					entity.setModifiedBy(finalHdrEntity.getModifiedBy());
					entity.setModifiedDt(finalHdrEntity.getModifiedDt());
				});
				dRepository.saveAll(requestSaveEntities);
				// updateGrantFdToAdTxnData(requestSaveEntities, savedEntities);
			} else {
				log.info("in else case saveOrUpdateSd service");
				requestSaveEntities.forEach(entity -> {
					entity.setGrantSurrenderHdrId(finalHdrEntity);
					entity.setCreatedDt(new Date());

				});

				log.info("in saveOrUpdated service before saving");
				dRepository.saveAll(requestSaveEntities);
				log.info("in saveOrUpdated service aftersaving");
			}

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_CREATE);
		}

	}

	/*
	 * private void updateGrantFdToAdSaveDt(List<GrantSurrenderSDEntity>
	 * requestSaveEntities, List<GrantSurrenderSDEntity> savedEntities) throws
	 * CustomException {
	 * 
	 * }
	 * 
	 * private void updateGrantFdToAdTxnData(List<GrantSurrenderDEntity>
	 * requestSaveEntities, List<GrantSurrenderDEntity> savedEntities) throws
	 * CustomException {
	 * 
	 * }
	 */

	private List<GrantSurrenderSDEntity> getAllSaveSurrendersdByHdrId(GrantSurrenderHdrEntity hdrEntity) {
		log.info("in saveOrUpdateSd  getAllSaveFdsByHdrId");
		return sdRepository.findByGrantSurrenderHdrId(hdrEntity);

	}

	private List<GrantSurrenderDEntity> getAllTxnsSurrendersdByHdrId(GrantSurrenderHdrEntity hdrEntity) {
		log.info("in saveOrUpdateSd  getAllTxnsSurrendersdByHdrId");
		return dRepository.findByGrantSurrenderHdrId(hdrEntity);

	}

	@Override
	public GrantSurrenderHdrEntity findById(Long id) {
		return hdrRepository.getOne(id);
	}

	/**
	 * Returns recent 10 financial years.
	 *
	 * @param
	 * @return list of EDPMsFinancialYearDto
	 */

	@Override
	public List<EDPMsFinancialYearDto> getFinancialYearList() throws CustomException {
		List<EDPMsFinancialYearDto> finYearList = new ArrayList<>();
		try {
			List<EDPMsFinancialYearDto> finTotalList = msFinancialYearRepository.getFinancialYearList();
			EDPMsFinancialYearDto obj = finTotalList.stream().filter(fin -> fin.getIsCurrentYear() == 1).findAny()
					.get();
			int endYear = obj.getFyEnd();
			finYearList = finTotalList.stream()
					.filter(fin -> fin.getFyEnd() > endYear - 10 && fin.getFyEnd() <= endYear)
					.collect(Collectors.toList());
			return finYearList;
		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	@Override
	public PagebleDTO<SurrenderListDto> getSurrnederSearchList(PageDetails pageDetails) throws CustomException {
		try {
			String procName = Constant.GRANT_SCHEMA.concat(Constant.DOT.concat("SP_GRANT_SURRENDER_SC_LIST"));
			Map<String, Object> map = buildPageInMap(pageDetails);
			List<SurrenderListDto> objStorePro = this.storeProcSearch(procName, map);
			int totalPages = objStorePro.size();
			return new PagebleDTO<>(pageDetails.getPageElement(), totalPages, totalPages, objStorePro);

		} catch (Exception ex) {
			log.error(ex.toString(), ex);
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	/**
	 * Builds the page in map.
	 *
	 * @param pageDetail the page detail
	 * @return the map
	 */

	private Map<String, Object> buildPageInMap(PageDetails pageDetail) {
		StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append((OAuthUtility.getCurrentUserUserId())).append(Constant
		// .COMMA);
		Map<String, String> collect = getSurrenderSearchFields().stream()
				.collect(Collectors.toMap(SurrenderSearchEnum::getKey, SurrenderSearchEnum::getValue));
		return SearchParam.buildPageInMap(stringBuilder, pageDetail, collect);
	}

	/*
	 * Gets the Surrender search fields.
	 *
	 * @return the Surrender search fields
	 */

	private List<SurrenderSearchEnum> getSurrenderSearchFields() {

		return Arrays.asList(SurrenderSearchEnum.SRN_SEARCH_FIELD_FY, SurrenderSearchEnum.SRN_SEARCH_FIELD_REF_NO,
				SurrenderSearchEnum.SRN_SEARCH_FIELD_FROM_DATE, SurrenderSearchEnum.SRN_SEARCH_FIELD_TO_DATE,
				SurrenderSearchEnum.SRN_SEARCH_FIELD_STATUS, SurrenderSearchEnum.SRN_SEARCH_LYING_WITH);

	}

	/**
	 * Store proc grantsearch.
	 *
	 * @param procName the proc name
	 * @param map      the map
	 * @return the list
	 * @throws CustomException the custom exception
	 */
	public List<SurrenderListDto> storeProcSearch(String procName, Map<String, Object> map) throws CustomException {
		List<Object[]> objectSp = hdrRepository.callStoredProcedure(procName, map);
		return !objectSp.isEmpty() ? NativeQueryResultsMapper.map(objectSp, SurrenderListDto.class)
				: Collections.emptyList();
	}

	@Override
	public GrantSurrenderSaveRequestDto getData(Long id) throws CustomException {

		try {
			GrantSurrenderSaveRequestDto reqDto = new GrantSurrenderSaveRequestDto();
			List<GrantSurrenderDtlsDto> surrDtlsListDto = new ArrayList<>();

			GrantSurrenderHdrEntity hdrEntity = hdrRepository.getOne(id);

			List<GrantSurrenderSDEntity> sdEntityList = sdRepository.findByGrantSurrenderHdrId(hdrEntity);
			List<String> budgetHeadList = sdRepository.getBudgetHead(id);

			List<GrantSurrenderSdDto> totalSdDtoList = new ArrayList<>();
			sdEntityList.forEach(sd -> {
				totalSdDtoList.add(sdConverter.toDTO(sd));
			});

			budgetHeadList.forEach(bh -> {
				GrantSurrenderDtlsDto dtlsDto = new GrantSurrenderDtlsDto();
				List<GrantSurrenderSdDto> sdDtoList = new ArrayList<>();
				totalSdDtoList.forEach(sdDto -> {
					if (bh != null && bh.equals(sdDto.getBudgetHead())) {
						dtlsDto.setBudgetHeadCode(sdDto.getBudgetHead());
						dtlsDto.setItemName(itemRepository.findById(sdDto.getItemId()).get().getItemName());
						dtlsDto.setSchemeType(sdDto.getSchemeType());
						sdDtoList.add(sdDto);
					}
					if (bh == null && sdDto.getBudgetHead() == null) {
						dtlsDto.setBudgetHeadCode(null);
						dtlsDto.setItemName(itemRepository.findById(sdDto.getItemId()).get().getItemName());
						dtlsDto.setSchemeType(sdDto.getSchemeType());
						sdDtoList.add(sdDto);
					}
				});
				dtlsDto.setItemList(sdDtoList);
				surrDtlsListDto.add(dtlsDto);
			});

			reqDto.setGrantSurrenderHdrDto(hdrConverter.toDTO(hdrEntity));
			reqDto.setGrantSurrendeDtlsDto(surrDtlsListDto);

			return reqDto;
		} catch (Exception ex) {
			log.error(ex.toString(), ex);
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	/*
	 * @Override public List<BudgetMsDemandDto> getAllDemandList() throws
	 * CustomException { List<BudgetMsDemandDto> demandList = new ArrayList<>(); try
	 * { demandList = msDemandrepository.getAllDemandList();
	 * 
	 * return demandList; } catch (Exception e) { throw new
	 * CustomException(ErrorResponse.ERROR_WHILE_FATCHING); } }
	 */

	@Override
	public List getDemandList(SurrenderDto dto) throws CustomException {
		List<BudgetMsDemandDto> demandList = new ArrayList<>();
		try {
			System.out.println(dto.getRoleId());
			
			System.out.println(dto.getRoleId().contains(43));
			
			System.out.println(dto.getRoleId().contains(1));

			if (dto.getRoleId().contains(43)) {
				demandList = subHeadWiseSdRepository.getDemandList("AD-CO", "CO-CO");
				return demandList;
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				demandList = subHeadWiseSdRepository.getDemandList("AD-DDO", "CO-DDO");
				return demandList;
			} else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				demandList = subHeadWiseSdRepository.getAdDemandList(dto.getOfficeId());
				return demandList;
			}
			return null;

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	@Override
	public List<GrantSurrenderReqParamsDto> getMajorHeadList(SurrenderDto dto) throws CustomException {
		try {

			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getMajorHeadList(dto.getDemandId(), "AD-CO", "CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				return subHeadWiseSdRepository.getMajorHeadList(dto.getDemandId(), "AD-DDO", "CO-DDO");
			} else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				return subHeadWiseSdRepository.getAdMajorHeadList(dto.getDemandId());
			}
			return null;

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	@Override
	public List<BudgetMsSubMajorHeadDto> getSubMajorHeadList(SurrenderDto dto) throws CustomException {
		try {

			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getSubMajorHeadList(dto.getDemandId(), dto.getMajorHeadId(), "AD-CO",
						"CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				return subHeadWiseSdRepository.getSubMajorHeadList(dto.getDemandId(), dto.getMajorHeadId(), "AD-DDO",
						"CO-DDO");
			}
			else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				return msSubMajorHeadrepository.getSubMajorHeadList(dto.getDemandId(), dto.getMajorHeadId());
			}
			return null;

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	@Override
	public List<BudgetMsMinorHeadDto> getMinorHeadList(SurrenderDto dto) throws CustomException {
		try {

			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getMinorHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), "AD-CO", "CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				return subHeadWiseSdRepository.getMinorHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), "AD-DDO", "CO-DDO");
			} else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				return msMinorHeadRepository.getMinorHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId());
			}
			return null;

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	@Override
	public List<BudgetMsSubHeadDto> getSubHeadList(SurrenderDto dto) throws CustomException {
		try {

			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getSubHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), "AD-CO", "CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				return subHeadWiseSdRepository.getSubHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), "AD-DDO", "CO-DDO");
			} else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				return msSubHeadRepository.getSubHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId());
			}
			return null;

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	@Override
	public List<BudgetMsDetailHeadDto> getDetailHeadList(SurrenderDto dto) throws CustomException {
		try {

			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getDetailHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(), "AD-CO", "CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				return subHeadWiseSdRepository.getDetailHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(), "AD-DDO", "CO-DDO");
			} else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				return msDetailHeadRepository.getDetailHeadList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId());
			}
			return null;

		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}
	
	public List<GrantPubHdrDto> getBudgetEstimateList(SurrenderDto dto) throws CustomException {
		try {
			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getBudgetEstimateList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(),dto.getDetailHeadId(),dto.getChargedVotedFlag(), "AD-CO", "CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)") ) {
				return subHeadWiseSdRepository.getBudgetEstimateList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(),dto.getDetailHeadId(),dto.getChargedVotedFlag(), "AD-DDO", "CO-DDO");
			} else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				if (dto.getChargedVotedFlag().equalsIgnoreCase(GrantConstants.CHARGED_FLAG)) 
				dto.setChargedVotedFlag(GrantConstants.CHARGED);	
				else 
				dto.setChargedVotedFlag(GrantConstants.VOTED);
				return	grantPubHdrRepository.getBudgetEstimateTypeList(dto.getFinYear(), dto.getRc(), dto.getChargedVotedFlag(), dto.getDeptId(), dto.getDemandId(), dto.getMajorHeadId(), dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(), dto.getDetailHeadId());
			}
			return null;
			
	} catch (Exception e) {
		throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
	}
	}
	
	public String getSchemeType(SurrenderDto dto) throws CustomException {
		try {
			System.out.println("Calling repo method");
			GrantBudgetHeadWiseUtilSmryDto utilDto= utilRepository.getStateCenterPercent(dto.getFinYear(),dto.getDemandId(), dto.getMajorHeadId(),
				dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(),dto.getDetailHeadId(),dto.getChargedVotedFlag());
		System.out.println("utilDto- "+utilDto);
		System.out.println(utilDto.getBeStatePercent());
		if(utilDto.getBeStatePercent().intValue()==0 && utilDto.getBeCenterPercent().intValue()>0) {
			return "CSS";
		}else if(utilDto.getBeStatePercent().intValue()>0 && utilDto.getBeCenterPercent().intValue()==0)
			return "state";
		else
			return "Partial";
	}catch (Exception e) {
		throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
	}
	}
	
	public List<BudgetMsItemDto> getItemList(SurrenderDto dto) throws CustomException {
		try {
			if (dto.getRoleId().contains(43)) {
				return subHeadWiseSdRepository.getItemList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(),dto.getDetailHeadId(),dto.getEstimateType(), "AD-CO", "CO-CO");
			} else if (dto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
				return subHeadWiseSdRepository.getItemList(dto.getDemandId(), dto.getMajorHeadId(),
						dto.getSubMajorHeadId(), dto.getMinorHeadId(), dto.getSubHeadId(),dto.getDetailHeadId(),dto.getEstimateType(), "AD-DDO", "CO-DDO");
			}
			else if(dto.getOfficeType().equals("Administrative Department") || dto.getOfficeType().equals("Head of Department (HOD)")) {
				List<BudgetMsItemDto> list=grantMsItemRepository.getItemNameList(dto.getEstimateType());
				list=list.stream().sorted(Comparator.comparing(BudgetMsItemDto :: getItemCode )).collect(Collectors.toList());
				return list;
			}
			return null;
		
		}catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		
		}
	}
	
	
	
	
	public BigDecimal getAvailableGrantState(SurrenderDto dto) throws CustomException {
		BigDecimal outOfficers=BigDecimal.valueOf(0);
		try {
		if(dto.getOfficeType().equals("Administrative Department")) {
		BigDecimal inFd=fdToAdTxnRepository.getReleasedGrantFromFd(dto.getDeptId());
		BigDecimal inAd=subHeadWiseSdRepository.getAvailableGrantFromAd(dto.getDeptId(),"AD-AD");
		if(inFd!=null && inAd!=null)
		inAd=inAd.add(inFd);
		else if(inAd==null && inFd!=null)
		inAd=inFd;
		List<BigDecimal> outAmount=subHeadWiseSdRepository.getReleasedGrantToOfficers(dto.getDeptId(),"AD-CO","AD-DDO","AD-AD");
		if(!outAmount.isEmpty()) {
		for(BigDecimal amt:outAmount)
		outOfficers=outOfficers.add(amt);
		}

		BigDecimal outSurr=dRepository.getSurrenderAmount(dto.getDeptId());

		if(outOfficers!=null && outSurr!=null)
		outOfficers=outOfficers.add(outSurr);
		else if(outOfficers==null && outSurr!=null)
		outOfficers=outSurr;


		if(inAd!=null && outOfficers!=null) {
		if(inAd.compareTo(outOfficers)==1)
		return inAd.subtract(outOfficers);
		}

		else if(inAd==null && outOfficers!=null)
		return new BigDecimal(0);
		else if(inAd!=null && outOfficers==null)
		return inAd;
		}
		return new BigDecimal(0);
		}catch (Exception e) {
		throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}

		}
	
	public void deleteData(Long id) throws CustomException{
		try {
			GrantSurrenderHdrEntity hdrEntity=hdrRepository.findById(id).get();
			
			List<GrantSurrenderSDEntity> sdEntityList=sdRepository.findByGrantSurrenderHdrId(hdrEntity);
			if(!sdEntityList.isEmpty()) {
				sdEntityList.forEach(sdEntity->sdRepository.delete(sdEntity));
			}
			
			
			List<GrantSurrenderDEntity> txnEntityList=dRepository.findByGrantSurrenderHdrId(hdrEntity);
			if(!txnEntityList.isEmpty()) {
				txnEntityList.forEach(txnEntity->dRepository.delete(txnEntity));
			}
			
			hdrRepository.deleteById(id);
			
		}catch(Exception ex) {
			log.error(ex.toString(), ex);
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

			

}
