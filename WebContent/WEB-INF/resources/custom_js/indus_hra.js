$('.addQuestionDiv').click(function(){
	$('#addNewQuestionDiv').show();
	$('#saveQuestionBtnDiv').show();
	$('#updateQuestionBtnDiv').hide();
	$('.mainQuestionDiv').animate({scrollTop:$('#questionDiv').height()}, 'slow');
});

$("#hraTypeMaster").change(function() {
	var selected=$('#hraTypeMaster option:selected').text();
	var hraTypeMasterId=$('#hraTypeMaster').val();
	
	$('#question').val("");
	$('#questionType').val("");
	$('#option_0').val("");
	$('#newOptions').html("");
	$('#addNewQuestionDiv').hide();
	
	if(selected.trim()==="Select"){
		$('.mainQuestionDiv').hide();
		$('.addQuestionDiv').hide();
		$('.addQuestionDiv').hide();
	}
	else{
		displayQuestions(hraTypeMasterId);
	}
});

function displayQuestions(hraTypeMasterId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {"hraTypeMasterId":hraTypeMasterId},
		url : "hra/listOfQuestionMasterByHraId",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<div class='ss-formwidget-container' tabindex='0' role='group' style=''>"
				+"<div class='ss-formwidget-topright-actions ss-widget-nodrag'>"
				+"<div onclick='getQuestionMaster("+r[i].questionId+")' tabindex='0' class='editUserAccess goog-inline-block jfk-button jfk-button-standard jfk-button-narrow jfk-button-checked' role='button'	style='-moz-user-select: none;' data-tooltip='Edit'>"
				+"<div class='docs-icon goog-inline-block'><div aria-hidden='true' class='docs-icon-img-container docs-icon-img docs-icon-mode-edit'>&nbsp;</div>"
				+"</div></div>"
				+"<div onclick='deleteQuestionMaster("+r[i].questionId+")' tabindex='0'	class='deleteUserAccess goog-inline-block jfk-button jfk-button-standard jfk-button-narrow jfk-button-checked' role='button' style='-moz-user-select: none;' data-tooltip='Delete'>"
				+"<div class='docs-icon goog-inline-block'><div aria-hidden='true' class='docs-icon-img-container docs-icon-img docs-icon-trash'>&nbsp;</div>"
				+"</div></div></div>"
				+"<div class='ss-form-entry'><span class='ss-q-title'>"+index+"."+r[i].question+"</span><span class='ss-q-help'></span>"
				if(r[i].optionMasterSet.length>0){
					divContent=divContent+"<ul class='ss-choices'>";
				for(var j=0;j<r[i].optionMasterSet.length;j++){
					divContent=divContent+"<li class='ss-choice-item'><label><input type='"+r[i].questionType+"' id='"+r[i].optionMasterSet[j].optionId+"' tabindex='-1' disabled>"
						+"<span class='ss-choice-label'>"+r[i].optionMasterSet[j].option+"</span> </label></li>";
				}
				divContent=divContent+"</ul>";
				}
				else{
					divContent=divContent+"<input style='background-color=white;' readonly='true' type='text' class='jfk-textinput ss-formwidget-inputfield' placeholder=''	dir='ltr'>";
				}
				divContent=divContent+"</div></div>";
				index++;
			}
			$('#questionDiv').html(divContent);
			$('.mainQuestionDiv').show();
			$('.addQuestionDiv').show();
			getUserAccess();
		}
	});
}

$("#questionType").change(function() {
	var selected=$('#questionType option:selected').text();
	if(selected.trim()==="Text"){
		$('#optionDiv').hide();
	}
	else{
		$('#optionDiv').show();
	}
});

function getListOfQuestionClientHRADetails(visitId){
	listOfHraTypeMaster();
	var clientId=$('#clientMasterId').html();
	setTimeout(function(){ jQuery.ajax({
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
				$("#history_"+r[i].hraTypeId+"Div").html("<h4 class='col-md-offset-1 col-sm-offset-1 col-xs-offset-1' style='margin-top: 15px;'>"+r[i].hraTypeName+"</h4>");
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
	});}, 300);
}

function listOfHraTypeMaster(){
	jQuery.ajax({
		async : false,
		type : "POST",
		url : "hra/listOfHraTypeMaster",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var loginType = $("#loginType").html();
			var divContent="";
			var div="";
			for(var i=0;i<r.length;i++)
			{
				if(loginType=="clientLogin" && r[i].endUserFlag=="Y"){
					divContent=divContent+"<div class='row' style='margin-bottom: 5px;'><div class='col-md-offset-1 col-sm-offset-1 col-xs-offset-1'><button type='button' class='btn btn-o btn-primary btn-sm' id='hra_"+r[i].hraTypeId+"'>"+r[i].hraTypeName+"</button></div></div>";
					div=div+"<div class='row' id='history_"+r[i].hraTypeId+"Div'><h4 class='col-md-offset-1 col-sm-offset-1 col-xs-offset-1' style='margin-top: 15px;'>"+r[i].hraTypeName+"</h4></div>";
				} else if(loginType=="null"){
					divContent=divContent+"<div class='row' style='margin-bottom: 5px;'><div class='col-md-offset-1 col-sm-offset-1 col-xs-offset-1'><button type='button' class='btn btn-o btn-primary btn-sm' id='hra_"+r[i].hraTypeId+"'>"+r[i].hraTypeName+"</button></div></div>";
					div=div+"<div class='row' id='history_"+r[i].hraTypeId+"Div'><h4 class='col-md-offset-1 col-sm-offset-1 col-xs-offset-1' style='margin-top: 15px;'>"+r[i].hraTypeName+"</h4></div>";
				}
			}
			$('#hraQuestionDiv').html(div);
			$('#hraHistoryBtnDiv').html(divContent);
			getUserAccess();
		}
	});
}

$('#hraHistoryBtnDiv').on('click','button',function(){
	var id=$(this).attr('id');
	var hraTypeId=id.split("_");
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {"hraTypeMasterId":hraTypeId[1]},
		url : "hra/listOfQuestionMasterByHraId",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#questionModal').modal('show');
			$('#questionModalLabel').html($("#"+id).text());
			var divContent="";
			var index=1;
			var questionIndex=1;
			var optionIndex=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<div class='row'><div class='col-md-offset-1 col-xs-offset-1'><h5 class='row'>"+questionIndex+". "+r[i].question+"</h5><div class='row'>";
				if(r[i].optionMasterSet.length>0){
				for(var j=0;j<r[i].optionMasterSet.length;j++){
					divContent=divContent+"<div class='col-md-3'>"+romanize(optionIndex)+".<label><input style='margin: 4px 5px 0;' name='optionSet"+i+"' class='markOption"+r[i].optionMasterSet[j].optionId+" optionSet option_"+r[i].questionId+"_"+r[i].optionMasterSet[j].optionId+"' type="+r[i].questionType+" value=" +r[i].optionMasterSet[j].option+">"+r[i].optionMasterSet[j].option+"</label></div>";
					optionIndex++;
				}
				optionIndex=1;
				}
				else{
					divContent=divContent+"<div class='col-md-12'><textarea id='answer_"+index+"' class='textAnswer question_"+r[i].questionId+"' style='width: 95%; height: 50px;' maxlength='5000'></textarea></div>";
					index++;
				}
				divContent=divContent+"</div></div></div><hr>";
				questionIndex++;
			}
			$('#hraQuestionBody').html(divContent);
			
			var clientId = $('#clientMasterId').html();
			var visitId = "";
		    if($("input[name='hraType']:checked").val()=="preCounselling") {
		    	visitId = 0;
		    }else {
		    	visitId = ($('#visitSelect').val()).split("_")[1];
		    }
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
					for(var i=0;i<r.length;i++)
					{
						if(r[i].optionSet.length>0){
							var options=[];
							if(r[i].textAnswer!=null && r[i].textAnswer!=""){
							options=(r[i].textAnswer).split(",");
							for(var k=0;k<options.length;k++){
								var optionMarkId=options[k];
								$(".markOption"+optionMarkId).attr('checked','checked');
							}
							}
							}
							else{
								$(".question_"+r[i].questionId).val(r[i].textAnswer);
							}
					}
				}
			});
			
		}
	});
});

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

function checkPreCouncelling(clientId) {
	jQuery.ajax({
		type : "GET",
		data : {
			"clientId" : clientId,
			"visitId" : 0
		},
		url : "hra/checkPreCouncelling",
		error : function() {
		},
		success : function(r) {
			listOfHraTypeMaster();
			if($('#visitSelect').val()==null) {
				$('#hraHistoryBtnDiv').hide();
				$('#hraQuestionDiv').removeClass("col-md-9").addClass("col-md-12");
			}
		}
	});
}

function mergePreCouncelling(clientId,visitId) {
	var r = confirm("Are you sure you want to Merge Pre-Counselling?");
    if (r == true) {
	jQuery.ajax({
		type : "POST",
		data : {
			"clientId" : clientId,
			"visitId" : visitId
		},
		url : "hra/mergePreCouncelling",
		error : function() {
		},
		success : function(r) {
			checkPreCouncelling(clientId);
			getListOfQuestionClientHRADetails(($('#visitSelect').val()).split("_")[1]);
			alert("Pre-Counselling Merged Successfully.");
		}
	});
    }
}

function preCouncelling() {
	$("#pleaseWait").show();
	if($("input[name='hraType']:checked").val()=="preCounselling") {
		$('#hraHistoryBtnDiv').show();
		$('#hraQuestionDiv').removeClass("col-md-12").addClass("col-md-9");
		getListOfQuestionClientHRADetails(0);
		$('#hraPrintBtn').attr('href','hra/hraPrintPage?clientId='+$("#clientMasterId").html()+'&visitId='+0);
	}else {
		listOfHraTypeMaster();
		if($('#visitSelect').val()!=null) {
			getListOfQuestionClientHRADetails(($('#visitSelect').val()).split("_")[1]);
			$('#hraPrintBtn').attr('href','hra/hraPrintPage?clientId='+$("#clientMasterId").html()+'&visitId='+($('#visitSelect').val()).split("_")[1]);
			$('#mergePreCouncellingBtn').attr('onclick','mergePreCouncelling('+$("#clientMasterId").html()+','+($('#visitSelect').val()).split("_")[1]+')');
			$('#hraHistoryBtnDiv').show();
			$('#hraQuestionDiv').removeClass("col-md-12").addClass("col-md-9");
		}else{
		setTimeout(() => {
			$('#hraHistoryBtnDiv').hide();
			$('#hraQuestionDiv').removeClass("col-md-9").addClass("col-md-12");
		}, 400);
		}
	}
	$("#pleaseWait").hide();
}

//$('#saveHRADetaisBtn').click(function(){
function saveHRADetais(){
	var divName=($('#questionModalLabel').text()).replace(/\s/g, '');
	$('#questionModal').modal('hide');
	var optionChecked = [];
    $.each($(".optionSet:checked"), function(){
    	var optionClass=$(this).attr('class');
    	var id=optionClass.split("_");
    	optionChecked.push(id[1]+"_"+id[2]);
    });
    var textAnswerLength=$('.textAnswer').length+1;
    var textAnswer=[];
    for(var i=1;i<textAnswerLength;i++){
    	var textClass=$('#answer_'+i).attr('class');
    	var questionId=textClass.split("_");
    	var text=$('#answer_'+i).val();
    	// alert("**text:"+text+"**questionId:"+questionId+"**textClass:"+textClass);
    	if(text!=null && text!=""){
    		textAnswer.push(text+"_"+questionId[1]);
    	}
    }
    
    var uncheckedOption = [];
    $.each($(".optionSet:not(:checked)"), function(){
    	var optionClass=$(this).attr('class');
    	var id=optionClass.split("_");
    	uncheckedOption.push(id[1]+"_"+id[2]);
    });
    var checkUpId="";
    if($("input[name='hraType']:checked").val() == "preCounselling") {
    	checkUpId=0;
    }else {
    	checkUpId=($('#visitSelect').val()).split("_")[1];
    }
    if(textAnswer!=null && textAnswer!="" || optionChecked!=null && optionChecked!=""){
    	$("#pleaseWait").show();
    jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"optionChecked":optionChecked,
			"uncheckedOption":uncheckedOption,
			"textAnswer":textAnswer,
			"checkUpId":checkUpId
			},
		url : "hra/saveClientHRADetails",
		timeout : 1000 * 60 * 5,
		error : function() {
			$("#pleaseWait").hide();
			alert("error");
		},
		success : function(r) {
			$("#pleaseWait").hide();
			checkPreCouncelling($('#clientMasterId').html());
			if($('#visitSelect').val()!=null){
				getListOfQuestionClientHRADetails(($('#visitSelect').val()).split("_")[1]);
			}
			alert("Assessment Saved Successfully");
		}
	});
    }
};

function listOfHraTypeMasterTableBody(){
	jQuery.ajax({
		type : "POST",
		url : "hra/listOfHraTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='hraTypeMasterRow' id='hra_type_master_"+r[i].hraTypeId+"'><td class='center'>"+index+"</td><td id='hraTypeName_"+r[i].hraTypeId+"'>"+r[i].hraTypeName+"</td><td id='hraPrintFlag_"+r[i].hraTypeId+"'>"+r[i].hraPrintFlag+"</td><td id='endUserFlag_"+r[i].hraTypeId+"'>"+r[i].endUserFlag+"</td><td class='editUserAccess' onclick='updateHRAMaster("+index+","+r[i].hraTypeId+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteHRATypeMaster("+r[i].hraTypeId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#HRATypeId').val(index);
			$('#hraTypeMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function listOfHraTypeMasterDropDown(){
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
			var divContent="";
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].hraTypeId+"'>"+r[i].hraTypeName+"</option>";
			}
			$('#hraTypeMaster').html(divContent);
		}
	});
}

//$('#saveHRATypeMasterBtn').click(function(){
function saveHRATypeMasterBtn(){	
	var hraTypeMasterName=$('#hraTypeMasterName').val();
	var printFlag = $("#printFlag").val();
	var endUserFlag = $("#endUserFlag").val();
	if(hraTypeMasterName!=null && hraTypeMasterName!=""){
		saveHRATypeMaster(hraTypeMasterName,printFlag,endUserFlag);
	}
	else{
		alert("Please give HRA type name");
	}
};

$('#cancelHRATypeMasterBtn').click(function(){
	var index=$('.hraTypeMasterRow').length;
	$('#HRATypeId').val(index+1);
	$('#hraTypeMasterName').val("");
	$("#printFlag").val("Y");
	$("#endUserFlag").val("Y");
	$('#saveHRABtnDiv').show();
	$('#updateHRABtnDiv').hide();
});

function updateHRAMaster(hraTypeMasterIndex,hraTypeMasterId){
	var hraTypeName=$('#hraTypeName_'+hraTypeMasterId).html();
	var hraPrintFlag=$('#hraPrintFlag_'+hraTypeMasterId).html();
	var endUserFlag = $("#endUserFlag_"+hraTypeMasterId).html();
	$('#hraTypeMasterName').val(hraTypeName);
	$("#printFlag").val(hraPrintFlag);
	$("#endUserFlag").val(endUserFlag);
	$('#HRATypeId').val(hraTypeMasterIndex);
	$('#hraTypeMasterId').html(hraTypeMasterId);
	$('#saveHRABtnDiv').hide();
	$('#updateHRABtnDiv').show();
}

//$('#updateHRATypeMasterBtn').click(function(){
function updateHRATypeMasterBtn(){
	var hraTypeMasterName=$('#hraTypeMasterName').val();
	var hraTypeMasterId=$('#hraTypeMasterId').html();
	var printFlag = $("#printFlag").val();
	var endUserFlag = $("#endUserFlag").val();
	if(hraTypeMasterName!=null && hraTypeMasterName!=""){
		updateHRATypeMaster(hraTypeMasterName,hraTypeMasterId,printFlag,endUserFlag);
	}
	else{
		alert("Please give HRA type name");
	}
};

function updateHRATypeMaster(hraTypeMasterName,hraTypeMasterId,printFlag,endUserFlag){
	  jQuery.ajax({
		type : "GET",
		data : {
			"hraTypeMasterName":hraTypeMasterName,
			"hraTypeMasterId":hraTypeMasterId,
			"hraPrintFlag":printFlag,
			"endUserFlag":endUserFlag
			},
		url : "hra/updateHRATypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				$('#hraTypeMasterName').val("");
				$("#printFlag").val("Y");
				$("#endUserFlag").val("Y");
				listOfHraTypeMasterTableBody();
				$('#saveHRABtnDiv').show();
				$('#updateHRABtnDiv').hide();
				alert("HRA Type Master Updated Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to edit HRA Type Master");
    			}
		}
	});
}

function saveHRATypeMaster(hraTypeMasterName,printFlag,endUserFlag){
	  jQuery.ajax({
		type : "GET",
		data : {
			"hraTypeMasterName":hraTypeMasterName,
			"hraPrintFlag":printFlag,
			"endUserFlag":endUserFlag
			},
		url : "hra/saveHRATypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#hraTypeMasterName').val("");
			$("#printFlag").val("Y");
			$("#endUserFlag").val("Y");
			listOfHraTypeMasterTableBody();
			alert("HRA Type Master Saved Successfully");
		}
	});
} 

function deleteHRATypeMaster(hraTypeMasterId){
	var r = confirm("Are you sure you want to delete HRA Type Master?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {"hraTypeMasterId":hraTypeMasterId},
		url : "hra/deleteHRATypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfHraTypeMasterTableBody();
				alert("HRA Type Master Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete HRA Type Master");
    			}
		}
	});
    }
} 

function listOfQuestionMasterTableBody(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "hra/listOfQuestionMaster",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='questionMasterRow' id='question_master_"+r[i].questionId+"'><td class='center'>"+index+"</td><td id='question_"+r[i].questionId+"'>"+r[i].question+"</td><td>"+r[i].questionType+"</td><td>"+r[i].hraTypeMaster.hraTypeName+"</td><td onclick='getQuestionMaster("+index+","+r[i].questionId+")'><i class='fa fa-pencil'></i></td><td onclick='deleteQuestionMaster("+r[i].questionId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#questionId').val(index);
			$('#questionMasterTableBody').html(divContent);
			
			getUserAccess();
		}
	});
}

function deleteQuestionMaster(questionId){
	var r = confirm("Are you sure you want to delete Question?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {"questionId":questionId},
		url : "hra/deleteQuestionMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				//listOfQuestionMasterTableBody();
				displayQuestions($('#hraTypeMaster').val());
				alert("Question Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete question");
    			}
		}
	});
    }
} 

function saveQuestionMaster(){
	var question=$('#question').val();
	var questionDisplay=$('#questionDisplay').val();
	var questionType=$('#questionType').val();
	var hraTypeMasterId=$('#hraTypeMaster').val();
	var optionId=[];
		$(".option").map(function(){
			var optionValue = this.value;
			if(optionValue!=null && optionValue!=""){
			   	optionId.push(optionValue);
		   }
		});
	   if(question==null || question==""){
		   alert("Please write question");
	   }
	   else if(questionType==null || questionType==""){
		   alert("Please select question type");
	   }
	   else if(hraTypeMasterId==null || hraTypeMasterId==""){
		   alert("Please select HRA type");
	   }
	   else if($('#questionType').val()==="radio" || $('#questionType').val()==="checkbox"){
		   if(optionId==null || optionId==""){
			   alert("Please give atleast one option");
		   }
		   else{
			   jQuery.ajax({
					type : "POST",
					data : {
							"question":question,
							"questionDisplay":questionDisplay,
							"questionType":questionType,
							"hraTypeMasterId":hraTypeMasterId,
							"optionId":optionId
							},
					url : "hra/saveQuestionMaster",
					error : function() {
						alert("error");
					},
					success : function(r) {
						$('#optionDiv').show();
						$('#question').val("");
						$('#questionDisplay').val("");
						$('#questionType').val("");
						//$('#hraTypeMaster').val("");
						$('#option_0').val("");
						$('#newOptions').html("");
						listOfQuestionMasterTableBody();
						$('#addNewQuestionDiv').hide();
						displayQuestions(hraTypeMasterId);
						$('.mainQuestionDiv').animate({scrollTop:$('#questionDiv').height()}, 'slow');
						alert("Question Saved Successfully");
					}
				});
		   }
	   }
	   else{
		   jQuery.ajax({
				type : "POST",
				data : {
						"question":question,
						"questionDisplay":questionDisplay,
						"questionType":questionType,
						"hraTypeMasterId":hraTypeMasterId,
						"optionId":optionId
						},
				url : "hra/saveQuestionMaster",
				error : function() {
					alert("error");
				},
				success : function(r) {
					$('#optionDiv').show();
					$('#question').val("");
					$('#questionDisplay').val("");
					$('#questionType').val("");
					//$('#hraTypeMaster').val("");
					$('#option_0').val("");
					$('#newOptions').html("");
					listOfQuestionMasterTableBody();
					$('#addNewQuestionDiv').hide();
					displayQuestions(hraTypeMasterId);
					$('.mainQuestionDiv').animate({scrollTop:$('#questionDiv').height()}, 'slow');
					alert("Question Saved Successfully");
				}
			});
	   }
};

$('#cancelQuestionMasterBtn').click(function(){
	var index=$('.questionMasterRow').length;
	$('#questionId').val(index+1);
	$('#optionDiv').show();
	$('#question').val("");
	$('#questionType').val("");
	//$('#hraTypeMaster').val("");
	$('#option_0').val("");
	$('#newOptions').html("");
//	$('#saveQuestionBtnDiv').show();
	$('#updateQuestionBtnDiv').hide();
	$('#addNewQuestionDiv').hide();
});

function getQuestionMaster(questionId){
	jQuery.ajax({
		type : "POST",
		data : {"questionId":questionId},
		url : "hra/getQuestionMasterByQuestionId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit question");
    		}
    		else{
			$('#question').val(r.question);
			$('#questionType').val(r.questionType);
			$('#questionDisplay').val(r.questionDisplay);
			//$('#hraTypeMaster').val(r.hraTypeMaster.hraTypeId);
			//$('#questionId').val(index);
			$('#questionMasterId').val(questionId);
			 var length=r.optionMasterSet.length;
			   var options="";
			   if(length>0){
			   $('#option_0').val(r.optionMasterSet[0].option);
			   $('#option_0').attr('class','');
			   $('#option_0').addClass("option jfk-textinput magic-list-item-input optionMasterId_"+r.optionMasterSet[0].optionId);
			   for(var i=1;i<length;i++){
						   options = options
								+ '<div class="row" style="margin-left: 4px;"><div id=":g6.mli-label" class="ss-header goog-inline-block"></div><input value="'
								+ r.optionMasterSet[i].option
								+ '" type="text" placeholder="Options" style="margin-top: 5px;margin-right: 4px;" class="option jfk-textinput magic-list-item-input optionMasterId_'+r.optionMasterSet[i].optionId+'" id="option_'
								+ i
								+ '" name="option"><i id="removeOption_'
								+ i
								+ '" class="removeOption fa fa-minus-square removeOptionId_'
								+ r.optionMasterSet[i].optionId
								+ '" onclick="removeOption('
								+ r.optionMasterSet[i].optionId + ','
								+ questionId + ');"></i></div>';
			   }
			   $('#newOptions').html(options);
			   $('#optionDiv').show();
			   $('.mainQuestionDiv').animate({scrollTop:$('#questionDiv').height()}, 'slow');
			   }
			   else{
				   $('#optionDiv').hide();
			   }
			$('#saveQuestionBtnDiv').hide();
			$('#updateQuestionBtnDiv').show();
			$('#addNewQuestionDiv').show();
		}
		}
	});
}

function updateQuestionMaster(){
	var question=$('#question').val();
	var questionDisplay=$('#questionDisplay').val();
	var questionType=$('#questionType').val();
	var hraTypeMasterId=$('#hraTypeMaster').val();
	var questionMasterId=$('#questionMasterId').val();
	var optionId=[];
	var length=$('.option').length;
	   for(var i=0;i<length;i++){
		   var optionClass=$('#option_'+i).attr('class');
		   var optionMasterId=optionClass.split("_");
		   optionId.push($('#option_'+i).val()+"_"+optionMasterId[1]);
	   }
	  jQuery.ajax({
			type : "POST",
			data : {
					"question":question,
					"questionDisplay":questionDisplay,
					"questionType":questionType,
					"hraTypeMasterId":hraTypeMasterId,
					"optionId":optionId,
					"questionMasterId":questionMasterId
					},
			url : "hra/updateQuestionMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#question').val("");
				$('#questionType').val("");
				$('#questionDisplay').val("");
				//$('#hraTypeMaster').val("");
				$('#option_0').val("");
				$('#newOptions').html("");
				//listOfQuestionMasterTableBody();
				$('#addNewQuestionDiv').hide();
				displayQuestions(hraTypeMasterId);
				$('.mainQuestionDiv').animate({scrollTop:$('#questionDiv').height()}, 'slow');
				alert("Question Updated Successfully");
			}
		});
};

$('#addOptionsBtn').on('click', function() {
	var i=$('.option').length+1;
    $('<div class="row" id="hreOptionRow_'+i+'" style="margin-left: 4px;"><div id=":g6.mli-label" class="ss-header goog-inline-block"></div><input type="text" placeholder="Options" style="margin-top: 5px;margin-right: 4px;" class="option jfk-textinput magic-list-item-input" id="option_'+i+'" name="option"><i id="removeOption_'+i+'" class="removeOption fa fa-minus-square" onclick="removeOption('+i+',null);"></i></div>').appendTo(newOptions);
});

function removeOption(optionId,questionId){
	if(optionId!=null && optionId!="" && questionId!=null && questionId!=""){
		var r = confirm("Are you sure to delete Option?");
	    if (r == true) {
	jQuery.ajax({
		type : "POST",
		data : {
				"questionId":questionId,
				"optionId":optionId
				},
		url : "hra/deleteOptionMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var options="";
			 $('#option_0').val(r[0].option);
			 $('#option_0').addClass("optionMasterId_"+r[0].optionId);
			   for(var i=1;i<r.length;i++){
				   options=options+'<div class="row" style="margin-left: 4px;"><div id=":g6.mli-label" class="ss-header goog-inline-block"></div><input value="'+r[i].option+'" type="text" placeholder="Options" style="margin-top: 5px;margin-right: 4px;" class="option jfk-textinput magic-list-item-input optionMasterId_'+r[i].optionId+'" id="option_'+i+'" name="option"><i id="removeOption_'+i+'" class="removeOption fa fa-minus-square removeOptionId_'+r[i].optionId+'" onclick="removeOption('+r[i].optionId+','+questionId+');"></i></div>';
			   }
			   $('#newOptions').html(options);
			/*$('.optionMasterId_'+optionId).remove();
			$('.removeOptionId_'+optionId).remove();*/
			alert("Option Deleted Successfully");
		}
	});
	    }
	}
	else{
		   $('#hreOptionRow_'+optionId).remove();
		  // $('#removeOption_'+optionId).remove();
		   /*var length=$('.option').length;
		   var options="";
		   var index=1;
		   for(var i=0;i<length;i++){
			   options=options+'<div class="row" style="margin-left: 4px;"><div id=":g6.mli-label" class="ss-header goog-inline-block"></div><input type="text" placeholder="Options" style="margin-top: 5px;margin-right: 4px;" class="option jfk-textinput magic-list-item-input" id="option_'+index+'" name="option"><i id="removeOption_'+index+'" class="removeOption fa fa-minus-square" onclick="removeOption('+index+',null);"></i></div>';
			   index++;
		   }
		   $('#newOptions').html(options);*/
	}
}

function copyHRAFromVisit(){
	var clientId=$('#clientMasterId').html();
	var visitId=($('#visitSelect').val()).split("_")[1];
	var r = confirm("Are you sure you want to Copy HRA?");
    if (r == true) {
	$('#pleaseWait').show();
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"clientId" : clientId,
			"visitId":visitId
		},
		url : "hra/copyHRAFromVisit",
		catche : false,
		error : function() {
			alert("error");
			$('#pleaseWait').hide();
		},
		success : function(r) {
			$('#pleaseWait').hide();
			alert("HRA Copied Successfully");
		}
	});
   }
}

/*$('#newOptions').on('click','.removeOption', function() { 
   var removeId=$(this).attr('id');
   var removeClass=$(this).attr('class');
   var removeOptionId=removeClass.split("_");
   if(removeOptionId[1]!=null && removeOptionId[1]!=""){
	   alert("reomve");
   }
   var id=[];
   id=removeId.split("_");
   $("#"+removeId).remove();
   $('#option_'+id[1]).remove();
   var length=$('.option').length;
   var options="";
   var index=1;
   for(var i=0;i<length;i++){
	   options=options+'<input type="text" placeholder="Options" style="margin-top: 5px;" class="option form-control" id="option_'+index+'" name="option"><i id="removeOption_'+index+'" class="removeOption fa fa-minus-square"></i>';
	   index++;
   }
   $('#newOptions').html(options);
});*/