function saveVisitTypeMaster(){
	var visitType=$('#visitType').val();
	if(visitType==null || visitType==""){
		alert("Please write Visit Type");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"visitType":visitType
					},
			url : "visitType/saveVisitTypeMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#visitType').val("");
				listOfVisitTypeMaster();
				alert("Visit Type Saved Successfully");
			}
		});
	}
};

function updateVisitTypeMaster(){
	var visitType=$('#visitType').val();
	var visitTypeMasterId=$('#visitTypeMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"visitType":visitType,
						"visitTypeMasterId":visitTypeMasterId
					},
			url : "visitType/updateVisitTypeMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#visitType').val("");
				listOfVisitTypeMaster();
				$('#saveVisitTypeBtnDiv').show();
				$('#updateVisitTypeBtnDiv').hide();
				alert("Visit Type Updated Successfully");
			}
		});
};

$('#cancelVisitTypeMasterBtn').click(function(){
	$('#visitType').val("");
	listOfVisitTypeMaster();
	$('#saveVisitTypeBtnDiv').show();
	$('#updateVisitTypeBtnDiv').hide();
});

function listOfVisitTypeMaster(){
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
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='visitTypeMasterRow' id='visit_type_master_"+r[i].visitTypeId+"'><td class='center'>"+index+"</td><td id='visitType"+r[i].visitTypeId+"'>"+r[i].visitType+"</td><td class='editUserAccess' onclick='getVisitTypeMaster("+r[i].visitTypeId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteVisitTypeMaster("+r[i].visitTypeId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#visitTypeId').val(index);
			$('#visitTypeMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteVisitTypeMaster(visitTypeMasterId){
	var r = confirm("Are you sure you want to delete visit type?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"visitTypeMasterId":visitTypeMasterId},
		url : "visitType/deleteVisitTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfVisitTypeMaster();
				alert("Visit Type Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete visit type");
    			}
		}
	});
    }
}

function getVisitTypeMaster(visitTypeMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"visitTypeMasterId":visitTypeMasterId},
		url : "visitType/getVisitTypeMasterByVisitTypeId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit visit type");
    		}
    		else{
			$('#saveVisitTypeBtnDiv').hide();
			$('#updateVisitTypeBtnDiv').show();
			$('#visitType').val(r.visitType);
			$('#visitTypeMasterId').html(r.visitTypeId);
			$('#visitTypeId').val(index);
    		}
		}
	});
}
