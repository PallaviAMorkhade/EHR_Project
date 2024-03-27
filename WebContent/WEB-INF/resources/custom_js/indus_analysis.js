function getRecordByDateAnalysis() {
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
					url : "client/getClientMastersByDateTestResults",
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
							numberOfRows +="<li onclick='paginationAnalysis("+index+")'><a>"+index+"</a></li>";
							index=index+1;
						}
						if(numberOfPages>6){
							numberOfRows +="<li class='next' onclick='nextPaginationAnalysis("+index+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
						}
						for ( var i = 0; i < r.listClientMasters.length; i++) {
							str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td class='interactionClientSelect'>" + r.listClientMasters[i].firstName
									+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].lastName
									+ "</td><td class='interactionClientSelect'>" + r.listClientMasters[i].mobNo + "</td>" 
									/*+ "<td class='interactionClientSelect'>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
									+ "<td class='interactionClientSelect'>" + r.listClientMasters[i].addAt
									+ "</td><td><button onclick=getPatientAnalysis("+r.listClientMasters[i].clientId+",'"+r.listClientMasters[i].userId+"')>Analysis</button></td></tr>";
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

function paginationAnalysis(pageNumber){
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
		url : "client/getClientMastersByDateTestResults",
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
						+ "</td><td><button onclick=getPatientAnalysis("+r.listClientMasters[i].clientId+",'"+r.listClientMasters[i].userId+"')>Analysis</button></td></tr>";
			}
			$('#clientRecordTable').html(str);
		}
	});
}

function nextPaginationAnalysis(currentIndex,numberOfPages){
	var displayPagination=currentIndex+5;
	var numberOfRows='';
	numberOfRows +="<li class='previous' onclick='previousPaginationAnalysis("+currentIndex+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
	if(numberOfPages<displayPagination){
		displayPagination=numberOfPages+1;
		//numberOfRows +="<li class='next disabled' onclick='nextPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
	}
	for(var j=currentIndex;j<displayPagination;j++){
		numberOfRows +="<li onclick='pagination("+j+")'><a>"+j+"</a></li>";
	}
	if(numberOfPages>displayPagination){
		numberOfRows +="<li class='next' onclick='nextPaginationAnalysis("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
	}
		$('#patientRecordPagination').html(numberOfRows);
}

function previousPaginationAnalysis(currentIndex,numberOfPages){
	var displayPagination=currentIndex-5;
	var numberOfRows='';
	if(currentIndex>6){
		numberOfRows +="<li class='previous' onclick='previousPaginationAnalysis("+displayPagination+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
	}
	for(var j=displayPagination;j<currentIndex;j++){
		numberOfRows +="<li onclick='pagination("+j+")'><a>"+j+"</a></li>";
	}
		numberOfRows +="<li class='next' onclick='nextPaginationAnalysis("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
		$('#patientRecordPagination').html(numberOfRows);
}
    
    $('#patientRecordPagination').on('click', 'li', function () {
    	$(this).addClass('active').siblings().removeClass('active');
    });
    
function assignDate(){
	 $('#endDate').val($.datepicker.formatDate('dd-mm-yy', new Date())); 
	 $('#emailEndDate').val($.datepicker.formatDate('dd-mm-yy', new Date())); 
}

$("#searchBoxAnalysis").autocomplete({
	 source : function(request, response) {
		 var searchKeyword = $("#searchBoxAnalysis").val();
			jQuery.ajax({
				type : "POST",
				data : {
					searchKeyword : searchKeyword
				},
				url : "client/autoSuggestionClientTestResults",
				timeout : 1000 * 60 * 5,
				catche : false,
				error : function() {
					alert('error');
				},
				success : function(r) {
					var availableTags = [];
					for(var i=0;i<r.length;i++){
						availableTags[i]=r[i].clientFullName+"_"+r[i].clientDOB;
					}
					response(availableTags);
				}
			});
	 	},
	 	select: function(event, ui) {
	 		var searchKeyword = ui.item.value;
	 		var id=searchKeyword.split("_");
	 		getRecordByNameAnalysis(id[1]);
	 	}
});
	
$( "#searchBoxAnalysis" ).autocomplete("widget").addClass("fixedHeight");

$('#searchByClientId').keypress(function(event){
	var clientId=$('#searchByClientId').val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13'){
		if(clientId==""){
    		alert("Please Enter client Id");
    	}
		else{
			//$('#clientMasterId').html(clientId);
			jQuery.ajax({
				type : "POST",
				data : {
					clientId : clientId
				},
				url : "client/getClientMastersByClientIdTestResults",
				error : function() {
					alert("error");
				},
				success : function(r) {
					if(r.userId != null){
						var str = "";
							str += "<tr style='cursor: pointer;' id='"+r.userId+"' class='"+clientId+"'><td class='interactionClientSelect'>" + r.firstName
									+ "</td><td class='interactionClientSelect'>" + r.lastName
									+ "</td><td class='interactionClientSelect'>" + r.mobNo + "</td>" 
									/*+ "<td class='interactionClientSelect'>"+ r.packageMaster.packageDescription+"</td>"*/
									+ "<td class='interactionClientSelect'>" + r.addAt
									+ "</td><td><button onclick=getPatientAnalysis("+clientId+",'"+r.userId+"')>Analysis</button></td></tr>";
					$('#clientRecordTable').html(str);
					$('#patientRecordPagination').html("");
					$('#totalNumberOfPages').html("");
				}
				else{
					alert("Sorry client not found");
				}
				}
			});
		}
	}
});

$('#searchByMemberId').keypress(function(event){
	var memberId=$('#searchByMemberId').val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13'){
		if(memberId==""){
    		alert("Please Enter member Id");
    	}
		else{
			jQuery.ajax({
				type : "POST",
				data : {
					memberId : memberId
				},
				url : "client/getRecordByMemberIdTestResults",
				error : function() {
					alert("error");
				},
				success : function(r) {
					if(r.length > 0){
						var str = "";
						for(var i=0;i<r.length;i++){
							str += "<tr style='cursor: pointer;' id='"+r[i].userId+"' class='"+[i].clientId+"'><td class='interactionClientSelect'>" + r[i].firstName
									+ "</td><td class='interactionClientSelect'>" + r[i].lastName
									+ "</td><td class='interactionClientSelect'>" + r[i].mobNo + "</td>" 
									/*+ "<td class='interactionClientSelect'>"+ r[i].packageMaster.packageDescription+"</td>"*/
									+ "<td class='interactionClientSelect'>" + r[i].addAt
									+ "</td><td><button onclick=getPatientAnalysis("+r[i].clientId+",'"+r[i].userId+"')>Analysis</button></td></tr>";
						}
					$('#clientRecordTable').html(str);
					$('#patientRecordPagination').html("");
					$('#totalNumberOfPages').html("");
				}
				else{
					alert("Sorry client not found");
				}
				}
			});
		}
	}
});

function getRecordByNameAnalysis(clientId) {
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
							+ "</td><td><button onclick=getPatientAnalysis("+clientId+",'"+r.userId+"')>Analysis</button></td></tr>";
			$('#clientRecordTable').html(str);
			$('#patientRecordPagination').html("");
			$('#totalNumberOfPages').html("");
		}
	});
}


function viewReport(filePath,testName) {
	$('#viewReportModal').modal('show');
	$('#displayViewReport').attr('data',"readPdf?pdfFileName="+filePath);
	$('#testNameViewReport').html(testName);
};

function listOfVisitTypeMasterInteraction(){
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

function getVisitDateListInteraction(clientId){
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
			var index=1;
			//divContent=divContent+"<option value=''>select state</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='visit_"+r[i].checkUpId+"' class='"+(r[i].checkUpDate).split(" ")[0]+"_"+r[i].visitTypeMaster.visitTypeId+"'> Visit "+index+"</option>";
				index++;
				$('#showVisitDate').val((r[i].checkUpDate).split(" ")[0]);
				$('#showVisitDateOnMyReportPage').val((r[i].checkUpDate).split(" ")[0]);
				
				$('.lastCheckUpDate').html((r[i].checkUpDate).split(" ")[0]);
				$('.lastCheckUpDate').attr("id",r[i].checkUpId);

				$('#visitTypeMasterSelect').val(r[i].visitTypeMaster.visitTypeId);
				$('#visitTypeMasterSelectOnMyReportPage').val(r[i].visitTypeMaster.visitTypeId);
			}
			$('#visitSelect').html(divContent);
			$('#visitSelect').val($("#visitSelect option:last").val());
			$('#visitSelectOnMyReportPage').html(divContent);
			$('#visitSelectOnMyReportPage').val($("#visitSelect option:last").val());
		}
	});
}