//$('#saveTemplateMasterBtn').click(function(){
function saveTemplateMaster(){
	var templateName=$('#templateName').val();
	var templateDescription=$('#templateDescription').val();
	if(templateName==null || templateName==""){
		alert("Please give template name");
	}
	else if(templateDescription==null || templateDescription==""){
		alert("Please give template description");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"templateName":templateName,
						"templateDescription":templateDescription
					},
			url : "template/saveTemplateMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#templateName').val(" ");
				$('#templateDescription').val(" ");
				listOfTemplateMaster();
				alert("Template Saved Successfully");
			}
		});
	}
};

//$('#updateTemplateMasterBtn').click(function(){
function updateTemplateMaster(){
	var templateName=$('#templateName').val();
	var templateDescription=$('#templateDescription').val();
	var templateMasterId=$('#templateMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"templateName":templateName,
						"templateDescription":templateDescription,
						"templateMasterId":templateMasterId
					},
			url : "template/updateTemplateMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#templateName').val(" ");
				$('#templateDescription').val(" ");
				listOfTemplateMaster();
				$('#saveTemplateBtnDiv').show();
				$('#updateTemplateBtnDiv').hide();
				alert("Template updated");
			}
		});
};

$('#cancelTemplateMasterBtn').click(function(){
	$('#templateName').val(" ");
	$('#templateDescription').val(" ");
	listOfTemplateMaster();
	$('#saveTemplateBtnDiv').show();
	$('#updateTemplateBtnDiv').hide();
});

function listOfTemplateMaster(){
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
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='templateMasterRow' id='template_master_"+r[i].templateId+"'><td class='center'>"+index+"</td><td id='template_"+r[i].templateId+"'>"+r[i].templateName+"</td><td class='editUserAccess' onclick='getTemplateMaster("+r[i].templateId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteTemplateMaster("+r[i].templateId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#templateId').val(index);
			$('#templateMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteTemplateMaster(templateMasterId){
	var r = confirm("Are you sure you want to delete template?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"templateMasterId":templateMasterId},
		url : "template/deleteTemplateMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfTemplateMaster();
				alert("Template Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}
		}
	});
    }
}

function getTemplateMaster(templateMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"templateMasterId":templateMasterId},
		url : "template/getTemplateMasterByTemplateId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit template");
    		}
    		else{
			$('#saveTemplateBtnDiv').hide();
			$('#updateTemplateBtnDiv').show();
			$('#templateName').val(r.templateName);
			$('#templateDescription').val(r.templateDescription);
			$('#templateId').val(index);
			$('#templateMasterId').html(templateMasterId);
    		}
		}
	});
}
