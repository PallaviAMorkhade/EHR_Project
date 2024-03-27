 $("#searchBox").autocomplete({
		 source : function(request, response) {
			 var searchKeyword = $("#searchBox").val();
				jQuery.ajax({
					type : "POST",
					data : {
						searchKeyword : searchKeyword
					},
					url : "user/autoSuggestionsUsers",
					timeout : 1000 * 60 * 5,
					catche : false,
					error : function() {
						alert('error');
					},
					success : function(r) {
						var availableTags = [];
						for(var i=0;i<r.length;i++){
							availableTags[i]=r[i].firstName+" "+r[i].middleName+" "+r[i].lastName+"_"+r[i].userId;
						}
						response(availableTags);
					}
				});
		 	},
		 	select: function(event, ui) {
		 		var searchKeyword = ui.item.value;
		 		var id=searchKeyword.split("_");
		 		updateUserAccess(id[1]);
		 	}
	});
		
    $( "#searchBox" ).autocomplete("widget").addClass("fixedHeight");
    
    function updateUserAccess(userId){
    	//$('#tree_2').jstree('select_node', 'j1_1_anchor');
    	$('#userIdForUserAccess').html(userId);
    	if(userId=="1"){
    		alert("Sorry you dont have authority to change access of super admin");
    	}
    	else{
    	jQuery.ajax({
    		async : true,
    		type : "POST",
    		data : {"userId":userId},
    		url : "user/getUserByUserId",
    		timeout : 1000 * 60 * 5,
    		catche : false,
    		error : function() {
    			alert("error");
    		},
    		success : function(r) {
    			$('#userNameForUserAccess').html(r.title+" "+r.firstName+" "+r.middleName+" "+r.lastName);
    			$('#userTypeForUserAccess').html(r.userTypeMaster.userTypeName);
    			$('.userAccessMainDiv').show();
    			getUserAccessByUserId(userId);
    		}
    	});
    	}
    }
    
    function getUserAccessByUserId(userId){
    	if(userId=="1"){
    		alert("Sorry you dont have authority to change access of super admin");
    	}
    	else{
    	jQuery.ajax({
    		async : true,
    		type : "POST",
    		data : {"userId":userId},
    		url : "user/getUserAccessByUserId",
    		timeout : 1000 * 60 * 5,
    		catche : false,
    		error : function() {
    			alert("error");
    		},
    		success : function(r) {
    			$('#userAccessTable').find('input[type=checkbox]').each(function(){
					var id=($(this).attr('class')).split("_");
					$('.edit_'+id[1]).prop('checked', false);
					$('.view_'+id[1]).prop('checked', false);
					$('.delete_'+id[1]).prop('checked', false);
			    });
    			if(r.isRead!=null && r.isRead.trim()!="null"){
    				var checkId=[];
    				checkId=(r.isRead).split(",");
    				for(var i=0;i<checkId.length;i++){
    					$('.view_'+checkId[i].trim()).prop('checked', true);
    				}
    			}
    			if(r.isWrite!=null && r.isWrite.trim()!="null"){
    				var checkId=[];
    				checkId=(r.isWrite).split(",");
    				for(var i=0;i<checkId.length;i++){
    					$('.edit_'+checkId[i].trim()).prop('checked', true);
    				}
    			}
    			if(r.isDelete!=null && r.isDelete.trim()!="null"){
    				var checkId=[];
    				checkId=(r.isDelete).split(",");
    				for(var i=0;i<checkId.length;i++){
    					$('.delete_'+checkId[i].trim()).prop('checked', true);
    				}
    			}
    		}
    	});
    	}
    }
    
function listOfUserAccessMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "user/getListOfUserMaster",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr><td>"+index+"</td><td>"+r[i].title+""+r[i].firstName+" "+r[i].middleName+" "+r[i].lastName+"</td><td>"+r[i].userTypeMaster.userTypeName+"</td>"
				/*+"<td onclick='updateUser("+r[i].userId+")'><i class='fa fa-pencil'></i></td><td onclick='deleteUserMaster("+r[i].userId+")'><i class='fa fa-times fa fa-white'></i></td>"*/
				+"<td onclick='updateUserAccess("+r[i].userId+")'><i class='fa fa-pencil'></i></td></tr>"
				index++;
			}
			//<div class='setting-box clearfix'><span class='setting-title pull-left'> Fixed header</span><span class='setting-switch pull-right'><input type='checkbox' class='js-switch' id='fixed-header' /></span></div>
			$('#userAccessMasterTableBody').html(divContent);
		}
	});
}

function assignAccess(){
	var userId=$('#userIdForUserAccess').html();
	var userAccessView=[];
	var userAccessEdit=[];
	var userAccessDelete=[];
	$.each($(".userAccessView:checked"), function(){
    	userAccessView.push($(this).attr('id'));
    });
	$.each($(".userAccessEdit:checked"), function(){
		userAccessEdit.push($(this).attr('id'));
    });
	$.each($(".userAccessDelete:checked"), function(){
		userAccessDelete.push($(this).attr('id'));
    });
	//alert("userAccessEdit:"+userAccessEdit);
	//alert($('#tree_2').jstree(true).get_selected()); 
	if(userId=="1"){
		alert("Sorry you dont have authority to change access of super admin");
	}
	else{
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {"userId":userId,
				"userAccessView":userAccessView,	
				"userAccessEdit":userAccessEdit,
				"userAccessDelete":userAccessDelete
				},
		url : "user/saveUserAccess",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			alert("Access Assigned Successfully");
		}
	});
	}
}

function showProfileSubMenu(){
	$('#profileSubMenu').toggle('slow');
}

function showInteractionSubMenu(){
	$('#interactionSubMenu').toggle('slow');
}

function showAdministratorSubMenu(){
	$('#administratorSubMenu').toggle('slow');
}

function showDataManagementSubMenu(){
	$('#dataManagementSubMenu').toggle('slow');
}

function showDashboardMasterSubMenu(){
	$('#dashboardMasterSubMenu').toggle('slow');
}

function showContentMasterSubMenu(){
	$('#contentMasterSubMenu').toggle('slow');
}

function showHealthReportMasterSubMenu(){
	$('#healthReportMasterSubMenu').toggle('slow');
}

/*function listOfMenuMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "user/getListOfMenuMaster",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var menu=[];
			[{"text" : "My Profile",
				"children" : [{
					"text" : "Demographics",
				}]
			}]
			for(var i=0;i<r.length;i++){
				//alert(r[i].menuName+"list"+r[i].subMenuMastersList.length);
				menu.push("text",r[i].menuName);
			}
			//alert(menu);
		}
	});
}*/