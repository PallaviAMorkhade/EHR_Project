//$('#saveSmsTemplateMasterBtn').click(function(){
function saveSmsTemplateMaster(){
	var smsTemplateName=$('#smsTemplateName').val();
	var smsTemplateDescription=$('#smsTemplateDescription').val();
	if(smsTemplateName==null || smsTemplateName==""){
		alert("Please give template name");
	}
	else if(smsTemplateDescription==null || smsTemplateDescription==""){
		alert("Please give template description");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"smsTemplateName":smsTemplateName,
						"smsTemplateDescription":smsTemplateDescription
					},
			url : "template/saveSmsTemplateMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#smsTemplateName').val(" ");
				$('#smsTemplateDescription').val(" ");
				listOfSmsTemplateMaster();
				alert("Template Saved Successfully");
			}
		});
	}
};

//$('#updateSmsTemplateMasterBtn').click(function(){
function updateSmsTemplateMaster(){
	var smsTemplateName=$('#smsTemplateName').val();
	var smsTemplateDescription=$('#smsTemplateDescription').val();
	var smsTemplateMasterId=$('#smsTemplateMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"smsTemplateName":smsTemplateName,
						"smsTemplateDescription":smsTemplateDescription,
						"smsTemplateMasterId":smsTemplateMasterId
					},
			url : "template/updateSmsTemplateMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#smsTemplateName').val(" ");
				$('#smsTemplateDescription').val(" ");
				listOfSmsTemplateMaster();
				$('#saveSmsTemplateBtnDiv').show();
				$('#updateSmsTemplateBtnDiv').hide();
				alert("Template Updated Successfully");
			}
		});
};

$('#cancelSmsTemplateMasterBtn').click(function(){
	$('#smsTemplateName').val(" ");
	$('#smsTemplateDescription').val(" ");
	listOfSmsTemplateMaster();
	$('#saveSmsTemplateBtnDiv').show();
	$('#updateSmsTemplateBtnDiv').hide();
});

function listOfSmsTemplateMaster(){
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
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='smsTemplateMasterRow' id='smsTemplate_master_"+r[i].smsTemplateId+"'><td class='center'>"+index+"</td><td id='smsTemplate_"+r[i].smsTemplateId+"'>"+r[i].smsTemplateName+"</td><td class='editUserAccess' onclick='getSmsTemplateMaster("+r[i].smsTemplateId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteSmsTemplateMaster("+r[i].smsTemplateId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#smsTemplateId').val(index);
			$('#smsTemplateMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteSmsTemplateMaster(smsTemplateMasterId){
	var r = confirm("Are you sure you want to delete template?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"smsTemplateMasterId":smsTemplateMasterId},
		url : "template/deleteSmsTemplateMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfSmsTemplateMaster();
				alert("Template Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}
		}
	});
    }
}

function getSmsTemplateMaster(smsTemplateMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"smsTemplateMasterId":smsTemplateMasterId},
		url : "template/getSmsTemplateMasterByTemplateId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit template");
    		}
    		else{
			$('#saveSmsTemplateBtnDiv').hide();
			$('#updateSmsTemplateBtnDiv').show();
			$('#smsTemplateName').val(r.smsTemplateName);
			$('#smsTemplateDescription').val(r.smsTemplateDescription);
			$('#smsTemplateId').val(index);
			$('#smsTemplateMasterId').html(smsTemplateMasterId);
    		}
		}
	});
}
