function saveVideoMaster(){
	var fileName=$('#videoFiles').val();
	var videoDescription=$('#videoDescription').val();
	var videoDate=$('#videoDate').val();
	if(fileName==null || fileName==""){
		alert("Please select File");
	}
	else if(videoDescription==null || videoDescription==""){
		alert("Please give video description");
	}
	else if(videoDate==null || videoDate==""){
		alert("Please give video Date");
	}
	else{
	$('.uploadVideo').trigger("click");
	   jQuery.ajax({
			type : "POST",
			data : {
						"fileName":fileName,
						"videoDescription":videoDescription,
						"videoDate" : videoDate
					},
			url : "video/saveVideoMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#fileName').val("");
				$('#videoDescription').val("");
				$('#videoDate').val("");
				//listOfVideoMaster();
				//alert("File has been uploaded successfully!");
			}
		});
	}
};

function updateVideoMaster(){
	var videoMasterId=$('#videoMasterId').html();
	var videoDescription=$('#videoDescription').val();
	var videoDate=$('#videoDate').val();
	if(videoDate==null || videoDate==""){
		alert("Please give video Date");
	}
	else if(videoDescription==null || videoDescription==""){
		alert("Please give video description");
	}
	else{
	   jQuery.ajax({
			type : "POST",
			data : {
						"videoDescription":videoDescription,
						"videoDate" : videoDate,
						"videoMasterId":videoMasterId
					},
			url : "video/updateVideoMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#fileName').val("");
				$('#videoDescription').val("");
				$('#videoDate').val("");
				listOfVideoMaster();
				$('#saveVideoBtnDiv').show();
				$('#updateVideoBtnDiv').hide();
				alert("Video Updated Successfully");
			}
		});
	}
};

$('#cancelVideoMasterBtn').click(function(){
	$('#fileName').val("");
	$('#videoDescription').val("");
	$('#videoDate').val("");
	listOfVideoMaster();
	$('#saveVideoBtnDiv').show();
	$('#updateVideoBtnDiv').hide();
	$('#videoDate').removeAttr("disabled","disabled");
});

function listOfVideoMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "video/listOfVideoMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='videoMasterRow' id='video_master_"+r[i].videoId+"'><td class='center'>"+index+"</td><td id='link_"+r[i].videoId+"'>"+r[i].fileName+"</td><td class='editUserAccess' onclick='getVideoMaster("+r[i].videoId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteVideoMaster("+r[i].videoId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#videoId').val(index);
			$('#videoMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteVideoMaster(videoMasterId){
	var r = confirm("Are you sure you want to delete video?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"videoMasterId":videoMasterId},
		url : "video/deleteVideoMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfVideoMaster();
				alert("Video Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete video");
    			}
		}
	});
    }
}

function getVideoMaster(videoMasterId,index){
	jQuery.ajax({
		type : "POST",
		data : {"videoMasterId":videoMasterId},
		url : "video/getVideoMasterByVideoId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit video");
    		}
    		else{
			$('#saveVideoBtnDiv').hide();
			$('#updateVideoBtnDiv').show();
			//$('#fileName').val(r.link);
			$('#videoDescription').val(r.videoDescription);
			$('#videoDate').val(r.videoDate);
			$('#videoId').val(index);
			$('#videoMasterId').html(videoMasterId);
			
			$('#videoDate').attr("disabled","disabled");
    		}
		}
	});
}
