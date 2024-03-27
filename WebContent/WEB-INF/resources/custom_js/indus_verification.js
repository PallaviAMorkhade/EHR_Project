function getVerifyUploadedReport(clientId,visitId) {
	/*var inputs = [];
	inputs.push('clientId=' + clientId);
	var str = inputs.join('&');*/
	jQuery
			.ajax({
				async : true,
				type : "POST",
				/*data : str + "&reqType=AJAX",
				url : "/IndusHealth/indus/clientUploadReport",
				timeout : 1000 * 60 * 5,*/
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
					var userTypeId = $('#userTypeId').val();
					if(r.length>0)
					{	
						var testId="";
						var packageId="";
						var analysisFlag="N";
						var centerId="";
						
						var index=1;
						var addedOn=[];
						for(var i=0;i<r.length;i++)
						{
							var editReportIconColor="color_red";
							if(r[i].editReportStatus==1){
								editReportIconColor="color_orange";
							}
							
							if(r[i].testMaster!=null && r[i].testMaster!=""){
								testId=r[i].testMaster.testId;
							}
							else{
								testId=0;
							}
							if(r[i].packageMaster!=null && r[i].packageMaster!=""){
								packageId=r[i].packageMaster.packageId;
								analysisFlag=r[i].packageMaster.analysisFlag;
							}
							else{
								packageId=0;
							}
							if(r[i].clientReportHead.centreMaster!=null && r[i].clientReportHead.centreMaster!=""){
								centerId=r[i].clientReportHead.centreMaster.centreId;
							}
							else{
								centerId=0;
							}
							
							addedOn=(r[i].clientReportHead.addedOn).split(" ");
							if(r[i].reportVerificationSet.length>0){
								if(r[i].reportVerificationSet[0].verifyComment!=null){
									//console.log(r[i].reportVerificationSet[0].verifyComment);
									divContent=divContent+"<tr class='"+testId+"#"+packageId+"#"+centerId+"#"+r[i].clientReportLineId+"'><td class='center'>"+index+"</td><td style='width: 264px;'>"+r[i].reportDescription+"</td><td style='width: 218px;'>"+r[i].clientReportHead.centreMaster.centreName+"</td><td>"+r[i].clientReportHead.addedBy+"</td>"
									+"<td>"+addedOn[0]+"</td><td>"+addedOn[1]+"</td><td> <span class='color_green'>Verified <i class='fa fa-check'></i></span></td><td><div>"
									+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='btn btn-transparent btn-xs check next_"+index+"' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a></div></td>"
									/*+"<td><button type='button' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#verifyUploadReportModal'><span>Verify</span></button>"
									+"<td><div class='checkbox clip-check check-primary'><input type='checkbox' class='check' id='"+r[i].filePath+"' value='"+r[i].clientReportLineId+"'><label for='"+r[i].filePath+"'></label></div></td>"*/
									+"<td style='display:none;'><div id='comment_"+r[i].clientReportLineId+"' class='verifyComment'>"+r[i].reportVerificationSet[0].verifyComment+"</div></td>";
									/*+"<td><div> <a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='btn btn-transparent btn-xs check next_"+index+"' tooltip-placement='top' tooltip='Edit'><i class='fa fa-send'></i></a></div></td>"*/
									
									if(analysisFlag == 'Y'){
										if(r[i].isEmailSend == 'Y'){
											divContent=divContent +"<td><div> <a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' onclick=SendEmail('"+r[i].clientReportLineId+"',this.id,'"+clientId+"','"+visitId+"') tooltip-placement='top' tooltip='Edit'>  <span class='color_orangered'>Email <i class='fa fa-send' style='color: orangered'></i></span></a></div></td>";
										}else{
											divContent=divContent +"<td><div> <a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' onclick=SendEmail('"+r[i].clientReportLineId+"',this.id,'"+clientId+"','"+visitId+"') tooltip-placement='top' tooltip='Edit'>  <span class='color_orangered'>Email <i class='fa fa-send' style='color: black'></i></span></a></div></td>";
										}
										
									}
									+"</tr>";
								}
								if(r[i].reportVerificationSet[0].rejectComment!=null){
									//console.log(r[i].reportVerificationSet[0].rejectComment);
									divContent=divContent+"<tr class='"+testId+"#"+packageId+"#"+centerId+"#"+r[i].clientReportLineId+"'><td class='center'>"+index+"</td><td style='width: 264px;'>"+r[i].reportDescription+"</td><td style='width: 218px;'>"+r[i].clientReportHead.centreMaster.centreName+"</td><td>"+r[i].clientReportHead.addedBy+"</td>"
									+"<td>"+addedOn[0]+"</td><td>"+addedOn[1]+"</td><td> <span class='"+editReportIconColor+"'>Rejected <i class='fa fa-close'></i></span></td><td><div>"
									+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='btn btn-transparent btn-xs check next_"+index+"' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a></div></td>"
									/*+"<td><button type='button' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#verifyUploadReportModal'><span>Verify</span></button>"
									+"<td><div class='checkbox clip-check check-primary'><input type='checkbox' class='check' id='"+r[i].filePath+"' value='"+r[i].clientReportLineId+"'><label for='"+r[i].filePath+"'></label></div></td>"*/
									+"<td style='display:none;'><div id='comment_"+r[i].clientReportLineId+"' class='rejectComment'>"+r[i].reportVerificationSet[0].rejectComment+"</div><div id='reason_"+r[i].clientReportLineId+"'>"+r[i].reportVerificationSet[0].rejectMaster.rejectId+"</div></td>"
									+"</tr>";
								}
							}
							else{
							divContent=divContent+"<tr class='"+testId+"#"+packageId+"#"+centerId+"#"+r[i].clientReportLineId+"'><td class='center'>"+index+"</td><td style='width: 264px;'>"+r[i].reportDescription+"</td><td style='width: 218px;'>"+r[i].clientReportHead.centreMaster.centreName+"</td><td>"+r[i].clientReportHead.addedBy+"</td>"
							+"<td>"+addedOn[0]+"</td><td>"+addedOn[1]+"</td><td></td><td><div>"
							+"<a value='"+r[i].clientReportLineId+"' id='"+r[i].filePath+"' target='_blank' class='btn btn-transparent btn-xs check next_"+index+"' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a></div></td>"
							/*+"<td><button type='button' class='btn btn-primary btn-xs' data-toggle='modal' data-target='#verifyUploadReportModal'><span>Verify</span></button>" 
							+"<td><div class='checkbox clip-check check-primary'><input type='checkbox' class='check' id='"+r[i].filePath+"' value='"+r[i].clientReportLineId+"'><label for='"+r[i].filePath+"'></label></div></td>"*/
							+"</tr>";
							}
							index++;
							
							/*+"<a href='#' class='btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit' data-target='.editTestModal' data-toggle='modal'><i class='fa fa-pencil'></i></a>"
							+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Share'><i class='fa fa-share'></i></a>"
							+"<a href='#' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"*/
							/* <td class="hidden-xs"> <span class="color_red">Rejected <i class="fa fa-close"></i></span></td>*/
						}
						$('#lastId').html(index);
					}
					else
					{
						divContent=divContent+"<b>No Reports Available</b>";
					}	
					$("#verifiedReportData").html(divContent);
					$("#verifiedReportDataAnalysis").html(divContent);
				}
			});
	getEmailIdToDisplay(clientId);
	return true;
}

$('#verifiedReportData,#verifiedReportDataAnalysis').on('click', '.check', function () {
	$('#verifyUploadReportModal').modal('show');
	$('#displayPatientReport').attr('data',"readPdf?pdfFileName="+$(this).attr('id'));
	$("#clientReportName").html($(this).attr('id'));
	$('#clientReportLineId').html($(this).attr('value'));
	$('#ocrValues').html($(this).parents('tr').attr('class'));
	$('#verifyReportBtn').addClass('disabled');
	var nextClass=$(this).attr('class');
	var nextId=nextClass.split("_");
	$('#nextId').html(nextId[1]);
	//clear verify selection
	$('#verifyComment').val("");
	$('#reasonToReject').val("");
	$('#rejectComment').val("");
	$('#selectAll').prop('checked', false);
	$('#checkbox1').prop('checked', false);
	$('#checkbox2').prop('checked', false);
	$('#checkbox3').prop('checked', false);
	$('#checkbox4').prop('checked', false);
	//Retrieve comment
	var reportId = $('#clientReportLineId').html();
	var commentClass = $('#comment_'+reportId).attr('class');
	var comment = $('#comment_'+reportId).html();
	if(commentClass == "verifyComment"){
		$('#verifyComment').val(comment);
		$('#selectAll').prop('checked', true);
		$('#checkbox1').prop('checked', true);
		$('#checkbox2').prop('checked', true);
		$('#checkbox3').prop('checked', true);
		$('#checkbox4').prop('checked', true);
		$('#verifyReportBtn').removeClass('disabled');
	}
	else if(commentClass == "rejectComment"){
		$('#rejectComment').val(comment);
		$('#reasonToReject').val($('#reason_'+reportId).html());
	}
	
	 /*var checked = $(this).is(':checked');
     $('#verifiedReportData').find('input[type=checkbox]').each(function(){
           document.getElementById($(this).attr('id')).checked = false;
    });
    if(checked)
        $(this).prop("checked", true); 
		checked=$(this).attr('id');
		clientReportLineId=$(this).val();
		patentReportPath=checked;*/
});

/*$('#verifySelectionBtn').click(function(){
		$('#displayPatientReport').attr('src',"readPdf?pdfFileName="+patentReportPath);
		$('#clientReportLineId').html(clientReportLineId);
});*/

$('#selectAll').click(function(){
	var checked = $(this).is(':checked');
	if(checked==true){
	$('#checkAll').find('input[type=checkbox]').each(function(){
        document.getElementById($(this).attr('id')).checked = true;
	});
	$('#verifyReportBtn').removeClass('disabled');
	}
	else{
		$('#checkAll').find('input[type=checkbox]').each(function(){
	        document.getElementById($(this).attr('id')).checked = false;
		});
		$('#verifyReportBtn').addClass('disabled');
	}
});

$('#checkAll').find('input[type=checkbox]').click(function(){
	var checkAll=[];
	$('#checkAll').find('input[type=checkbox]').each(function(){
        checkAll.push(document.getElementById($(this).attr('id')).checked);
	});
	if(checkAll[0]==true && checkAll[1]==true && checkAll[2]==true && checkAll[3]==true){
		document.getElementById("selectAll").checked = true;
		$('#verifyReportBtn').removeClass('disabled');
	}
	else{
		document.getElementById("selectAll").checked = false;
		$('#verifyReportBtn').addClass('disabled');
	}
});

/*$('#verifyReportBtn').click(function(){*/
function verifyReport(){
	var clientReportLineId=$('#clientReportLineId').text();
	var verifyComment=$('#verifyComment').val();
	var clientId=$('#clientMasterId').html();
	var clientReportName=$("#clientReportName").html();
	var inputs = [];
	inputs.push('clientReportLineId=' + clientReportLineId);
	inputs.push('verifyComment=' + verifyComment);
	inputs.push('clientId=' + clientId);
	inputs.push('clientReportName=' + clientReportName);
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/verifyClientUploadReport",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			if($("#visitSelectOnMyReportPage").val()!=null){
				getVerifyUploadedReport($('#clientMasterId').html(),$("#visitSelectOnMyReportPage").val().split("_")[1]);
			}else if($("#workVisitId").val()!=""){
				getVerifyUploadedReport($('#clientMasterId').html(),$("#workVisitId").val());
			}
			//ocrConversionAndSave(); //OCR function
			sendSmsAndMail(); //For Sending Email and SMS
			alert("Report Verified Successfully");
		}
	});
};

function sendSmsAndMail(){
	var clientId=$('#clientMasterId').html();
	jQuery.ajax({
		type : "POST",
		data : {"clientId":clientId},
		url : "client/sendSmsAndMail",
		error : function() {
			//alert("error");
		},
		success : function(r) {
			//alert("in send sms and mail");
		}
	});
}

/*$('#rejectReportBtn').click(function(){*/
function rejectReport(){
	var clientReportLineId=$('#clientReportLineId').text();
	var rejectComment=$('#rejectComment').val();
	var reasonToReject=$('#reasonToReject').val();
	//var clientId=$('#clientId').text();
	if(reasonToReject!=null && reasonToReject!="" && rejectComment!=null && rejectComment!=""){
	var inputs = [];
	inputs.push('clientReportLineId=' + clientReportLineId);
	inputs.push('rejectComment=' + rejectComment);
	inputs.push('reasonToReject=' + reasonToReject);
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/rejectClientUploadReport",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			if($("#visitSelectOnMyReportPage").val()!=null){
				getVerifyUploadedReport($('#clientMasterId').html(),$("#visitSelectOnMyReportPage").val().split("_")[1]);
			}else if($("#workVisitId").val()!=""){
				getVerifyUploadedReport($('#clientMasterId').html(),$("#workVisitId").val());
			}
			//$('#verifyUploadReportModal').hide();
			alert("Report rejected");
		}
	});
	}
	else{
		alert("Please enter reject comment and reason to reject");
	}
};

function listOfRejectMasterDropDown(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "reject/listOfRejectMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].rejectId+"'>"+r[i].rejectReason+"</option>";
			}
			$('#reasonToReject').html(divContent);
		}
	});
}

function nextReport(){
	var id=$('#nextId').html();
	var next=parseInt(id)+1;
	var lastId=parseInt($('#lastId').html());
	if(next<lastId){
		var nextId=$('.next_'+next).attr('id');
		var nextReportId=$('.next_'+next).attr('value');
		$('#displayPatientReport').attr('data',"readPdf?pdfFileName="+nextId);
		$('#clientReportLineId').html(nextReportId);
		$('#nextId').html(next);
		$('#ocrValues').html($('.next_'+next).parents('tr').attr('class'));
		
		//clear verify selection
		$('#verifyComment').val("");
		$('#reasonToReject').val("");
		$('#rejectComment').val("");
		$('#selectAll').attr('checked', false);
		$('#checkbox1').attr('checked', false);
		$('#checkbox2').attr('checked', false);
		$('#checkbox3').attr('checked', false);
		$('#checkbox4').attr('checked', false);
		$('#verifyReportBtn').addClass('disabled');
	}
	else{
		alert("This is last report");
	}
}

function ocrConversionAndSave(){
	var ocrValues=$('#ocrValues').html();
	var fileName=$('#displayPatientReport').attr('src').split("=")[1];
    jQuery.ajax({
		async : true,
		type : "POST",
		data :{
			"ocrValues" : ocrValues,
			"fileName" : fileName
		},
		url : "ocr/ocrConversionAndSave",
		catche : false,
		error : function() {
			//alert("error in ocrConversionAndSave");
		},
		success : function(r) {
			//alert("ocrConversionAndSave success");
		}
	});
}

//Added by kishor for send email after variefied reports
function SendEmail(clientMasterId,clientReportName,clientId,visitId){
	var EmailId=$('#clientEmailId').text();
	var r = confirm("Are you sure you want Send Email On "+EmailId);
	if (r == true) {
	var inputs = [];	
	inputs.push('clientId=' + clientMasterId);
	inputs.push('clientReportName=' + clientReportName);
	inputs.push('clientIdd=' + clientId);
	inputs.push('visitId=' + visitId);
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/verifyClientUploadReportIndividual",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			if($("#visitSelectOnMyReportPage").val()!=null){
				getVerifyUploadedReport($('#clientMasterId').html(),$("#visitSelectOnMyReportPage").val().split("_")[1]);
			}else if($("#workVisitId").val()!=""){
				getVerifyUploadedReport($('#clientMasterId').html(),$("#workVisitId").val());
			}
			//ocrConversionAndSave(); //OCR function
			//sendSmsAndMail(); //For Sending Email and SMS
			//alert(r);
			if(r==1){
				alert("Email Sent Successfully.");
			}else if(r==2){
				alert("Sorry Client Flag is Inactive");
			}else if(r== -1){
				alert("Email Sent But No Data Available");
			}else{
				alert("Network Issue");
			}
		}
	});
	}
}

//Added by kishor for get email Id of client
function getEmailIdToDisplay(clientMasterId){
	
	var inputs = [];	
	inputs.push('clientId=' + clientMasterId);
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "client/getEmailIdToDisplay",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			//setTimeout(function(){
				$('#clientEmailId').text(r);
				//}, 10);
		}
	});
	
}

