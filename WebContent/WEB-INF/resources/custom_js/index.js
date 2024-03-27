function numericOnly(current) {
	var number = $(current).val();
	var id = $(current).attr('id');
	if (number!="" && jQuery.isNumeric(number) == false) {
		var value = number.slice(0,-1);
		$('#'+id).val(value);
		alertify.error('Please enter numeric value only');
		$("#"+id).css("border","1px solid red");
		return;
	}
	else{
		$("#"+id).css("border","");
	}
}

$(document).on('click', '#rejectedReportList tr', function () {
	var userId=$(this).attr('id');
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{
			"userId" : userId
		},
		url : "dashboard/redirectRejectedReportListPage",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			window.open('homepage', '_blank');
		}
	});
});

function rejectedReportList(){
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var currentPageId = $('#currentPageId').html();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var dateAr = startDate.split('-');
		startDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0].slice(-2);
		
		var dateAr1 = endDate.split('-');
		endDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0].slice(-2);
	}else{
		startDate = 'NoDate';
		endDate = 'NoDate';
	}
	
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			startDate : startDate,
			endDate : endDate,
			listData : "userwise"
		},
		url : "dashboard/rejectedReportList",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#rejectedReportListModal').modal('show');
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr style='cursor: pointer' title='Click for open Report Upload Page' id="+r[i].userId+"><td class='center'>"+index+"</td><td>"+r[i].clientId+"</td><td>"+r[i].firstName+" "+r[i].middleName+" "+r[i].lastName+"</td><td>"+r[i].packageShortDesc+"</td><td>"+r[i].checkupDate+"</td><td>"+r[i].reportDescription+"</td><td>"+r[i].rejectReason+"</td>"
				+"<td>"+r[i].approvedBy+"</td></tr>";
				index++;
				/*<td>"+r[i].rejectComment+"</td>"*/
			}
			$('#rejectedReportList').html(divContent);
		}
	});
}


//Added by kishor
function rejectedReportListAll(){
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var currentPageId = $('#currentPageId').html();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var dateAr = startDate.split('-');
		startDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0].slice(-2);
		
		var dateAr1 = endDate.split('-');
		endDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0].slice(-2);
	}else{
		startDate = 'NoDate';
		endDate = 'NoDate';
	}
	
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			startDate : startDate,
			endDate : endDate,
			listData : "All"
		},
		url : "dashboard/rejectedReportList",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#rejectedReportListModal').modal('show');
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr style='cursor: pointer' title='Click for open Report Upload Page' id="+r[i].userId+"><td class='center'>"+index+"</td><td>"+r[i].clientId+"</td><td>"+r[i].firstName+" "+r[i].middleName+" "+r[i].lastName+"</td><td>"+r[i].packageShortDesc+"</td><td>"+r[i].checkupDate+"</td><td>"+r[i].rejectReason+"</td>"
				+"<td>"+r[i].approvedBy+"</td></tr>";
				index++;
				/*<td>"+r[i].rejectComment+"</td>"*/
			}
			$('#rejectedReportList').html(divContent);
		}
	});
}




$(document).on('click', '#totalPendingVisits tr', function () {
	var userId=$(this).attr('id');
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{
			"userId" : userId
		},
		url : "dashboard/redirectReportUploadListPage",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			window.open('homepage', '_blank');
		}
	});
});

//Added by kishor for get total pending reports visit list
function totalPendingVisits(){
	
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var currentPageId = $('#currentPageId').html();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var dateAr = startDate.split('-');
		startDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0].slice(-2);
		
		var dateAr1 = endDate.split('-');
		endDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0].slice(-2);
	}else{
		startDate = 'NoDate';
		endDate = 'NoDate';
	}
	
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			startDate : startDate,
			endDate : endDate,
			listData : "All"
		},
		url : "dashboard/totalPendingVisits",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#totalPendingVisitsModal').modal('show');
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr style='cursor: pointer' title='Click for open Report Upload Page' id="+r[i].userId+"><td class='center'>"+index+"</td><td>"+r[i].clientId+"</td><td>"+r[i].firstName+" "+r[i].middleName+" "+r[i].lastName+"</td><td>"+r[i].userId+"</td><td>"+r[i].packageShortDesc+"</td><td>"+r[i].checkupDate+"</td> </tr>";
				index++;
				/*<td>"+r[i].rejectComment+"</td>"*/
			}
			$('#totalPendingVisits').html(divContent);
		}
	});
}


function dashboard(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "dashboard/getDashboard",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var index=1;
			var divContent="";
			divContent=divContent+"<h4 style='margin-top:10px;'>Your Pending Tests :</h4>";
			if(r.pendingTestMasterList.length>0){
			for(var i=0;i<r.pendingTestMasterList.length;i++){
				divContent=divContent+"<div class='row'><div class='col-md-6'><h5>"+index+". "+r.pendingTestMasterList[i].testDescription+"</h5></div></div>";
				index++;
			}
			$('#pendingTestsDiv').html(divContent);
			}
			$('#dashboardLink').attr("href",r.linkObject.link);
			$('#dashboardLink').html(r.linkObject.link);
			//$('#dashboardVideo').attr('src','preview?fileName=Airtel 4G (MP4)(PaGalWorld.CC).mp4');
			$('#dashboardVideo').attr('src',"preview?fileName="+r.videoObject.fileName);
			$("video")[0].load();
			$('#dashboardVideoDescription').html(r.videoObject.videoDescription);
			$('#dashboardHealthFeed').html("<marquee behavior='SCROLL'>"+r.healthFeedObject.healthFeed+"</marquee>");
			
			var analysisCommentsDashboard = "";
			for(var j=0;j<r.analysisComments.length;j++){
				analysisCommentsDashboard = analysisCommentsDashboard +'<div class="col-md-3">'
					+'<div class="form-group"><label class="control-label"> '+r.analysisComments[j].comment+' </label>'
					+'<textarea style="max-width:100%">'+r.analysisComments[j].analysisComment+'</textarea></div></div>';
			}
			$("#analysisCommentsDashboard").html(analysisCommentsDashboard);
			
			if(r.healthStatisticsList.length>0){
				for(var i=0;i<r.healthStatisticsList.length;i++){
					var lowerValue=parseFloat(r.healthStatisticsList[i].lowerValue);
					var upperValue=parseFloat(r.healthStatisticsList[i].upperValue);
					var parameterValue=parseFloat(r.healthStatisticsList[i].parameterValue);
					var parameterId=r.healthStatisticsList[i].parameterId;
					$('#parameterHealthStatistics_'+parameterId).show();
					var max=parseFloat($('#maxValue_'+parameterId).html());
					//console.log(lowerValue+"**"+upperValue);
					//console.log(parameterId+"**parameterValue:"+parameterValue+"**max:"+max);
					var firstWidth=(lowerValue/max)*100;
					var secondWidth=((upperValue/max)*100)-firstWidth;
					var thirdWidth=100-(firstWidth+secondWidth);
					var clientPosition=(parameterValue/max)*100;
					$('#firstWidth_'+parameterId).css("width", firstWidth+"%");
					$('#secondWidth_'+parameterId).css("width", secondWidth+"%");
					$('#thirdWidth_'+parameterId).css("width", thirdWidth+"%");
					/*if(isNaN(parameterValue)==false){
						firstWidth=firstWidth-clientPosition;
					}else{
						//firstWidth=firstWidth+2;
					}*/
					//console.log(isNaN(parameterValue)+"***"+firstWidth+"*"+secondWidth+"*"+thirdWidth);
					/*$('#lowerValue_'+parameterId).css("margin-left", (firstWidth-1)+"%");
					$('#upperValue_'+parameterId).css("margin-left", (firstWidth+secondWidth-1)+"%");*/
					$('#parameterValue_'+parameterId).css("margin-left", (clientPosition)+"%");
					$('#parameterValueIcon_'+parameterId).css("margin-left", (clientPosition)+"%");
					$('#lowerValue_'+parameterId).html(lowerValue);
					$('#upperValue_'+parameterId).html(upperValue);
					$('#parameterValue_'+parameterId).html(parameterValue);
				}
			}
			
			$('#disclaimerNote').show();
		}
	});
}

function calculateParameterWidth(lowerValue,upperValue,max){
	
}

function getRepresentativeDashboard(){
	//Added By Kishor
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var currentPageId = $('#currentPageId').html();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var dateAr = startDate.split('-');
		startDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0].slice(-2);
		
		var dateAr1 = endDate.split('-');
		endDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0].slice(-2);

	}else{
		startDate = 'NoDate';
		endDate = 'NoDate';
	}
	
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			startDate : startDate,
			endDate : endDate
		},
		url : "dashboard/getRepresentativeDashboard",
		catche : false,
		error : function() {
			//alert("error");
		},
		success : function(r) {
			

			$('#turnUpNoOfClient').html(r.turnUpNoOfClient);
			$('#uploadReportClient').html(r.clientReportCountVisitWise);
			$('#noOfReportsUploaded').html(r.uploadReportClient);
			$('#reportsRejected').html(r.rejectedReportAll);
			$('#uploadReportCountByUser').html(r.uploadReportCountByUser);
			$('#rejectedReportByUser').html(r.rejectedReportByUser);
			$('#pendingUpload').html(r.clientReportNotUplodedVisitWise);
			  
			
			
			var options = {
					title: {
						text: "CENTER DASH-BOARD"
					},
					subtitles: [{
						text: "INDUS HEALTH PLUS © 2019-2020"
					}],
					animationEnabled: true,
					data: [{
						type: "pie",
						startAngle: 40,
						toolTipContent: "<b>{label}</b>: {y}",
						showInLegend: "true",
						legendText: "{label}",
						indexLabelFontSize: 16,
						indexLabel: "{label} - {y}",
						dataPoints: [
							{ y: r.turnUpNoOfClient, label: "Total TURN UP" },
							{ y: r.clientReportCountVisitWise, label: "Total Upload " },
							/*{ y: r.uploadReportClient, label: " No.of Reports Uploaded" },*/
							{ y: r.rejectedReportAll, label: " No.of All Incomplete Reports" },
							/*{ y: r.uploadReportCountByUser, label: " No.of Reports Upload By You" },
							{ y: r.rejectedReportByUser, label: " No.of My Rejected Reports" },*/
							{ y: r.clientReportNotUplodedVisitWise, label: "Total Upload Pending" }/*,
							{ y: 4.03, label: "Others" }*/
						]
					}]
				};
				$("#pichart").CanvasJSChart(options);
		}
	});
}


function assignDateNew() {
	 $('#endDate').val($.datepicker.formatDate('dd-mm-yy', new Date())); 
}

function submitFeedback(){
	var feedback=$('#feedbackTextarea').val();
	if(feedback!="" && feedback!=null){
		$('#pleaseWait').show();
		jQuery.ajax({
			type : "POST",
			data : {
				feedback : feedback
			},
			url : "client/submitFeedback",
			error : function() {
				//alert("Email service is not activated yet....coming soon....Please write to info@indushealthplus.com for feedback");
				$('#myModal').modal('hide');
				$('#pleaseWait').hide();
			},
			success : function(r) {
				$('#myModal').modal('hide');
				$('#pleaseWait').hide();
				alert(r);
			}
		});
	}
	else{
		alert("Please write something in the feedback field.");
	}
}

function getUserAccess(){
	$('#pleaseWait').show();
	var isRead=$('#isRead').html();
	var isWrite=$('#isWrite').html();
	var isDelete=$('#isDelete').html();
	var currentPageId=$('#currentPageId').html();
	
	if(isRead!=null && isRead.trim()!="null"){
		var checkId=[];
		checkId=(isRead).split(",");
		for(var i=0;i<checkId.length;i++){
			$('#menu_'+checkId[i].trim()).show();
			$('.'+checkId[i].trim()).show();
			$('.menu_'+checkId[i].trim()).show();
			if(currentPageId==checkId[i].trim()){
				//for demoghraphic page
				$('.edit-user-info').hide();
				$('.user-image-buttons').hide();
				$('#updateProfileBtnRefreshDiv').hide();
				$('.timeline').show();
				
				//for my reports page
				$('.editReport').hide();
				$('.deleteReport').hide();
				$('#uploadReportBtn').hide();
				
				//for HRA page
				$('#hraHistoryBtnDiv').hide();
				$('#hraQuestionDiv').removeClass("col-md-9").addClass("col-md-12");
				
				//for account management
				$('#selectUserTypeDiv').hide();
				$('#createUserDiv').hide();
				
				//common for all administrator menu
				$('.editUserAccess').hide();
				$('.deleteUserAccess').hide();
				$('.userAccessEditTableHeading').hide();
				$('.userAccessDeleteTableHeading').hide();
				
				//for HRA type master
				$('#addHraTypeMasterDiv').hide();
				
				//for parameter master
				$('#selectTestDiv').hide();
				$('#displayParameterDiv').hide();
				
				//for center wise parameter master
				$('#displayCenterParameterDiv').hide();
				
				//for unit master
				$('#addUnitMasterDiv').hide();
				
				//for Package Test Relation master
				$('#saveTestBtnnn').hide();
				
				//for reject master
				$('#addRejectMasterDiv').hide();
				
				//for email template master
				$('#addEmailTemplateDiv').hide();
				
				//for sms template master
				$('#addSmsTemplateDiv').hide();
				
				//for action master
				$('#addActionMasterDiv').hide();
				
				//for sub action master
				$('#addSubActionMasterDiv').hide();
				
				//for visit type master
				$('#addVisitTypeMasterDiv').hide();
			}
		}
	}
	
	if(isWrite!=null && isWrite.trim()!="null"){
		var checkId=[];
		checkId=(isWrite).split(",");
		for(var i=0;i<checkId.length;i++){
			$('#menu_'+checkId[i].trim()).show();
			$('.'+checkId[i].trim()).show();
			$('.menu_'+checkId[i].trim()).show();
			if(currentPageId==checkId[i].trim()){
				//for demoghraphic page
				$('.edit-user-info').show();
				$('.user-image-buttons').show();
				$('#updateProfileBtnRefreshDiv').show();
				$('.timeline').show();
				
				//for my reports page
				$('.editReport').show();
				$('.deleteReport').hide();
				$('#uploadReportBtn').show();
				
				//for HRA page
				$('#hraHistoryBtnDiv').show();
				$('#hraQuestionDiv').removeClass("col-md-12").addClass("col-md-9");
				
				//for account management
				$('#selectUserTypeDiv').show();
				$('#createUserDiv').show();
				
				//common for all administrator menu
				$('.editUserAccess').show();
				$('.deleteUserAccess').hide();
				$('.userAccessEditTableHeading').show();
				$('.userAccessDeleteTableHeading').hide();
				
				//for HRA type master
				$('#addHraTypeMasterDiv').show();
				
				//for parameter master
				$('#selectTestDiv').show();
				$('#displayParameterDiv').show();
				
				//for center wise parameter master
				$('#displayCenterParameterDiv').show();
				
				//for unit master
				$('#addUnitMasterDiv').show();
				
				//for Package Test Relation master
				$('#saveTestBtnnn').show();
				
				//for reject master
				$('#addRejectMasterDiv').show();
				
				//for email template master
				$('#addEmailTemplateDiv').show();
				
				//for sms template master
				$('#addSmsTemplateDiv').show();
				
				//for action master
				$('#addActionMasterDiv').show();
				
				//for sub action master
				$('#addSubActionMasterDiv').show();
				
				//for visit type master
				$('#addVisitTypeMasterDiv').show();
				
			}
		}
	}
	
	if(isDelete!=null && isDelete.trim()!="null"){
		var checkId=[];
		checkId=(isDelete).split(",");
		for(var i=0;i<checkId.length;i++){
			$('#menu_'+checkId[i].trim()).show();
			$('.'+checkId[i].trim()).show();
			$('.menu_'+checkId[i].trim()).show();
			if(currentPageId==checkId[i].trim()){
				//for demoghraphic page
				$('.edit-user-info').show();
				$('.user-image-buttons').show();
				$('#updateProfileBtnRefreshDiv').show();
				$('.timeline').show();
				
				//for my reports page
				$('.editReport').show();
				$('.deleteReport').show();
				$('#uploadReportBtn').show();
				
				//for other details page
				$('.editReport').show();
				$('.deleteReport').show();
				$('#clientUploadReportBtn').show();
				
				//for HRA page
				$('#hraHistoryBtnDiv').show();
				$('#hraQuestionDiv').removeClass("col-md-12").addClass("col-md-9");
				
				//for account management
				$('#selectUserTypeDiv').show();
				$('#createUserDiv').show();
				
				//common for all administrator menu
				$('.editUserAccess').show();
				$('.deleteUserAccess').show();
				$('.userAccessEditTableHeading').show();
				$('.userAccessDeleteTableHeading').show();
				
				//for HRA type master
				$('#addHraTypeMasterDiv').show();
				
				//for parameter master
				$('#selectTestDiv').show();
				$('#displayParameterDiv').show();
				
				//for center wise parameter master
				$('#displayCenterParameterDiv').show();
				
				//for unit master
				$('#addUnitMasterDiv').show();
				
				//for Package Test Relation master
				$('#saveTestBtnnn').show();
				
				//for reject master
				$('#addRejectMasterDiv').show();
				
				//for email template master
				$('#addEmailTemplateDiv').show();
				
				//for sms template master
				$('#addSmsTemplateDiv').show();
				
				//for action master
				$('#addActionMasterDiv').show();
				
				//for sub action master
				$('#addSubActionMasterDiv').show();
				
				//for visit type master
				$('#addVisitTypeMasterDiv').show();
			}
		}
	}
	
	var interactionId=$('#interactionPageId').html();
	if(interactionId==1){
		//alert("from interaction page");
		$('.interactionTabShow').show();
		$('.interactionTabHide').hide();
		$('.timeline').show();
		//for demoghraphic page
		$('.edit-user-info').hide();
		$('.user-image-buttons').hide();
		$('#updateProfileBtnRefreshDiv').hide();
		
		//for my reports page
		$('.editReport').hide();
		$('.deleteReport').hide();
		$('#uploadReportBtn').hide();
		
		//for HRA page
		$('#hraHistoryBtnDiv').hide();
		$('#hraQuestionDiv').removeClass("col-md-9").addClass("col-md-12");
	}else{
		$('.interactionTabShow').hide();
	}
	$('#pleaseWait').hide();
}

function loadDatabasePage(){
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/database",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
		}
	});
};

function loadDemoghraphicPage(){
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
	jQuery.ajax({
		type : "GET",
		data : {
			"clientId" : clientId
		},
		url : "client/displayPatientDemoghraphic",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			$('#currentPageId').html("3");
			//getClientTimeline($('#clientMasterId').html());
			listOfVisitTypeMasterDropDown();
			getVisitDateList($('#clientMasterId').html());
			getUserAccess();
			//setTimeout(function(){ getUserAccess(); }, 300);
			//getClientTimeline($('#clientMasterId').html());
		}
	});
	}
	else{
		$('#pleaseWait').hide();
		alert("Please Select Client Name First");
	}
};

function getClientTimeline(clientId){
	jQuery.ajax({
		type : "POST",
		data : {
			clientId : clientId
		},
		url : "client/getClientTimeline",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			for(var i=1;i<=5;i++){
				$('#step-'+i).removeClass('selected');
				//alert(i+"getClientTimeline:"+r);
			}
			for(var i=1;i<=r;i++){
				$('#step-'+i).addClass('selected');
				//alert(i+"getClientTimeline:"+r);
			}
		}
	});
}

function loadReportPage() {
	$('#currentPageId').html("4");
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
		jQuery.ajax({
			type : "GET",
			data : {
				"clientId" : clientId
			},
			url : "/IndusHealth/indus/reports",
			error : function() {
				$('#pleaseWait').hide();
				//alert("error");
			},
			success : function(r) {
				$('#mainBody').html(r);
				getClientTimeline($('#clientMasterId').html());
				//getUploadedReport($('#clientMasterId').html());
				listOfVisitTypeMasterDropDown();
				getVisitDateList($('#clientMasterId').html());
				//setTimeout(function(){ getUserAccess(); }, 1000);
				listOfPackageMasterReport("forNewVisit");
				getListOfCentres();
				//testPackageList("package");
			}
		});
	}
	else{
		$('#pleaseWait').hide();
		alert("Please Select Client Name First");
	}
}

function loadHRAPage(){
	$('#currentPageId').html("6");
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
	jQuery.ajax({
		type : "GET",
		data : {
			"clientId" : clientId
		},
		url : "/IndusHealth/indus/hra",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfHraTypeMaster();
			getClientTimeline($('#clientMasterId').html());
			listOfVisitTypeMasterDropDown();
			getVisitDateList($('#clientMasterId').html());
			sample();
			//setTimeout(function(){ getListOfQuestionClientHRADetails(); }, 100);
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}
else{
	$('#pleaseWait').hide();
	alert("Please Select Client Name First");
}
};

function sample(){
	getClientCenterPackageByUserId($('#clientMasterId').html());
	checkPreCouncelling($('#clientMasterId').html());
	if($('#visitSelect').val()!=null){
		getListOfQuestionClientHRADetails(($('#visitSelect').val()).split("_")[1]);
		$('#hraPrintBtn').attr('href','hra/hraPrintPage?clientId='+$("#clientMasterId").html()+'&visitId='+($('#visitSelect').val()).split("_")[1]);
		$('#mergePreCouncellingBtn').attr('onclick','mergePreCouncelling('+$("#clientMasterId").html()+','+($('#visitSelect').val()).split("_")[1]+')');
	}
}

function loadVerificationPage(){
	$('#currentPageId').html("5");
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
	jQuery.ajax({
		type : "GET",
		data : {
			"clientId" : clientId
		},
		url : "/IndusHealth/indus/verification",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			getClientTimeline($('#clientMasterId').html());
			listOfVisitTypeMasterDropDown();
			getVisitDateList($('#clientMasterId').html());
			setTimeout(function(){ getUserAccess(); }, 300);
			if($('#visitSelect').val()!=null){
				getVerifyUploadedReport($('#clientMasterId').html(),($('#visitSelect').val()).split("_")[1]);
			}
			listOfRejectMasterDropDown();
		}
	});
	}
	else{
		$('#pleaseWait').hide();
		alert("Please Select Client Name First");
	}
};

function loadOtherDetailsPage(){
	$('#currentPageId').html("7");
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
		jQuery.ajax({
			type : "GET",
			data : {
				"clientId" : clientId
			},
			url : "/IndusHealth/indus/otherDetails",
			error : function() {
				$('#pleaseWait').hide();
				//alert("error");
			},
			success : function(r) {
				$('#mainBody').html(r);
				getClientTimeline($('#clientMasterId').html());
				listOfVisitTypeMasterDropDown();
				getVisitDateList($('#clientMasterId').html());
				getClientSelfUploadReport($('#clientMasterId').html());
				//setTimeout(function(){ getUserAccess(); }, 1000);
			}
		});
	}
	else{
		$('#pleaseWait').hide();
		alert("Please Select Client Name First");
	}
}

function loadReminderPage(){
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
	jQuery.ajax({
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "client/reminderPage",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			$('#currentPageId').html("8");
			getClientTimeline($('#clientMasterId').html());
			listOfVisitTypeMasterDropDown();
			getVisitDateList($('#clientMasterId').html());
			getClientCenterPackageByUserId($('#clientMasterId').html());
			getPatientReminder($('#clientMasterId').html());
			getUserAccess();
		}
	});
	}
	else{
		$('#pleaseWait').hide();
		alert("Please Select Client Name First");
	}
};

function loadDataEntryPage(){
	$('#pleaseWait').show();
	var userId=$('#clientUserId').html();
	var clientId = $('#clientMasterId').html();
	if(userId!=null && userId!="null"){
	jQuery.ajax({
		type : "GET",
		data : {
			"clientId" : clientId
		},
		url : "/IndusHealth/indus/dataManagement",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			$('#currentPageId').html("9");
			listOfVisitTypeMasterDropDown();
			getVisitDateList($('#clientMasterId').html());
			setTimeout(() => {
				if($('#visitSelect').val()!=null){
					//getVrifiedReport($('#clientMasterId').html(),($('#visitSelect').val()).split("_")[1]);
					getVrifiedReport($('#clientMasterId').html(),$("#workVisitId").val());
					getVisitStatus(($('#visitSelect').val()).split("_")[1]);
				}
			}, 200);
			getUserAccess();
			//$('#pleaseWait').hide();
		}
	});
}
else{
	$('#pleaseWait').hide();
	alert("Please Select Client Name First");
}
};

function listOfVisitTypeMasterDropDown(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "visitType/listOfVisitTypeMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value='0'>Select Visit Type</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].visitTypeId+"'>"+r[i].visitType+"</option>";
			}
			$('#visitTypeMasterSelect').html(divContent);
			$('#visitTypeMasterSelectOnMyReportPage').html(divContent);
		}
	});
}

function changeVisitSelectList(current){
	var visitDate=$(current).find('option:selected').attr("class");//$('#visitSelect option:selected').attr('class');
	$('#showVisitDate').val(visitDate.split("_")[0]);
	$('#showVisitDateOnMyReportPage').val(visitDate.split("_")[0]);
	var packageDescription = visitDate.split("_")[4];
	if(packageDescription!=null && packageDescription!="null"){
		$('.packageNameSpan').html(packageDescription);
	}else{
		$('.packageNameSpan').html("");
	}
	
	$('#visitTypeMasterSelect').val(visitDate.split("_")[1]);
	$('#visitTypeMasterSelectOnMyReportPage').val(visitDate.split("_")[1]);
	
	$('#visitSelect').val($('#visitSelectOnMyReportPage').val());
	var visitId=($(current).val()).split("_")[1];
	$("#visitSelectOnMyReportPage").attr("title",visitId);
	var currentPageId=$('#currentPageId').html();
	
	var healthReport = visitDate.split("_")[3];
	if(healthReport=="true" && $('#interactionPageId').html()=="1"){
		$("#healthReportBtnDiv").show();
	}else{
		$("#healthReportBtnDiv").hide();
	}
	
	//For Report page
	if (currentPageId == "4") {
		getVisitByVisitId(visitId);
		var packageId = visitDate.split("_")[2];
		$('#popUpTestPackageList').val(packageId);
	}
	
	//For HRA Page
	else if (currentPageId == "6") {
		getListOfQuestionClientHRADetails(visitId);
		$('#hraPrintBtn').attr('href','hra/hraPrintPage?clientId='+$("#clientMasterId").html()+'&visitId='+visitId);
		$('#mergePreCouncellingBtn').attr('onclick','mergePreCouncelling('+$("#clientMasterId").html()+','+visitId+')');
	}
	
	//For Verification Page
	else if (currentPageId == "5") {
		getVerifyUploadedReport($('#clientMasterId').html(),visitId);
	}
	
	//For Data Entry Page
	else if (currentPageId == "9") {
		getVrifiedReport($('#clientMasterId').html(),visitId);
		getVisitStatus(visitId);
	}
	healthReportBtn(visitId);
	getClientCenterPackageByUserId($('#clientMasterId').html());
	getClientTimeline(visitId);
}

function changeVisitSelectListPopUp(current){
	$('#reportDiv').html("");
	var visitDate=$(current).find('option:selected').attr("class");
	$('#showVisitDate').val(visitDate.split("_")[0]);
	var visitId=($(current).val()).split("_")[1];
	var packageId=visitDate.split("_")[2];
	getVisitByVisitId(visitId);
	
	$('#visitSelectOnMyReportPage').val("visit_"+visitId);
	$('#showVisitDateOnMyReportPage').val(visitDate.split("_")[0]);
	
	$('#visitTypeMasterSelect').val(visitDate.split("_")[1]);
	$('#visitTypeMasterSelectOnMyReportPage').val(visitDate.split("_")[1]);
	
	$('#popUpTestPackageList').val(packageId);
}

function getVisitDateList(clientId){
	//var clientId=$('#clientMasterId').html();
	jQuery.ajax({
		type : "POST",
		data : {
			clientId : clientId
		},
		url : "client/getVisitDateList",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var divContent1="";
			var index=1;
			var packageId="";
			var packageDescription = "";
			//divContent=divContent+"<option value=''>select state</option>";
			for(var i=0;i<r.length;i++){
				if(r[i].packageMaster !="" && r[i].packageMaster !=null){
					packageId=r[i].packageMaster.packageId;
					if(r[i].packageMaster.packageDescription !="" && r[i].packageMaster.packageDescription !=null){
					packageDescription=r[i].packageMaster.packageDescription;
					}else{
						packageDescription="No Package";
					}
				} else{
					packageId=0;
					packageDescription=undefined;
				}
				
				divContent=divContent+"<option value='visit_"+r[i].checkUpId+"' class='"+(r[i].checkUpDate).split(" ")[0]+"_"+r[i].visitTypeMaster.visitTypeId+"_"+packageId+"_"+r[i].healthReport+"_"+packageDescription+"'> Visit "+index+"("+packageDescription+", "+r[i].checkUpDate+", "+r[i].checkUpId+")</option>";
				divContent1=divContent1+"<option value='visit_"+r[i].checkUpId+"' class='"+(r[i].checkUpDate).split(" ")[0]+"_"+r[i].visitTypeMaster.visitTypeId+"_"+packageId+"_"+r[i].healthReport+"_"+packageDescription+"'> Visit "+index+"</option>";

				index++;
				$('#showVisitDate').val((r[i].checkUpDate).split(" ")[0]);
				$('#showVisitDateOnMyReportPage').val((r[i].checkUpDate).split(" ")[0]);
				
				if(packageDescription!=null && packageDescription!="null"){
					$('#packageNameSpan').html(packageDescription);
				}else{
					$('.packageNameSpan').html("");
				}
				
				$('.lastCheckUpDate').html((r[i].checkUpDate).split(" ")[0]);
				$('.lastCheckUpDate').attr("id",r[i].checkUpId);

				$('#visitTypeMasterSelect').val(r[i].visitTypeMaster.visitTypeId);
				$('#visitTypeMasterSelectOnMyReportPage').val(r[i].visitTypeMaster.visitTypeId);
				
			}
			$('#visitSelect').html(divContent1);
			$('#visitSelect').val($("#visitSelect option:last").val());
			$('#visitSelectOnMyReportPage').html(divContent);
			$('#visitSelectOnMyReportPage').val($("#visitSelect option:last").val());
			
			//added by kishor for disable package dropdown
			//alert($('#currentPageId').html());
			if($('#currentPageId').html()==9){
				var visitIdd='visit_'+$("#workVisitId").val();
				if(visitIdd=="visit_"){
					$('#visitSelectOnMyReportPage').val($("#visitSelect option:last").val());
					changeVisitSelectList($('#visitSelectOnMyReportPage').val());
				}else{
					$('#visitSelectOnMyReportPage').val(visitIdd);
				}
				//$("#visitSelectOnMyReportPage").attr("disabled","disabled");
			}if($('#currentPageId').html()==40){
				var visitIdd='visit_'+$("#workVisitId").val();
				$('#visitSelectOnMyReportPage').val(visitIdd);
				//$("#visitSelectOnMyReportPage").attr("disabled","disabled");
			}
			setTimeout(function(){
				if(r.length>0){
				var visitDate=$("#visitSelect option:last").attr('class');
				$('#showVisitDate').val(visitDate.split("_")[0]);
				$('#showVisitDateOnMyReportPage').val(visitDate.split("_")[0]);
				$('#visitTypeMasterSelect').val(visitDate.split("_")[1]);
				$('#visitTypeMasterSelectOnMyReportPage').val(visitDate.split("_")[1]);
				
				var visitId = $('#visitSelectOnMyReportPage').val().split("_")[1];
				$("#visitSelectOnMyReportPage").attr("title",visitId);
				getClientTimeline(visitId);
				var healthReport = visitDate.split("_")[3];
				if(healthReport=="true" && $('#interactionPageId').html()=="1"){
					healthReportBtn(visitId);
					$("#healthReportBtnDiv").show();
				}else{
					$("#healthReportBtnDiv").hide();
				}
				
				}else{
					//$('#uploadReportBtn').hide();
					//$("#hraPrintBtnDiv").hide();
					$('#hraHistoryBtnDiv').hide();
					$('#hraQuestionDiv').removeClass("col-md-9").addClass("col-md-12");
					$("#visitSelectOnMyReportPage").hide();
					$("#showVisitDateOnMyReportPage").hide();
					$("#visitTypeMasterSelectOnMyReportPage").hide();
				}
			}, 400);
		}
	});
}

function getVisitStatus(visitId) {
	jQuery.ajax({
		type : "GET",
		data : {
			"visitId" : visitId
		},
		url : "client/getVisitStatus",
		success : function(r) {
			$("#workStatus").val(r);
			/*if(r=="5"){
				$(".saveResultBtn").removeAttr("disabled");
			}else{
				$(".saveResultBtn").attr("disabled","disabled");
			}*/
			
			if(r > "7"){
				$(".dataEntryCommentDiv").hide();
			}
			
			if(r == "12"){
				$("#generateReportBtn").show();
			}else{
				$("#generateReportBtn").hide();
			}
		}
	});
}

function generateReport() {
	var r = confirm("Are you sure you want to generate Health Report?");
	if (r == true) {
		var visitId=($('#visitSelectOnMyReportPage').val()).split("_")[1];
	jQuery.ajax({
		type : "POST",
		data : {
			"visitId" : visitId
		},
		url : "client/generateReport",
		error : function() {
			$("#pleaseWait").hide();
		},
		success : function(r) {
			$("#pleaseWait").hide();
			alert("Helath Report Generated Successfully");
		}
	});
	}
}

function listOfCommentMasterForAnalysis(){
	jQuery.ajax({
		async : true,
		type : "GET",
		url : "comment/listOfAnalysisComment",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="<option value=''>Select</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].commentId+"'>"+r[i].comment+"</option>";
			}
			$('#commentType').html(divContent);
		}
	});
}

function addAnalysisComment(){
	var commentId = $("#commentType").val();
	if(commentId!="") {
	var commentType = $("#commentType :selected").text();
	var clientAnalysisComment = $("#clientAnalysisComment").val();
	var index = $("#analysisComments tr").length + 1;
	if($("#analysisComments #commentType_"+commentId).length == 0){
		var divContent = "<tr id='commentType_"+commentId+"'><td class='center'>"+index+"</td><td val='"+commentId+"'>"+commentType+"</td><td style='max-width: 50px; word-wrap: break-word;'>"+clientAnalysisComment+"</td>"
		+"<td><button onclick='viewAnalysisComment(this)' style='margin-right: 5px;' class='btn btn-success btn-xs'>"
		+"<span class='glyphicon glyphicon-eye-open'></span> </button>"
		+"<button onclick='removeAnalysisComment(this)' class='btn btn-danger btn-xs'><span class='glyphicon glyphicon-remove'></span> </button></td></tr>";
		$("#analysisComments").append(divContent);
	}else{
		index = $("#commentType_"+commentId+" :first").text();
		var divContent = "<td class='center'>"+index+"</td><td val='"+commentId+"'>"+commentType+"</td><td style='max-width: 50px; word-wrap: break-word;'>"+clientAnalysisComment+"</td>"
		+"<td><button onclick='viewAnalysisComment(this)' style='margin-right: 5px;' class='btn btn-success btn-xs'>"
		+"<span class='glyphicon glyphicon-eye-open'></span> </button>"
		+"<button onclick='removeAnalysisComment(this)' class='btn btn-danger btn-xs'><span class='glyphicon glyphicon-remove'></span> </button></td>";
		$("#commentType_"+commentId).html(divContent);
	}
	
	$("#commentType").val("");
	$("#clientAnalysisComment").val("");
	}else{
		alert("Please Select Comment Type");
	}
}

function removeAnalysisComment(current){
	var r = confirm("Are you sure you want to delete Comment?");
    if (r == true) {
    	var removedCommentId = $(current).parent().parent().attr("id").split("_")[1];
    	$("#removedAnalysisComments").append("<tr class='removedAnalysisComment' val='"+removedCommentId+"'></tr>");
	$(current).parent().parent().remove();
	var divContent = "";
	var index = 1;
	$("#analysisComments tr").map(function() {
		  var $tds = $(this).find('td');
		  var commentId = $tds.eq(1).attr("val");
		  var commentType = $tds.eq(1).text();
		  var clientAnalysisComment = $tds.eq(2).text();
		  
		  divContent = divContent + "<tr id='commentType_"+commentId+"'><td class='center'>"+index+"</td><td val='"+commentId+"'>"+commentType+"</td><td style='max-width: 50px; word-wrap: break-word;'>"+clientAnalysisComment+"</td>"
		  	+"<td><button onclick='viewAnalysisComment(this)' style='margin-right: 5px;' class='btn btn-success btn-xs'>"
			+"<span class='glyphicon glyphicon-eye-open'></span> </button>"
			+"<button onclick='removeAnalysisComment(this)' class='btn btn-danger btn-xs'>"
			+"<span class='glyphicon glyphicon-remove'></span> </button></td></tr>";
		  index++;
	});
	$("#analysisComments").html(divContent);
    }
}

function viewAnalysisComment(current){
	var id = $(current).parent().parent().attr("id");
	var $tds = $("#"+id).find('td');
	var commentId = $tds.eq(1).attr("val");
	var clientAnalysisComment = $tds.eq(2).text();
	$("#commentType").val(commentId);
	$("#clientAnalysisComment").val(clientAnalysisComment);
}

function savePatientAnalysis(){
	var classification=$('#clientClassification').val();
	if(classification==""){
		alert("Please Select Classification");
	}
	else{
	var comments=[];
	$('.analysisStatus').map(function() {
		comments.push(this.value+"#"+this.id);
	});
	var clientAnalysisComment=$('#clientAnalysisComment').val();
	var clientId=$('#analysisClientId').html();
	var visitId = $('#visitSelectOnMyReportPage').val().split("_")[1];
	
	var analysisComments = [];
	$(".removedAnalysisComment").map(function() {
		  var isActive = false;
		  var commentId = $(this).attr("val");
		  var analysisObject = {"commentId":commentId,"isActive":isActive};
		  analysisComments.push(analysisObject);
	});
	
	$("#analysisComments tr").map(function() {
		  var isActive = true;
		  var $tds = $(this).find('td');
		  var commentId = $tds.eq(1).attr("val");
		  var comment = $tds.eq(2).text();
		  var analysisObject = {"commentId":commentId,"comment":comment,"isActive":isActive};
		  analysisComments.push(analysisObject);
	});
	
	$("#pleaseWait").show();
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{
			"clientId" : clientId,
			"comments" : comments,
			"classification" : classification,
			//"clientAnalysisComment" : clientAnalysisComment,
			"analysisComments" : JSON.stringify(analysisComments),
			"visitId" : visitId
		},
		url : "client/savePatientAnalysis",
		catche : false,
		error : function() {
			$("#pleaseWait").hide();
			alert("error");
		},
		success : function(r) {
			$("#pleaseWait").hide();
			alert("Patient Analysis Saved Successfully");
		}
	});
	}
}

function getPatientAnalysis(clientId,userId){
	$("#pleaseWait").show();
	$('#clientUserId').html(userId);
	$('#analysisClientId').html(clientId);
	$('#clientMasterId').html(clientId);
	
	//for visit dropdown
	listOfVisitTypeMasterDropDown();
	getVisitDateList($('#clientMasterId').html());
	listOfCommentMasterForAnalysis();
	setTimeout(function(){
		var visitId=($('#visitSelectOnMyReportPage').val()).split("_")[1];
		getVisitStatus(visitId);
		getAnalysis(visitId);
	}, 300);
}

function getAnalysis(visitId){
	$('#pleaseWait').show();
	var clientId = $('#clientMasterId').html();
	var userId = $('#clientUserId').html();
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"clientId" : clientId,
			"userId" : userId,
			"visitId" : visitId
		},
		url : "client/getPatientAnalysis",
		catche : false,
		error : function() {
			$("#pleaseWait").hide();
			alert("error");
		},
		success : function(r) {
			$("#pleaseWait").hide();
			$('#analysisModal').modal('show');
			var divContent="";
			var index=1;
			for(var i=0;i<r.patientParameter.length;i++)
			{
				var parameterValue = r.patientParameter[i].parameterValue;
				if(r.patientParameter[i].naFlag=="true"){
					parameterValue = "";
				}
				divContent=divContent+"<tr><td class='center'>"+index+"</td><td>"+r.patientParameter[i].addedOn+"</td><td>"+r.patientParameter[i].testDescription+"</td><td>"+r.patientParameter[i].parameterName+"</td>" +
						"<td>"+parameterValue+"</td><td>"+r.patientParameter[i].lowerValue+"</td><td>"+r.patientParameter[i].upperValue+"</td>"
				+"<td>"+r.patientParameter[i].unitName+"</td> <td>"+r.patientParameter[i].testResultStatus+"</td>"
				+"<td><select class='analysisStatus' id='parameterAnalysisSatus_"+r.patientParameter[i].reportId+"@@"+clientId+"@@"+r.patientParameter[i].testId+"@@"+r.patientParameter[i].parameterId+"'>"
				+"<option value=''>Select</option>";
				if(r.patientParameter[i].statusId=="1"){
					divContent=divContent + "<option value='1' selected>Normal</option>";
				}else{
					divContent=divContent + "<option value='1'>Normal</option>";
				}
				if(r.patientParameter[i].statusId=="2"){
					divContent=divContent + "<option value='2' selected>Abnormal</option>";
				}else{
					divContent=divContent + "<option value='2'>Abnormal</option>";
				}
				if(r.patientParameter[i].lowerValue!="-" && r.patientParameter[i].statusId=="3"){
					divContent=divContent + "<option value='3' selected>Urgent</option>";
				}else if(r.patientParameter[i].lowerValue!="-"){
					divContent=divContent + "<option value='3'>Urgent</option>";
				}
				if(r.patientParameter[i].lowerValue!="-" && r.patientParameter[i].statusId=="4"){
					divContent=divContent + "<option value='4' selected>Borderline</option>";
				}else if(r.patientParameter[i].lowerValue!="-"){
					divContent=divContent + "<option value='4'>Borderline</option>";
				}
				divContent=divContent +"</select></td>"
				+"<td><textarea class='analysisComment' id='parameterAnalysisComment_"+r.patientParameter[i].reportId+"@@"+clientId+"@@"+r.patientParameter[i].testId+"@@"+r.patientParameter[i].parameterId+"' style='width: 236px; height: 30px;' maxlength='5000' disabled>"+r.patientParameter[i].analysisComment+"</textarea></td></tr>";
				index++;
			}
			$('#analysisParameterList').html(divContent);
			
			index=1;
			var analysisComments = "";
			for(var j=0;j<r.analysisComment.length;j++){
				analysisComments = analysisComments + "<tr id='commentType_"+r.analysisComment[j].commentTypeId+"'><td class='center'>"+index+"</td><td val='"+r.analysisComment[j].commentTypeId+"'>"+r.analysisComment[j].comment+"</td><td style='max-width: 50px; word-wrap: break-word;'>"+r.analysisComment[j].analysisComment+"</td>"
				+"<td><button onclick='viewAnalysisComment(this)' style='margin-right: 5px;' class='btn btn-success btn-xs'>"
				+"<span class='glyphicon glyphicon-eye-open'></span> </button>"
				+"<button onclick='removeAnalysisComment(this)' class='btn btn-danger btn-xs'><span class='glyphicon glyphicon-remove'></span> </button></td></tr>";
			  index++;
			  
			  $('#analysisAddedOn').val(r.analysisComment[j].addedOn);
			  $('#clientClassification').val(r.analysisComment[j].classification);
			}
			$("#analysisComments").html(analysisComments);
			
			//Demoghraphic tab
			if(r.profilePicture!=null && r.profilePicture!="" && r.profilePicture!="null"){
				$('#profilePictureImg').attr('src',"readImage?url="+r.profilePicture);
			}
			else{
				$('#profilePictureImg').attr('src',"resources/assets/images/default-user.png");
			}
			$('#displayFirstNameTd').html(r.firstName);
			$('#displayMiddleNameTd').html(r.middleName);
			$('#displayLastNameTd').html(r.lastName);
			$('#displayDOBTd').html(r.clientDOB);
			$('#displayGenderTd').html(r.gender);
			$('#displayHeightTd').html(r.height);
			$('#displayWeightTd').html(r.weight);
			$('#displayBloodGroupTd').html(r.bloodGroup);
			$('#displayNomineeRelationTd').html(r.nomineeRelation);
			$('#displayNomineeFirstNameTd').html(r.nomineeFirstName);
			$('#displayNomineeMiddleNameTd').html(r.nomineeMiddleName);
			$('#displayNomineeLastNameTd').html(r.nomineeLastname);
			$('#displayNomineeDOBTd').html(r.nomineeDOB);
			$('#displayAddress1Td').html(r.address1);
			$('#displayAddress2Td').html(r.address2);
			$('#displayAddress3Td').html(r.address3);
			$('#displayCityNameTd').html(r.city);
			$('#displayStateNameTd').html(r.state);
			$('#displayCountryNameTd').html(r.country);
			$('#displayPincodeTd').html(r.pincode);
			$('#displayMobNoTd').html(r.mobile);
			$('#displayLandlineNoTd').html(r.landline);
			$('#displayEmailIdTd').html(r.email);
			$('#displayUserNameTd').html(r.userName);
		}
	});
}

function loadInteractionPage(){
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/interaction",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfCentresInteraction();
			listOfActionMasterInteraction();
			listOfEmailTemplateMasterInteraction();
			//$('#pleaseWait').hide();
		}
	});
};

function listOfCentresInteraction(){
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
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].centreId+"'>"+r[i].centreName+"</option>";
			}
			$('#centerSelectListInteraction').html(divContent);
		}
	});
}

function listOfActionMasterInteraction(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "action/listOfActionMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].actionId+"'>"+r[i].action+"</option>";
			}
			$('#actionSelectListInteraction').html(divContent);
		}
	});
}

function listOfSubActionMasterInteraction(actionId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data :{
			"actionId" : actionId
		},
		url : "subAction/listOfSubActionMasterByActionId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].subActionId+"'>"+r[i].subAction+"</option>";
			}
			$('#subActionSelectListInteraction').html(divContent);
		}
	});
}

function saveFollowUpRecord(){
	var followUpDate=$("#followUpDate").val();
	var time=$('#timepicker-default').val();
	var actionId=$('#actionSelectListInteraction option:selected').val();
	var centerId=$('#centerSelectListInteraction option:selected').val();
	var activities=$('#activitiesInteraction').val();
	var comment=$('#commentInteraction').val();
	var callResultStatus=$('#callResultStatus').val();
	var clientId=$('#clientMasterId').html();
	var subActionId=$('#subActionSelectListInteraction option:selected').val();
	var status=$('#engmtStatus').val();
	var visitId=$("#workVisitId").val();
	if(actionId==""){
		alert("Please Select Action");
	}
	/*else if(subActionId==""){
		alert("Please Select SubAction");
	}*/
	else{
	jQuery.ajax({
		type : "POST",
		data : {
			"followUpDate" : followUpDate,
			"time" : time,
			"actionId" : actionId,
			"centerId" : centerId,
			"activities" : activities,
			"comment" : comment,
			"callResultStatus" : callResultStatus,
			"clientId" : clientId,
			"subActionId" : subActionId,
			"status" : status,
			"visitId" : visitId
		},
		url : "client/saveFollowUpRecord",
		error : function() {
			alert("error");
		},
		success : function(r) {
			listOfFollowUpRecord(visitId);
			clearFollowUpRecord();
			$('#followUpRecordModal').modal('hide');
			alert("Follow-up Record Saved Successfully");
		}
	});
	}
}

function listOfFollowUpRecord(clientId){
	jQuery.ajax({
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "client/listOfFollowUpRecord",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++) {
				divContent=divContent+"<tr id='follow_"+r[i].ehrFollowUpRecordId+"'><td class='center'>"+index+"</td><td>"+r[i].addedOn+"</td><td>"+r[i].followUpDate+"</td><td>"+r[i].engmtStatus+"</td><td><a onclick='getFollowUpRecordById("+r[i].ehrFollowUpRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
				index++;
			}
			$('#engmntTblBody').html(divContent);
		}
	});
}

function clearFollowUpRecord(){
	$("#followUpDate").val("");
	//$('#timepicker-default').val("");
	$('#actionSelectListInteraction').val("");
	$('#subActionSelectListInteraction').html("");
	$('#centerSelectListInteraction').val("");
	$('#activitiesInteraction').val("");
	$('#commentInteraction').val("");
	$('#engmtStatus').val(0);
	$('.followUpPop').removeAttr('disabled');
	$('#showSubAction').hide();
	$('#subActionSelectListInteraction').show();
	$('#saveFollowUpRecordBtn').show();
}

function getFollowUpRecordById(ehrFollowUpRecordId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"ehrFollowUpRecordId" : ehrFollowUpRecordId
		},
		url : "client/getFollowUpRecordById",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#saveFollowUpRecordBtn').hide();
			$("#followUpDate").val(r.followUpDate);
			$('#timepicker-default').val(r.time);
			$('#actionSelectListInteraction').val(r.actionId);
			$('#centerSelectListInteraction').val(r.centerId);
			$('#activitiesInteraction').val(r.activities);
			$('#commentInteraction').val(r.comment);
			$('#callResultStatus').val(r.callResultStatus);
			$('#engmtStatus').val(r.engmtStatusId)
			$('#showSubAction').val(r.subAction);
			$('#showSubAction').show();
			$('#subActionSelectListInteraction').hide();
			$('.followUpPop').attr('disabled','disabled');
		}
	});
}

function loadAccountManagementPage(){
	$('#currentPageId').html("11");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/accountManagement",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfUserTypeMaster();
			listOfUserMaster();
			listOfCenterAccount();
			listOfPackageMasterAccount();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
};

function loadUserAccessManagementPage(){
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/userAccessManagement",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			$('#currentPageId').html("12");
			getUserAccess();
			//setTimeout(function(){ getUserAccess(); }, 300);
			listOfUserAccessMaster();
		}
	});
};

function loadHRATypeMasterPage(){
	$('#currentPageId').html("13");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/HRATypeMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfHraTypeMasterTableBody();
			//setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
};

function loadQuestionMasterPage(){
	$('#currentPageId').html("14");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/HRAQuestionMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfHraTypeMasterDropDown();
			setTimeout(function(){ getUserAccess(); }, 10);
		}
	});
};

function loadParameterMasterPage(){
	$('#currentPageId').html("15");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/parameterMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfTestMaster();
			listOfParameterMaster();
			setTimeout(function(){ getUserAccess(); }, 70);
		}
	});
};

function loadCenterWiseParameterMasterPage(){
	$('#currentPageId').html("16");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/parameterValues",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfTestMasterTableBody();
			getListOfCentres();
			listOfUnitMasterCentreWise();
			setTimeout(function(){ getUserAccess(); }, 100);
		}
	});
};

function loadUnitMasterPage(){
	$('#currentPageId').html("17");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/unitMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfUnitMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadpackageTestRelation(){
	$('#currentPageId').html("43");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/packageTestRelation",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			getPackageNameList();
			getTestNameList();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};



function getClientCenterPackageByUserId(clientId){
//	alert(clientId);
	//var visitId=$('#visitSelectOnMyReportPage').val().split("_")[1];
	var visitId=$('#visitSelectOnMyReportPage').val();
	if(visitId == null || visitId == "" || visitId == undefined){
		visitId="visit_0";
	}
	//alert(visitId);
	
	$('#pleaseWait').show();
	jQuery.ajax({
		async : false,
		type : "POST",
		data : {
			clientId : clientId,
			visitId  : visitId
		},
		url : "client/getClientCenterPackageByUserId",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			if(r.packageMaster!=null) {
				$('.packageNameSpan').html(r.packageMaster.packageDescription);
				$('.packageNameSpan').attr("id",r.packageMaster.packageId);
			}
			if(r.centreMaster!=null){
				$('.centerNameSpan').html(r.centreMaster.centreName);
				$('.centerNameSpan').attr("id",r.centreMaster.centreId);
			}
			if(r.checkUpMasterSet[0]!=null){
				if(r.checkUpMasterSet[0].checkUpDate!=null){
					$('.lastCheckUpDate').html((r.checkUpMasterSet[0].checkUpDate).split(" ")[0]);
				}
				$('.lastCheckUpDate').attr("id",r.checkUpMasterSet[0].checkUpId);
				
				if($('#currentPageId').html()=="4"){
					getVisitByVisitId(r.checkUpMasterSet[0].checkUpId);
				}
			}
			$('#emailCount').html(r.addAt);
			$('#smsCount').html(r.modAt);
			$('#pleaseWait').hide();
		}
	});
}

function loadRejectMasterPage(){
	$('#currentPageId').html("18");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/rejectMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfRejectMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadTemplateMasterPage(){
	$('#currentPageId').html("19");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/templateMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfTemplateMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadSmsTemplateMasterPage(){
	$('#currentPageId').html("20");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/smsTemplateMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfSmsTemplateMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
}

function loadActionMasterPage(){
	$('#currentPageId').html("21");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/actionMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfActionMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadSubActionMasterPage(){
	$('#currentPageId').html("22");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/subActionMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfActionMaster();
			listOfSubActionMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadVisitTypeMasterPage(){
	$('#currentPageId').html("23");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		/*data : {
			userId : userId
		},*/
		url : "/IndusHealth/indus/visitTypeMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfVisitTypeMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadFollowUpPage(){
	$('#pleaseWait').show();
	var clientId = $('#clientMasterId').html();
	jQuery.ajax({
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "/IndusHealth/indus/followUpPage",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			setTimeout(function(){ getUserAccess(); }, 300);
			listOfCentresInteraction();
			listOfActionMasterInteraction();
			listOfFollowUpRecord($('#clientMasterId').html());
			getClientCenterPackageByUserId($('#clientMasterId').html());
			listOfCommentMasterForAnalysis();
			$('#viewAnalysisBtn').attr("onclick","getPatientAnalysis('"+$('#clientMasterId').html()+"','"+$('#clientUserId').html()+"')");
		//	$('#currentPageId').html("21");
		}
	});
};

function loadContactHubPage(){
	$('#pleaseWait').show();
	var clientId = $('#clientMasterId').html();
	jQuery.ajax({
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "/IndusHealth/indus/contactHubPage",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			setTimeout(function(){ getUserAccess(); }, 300);
			getEmailSmsRecordByClientId($('#clientMasterId').html());
			getClientCenterPackageByUserId($('#clientMasterId').html());
		}
	});
};

function clientRegistrationPage(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "POST",
		url : "/IndusHealth/indus/clientRegistrationPage",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfCentresRegistration();
			listOfPackageRegistration();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function clientIntegrationPage(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/clientIntegrationPage",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function bunchUploadPage(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/bunchUploadPage",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function contentArticlePage(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/contentArticlePage",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfContentMaster();
			categoryTable();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function contentPreviewPage(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/contentPreviewPage",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			categories();
			//subCategories();
			contentsPreview();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function loadFeedTypeMaster(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "POST",
		url : "/IndusHealth/indus/feedTypeMaster",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfFeedTypeMaster();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function loadHealthFeedMaster(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "POST",
		url : "/IndusHealth/indus/healthFeedMaster",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfFeedTypeMasterSelect();
			listOfHealthFeedMaster();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function loadUploadVideoMaster(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "POST",
		url : "/IndusHealth/indus/uploadVideoMaster",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfVideoMaster();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function loadLinkMasterPage(){
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "POST",
		url : "/IndusHealth/indus/linkMaster",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfLinkMaster();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
}

function frequencyMasterPage(){
	$('#currentPageId').html("38");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/frequencyMasterPage",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfFrequencyMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function listOfFrequencyMasterForReport() {
	jQuery.ajax({
		type : "GET",
		url : "frequency/listOfFrequencyMaster",
		success : function(r) {
			var divContent = "";
			divContent = divContent
					+ "<option value=''>Select Frequency</option>";
			for (var i = 0; i < r.length; i++) {
				divContent = divContent + "<option value='" + r[i].frequencyId
						+ "'>" + r[i].frequency + "</option>";
			}
			$('.frequencyList').html(divContent);
		}
	});
}

function listOfTestMasterForReport() {
	jQuery.ajax({
		async : false,
		type : "POST",
		url : "client/getListOftestMaster",
		catche : false,
		success : function(r) {
			var divContent = "";
			divContent = divContent + "<option value=''>Select Test</option>";
			for (var i = 0; i < r.length; i++) {
				divContent = divContent + "<option value='" + r[i].testId
						+ "'>" + r[i].testDescription + "</option>";
			}
			//setTimeout(function() {
				$('.testList').html(divContent);
				$(function() {
					$('.chosen-select').chosen();
					$('.chosen-select-deselect').chosen({
						allow_single_deselect : true
					});
				});
			//}, 200);
		}
	});
}

function parameterDetailsPage(){
	$('#currentPageId').html("36");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/parameterDetailsPage",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfFrequencyMasterForReport();
			listOfParameterMasterForReport();
			listOfTestMasterForReport();
			setTimeout(function(){ getUserAccess(); }, 300);
		}
	});
};

function hraDetailsPage(){
	$('#currentPageId').html("37");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/hraDetailsPage",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfQuestionMasterForReport();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadCategoryMasterPage(){
	$('#currentPageId').html("33");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/categoryMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfCategoryMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function loadSubCategoryMasterPage(){
	$('#currentPageId').html("34");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/subCategoryMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfCategoryMaster();
			listOfSubCategoryMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function analysisCommentMasterPage(){
	$('#currentPageId').html("39");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/analysisCommentMaster",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			listOfCommentMaster();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function workManagementPage(){
	$('#currentPageId').html("40");
	$('#pleaseWait').show();
	jQuery.ajax({
		type : "GET",
		url : "/IndusHealth/indus/workManagement",
		error : function() {
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#mainBody').html(r);
			if($("#isLeader").val() == "Y"){
				if($("#userTypeId").val()=="7"){
					$("#reportVerificationTab").removeClass("in active");
					$("#doctorAnalysisTab").addClass("in active");
					getWork("10");
				}else {
					getWork("1");
					$("#doctorAnaTab").removeClass("active");
				}
			} else {
				if($("#userTypeId").val()=="12"){
					$("#reportVeriTab").attr("onclick","getWork('3')");
					$("#dateEntrTab").attr("onclick","getWork('6')");
					$("#dateEntrVerTab").attr("onclick","getWork('9')");
					getWork("3");
				}else{
					$("#reportVeriTab").attr("onclick","getWork('2')");
					$("#dateEntrTab").attr("onclick","getWork('5')");
					$("#dateEntrVerTab").attr("onclick","getWork('8')");
					$("#doctorAnaTab").attr("onclick","getWork('11')");
					$("#healthRepoTab").attr("onclick","getWork('13')");
					$("#healthReportVerTab").attr("onclick","getWork('15')");
					
					if($("#userTypeId").val()=="10"){
						$("#reportVerificationTab").removeClass("in active");
						$("#doctorAnalysisTab").addClass("in active");
						getWork("11");
					} else {
						getWork("2");
					}
				}
			}
			listOfUserTypeMaster();
			listOfRejectMasterDropDown();
			setTimeout(function(){ getUserAccess(); }, 30);
		}
	});
};

function healthReportBtn(visitId) {
	$('#healthReportBtn').attr('href','hra/healthReport?clientId='+$("#clientMasterId").html()+'&visitId='+visitId);
}

function reportInteraction(){
	/*listOfVisitTypeMasterInteraction();
	getVisitDateListInteraction($('#clientMasterId').html());
	setTimeout(function(){ */
		getVisitByVisitIdInteraction($('#visitSelectOnMyReportPage').val().split("_")[1]); 
	//}, 300);
}

function getVisitByVisitIdInteraction(visitId){
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
			if(r.length>0)
			{	
				var index=1;
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
					if(r[i].reportVerificationSet.length>0){
						if(r[i].reportVerificationSet[0].verifyComment!=null){
							//alert(r[i].clientReportHead.centreMaster.centreName);  // readPdf?pdfFileName=
							divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'><a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].verifyComment+"'><i class='fa fa-comment color_green' data-placement='right' data-toggle='tooltip' class='btn btn-primary' type='button' data-original-title='"+r[i].reportVerificationSet[0].rejectComment+"'></i></a></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
							+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
							+"<a onclick='viewReport(\""+r[i].filePath+"\",\""+r[i].reportDescription+"\")' value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
							/*+"<a onclick='editReport("+r[i].clientReportLineId+");' class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit' data-target='#uploadReportModal' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
							+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"
							+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"*/
							+"</div></td></tr>";
							index++;
						}
						/*if(r[i].reportVerificationSet[0].rejectComment!=null){
							//console.log(r[i].reportVerificationSet[0].rejectComment);
							divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'><a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].rejectMaster.rejectReason+"'><i class='fa fa-exclamation-triangle "+editReportIconColor+"'></i></a> <a href='#' data-toggle='tooltip' title='"+r[i].reportVerificationSet[0].rejectComment+"'><i class='fa fa-comment "+editReportIconColor+"'></i></a></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
							+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
							+"<a onclick='viewReport('"+r[i].filePath+"','"+r[i].reportDescription+"')' value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
							+"<a onclick='editReport("+r[i].clientReportLineId+");' class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit' data-target='#uploadReportModal' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
							+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"
							+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
							+"</div></td></tr>";
							$('#verificationFailed').removeClass('hidden');
						}*/
					}
					/*else{
						divContent=divContent+"<tr><td class='center'>"+index+"</td><td style='width: 50px;'></td><td id='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td class='reportUploader' id='uploadedBy_"+r[i].clientReportLineId+"'>"+r[i].clientReportHead.addedBy+"</td>"
						+"<td class='reportUploaderDate' id='uploadedOn_"+r[i].clientReportLineId+"'>"+addedOn[0]+"</td><td id='uploadTime_"+r[i].clientReportLineId+"'>"+r[i].reportDate+"</td><td style='width: 90px;'><div>"
						+"<a onclick=viewReport('"+r[i].filePath+"','"+r[i].reportDescription+"') value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
						+"<a onclick='editReport("+r[i].clientReportLineId+");' class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit' data-target='#uploadReportModal' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
						+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"
						+"<a onclick='deleteReport("+r[i].clientReportLineId+","+clientId+");' class='btn btn-transparent btn-xs tooltips deleteReport' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
						+"</div></td></tr>";
					}*/
					//index++;
					
					/*divContent2=divContent2+"<tr><td></td><td>"+r[i].reportDescription+"</td><td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td>"+r[i].reportDate+"</td><td>"+fileName+"</td><td></td><td></td></tr>";
					<span class='btn btn-danger btn-xs' onclick='removeReport()'><i class='glyphicon glyphicon-ban-circle'></i><span>Remove</span></span>
					$('#reportLength').html(r.length);*/
				}
			}
			else
			{
				divContent=divContent+"<b>No Reports Available</b>";
				divContent2=divContent2+"<b>No Reports Available</b>";
				$('#reportLength').html("");
			}	
			$("#uploadReportDataInteraction").html(divContent);
			
			//$("#displayReportTableBody").html(divContent2);
			
			/*if($('#clientLoginUserType').html()==1){
				//alert($('#clientLoginUserType').html());
				$('.reportUploader').hide();
				$('.reportUploaderDate').hide();
			}
			else{
				$('.reportUploader').show();
				$('.reportUploaderDate').show();
			}
			getUserAccess();*/
		}
	});
}

//for handling Analysis modal hide
$(document).on('hide.bs.modal','#analysisModal', function () {
	jQuery.ajax({
		type : "POST",
		url : "client/removeClientLocked",
		data : {
			"clientId" : $("#clientMasterId").html()
		},
		error : function() {
			//alert("error in webservice");
		},
		success : function(r) {
			//alert("success");
		}
	});
});

function changeVisitSelectListInteraction(current){
	var visitDate=$(current).find('option:selected').attr("class");//$('#visitSelect option:selected').attr('class');
	$('#showVisitDate').val(visitDate.split("_")[0]);
	$('#showVisitDateOnMyReportPage').val(visitDate.split("_")[0]);
	
	$('#visitTypeMasterSelect').val(visitDate.split("_")[1]);
	$('#visitTypeMasterSelectOnMyReportPage').val(visitDate.split("_")[1]);
	
	$('#visitSelect').val($('#visitSelectOnMyReportPage').val());
	var visitId=($(current).val()).split("_")[1];
	getVisitByVisitIdInteraction(visitId);
	getListOfQuestionClientHRADetailsInteraction(visitId);
	getAnalysis(visitId);
}

function listOfHraTypeMasterInteraction(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "hra/listOfHraTypeMaster",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var div="";
			for(var i=0;i<r.length;i++)
			{
				//var divName=(r[i].hraTypeName).replace(/\s/g, '');
				div=div+"<div class='row' id='history_"+r[i].hraTypeId+"Div'><h4 class='col-md-offset-1' style='margin-top: 15px;'>"+r[i].hraTypeName+"</h4></div>";
			}
			$('#hraQuestionDiv').html(div);
			getListOfQuestionClientHRADetailsInteraction(($('#visitSelectOnMyReportPage').val()).split("_")[1]);
		}
	});
}

function getListOfQuestionClientHRADetailsInteraction(visitId){
	var clientId=$('#clientMasterId').html();
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"clientId" : clientId,
			"visitId":visitId
		},
		url : "hra/getListOfQuestionClientHRADetails",
		//timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var index=1;
			var questionIndex=1;
			var optionIndex=1;
			var divContent="";
			for(var i=0;i<r.length;i++)
			{
				$("#history_"+r[i].hraTypeId+"Div").html("<h4 class='col-md-offset-1 col-sm-offset-1 col-xs-offset-2' style='margin-top: 15px;'>"+r[i].hraTypeName+"</h4>");
			}
			for(var i=0;i<r.length;i++)
			{
				if(r[i].optionSet.length>0){
				for(var j=0;j<r[i].optionSet.length;j++){
					divContent=divContent+"<div class='col-md-3'>"+romanize(optionIndex)+".<label><input name='optionSet"+i+"' style='margin: 4px 5px 0;' class='markOption_"+r[i].optionSet[j].optionId+" option_"+r[i].questionId+"_"+r[i].optionSet[j].optionId+"' type="+r[i].questionType+" value=" +r[i].optionSet[j].option+" disabled>"+r[i].optionSet[j].option+"</label></div>";
					optionIndex++;
				}
				}
				else{
					divContent=divContent+"<div class='col-md-12'><textarea class='question_"+r[i].questionId+"' style='width: 500px; height: 50px;' disabled>"+r[i].textAnswer+"</textarea></div>";
					index++;
				}
				optionIndex=1;
				$("<div class='row question'><div class='col-md-offset-1 col-sm-offset-1 col-xs-offset-2'><h5 class='row'>"
								+ ($("#history_"+r[i].hraTypeId+"Div .question").length + 1) + ". " + r[i].question
								+ "</h5><div class='row'>"+divContent+"</div></div></div>")
				.appendTo("#history_"+r[i].hraTypeId+"Div");
				
				divContent="";
				questionIndex++;
				
				if(r[i].optionSet.length>0){
				var options=[];
				if(r[i].textAnswer!=null && r[i].textAnswer!=""){
				options=(r[i].textAnswer).split(",");
				for(var k=0;k<options.length;k++){
					var optionMarkId=options[k];
					$(".markOption_"+optionMarkId).attr('checked','checked');
				}
				}
			}
			}
			
		}
	});
}

function romanize(num) {
	  var lookup = {M:1000,CM:900,D:500,CD:400,C:100,XC:90,L:50,XL:40,X:10,IX:9,V:5,IV:4,I:1},
	      roman = '',
	      i;
	  for ( i in lookup ) {
	    while ( num >= lookup[i] ) {
	      roman += i;
	      num -= lookup[i];
	    }
	  }
	  return roman;
}

function agreeTermsAndCondition(){
	var isChecked=document.getElementById("agreeTermsAndCondition").checked;
	if(isChecked){
		$('#acceptTermsAndConditionBtn').removeClass("disabled");
	}else{
		$('#acceptTermsAndConditionBtn').addClass("disabled");
	}
}

function acceptTermsAndCondition(){
	var isChecked=document.getElementById("agreeTermsAndCondition").checked;
	if(isChecked){
		jQuery.ajax({
			async : true,
			type : "POST",
			url : "client/acceptTermsAndConditions",
			catche : false,
			error : function() {
				
			},
			success : function(r) {
				$('#termsAndConditionModal').modal('hide');
				$( window ).unbind( "click" );
			}
		});
	}else{
		alert("Please accept terms and conditions");
	}
}

function callWebServiceGetBeneficiary() {
	if($("#startDate").val() == ""){
		alert("Please Select Start Date");
		return false;
	}else if($("#endDate").val() == ""){
		alert("Please Select End Date");
		return false;
	}
    jQuery.ajax({
		type : "GET",
		data : {
			"fromDate" : $("#startDate").val(),
			"toDate" : $("#endDate").val()
		},
		url : "webService/callWebServiceGetBeneficiary",
		error : function() {
			alert("error in webservice");
		},
		success : function(r) {
			alert("callWebServiceGetBeneficiary success");
		}
	});
}

function callWebServiceGetCheckUpStatus(){
    jQuery.ajax({
		async : true,
		type : "POST",
		url : "webService/callWebServiceGetCheckUpStatus",
		catche : false,
		error : function() {
			alert("error in callWebServiceGetCheckUpStatus");
		},
		success : function(r) {
			alert("callWebServiceGetCheckUpStatus success");
		}
	});
}

function callWebServiceUpdateEHRID(){
    jQuery.ajax({
		type : "POST",
		url : "webService/callWebServiceUpdateEHRID",
		error : function() {
			alert("error in callWebServiceUpdateEHRID");
		},
		success : function(r) {
			alert("callWebServiceUpdateEHRID success");
		}
	});
}

function GetCounsellorAppointment(){
	jQuery.ajax({
		type : "GET",
		url : "webService/GetCounsellorAppointment",
		error : function() {
			alert("error in GetCounsellorAppointment");
		},
		success : function(r) {
			alert("GetCounsellorAppointment success");
		}
	});
}

function getAllReminder(){
    jQuery.ajax({
		async : true,
		type : "POST",
		url : "reminder/getAllReminder",
		catche : false,
		error : function() {
			alert("error in getAllReminder");
		},
		success : function(r) {
			alert("getAllReminder success");
		}
	});
}

//irfan khan 20-nov-2018
function loadContactHubPageNew(){
	$('#currentPageId').html("41");
	$('#pleaseWait').show();
	//var clientId = $('#clientMasterId').html();
	jQuery.ajax({
		type : "POST",
		/*data : {
			"clientId" : clientId
		},*/
		url : "/IndusHealth/indus/loadContactHubPageNew",
		error : function() {
			$('#pleaseWait').hide();
			//alert("error");
		},
		success : function(r) {
			$('#mainBody').html(r);
			setTimeout(function(){ getUserAccess(); }, 300);
			//getEmailSmsRecordByClientId($('#clientMasterId').html());
			//getClientCenterPackageByUserId($('#clientMasterId').html());
		}
	});
};