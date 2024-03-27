//$('#saveActionMasterBtn').click(function(){
function saveActionMaster(){
	var action=$('#action').val();
	if(action==null || action==""){
		alert("Please write action");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"action":action
					},
			url : "action/saveActionMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#action').val("");
				listOfActionMaster();
				alert("Action Saved Successfully");
			}
		});
	}
};

//$('#updateActionMasterBtn').click(function(){
function updateActionMaster(){
	var action=$('#action').val();
	var actionMasterId=$('#actionMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"action":action,
						"actionMasterId":actionMasterId
					},
			url : "action/updateActionMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#action').val("");
				listOfActionMaster();
				$('#saveActionBtnDiv').show();
				$('#updateActionBtnDiv').hide();
				alert("Action Updated Successfully");
			}
		});
};

$('#cancelActionMasterBtn').click(function(){
	$('#action').val("");
	listOfActionMaster();
	$('#saveActionBtnDiv').show();
	$('#updateActionBtnDiv').hide();
});

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
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='actionMasterRow' id='action_master_"+r[i].actionId+"'><td class='center'>"+index+"</td><td id='action"+r[i].actionId+"'>"+r[i].action+"</td><td class='editUserAccess' onclick='getActionMaster("+r[i].actionId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteActionMaster("+r[i].actionId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#actionId').val(index);
			$('#actionMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteActionMaster(actionMasterId){
	var r = confirm("Are you sure you want to delete action?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"actionMasterId":actionMasterId},
		url : "action/deleteActionMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfActionMaster();
				alert("Action Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete action");
    			}
		}
	});
    }
}

function getActionMaster(actionMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"actionMasterId":actionMasterId},
		url : "action/getActionMasterByActionId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit action");
    		}
    		else{
			$('#saveActionBtnDiv').hide();
			$('#updateActionBtnDiv').show();
			$('#action').val(r.action);
			$('#actionMasterId').html(r.actionId);
			$('#actionId').val(index);
    		}
		}
	});
}
