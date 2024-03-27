function saveLinkMaster(){
	var link=$('#link').val();
	var linkDescription=$('#linkDescription').val();
	var linkDate=$('#linkDate').val();
	if(link==null || link==""){
		alert("Please give link");
	}
	else if(linkDescription==null || linkDescription==""){
		alert("Please give link description");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"link":link,
						"linkDescription":linkDescription,
						"linkDate" : linkDate
					},
			url : "link/saveLinkMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#link').val("");
				$('#linkDescription').val("");
				$('#linkDate').val("");
				listOfLinkMaster();
				alert("Link Saved Successfully");
			}
		});
	}
};

function updateLinkMaster(){
	var linkMasterId=$('#linkMasterId').html();
	var link=$('#link').val();
	var linkDescription=$('#linkDescription').val();
	if(link==null || link==""){
		alert("Please give link");
	}
	else if(linkDescription==null || linkDescription==""){
		alert("Please give link description");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"link":link,
						"linkDescription":linkDescription,
						"linkMasterId":linkMasterId
					},
			url : "link/updateLinkMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#link').val(" ");
				$('#linkDescription').val(" ");
				$('#linkDate').val("");
				listOfLinkMaster();
				$('#saveLinkBtnDiv').show();
				$('#updateLinkBtnDiv').hide();
				alert("Link Updated Successfully");
			}
		});
	}
};

$('#cancelLinkMasterBtn').click(function(){
	$('#link').val(" ");
	$('#linkDescription').val(" ");
	$('#linkDate').val("");
	listOfLinkMaster();
	$('#saveLinkBtnDiv').show();
	$('#updateLinkBtnDiv').hide();
	$('#linkDate').removeAttr("disabled","disabled");
});

function listOfLinkMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "link/listOfLinkMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='linkMasterRow' id='link_master_"+r[i].linkId+"'><td class='center'>"+index+"</td><td id='link_"+r[i].linkId+"'>"+r[i].link+"</td><td class='editUserAccess' onclick='getLinkMaster("+r[i].linkId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteLinkMaster("+r[i].linkId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#linkId').val(index);
			$('#linkMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteLinkMaster(linkMasterId){
	var r = confirm("Are you sure you want to delete link?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"linkMasterId":linkMasterId},
		url : "link/deleteLinkMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfLinkMaster();
				alert("Link Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete link");
    			}
		}
	});
    }
}

function getLinkMaster(linkMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"linkMasterId":linkMasterId},
		url : "link/getLinkMasterByLinkId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit link");
    		}
    		else{
			$('#saveLinkBtnDiv').hide();
			$('#updateLinkBtnDiv').show();
			$('#link').val(r.link);
			$('#linkDescription').val(r.linkDescription);
			$('#linkDate').val(r.linkDate);
			$('#linkId').val(index);
			$('#linkMasterId').html(linkMasterId);
			$('#linkDate').attr("disabled","disabled");
    		}
		}
	});
}
