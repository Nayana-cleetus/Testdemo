package gov.ifms.grant.service;

import java.math.BigDecimal;
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
import gov.ifms.edp.entity.EDPMsDepartmentEntity;
import gov.ifms.edp.service.EDPMsDepartmentService;
import gov.ifms.grant.converter.GrantBudgetHeadWiseUtilSmryConverter;
import gov.ifms.grant.dto.FdToCssDto;
import gov.ifms.grant.dto.GrantBudgetHeadWiseUtilSmryDto;
import gov.ifms.grant.dto.GrantSurrenderReqParamsDto;
import gov.ifms.grant.dto.SurrenderDto;
import gov.ifms.grant.dto.SurrenderGrantOutputDto;
import gov.ifms.grant.entity.GrantBudgetHeadWiseUtilSmryEntity;
import gov.ifms.grant.repository.GrantAdSubHeadwiseUtilSmryRepository;
import gov.ifms.grant.repository.GrantBudgetHeadWiseUtilSmryRepository;
import gov.ifms.grant.repository.GrantMsItemRepository;
import gov.ifms.grant.repository.ContigencyFdtoDeptSubHeadDRepository;
import gov.ifms.grant.repository.GrantFdToCssSubHeadDRepository;
import gov.ifms.grant.repository.GrantSurrenderTxnRepository;
//import gov.ifms.grant.repository.MsDepartmentRepository;
@Service
@Transactional
public class GrantBudgetHeadWiseUtilSmryServiceImpl implements GrantBudgetHeadWiseUtilSmryService {

	
	@Autowired
	GrantBudgetHeadWiseUtilSmryRepository   grantBudgetHeadWiseUtilSmryRepo;
	@Autowired
	GrantBudgetHeadWiseUtilSmryConverter  converter;
	
	@Autowired
	private BudgetMsObjectClassRepository repository;
	@Autowired
	private GrantAdSubHeadwiseUtilSmryRepository surrendertoRepository;
	@Autowired
	EDPMsDepartmentService  departmentService;
//	@Autowired
//	MsDepartmentRepository departmentRepository;
	@Autowired
	GrantFdToCssSubHeadDRepository cssRepository;
	@Autowired
	ContigencyFdtoDeptSubHeadDRepository ctnfRepository;
	@Autowired
	GrantSurrenderTxnRepository surrenderTxnRepository;
	
	@Autowired
	GrantMsItemRepository  msItemRepository;
	
	public List<GrantBudgetHeadWiseUtilSmryDto> getSubHeadWiseDetails(FdToCssDto grantDto) throws CustomException
	{
		try {
			List<GrantBudgetHeadWiseUtilSmryEntity> budgetHeadEntity = grantBudgetHeadWiseUtilSmryRepo.getSubHeadWiseDetails(
					grantDto.getFinYear(), grantDto.getRcFlag(), grantDto.getDepartmentId(),
					grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(),
					grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(),grantDto.getChargedVotedFlag(), grantDto.getItem());
			
			
			List<GrantBudgetHeadWiseUtilSmryDto> 	budgetHeadDto =converter.toDTO(budgetHeadEntity);
			
			budgetHeadDto.forEach(grantBudgetDto->{
				grantBudgetDto.setDepartmentName(departmentService.getMsDepartment(grantDto.getDepartmentId()).getDepartmentName());
				grantBudgetDto.setGrantReleseType(grantDto.getGrantReleseType());
				grantBudgetDto.setBudgetEstimateType(grantDto.getBudgetEstimateType());
				
				setBudgetHeadCode(grantBudgetDto,grantDto);
				
				
			});
			
			return budgetHeadDto;
		} catch (Exception e) {
			throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
		}

	}
	
	private GrantBudgetHeadWiseUtilSmryDto setBudgetHeadCode(GrantBudgetHeadWiseUtilSmryDto   grantBudgetDto ,FdToCssDto grantDto )
	{
		String [] array=	grantDto.getInitialHeadCode().split("-");
		for(String head:array)
		{
			if((head.startsWith(grantBudgetDto.getItemId().toString())))
				grantBudgetDto.setBudgetHeadCode(grantDto.getBudgetHeadCode().concat(head.substring(grantBudgetDto.getItemId().toString().length())));	
		}
		
		return grantBudgetDto;
	}
	
	private GrantBudgetHeadWiseUtilSmryDto setSurrenderBudgetHeadCode(GrantBudgetHeadWiseUtilSmryDto   grantBudgetDto ,SurrenderDto grantDto )
	{
		String [] array=	grantDto.getInitialHeadCode().split("-");
		for(String head:array)
		{
			if((head.startsWith(grantBudgetDto.getItemId().toString())))
				grantBudgetDto.setBudgetHeadCode(grantDto.getBudgetHeadCode().concat(head.substring(grantBudgetDto.getItemId().toString().length())));	
		}
		
		return grantBudgetDto;
	}
	
	
	
	
	  @Override
	  public List<GrantBudgetHeadWiseUtilSmryDto> getItemClassWiseDetails(FdToCssDto grantDto) throws CustomException { try {
	  List<GrantBudgetHeadWiseUtilSmryEntity> itemClassWiseEntity =
	  grantBudgetHeadWiseUtilSmryRepo.getItemClassWiseDetails(
	 grantDto.getFinYear(), grantDto.getRcFlag(), grantDto.getDepartmentId(),
	 grantDto.getDemandId(), grantDto.getMajorHeadId(),
	 grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(),
	 grantDto.getSubHeadId(),
	 grantDto.getDetailHeadId(),grantDto.getChargedVotedFlag(),
	 grantDto.getItem());
	 
	  
	  List<GrantBudgetHeadWiseUtilSmryDto> itemClassWiseDto =converter.toDTO(itemClassWiseEntity);
	  
	 itemClassWiseDto.forEach(grantBudgetDto->{
	 grantBudgetDto.setObjectClassName(repository.findById(grantBudgetDto.
	 getObjectClassId()).get().getObjectClassName());
	 grantBudgetDto.setObjectClassCode(repository.findById(grantBudgetDto.
	 getObjectClassId()).get().getObjectClassCode());
	 grantBudgetDto.setGrantReleseType(grantDto.getGrantReleseType());
	 grantBudgetDto.setDepartmentName(departmentService.getMsDepartment(grantDto.getDepartmentId()).getDepartmentName());
	 grantBudgetDto.setBudgetEstimateType(grantDto.getBudgetEstimateType());
	 setBudgetHeadCode(grantBudgetDto,grantDto);
	 
	 });
	 
	 return itemClassWiseDto; } catch (Exception e) { throw new
	 CustomException(ErrorResponse.ERROR_WHILE_FATCHING); }
	  }
	  
	  
	  
	  @SuppressWarnings("unlikely-arg-type")
	
	  @Override
	  public List<SurrenderGrantOutputDto> getSurrenderSubHeadWiseDetails(SurrenderDto grantDto)
	  throws CustomException {
	  
	  try {
	  List<SurrenderGrantOutputDto>  resultList = new ArrayList <> ();
	  
	  Map<String,BigDecimal> adMap=new HashMap<>();
	  
	  BigDecimal grantAmtState=cssRepository.findGrantAmtState(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
		 
	  BigDecimal grantAmtCenter=cssRepository.findGrantAmtCenter(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	  
	  BigDecimal findGrantAmtCTNFState=ctnfRepository.findGrantAmtCTNFState(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());

	  BigDecimal findGrantAmtCTNFCenter=ctnfRepository.findGrantAmtCTNFCenter(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());

	  List<BigDecimal> grantReleaseTillDateState= surrendertoRepository.getgrantreleaseStatetillDate(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
      List<BigDecimal> grantReleaseTillDateCenter=surrendertoRepository.getgrantreleaseCentertillDate(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	
	  BigDecimal grantReleaseTillDateStatefinal=grantReleaseTillDateState.get(0);
	  
	 
      BigDecimal grantReleaseTillDateCenterfinal = grantReleaseTillDateCenter.get(0);
   
      BigDecimal getgrantAmountStateADDDO=surrendertoRepository.getgrantAmountStateADDDO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
      BigDecimal getgrantAmountStateCODDO=surrendertoRepository.getgrantAmountStateCODDO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
     BigDecimal getgrantAmountcenterADDDO=surrendertoRepository.getgrantAmountCenterADDDO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	 BigDecimal getgrantAmountCenterCODDO=surrendertoRepository.getgrantAmountCenterCODDO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
    
	 BigDecimal grantAmountCenterADCO=surrendertoRepository.getgrantAmountCenterADCO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	 BigDecimal grantAmountCenterCOCO=surrendertoRepository.getgrantAmountCenterADCO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	 BigDecimal	 getgrantAmountStateCOCO=surrendertoRepository.getgrantAmountStateCOCO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	 BigDecimal grantAmountStateADCO=surrendertoRepository.getgrantAmountStateADCO(grantDto.getDeptId(), grantDto.getChargedVotedFlag(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId(), grantDto.getEstimateType());
	
	 
	  BigDecimal getSurrenderAmt=surrenderTxnRepository.findSurrenderAmt(grantDto.getDeptId(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getItemId());

	  List<String> surrenderto= new ArrayList<>(); 
	  System.out.println(grantDto.toString());
	  if (grantDto.getRoleId().contains(43))
	  {
		  
		  List<GrantSurrenderReqParamsDto>  x=surrendertoRepository.getFromADCOId(grantDto.getChargedVotedFlag(), grantDto.getDeptId(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(),grantDto.getItemId(),grantDto.getUserName());
		  List<GrantSurrenderReqParamsDto>  y=surrendertoRepository.getFromCOCOId(grantDto.getChargedVotedFlag(), grantDto.getDeptId(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(),grantDto.getItemId(),grantDto.getUserName());
		  if(!x.isEmpty()) {
			  for(GrantSurrenderReqParamsDto ad:x)
			  adMap.put(ad.getDeptOrOfficer(),ad.getGrantAmount());
		  }
		 if(!y.isEmpty()) {
			for(GrantSurrenderReqParamsDto ad:x)
				 adMap.put(ad.getDeptOrOfficer(),ad.getGrantAmount());
		 }
	  }
	  else if(grantDto.getOfficeType().equals("Drawing and Disbursing office (DDO)"))
	  {
		  List<GrantSurrenderReqParamsDto>  x=surrendertoRepository.getFromADDDOId(grantDto.getChargedVotedFlag(), grantDto.getDeptId(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(),grantDto.getItemId(),grantDto.getUserName());
		  List<GrantSurrenderReqParamsDto>  y=surrendertoRepository.getFromCODDOId(grantDto.getChargedVotedFlag(), grantDto.getDeptId(), grantDto.getDemandId(), grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(), grantDto.getSubHeadId(), grantDto.getDetailHeadId(),grantDto.getItemId(),grantDto.getUserName());
		  if(!x.isEmpty()) {
			  for(GrantSurrenderReqParamsDto ad:x)
			  adMap.put(ad.getDeptOrOfficer(),ad.getGrantAmount());
		  }
		 if(!y.isEmpty()) {
			for(GrantSurrenderReqParamsDto ad:x)
				 adMap.put(ad.getDeptOrOfficer(),ad.getGrantAmount());
		 }
		  
	  }
	  else
	  {
		  System.out.println("hey@!!!");
		  List<GrantSurrenderReqParamsDto> adList=surrendertoRepository.getFromDepartmentId(grantDto.getChargedVotedFlag(),grantDto.getDeptId(),grantDto.getDemandId(),grantDto.getMajorHeadId(),grantDto.getSubMajorHeadId(),grantDto.getMinorHeadId(),grantDto.getSubHeadId(),grantDto.getDetailHeadId(),grantDto.getItemId());
		  
		  if(!adList.isEmpty()) {
		  for(GrantSurrenderReqParamsDto ad:adList)
		  adMap.put(ad.getDeptOrOfficer(),ad.getGrantAmount());
		  
		  }
	  }
System.out.println(grantDto.getItemId());


	  List<GrantBudgetHeadWiseUtilSmryEntity> itemClassWiseEntity  = grantBudgetHeadWiseUtilSmryRepo.getSurrenderItemClassWiseDetail(grantDto.getFinYear(), grantDto.getRc(), grantDto.getDemandId(),
	  grantDto.getMajorHeadId(), grantDto.getSubMajorHeadId(), grantDto.getMinorHeadId(),
	  grantDto.getSubHeadId(), grantDto.getDetailHeadId(), grantDto.getChargedVotedFlag(), grantDto.getItemId());
	  
	  
	  List<GrantBudgetHeadWiseUtilSmryDto> SurrenderItemClassWiseDto=converter.toDTO(itemClassWiseEntity);
	  
	  if(grantDto.getRoleId().contains("43")) {
			 if("PARTIAL".equals(grantDto.getStateFlag())) {
				 if("CSS".equals(grantDto.getSchemeType())) {
					 BigDecimal totalCOAvailableGrantCenter=grantAmountCenterADCO.subtract(grantAmountCenterCOCO).subtract(getSurrenderAmt);
					 for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
						 grantBudgetHeadWiseUtilSmryDto.setRemainingCenterAmt(totalCOAvailableGrantCenter);
					}
				 }else {
					 BigDecimal totalCOAvailableGrantState=grantAmountStateADCO.subtract(getgrantAmountStateCOCO).subtract(getSurrenderAmt);
					 for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
						 grantBudgetHeadWiseUtilSmryDto.setRemainingStateAmt(totalCOAvailableGrantState);
					}
				 }
			 }
		 }
		 if(grantDto.getOfficeType().equals("Drawing and Disbursing office (DDO)")) {
			 if("PARTIAL".equals(grantDto.getStateFlag())) {
				 if("CSS".equals(grantDto.getSchemeType())) {
					 BigDecimal totalDDOAvailableGrantCenter=getgrantAmountcenterADDDO.add(getgrantAmountCenterCODDO).subtract(getSurrenderAmt);
					 for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
						 grantBudgetHeadWiseUtilSmryDto.setRemainingCenterAmt(totalDDOAvailableGrantCenter);
					}
				 }
				 else {
					 BigDecimal totalDDOAvailableGrantState=getgrantAmountStateADDDO.add(getgrantAmountStateCODDO).subtract(getSurrenderAmt);
					 for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
						 grantBudgetHeadWiseUtilSmryDto.setRemainingStateAmt(totalDDOAvailableGrantState);
					}
				 }
			 }
		 }
		 
		 
	  if(grantDto.getOfficeType().equals("Administrative Department")) {
		  if("PARTIAL".equals(grantDto.getStateFlag()))
		  {
			  if("CSS".equals(grantDto.getSchemeType())) {
				  
				  BigDecimal totalAvailableGrantCenter= grantAmtCenter.add(findGrantAmtCTNFCenter).subtract((BigDecimal) grantReleaseTillDateCenter).subtract(getSurrenderAmt);
				
				  for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
					  grantBudgetHeadWiseUtilSmryDto.setRemainingCenterAmt(totalAvailableGrantCenter);
				}
			  }
			  else {
				  BigDecimal totalAvailableGrantState=grantAmtState.add(findGrantAmtCTNFState).subtract((BigDecimal) grantReleaseTillDateState).subtract(getSurrenderAmt);
			
				  for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
					  grantBudgetHeadWiseUtilSmryDto.setRemainingStateAmt(totalAvailableGrantState);
				}
			  }
		  }
	 
	  }
	  
	  if("CSS".equals(grantDto.getSchemeType())) {
	 for (GrantBudgetHeadWiseUtilSmryDto grantBudgetHeadWiseUtilSmryDto : SurrenderItemClassWiseDto) {
	 grantBudgetHeadWiseUtilSmryDto.setBeStateAmt(grantBudgetHeadWiseUtilSmryDto.getBeCenterAmt());
	 grantBudgetHeadWiseUtilSmryDto.setReStateAmt(grantBudgetHeadWiseUtilSmryDto.getReCenterAmt());
	 }
	  }
	  SurrenderItemClassWiseDto.forEach(grantBudgetDto->{
	  grantBudgetDto.setObjectClassName(repository.findById(grantBudgetDto.getObjectClassId()).get().getObjectClassName());
	  grantBudgetDto.setObjectClassCode(repository.findById(grantBudgetDto.getObjectClassId()).get().getObjectClassCode());
	  grantBudgetDto.setGrantReleseType(grantDto.getGrantReleseType());
	  grantBudgetDto.setBudgetEstimateType(grantDto.getEstimateType());
	  grantBudgetDto.setSurrenderDeptTo(adMap);
	  setSurrenderBudgetHeadCode(grantBudgetDto,grantDto);

	  });
	  Map<String, List<GrantBudgetHeadWiseUtilSmryDto>> map = new HashMap<> ();
	  map=SurrenderItemClassWiseDto.stream().collect(Collectors.groupingBy(GrantBudgetHeadWiseUtilSmryDto::getBudgetHeadCode));
	  map.forEach((k,v)->{
          SurrenderGrantOutputDto test= new SurrenderGrantOutputDto();
          test.setBudgetHeadCode(k);
          test.setItemName(msItemRepository.findById(v.get(0).getItemId()).get().getItemName());
          test.setSchemeType(grantDto.getSchemeType());
          test.setItemList(v);
          test.setStateFlag(grantDto.getStateFlag());
//          test.setSurrenderToDept(FromDepartmentId);
          resultList.add(test);
         
         //resultList.add(test.getItemList().get(0).getSurrenderTo().set);
          });
		
	  System.out.println("final result list : " + resultList);
	  return resultList;
	  }catch (Exception e) {
		  System.out.println(e.getMessage());
	  throw new CustomException(ErrorResponse.ERROR_WHILE_FATCHING);
	  }
	  }
	  
	  
/*	  private List<String> getDeptNames(List<Long> filetredData) {
		 for (Long long1 : filetredData) {
			
		}
		  return null;
	  }
*/

	@Override
	public List<GrantBudgetHeadWiseUtilSmryDto> getSubHeadWiseDetailscontingency(FdToCssDto grantDto)
			throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, List<GrantBudgetHeadWiseUtilSmryDto>>> getItemClassWiseGroupByHead(FdToCssDto grantDto)
			throws CustomException {
		// TODO Auto-generated method stub
		return null;
	}
	 
	
}
