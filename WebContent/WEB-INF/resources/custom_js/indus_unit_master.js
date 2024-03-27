function saveUnitMasterBtn(){
	var unitMasterName=$('#unitMasterName').val();
	if(unitMasterName!=null && unitMasterName!=""){
		saveUnitMaster(unitMasterName);
	}
	else{
		alert("Please give unit name");
	}
};

function saveUnitMaster(unitMasterName){
	  jQuery.ajax({
		type : "POST",
		data : {"unitMasterName":unitMasterName},
		url : "user/saveUnitMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#unitMasterName').val("");
			listOfUnitMaster();
			alert("Unit Saved Successfully");
		}
	});
}

function listOfUnitMaster(){
	jQuery.ajax({
		type : "POST",
		url : "user/listOfUnitMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='unitMasterRow' id='unit_master_"+r[i].unitId+"'><td class='center'>"+index+"</td><td id='unitName_"+r[i].unitId+"'>"+r[i].unitName+"</td><td class='editUserAccess' onclick='getUnitMaster("+index+","+r[i].unitId+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteUnitMaster("+r[i].unitId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#unitId').val(index);
			$('#unitMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function getUnitMaster(index,unitId){
	var unitName=$('#unitName_'+unitId).html();
	$('#unitMasterName').val(unitName);
	$('#unitId').val(index);
	$('#unitMasterId').html(unitId);
	$('#saveUnitBtnDiv').hide();
	$('#updateUnitBtnDiv').show();
}

//$('#updateUnitMasterBtn').click(function(){
function updateUnitMasterBtn(){
	var unitMasterName=$('#unitMasterName').val();
	var unitMasterId=$('#unitMasterId').html();
	if(unitMasterName!=null && unitMasterName!=""){
		updateUnitMaster(unitMasterId,unitMasterName);
	}
};

function updateUnitMaster(unitMasterId,unitMasterName){
	  jQuery.ajax({
		type : "POST",
		data : {"unitMasterId":unitMasterId,"unitMasterName":unitMasterName},
		url : "user/updateUnitMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				$('#unitMasterName').val("");
				listOfUnitMaster();
				$('#saveUnitBtnDiv').show();
				$('#updateUnitBtnDiv').hide();
				alert("Unit updated successfully");
    		}
    			else{
    				alert("Sorry you don't have access to edit unit");
    			}
		}
	});
}

function deleteUnitMaster(unitMasterId){
	var r = confirm("Are you sure you want to delete unit?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"unitMasterId":unitMasterId},
		url : "user/deleteUnitMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				$('#unitMasterName').val("");
				listOfUnitMaster();
				alert("Unit Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete unit");
    			}
		}
	});
    }
}

$('#cancelUnitMasterBtn').click(function(){
	var index=$('.unitMasterRow').length;
	$('#unitId').val(index+1);
	$('#unitMasterName').val("");
	$('#saveUnitBtnDiv').show();
	$('#updateUnitBtnDiv').hide();
});