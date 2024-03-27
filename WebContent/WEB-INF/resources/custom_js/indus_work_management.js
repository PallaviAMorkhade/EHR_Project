function listOfUserTypeMaster() {
	jQuery.ajax({
		type : "POST",
		url : "user/getListOfUserTypeMaster",
		error : function() {
		},
		success : function(r) {
			var divContent = "<option value=''>Select User Type</option>";
			for (var i = 0; i < r.length; i++) {
				divContent = divContent + "<option value='" + r[i].userTypeId
						+ "'>" + r[i].userTypeName + "</option>";
			}
			$('#userTypeMaster').html(divContent);
			
			if($("#userTypeId").val()=="7"){
				$('#userTypeMaster').val("10");
			}else if($("#userTypeId").val()=="8"){
				$('#userTypeMaster').val("11");
			}
			listOfUserMaster();
		}
	});
}

function listOfUserMaster() {
	jQuery.ajax({
		type : "POST",
		url : "user/getListOfUserMaster",
		data : {
			userTypeId : $("#userTypeMaster").val()
		},
		error : function() {
		},
		success : function(r) {
			$('#users_chosen').remove();
			var divContent = divContent
					+ "<option value=''>Select User</option>";
			for (var i = 0; i < r.length; i++) {
				divContent = divContent + "<option value='" + r[i].userId
						+ "'>" + r[i].title + " " + r[i].firstName + " "
						+ r[i].middleName + " " + r[i].lastName + "</option>";
			}
			$('#users').html(divContent);
			$('.chosen-select').chosen().trigger("chosen:updated");
			$('#users_chosen').css("width","100%");
		}
	});
}

function clientSelect(userId, clientId, visitId) {
	$('#pleaseWait').show();
	var status = $("#currentWorkStatusId").val();
	jQuery.ajax({
		type : "POST",
		data : {
			"clientId" : clientId
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
			} else {
				$('#clientUserId').html(userId);
				$('#clientMasterId').html(clientId);
				$("#workVisitId").val(visitId);
				if($("#userTypeId").val()=="12" && status == "3"){
					loadReportPage();
				} else if (status == "1" || status == "2" || status == "3") {
					//loadVerificationPage();					
					getVerifyUploadedReport(clientId,visitId);
					$('#verifiedReportModal').modal('show');
					var clientName = $("#clientName_"+clientId).html();
					$("#reportModalPatientName").html("("+clientName+")");
					if(status == "1"){
						$("#verifyReportBtn").attr("disabled","disabled");
						$("#rejectReportBtn").attr("disabled","disabled");
					}else{
						$("#verifyReportBtn").removeAttr("disabled");
						$("#rejectReportBtn").removeAttr("disabled");
					}
				} else if (status == "4" || status == "5" || status == "6"
						|| status == "7" || status == "8" || status == "9") {
					loadDataEntryPage();
				} else if (status == "10" || status == "11" || status == "12"
						|| status == "13" || status == "16") {
					getPatientAnalysis(clientId, userId);
					
					if(status == "16") {
						$("#engmntPopId").removeClass('hidden');
						$("#engmntPopId").addClass('active');
						$("#engagement").addClass('active');
						$("#analysisPopId").removeClass('active');
						$("#analysis").removeClass('active');
						
						listOfFollowUpRecord(visitId);
					} else {
						$("#engmntPopId").addClass('hidden');
						$("#engagement").removeClass('active');
						$("#analysisPopId").addClass('active');
						$("#analysis").addClass('active in');
					}
				}
			}
		}
	});
};

function getWork(status) {
	var userId = $("#userId").val();
	var pageId = 1;
	jQuery.ajax({
		type : "GET",
		data : {
			"status" : status,
			"userId" : userId,
			"pageId" : pageId
		},
		url : "dashboard/countVisitStatus",
		error : function() {
		},
		success : function(r) {
			var pageCount = parseInt(r);
			if (pageCount > 0) {
				if($('#pagination').data("twbs-pagination")) {
					$("#pagination").twbsPagination('destroy');
				}
				$("#pagination").twbsPagination({
					totalPages : pageCount,
					visiblePages : 5,
					onPageClick : function(event, page) {
						getWorkByPages(status, page);
					}
				});
			} else {
				if($('#pagination').data("twbs-pagination")) {
					$("#pagination").twbsPagination('destroy');
				}
			}
			getWorkByPages(status, pageId);
		}
	});
}

function searchByName() {
	var name = $('#searchByName').val();
	var status = $("#currentWorkStatusId").val();
	if (name != "") {
		getWorkByPages(status, 1, "name", name);
		if ($('#pagination').data("twbs-pagination")) {
			$("#pagination").twbsPagination('destroy');
		}
	} else {
		getWork(status);
	}
}

$('#searchByClientId').keypress(function(event) {
	var clientId=$('#searchByClientId').val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13') {
		if(clientId==""){
    		alert("Please Enter Client Id");
    	}
		else{
			var status = $("#currentWorkStatusId").val();
			getWorkByPages(status, 1, "clientId", clientId);
			if ($('#pagination').data("twbs-pagination")) {
				$("#pagination").twbsPagination('destroy');
			}
		}
	}
});

$('#searchByVisitId').keypress(function(event) {
	var visitId=$('#searchByVisitId').val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13') {
		if(visitId==""){
    		alert("Please Enter Visit Id");
    	}
		else{
			var status = $("#currentWorkStatusId").val();
			getWorkByPages(status, 1, "visitId", visitId);
			if ($('#pagination').data("twbs-pagination")) {
				$("#pagination").twbsPagination('destroy');
			}
		}
	}
});

$('#searchByMemberId').keypress(function(event) {
	var memberId=$('#searchByMemberId').val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13') {
		if(memberId==""){
    		alert("Please Enter Member Id");
    	}
		else{
			var status = $("#currentWorkStatusId").val();
			getWorkByPages(status, 1, "memberId", memberId);
			if ($('#pagination').data("twbs-pagination")) {
				$("#pagination").twbsPagination('destroy');
			}
		}
	}
});

function getWorkByPages(status, pageId, searchBy, searchText) {
	$("#status_" + status).prop('checked', true);
	$("#currentWorkStatusId").val(status);
	var userId = $("#userId").val();
	/*var today = new Date();
	var dd = today.getDate();
	 var month = new Array();
	  month[0] = "Jan";
	  month[1] = "Feb";
	  month[2] = "Mar";
	  month[3] = "Apr";
	  month[4] = "May";
	  month[5] = "Jun";
	  month[6] = "Jul";
	  month[7] = "Aug";
	  month[8] = "Sep";
	  month[9] = "Oct";
	  month[10]= "Nov";
	  month[11]= "Dec";

	  var mm = month[today.getMonth()];
	//alert(n);

	var yyyy = today.getFullYear();
	if (dd < 10) {
	  dd = '0' + dd;
	} 
	if (mm < 10) {
	  mm = '0' + mm;
	} 
	var today = dd + '-' + mm + '-' + yyyy;*/
	//alert(today);
	jQuery
			.ajax({
				type : "GET",
				data : {
					"status" : status,
					"userId" : userId,
					"pageId" : pageId,
					"searchBy" : searchBy,
					"searchText" : searchText
				},
				url : "dashboard/getWork",
				error : function() {
				},
				success : function(r) {
					var reportPendingWork = "";
					for (var i = 0; i < r.length; i++) {
						reportPendingWork = reportPendingWork
						/*if(r[i].checkupDate==today){
							//alert("in");
							reportPendingWork = reportPendingWork
							+ "<tr style='cursor: pointer;' class='"
						}else{
							//alert("else");
							reportPendingWork = reportPendingWork
							+ "<tr style='cursor: pointer;background: darkgray' class='"
						}
							
						reportPendingWork = reportPendingWork*/
						+ "<tr style='cursor: pointer;' class='"
							+ r[i].clientId
							+ "' id='"
							+ r[i].clientUserId
							+ "'><td class='center' " 
							+ "onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ (i + 1)
							+ "</td>"
							+ "<td id='clientName_"+ r[i].clientId+"' onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ r[i].clientName
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ r[i].clientId
							+ "</td><td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ r[i].checkupId
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ r[i].memberId
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ r[i].packageName
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>"
							+ r[i].checkupDate
							+ "</td>"
							+ "<td class='hidden' id='analysisFlag_"+r[i].clientUserId+"'>"
							+ r[i].analysisFlag
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"') class='assigned' style='display:none;'>"
							+ r[i].assignedOn
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"') class='assigned' style='display:none;'>"
							+ r[i].assignedToName
							+ "</td>"
							+ "<td onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"') class='assigned' style='display:none;'>"
							+ r[i].assignedByName
							+ "</td>"
							+ "<td class='engagement' style='display:none;' onclick=clientSelect('"+ r[i].clientUserId+ "','"+ r[i].clientId+"','"+r[i].checkupId+"')>status</td>"
							+ "<td class='pending' style='width: 7%;display:none;'><div style='margin: -15px 0 -12px !important;' class='checkbox clip-check check-primary check-md checkbox-inline'>"
							+ "<input class='pendingChk' type='checkbox' value='"
							+ r[i].clientId + "_" + r[i].checkupId + "_"
							+ r[i].taskId + "_" + r[i].analysisFlag + "' id='client_" + r[i].clientId	+ "_"+r[i].checkupId+"'>" 
							+ "<label for='client_" + r[i].clientId	+ "_"+r[i].checkupId+"'></label></div></td></tr>";
					}
					if (status == "1" || status == "2" || status == "3") {
						$("#dataEntryTblBody").html("");
						$("#dataEntryVerificationTblBody").html("");
						$("#doctorAnalysisTblBody").html("");
						$("#healthReportTblBody").html("");
						$("#healthReportVerificationTblBody").html("");
						$("#engagementTblBody").html("");
						$("#reportPendingWork").html(reportPendingWork);
					} else if (status == "4" || status == "5" || status == "6") {
						$("#reportPendingWork").html("");
						$("#dataEntryVerificationTblBody").html("");
						$("#doctorAnalysisTblBody").html("");
						$("#healthReportTblBody").html("");
						$("#healthReportVerificationTblBody").html("");
						$("#engagementTblBody").html("");
						$("#dataEntryTblBody").html(reportPendingWork);
					} else if (status == "7" || status == "8" || status == "9") {
						$("#reportPendingWork").html("");
						$("#dataEntryTblBody").html("");
						$("#doctorAnalysisTblBody").html("");
						$("#healthReportTblBody").html("");
						$("#healthReportVerificationTblBody").html("");
						$("#engagementTblBody").html("");
						$("#dataEntryVerificationTblBody").html(
								reportPendingWork);
					} else if (status == "10" || status == "11" || status == "15") {
						$("#reportPendingWork").html("");
						$("#dataEntryTblBody").html("");
						$("#dataEntryVerificationTblBody").html("");
						$("#healthReportTblBody").html("");
						$("#healthReportVerificationTblBody").html("");
						$("#engagementTblBody").html("");
						$("#doctorAnalysisTblBody").html(reportPendingWork);
					} else if (status == "12" || status == "13") {
						$("#reportPendingWork").html("");
						$("#dataEntryTblBody").html("");
						$("#dataEntryVerificationTblBody").html("");
						$("#doctorAnalysisTblBody").html("");
						$("#healthReportVerificationTblBody").html("");
						$("#engagementTblBody").html("");
						$("#healthReportTblBody").html(reportPendingWork);
					} else if (status == "14" || status == "15") {
						$("#reportPendingWork").html("");
						$("#dataEntryTblBody").html("");
						$("#dataEntryVerificationTblBody").html("");
						$("#doctorAnalysisTblBody").html("");
						$("#healthReportTblBody").html("");
						$("#engagementTblBody").html("");
						$("#healthReportVerificationTblBody").html(
								reportPendingWork);
					} else if (status == "16"){
						$("#reportPendingWork").html("");
						$("#dataEntryTblBody").html("");
						$("#dataEntryVerificationTblBody").html("");
						$("#doctorAnalysisTblBody").html("");
						$("#healthReportTblBody").html("");
						$("#healthReportVerificationTblBody").html("");
						$("#engagementTblBody").html(reportPendingWork);
						
						listOfCentresInteraction();
						listOfActionMasterInteraction();
						listOfCommentMasterForAnalysis();
					}

					if ($("#isLeader").val() == "Y") {
						$(".leaderDiv").show();
						$(".myWorkDiv").hide();

						if (status == "1" || status == "4" || status == "7"
								|| status == "10" || status == "12"
								|| status == "14") {
							$(".pending").show();
							$(".leaderDivBtn").show();
							$(".assigned").hide();
						} else if (status == "2" || status == "3"
								|| status == "5" || status == "6"
								|| status == "8" || status == "9"
								|| status == "13") {
							$(".pending").hide();
							$(".leaderDivBtn").hide();
							$(".assigned").show();
						} else if (status == "11"){
							$(".pending").show();
							$(".leaderDivBtn").show();
							$(".assigned").show();
						} else if (status == "15"){
							$(".pending").hide();
							$(".assigned").hide();
							$(".leaderDivBtn").hide();
							$(".myWorkDiv").hide();
						} else if (status == "16"){
							$(".pending").show();
							$('.engagement').show();
							$(".assigned").hide();
						}

					} else {
						$(".leaderDiv").hide();
						$(".leaderDivBtn").hide();
						$(".myWorkDiv").show();
						$(".assigned").show();
						$(".pending").show();

						if ($("#userTypeId").val() == "12") {
							$(".myWorkDiv").hide();
							$(".pending").hide();
						}
						
						else if (status == "16"){
							$('.engagement').show();
							$(".assigned").hide();
						}
					}
					
					if(r.length == 0){
						var str = "<tr><td class='center' colspan='8'><b>Record Not Found</b></td></tr>";
						$("#reportPendingWork").html(str);
						$("#dataEntryTblBody").html(str);
						$("#dataEntryVerificationTblBody").html(str);
						$("#doctorAnalysisTblBody").html(str);
						$("#healthReportTblBody").html(str);
						$("#healthReportVerificationTblBody").html(str);
					}

				}
			});
}

function assignWorkModal(workStatusId) {
	if ($(".pendingChk:checkbox:checked").length > 0) {
		$("#assignWorkModal").modal();
		$("#workStatusId").val(workStatusId);
	} else {
		alert("Please Select Atleast One Client");
	}
}

function assignTask() {
	var taskSlaves = [];
	var todayDate = new Date();
	$(".pendingChk:checkbox:checked").map(function() {
		var value = this.value;
		var taskSlave = {
			"clientId" : value.split("_")[0],
			"checkUpId" : value.split("_")[1],
			"workStatusId" : $("#workStatusId").val()
		};
		taskSlaves.push(taskSlave);
	});

	var assignedTo = {
		userId : $("#users").val()
	};
	var status = $("#taskStatus").val();
	var progress = $("#progress").val();
	var taskDescription = $("#taskDescription").val();

	var taskMaster = {
		"assignedTo" : assignedTo,
		"status" : status,
		"progress" : progress,
		"taskDescription" : taskDescription,
		"assignedOn" : todayDate,
		"taskSlaves" : taskSlaves
	}
	$.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		url : "dashboard/assignTask",
		data : JSON.stringify(taskMaster),
		success : function(response) {
			$("#assignWorkModal").modal("hide");
			getWork(parseInt($("#workStatusId").val()) - 1);
			alert("Task Assigned Successfully");
		}
	});
}

function checkedAll(currentClass) {
	$("." + currentClass).prop('checked', true);
}

function unCheckedAll(currentClass) {
	$("." + currentClass).prop('checked', false);
}

function submitTask(workStatusId) {
	if ($(".pendingChk:checkbox:checked").length > 0) {
		var clients = [];
		var r = confirm("Are you sure you want to Submit Task?");
		if (r == true) {
			//checkStatusBeforeSubmitTask(workStatusId);
			$(".pendingChk:checkbox:checked").map(function() {
				var clientId = this.value.split("_")[0];
				var checkupId = this.value.split("_")[1];
				var taskId = this.value.split("_")[2];
				var analysisFlag = this.value.split("_")[3];
				
				var clientObject = {
					"clientId" : clientId,
					"checkupId" : checkupId,
					"taskId" : taskId,
					"workStatusId" : workStatusId,
					"analysisFlag" : analysisFlag
				};
				clients.push(clientObject);
			});
			
			if(workStatusId=="2"){
			$.ajax({
				async:false,
				type : "POST",
				url : "dashboard/checkStatusBeforeSubmitTask",
				data : {
					"clients" : JSON.stringify(clients)
				},
				success : function(response) {					
					if(response == "Check Visit ID" || response == ""){
						$.ajax({
							async:false,
							type : "POST",
							url : "dashboard/submitTask",
							data : {
								"clients" : JSON.stringify(clients)
							},
							success : function(response) {
								getWork(workStatusId);
								alert("Task Submitted Successfully");
							}
						});
					}else{
						alert(response);
						return false;
					}
				}
			});
			}else{
				$.ajax({
					async:false,
					type : "POST",
					url : "dashboard/submitTask",
					data : {
						"clients" : JSON.stringify(clients)
					},
					success : function(response) {
						getWork(workStatusId);
						alert("Task Submitted Successfully");
					}
				});
			}
			
		}
	} else {
		alert("Please Select Atleast One Client");
	}
}

function getClientStatus() {
	var clientId = $("#searchByClientId").val();
	var visitId = $("#searchByVisitId").val();
	if (clientId != "" || visitId != "") {
		jQuery.ajax({
			type : "GET",
			data : {
				"clientId" : clientId,
				"visitId" : visitId
			},
			url : "dashboard/getClientStatus",
			error : function() {
			},
			success : function(r) {
				$("#clientStatusModal").modal("show");
				var clientStatusBody = "";
				for (var i = 0; i < r.length; i++) {
					clientStatusBody = clientStatusBody + "<tr><td>"+(i+1)+"</td><td>"+r[i].clientName+"</td>" +
					"<td>"+r[i].clientId+"</td>"+
					"<td>"+r[i].checkupId+"</td>"+
					"<td>"+r[i].checkupDate+"</td>"+
					"<td>"+r[i].status+"</td>"+
					"<td><a onclick=getClientStatusInDetail('"+ r[i].clientId+ "','"+ r[i].checkupId+"')>Details</a></td>"+
					"</tr>";
				}
				$("#clientStatusBody").html(clientStatusBody);
			}
		});
	} else {
		alert("Please Enter Client Id or Visit Id")
	}
}
function getClientStatusInDetail(clientId,visitId){	

	if (clientId != "" || visitId != "") {
		jQuery.ajax({
			type : "GET",
			data : {
				"clientId" : clientId,
				"visitId" : visitId
			},
			url : "dashboard/getClientStatusAll",
			error : function() {
			},
			success : function(r) {
				$("#clientStatusModalAll").modal("show");
				var clientStatusBody = "";
				for (var i = 0; i < r.length; i++) {
					var statuss="";
					if(r[i].status == "Data Entry Pending"){
						statuss="Report Verification Done";
					}else if(r[i].status == "Data Entry Verification Pending"){
						statuss="Data Entry Done";
					}else if(r[i].status == "Doctor Analysis Overall Pending Work"){
						statuss="Data Entry Verification Done";
					}else{
						statuss = r[i].status;
					}
					clientStatusBody = clientStatusBody + 
					"<tr>" +
					"<td>"+(i+1)+"</td>" +
					"<td>"+r[i].clientName+"</td>" +
					"<td>"+r[i].clientId+"</td>"+
					"<td>"+r[i].checkupId+"</td>"+
					"<td>"+r[i].checkupDate+"</td>"+
					"<td>"+r[i].UserName+"</td>"+
					"<td>"+statuss+"</td>"+
					"</tr>";
				}
				$("#clientStatusBodyAll").html(clientStatusBody);
			}
		});
	} else {
		alert("Please Enter Client Id or Visit Id")
	}

}

function closeVisit(workStatusId) {
	if ($(".pendingChk:checkbox:checked").length > 0) {
		var clients = [];
		var re="";
		var r = confirm("Are you sure you want to Close Visit?");
		if (r == true) {
			$(".pendingChk:checkbox:checked").map(function() {
				var narration=prompt("Narration","");
				if(narration==null || narration=="" || narration==undefined){
					alert("Narration is compulsary.");
					re="return";
					return false;
				}else{
					var clientId = this.value.split("_")[0];
					var checkupId = this.value.split("_")[1];
					var clientObject = {
						"clientId" : clientId,
						"checkupId" : checkupId,
						"narration" : narration
					};
					clients.push(clientObject);
				}
				
			});
			if(re == "return"){
				return false;
			}
			$.ajax({
				type : "POST",
				url : "dashboard/closeVisit",
				data : {
					"clients" : JSON.stringify(clients)
				},
				success : function(response) {
					getWork(workStatusId);
					alert("Visit Closed Successfully");
				}
			});
		}
	} else {
		alert("Please Select Atleast One Client");
	}
}

