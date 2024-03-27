function getRecordByDate() {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			var currentPageId = $('#currentPageId').html();
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
					url : "client/getClientMasterRecordByDate",
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
							str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td>" + r.listClientMasters[i].firstName
									+ "</td><td>" + r.listClientMasters[i].lastName
									+ "</td><td>" + r.listClientMasters[i].mobNo + "</td>" 
									/*+ "<td>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
									+ "<td>" + r.listClientMasters[i].addAt
									if(currentPageId == 41){
										str +="</td><td><button onclick='openViewModal(\""+r.listClientMasters[i].firstName+"\",\""+r.listClientMasters[i].lastName+"\","+r.listClientMasters[i].clientId+")' >View</button></td><td><input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"' for='"+r.listClientMasters[i].clientId+"'>";
									}
											
									str += "</td></tr>";
						}
						$('#totalNumberOfPages').html("<li><a>No. Of Pages:"+(Math.round(numberOfPages))+"</a></li>");
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
		url : "client/getClientMasterRecordByDate",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var str = "";
		//	var numberOfRows="";
		//	var index=1;
		//	var count=r.count;
		//	var numberOfPages=(count/10);
		//	var displayPagination=numberOfPages;
			/*if(numberOfPages>5){
				numberOfRows +="<li class='disabled previous'><a><i class='ti-angle-left'></i></a></li>";
				displayPagination=5;
			}
			for(var j=0;j<displayPagination;j++){
				numberOfRows +="<li onclick='pagination("+index+")'><a>"+index+"</a></li>";
				index=index+1;
			}
			if(numberOfPages>6){
				numberOfRows +="<li class='next' onclick='nextPagination("+index+")'><a><i class='ti-angle-right'></i></a></li>";
			}*/
			for ( var i = 0; i < r.listClientMasters.length; i++) {
				str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td>" + r.listClientMasters[i].firstName
						+ "</td><td>" + r.listClientMasters[i].lastName
						+ "</td><td>" + r.listClientMasters[i].mobNo + "</td>"
						/*+ "<td>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
						+ "<td>" + r.listClientMasters[i].addAt
						+ "</td></tr>";
			}
			//$('#patientRecordPagination').html("");
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
    
    $('#clientRecordTable').on('click', 'tr', function () {
    	var currentPageId = $('#currentPageId').html();
    	if(currentPageId == 41){
    		//some code
    	}else{
    	//$('#pleaseWait').show();
    	var userId=$(this).attr('id');
    	var clientId=$(this).attr('class');
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
				}else{
					$('#pleaseWait').show();
					$('#clientUserId').html(userId);
			    	$('#clientMasterId').html(clientId);
			    	jQuery.ajax({
						type : "GET",
						data : {
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
							setTimeout(function(){ getUserAccess(); }, 10);
						}
					});
				}
			}
		});
    	}
    });

    /*function autoSuggestions() {
			var searchKeyword = $("#q").val();
			jQuery.ajax({
				type : "POST",
				data : {
					searchKeyword : searchKeyword
				},
				url : "client/autoSuggestions",
				error : function() {
					alert("error");
				},
				success : function(r) {
					var results=[];
					results=r;
					setGrid(results);
				}
			});
		}*/
    
    $("#searchBox").autocomplete({
		 source : function(request, response) {
			 var searchKeyword = $("#searchBox").val();
				jQuery.ajax({
					type : "POST",
					data : {
						searchKeyword : searchKeyword
					},
					url : "client/autoSuggestions",
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
		 		getRecordByName(id[1]);
		 	}
	});
		
    $( "#searchBox" ).autocomplete("widget").addClass("fixedHeight");
    
    $('#searchByClientId').keypress(function(event){
    	var clientId=$('#searchByClientId').val();
    	var keycode = (event.keyCode ? event.keyCode : event.which);
    	if(keycode == '13') {
    		if(clientId==""){
        		alert("Please Enter client Id");
        	}
    		else{
    			getRecordByName(clientId);
    		}
    	}
    });
    
		function getRecordByName(clientId) {
			$('#clientMasterId').html(clientId);
			var currentPageId = $('#currentPageId').html();
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
					
			    	
					if(r.isActive == 'Y' && r.userId != null){
						var str = "";
							str += "<tr style='cursor: pointer;' class='"+r.clientId+"' id='"+r.userId+"'><td>" + r.firstName
									+ "</td><td>" + r.lastName
									+ "</td><td>" + r.mobNo + "</td>" 
									/*+ "<td>"+ r.packageMaster.packageDescription+"</td>"*/
									+ "<td>" + r.addAt;
							if(currentPageId == 41){
								str +="</td><td><button onclick='openViewModal(\""+r.firstName+"\",\""+r.lastName+"\","+r.clientId+")' >View</button></td><td><input type='checkbox' class='check' id='"+r.emailId+"' value='"+r.mobNo+"' for='"+r.clientId+"'>";
							}
									
							str += "</td></tr>";
					$('#clientRecordTable').html(str);
					$('#patientRecordPagination').html("");
					$('#totalNumberOfPages').html("");
					}else if(r.isActive == 'N'){
						alert("Client is deactivated");
					}else{
						alert("Sorry client not found");
					}
				}
			});
		}
		
		$('#searchByMemberId').keypress(function(event){
	    	var memberId=$('#searchByMemberId').val();
	    	var keycode = (event.keyCode ? event.keyCode : event.which);
	    	if(keycode == '13'){
	    		if(memberId==""){
	        		alert("Please Enter member Id");
	        	}
	    		else{
	    			getRecordByMemberId(memberId);
	    		}
	    	}
	    });
		
		function getRecordByMemberId(memberId) {
			//$('#clientMasterId').html(clientId);
			var currentPageId = $('#currentPageId').html();
			jQuery.ajax({
				type : "POST",
				data : {
					memberId : memberId,
					startIndex : 0
				},
				url : "client/getRecordByMemberId",
				error : function() {
					alert("error");
				},
				success : function(r) {
					if(r.listClientMasters.length > 0){
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
							numberOfRows +="<li onclick='memberIdPagination("+index+")'><a>"+index+"</a></li>";
							index=index+1;
						}
						if(numberOfPages>6){
							numberOfRows +="<li class='next' onclick='nextMemberPagination("+index+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
						}
						for ( var i = 0; i < r.listClientMasters.length; i++) {
							str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td>" + r.listClientMasters[i].firstName
									+ "</td><td>" + r.listClientMasters[i].lastName
									+ "</td><td>" + r.listClientMasters[i].mobNo + "</td>" 
									/*+ "<td>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
									+ "<td>" + r.listClientMasters[i].addAt;
							if(currentPageId == 41){
								str +="</td><td><button onclick='openViewModal(\""+r.listClientMasters[i].firstName+"\",\""+r.listClientMasters[i].lastName+"\","+r.listClientMasters[i].clientId+")' >View</button></td><td><input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"' for='"+r.listClientMasters[i].clientId+"'>";
							}
									
							str += "</td></tr>";
						}
						$('#totalNumberOfPages').html("<li><a>No. Of Pages:"+(Math.round(numberOfPages))+"</a></li>");
						$('#patientRecordPagination').html(numberOfRows);
						$('#clientRecordTable').html(str);
					}
					else{
						alert("Sorry client not found");
					}
				}
			});
		}
		
		function memberIdPagination(pageNumber){
			var memberId=$('#searchByMemberId').val();
			var startIndex = (pageNumber-1)+"0";
			jQuery.ajax({
				type : "POST",
				data : {
					memberId : memberId,
					startIndex : startIndex
				},
				url : "client/getRecordByMemberId",
				error : function() {
					alert("error");
				},
				success : function(r) {
					var str = "";
					for ( var i = 0; i < r.listClientMasters.length; i++) {
						str += "<tr style='cursor: pointer;' class='"+r.listClientMasters[i].clientId+"' id='"+r.listClientMasters[i].userId+"'><td>" + r.listClientMasters[i].firstName
								+ "</td><td>" + r.listClientMasters[i].lastName
								+ "</td><td>" + r.listClientMasters[i].mobNo + "</td>" 
								/*+ "<td>"+ r.listClientMasters[i].packageMaster.packageDescription+"</td>"*/
								+ "<td>" + r.listClientMasters[i].addAt
								+ "</td></tr>";
					}
					//$('#patientRecordPagination').html("");
					$('#clientRecordTable').html(str);
				}
			});
		}

		function nextMemberPagination(currentIndex,numberOfPages){
			var displayPagination=currentIndex+5;
			var numberOfRows='';
			numberOfRows +="<li class='previous' onclick='previousMemberPagination("+currentIndex+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
			if(numberOfPages<displayPagination){
				displayPagination=numberOfPages+1;
				//numberOfRows +="<li class='next disabled' onclick='nextPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
			}
			for(var j=currentIndex;j<displayPagination;j++){
				numberOfRows +="<li onclick='memberIdPagination("+j+")'><a>"+j+"</a></li>";
			}
			if(numberOfPages>displayPagination){
				numberOfRows +="<li class='next' onclick='nextMemberPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
			}
				$('#patientRecordPagination').html(numberOfRows);
		}

		function previousMemberPagination(currentIndex,numberOfPages){
			var displayPagination=currentIndex-5;
			var numberOfRows='';
			if(currentIndex>6){
				numberOfRows +="<li class='previous' onclick='previousMemberPagination("+displayPagination+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-left'></i></a></li>";
			}
			for(var j=displayPagination;j<currentIndex;j++){
				numberOfRows +="<li onclick='memberIdPagination("+j+")'><a>"+j+"</a></li>";
			}
				numberOfRows +="<li class='next' onclick='nextMemberPagination("+j+","+Math.round(numberOfPages)+")'><a><i class='ti-angle-right'></i></a></li>";
				$('#patientRecordPagination').html(numberOfRows);
		}
		
	function assignDate() {
			 $('#endDate').val($.datepicker.formatDate('dd-mm-yy', new Date())); 
	}
	
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
								+ "</td><td><button onclick='openViewModal(\""+r.listClientMasters[i].firstName+"\",\""+r.listClientMasters[i].lastName+"\","+r.listClientMasters[i].clientId+")'>View</button></td><td> <input type='checkbox' class='check' id='"+r.listClientMasters[i].emailId+"' value='"+r.listClientMasters[i].mobNo+"' for='"+r.listClientMasters[i].clientId+"'></td></tr>";
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

	//Irfan khan 21-nov-2018
	function openViewModal(firstName,lastName,clientId){
		$('#cName').html(firstName +" "+lastName);
		$('#contactHubModal').modal('show');
		$('#clientMasterId').html(clientId);
		getEmailSmsRecordByClientIdNew(clientId);//$('#clientMasterId').html());
	}
	
	function getEmailSmsRecordByClientIdNew(clientId){
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
					divContent=divContent+"<tr id='email_"+r[0][i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[0][i].topic+"</td><td>"+r[0][i].sentBy+"</td><td>"+r[0][i].sentOn+"</td><td><a onclick='getEmailRecordByEmailIdNew("+r[0][i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				for(var i=0;i<r[1].length;i++)
				{
					divContent=divContent+"<tr id='sms_"+r[1][i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[1][i].topic+"</td><td>"+r[1][i].sentBy+"</td><td>"+r[1][i].sentOn+"</td><td><a onclick='getSmsRecordBySmsIdNew("+r[1][i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				if(r[0]==0 && r[1]==0){
					alert("No data to display");
				}
				$('#contactHubTableBody').html(divContent);
			}
		});
	}
	
	//old functions from interaction.js
	function getEmailRecordByEmailIdNew(emailId){
		$('#contactHubModal').modal('hide');
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
	
	function getSmsRecordBySmsIdNew(smsId){
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

	//$('#getEmailSmsForm').change(function(){
	function getEmailSmsFormNew(){
		var value = $("input[name='contactHubValues']:checked").val();
		var clientId=$('#clientMasterId').html();
		if(value==="email"){
			getEmailRecordByClientIdNew(clientId);
		}
		if(value==="sms"){
			getSmsRecordByClientIdNew(clientId);
		}
		if(value==="all"){
			getEmailSmsRecordByClientIdNew(clientId);
		}
	};

	function getEmailAndSmsRecordByDateNew(){
		var value = $("input[name='contactHubValues']:checked").val();
		var clientId=$('#clientMasterId').html();
		if(value==="email"){
			getEmailRecordByDateNew(clientId);
		}
		if(value==="sms"){
			getSmsRecordByDateNew(clientId);
		}
		if(value==="all"){
			getEmailSmsRecordByDateNew(clientId);
		}
	}

	function getEmailSmsRecordByDateNew(clientId){
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
						divContent=divContent+"<tr id='email_"+r[0][i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[0][i].topic+"</td><td>"+r[0][i].sentBy+"</td><td>"+r[0][i].sentOn+"</td><td><a onclick='getEmailRecordByEmailIdNew("+r[0][i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
						index++;
					}
					for(var i=0;i<r[1].length;i++)
					{
						divContent=divContent+"<tr id='sms_"+r[1][i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[1][i].topic+"</td><td>"+r[1][i].sentBy+"</td><td>"+r[1][i].sentOn+"</td><td><a onclick='getSmsRecordBySmsIdNew("+r[1][i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
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

	function getEmailRecordByDateNew(clientId){
		var startDate = $('#emailStartDate').val();
		var endDate = $('#emailEndDate').val();
		alert(startDate + "  "+ endDate);
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
						divContent=divContent+"<tr id='email_"+r[i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[i].topic+"</td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getEmailRecordByEmailIdNew("+r[i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
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

	function getSmsRecordByDateNew(clientId){
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
						divContent=divContent+"<tr id='sms_"+r[i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[i].topic+"</td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getSmsRecordBySmsIdNew("+r[i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
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
	
	function getEmailRecordByClientIdNew(clientId){
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
					divContent=divContent+"<tr id='email_"+r[i].ehrEmailRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-envelope-o'></i></td><td>"+r[i].topic+"</td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getEmailRecordByEmailIdNew("+r[i].ehrEmailRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				if(r==0){
					alert("No data to display");
				}
				$('#contactHubTableBody').html(divContent);
			}
		});
	}

	function getSmsRecordByClientIdNew(clientId){
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
					divContent=divContent+"<tr id='sms_"+r[i].ehrSmsRecordId+"'><td class='center'>"+index+"</td><td><i class='fa fa-comments-o'></i></td><td>"+r[i].topic+"</td><td>"+r[i].sentBy+"</td><td>"+r[i].sentOn+"</td><td><a onclick='getSmsRecordBySmsIdNew("+r[i].ehrSmsRecordId+")'><i class='fa fa-binoculars'></i></a></td></tr>";
					index++;
				}
				if(r==0){
					alert("No data to display");
				}
				$('#contactHubTableBody').html(divContent);
			}
		});
	}
	
	function sendEmailAndSmsBtnNew(){
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
		clearSendEmailAndSmsPopUpDivNew();
		listOfEmailTemplateMasterInteractionNew();
		listOfSmsTemplateMasterInteractionNew();
		}
	}
	
	function clearSendEmailAndSmsPopUpDivNew(){
		$('#emailTemplateSelectListInteraction option:selected').val("");
		//$('#emailMedicalAdvice').html("");
		$('.ckeditor').val("");
		$('#smsTemplateSelectListInteraction option:selected').val();
		$('#smsMedicalAdvice').val("");
		$('#emailSubject').val("");
	}
	
	function listOfEmailTemplateMasterInteractionNew(){
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
	
	function listOfSmsTemplateMasterInteractionNew(){
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