
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { WorkflowDetailsGrantComponent } from '../workflow-details-grant/workflow-details-grant.component';
import { grantMessage } from 'src/app/shared/constants/grant/grant-msg.constants';
import { GrantSurrenderProcess } from 'src/app/models/grant/grant-surrender-process';
import { BrwoseDatagrant, ListValue } from 'src/app/models/grant/common-grant';
import { SurrenderService } from './service/surrender.service';
import { APIConst } from './constants/api.constants';
import { StorageService } from 'src/app/shared/services/storage.service';
import { UserPostList } from 'src/app/models/userPost-model';
import { SubmitDialogComponent } from '../grant-release-fd-to-administrative-department/submit-dialog/submit-dialog.component';
import { Subscription } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';


@Component({
    selector: 'app-grant-surrender-process',
    templateUrl: './grant-surrender-process.component.html',
    styleUrls: ['./grant-surrender-process.component.css']
})
export class GrantSurrenderProcessComponent implements OnInit {

    userName: any;
    errorMessages;
    createGrantVisible = false;
    public toggleButton = true;
    public toggleButton2 = true;


    demand_list: any[] = [];
    majorHead_list: any[] = [];
    subMajorHead_list: any[] = [];
    minorHead_list: any[] = [];
    subHead_list: any[] = [];
    userPostList: UserPostList[] = [];
    demandModel: any[] = [];
    detailHead_list: any[] = [];
    bflag:number;
    rflag:number;
    submitvalidation: boolean;
    finYea_list: ListValue[] = [];
    budgetEstimateType_list:any[]=[];
    schemeType:String='Partial';

    // budgetEstimateType_list: ListValue[] = [
    //     { value: '1', viewValue: 'Standing Charges' },
    //     { value: '2', viewValue: 'New Item Estimate' },
    //     { value: '3', viewValue: 'Item Continuous Estimate' },
    //     { value: '4', viewValue: 'New Work Estimate' },
    //     { value: '5', viewValue: 'Work in Progress' },
    // ];

    chargedVoted_list: ListValue[] = [
        { value: 'C', viewValue: 'Charged' },
        { value: 'V', viewValue: 'Voted' },
    ];
    item_list: any[] = [
        // { value: '1', viewValue: 'Item 1' },
        // { value: '2', viewValue: 'Item 2' },
    ];
    attachmentTypeCode: ListValue[] = [
        { value: '01', viewValue: 'Supporting Document' },
        { value: '02', viewValue: 'Sanction Order' },
        { value: '03', viewValue: 'Others' }
    ];

    Surrenderedlist: any[] = [
       
    ];
    brwoseData: BrwoseDatagrant[] = [{
        name: undefined,
        file: undefined,
        uploadedBy: ''
    }];
    fileBrowseIndex: number;
    displayedBrowseColumns = ['attachmentType', 'fileName', 'browse', 'uploadedFileName', 'uploadedBy', 'action'];
    dataSourceBrowse = new MatTableDataSource(this.brwoseData);
    schemeTypeCtrl: FormControl = new FormControl();
    schemeType_list: any[] = [
        { value: 'State', viewValue: 'State' },
        { value: 'CSS', viewValue: 'CSS' }
    ];
    ELEMENT_DATA: any[] = [
        // {
        //     expenditureDueprobable: '',
        //     budgetHead: '001:2404:00:800:01:00 (New Item Estimate) ', itemWorkName: 'Item 1', type: 'State',
        //     detailHeadDiscription: 'Object Class 1', budgetEstimate: 'Object Class 2',
        //     revisedEstimate: 9874.0000, supplementaryDemand: '', sanctionGrant: '',
        //     expenditureDueOnDate: 10.0000, avilableGrant: 748541.0000,
        //     amountToBeSurrender: '', surrenderOrderNo: '',
        //     remarksSurrender: '', surrenderTo: '', surrendersavingto: '', tooltip: '00:C1: Personel Services and Benefits'
        // },
        // {
        //     expenditureDueprobable: '',
        //     budgetHead: '001:2404:00:800:02:00 (New Item Estimate) ', itemWorkName: 'Item 2', type: 'CSS',
        //     detailHeadDiscription: 'Object Class 2', budgetEstimate: 'Object Class 3',
        //     revisedEstimate: 9774.0000, supplementaryDemand: '', sanctionGrant: '',
        //     expenditureDueOnDate: 40.0000, avilableGrant: 7441.0000,
        //     amountToBeSurrender: '', surrenderOrderNo: '',
        //     remarksSurrender: '', surrenderTo: '', surrendersavingto: '', tooltip: '00:C2: Personel Services and Benefits'
        // }
    ];

    displayedGrantColumns: string[] = [
        'srNo', 'budgetHead', 'itemWorkName', 'type', 'detailHeadDiscription', 'budgetEstimate',
        'revisedEstimate', 'supplementaryDemand', 'sanctionGrant',
        'expenditureDueOnDate', 'expenditureDueprobable',
        'avilableGrant', 'amountToBeSurrender',
        'remarksSurrender', 'surrenderTo', 'surrendersavingto', 'action'];
    grantdataSource = new MatTableDataSource<any>(this.ELEMENT_DATA);
    createGrantOrder: FormGroup;
    initiatiomdate = new Date((new Date()));
    revenueCaptialCtrl: FormControl = new FormControl();
    majorHeadCtrl: FormControl = new FormControl();
    demandCtrl: FormControl = new FormControl();
    subMajorHeadCtrl: FormControl = new FormControl();
    minorHeadCtrl: FormControl = new FormControl();
    subHeadCtrl: FormControl = new FormControl();
    detailHeadCtrl: FormControl = new FormControl();
    chargedVotedCtrl: FormControl = new FormControl();
    finYearCtrl: FormControl = new FormControl();
    budgetEstimateTypeCtrl: FormControl = new FormControl();
    itemCtrl: FormControl = new FormControl();
    SurrenderedCtrl: FormControl = new FormControl();
    attachmentTypeCtrl: FormControl = new FormControl();
    //item_list: any[] = [];
    budgetHeadCode: string;
    subDetailHeadMap = {};
    dropDownDisable = false;
    editIndex = 0;
  gridDisplay: boolean=false;
    roleId: any;
    deptId: any;
    coId=0;
    stateFlag: any;

    constructor(private fb: FormBuilder, private toaster: ToastrService,
        private router: Router, public dialog: MatDialog, private el: ElementRef,
        private surrenderService: SurrenderService,
        private activatedRoute: ActivatedRoute,
        private storageService: StorageService) { }

    //userPostList: UserPostList[] = [];
    currentUserData: string;
    status: string;

    menu: String;
    cudSub1: String;
    edpMsRolePer: String;
    cudSub2: String;
    btnVisiblity: boolean = true;
    //role: String;
    role: number;
    subscribeParams: Subscription;
    action: string;

    officeType: string;
    officeId: any;
    date: number = new Date().getFullYear();
    newDate = this.date + 1;
    currYear = this.date.toString() + '-' + this.newDate.toString();
    isCo: any;

    ngOnInit() {
        this.userName = this.storageService.get('userName');

        this.currentUserData = this.storageService.get('currentUser');
        this.userPostList = this.currentUserData['post'];


        this.officeType = this.storageService.get('userOffice')['officeTypeName'];
        this.officeId = this.storageService.get('userOffice')['officeId'];

        console.log(this.officeType);
        console.log(this.officeId);
        this.menu = this.storageService.get('menu');
        this.cudSub1 = this.menu['0'];
        this.edpMsRolePer = this.cudSub1['edpMsRolePermissionsDto'];
        this.cudSub2 = this.edpMsRolePer['0'];
        this.role = this.cudSub2['parentRoleId'];
        this.currentUserData = this.storageService.get('currentUser');
        // this.userPostList = this.currentUserData['post'];
       
        for(let i=0;i<this.menu.length;i++){
            if(this.menu[i]['menuId']==96)
                this.roleId=this.menu[i]['wfRoleId']['0']['wfRoleIds'];   
        }
        this.coId=this.roleId.find(id=>id === 43);

        this.deptId = this.storageService.get('userOffice')['departmentId'];
        
        console.log(this.userName);
        console.log(this.menu);
        console.log(this.cudSub1);
        console.log(this.edpMsRolePer);
        console.log(this.cudSub2);
        console.log(this.role);
        console.log(this.currentUserData);
        console.log(this.userPostList);
        this.formSubmitSurrender();
        this.getDemandDetailsList();

        //edit and view functionality
        this.subscribeParams = this.activatedRoute.params.subscribe(resRoute => {

            this.action = resRoute.action;
            this.status = resRoute.status;
            if (this.action === 'edit') {

            }
            if (this.action === 'view') {

            }
            if (this.action === 'edit' || this.action === 'view') {
                this.grantSurrenderHdrId = +resRoute.id;

                if (this.grantSurrenderHdrId) {
                    this.getHdrSdDetails(this.grantSurrenderHdrId);
                }
            }
           
        });
    }
    getHdrSdDetails(grantSurrenderHdrId) {


        this.surrenderService.getHdrSdData(grantSurrenderHdrId, APIConst.GRANT_SURRENDER.GET_DATA).subscribe((res) => {

            if (res && res['result']) {
                this.createGrantVisible = true;
                const headerData = res['result']['grantSurrenderHdrDto'];
                console.log("hdrdata" + headerData);
                this.createGrantOrder.patchValue({
                    surrenderNo: headerData.surrenderNo
                });
                // this.demandModel.forEach(x => {
                //     if (x.demandId == headerData.demandId) { 
                //                console.log("in frommonth") 
                //     this.createGrantOrder.get('demand').setValue(x);
                //            }
                // });
                this.grantdataSource.data = res['result']['grantSurrendeDtlsDto'];
                console.log("grantdataSource : " + this.grantdataSource.data);
            }
        },
            (err) => {
                this.toaster.error(err);
            }
        );


    }
    
    showSchemeRatio() {

    }
    formSubmitSurrender() {
        this.errorMessages = grantMessage;

        this.finYea_list.push({
            value: '1',
            viewValue: this.currYear,
        })
        this.createGrantOrder = this.fb.group({
            finYear: ['1'],
            revenueCapital: ['', Validators.required],
            demand: ['', Validators.required],
            majorHead: ['', Validators.required],
            subMajorHead: ['', Validators.required],
            minorHead: ['', Validators.required],
            subHead: ['', Validators.required],
            detailHead: ['', Validators.required],
            chargedVoted: ['', Validators.required],
            surrenderNo: ['', Validators.required],
            budgetEstimateType: ['', Validators.required],
            item: ['', Validators.required],
            schemeType: [''],
            availableGrant:['']
        });
        this.createGrantOrder.controls.finYear.patchValue[1];
        //this.createGrantOrder.controls.revenueCaptial.disable();
    }
    workflowDetails(): void {
        const dialogRef = this.dialog.open(WorkflowDetailsGrantComponent, {
            width: '1200px',
        });

        dialogRef.afterClosed().subscribe(result => {
            console.log('The dialog was closed');
        });
    }


    setParams() {

        const param = {
            finYear:this.currYear,
            rc:this.createGrantOrder.value.revenueCapital,
            chargedVotedFlag:this.createGrantOrder.value.chargedVoted=='V'?'V':'C',
            demandId: this.createGrantOrder.value.demand.demandId,
            majorHeadId: this.createGrantOrder.value.majorHead.id,
            subMajorHeadId: this.createGrantOrder.value.subMajorHead.subMajorHeadId,
            minorHeadId: this.createGrantOrder.value.minorHead.minorHeadId,
            subHeadId: this.createGrantOrder.value.subHead.subHeadId,
            detailHeadId: this.createGrantOrder.value.detailHead.detailHeadId,
            officeId: this.officeId,
            officeType: this.officeType,
            roleId:this.roleId,
            estimateType:this.createGrantOrder.value.budgetEstimateType.budgetEstimateType,
            deptId:this.deptId
           
        };
        return param;
    }

    setBudgetParams() {

        const param = {
            finYear:this.currYear,
            rc: this.createGrantOrder.value.revenueCapital[0],
            demandId: this.createGrantOrder.value.demand.demandId,
            majorHeadId: this.createGrantOrder.value.majorHead.id,
            subMajorHeadId: this.createGrantOrder.value.subMajorHead.subMajorHeadId,
            minorHeadId: this.createGrantOrder.value.minorHead.minorHeadId,
            subHeadId: this.createGrantOrder.value.subHead.subHeadId,
            detailHeadId: this.createGrantOrder.value.detailHead.detailHeadId,
            chargedVotedFlag:this.createGrantOrder.value.chargedVoted,
            estimateType:this.createGrantOrder.value.budgetEstimateType.budgetEstimateType,
            itemId:this.createGrantOrder.value.item,
            schemeType: this.createGrantOrder.value.schemeType,
            initialHeadCode: '',
            budgetHeadCode:this.budgetHeadCode,
            officeId: this.officeId,
            officeType: this.officeType,
            roleId:this.roleId,
           deptId:this.deptId,
           stateFlag:this.stateFlag
        };
        return param;
    }
    getDemandDetailsList() {

        var params = this.setParams();
        console.log(params);
        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_DEMAND_LIST).subscribe(


            res => {
                console.log(res);
                const resValue = this.checkSucessApiResponse(res);
                if (resValue === 1) {
                    this.demand_list = res['result'];
                    if (this.officeType === "Administrative Department" || this.officeType === "Head of Department (HOD)" && this.coId != 43) {
                        for (let i = 0; i < this.demand_list.length; i++) {
                            this.demandModel.push({
                                demandId: res['result'][i][0],
                                demandName: res['result'][i][1],
                                demandCode: res['result'][i][2]
                            });
                        }

                    }
                    else {
                        console.log("else");
                        this.demandModel = res['result'];
                        console.log(this.demandModel);
                    }
                    if (this.demand_list.length === 1) {
                        this.createGrantOrder.get('demand').setValue(this.demand_list[0]);
                        this.getMajorHeadList();
                    }
                }

            },
            err => {
                this.toaster.error(err['message']);
            }
        );

    }

    getMajorHeadList() {
        this.majorHead_list = [];
        this.subMajorHead_list = [];
        this.minorHead_list = [];
        this.subHead_list = [];
        this.detailHead_list = [];


        var params = this.setParams();
        console.log("major head" + params);
        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_MAJOR_HEAD_LIST).subscribe(
            res => {
                console.log(res);
                const resValue = this.checkSucessApiResponse(res);
                if (resValue === 1) {
                    this.majorHead_list = res['result'];
                    if (this.majorHead_list.length === 1) {
                        this.createGrantOrder.get('majorHead').setValue(this.majorHead_list[0]);
                        this.getSubMajorHeadList();
                    }

                }
            },
            err => {
                this.toaster.error(err['message']);
            }
        );

    }


    getSubMajorHeadList() {

        this.subMajorHead_list = [];
        this.minorHead_list = [];
        this.subHead_list = [];
        this.detailHead_list = [];
        this.budgetEstimateType_list=[];
        this.item_list=[];

        this.createGrantOrder.get('revenueCapital').setValue(this.createGrantOrder.value.majorHead.rc);
        console.log("submajorhead");
        console.log(this.createGrantOrder.value.revenueCaptial);

        var params = this.setParams();

        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_SUB_MAJOR_HEAD_LIST).subscribe(
            res => {
                const resValue = this.checkSucessApiResponse(res);
                if (resValue === 1) {
                    this.subMajorHead_list = res['result'];
                    if (this.subMajorHead_list.length === 1) {
                        this.createGrantOrder.get('subMajorHead').setValue(this.subMajorHead_list[0]);
                        this.getMinorHeadList();
                    }

                }

            },
            err => {
                this.toaster.error(err['message']);
            }
        );
    }

    getMinorHeadList() {
        this.minorHead_list = [];
        this.subHead_list = [];
        this.detailHead_list = [];
        this.budgetEstimateType_list=[];
        this.item_list=[];

        var params = this.setParams();
        console.log("minor head" + params);
        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_MINOR_HEAD_LIST).subscribe(
            res => {
                console.log(this.createGrantOrder);
                const resValue = this.checkSucessApiResponse(res);
                if (resValue === 1) {
                    this.minorHead_list = res['result'];
                    if (this.minorHead_list.length === 1) {
                        this.createGrantOrder.get('minorHead').setValue(this.minorHead_list[0]);
                        this.getSubHeadList();
                    }

                }

            },
            err => {
                this.toaster.error(err['message']);
            }
        );
    }



    getSubHeadList() {
        this.detailHead_list = [];
        this.budgetEstimateType_list=[];
        this.item_list=[];

        var params = this.setParams();
        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_SUB_HEAD_LIST).subscribe(
            res => {
                const resValue = this.checkSucessApiResponse(res);
                if (resValue === 1) {
                    this.subHead_list = res['result'];
                    if (this.subHead_list.length === 1) {
                        this.createGrantOrder.get('subHead').setValue(this.subHead_list[0]);
                        this.getDetailHeadList();
                    }

                }

            },
            err => {
                this.toaster.error(err['message']);
            }
        );
    }

    getDetailHeadList() {
        this.budgetEstimateType_list=[];
        this.item_list=[];

        var params = this.setParams();
        console.log(params);
        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_DETAIL_HEAD_LIST).subscribe(
            res => {
                const resValue = this.checkSucessApiResponse(res);
                if (resValue === 1)
                    this.detailHead_list = res['result'];
                if (this.detailHead_list.length === 1){
                    this.createGrantOrder.get('detailHead').setValue(this.detailHead_list[0]);
                    this.getBudgetEstimateTypeList();
                }
            },
            err => {
                this.toaster.error(err['message']);
            }
        );
    }



    getBudgetEstimateTypeList()
    {
        this.item_list=[]
     var params = this.setParams();
     console.log(params);
     this.createGrantOrder.get('schemeType').reset();
     this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_BUDGET_ESTIMATE).subscribe(
            res => {
                console.log(res);
                const resValue = this.checkSucessApiResponse(res);
                if(resValue===1)
                    this.budgetEstimateType_list= res['result'];
                    if(this.budgetEstimateType_list.length===1){
                        this.createGrantOrder.get('budgetEstimateType').setValue(this.budgetEstimateType_list[0]);
                        // this.getItemNameList();
                        this.getSchemeTye();
                    }
                },
            err => {
                this.toaster.error(err['message']);
            }
        );
    }


    getSchemeTye(){
        var params = this.setParams();
        this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_SCHEME_TYPE).subscribe(
            res => {
                console.log(res);
                const resValue = this.checkSucessApiResponse(res);
                this.stateFlag=res['result'];
                this.schemeType=res['result'];
                console.log(this.schemeType);
                if(res['result']==="state"){
                    this.createGrantOrder.get('schemeType').setValue(this.schemeType_list[0].value);
                    this.getItemNameList();
                    this.getAvailableGrant();
                }
                else if(res['result']==="CSS"){
                    this.createGrantOrder.get('schemeType').setValue(this.schemeType_list[1].value);
                    this.getItemNameList();
                }
            err => {
                this.toaster.error(err['message']);
            }
        }
        );
    }


    getItemNameList()
    {
    var params= this.setParams();
    console.log(params);
 
    this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_ITEM_LIST).subscribe(
      res => {
        console.log(res);
        const resValue=this.checkSucessApiResponse(res);
        if(resValue===1)
        {
          this.item_list= res['result'];
        //   if (this.item_list.length === 1) 
        //   this.createGrantOrder.get('item').setValue(this.item_list[0]);
        }
      },
      err => {
        this.toaster.error(err['message']);
      }
    );
  }


  getAvailableGrant(){
      var params=this.setParams();
      if(this.officeType==="Administrative Department")
      this.surrenderService.getData(params, APIConst.GRANT_SURRENDER.GET_AVAILABLE_GRANT_STATE).subscribe(
        res => {
          console.log(res);
          const resValue=this.checkSucessApiResponse(res);
              console.log(res['result']);
              this.createGrantOrder.get('availableGrant').setValue(res['result']);
        },
        err => {
            this.toaster.error(err['message']);
          }
        );
  }



    // item_list: any[] = [];
    itemMap = {};
    formSubmit() {
      this.gridDisplay=true;
      console.log(this.gridDisplay);
      this.item_list.forEach(item=>{
        this.itemMap[item.itemId]=item;
      })
        console.log(this.itemMap);
    console.log(this.createGrantOrder);
    this.budgetHeadCode= this.createGrantOrder.value.demand.demandCode   + ":" +
    this.createGrantOrder.value.majorHead.code + ":" +
    this.createGrantOrder.value.subMajorHead.subMajorHeadCode+ ":" +
    this.createGrantOrder.value.minorHead.minorHeadCode + ":" +
    this.createGrantOrder.value.detailHead.detailHeadCode + ":" +
    this.createGrantOrder.value.subHead.subHeadCode + ":" +
    this.createGrantOrder.value.budgetEstimateType +":"+
    this.createGrantOrder.value.schemeType;

    console.log(this.budgetHeadCode);
if(this.grantdataSource.data.length!=0)
{
  
        let copy=this.budgetHeadCode;
        let initialHeadCode: string='';
        console.log(this.createGrantOrder);
        this.createGrantOrder.value.item.forEach(value=>{
          console.log(this.subDetailHeadMap[copy.concat(this.itemMap[value].viewValue)]);
if((this.subDetailHeadMap[copy.concat(this.itemMap[value].viewValue)]==null 
|| this.subDetailHeadMap[copy.concat(this.itemMap[value].viewValue)]==undefined ))
initialHeadCode=initialHeadCode.concat(value+ this.itemMap[value].viewValue+ '-');


else
this.toaster.error("Selected Sub Head Wise Already Selected");
});
if(initialHeadCode!='')
this.budgetServiceCall(initialHeadCode);

}
else{  

let initialHeadCode: string='';
if(this.createGrantOrder.value.item)
{
    console.log(this.itemMap);
this.createGrantOrder.value.item.forEach(value=>{
initialHeadCode=initialHeadCode.concat( value+ this.itemMap[value].itemName+ '-');
console.log(initialHeadCode);
});}
this.budgetServiceCall(initialHeadCode);
}
}




budgetServiceCall(initialHeadCode)
{console.log("budget");
        var params=this.setBudgetParams();
       console.log(params);
      params.initialHeadCode=initialHeadCode;
      console.log(params);
        this.surrenderService.getData(params, APIConst. GRANT_SURRENDER.GET_BUDGET_HEAD_DATA).subscribe(
          res => {
            console.log("hello");
            console.log(res);
            const resValue=this.checkSucessApiResponse(res);
            console.log(resValue);
            console.log(res['result']);
            if(resValue===1)
            {
                
                
              console.log("hello");
              console.log(res['result']);
              if(this.grantdataSource.data.length>0  && !this.dropDownDisable)
              {
                console.log('hii');
                const data=this.grantdataSource.data;
                res['result'].forEach(value=>{
                  data.push(value);
                });
                this.grantdataSource.data=data;
                
              }
                else if(this.grantdataSource.data.length>0  && this.dropDownDisable)
                {
                  console.log("hi");
                  this.grantdataSource.data[this.editIndex]=res['result'][0];
                  this.grantdataSource= new MatTableDataSource<any>(this.grantdataSource.data);
                  
                }
              
              else{
                console.log("hi");
                this.grantdataSource.data=res['result'];
              }
              console.log(this.grantdataSource);
             
        let count =0;
              this.grantdataSource.data.forEach(value=>{
                  value.itemList.forEach(value=>{
                    
                value.sanctionGrantAmt=value.supplementaryAmt+value.beStateAmt;
                
                
            });
                this.subDetailHeadMap[value.budgetHeadCode]=value;
            
                this.showHideBeRe(value);
          
              })
           
            }
             
             else
             this.toaster.error(res['message']);
        },
        err => {
          this.toaster.error(err['message']);
         }
        );
    
        
}


    checkSucessApiResponse(res) {
        if ((res && res['status'] === 200 && res['result'].length > 0))
            return 1;
        else if (res && res['status'] === 200 && res['result'] == null) {
            res['message'] = "No Record Found";
            return 2;
        }

        else if (res && res['status'] !== 200)
            return 3;
    }
    //show hide
    showHideBeRe(value) {
        if (value.reStateAmt === 0) {
          this.rflag = 1;
    
          this.displayedGrantColumns = [
            'srNo', 'budgetHead', 'itemWorkName', 'type', 'detailHeadDiscription', 'budgetEstimate',
          'sanctionGrant',
        'expenditureDueOnDate', 'expenditureDueprobable',
        'avilableGrant', 'amountToBeSurrender',
        'remarksSurrender', 'surrenderTo', 'surrendersavingto', 'action'
          ];
    
        }
    
    
        if (value.reStateAmt != 0) {
          this.bflag = 1;
    
         
    
        }
    
        if ((this.rflag === 1 && this.bflag === 1) || (this.bflag === 1)) {
          this.displayedGrantColumns = [
            'srNo', 'budgetHead', 'itemWorkName', 'type', 'detailHeadDiscription', 'budgetEstimate',
        'revisedEstimate', 'supplementaryDemand', 'sanctionGrant',
        'expenditureDueOnDate', 'expenditureDueprobable',
        'avilableGrant', 'amountToBeSurrender',
        'remarksSurrender', 'surrenderTo', 'surrendersavingto', 'action'
          ];
        }
      }

    viewcreate() {
        this.toggleButton2 = true;
    }
    gotoListing() {
        this.router.navigate(['./grant/grant-surrender-process']);
    }
    onFileSelection(fileSelected) {
        if (fileSelected.target && fileSelected.target.files) {
            this.dataSourceBrowse.data[this.fileBrowseIndex].file = fileSelected.target.files[0];
        }
    }
    createDisplay() {
        this.createGrantOrder.controls.minorHead.setValidators;
        this.createGrantVisible = true;
    }
    openFileSelector(index) {
        this.el.nativeElement.querySelector('#fileBrowse').click();
        this.fileBrowseIndex = index;
    }

    addBrowse() {
        if (this.dataSourceBrowse) {
            const data = this.dataSourceBrowse.data[this.dataSourceBrowse.data.length - 1];
            if (data && data.name && data.file) {
                const p_data = this.dataSourceBrowse.data;
                p_data.push({
                    name: undefined,
                    file: undefined,
                    uploadedBy: ''
                });
                this.dataSourceBrowse.data = p_data;
            } else {
                this.toaster.error('Please fill the detail.');
            }
        }
    }
    decimalKeyPress(event: any) {
        const pattern = /^\d+(\.\d{0,4})?$/;
        const inputChar = String.fromCharCode(
            !event.charCode ? event.which : event.charCode
        );
        let tempstr = event.target.value;
        tempstr += inputChar;

        if (!pattern.test(tempstr)) {
            event.preventDefault();
            return false;
        }


    }

    decimalPoint(data, key) {
        if (data[key]) {
            data[key] = Number(data[key]).toFixed(4);
        }


    }


    deleteBrowse(index) {
        this.dataSourceBrowse.data.splice(index, 1);
        this.dataSourceBrowse = new MatTableDataSource(this.dataSourceBrowse.data);
    }
    grantSurrenderHdrId: number;
    surrenderSaveAsDraft(event = null) {


        let grantSurrenderHdrDto = this.createGrantOrder.value;

        grantSurrenderHdrDto.grantSurrenderHdrId = this.grantSurrenderHdrId;
        grantSurrenderHdrDto.createdBy = this.userName;
        grantSurrenderHdrDto.modifiedBy = this.userName;
        // grantSurrenderHdrDto.chargedVoted = "C";
        grantSurrenderHdrDto.createdByPost = this.userName;
        if (grantSurrenderHdrDto.finYear == 1) {
            grantSurrenderHdrDto.finYear = '2020-2021';
        }
        if (grantSurrenderHdrDto.revenueCapital == "Capital") {
            grantSurrenderHdrDto.revenueCapital = 'C';
        } else {
            grantSurrenderHdrDto.revenueCapital = 'R';
        }
        // grantSurrenderHdrDto.finYear = "2020-2021";
        // grantSurrenderHdrDto.revenueCapital = "R";
        //grantSurrenderHdrDto.surrenderNo = "12345";


        // this.grantdataSource.data.forEach(element => {
        //     element.itemList.forEach(element => {
        //         element.createdBy = this.userName;

        //     });

        // });
        let grantSurrendeDtlsDto = this.grantdataSource.data;
        var dto = {
            grantSurrenderHdrDto: grantSurrenderHdrDto,
            grantSurrendeDtlsDto: grantSurrendeDtlsDto

        }
        console.log(dto);
        this.surrenderService.saveData(dto, APIConst.GRANT_SURRENDER.SAVE_DATA).subscribe(
            res => {
                if ((res && res['status'] === 200)) {

                    this.toaster.success(res['message']);


                    // location.reload();

                }


                else if (res && res['status'] !== 200)
                    this.toaster.error(res['message']);

            },
            err => {
                this.toaster.error(err['message']);
            }
        );

    }

    surrenderSubmit(event = null) {
        const dialogRef = this.dialog.open(SubmitDialogComponent, {
            width: '400px'
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result === 'yes') {
                let grantSurrenderHdrDto = this.createGrantOrder.value;

                grantSurrenderHdrDto.grantSurrenderHdrId = this.grantSurrenderHdrId;
                grantSurrenderHdrDto.createdBy = this.userName;
                grantSurrenderHdrDto.modifiedBy = this.userName;
                // grantSurrenderHdrDto.chargedVoted = "C";
                grantSurrenderHdrDto.createdByPost = this.userName;
                if (grantSurrenderHdrDto.finYear == 1) {
                    grantSurrenderHdrDto.finYear = '2020-2021';
                }
                if (grantSurrenderHdrDto.revenueCapital == "Capital") {
                    grantSurrenderHdrDto.revenueCapital = 'C';
                } else {
                    grantSurrenderHdrDto.revenueCapital = 'R';
                }


                this.grantdataSource.data.forEach(element => {
                    element.itemList.forEach(element => {
                        element.createdBy = this.userName;

                    });

                });
                let grantSurrendeDtlsDto = this.grantdataSource.data;
                var dto = {
                    grantSurrenderHdrDto: grantSurrenderHdrDto,
                    grantSurrendeDtlsDto: grantSurrendeDtlsDto

                }
                console.log(dto);
                this.surrenderService.saveData(dto, APIConst.GRANT_SURRENDER.SUBMIT_DATA).subscribe(
                    res => {
                        if ((res && res['status'] === 200)) {
                            this.toaster.success(res['message']);

                            // location.reload();

                        }


                        else if (res && res['status'] !== 200)
                            this.toaster.error(res['message']);

                    },
                    err => {
                        this.toaster.error(err['message']);
                    }
                );

            }
        });
    }
    showDeleteConfirmPopup(element,index) {
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
        width: '360px',
        data: "DELETE"
        });
        
        dialogRef.afterClosed().subscribe(result => {
        if (result === 'yes') {
        this.delete(element,index);
        }
        })
        }

      delete(element, index){
        this.grantdataSource.data.splice(index, 1);
       
        
      
      this.grantdataSource = new MatTableDataSource<any>(this.grantdataSource.data);
     
      delete  this.subDetailHeadMap[element.budgetHeadCode];
      
      if(this.grantdataSource.data.length===0)
      this.submitvalidation=true;
      }

      
      getTotalBeStateAmt(): number {
        let totalBeStateAmt = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach(element => {
            element.itemList.forEach(d=>{
                totalBeStateAmt =
                totalBeStateAmt + Number(d.beStateAmt);
            })
         
        });
      
        return totalBeStateAmt;
      }

      getTotalReStateAmt(): number {
        let totalReStateAmt = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach((element) => {
            element.itemList.forEach(d=>{
                totalReStateAmt =
                totalReStateAmt + Number(d.reStateAmt);
            })
         
        });
        return totalReStateAmt;
      }

      getTotalExpAsOnDate(): number {
        let totalExpAsOnDate = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach((element) => {
            element.itemList.forEach(d=>{
                totalExpAsOnDate =
                totalExpAsOnDate + Number(d.expenditureAsonDtAmt);
            })
         
        });
        return totalExpAsOnDate;
      }

      getTotalSupplementaryDemand(): number {
        let totalSupplementaryDemand = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach((element) => {
            element.itemList.forEach(d=>{
              totalSupplementaryDemand =
              totalSupplementaryDemand + Number(d.supplementaryAmt);
            })
         
        });
        return totalSupplementaryDemand;
      }


      getTotalSanctionGrantAmt(): number {
        let totalSanctionGrantAmt = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach((element) => {
            element.itemList.forEach(d=>{
              totalSanctionGrantAmt =
              totalSanctionGrantAmt + Number(d.sanctionGrantAmt);
            })
         
        });
        return totalSanctionGrantAmt;
      }

      getTotalAvailableGrant(): number {
        let totalAvailableGrant = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach((element) => {
            element.itemList.forEach(d=>{
              totalAvailableGrant =
              totalAvailableGrant + Number(d.remainingStateAmt);
            })
         
        });
        return totalAvailableGrant;
      }

      getTotalFinalGrantAfterSurrender(): number {
        let totalFinalGrantAfterSurrender = 0;
        // tslint:disable-next-line:no-shadowed-variable
        this.grantdataSource.data.forEach((element) => {
            element.itemList.forEach(d=>{
              totalFinalGrantAfterSurrender =
              totalFinalGrantAfterSurrender + Number(d.finalGrantAfterSurrender);
            })
         
        });
        return totalFinalGrantAfterSurrender;
      }

setStateValue(data, key,i)
{
  console.log(data);
  console.log(key);
  console.log(i);
  data['finalGrantAfterSurrender']=data[key];
  console.log(data[key]);
  console.log(data['finalGrantAfterSurrender']);
  console.log(this.grantdataSource);
  // if(!data['stateWrongValue'])
  // {
    // this.grantdataSource.data[0].grantUnutilizedStateAmt=this.grantdataSource.data[0].grantUnutilizedStateAmt-data[key];
    // this.itemClassDatamap[data['budgetHeadCode']].itemWiseState=data[key];
  
    // this.detailHeadeDataSource.data.forEach(value=>{
      // if((this.itemClassDatamap[data['budgetHeadCode']].length===1)  && (value.budgetHeadCode===data['budgetHeadCode']))
        // value.grantToBeReleasedState=this.subDetailHeadMap[data['budgetHeadCode']].grantToBeReleasedState;

      //  if((value.budgetHeadCode===data['budgetHeadCode']))
        // value.itemWiseState=this.itemClassDatamap[data['budgetHeadCode']].itemWiseState;
      
    // });
  // }

}


expDueProbableValidation(data,i,j)
  {
    data['stateWrongValue'] =false;
    console.log("exp");
    console.log(data);
    console.log(this.grantdataSource.data);
    if (this.grantdataSource.data[i].itemList[j]['probableExpenditureAmt']>0)
    {
      console.log(this.grantdataSource.data[i].itemList[j]);
       if((this.grantdataSource.data[i].itemList[j]['probableExpenditureAmt']<= this.grantdataSource.data[i].itemList[j]['sanctionGrantAmt']-this.grantdataSource.data[i].itemList[j]['expenditureAsonDtAmt']))
        data['stateWrongValue'] =false;
     
    
      else{
        data['stateWrongValue'] =true;
        this.submitvalidation=true;
        this.toaster.error("error");
      }
       
         if(!data['stateWrongValue'])
         {
          this.submitvalidation=false;
        
         }
        

    }
  }


  amountToBeSurrenderValidation(data,i,j)
  {

    data['surrenderWrongValue'] =false;
    console.log("exp");
    console.log(data);
    if (this.grantdataSource.data[i].itemList[j]['surrenderAmt']>0)
    {
      console.log(this.grantdataSource.data[i].itemList[j]);
      if(this.officeType!="Administrative Department" || this.stateFlag!="State")
      {
       if((this.grantdataSource.data[i].itemList[j]['surrenderAmt']<= this.grantdataSource.data[i].itemList[j]['remainingStateAmt']))
        data['surrenderWrongValue'] =false;
      }
      else if(this.officeType==="Administrative Department" && this.stateFlag==="State")
      data['surrenderWrongValue'] =false;
    
      else{
        data['surrenderWrongValue'] =true;
        this.submitvalidation=true;
        this.toaster.error("error");
      }
       

         if(!data['surrenderWrongValue'])
         {
          if(this.officeType==='Administrative Department')
          this.grantdataSource.data[i].itemList[j]['finalGrantAfterSurrender']= this.grantdataSource.data[i].itemList[j]['sanctionGrantAmt']- this.grantdataSource.data[i].itemList[j]['surrenderAmt']
          if(this.officeType!='Administrative Department')
          this.grantdataSource.data[i].itemList[j]['finalGrantAfterSurrender']=this.grantdataSource.data[i].itemList[j]['remainingStateAmt']-this.grantdataSource.data[i].itemList[j]['surrenderAmt']+this.grantdataSource.data[i].itemList[j]['expenditureAsonDtAmt'];
          this.submitvalidation=false;
        
         }
        

    }
    else if(this.grantdataSource.data[i].itemList[j]['surrenderAmt']==0)
    {
      if(this.officeType==='Administrative Department')
      this.grantdataSource.data[i].itemList[j]['finalGrantAfterSurrender']= this.grantdataSource.data[i].itemList[j]['sanctionGrantAmt'];
      if(this.officeType!='Administrative Department')
      this.grantdataSource.data[i].itemList[j]['finalGrantAfterSurrender']=this.grantdataSource.data[i].itemList[j]['remainingStateAmt']+this.grantdataSource.data[i].itemList[j]['expenditureAsonDtAmt'];
      this.submitvalidation=false;
    }
  }

 
}
