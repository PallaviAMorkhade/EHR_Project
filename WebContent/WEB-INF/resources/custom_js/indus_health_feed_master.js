function listOfFeedTypeMasterSelect(){
	jQuery.ajax({
		type : "POST",
		url : "feedType/listOfFeedTypeMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].feedTypeId+"'>"+r[i].feedType+"</option>";
				index++;
			}
			$('#feedTypeMasterSelect').html(divContent);
			getUserAccess();
		}
	});
}

function saveHealthFeedMaster(){
	var healthFeed=$('#healthFeed').val();
	var healthFeedDate=$('#healthFeedDate').val();
	var healthFeedDescription=$('#healthFeedDescription').val();
	var feedTypeId=$('#feedTypeMasterSelect').val();
	if(healthFeed==null || healthFeed==""){
		alert("Please write Health Feed");
	}
	else if(feedTypeId==null || feedTypeId==""){
		alert("Please select Feed Type");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"healthFeed":healthFeed,
						"feedTypeId" : feedTypeId,
						"healthFeedDate" : healthFeedDate,
						"healthFeedDescription" : healthFeedDescription
					},
			url : "healthFeed/saveHealthFeedMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#healthFeed').val("");
				$('#feedTypeMasterSelect').val("");
				$('#healthFeedDate').val("");
				$('#healthFeedDescription').val("");
				listOfHealthFeedMaster();
				alert("Health Feed Saved Successfully");
			}
		});
	}
};

function updateHealthFeedMaster(){
	var healthFeed=$('#healthFeed').val();
	var feedTypeId=$('#feedTypeMasterSelect').val();
	var healthFeedDescription=$('#healthFeedDescription').val();
	if(healthFeed==null || healthFeed==""){
		alert("Please write Health Feed");
	}
	else if(feedTypeId==null || feedTypeId==""){
		alert("Please select Feed Type");
	}
	else{
	var healthFeedMasterId=$('#healthFeedMasterId').html();
	   jQuery.ajax({
			type : "POST",
			data : {
						"healthFeed":healthFeed,
						"feedTypeId" : feedTypeId,
						"healthFeedMasterId":healthFeedMasterId,
						"healthFeedDescription" : healthFeedDescription
					},
			url : "healthFeed/updateHealthFeedMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#healthFeed').val("");
				$('#feedTypeMasterSelect').val("");
				$('#healthFeedDate').val("");
				$('#healthFeedDescription').val("");
				listOfHealthFeedMaster();
				$('#saveHealthFeedBtnDiv').show();
				$('#updateHealthFeedBtnDiv').hide();
				alert("Health Feed Updated Successfully");
			}
		});
	}
};

$('#cancelHealthFeedMasterBtn').click(function(){
	$('#healthFeed').val("");
	$('#feedTypeMasterSelect').val("");
	$('#healthFeedDate').val("");
	$('#healthFeedDescription').val("");
	listOfHealthFeedMaster();
	$('#saveHealthFeedBtnDiv').show();
	$('#updateHealthFeedBtnDiv').hide();
	$('#healthFeedDate').removeAttr("disabled","disabled");
});

function listOfHealthFeedMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "healthFeed/listOfHealthFeedMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='healthFeedMasterRow' id='health_feed_master_"+r[i].healthFeedId+"'><td class='center'>"+index+"</td><td id='healthFeed"+r[i].healthFeedId+"'>"+r[i].healthFeed+"</td><td>"+r[i].feedType+"</td><td class='editUserAccess' onclick='getHealthFeedMaster("+r[i].healthFeedId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteHealthFeedMaster("+r[i].healthFeedId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#healthFeedId').val(index);
			$('#healthFeedMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteHealthFeedMaster(healthFeedMasterId){
	var r = confirm("Are you sure you want to delete Health Feed?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"healthFeedMasterId":healthFeedMasterId},
		url : "healthFeed/deleteHealthFeedMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfHealthFeedMaster();
				alert("Health Feed Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete health feed");
    			}
		}
	});
    }
}

function getHealthFeedMaster(healthFeedMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"healthFeedMasterId":healthFeedMasterId},
		url : "healthFeed/getHealthFeedMasterByHealthFeedId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit health feed");
    		}
    		else{
			$('#saveHealthFeedBtnDiv').hide();
			$('#updateHealthFeedBtnDiv').show();
			$('#healthFeed').val(r.healthFeed);
			$('#healthFeedMasterId').html(r.healthFeedId);
			$('#feedTypeMasterSelect').val(r.feedTypeId);
			$('#healthFeedDate').val(r.healthFeedDate);
			$('#healthFeedDescription').val(r.healthFeedDescription);
			$('#healthFeedId').val(index);
			$('#healthFeedDate').attr("disabled","disabled");
    		}
		}
	});
}
