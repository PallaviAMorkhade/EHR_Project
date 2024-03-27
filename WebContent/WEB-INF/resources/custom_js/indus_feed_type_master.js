function saveFeedTypeMasterBtn(){
	var feedTypeMaster=$('#feedTypeMaster').val();
	if(feedTypeMaster!=null && feedTypeMaster!=""){
		saveFeedTypeMaster(feedTypeMaster);
	}
	else{
		alert("Please give Feed Type ");
	}
};

function saveFeedTypeMaster(feedType){
	  jQuery.ajax({
		type : "POST",
		data : {"feedType":feedType},
		url : "feedType/saveFeedTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#feedTypeMaster').val("");
			listOfFeedTypeMaster();
			alert("Feed Type Saved Successfully");
		}
	});
}

function listOfFeedTypeMaster(){
	jQuery.ajax({
		type : "POST",
		url : "feedType/listOfFeedTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='feedTypeMasterRow' id='feed_type_master_"+r[i].feedTypeId+"'><td class='center'>"+index+"</td><td id='feedType_"+r[i].feedTypeId+"'>"+r[i].feedType+"</td><td class='editUserAccess' onclick='getFeedTypeMaster("+index+","+r[i].feedTypeId+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteFeedTypeMaster("+r[i].feedTypeId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#feedTypeId').val(index);
			$('#feedTypeMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function getFeedTypeMaster(index,feedTypeId){
	var feedType=$('#feedType_'+feedTypeId).html();
	$('#feedTypeMaster').val(feedType);
	$('#feedTypeId').val(index);
	$('#feedTypeMasterId').html(feedTypeId);
	$('#saveFeedTypeBtnDiv').hide();
	$('#updateFeedTypeBtnDiv').show();
}

function updateFeedTypeMasterBtn(){
	var feedTypeMaster=$('#feedTypeMaster').val();
	var feedTypeMasterId=$('#feedTypeMasterId').html();
	if(feedTypeMaster!=null && feedTypeMaster!=""){
		updateFeedTypeMaster(feedTypeMasterId,feedTypeMaster);
	}
};

function updateFeedTypeMaster(feedTypeMasterId,feedType){
	  jQuery.ajax({
		type : "POST",
		data : {"feedTypeMasterId":feedTypeMasterId,"feedType":feedType},
		url : "feedType/updateFeedTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				$('#feedTypeMaster').val("");
				listOfFeedTypeMaster();
				$('#saveFeedTypeBtnDiv').show();
				$('#updateFeedTypeBtnDiv').hide();
				alert("Feed Type Updated Successfully");
    		}
    			else{
    				alert("Sorry you don't have access to edit Feed Type");
    			}
		}
	});
}

function deleteFeedTypeMaster(feedTypeMasterId){
	var r = confirm("Are you sure you want to delete Feed Type?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"feedTypeMasterId":feedTypeMasterId},
		url : "feedType/deleteFeedTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				$('#feedTypeMaster').val("");
				listOfFeedTypeMaster();
				alert("Feed Type Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete Feed Type");
    			}
		}
	});
    }
}

$('#cancelFeedTypeMasterBtn').click(function(){
	var index=$('.feedTypeMasterRow').length;
	$('#feedTypeId').val(index+1);
	$('#feedTypeMaster').val("");
	$('#saveFeedTypeBtnDiv').show();
	$('#updateFeedTypeBtnDiv').hide();
});