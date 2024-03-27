$('#uploadReportData').on('click', '.viewReport', function () {
	$('#viewReportModal').modal('show');
	$('#displayViewReport').attr('data',"readPdf?pdfFileName="+$(this).attr('id'));
	var testName=$('#testName_'+$(this).attr('value')).text();
	$('#testNameViewReport').html(testName);
});

function changeProfile(event){
	var fileName=event.target.files[0].name;
	/*var fileName=$("#changeProfilePicture");*/
	//$('#profilePictureImg').attr('src','readImage?url='+fileName);
	var clientId=$('#clientMasterId').html();
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{"clientId":clientId,"fileName":fileName},
		url : "client/changeProfilePicture",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#profilePictureImg').attr('src','readImage?url='+r);
		}
	});
}

function deleteProfilePicture(){
	var clientId=$('#clientMasterId').html();
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{"clientId":clientId},
		url : "client/deleteProfilePicture",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#profilePictureImg').attr('src','resources/assets/images/default-user.png');
		}
	});
}

function getUploadedReport(clientId) {
	var inputs = [];
	inputs.push('clientId=' + clientId);
	var str = inputs.join('&');
	jQuery
			.ajax({
				async : true,
				type : "POST",
				data : str + "&reqType=AJAX",
				url : "/IndusHealth/indus/clientUploadReport",
				timeout : 1000 * 60 * 5,
				catche : false,
				error : function() {
					alert("error");
				},
				success : function(r) {
					var divContent="";
					if(r.length>0)
					{	
						/*divContent=divContent+"<button title='' data-placement='top' data-toggle='tooltip' class='btn btn-primary' type='button' data-original-title='Tooltip on top'>Tooltip on top</button>";*/
						/*divContent=divContent+"<a href='#' data-toggle='tooltip' title='Hooray!'>Hover over me</a>";*/
						var index=1;
						var addedOn=[];
						for(var i=0;i<r.length;i++)
						{
							var editReportIconColor="color_red";
							if(r[i].editReportStatus==1){
								editReportIconColor="color_orange";
							}
							
							addedOn=(r[i].clientReportHead.addedOn).split(" ");
							var testOrPackage = "";
							if(r[i].testMaster!=null){
								testOrPackage = "test_"+r[i].testMaster.testId+"_"+r[i].clientReportHead.centreMaster.centreId;
							}
							if(r[i].packageMaster!=null){
								testOrPackage = "package_"+r[i].packageMaster.packageId+"_"+r[i].clientReportHead.centreMaster.centreId;
							}
							if(r[i].reportVerificationSet.length>0){
								if(r[i].reportVerificationSet[0].verifyComment!=null){
									//alert(r[i].clientReportHead.centreMaster.centreName);  // readPdf?pdfFileName=
									divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'><a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].verifyComment+"'><i class='fa fa-comment color_green' data-placement='right' data-toggle='tooltip' class='btn btn-primary' type='button' data-original-title='"+r[i].reportVerificationSet[0].rejectComment+"'></i></a></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
									+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
									+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
									+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
									/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
									+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
									+"</div></td></tr>";
								}
								if(r[i].reportVerificationSet[0].rejectComment!=null){
									//console.log(r[i].reportVerificationSet[0].rejectComment);
									divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'><a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].rejectMaster.rejectReason+"'><i class='fa fa-exclamation-triangle "+editReportIconColor+"'></i></a> <a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].rejectComment+"'><i class='fa fa-comment "+editReportIconColor+"'></i></a></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
									+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
									+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
									+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
									/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
									+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
									+"</div></td></tr>";
									$('#verificationFailed').removeClass('hidden');
								}
							}
							else{
								divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
								+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
								+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
								+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
								/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
								+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
								+"</div></td></tr>";
							}
							index++;
						}
					}
					else
					{
						divContent=divContent+"<b>No Reports Available</b>";
					}	
					$("#uploadReportData").html(divContent);
					if($('#clientLoginUserType').html()==1){
						//alert($('#clientLoginUserType').html());
						$('.reportUploader').hide();
						$('.reportUploaderDate').hide();
					}
					else{
						$('.reportUploader').show();
						$('.reportUploaderDate').show();
					}
					getUserAccess();
				}
			});

	return true;
}

$("#uploadAllPatientReport").bind("click", (function () {
	$(".uploadSingleReport").trigger("click");
}));

$(document).on('click','.removeSingleReport',function(){
	$('#reportLength').html($('.template-upload').length);
	var fileId = $(this).attr("data-fileid");
	$(".removeReport_"+fileId).trigger("click");
	var id = $(this).parents()[1];//For getting second parent
	$(id).remove();
});

//$("#removeAllPatientReport").bind("click", (function () {
	function removePatientReport(){
		$(".removeSingleReport").trigger("click");
		$('#reportLength').html("0");
};

function editReport(clientReportLineId,testOrPackage){
	$('#uploadReportModal').modal('show');
	$('#addNewVisitDiv').hide();
	$('#displayReportTableBody').hide();
	
	$('.clientUploadReportHide').show();
	$('.clientUploadReportShow').hide();
	$('#saveSelfClientReportBtn').hide();
	$('#packageDetails').show();
	
	//$('#savePatientReportBtn').hide();
	$('.editReportPopUp').show();
	$('.uploadReport').hide();
	$('#displayReportDiv').removeClass('col-md-9').addClass('col-md-12');
	$('#reportDiv').html("");
	$('#clientReportLineIdEdit').html(clientReportLineId);
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{"clientReportLineId":clientReportLineId},
		url : "client/getClientReportByClientReportLineId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				$('#uploadReportModal').modal('hide');
					alert("Sorry you don't have access to edit report");
    			}
    			else{
    				//getUploadedReport(clientId);
    				//var str="<tr><td></td><td>"+r.clientUploadReportSet[0].reportDescription+"</td><td>"+r.centreMaster.centreName+"</td><td>"+r.clientUploadReportSet[0].reportDate+"</td><td>"+r.clientUploadReportSet[0].filePath+"</td><td></td><td><span class='btn btn-danger btn-xs' onclick='removeReport()'><i class='glyphicon glyphicon-ban-circle'></i><span>Remove</span></span></td></tr>";
    				var filePath = r.clientUploadReportSet[0].filePath;
					var avoid = filePath.split("_")[0];
					var avoid1 = filePath.split("_")[1];
					var avoided = filePath.replace(avoid,'');
					var avoided1 = avoided.replace(avoid1,'');
					var fileName = avoided1.slice(2);
    				var str = "<tr class='template-upload'><td><Select id='editReportSel' onchange='testPackageList(this)' class='testPackageSelectList testPackageSelect_1' style='width: 100px; margin-right: 15px;'><option val='package'>Package</option><option val='test'>Test</option></select></td>"
    						+"<td><select class='testSelectList testSelect_1' style='width: 100px; margin-right: 15px;'></td><td><select class='centerSelectList centreSelect_1' style='width: 100px; margin-right: 15px;'></td>"
    						+"<td><input style='width:67%;' class='form-control datepicker reportDate reportDate_1' type='text' value="+r.clientUploadReportSet[0].reportDate+"></td>"
    						+"<td><p id='oldReport_1' style='display:none;'>"+r.clientUploadReportSet[0].filePath+"</p><p class='name fileName_1'>"+fileName+"</p></td><td><p class='size'></p></td><td><span class='btn btn-danger btn-xs' onclick='removeReport()'><i class='glyphicon glyphicon-ban-circle'></i><span>Remove</span></span></td></tr>";
    				$('#reportDiv').html(str);
    				setTimeout(function(){getListOfCentres();}, 100);
    				if(testOrPackage.split("_")[0]=="test"){
    					setTimeout(function(){listOfTestMasterReport("1");}, 100);
    					$('.testPackageSelect_1').val("Test");
    				}
    				else{
    					setTimeout(function(){listOfPackageMasterReport("1");}, 100);
    					$('.testPackageSelect_1').val("Package");
    				}
    				setTimeout(function(){
    					$(".centreSelect_1").val("centre_"+testOrPackage.split("_")[2]);
    					$(".testSelect_1").val(testOrPackage.split("_")[1]);
    				}, 800);
    				//FormElements.init();
    				//For disable future dates
    				$('.reportDate').datepicker({
    			        format: 'dd-mm-yyyy',
    			        endDate: '+0d',
    			        autoclose: true
    			    });
    			}
		}
	});
}

function removeReport(){
	$('#reportDiv').html("");
}

$('#uploadReportBtn').click(function(){
	$('#uploadReportModal').modal('show');
	$('#k').html("0");
	$('.editReportPopUp').hide();
	$('#displayReportTableBody').show();
	$('.uploadReport').show();
	$('#displayReportDiv').removeClass('col-md-12').addClass('col-md-9');
	$('#reportDiv').html("");
	$('#packageNameUploadReportPopUp').html($('.packageNameSpan').html());
	$('#reportLength').html("");
	$('#savePatientReportBtn').show();
	
	$('.clientUploadReportHide').show();
	$('.clientUploadReportShow').hide();
	$('#saveSelfClientReportBtn').hide();
	$('#packageDetails').show();
	
	$('#addNewVisitDiv').show();
	
	$('#reportLength').html($('#displayReportTableBody tr').length);
	$('#clientReportLineIdEdit').html("");
	var packageId=$('#visitSelectOnMyReportPage :selected').attr('class').split("_")[2];
	//listOfPackageMasterReport(0);
	//alert(packageId);
	$('#popUpTestPackageList').val(packageId);
});

function editReportFile(event){
	$('#reportDiv').html("");
	$('#savePatientReportBtn').show();
}

function deleteReport(clientReportLineId,clientId){
	var visitId=($('#visitSelectOnMyReportPage').val()).split("_")[1];
	var r = confirm("Are you sure you want to delete report?");
    if (r == true) {
    	jQuery.ajax({
    		async : true,
    		type : "POST",
    		data :{
    			"clientReportLineId" : clientReportLineId,
    			"clientId" : clientId,
    			"visitId" : visitId
    		},
    		url : "client/deletePatientReport",
    		catche : false,
    		error : function() {
    			alert("error");
    		},
    		success : function(r) {
    			if(r=="true"){
    			//getUploadedReport(clientId);
    			getVisitByVisitId(visitId);
    			alert("Report Deleted Successfully");
    			}else if(r=="verified"){
    				alert("Once Report Is Verified It Cannot Be Deleted");
    			}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}
    		}
    	});
    } 
}

function getPatientTest(visitId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{"visitId":visitId},
		url : "client/getPatientTest",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var packageId=$('.packageNameSpan').attr('id');
			var packageName=$('.packageNameSpan').html();
			divContent=divContent+"<option value='test_null_"+packageId+"'>"+packageName+"</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='test_"+r[i].testId+"_"+packageId+"'>"+r[i].testDescription+"</option>";
			}
			/*var reports=$('.template-upload').length;
			$('.testSelectList').addClass();*/
			$('.testSelectList').html(divContent);
			//alert(packageId);
			$('.testSelectList').val("test_null_"+packageId+"");
			
		}
	});
}

function getListOfCentres(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfCentres",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='centre_"+r[i].centreId+"'>"+r[i].centreName+"</option>";
			}
			$('.centerSelectList').html(divContent);
			if($('.centerNameSpan').attr('id')!='0'){
				$('.centerSelectList').val("centre_"+$('.centerNameSpan').attr('id'));
			}
			$('.centerSelectList').removeClass("centerSelectList");
		}
	});
}

function getPatientCenter(clientId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{"clientId":clientId},
		url : "client/getPatientCenter",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			//$('#centre_'+r.centreId).val('centre_'+r.centreId);
			if(r.checkUpMasterSet!=null && r.checkUpMasterSet[0]!=null){
				$('.checkUpDate').html(r.checkUpMasterSet[0].checkUpDate);
				$('.checkUpDate').attr("id","checkUpId_"+r.checkUpMasterSet[0].checkUpId);
			}
			/*$('.patientCenter').addClass("centreId_"+r.centreId);
			$('.patientCenter').val(r.centreName);*/
		}
	});
}

/*$('#savePatientReportBtn').click(function(){*/

function savePatientReport(){
	if($('#visitSelect').val()=="newVisit"){
		alert("Please save visit first");
	}
	else{
	var fileData=[];
	var length=$('.template-upload').length;//$('#k').html();
	//alert("length:"+length);
    //for(var i=1;i<=length;i++){
	$('.name').map(function() {
		var i = $(this).attr("class").split("_")[1];
    	//var test=$('.testSelect_'+i).val();
    	var testPackage=$('.testPackageSelect_'+i).val();
    	if(testPackage!=null && testPackage!='' && testPackage!=undefined)
    	{
    		//var centre=$('.centreSelect_'+i).val();
        	//var checkUpDate=$('.checkUpDate_'+i).attr('id');
        	var fileName=$('.fileName_'+i).html();
        	var id=$('.testSelect_'+i).val();
        	var centreId=($('.centreSelect_'+i).val()).split("_")[1];
        	var checkUpId=($('#visitSelect').val()).split("_")[1];//checkUpDate.split("_");
        	//var visitDate=$('.checkUpDate_'+i).html();
        	var testName=$(".testSelect_"+i+" option:selected").text();
        	var reportDate=$('.reportDate_'+i).val();
        	var clientReportLineId=0;
        	if($('#clientReportLineIdEdit').html()!=null && $('#clientReportLineIdEdit').html()!=""){
        		clientReportLineId=$('#clientReportLineIdEdit').html();
        	}
        	fileData.push(testPackage+"#"+centreId+"#"+checkUpId+"#"+fileName+"#"+$('#clientMasterId').html()+"#"+testName+"#"+id+"#"+clientReportLineId+"#"+reportDate);
    	}
	});
    if($('.template-upload').length>0){
    //$(".uploadSingleReport").trigger("click");
    	$('#pleaseWait').show();
    	var oldReport = $("#oldReport_1").html();
    jQuery.ajax({
		async : false,
		type : "POST",
		data :{"fileData":fileData,"oldReport":oldReport},
		url : "client/savePatientreport",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				$('#pleaseWait').hide();
				//getUploadedReport($('#clientMasterId').html());
				$('#uploadReportModal').modal("hide");
				getVisitByVisitId($('.lastCheckUpDate').attr('id'));
				$('#clientReportLineIdEdit').html("");
				alert("File Uploaded Successfully");
    			}
    			else{
    				$('#pleaseWait').hide();
    				alert("Sorry you don't have access to upload report");
    			}
		}
	});
    }
    else{
    	alert("Please select atleast one file");
    }
	}
}

$('#editFirstName').click(function(){
	$('#displayFirstNameTd').hide();
	$('#editFirstNameTd').show();
});

$('#editMiddleName').click(function(){
	$('#displayMiddleNameTd').hide();
	$('#editMiddleNameTd').show();
});

$('#editLastName').click(function(){
	$('#displayLastNameTd').hide();
	$('#editLastNameTd').show();
});

$('#editBloodGroup').click(function(){
	$('#displayBloodGroupTd').hide();
	$('#editBloodGroupTd').show();
	$('#bloodGroupList').val($('#bloodGroupHidden').html());
});

$('#editAddress1').click(function(){
	$('#displayAddress1Td').hide();
	$('#editAddress1Td').show();
});

$('#editAddress2').click(function(){
	$('#displayAddress2Td').hide();
	$('#editAddress2Td').show();
});

$('#editAddress3').click(function(){
	$('#displayAddress3Td').hide();
	$('#editAddress3Td').show();
});

$('#editCityName').click(function(){
	$('#displayCityNameTd').hide();
	$('#editCityNameTd').show();
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfCitys",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>select city</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='city_"+r[i].cityId+"'>"+r[i].cityName+"</option>";
			}
			$('#cityList').html(divContent);
			var currentCityId=$('#currentCityId').html();
			$('#cityList').val("city_"+currentCityId);
		}
	});
});

$('#editStateName').click(function(){
	$('#displayStateNameTd').hide();
	$('#editStateNameTd').show();
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfStates",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>select state</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='state_"+r[i].stateId+"'>"+r[i].stateName+"</option>";
			}
			$('#stateList').html(divContent);
			var currentStateId=$('#currentStateId').html();
			$('#stateList').val("state_"+currentStateId);
		}
	});
});

$('#editCountryName').click(function(){
	$('#displayCountryNameTd').hide();
	$('#editCountryNameTd').show();
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfCountrys",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>select country</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='country_"+r[i].countryId+"'>"+r[i].countryName+"</option>";
			}
			$('#countryList').html(divContent);
			var currentCountryId=$('#currentCountryId').html();
			$('#countryList').val("country_"+currentCountryId);
		}
	});
});

$('#editPincode').click(function(){
	$('#displayPincodeTd').hide();
	$('#editPincodeTd').show();
});

$('#editMobNo').click(function(){
	$('#displayMobNoTd').hide();
	$('#editMobNoTd').show();
});

$('#editLandlineNo').click(function(){
	$('#displayLandlineNoTd').hide();
	$('#editLandlineNoTd').show();
});

$('#editDOB').click(function(){
	$('#displayDOBTd').hide();
	$('#editDOBTd').show();
});

$('#editNomineeFirstName').click(function(){
	$('#displayNomineeFirstNameTd').hide();
	$('#editNomineeFirstNameTd').show();
});

$('#editNomineeMiddleName').click(function(){
	$('#displayNomineeMiddleNameTd').hide();
	$('#editNomineeMiddleNameTd').show();
});

$('#editNomineeLastName').click(function(){
	$('#displayNomineeLastNameTd').hide();
	$('#editNomineeLastNameTd').show();
});

$('#editNomineeDOB').click(function(){
	$('#displayNomineeDOBTd').hide();
	$('#editNomineeDOBTd').show();
});

$('#editNomineeRelation').click(function(){
	$('#displayNomineeRelationTd').hide();
	$('#editNomineeRelationTd').show();
});

$('#editHeightWeight').click(function(){
	$('#displayHeightWeightTd').hide();
	$('#editHeightWeightTd').show();
	$('#genderSelect').val($('#currentGender').html());
});

$('#editEmailId').click(function(){
	$('#displayEmailIdTd').hide();
	$('#editEmailIdTd').show();
});

$('#editGender').click(function(){
	$('#displayGenderTd').hide();
	$('#editGenderTd').show();
	$('#genderSelect').val($('#currentGender').html());
});

$('#editHeight').click(function(){
	$('#displayHeightTd').hide();
	$('#editHeightTd').show();
});

$('#editWeight').click(function(){
	$('#displayWeightTd').hide();
	$('#editWeightTd').show();
});

$('#editSmsIsOn').click(function(){
	$('#displaySmsIsOnTd').hide();
	$('#editSmsIsOnTd').show();
	$('#smsIsOnSelect').val($('#currentSmsIsOn').html());
});

function genderChange(){
	$('#currentGender').html($('#genderSelect').val());
}

function smsIsOnChange(){
	$('#currentSmsIsOn').html($('#smsIsOnSelect').val());
	updateSmsIsOnOffFlag();
}




//$('#updateSmsIsOn').click(function(){
function updateSmsIsOnOffFlag(){
	
	var r = confirm("Are you sure you want to change SMS Status?");
	if (r == true) {
	var inputs = [];
	
	inputs.push('clientId=' + $('#clientMasterId').html());
	inputs.push('contactFlag=' + $('#currentSmsIsOn').html());
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/updateSmsIsOnOffFlag",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
		refreshDemoghraphic();
			setTimeout(function(){ getUserAccess(); }, 1000);
			alertify.success("SMS Status Updated Successfully");
		}
	});
}
};




$('#updateUserProfile').click(function(){
	var cityId;
	if($('#cityList').val()!=null){
		cityId=($('#cityList').val()).split("_")[1];
	}
	else{
		cityId=$('#currentCityId').html();
	}
	
	var stateId;
	if($('#stateList').val()!=null){
		stateId=($('#stateList').val()).split("_")[1];
	}
	else{
		stateId=$('#currentStateId').html();
	}
	
	var countryId;
	if($('#countryList').val()!=null){
		countryId=($('#countryList').val()).split("_")[1];
	}
	else{
		countryId=$('#currentCountryId').html();
	}

	var inputs = [];
	inputs.push('firstName=' + $('#editFirstNameInput').val());
	inputs.push('middleName=' + $('#editMiddleNameInput').val());
	inputs.push('lastName=' + $('#editLastNameInput').val());
	inputs.push('bloodGroup=' + $('#bloodGroupList').val());
	inputs.push('addressLine1=' + $('#editAddress1Input').val());
	inputs.push('addressLine2=' + $('#editAddress2Input').val());
	inputs.push('addressLine3=' + $('#editAddress3Input').val());
	inputs.push('cityId=' + cityId);
	inputs.push('stateId=' + stateId);
	inputs.push('countryId=' + countryId);
	inputs.push('pincode=' + $('#editPincodeInput').val());
	inputs.push('mobNo=' + $('#editMobNoInput').val());
	inputs.push('landlineNo=' + $('#editLandlineNoInput').val());
	inputs.push('clientId=' + $('#clientMasterId').html());
	inputs.push('birthDate=' + $('#birthDate').val());
	inputs.push('nomineeFirstName=' + $('#editNomineeFirstNameInput').val());
	inputs.push('nomineeMiddleName=' + $('#editNomineeMiddleNameInput').val());
	inputs.push('nomineeLastName=' + $('#editNomineeLastNameInput').val());
	inputs.push('nomineeBirthDate=' + $('#nomineeBirthDate').val());
	inputs.push('nomineeRelation=' + $('#editNomineeRelationInput').val());
	inputs.push('clientGender=' + $('#currentGender').html());
	inputs.push('clientHeight=' + $('#heightUnit').val()+"#"+$('#editHeightInput').val()+"#"+$('#editHeightFoot').val()+"#"+$('#editHeightInches').val());
	inputs.push('clientWeight=' + $('#weightUnit').val()+"#"+$('#editWeightInput').val());
	inputs.push('emailId=' + $('#editEmailIdInput').val());
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/updateClientProfile",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			refreshDemoghraphic();
			setTimeout(function(){ getUserAccess(); }, 1000);
			alert("Profile Updated Successfully");
		}
	});
});

$("#cityList").change(function() {
	var id=$("#cityList").val();
	var cityId=id.split("_");
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {"cityId":cityId[1]},
		url : "client/getCityByCityId",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			//alert("citys");
		}
	});
});

$("#stateList").change(function() {
	var id=$("#stateList").val();
	var stateId=id.split("_");
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {"stateId":stateId[1]},
		url : "client/getCityByStateId",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>select city</option>";
			for(var i=0;i<r.cityMasterList.length;i++){
				divContent=divContent+"<option value='city_"+r.cityMasterList[i].cityId+"'>"+r.cityMasterList[i].cityName+"</option>";
			}
			$('#cityList').html(divContent);
			$('#displayCityNameTd').hide();
			$('#editCityNameTd').show();
		}
	});
});

$("#countryList").change(function() {
	var id=$("#countryList").val();
	var countryId=id.split("_");
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {"countryId":countryId[1]},
		url : "client/getStateByCountryId",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>select state</option>";
			for(var i=0;i<r.stateMasterList.length;i++){
				divContent=divContent+"<option value='state_"+r.stateMasterList[i].stateId+"'>"+r.stateMasterList[i].stateName+"</option>";
			}
			$('#stateList').html(divContent);
			$('#displayStateNameTd').hide();
			$('#editStateNameTd').show();
			
			$('#displayCityNameTd').hide();
			$('#editCityNameTd').show();
			$('#cityList').html("<option value=''>select city</option>");
		}
	});
});

function refreshDemoghraphic(){
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	jQuery.ajax({
		type : "GET",
		data : {
			//userId : userId
			"clientId" : clientId
		},
		url : "client/displayPatientDemoghraphic",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			$('#currentPageId').html("3");
			getClientTimeline($('#clientMasterId').html());
			listOfVisitTypeMasterDropDown();
			getVisitDateList($('#clientMasterId').html());
			getUserAccess();
			//setTimeout(function(){ getUserAccess(); }, 10);
		}
	});
}

function addNewVisit(){
	var r = confirm("Do you want to add new visit?");
    if (r == true) {
    	var optionLength=($("#visitSelect option").length)+1;
    	$('#visitSelect').append('<option value="newVisit" selected="selected">Visit '+optionLength+'</option>');
    	$('#visitDate').removeClass('hidden');
    	$('#showVisitDate').hide();
    	$('#displayReportTableBody').html("");
    	$('#reportLength').html("");
    	$('#visitTypeMasterSelect').removeAttr("disabled");
    	
    	$('#popUpCenterList').removeAttr("disabled");
    	//$('#forNewVisit').removeAttr("disabled");
    	$('#popUpTestPackageList').removeAttr("disabled");
    	//$('#popUpTestPackageList').val("");
    	
    	//For disable future dates
		$('#visitDate').datepicker({
	        format: 'dd-mm-yyyy',
	        endDate: '+0d',
	        autoclose: true
	    });
    }
}

function saveNewVisit(){
	var clientId=$('#clientMasterId').html();
	var visitDateClass=$('#visitDate').attr('class');
	var visitTypeId=$('#visitTypeMasterSelect').val();
	var centerId=$('#popUpCenterList').val().split("_")[1];
	var testPackageId=$('#popUpTestPackageList').val();
	var testPackage=$('#forNewVisit').val();
	if (visitDateClass.indexOf("hidden") > -1) {
		 alert('Please add new visit first');
	 } 
	else {
		 var visitSelected=$('#visitSelect').val();
			var visitDate=$('#visitDate').val();
			if(visitSelected!="newVisit"){
				alert("Please select new added visit date");
			}
			else if(visitDate==null || visitDate==""){
				alert("Please select visit date");
			}
			else if(testPackageId==""){
				alert("Please select test or package");
			}
			else if(visitDate!=null && visitDate!="" && visitSelected!=null){
				jQuery.ajax({
					async : true,
					type : "POST",
					data :{
						"clientId" : clientId,
						"visitDate" : visitDate,
						"visitTypeId" : visitTypeId,
						"centerId" : centerId,
						"testPackageId" : testPackageId,
						"testPackage" : testPackage
					},
					url : "client/saveNewVisit",
					catche : false,
					error : function() {
						alert("error");
					},
					success : function(r) {
						if(r=="true"){
							$('#visitDate').addClass('hidden');
							$('#visitDate').val("");
					    	$('#showVisitDate').show();
					    	getVisitDateList($('#clientMasterId').html());
					    	$('#uploadReportData').html("");
					    	$('#visitTypeMasterSelect').attr('disabled','disabled');
					    	
					    	$('#popUpCenterList').attr('disabled','disabled');
					    	$('#forNewVisit').attr('disabled','disabled');
					    	$('#popUpTestPackageList').attr('disabled','disabled');
					    	
							alert("Visit Saved Successfully");
			    		}
			    			else{
			    				alert("Sorry you don't have access to upload report");
			    			}
					}
				});
			}
	 }
}

function listOfTestMasterReport(id){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOftestMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var newVisitDivContent="";
			newVisitDivContent=newVisitDivContent+"<option value=''>Select Test</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].testId+"'>"+r[i].testDescription+"</option>";
				newVisitDivContent=newVisitDivContent+"<option value='"+r[i].testId+"'>"+r[i].testDescription+"</option>";
			}
			if(id==0){
				$('.assignTestPackage').html(divContent);
			}
			else{
				if(id=="forNewVisit"){
					$('.testSelect_forNewVisit').html(newVisitDivContent);
				}
				else{
					$('.testSelect_'+id).html(divContent);
				}
			}
			$("#reportDiv select").removeClass("assignTestPackage");
		}
	});
}

function listOfPackageMasterReport(id){
	//alert(id);
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfPackageMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var newVisitDivContent="";
			newVisitDivContent=newVisitDivContent+"<option value=''>Select Package</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].packageId+"'>"+r[i].packageDescription+"</option>";
				newVisitDivContent=newVisitDivContent+"<option value='"+r[i].packageId+"'>"+r[i].packageDescription+"</option>";
			}//alert(divContent);
			if(id==0){
				$('.assignTestPackage').html(divContent);
				//alert(divContent);
			}
			else{
				if(id=="forNewVisit"){
				//	$('.testSelect_forNewVisit').html(newVisitDivContent);
					$('#popUpTestPackageList').html(divContent);
				}
				else{
					$('.testSelect_'+id).html(divContent);
				}
			}
			$("#reportDiv select").removeClass("assignTestPackage");
			var packageId=$('#visitSelectOnMyReportPage :selected').attr('class').split("_")[2];
			$('.testSelectList').val(packageId);
		}
	});
}

function testPackageList(current){
	var id=($(current).attr('class')).split("_")[1];
	//alert($(current).attr('class')+"*****"+id);
	var current=$('.testPackageSelect_'+id).val();
	if(current=="Test"){
		listOfTestMasterReport(id);
	}
	else{
		listOfPackageMasterReport(id);
	}
}

function testPackageListForNewVisit(){
	var current=$('#forNewVisit').val();
	//alert("curr"+current);
	if(current=="Test"){
		listOfTestMasterReport("forNewVisit");
	}
	else{
		listOfPackageMasterReport("forNewVisit");
	}
}

function getVisitByVisitId(visitId){
	var clientId=$('#clientMasterId').html();
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
				"clientId" : clientId,
				"visitId":visitId
			},
		url : "client/getVisitByVisitId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var divContent2="";
			var clientDivContent="";
			if(r.length>0)
			{	
				var index=1;
				var clientIndex=1;
				var addedOn=[];
				for(var i=0;i<r.length;i++)
				{
					var editReportIconColor="color_red";
					if(r[i].editReportStatus==1){
						editReportIconColor="color_orange";
					}
					var filePath = r[i].filePath;
					var avoid = filePath.split("_")[0];
					var avoid1 = filePath.split("_")[1];
					var avoided = filePath.replace(avoid,'');
					var avoided1 = avoided.replace(avoid1,'');
					var fileName = avoided1.slice(2);
					addedOn=(r[i].clientReportHead.addedOn).split(" ");
					var testOrPackage = "";
					if(r[i].testMaster!=null){
						testOrPackage = "test_"+r[i].testMaster.testId+"_"+r[i].clientReportHead.centreMaster.centreId;
					}
					if(r[i].packageMaster!=null){
						testOrPackage = "package_"+r[i].packageMaster.packageId+"_"+r[i].clientReportHead.centreMaster.centreId;
					}
					if(r[i].reportVerificationSet.length>0){
						if(r[i].reportVerificationSet[0].verifyComment!=null){
							divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'><a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].verifyComment+"'><i class='fa fa-comment color_green' data-placement='right' data-toggle='tooltip' class='btn btn-primary' type='button' data-original-title='"+r[i].reportVerificationSet[0].rejectComment+"'></i></a></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
							+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
							+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
							+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
							/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
							+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
							+"</div></td></tr>";
							
							clientDivContent=clientDivContent+"<tr><td class='center'>"+clientIndex+"</td><td></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
							+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
							+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
							+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
							/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
							+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
							+"</div></td></tr>";
							clientIndex++;
						}
						if(r[i].reportVerificationSet[0].rejectComment!=null){
							//console.log(r[i].reportVerificationSet[0].rejectComment);
							divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'><a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].rejectMaster.rejectReason+"'><i class='fa fa-exclamation-triangle "+editReportIconColor+"'></i></a> <a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].rejectComment+"'><i class='fa fa-comment "+editReportIconColor+"'></i></a></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
							+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
							+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
							+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
							/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
							+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
							+"</div></td></tr>";
							$('#verificationFailed').removeClass('hidden');
						}
					}
					else{
						divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
						+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
						+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
						+"<a onclick=editReport("+r[i].clientReportLineId+",'"+testOrPackage+"'); class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit'><i class='fa fa-pencil'></i></a>"
						/*+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"*/
						+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
						+"</div></td></tr>";
					}
					index++;
					
					divContent2=divContent2+"<tr><td></td><td>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td>"+r[i].reportDate+"</td><td>"+fileName+"</td><td></td><td></td></tr>";
					/*<span class='btn btn-danger btn-xs' onclick='removeReport()'><i class='glyphicon glyphicon-ban-circle'></i><span>Remove</span></span>*/
					$('#reportLength').html(r.length);
				}
			}
			else
			{
				divContent=divContent+"<b>No Reports Available</b>";
				divContent2=divContent2+"<b>No Reports Available</b>";
				$('#reportLength').html("");
			}	
			
			if($('#clientLoginUserType').html()==1){
				//alert($('#clientLoginUserType').html());
				$("#uploadReportData").html(clientDivContent);
				$('.reportUploader').hide();
				$('.reportUploaderDate').hide();
			}
			else{
				$("#uploadReportData").html(divContent);
				$("#displayReportTableBody").html(divContent2);
				$('.reportUploader').show();
				$('.reportUploaderDate').show();
			}
			getUserAccess();
		}
	});
}

function unitChanged(){
	var unit=$('#heightUnit').val();
	if(unit=="cm"){
		$('.heightInCm').show();
		$('.heightInFoot').hide();
		$('.heightInInches').hide();
	}
	else{
		$('.heightInCm').hide();
		$('.heightInFoot').show();
		$('.heightInInches').show();
	}
}
