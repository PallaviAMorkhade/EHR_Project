function listOfActionMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "action/listOfActionMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].actionId+"'>"+r[i].action+"</option>";
			}
			$('#actionMasterSelect').html(divContent);
			getUserAccess();
		}
	});
}

function saveSubActionMaster(){
	var subAction=$('#subAction').val();
	var actionId=$('#actionMasterSelect').val();
	if(subAction==null || subAction==""){
		alert("Please write sub action");
	}
	else if(actionId==null || actionId==""){
		alert("Please select action");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"subAction":subAction,
						"actionId" : actionId
					},
			url : "subAction/saveSubActionMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#subAction').val("");
				$('#actionMasterSelect').val("");
				listOfSubActionMaster();
				alert("Sub Action Saved Successfully");
			}
		});
	}
};

function updateSubActionMaster(){
	var subAction=$('#subAction').val();
	var actionId=$('#actionMasterSelect').val();
	if(subAction==null || subAction==""){
		alert("Please write sub action");
	}
	else if(actionId==null || actionId==""){
		alert("Please select action");
	}
	else{
	var subActionMasterId=$('#subActionMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"subAction":subAction,
						"subActionMasterId":subActionMasterId,
						"actionId" : actionId
					},
			url : "subAction/updateSubActionMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#subAction').val("");
				$('#actionMasterSelect').val("");
				listOfSubActionMaster();
				$('#saveSubActionBtnDiv').show();
				$('#updateSubActionBtnDiv').hide();
				alert("Sub Action Updated Successfully");
			}
		});
	}
};

$('#cancelSubActionMasterBtn').click(function(){
	$('#subAction').val("");
	$('#actionMasterSelect').val("");
	listOfSubActionMaster();
	$('#saveSubActionBtnDiv').show();
	$('#updateSubActionBtnDiv').hide();
});

function listOfSubActionMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "subAction/listOfSubActionMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='subActionMasterRow' id='sub_action_master_"+r[i].subActionId+"'><td class='center'>"+index+"</td><td id='subAction"+r[i].subActionId+"'>"+r[i].subAction+"</td><td>"+r[i].actionMaster.action+"</td><td class='editUserAccess' onclick='getSubActionMaster("+r[i].subActionId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteSubActionMaster("+r[i].subActionId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#subActionId').val(index);
			$('#subActionMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteSubActionMaster(subActionMasterId){
	var r = confirm("Are you sure you want to delete sub action?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"subActionMasterId":subActionMasterId},
		url : "subAction/deleteSubActionMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfSubActionMaster();
				alert("Sub Action Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete sub action");
    			}
		}
	});
    }
}

function getSubActionMaster(subActionMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"subActionMasterId":subActionMasterId},
		url : "subAction/getSubActionMasterBySubActionId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit sub action");
    		}
    		else{
			$('#saveSubActionBtnDiv').hide();
			$('#updateSubActionBtnDiv').show();
			$('#subAction').val(r.subAction);
			$('#subActionMasterId').html(r.subActionId);
			$('#actionMasterSelect').val(r.actionMaster.actionId);
			$('#subActionId').val(index);
    		}
		}
	});
}
