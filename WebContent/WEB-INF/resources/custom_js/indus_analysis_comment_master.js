function saveCommentMaster(){
	var commentMasterId=$('#commentMasterId').html();
	var comment=$('#comment').val();
	if(comment==null || comment==""){
		alert("Please Write Comment");
	}
	else{
		var commentMaster = {
			"commentId":commentMasterId,
			"comment":comment
		};
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			//dataType : 'json',
			url : "comment/saveAnalysisComment",
			data : JSON.stringify(commentMaster),
			/*error : function(response) {
				alert("error"+response);
			},*/
			success : function(response) {
				if(commentMasterId==""){
					alert("Analysis Comment Saved Successfully");
				}else{
					alert("Analysis Comment Updated Successfully");
				}
				listOfCommentMaster();
				clearCommentMaster();
			}
		});
	}
};

function clearCommentMaster(){
	$("#commentId").val($("#commentMasterTableBody tr").length+1);
	$('#commentMasterId').html("");
	$('#comment').val("");
	$('#saveCommentBtnDiv').show();
	$('#updateCommentBtnDiv').hide();
}

function listOfCommentMaster(){
	jQuery.ajax({
		async : true,
		type : "GET",
		url : "comment/listOfAnalysisComment",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='commentMasterRow' id='comment_master_"+r[i].commentId+"'><td class='center'>"+index+"</td><td id='comment"+r[i].commentId+"'>"+r[i].comment+"</td><td class='editUserAccess' onclick='getCommentMaster("+r[i].commentId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteCommentMaster("+r[i].commentId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#commentId').val(index);
			$('#commentMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteCommentMaster(commentMasterId){
	var r = confirm("Are you sure you want to delete Comment?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {"analysisCommentMasterId":commentMasterId},
		url : "comment/deleteAnalysisComment",
		error : function() {
			alert("error");
		},
		success : function(r) {
			//if(r=="true"){
				listOfCommentMaster();
				alert("Analysis Comment Deleted Successfully");
    			/*}
    			else{
    				alert("Sorry you don't have access to delete comment");
    			}*/
		}
	});
    }
}

function getCommentMaster(commentMasterId,index){
	jQuery.ajax({
		type : "GET",
		data : {"analysisCommentMasterId":commentMasterId},
		url : "comment/getAnalysisCommentByCommentId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			/*if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit comment");
    		}
    		else{*/
			$('#saveCommentBtnDiv').hide();
			$('#updateCommentBtnDiv').show();
			$('#comment').val(r.comment);
			$('#commentMasterId').html(r.commentId);
			$('#commentId').val(index);
    		//}
		}
	});
}
