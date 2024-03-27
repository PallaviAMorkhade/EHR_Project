//$('#saveRejectMasterBtn').click(function(){
function saveRejectMaster(){
	var rejectReason=$('#rejectReason').val();
	if(rejectReason==null || rejectReason==""){
		alert("Please give reject reason");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"rejectReason":rejectReason
					},
			url : "reject/saveRejectMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#rejectReason').val("");
				listOfRejectMaster();
				alert("Reject Reason Saved Successfully");
			}
		});
	}
};

//$('#updateRejectMasterBtn').click(function(){
function updateRejectMaster(){
	var rejectReason=$('#rejectReason').val();
	var rejectMasterId=$('#rejectMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"rejectReason":rejectReason,
						"rejectMasterId":rejectMasterId
					},
			url : "reject/updateRejectMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#rejectReason').val("");
				listOfRejectMaster();
				$('#saveRejectBtnDiv').show();
				$('#updateRejectBtnDiv').hide();
				alert("Reject Reason Updated Successfully");
			}
		});
};

$('#cancelRejectMasterBtn').click(function(){
	$('#rejectReason').val("");
	listOfRejectMaster();
	$('#saveRejectBtnDiv').show();
	$('#updateRejectBtnDiv').hide();
});

function listOfRejectMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "reject/listOfRejectMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='rejectMasterRow' id='reject_master_"+r[i].rejectId+"'><td class='center'>"+index+"</td><td id='parameter_"+r[i].rejectId+"'>"+r[i].rejectReason+"</td><td class='editUserAccess' onclick='getRejectMaster("+r[i].rejectId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteRejectMaster("+r[i].rejectId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#rejectId').val(index);
			$('#rejectMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteRejectMaster(rejectMasterId){
	var r = confirm("Are you sure you want to delete Reject reason?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"rejectMasterId":rejectMasterId},
		url : "reject/deleteRejectMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfRejectMaster();
				alert("Reject reason deleted");
    			}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}
		}
	});
    }
}

function getRejectMaster(rejectMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"rejectMasterId":rejectMasterId},
		url : "reject/getRejectMasterByRejectId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit reject reason");
    		}
    		else{
			$('#saveRejectBtnDiv').hide();
			$('#updateRejectBtnDiv').show();
			$('#rejectReason').val(r.rejectReason);
			$('#rejectMasterId').html(r.rejectId);
			$('#rejectId').val(index);
    		}
		}
	});
}
