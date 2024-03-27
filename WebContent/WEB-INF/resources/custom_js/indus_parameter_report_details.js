function listOfParameterMasterForReport() {
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getParameterList",
		catche : false,
		success : function(r) {
			var parameterList = "<option value=''></option>";
			for (var i = 0; i < r.length; i++) {
				parameterList = parameterList + "<option value='"
						+ r[i].parameterId + "'>" + r[i].parameterName
						+ "</option>";
			}
			$('#parameterList').html(parameterList);
		}
	});
}

function listParameterReportDetail() {
	jQuery.ajax({
		type : "GET",
		url : "parameterReport/listParameterReportDetail",
		success : function(r) {
			$("#viewParameterReportModal").modal("show");
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='parameterMasterRow' id='parameter_master_"+r[i].parameterId+"'><td class='center'>"+index+"</td><td id='parameter_"+r[i].parameterId+"'>"+r[i].parameterName+"</td><td class='editUserAccess' onclick='getParameterReportByParameterId("+r[i].parameterId+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteParameterReportDetail("+r[i].parameterId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#parameterReportTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteParameterReportDetail(parameterId){
	var r = confirm("Are you sure to delete Parameter Report Details?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {
			"parameterId" : parameterId
		},
		url : "parameterReport/deleteParameterReportDetail",
		/*error : function() {
			alert("error");
		},*/
		success : function(r) {
			//if(r=="true"){
			listParameterReportDetail();
				alert("Parameter Report Details Deleted Successfully");
				clearParameterReportDetails();
				/*}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}*/
		}
	});
    }
}

function saveParameterReportDetails() {
	var parametereReportDetails = [];
	var parameterId = $("#parameterList").val();
	if(parameterId!=""){
	for (var i = 1; i < 5; i++) {
		var parameterReportId = {
			"parameterId" : parameterId,
			"statusId" : i
		};
		var healthScore = $("#healthScore_" + i).val();
		var reportStatement = CKEDITOR.instances["reportStatement_" + i]
				.getData();
		var testId = $("#test_" + i).val();
		var frequencyNumber = $("#frequencyNumber_" + i).val();
		var frequencyId = $("#frequency_" + i).val();
		var reason = $("#reason_" + i).val();
		var isActive = true;
		var parametereReportDetail = {
			"parameterReportId" : parameterReportId,
			"healthScore" : healthScore,
			"reportStatement" : reportStatement,
			"testId" : testId,
			"frequencyNumber" : frequencyNumber,
			"frequencyId" : frequencyId,
			"reason" : reason,
			"isActive" : isActive
		}
		parametereReportDetails.push(parametereReportDetail);
	}

	$.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		url : "parameterReport/saveParameterReport",
		data : JSON.stringify(parametereReportDetails),
		success : function(response) {
			alert("Parameter Report Details Saved Successfully");
			clearParameterReportDetails();
		}
	});
	}else{
		alert("Please Select Parameter First");
	}
}

function getParameterReportByParameterId(parameterId){
	$("#viewParameterReportModal").modal("hide");
	clearParameterReportDetails();
	jQuery.ajax({
		type : "GET",
		url : "parameterReport/getParameterReportByParameterId",
		data : {
			"parameterId" : parameterId
		},
		success : function(r) {
			$("#parameterList").val(parameterId);
			var parameterMaster = r.parameterMaster;
			var parameterReportDetails = r.parameterReportDetails;
			$("#parameterType").val(parameterMaster.modBy);
			if(parameterMaster.normalValue=="2"){
				$("#urgentDetailPanel").hide();
				$("#borderlineDetailPanel").hide();
			}else{
				$("#urgentDetailPanel").show();
				$("#borderlineDetailPanel").show();
			}
			$("#testName").val(parameterMaster.testMaster.testDescription);
			
			setTimeout(function() {
			for (var i = 0; i < parameterReportDetails.length; i++) {
				$("#healthScore_" + (i+1)).val(parameterReportDetails[i].healthScore);
				$("#test_" + (i+1)).val(parameterReportDetails[i].testId);
				$("#frequencyNumber_" + (i+1)).val(parameterReportDetails[i].frequencyNumber);
				$("#frequency_" + (i+1)).val(parameterReportDetails[i].frequencyId);
				$("#reason_" + (i+1)).val(parameterReportDetails[i].reason);
				CKEDITOR.instances["reportStatement_"+(i+1)].setData(parameterReportDetails[i].reportStatement);
			}
			$('.chosen-select').chosen().trigger("chosen:updated");
			}, 400);
		}
	});
}

function clearParameterReportDetails() {
	$("#parameterList").val("");
	$("#parameterType").val("");
	$("#testName").val("");
	for (var i = 0; i < 4; i++) {
		$("#healthScore_" + (i+1)).val("");
		$("#test_" + (i+1)).val("");
		$("#frequencyNumber_" + (i+1)).val("");
		$("#frequency_" + (i+1)).val("");
		$("#reason_" + (i+1)).val("");
		CKEDITOR.instances["reportStatement_"+(i+1)].setData("");
	}
	$('.chosen-select').chosen().trigger("chosen:updated");
}