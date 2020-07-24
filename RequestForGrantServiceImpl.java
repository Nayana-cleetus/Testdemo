package gov.ifms.grant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.ifms.budget.repository.BudgetMsObjectClassRepository;
import gov.ifms.common.exception.CustomException;
import gov.ifms.common.util.ErrorResponse;
import gov.ifms.grant.converter.GrantBudgetHeadWiseUtilSmryConverter;
import gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto;
import gov.ifms.grant.dto.RequestForGrantInputDto;
import gov.ifms.grant.dto.RequestForGrantOutputDto;
import gov.ifms.grant.entity.GrantBudgetHeadWiseUtilSmryEntity;
import gov.ifms.grant.repository.GrantMsItemRepository;
import gov.ifms.grant.repository.RequestForGrantRepository;

@Service
@Transactional
public class RequestForGrantServiceImpl implements RequestForGrantSrvice {

	@Autowired
	private BudgetMsObjectClassRepository repository;

	@Autowired
	RequestForGrantRepository grantBudgetHeadWiseUtilSmryRepo;
	@Autowired
	GrantBudgetHeadWiseUtilSmryConverter converter;

	@Autowired
	GrantMsItemRepository msItemRepository;

	@Override
	public List<RequestForGrantOutputDto> getRequestForGrantDetails(RequestForGrantInputDto inputDto)
			throws CustomException {

		System.out.println("Inside Service Impl..");
		try {
			List<RequestForGrantOutputDto> resultList = new ArrayList<>();
			List<GrantBudgetHeadWiseUtilSmryEntity> itemClassWiseEntity = new ArrayList<GrantBudgetHeadWiseUtilSmryEntity>();
			if(inputDto.getItemId()== null ||inputDto.getItemId().equals(null)) {
				itemClassWiseEntity=grantBudgetHeadWiseUtilSmryRepo.getRequestForGrantDetailWithoutItem(inputDto.getFinYear(),
					inputDto.getDemandId(), inputDto.getMajorHeadId(), inputDto.getRcFlag(),
					inputDto.getSubMajorHeadId(), inputDto.getMinorHeadId(), inputDto.getSubHeadId(),
					inputDto.getDetailHeadId(), inputDto.getChargedVotedFlag(),
					inputDto.getDepartmentId());
			}else {
			itemClassWiseEntity = grantBudgetHeadWiseUtilSmryRepo.getRequestForGrantDetail(inputDto.getFinYear(),
					inputDto.getDemandId(), inputDto.getMajorHeadId(), inputDto.getRcFlag(),
					inputDto.getSubMajorHeadId(), inputDto.getMinorHeadId(), inputDto.getSubHeadId(),
					inputDto.getDetailHeadId(), inputDto.getChargedVotedFlag(), inputDto.getItemId(),
					inputDto.getDepartmentId());
			}
			System.out.println(itemClassWiseEntity);
			System.out.println(itemClassWiseEntity);
			List<GrantBudgetHeadWiseUtilSmryDto> SurrenderItemClassWiseDto = converter.toDTO(itemClassWiseEntity);

			if ("CSS".equals(inputDto.getSchemeType())) {
				for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
					grantBudgetHeadWiseUtilSmryDto.setBeStateAmt(grantBudgetHeadWiseUtilSmryDto.getBeCenterAmt());
					grantBudgetHeadWiseUtilSmryDto.setReStateAmt(grantBudgetHeadWiseUtilSmryDto.getReCenterAmt());
					grantBudgetHeadWiseUtilSmryDto.setExpenditureAsonDtAmt(grantBudgetHeadWiseUtilSmryDto.getExpenditureAsonDtCenterAmt());
				}
			}

			SurrenderItemClassWiseDto.forEach(grantBudgetDto -> {
				grantBudgetDto.setObjectClassName(
						repository.findById(grantBudgetDto.getObjectClassId()).get().getObjectClassName());
				grantBudgetDto.setObjectClassCode(
						repository.findById(grantBudgetDto.getObjectClassId()).get().getObjectClassCode());
				grantBudgetDto.setGrantReleseType(inputDto.getGrantReleseType());
				grantBudgetDto.setBudgetEstimateType(inputDto.getBudgetEstimateType());
				setBudgetHeadCode(grantBudgetDto, inputDto);
			});

			System.out.println("hiii");
			Map<String, List<GrantBudgetHeadWiseUtilSmryDto>> map = new HashMap<>();
			System.out.println("hiii");
			map = SurrenderItemClassWiseDto.stream()
					.collect(Collectors.groupingBy(GrantBudgetHeadWiseUtilSmryDto::getBudgetHeadCode));
			map.forEach((k, v) -> {
				RequestForGrantOutputDto test = new RequestForGrantOutputDto();
				test.setBudgetHeadCode(k);
				test.setItemWorkName(msItemRepository.findById(v.get(0).getItemId()).get().getItemName());
				test.setSchemeType(inputDto.getSchemeType());
				test.setItemList(v);
				resultList.add(test);

			});

			System.out.println("final result list : " + resultList);

			return resultList;
		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}
	}

	private GrantBudgetHeadWiseUtilSmryDto setBudgetHeadCode(GrantBudgetHeadWiseUtilSmryDto grantBudgetDto,
			RequestForGrantInputDto inputDto) {
		String[] array = inputDto.getInitialHeadCode().split("-");
		for (String head : array) {
			if ((head.startsWith(grantBudgetDto.getItemId().toString()))) {
				grantBudgetDto.setBudgetHeadCode(inputDto.getBudgetHeadCode()
						.concat(head.substring(grantBudgetDto.getItemId().toString().length())));
			}else {
				grantBudgetDto.setBudgetHeadCode(inputDto.getBudgetHeadCode());
			}
		}

		return grantBudgetDto;
	}

}
