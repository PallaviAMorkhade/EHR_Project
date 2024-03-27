function getVrifiedReport(clientId,visitId) {
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
						if(r.length>0)
						{	
							var index=1;
							var addedOn=[];
							for(var i=0;i<r.length;i++)
							{
								//addedOn=(r[i].clientReportHead.addedOn).split(" ");
								if(r[i].reportVerificationSet.length>0){
									addedOn=r[i].reportVerificationSet[0].approvedOn;
									if(r[i].reportVerificationSet[0].verifyComment!=null){
										var testId="";
										var packageId="";
										var centerId="";
										if(r[i].testMaster!=null && r[i].testMaster!=""){
											testId=r[i].testMaster.testId;
										}
										else{
											testId=0;
										}
										if(r[i].packageMaster!=null && r[i].packageMaster!=""){
											packageId=r[i].packageMaster.packageId;
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
										
										var dataEntryComment = "<td></td>";
										if(r[i].dataEntryVerifications[0]!=null){
											if(r[i].dataEntryVerifications[0].isRejectVerify){
												dataEntryComment = "<td class='center'><a id='dataEntryComment_"+r[i].clientReportLineId+"' href='"+r[i].dataEntryVerifications[0].isRejectVerify+"' data-toggle='tooltip' title='"+r[i].dataEntryVerifications[0].comment+"'><i class='fa fa-comment color_green' data-placement='right' data-toggle='tooltip' class='btn btn-primary' type='button' data-original-title='"+r[i].dataEntryVerifications[0].comment+"'></i></a></td>"
											}else{
												dataEntryComment = "<td class='center'><a id='dataEntryComment_"+r[i].clientReportLineId+"' href='"+r[i].dataEntryVerifications[0].isRejectVerify+"' data-toggle='tooltip' title='"+r[i].dataEntryVerifications[0].comment+"'><i class='fa fa-comment color_red' data-placement='right' data-toggle='tooltip' class='btn btn-primary' type='button' data-original-title='"+r[i].dataEntryVerifications[0].comment+"'></i></a></td>"
											}
										}
										
										divContent=divContent+"<tr style='cursor: pointer;' class='"+r[i].filePath+"#"+testId+"#"+packageId+"#"+centerId+"' id='"+r[i].clientReportLineId+"'>"
										+"<td class='center'>"+index+"</td>"
										+ dataEntryComment
										+"<td class='testName_"+r[i].clientReportLineId+"'>"+r[i].reportDescription+"</td>"
										+"<td>"+r[i].clientReportHead.centreMaster.centreName+"</td><td>"+r[i].clientReportHead.addedBy+"</td>"
										+"<td>"+addedOn+"</td>"
										+"</tr>";
										index++;
									}
									
								}
								
							}
						}
						else
						{
							divContent=divContent+"<b>No Reports Available</b>";
						}	
						$("#verifiedPatientReportData").html(divContent);
					}
				});
		return true;
}

$('#verifiedPatientReportData').on('click', 'tr', function () {
	var clientReportLineId=$(this).attr('id');
	var testName=$('.testName_'+clientReportLineId).html();
	var currentClass=$(this).attr('class');
	var data=currentClass.split("#");
	$('#dataEntryReportModal').modal('show');
	$('#dataEntryReport').attr('data',"readPdf?pdfFileName="+data[0]);
	$('#dataEntryClientReportLineId').html(clientReportLineId);
	$('#dataEntryTestId').html(data[1]);
	$('#testNameDataEntry').html(testName);
	$('#dataEntryReportText').attr('data',"readTextFile?textFileName="+data[0].substring(0,data[0].lastIndexOf("."))+".txt");
	var workStatus = $("#workStatus").val();
	if(workStatus > "7"){
		$(".dataEntryCommentDiv").show();
	var isRejectVerify = $("#dataEntryComment_"+clientReportLineId).attr("href");
	var dataEntryComment = $("#dataEntryComment_"+clientReportLineId).attr("title");
	$("#dataEntryComment").val(dataEntryComment);
	if(isRejectVerify=="true"){
		$("#verifiedMessage").show();
		$("#rejectedMessage").hide();
		$("#dataEntryVerifyBtn").css("margin-right","4px");
		$("#dataEntryRejectBtn").css("margin-right","69px");
	}else if(isRejectVerify=="false") {
		$("#verifiedMessage").hide();
		$("#rejectedMessage").show();
		$("#dataEntryVerifyBtn").css("margin-right","69px");
		$("#dataEntryRejectBtn").css("margin-right","");
	}else{
		$("#verifiedMessage").hide();
		$("#rejectedMessage").hide();
		$("#dataEntryVerifyBtn").css("margin-right","69px");
		$("#dataEntryRejectBtn").css("margin-right","69px");
	}
	}else{
		$(".dataEntryCommentDiv").hide();
	}
	
	var testId=data[1];
	var packageId=data[2];
	var centerId=data[3];
	if(data[1]==0){
		$('#testReportData').html("");
		$('#centerId').html(centerId);
		$('#packageId').html(packageId);
		$('#displayPackageDiv').show();
		$('#displayTestDiv').hide();
		$('.saveResultBtn').attr('id','savePackageResultBtn');
		jQuery.ajax({
			type : "POST",
			data : {
						"packageId":data[2]
				    },
			url : "client/getTestByPackageIdInSequence",
			error : function() {
				//alert("error");
			},
			success : function(r) {
				var divContent="";
				for(var i=0;i<r.length;i++)
				{
					divContent=divContent+"<li id='test_"+r[i].testId+"' class='samplebutton test_"+r[i].testId+"'>"+r[i].testDescription+"</li>";
				}
				$('#browser').html(divContent);
			}
		});
	}
	else{
		$('#displayPackageDiv').hide();
		$('#displayTestDiv').show();
		$('#packageId').html(packageId);
		$('.saveResultBtn').attr('id','saveTestResultBtn');
	jQuery.ajax({
		type : "POST",
		data : {
					"testId":data[1]
			    },
		url : "client/getParameterByTestId",
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var divContent="";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr><td class='parameterName_"+r[i].parameterId+"'>"+r[i].parameterName+"</td><td><textarea style='width: 87%;' onkeyup='checkStatusOfClient("+r[i].parameterId+","+data[1]+")' class='testResult result_"+i+"' id='parameterResult_"+r[i].parameterId+"_"+data[1]+"' maxlength='5000'></textarea><input type='checkbox' onclick='disabledResult("+r[i].parameterId+","+testId+")' class='naFlag_"+r[i].parameterId+"_"+testId+"'></td><td><span class='hidden' id='generalComment_"+r[i].parameterId+"'></span><span id='lowerValue_"+r[i].parameterId+"'></span> - <span id='upperValue_"+r[i].parameterId+"'></span> <span id='unitName_"+r[i].parameterId+"'></span> <input type='hidden' id='criticalLowValue_"+r[i].parameterId+"' value='"+r[i].criticalLowValue+"'><input type='hidden' id='criticalHighValue_"+r[i].parameterId+"' value='"+r[i].criticalHighValue+"'></td></tr>";
			}
			$('#testReportData').html(divContent);
			getParameterValuesByCenterIdAndTestId(centerId,testId,$('#clientMasterId').html());
			//getTestResultsByClientId($('#clientMasterId').html(),data[1],$('#dataEntryClientReportLineId').html());
		}
		});
	}
});

function checkStatusOfClient(parameterId,testId){
	var lowerValue=parseFloat($("#lowerValue_"+parameterId).html());
	var upperValue=parseFloat($("#upperValue_"+parameterId).html());
	var parameterResult=parseFloat($("#parameterResult_"+parameterId+"_"+testId).val());
	var criticalLowValue = parseFloat($("#criticalLowValue_"+parameterId).val());
	var criticalHighValue = parseFloat($("#criticalHighValue_"+parameterId).val());
	//console.log(lowerValue+"********"+upperValue+"***parameterResult:"+parameterResult);
	if(isNaN(parameterResult)){
		$("#parameterResult_"+parameterId+"_"+testId).css("background-color","");
	}
	else if(parameterResult>upperValue){
		$("#parameterResult_"+parameterId+"_"+testId).css("background-color","#F28383");
	}
	else if(parameterResult<lowerValue){
		$("#parameterResult_"+parameterId+"_"+testId).css("background-color","#F28383");
	}
	else if(parameterResult<=upperValue && parameterResult>=lowerValue){
		$("#parameterResult_"+parameterId+"_"+testId).css("background-color","#AFEFAB");
	}
	
	//for critical value validation
	if(parameterResult<criticalLowValue){
		alertify.error("Value must be greater than Critical low value");
		$("#parameterResult_"+parameterId+"_"+testId).css("border","1px solid red");
		$("#parameterResult_"+parameterId+"_"+testId).addClass("error");
	}else if(parameterResult>criticalHighValue){
		alertify.error("Value must not be greater than Critical high value");
		$("#parameterResult_"+parameterId+"_"+testId).css("border","1px solid red");
		$("#parameterResult_"+parameterId+"_"+testId).addClass("error");
	}else{
		$("#parameterResult_"+parameterId+"_"+testId).css("border","");
		$("#parameterResult_"+parameterId+"_"+testId).removeClass("error");
	}
	
}

/*$('#saveTestResultBtn').click(function(){*/
function saveTestResult(){
	if($("#sample_1 .error").length==0){
	var parameterName=[];
	var parameterNameWithoutValues=[];
	var length=$('.testResult').length+1;
	   for(var i=0;i<length;i++){
		   var id=$('.result_'+i).attr('id');
		   if(id != undefined){
			   var parameterId=id.split("_");
		   if($('.result_'+i).val()!=null && $('.result_'+i).val()!=""){
			   if($("#lowerValue_"+parameterId[1]).html()!="" || $("#upperValue_"+parameterId[1]).html()!="" || $("#generalComment_"+parameterId[1]).html()!=""){
				   parameterName.push($('.result_'+i).val()+"_"+parameterId[1]+"_"+$('#dataEntryTestId').html()+"_"+$('#clientMasterId').html()+"_"+$('#packageId').html()+"_"+$('#dataEntryClientReportLineId').html()+"_"+$(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(':checked')+"_"+$("#lowerValue_"+parameterId[1]).html()+"_"+$("#upperValue_"+parameterId[1]).html());
			   }
			   else{
				   if($(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(":checked")){
					   parameterName.push(0+"_"+parameterId[1]+"_"+parameterId[2]+"_"+$('#clientMasterId').html()+"_"+$('#packageId').html()+"_"+$('#dataEntryClientReportLineId').html()+"_"+$(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(':checked')+"_"+0+"_"+0);
				   }else{
					   parameterNameWithoutValues.push($(".parameterName_"+parameterId[1]).html());
				   }
			   }
			}
		   if($(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(":checked")){
			   parameterName.push(0+"_"+parameterId[1]+"_"+parameterId[2]+"_"+$('#clientMasterId').html()+"_"+$('#packageId').html()+"_"+$('#dataEntryClientReportLineId').html()+"_"+$(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(':checked')+"_"+0+"_"+0);
		   }
		   }
	   }
	   if(parameterNameWithoutValues!=null && parameterNameWithoutValues!=""){
		   alert("First please fill parameter values for this parameter:"+parameterNameWithoutValues);
	   }
	   /*if(parameterName==null && parameterName==""){
		   $("#pleaseWait").hide();
		   alert("Please fill atleast one parameter value");
	   }*/
	   if(parameterName!=null && parameterName!=""){
		   $("#pleaseWait").show();
		   jQuery.ajax({
				type : "POST",
				data : {
						"parameterName":parameterName
						},
				url : "client/saveTestResult",
				error : function() {
					$("#pleaseWait").hide();
				},
				success : function(r) {
					$("#pleaseWait").hide();
					$('#dataEntryReportModal').modal('hide');
					alert("Test Result Saved Successfully");
				}
			});
	   }
	}else{
		alert("Please fill values properly");
	}
};

function savePackageResult(){
	if($("#sample_1 .error").length==0){
	var parameterName=[];
	var parameterNameWithoutValues=[];
	var length=$('.packageResult').length+1;
	   for(var i=0;i<length;i++){
		   var id=$('.result_'+i).attr('id');
		   if(id != undefined){
		   var parameterId=id.split("_");
		   if($('.result_'+i).val()!=null && $('.result_'+i).val()!=""){
			   if($("#lowerValue_"+parameterId[1]).html()!="" || $("#upperValue_"+parameterId[1]).html()!="" || $("#generalComment_"+parameterId[1]).html()!=""){
				   parameterName.push($('.result_'+i).val()+"_"+parameterId[1]+"_"+parameterId[2]+"_"+$('#clientMasterId').html()+"_"+$('#packageId').html()+"_"+$('#dataEntryClientReportLineId').html()+"_"+$(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(':checked')+"_"+$("#lowerValue_"+parameterId[1]).html()+"_"+$("#upperValue_"+parameterId[1]).html());
			   }
			   else{
				   if($(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(":checked")){
					   parameterName.push(0+"_"+parameterId[1]+"_"+parameterId[2]+"_"+$('#clientMasterId').html()+"_"+$('#packageId').html()+"_"+$('#dataEntryClientReportLineId').html()+"_"+$(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(':checked')+"_"+0+"_"+0);
				   }else{
					   parameterNameWithoutValues.push($(".parameterName_"+parameterId[1]).html());
				   }
			   }
		 }
		   if($(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(":checked")){
			   parameterName.push(0+"_"+parameterId[1]+"_"+parameterId[2]+"_"+$('#clientMasterId').html()+"_"+$('#packageId').html()+"_"+$('#dataEntryClientReportLineId').html()+"_"+$(".naFlag_"+parameterId[1]+"_"+parameterId[2]).is(':checked')+"_"+0+"_"+0);
		   }
		   }
	   }
	   if(parameterNameWithoutValues!=null && parameterNameWithoutValues!=""){
		   alert("First please fill parameter values for this parameter:"+parameterNameWithoutValues);
	   }
	   /*if(parameterName==null && parameterName==""){
		   $("#pleaseWait").hide();
		   alert("Please fill atleast one parameter value");
	   }*/
	   if(parameterName!=null && parameterName!=""){
		   $("#pleaseWait").show();
		   jQuery.ajax({
				type : "POST",
				data : {
						"parameterName":parameterName
						},
				url : "client/saveTestResult",
				error : function() {
					$("#pleaseWait").hide();
				},
				success : function(r) {
					$("#pleaseWait").hide();
					$('#dataEntryReportModal').modal('hide');
					alert("Package Result Saved Successfully");
				}
			});
	   }
	}else{
		alert("Please fill values properly");
	}
}

$('.saveResultBtn').click(function(){
	var id=$(this).attr('id');
	if("saveTestResultBtn"===id){
		saveTestResult();
	}
	else{
		savePackageResult();
	}
});

function getParameterValuesByCenterIdAndTestId(centerId,testId,clientId){
	jQuery.ajax({
		async: false,
		type : "POST",
		data : {
				"centerId":centerId,
				"testId":testId,
				"clientId":clientId,
				},
		url : "client/getParameterValuesByCenterIdAndTestId",
		error : function() {
			//alert("error");
		},
		success : function(r) {
			for(var i=0;i<r.length;i++){
				if(r[i].valueFor!=null && r[i].valueFor!="undefined"){
					$("#lowerValue_"+r[i].parameterId).html(r[i].lowerValue);
					$("#upperValue_"+r[i].parameterId).html(r[i].upperValue);
					$("#unitName_"+r[i].parameterId).html(r[i].unitName);
					
					$("#parameterResult_"+r[i].parameterId+"_"+testId).attr("onkeyup","numericOnly(this),checkStatusOfClient("+r[i].parameterId+","+testId+");");
				}
				if(r[i].generalComment!=null && r[i].generalComment!="undefined" && r[i].generalComment!="null"){
					$('#generalComment_'+r[i].parameterId).html(r[i].generalComment);
					$("#parameterResult_"+r[i].parameterId+"_"+testId).val(r[i].generalComment);
				}
			}
			setTimeout(function(){
				getTestResultsByClientId($('#clientMasterId').html(),testId,$('#dataEntryClientReportLineId').html(),centerId);
			}, 200);
		}
	});
}

function getTestResultsByClientId(clientId,testId,reportId,centreId){
	jQuery.ajax({
		async: false,
		type : "POST",
		data : {
				"clientId":clientId,
				"testId":testId,
				"reportId":reportId,
				"centreId" : centreId
				},
		url : "client/getTestResultsByClientId",
		error : function() {
			//alert("error");
		},
		success : function(r) {
			for ( var i = 0; i < r.length; i++) {

				if (r[i].generalComment != null
						&& r[i].generalComment != "undefined"
						&& r[i].generalComment != "null") {
					$('#generalComment_' + r[i].parameterId).html(
							r[i].generalComment);
					$("#parameterResult_" + r[i].parameterId + "_" + testId)
							.val(r[i].generalComment);
				} else {
					$("#lowerValue_" + r[i].parameterId).html(r[i].lowerValue);
					$("#upperValue_" + r[i].parameterId).html(r[i].upperValue);
					$("#unitName_" + r[i].parameterId).html(r[i].unitName);
					if(r[i].lowerValue!= undefined && r[i].lowerValue!= "undefined"){
						$("#parameterResult_"+r[i].parameterId+"_"+testId).attr("onkeyup","numericOnly(this),checkStatusOfClient("+r[i].parameterId+","+testId+")");
					}
				}
				
				if(r[i].naFlag != null && r[i].naFlag == "true"){
					$("#parameterResult_" + r[i].parameterId + "_" + testId)
					.val("");
					$("#parameterResult_" + r[i].parameterId + "_" + testId)
					.attr("disabled","disabled");
					$(".naFlag_" + r[i].parameterId + "_" + testId).prop("checked",true);
				}else if(r[i].parameterValue != null
						&& r[i].parameterValue != "undefined"
						&& r[i].parameterValue != "null") {
					$("#parameterResult_" + r[i].parameterId + "_" + testId)
							.val(r[i].parameterValue);
				}
				checkStatusOfClient(r[i].parameterId, testId);
			}
		}
	});
}

function disabledResult(parameterId, testId){
	$("#parameterResult_" + parameterId + "_" + testId).attr('disabled', $(".naFlag_" +parameterId + "_" + testId).is(':checked'));
}

function dataEntryVerify(status){
	var comment = $('#dataEntryComment').val();
	if(status==false && (comment==null || comment=="")){
		alert("Please Write Comment");
	}
	else{
		var dataEntryVerification = {
			"comment" : comment,
			"clientReportLineId" : $('#dataEntryClientReportLineId').html(),
			"isRejectVerify" : status,
			"clientId" : $("#clientMasterId").html(),
			"checkUpId" : $("#visitSelectOnMyReportPage").val().split("_")[1]
		};
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			url : "client/dataEntryVerify",
			data : JSON.stringify(dataEntryVerification),
			success : function(response) {
				$('#dataEntryReportModal').modal('hide');
				getVrifiedReport($('#clientMasterId').html(),($('#visitSelect').val()).split("_")[1]);
				if(status){
					alert("Data Entry Verified Successfully");
				}else{
					alert("Data Entry Rejected Successfully");
				}
			}
		});
	}
};