$("#recordSelectList").change(function() {
	var value=$('#recordSelectList option:selected').val();
	var startIndex = 0;
	if(value!="" && value!=null){
	  jQuery.ajax({
			type : "POST",
			data : {
				value : value,
				startIndex : startIndex
			},
			url : "client/clientTestResultsDropDown",
			error : function() {
				alert("error");
			},
			success : function(r) {
				var str = "";
				var numberOfRows="";
				var index=1;
				var count=r.count;
				var numberOfPages=(count/10);
				var displayPagination=numberOfPages;
				if(numberOfPages>5){
					numberOfRows +="<li class='disabled previous'><a><i class='ti-angle-left'></i></a></li>";
					displayPagination=5;
				}
				for(var j=0;j<displayPagination;j++){
					numberOfRows +="<li onclick='paginationDropDown("+index+")'><a>"+index+"</a></li>";
					index=index+1;
				}
				if(numberOfPages>6){
					numberOfRows +="<li class='next' onclick='nextPaginationDropDown("+index+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
				}
				for ( var i = 0; i < r.listClientMasters.length; i++) {
					str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td class='interactionClientSelect'>" + r.listClientMasters[i].firstName
							+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].lastName
							+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].mobNo + "</td>" 
							/*+ "<td class='interactionClientSelect'>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
							+ "<td class='interactionClientSelect'>" + r.listClientMasters[i].addAt
							+ "</td><td>"+r.listClientMasters[i].addBy+" <input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"' for='"+r.listClientMasters[i].clientId+"'></td></tr>";
				}
				$('#totalNumberOfPages').html("<li><a>No. Of Pages:"+(Math.round(numberOfPages)+1)+"</a></li>");
				$('#patientRecordPagination').html(numberOfRows);
				$('#clientRecordTable').html(str);
				if(r.listClientMasters.length==0){
					$('#totalNumberOfPages').html("");
				}
			}
		});
	}
	else{
		$('#clientRecordTable').html("");
	}
});

function paginationDropDown(pageNumber){
	var value=$('#recordSelectList option:selected').val();
	var startIndex = (pageNumber-1)+"0";
	var inputs = [];
	inputs.push('value=' + value);
	inputs.push('startIndex='+startIndex);
	var str = inputs.join('&');
	jQuery.ajax({
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/clientTestResultsDropDown",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var str = "";
			for ( var i = 0; i < r.listClientMasters.length; i++) {
				str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td class='interactionClientSelect'>" + r.listClientMasters[i].firstName
						+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].lastName
						+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].mobNo + "</td>" 
						/*+ "<td class='interactionClientSelect'>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
						+ "<td class='interactionClientSelect'>" + r.listClientMasters[i].addAt
						+ "</td><td>" + r.listClientMasters[i].addBy+" <input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"'></td></tr>";
			}
			$('#clientRecordTable').html(str);
		}
	});
}

function nextPaginationDropDown(currentIndex,numberOfPages){
	var displayPagination=currentIndex+5;
	var numberOfRows='';
	numberOfRows +="<li class='previous' onclick='previousPaginationDropDown("+currentIndex+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
	if(numberOfPages<displayPagination){
		displayPagination=numberOfPages+1;
		//numberOfRows +="<li class='next disabled' onclick='nextPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
	}
	for(var j=currentIndex;j<displayPagination;j++){
		numberOfRows +="<li onclick='paginationDropDown("+j+")'><a>"+j+"</a></li>";
	}
	if(numberOfPages>displayPagination){
		numberOfRows +="<li class='next' onclick='nextPaginationDropDown("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
	}
		$('#patientRecordPagination').html(numberOfRows);
}

function previousPaginationDropDown(currentIndex,numberOfPages){
	var displayPagination=currentIndex-5;
	var numberOfRows='';
	if(currentIndex>6){
		numberOfRows +="<li class='previous' onclick='previousPaginationDropDown("+displayPagination+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
	}
	for(var j=displayPagination;j<currentIndex;j++){
		numberOfRows +="<li onclick='paginationDropDown("+j+")'><a>"+j+"</a></li>";
	}
		numberOfRows +="<li class='next' onclick='nextPaginationDropDown("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
		$('#patientRecordPagination').html(numberOfRows);
}

function getRecordByDate() {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
				var startIndex = 0;
				var inputs = [];
				inputs.push('startDate=' + startDate);
				inputs.push('endDate=' + endDate);
				inputs.push('startIndex='+startIndex);
				var str = inputs.join('&');
				jQuery.ajax({
					type : "POST",
					data : str + "&reqType=AJAX",
					url : "client/getClientMastersByAnalysisResults",
					error : function() {
						alert("error");
					},
					success : function(r) {
						var str = "";
						var numberOfRows="";
						var index=1;
						var count=r.count;
						var numberOfPages=(count/10);
						var displayPagination=numberOfPages;
						if(numberOfPages>5){
							numberOfRows +="<li class='disabled previous'><a><i class='ti-angle-left'></i></a></li>";
							displayPagination=5;
						}
						for(var j=0;j<displayPagination;j++){
							numberOfRows +="<li onclick='pagination("+index+")'><a>"+index+"</a></li>";
							index=index+1;
						}
						if(numberOfPages>6){
							numberOfRows +="<li class='next' onclick='nextPagination("+index+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
						}
						for ( var i = 0; i < r.listClientMasters.length; i++) {
							str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td class='interactionClientSelect'>" + r.listClientMasters[i].firstName
									+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].lastName
									+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].mobNo + "</td>" 
									/*+ "<td class='interactionClientSelect'>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
									+ "<td class='interactionClientSelect'>" + r.listClientMasters[i].addAt
									+ "</td><td>" + r.listClientMasters[i].addBy+" <input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"'></td></tr>";
						}
						$('#totalNumberOfPages').html("<li><a>No. Of Pages:"+Math.round(numberOfPages)+"</a></li>");
						$('#patientRecordPagination').html(numberOfRows);
						$('#clientRecordTable').html(str);
					}
				});
			}
			else{
				alert("Please select date range for search");
			}
		}

function pagination(pageNumber){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	var startIndex = (pageNumber-1)+"0";
	var inputs = [];
	inputs.push('startDate=' + startDate);
	inputs.push('endDate=' + endDate);
	inputs.push('startIndex='+startIndex);
	var str = inputs.join('&');
	jQuery.ajax({
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/getClientMastersByAnalysisResults",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var str = "";
			for ( var i = 0; i < r.listClientMasters.length; i++) {
				str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td class='interactionClientSelect'>" + r.listClientMasters[i].firstName
						+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].lastName
						+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].mobNo + "</td>" 
						/*+ "<td class='interactionClientSelect'>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
						+ "<td class='interactionClientSelect'>" + r.listClientMasters[i].addAt
						+ "</td><td>" + r.listClientMasters[i].addBy+" <input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"'></td></tr>";
			}
			$('#clientRecordTable').html(str);
		}
	});
}

function nextPagination(currentIndex,numberOfPages){
	var displayPagination=currentIndex+5;
	var numberOfRows='';
	numberOfRows +="<li class='previous' onclick='previousPagination("+currentIndex+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
	if(numberOfPages<displayPagination){
		displayPagination=numberOfPages+1;
		//numberOfRows +="<li class='next disabled' onclick='nextPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
	}
	for(var j=currentIndex;j<displayPagination;j++){
		numberOfRows +="<li onclick='pagination("+j+")'><a>"+j+"</a></li>";
	}
	if(numberOfPages>displayPagination){
		numberOfRows +="<li class='next' onclick='nextPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
	}
		$('#patientRecordPagination').html(numberOfRows);
}

function previousPagination(currentIndex,numberOfPages){
	var displayPagination=currentIndex-5;
	var numberOfRows='';
	if(currentIndex>6){
		numberOfRows +="<li class='previous' onclick='previousPagination("+displayPagination+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
	}
	for(var j=displayPagination;j<currentIndex;j++){
		numberOfRows +="<li onclick='pagination("+j+")'><a>"+j+"</a></li>";
	}
		numberOfRows +="<li class='next' onclick='nextPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
		$('#patientRecordPagination').html(numberOfRows);
}
    
    $('#patientRecordPagination').on('click', 'li', function () {
    	$(this).addClass('active').siblings().removeClass('active');
    });
    
    //$('#clientRecordTable').on('click', 'tr', function () {
    $('#clientRecordTable').on('click', '.interactionClientSelect', function () {
    	//$('#pleaseWait').show();
    	var userId=$(this).parent('tr').attr('id');//$(this).attr('id');
    	var clientId=$(this).parent('tr').attr('class');
    	jQuery.ajax({
			type : "POST",
			data : {
				clientId : clientId
			},
			url : "client/isClientLockedByUserId",
			error : function() {
				alert("error");
				$('#pleaseWait').hide();
			},
			success : function(r) {
				$('#pleaseWait').hide();
				if(r.clientLocked==1){
					alert("Sorry client is locked by user : "+r.remark);
				}else{
					$('#pleaseWait').show();
    	$('#clientUserId').html(userId);
    	$('#clientMasterId').html(clientId);
    	jQuery.ajax({
			type : "GET",
			data : {
				clientId : clientId
			},
			url : "client/displayPatientDemoghraphic",
			error : function() {
				//alert("error");
				$('#pleaseWait').hide();
			},
			success : function(r) {
				$('#mainBody').html(r);
				$('#currentPageId').html("3");
				getClientTimeline($('#clientMasterId').html());
				listOfVisitTypeMasterDropDown();
				getVisitDateList($('#clientMasterId').html());
				$('#interactionPageId').html("1");
				setTimeout(function(){ getUserAccess(); }, 10);
			}
		});
				}
				}
			});
    });
    
function assignDate(){
	 $('#endDate').val($.datepicker.formatDate('mm/dd/yy', new Date())); 
	 $('#emailEndDate').val($.datepicker.formatDate('mm/dd/yy', new Date())); 
}

$("#searchBox").autocomplete({
	 source : function(request, response) {
		 var searchKeyword = $("#searchBox").val();
			jQuery.ajax({
				type : "POST",
				data : {
					searchKeyword : searchKeyword
				},
				url : "client/autoSuggestionClientAnalysisResults",
				timeout : 1000 * 60 * 5,
				catche : false,
				error : function() {
					alert('error');
				},
				success : function(r) {
					var availableTags = [];
					for(var i=0;i<r.length;i++){
						availableTags[i]=r[i].clientFullName+"_"+r[i].clientId;
					}
					response(availableTags);
				}
			});
	 	},
	 	select: function(event, ui) {
	 		var searchKeyword = ui.item.value;
	 		var id=searchKeyword.split("_");
	 		getRecordByName(id[1]);
	 	}
});
	
$( "#searchBox" ).autocomplete("widget").addClass("fixedHeight");

function getRecordByName(clientId) {
	$('#clientMasterId').html(clientId);
	jQuery.ajax({
		type : "POST",
		data : {
			clientId : clientId
		},
		url : "client/getClientMasterRecordByName",
		error : function() {
			alert("error");
		},
		success : function(r) {
				var str = "";
					str += "<tr style='cursor: pointer;' id='"+r.userId+"' class='"+clientId+"'><td class='interactionClientSelect'>" + r.firstName
							+ "</td><td class='interactionClientSelect'>" + r.lastName
							+ "</td><td class='interactionClientSelect'>" + r.mobNo + "</td>" 
							/*+ "<td class='interactionClientSelect'>"+ r.packageMaster.packageDescription+"</td>"*/
							+ "<td class='interactionClientSelect'>" + r.addAt
							+ "</td><td>" + r.addBy+" <input type='checkbox' class='check' id='"+r.emailId+"' for='"+clientId+"' value='"+r.mobNo+"'></td></tr>";
			$('#clientRecordTable').html(str);
			$('#patientRecordPagination').html("");
			$('#totalNumberOfPages').html("");
		}
	});
}

/*function listOfCentresInteraction(){
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
	//alert(followUpDate+"**"+time+"**"+actionId+"**"+centerId+"**"+activities+"**"+comment);
	if(actionId==""){
		alert("Please Select Action");
	}
	else if(subActionId==""){
		alert("Please Select SubAction");
	}
	else{
	jQuery.ajax({
		async : true,
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
			"subActionId" : subActionId
		},
		url : "client/saveFollowUpRecord",
		error : function() {
			alert("error");
		},
		success : function(r) {
			listOfFollowUpRecord($('#clientMasterId').html());
			clearFollowUpRecord();
			$('#followUpRecordModal').modal('hide');
			alert("Follow-up Record Saved Successfully");
		}
	});
	}
}

function listOfFollowUpRecord(clientId){
	jQuery.ajax({
		async : true,
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
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr id='follow_"+r[i].ehrFollowUpRecordId+"'><td class='center'>"+index+"</td><td>"+r[i].addedOn+"</td><td>"+r[i].followUpDate+"</td><td>"+r[i].action+"</td><td><i class='"+r[i].callResultStatus+"'></i></td><td><a onclick='getFollowUpRecordById("+r[i].ehrFollowUpRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
				index++;
			}
			//$('#unitId').val(index);
			$('#followUpTableBody').html(divContent);
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
	$('#saveFollowUpRecordBtn').show();
}

function newFollowUpBtn(){
	$('#showSubAction').hide();
	$('#subActionSelectListInteraction').show();
	$('.followUpPop').removeAttr('disabled');
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
			$('#followUpRecordModal').modal('show');
			$('#saveFollowUpRecordBtn').hide();
			$("#followUpDate").val(r.followUpDate);
			$('#timepicker-default').val(r.time);
			$('#actionSelectListInteraction').val(r.actionId);
			$('#centerSelectListInteraction').val(r.centerId);
			$('#activitiesInteraction').val(r.activities);
			$('#commentInteraction').val(r.comment);
			$('#callResultStatus').val(r.callResultStatus);
			
			$('#showSubAction').val(r.subAction);
			$('#showSubAction').show();
			$('#subActionSelectListInteraction').hide();
			$('.followUpPop').attr('disabled','disabled');
		}
	});
}*/

function listOfEmailTemplateMasterInteraction(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "template/listOfTemplateMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Email Template</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option id='emailTemplate_"+r[i].templateId+"' value='"+r[i].templateId+"' class='"+r[i].templateDescription+"'>"+r[i].templateName+"</option>";
			}
			$('#emailTemplateSelectListInteraction').html(divContent);
		}
	});
}

function listOfSmsTemplateMasterInteraction(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "template/listOfSmsTemplateMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Sms Template</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option id='smsTemplate_"+r[i].smsTemplateId+"' value='"+r[i].smsTemplateId+"' class='"+r[i].smsTemplateDescription+"'>"+r[i].smsTemplateName+"</option>";
			}
			$('#smsTemplateSelectListInteraction').html(divContent);
		}
	});
}

function sendEmailAndSmsBtn(){
	var seletedClient=[];
	var strEmail="";
	var strSms="";
	$("input:checkbox[class=check]").each(function () {
        //alert("Id: " + $(this).attr("id") + " Value: " + $(this).val() + " Checked: " + $(this).is(":checked"));
        if($(this).is(":checked")==true){
        	seletedClient.push($(this).attr("id")+"&"+$(this).val());
        	
        	strEmail=strEmail+"<div class='vR'><input type='text' class='strEmail hidden' value='"+$(this).attr("id")+"&"+$(this).attr("for")+"'><span class='vN'>"+
					"<div class='vT'>"+$(this).attr("id")+"</div><div class='vM' id="+$(this).attr("id")+" onclick='removeEmailContact(this)'></div></span></div>";
        	
        	strSms=strSms+"<div class='vR'><input type='text' class='strSms hidden' value='"+$(this).val()+"&"+$(this).attr("for")+"'><span class='vN'>"+
			"<div class='vT'>"+$(this).val()+"</div><div class='vM' id="+$(this).val()+" onclick='removeSmsContact(this)'></div></span></div>";
        	
        }
	});
	
	$('#emailSentToList').html(strEmail);
	$('#smsSentToList').html(strSms);
	
	if(seletedClient.length==0){
		alert("Please select atleast one client");
	}
	else{
	$('#sendEmailAndSmsModal').modal('show');
	clearSendEmailAndSmsPopUpDiv();
	listOfEmailTemplateMasterInteraction();
	listOfSmsTemplateMasterInteraction();
	}
}

function sendEmailAndSmsContactHubPageBtn(){
	
	var strEmail="<div class='vR'><input type='text' class='strEmail hidden' value='"+$('#clientEmailId').html()+"&"+$('#clientMasterId').html()+"'><span class='vN'>"+
	"<div class='vT'>"+$('#clientEmailId').html()+"</div><div class='vM' id="+$('#clientEmailId').html()+" onclick='removeEmailContact(this)'></div></span></div>";

	var strSms="<div class='vR'><input type='text' class='strSms hidden' value='"+$('#clientMobileNo').html()+"&"+$('#clientMasterId').html()+"'><span class='vN'>"+
	"<div class='vT'>"+$('#clientMobileNo').html()+"</div><div class='vM' id="+$('#clientMobileNo').html()+" onclick='removeSmsContact(this)'></div></span></div>";
	
	$('#emailSentToList').html(strEmail);
	$('#smsSentToList').html(strSms);	

	$('#sendEmailAndSmsModal').modal('show');
	clearSendEmailAndSmsPopUpDiv();
	listOfEmailTemplateMasterInteraction();
	listOfSmsTemplateMasterInteraction();
}

function clearSendEmailAndSmsPopUpDiv(){
	$('#emailTemplateSelectListInteraction option:selected').val("");
	//$('#emailMedicalAdvice').html("");
	$('.ckeditor').val("");
	$('#smsTemplateSelectListInteraction option:selected').val();
	$('#smsMedicalAdvice').val("");
	$('#emailSubject').val("");
}

function emailTemplateChange(){
	var templateId=$('#emailTemplateSelectListInteraction option:selected').val();
	var templateDescription=$("#emailTemplate_"+templateId).attr("class");
	//$('#emailMedicalAdvice').html(templateDescription);
	$('.ckeditor').val(templateDescription);
	if(templateId==""){
		//$('#emailMedicalAdvice').html("");
		$('.ckeditor').val("");
	}
}

function smsTemplateChange(){
	var templateId=$('#smsTemplateSelectListInteraction option:selected').val();
	var templateDescription=$("#smsTemplate_"+templateId).attr("class");
	$('#smsMedicalAdvice').val(templateDescription);
	if(templateId==""){
		$('#smsMedicalAdvice').html("");
	}
}

function sendEmailAndSms(){
	/*var seletedClient=[];
	$("input:checkbox[class=check]").each(function (){
        if($(this).is(":checked")==true){
        	seletedClient.push($(this).attr("id")+"&"+$(this).val()+"&"+$(this).attr("for"));
        }
	});*/
	var emailClient=[];
	var smsClient=[];
	$('.strEmail').each(function(){
		emailClient.push($(this).val());
	});
	$('.strSms').each(function(){
		smsClient.push($(this).val());
	});
	if(emailClient.length==0 && smsClient.length==0){
		alert("Please select atleast one client");
	}
	else{
	var emailTemplateId=$('#emailTemplateSelectListInteraction option:selected').val();
	var smsTemplateId=$('#smsTemplateSelectListInteraction option:selected').val();
	var emailMedicalAdvice=$('.ckeditor').val();//$('#emailMedicalAdvice').val();
	var smsMedicalAdvice=$('#smsMedicalAdvice').val();
	var emailSubject=$('#emailSubject').val();
	
	if(emailTemplateId=="" && smsTemplateId==""){
		alert("Please select atleast email or sms template");
	}
	
	if(emailTemplateId!="" || smsTemplateId!=""){
		$('#pleaseWait').show();
		//alert(emailTemplateId+" "+smsTemplateId+" "+emailMedicalAdvice+" "+smsMedicalAdvice+" "+seletedClient+" "+emailSubject+" "+clientId);
		$('#sendEmailAndSmsModal').modal('hide');
		jQuery.ajax({
			async : true,
			type : "POST",
			data : {
				"emailTemplateId" : emailTemplateId,
				"smsTemplateId" : smsTemplateId,
				"emailMedicalAdvice" : emailMedicalAdvice,
				"smsMedicalAdvice" : smsMedicalAdvice,
				"emailSubject" : emailSubject,
				"emailClient" : emailClient,
				"smsClient" : smsClient
			},
			url : "client/sendEmailAndSmsMedicalAdvice",
			catche : false,
			error : function() {
				//alert("error");
				$('#pleaseWait').hide();
			},
			success : function(r) {
				$('#pleaseWait').hide();
				alert(r);
			}
		});
	}
  }
}

function getEmailRecordByClientId(clientId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "client/getEmailRecordByClientId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr id='email_"+r[i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getEmailRecordByEmailId("+r[i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
				index++;
			}
			if(r==0){
				alert("No data to display");
			}
			$('#contactHubTableBody').html(divContent);
		}
	});
}

function getSmsRecordByClientId(clientId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "client/getSmsRecordByClientId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr id='sms_"+r[i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getSmsRecordBySmsId("+r[i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
				index++;
			}
			if(r==0){
				alert("No data to display");
			}
			$('#contactHubTableBody').html(divContent);
		}
	});
}

function getEmailRecordByEmailId(emailId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"ehrEmailId" : emailId
		},
		url : "client/getEmailRecordByEmailId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#emailSmsModal').modal('show');
			$('#emailSubjectPopUpDiv').show();
			$('#emailSentTo').html(r.sentToEmail);
			$('#emailSubjectPopUp').html(r.emailSubject);
			$('#emailBody').html(r.emailMedicalAdvice);
		}
	});
}

function getSmsRecordBySmsId(smsId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"ehrSmsId" : smsId
		},
		url : "client/getSmsRecordBySmsId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#emailSmsModal').modal('show');
			$('#emailSentTo').html(r.sentToMobNo);
			$('#emailBody').html(r.smsMedicalAdvice);
			$('#emailSubjectPopUpDiv').hide();
		}
	});
}

function getEmailSmsRecordByClientId(clientId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"clientId" : clientId
		},
		url : "client/getEmailSmsRecordByClientId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r[0].length;i++)
			{
				divContent=divContent+"<tr id='email_"+r[0][i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[0][i].sentBy+"</td><td>"+r[0][i].sentOn+"</td><td><a onclick='getEmailRecordByEmailId("+r[0][i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
				index++;
			}
			for(var i=0;i<r[1].length;i++)
			{
				divContent=divContent+"<tr id='sms_"+r[1][i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[1][i].sentBy+"</td><td>"+r[1][i].sentOn+"</td><td><a onclick='getSmsRecordBySmsId("+r[1][i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
				index++;
			}
			if(r[0]==0 && r[1]==0){
				alert("No data to display");
			}
			$('#contactHubTableBody').html(divContent);
		}
	});
}

//$('#getEmailSmsForm').change(function(){
function getEmailSmsForm(){
	var value = $("input[name='contactHubValues']:checked").val();
	var clientId=$('#clientMasterId').html();
	if(value==="email"){
		getEmailRecordByClientId(clientId);
	}
	if(value==="sms"){
		getSmsRecordByClientId(clientId);
	}
	if(value==="all"){
		getEmailSmsRecordByClientId(clientId);
	}
};

function getEmailAndSmsRecordByDate(){
	var value = $("input[name='contactHubValues']:checked").val();
	var clientId=$('#clientMasterId').html();
	if(value==="email"){
		getEmailRecordByDate(clientId);
	}
	if(value==="sms"){
		getSmsRecordByDate(clientId);
	}
	if(value==="all"){
		getEmailSmsRecordByDate(clientId);
	}
}

function getEmailSmsRecordByDate(clientId){
	var startDate = $('#emailStartDate').val();
	var endDate = $('#emailEndDate').val();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var inputs = [];
		inputs.push('startDate=' + startDate);
		inputs.push('endDate=' + endDate);
		inputs.push('clientId='+clientId);
		var str = inputs.join('&');
		jQuery.ajax({
			type : "POST",
			data : str + "&reqType=AJAX",
			url : "client/getEmailSmsRecordByDate",
			error : function() {
				alert("error");
			},
			success : function(r) {
				var divContent="";
				var index=1;
				for(var i=0;i<r[0].length;i++)
				{
					divContent=divContent+"<tr id='email_"+r[0][i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[0][i].sentBy+"</td><td>"+r[0][i].sentOn+"</td><td><a onclick='getEmailRecordByEmailId("+r[0][i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				for(var i=0;i<r[1].length;i++)
				{
					divContent=divContent+"<tr id='sms_"+r[1][i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[1][i].sentBy+"</td><td>"+r[1][i].sentOn+"</td><td><a onclick='getSmsRecordBySmsId("+r[1][i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				if(r[0]==0 && r[1]==0){
					alert("No data to display");
				}
				$('#contactHubTableBody').html(divContent);
			}
		});
	}
	else{
		alert("Please select date range for search");
	}
}

function getEmailRecordByDate(clientId){
	var startDate = $('#emailStartDate').val();
	var endDate = $('#emailEndDate').val();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var inputs = [];
		inputs.push('startDate=' + startDate);
		inputs.push('endDate=' + endDate);
		inputs.push('clientId='+clientId);
		var str = inputs.join('&');
		jQuery.ajax({
			type : "POST",
			data : str + "&reqType=AJAX",
			url : "client/getEmailRecordByDate",
			error : function() {
				alert("error");
			},
			success : function(r) {
				var divContent="";
				var index=1;
				for(var i=0;i<r.length;i++)
				{
					divContent=divContent+"<tr id='email_"+r[i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getEmailRecordByEmailId("+r[i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				if(r==0){
					alert("No data to display");
				}
				$('#contactHubTableBody').html(divContent);
			}
		});
	}
	else{
		alert("Please select date range for search");
	}
}

function getSmsRecordByDate(clientId){
	var startDate = $('#emailStartDate').val();
	var endDate = $('#emailEndDate').val();
	if(startDate!=null && startDate!="" && endDate!=null && endDate!=""){
		var inputs = [];
		inputs.push('startDate=' + startDate);
		inputs.push('endDate=' + endDate);
		inputs.push('clientId='+clientId);
		var str = inputs.join('&');
		jQuery.ajax({
			type : "POST",
			data : str + "&reqType=AJAX",
			url : "client/getSmsRecordByDate",
			error : function() {
				alert("error");
			},
			success : function(r) {
				var divContent="";
				var index=1;
				for(var i=0;i<r.length;i++)
				{
					divContent=divContent+"<tr id='sms_"+r[i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getSmsRecordBySmsId("+r[i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				if(r==0){
					alert("No data to display");
				}
				$('#contactHubTableBody').html(divContent);
			}
		});
	}
	else{
		alert("Please select date range for search");
	}
}

function removeEmailContact(id){
	$(id).parent().parent().remove();
}

function removeSmsContact(id){
	$(id).parent().parent().remove();
}