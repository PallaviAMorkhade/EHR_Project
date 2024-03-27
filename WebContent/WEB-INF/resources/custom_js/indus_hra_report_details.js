function listOfQuestionMasterForReport() {
	jQuery.ajax({
		type : "POST",
		url : "hra/listOfQuestionMaster",
		success : function(r) {
			var questionList = "<option value=''></option>";
			for (var i = 0; i < r.length; i++) {
				questionList = questionList + "<option value='"
						+ r[i].questionId + "'>" + r[i].question + "</option>";
			}
			$('#questionList').html(questionList);
			//setTimeout(function() {
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

function getQuestionReportByQuestionId(questionId) {
	jQuery
			.ajax({
				type : "GET",
				url : "questionReport/getQuestionReportByQuestionId",
				data : {
					"questionId" : questionId
				},
				success : function(r) {
					$("#viewQuestionReportModal").modal("hide");
					$("#questionList").val(questionId);
					var questionMaster = r.questionMaster;
					var questionReportDetails = r.questionReportDetails;
					$("#questionType").val(questionMaster.questionType);
					var questionReports = "";
					if (questionMaster.optionMasterSet.length > 0) {
						for (var i = 0; i < questionMaster.optionMasterSet.length; i++) {
							questionReports = questionReports
									+ '<div class="panel panel-white no-radius optionPanel" id="'+questionMaster.optionMasterSet[i].optionId+'">'
									+ '<div class="panel-heading border-light">'
									+ '<h4 class="panel-title">'
									+ '<span class="text-bold">'+questionMaster.optionMasterSet[i].option+'</span>'
									+ '</h4>'
									+ '<div class="panel-tools">'
									+ '<a href="#"'
									+ ' class="btn btn-transparent btn-sm panel-collapse"'
									+ ' data-placement="top" data-toggle="tooltip"'
									+ ' data-original-title="Collapse"><i'
									+ ' class="ti-minus collapse-off"></i><i'
									+ ' class="ti-plus collapse-on"></i></a>'
									+ '</div>'
									+ '</div>'
									+ '<div class="panel-body">'
									+ '<div class="row">'
									+ '<div class="col-md-2">'
									+ '<div class="form-group">'
									+ '<label class="control-label"> Helath Score </label> <Input'
									+ ' onkeyup="numericOnly(this)" type="text"'
									+ ' class="form-control" id="healthScore_'+questionMaster.optionMasterSet[i].optionId+'">'
									+ '</div>'
									+ '</div>'
									+ '<div class="col-md-5">'
									+ '<div class="form-group">'
									+ '<label class="control-label"> Lab Test / Check-ups'
									+ '</label> <select id="test_'+questionMaster.optionMasterSet[i].optionId+'" data-placeholder="Search Parameter"'
									+ ' class="chosen-select testList"></select>'
									+ '</div>'
									+ '</div>'
									+ '<div class="col-md-5">'
									+ '<div class="row">'
									+ '<div class="col-md-3">'
									+ '<div class="form-group">'
									+ '<label class="control-label"> Frequency </label> <Input'
									+ ' type="text" class="form-control" id="frequencyNumber_'+questionMaster.optionMasterSet[i].optionId+'">'
									+ '</div>'
									+ '</div>'
									+ '<div class="col-md-9">'
									+ '<div class="form-group">'
									+ '<label class="control-label"></label> <select'
									+ ' id="frequency_'+questionMaster.optionMasterSet[i].optionId+'" style="margin-top: 5px;"'
									+ ' class="form-control frequencyList">'
									+ '</select>'
									+ '</div>'
									+ '</div>'
									+ '</div>'
									+ '</div>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="control-label"> Report Statement </label>'
									+ '<textarea placeholder="Report Statement"'
									+ ' class="form-control reportStatement" id="reportStatement_'+questionMaster.optionMasterSet[i].optionId+'"></textarea>'
									+ '</div>'
									+ '<div class="form-group">'
									+ '<label class="control-label"> Reason </label>'
									+ '<textarea placeholder="Reason" class="form-control"'
									+ ' id="reason_'+questionMaster.optionMasterSet[i].optionId+'"></textarea>' + '</div>'
									+ '</div>' + '</div>';
						}
					} else {
						questionReports = questionReports
						+ '<div class="panel panel-white no-radius optionPanel" id="0">'
						+ '<div class="panel-heading border-light">'
						+ '<h4 class="panel-title">'
						+ '<span class="text-bold">Text Answer</span>'
						+ '</h4>'
						+ '<div class="panel-tools">'
						+ '<a href="#"'
						+ ' class="btn btn-transparent btn-sm panel-collapse"'
						+ ' data-placement="top" data-toggle="tooltip"'
						+ ' data-original-title="Collapse"><i'
						+ ' class="ti-minus collapse-off"></i><i'
						+ ' class="ti-plus collapse-on"></i></a>'
						+ '</div>'
						+ '</div>'
						+ '<div class="panel-body">'
						+ '<div class="row">'
						+ '<div class="col-md-2">'
						+ '<div class="form-group">'
						+ '<label class="control-label"> Helath Score </label> <Input'
						+ ' onkeyup="numericOnly(this)" type="text"'
						+ ' class="form-control" id="healthScore_0">'
						+ '</div>'
						+ '</div>'
						+ '<div class="col-md-5">'
						+ '<div class="form-group">'
						+ '<label class="control-label"> Lab Test / Check-ups'
						+ '</label> <select id="test_0" data-placeholder="Search Parameter"'
						+ ' class="chosen-select testList"></select>'
						+ '</div>'
						+ '</div>'
						+ '<div class="col-md-5">'
						+ '<div class="row">'
						+ '<div class="col-md-3">'
						+ '<div class="form-group">'
						+ '<label class="control-label"> Frequency </label> <Input'
						+ ' type="text" class="form-control" id="frequencyNumber_0">'
						+ '</div>'
						+ '</div>'
						+ '<div class="col-md-9">'
						+ '<div class="form-group">'
						+ '<label class="control-label"></label> <select'
						+ ' id="frequency_0" style="margin-top: 5px;"'
						+ ' class="form-control frequencyList">'
						+ '</select>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
						+ '</div>'
						+ '<div class="form-group">'
						+ '<label class="control-label"> Report Statement </label>'
						+ '<textarea placeholder="Report Statement"'
						+ ' class="form-control reportStatement" id="reportStatement_0"></textarea>'
						+ '</div>'
						+ '<div class="form-group">'
						+ '<label class="control-label"> Reason </label>'
						+ '<textarea placeholder="Reason" class="form-control"'
						+ ' id="reason_0"></textarea>' + '</div>'
						+ '</div>' + '</div>';
					}
					$("#questionReports").html(questionReports);
					listOfFrequencyMasterForReport();
					listOfTestMasterForReport();
					$(".reportStatement").map(function(){
						  var id = this.id;
						  CKEDITOR.replace(id);
					});
					
					setTimeout(function() {
					for(var j=0;j<questionReportDetails.length;j++){
						var optionId = questionReportDetails[j].questionReportId.optionId;
						$("#healthScore_"+optionId).val(questionReportDetails[j].healthScore);
						CKEDITOR.instances["reportStatement_"+optionId].setData(questionReportDetails[j].reportStatement);
						$("#test_" +optionId).val(questionReportDetails[j].testId);
						$("#frequencyNumber_" + optionId).val(questionReportDetails[j].frequencyNumber);
						$("#frequency_" + optionId).val(questionReportDetails[j].frequencyId);
						$("#reason_" + optionId).val(questionReportDetails[j].reason);
					}
					$('.chosen-select').chosen().trigger("chosen:updated");
					}, 400);
					
				}
			});
}

function saveQuestionReportDetails() {
	var questionReportDetails = [];
	var questionId = $("#questionList").val();
	if(questionId!=""){
	$(".optionPanel").map(function(){
		var i = this.id;
		var questionReportId = {
			"questionId" : questionId,
			"optionId" : i
		};
		var healthScore = $("#healthScore_" + i).val();
		var reportStatement = CKEDITOR.instances["reportStatement_" + i]
				.getData();
		var testId = $("#test_" + i).val();
		var frequencyNumber = $("#frequencyNumber_" + i).val();
		var frequencyId = $("#frequency_" + i).val();
		var reason = $("#reason_" + i).val();
		var isActive = true;
		var questionReportDetail = {
			"questionReportId" : questionReportId,
			"healthScore" : healthScore,
			"reportStatement" : reportStatement,
			"testId" : testId,
			"frequencyNumber" : frequencyNumber,
			"frequencyId" : frequencyId,
			"reason" : reason,
			"isActive" : isActive
		}
		questionReportDetails.push(questionReportDetail);
	});

	$.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		url : "questionReport/savequestionReport",
		data : JSON.stringify(questionReportDetails),
		success : function(response) {
			alert("Question Report Details Saved Successfully");
			clearQuestionReportDetails();
		}
	});
	}else{
		alert("Please Select Question First");
	}
}

function listQuestionReportDetail() {
	jQuery
			.ajax({
				type : "GET",
				url : "questionReport/listQuestionReportDetail",
				success : function(r) {
					$("#viewQuestionReportModal").modal("show");
					var divContent = "";
					var index = 1;
					for (var i = 0; i < r.length; i++) {
						divContent = divContent
								+ "<tr class='questionMasterRow' id='question_master_"
								+ r[i].questionId
								+ "'><td class='center'>"
								+ index
								+ "</td><td id='question_"
								+ r[i].questionId
								+ "'>"
								+ r[i].question
								+ "</td><td>"
								+ r[i].questionType
								+ "</td>"
								+ "<td onclick='getQuestionReportByQuestionId("+r[i].questionId+")'><i class='fa fa-pencil'></i></td>'"
								+ "<td onclick='deleteQuestionReportDetail("
								+ r[i].questionId
								+ ")'><i class='fa fa-times fa fa-white'></i></td></tr>";
						index++;
					}
					$('#questionReportTableBody').html(divContent);
					getUserAccess();
				}
			});
}

function deleteQuestionReportDetail(questionId){
	var r = confirm("Are you sure to delete Question Report Details?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {
			"questionId" : questionId
		},
		url : "questionReport/deleteQuestionReportDetail",
		/*error : function() {
			alert("error");
		},*/
		success : function(r) {
			//if(r=="true"){
				alert("Question Report Details Deleted Successfully");
				listQuestionReportDetail();
				clearQuestionReportDetails();
				/*}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}*/
		}
	});
    }
}

function clearQuestionReportDetails() {
	$("#questionList").val("");
	$("#questionType").val("");
	$("#questionReports").html("");
	$('.chosen-select').chosen().trigger("chosen:updated");
}