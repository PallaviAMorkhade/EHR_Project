function isAvailEmailId() {
	var userName = $('#userName').val();
	jQuery.ajax({
		type : "POST",
		data : {
			emailid : userName
		},
		url : "isAvailEmailId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if (r == true) {
				var userId = $('#userId').html();
				if (userId != null) {
					var currentUserName = $('#currentUserNameSpan').html();
					if (currentUserName == userName) {
						$('#userNameErrorSpan').hide();
						$('#userName').css('border-color', '');
						$('#saveUserMasterBtn').removeAttr("disabled");
						$('#userNameErrorSpan').addClass('errorUserName_1')
								.removeClass('errorUserName_2');
					} else {
						$('#userNameErrorSpan').show();
						$('#userName').css('border-color', 'red');
						$('#saveUserMasterBtn').attr("disabled", "disabled");
						$('#userNameErrorSpan').addClass('errorUserName_2')
								.removeClass('errorUserName_1');
					}
				} else {
					$('#userNameErrorSpan').show();
					$('#userName').css('border-color', 'red');
					$('#saveUserMasterBtn').attr("disabled", "disabled");
					$('#userNameErrorSpan').addClass('errorUserName_2')
							.removeClass('errorUserName_1');
				}
			} else {
				$('#userNameErrorSpan').hide();
				$('#userName').css('border-color', '');
				$('#saveUserMasterBtn').removeAttr("disabled");
				$('#userNameErrorSpan').addClass('errorUserName_1')
						.removeClass('errorUserName_2');
			}
		}
	});
}

$("#userTypeMaster").change(
		function() {
			var userTypeMasterName = $('#userTypeMaster option:selected')
					.text();
			var userTypeMasterId = $('#userTypeMaster').val();
			$('#userTypeSelect').html(
					'<option val=' + userTypeMasterId + '>'
							+ userTypeMasterName + '</otion>');
			if (userTypeMasterId == "") {
				$('#userMasterInfoDiv').hide();
			} else {
				$('#userMasterInfoDiv').show();
				$('#forDataEntryOperator').hide();
				$('#forHRUser').hide();
				
				if (userTypeMasterId == "4" || userTypeMasterId == "13") {
					//$('#forDataEntryOperator').show();
					$('#forHRUser').show();
				}
			}
		});

function listOfUserTypeMaster() {
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "user/getListOfUserTypeMaster",
		timeout : 1000 * 60 * 5,
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent = "<option value=''>Select User Type</option>";
			var searchContent = "<option value=''>Search By User Type</option>";
			for ( var i = 0; i < r.length; i++) {
				divContent = divContent + "<option value='" + r[i].userTypeId
						+ "'>" + r[i].userTypeName + "</option>";
				
				searchContent = searchContent + "<option value='" + r[i].userTypeId
				+ "'>" + r[i].userTypeName + "</option>";
			}
			$('#userTypeSelect').html(divContent);
			$('#userTypeMaster').html(divContent);
			
			$("#searchByUserType").html(searchContent);
		}
	});
}

function saveUserMaster() {
	var title = $('#userTypeSelectTitle').val();
	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();
	var userName = $('#userName').val();
	var password = $('#password').val();
	var userTypeSelect = $('#userTypeMaster').val();
	var error = $('#userNameErrorSpan').attr('class');
	var centers = $('#centerAccSelect').val();
	var testPackage=$('#forAsigningTestPackage').val();
	var testPackageId=$('#testPackageListAccMngt').val();
	var memberCode=$('#memberCode').val();	

	if(userTypeSelect == 13){
		if(memberCode=="" || memberCode==null){
			alert("Please Enter Member Code");
			return false;
		}
	}else{
		if(memberCode==""){
			memberCode="-";
		}
	}
	//alert(memberCode);
	if(centers==null){
		centers=["0"];
	}
	if(testPackageId==null){
		testPackageId=["0"];
	}
	if (title == null || title == "") {
		alert("Please select title");
	} else if (firstName == null || firstName == "") {
		alert("Please write first name");
	}
	/*
	 * else if(middleName==null || middleName==""){ alert("Please write middle
	 * name"); }
	 */
	else if (lastName == null || lastName == "") {
		alert("Please write last name");
	} else if (userName == null || userName == "") {
		alert("Please write user name");
	} else if (password == null || password == "") {
		alert("Please write password");
	} else if (password.length < 8) {
		alert("Paswword must be minimum of 8 letters");
	} else if (userTypeSelect == null || userTypeSelect == "") {
		alert("Please select user type");
	} else if (error.trim() == "errorUserName_2") {
		alert("Sorry user name already exists");
	} else {
		jQuery.ajax({
			async : true,
			type : "POST",
			url : "user/saveUserMaster",
			data : {
				"title" : title,
				"firstName" : firstName,
				"middleName" : middleName,
				"lastName" : lastName,
				"userName" : userName,
				"password" : password,
				"userTypeSelect" : userTypeSelect,
				"centers" : centers,
				"testPackage" : testPackage,
				"testPackageId" : testPackageId,
				"memberCode" : memberCode
			},
			timeout : 1000 * 60 * 5,
			catche : false,
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#userTypeSelectTitle').val('');
				$('#firstName').val('');
				$('#middleName').val('');
				$('#lastName').val('');
				$('#userName').val('');
				$('#password').val('');
				$('#userTypeMaster').val('');
				$('#userTypeSelect').val('');
				$('#userMasterInfoDiv').hide();
				$('#centerAccSelect').val("");
				$('#testPackageListAccMngt').val("");
				$('#memberCode').val("");
				listOfUserMaster();
				alert("User Saved Successfully");
			}
		});
	}
};

function listOfUserMaster() {
	jQuery
			.ajax({
				async : true,
				type : "POST",
				url : "user/getListOfUserMaster",
				data : {
					userTypeId : $("#searchByUserType").val()
				},
				timeout : 1000 * 60 * 5,
				catche : false,
				error : function() {
					alert("error");
				},
				success : function(r) {
					var divContent = "";
					var index = 1;
					for ( var i = 0; i < r.length; i++) {
						divContent = divContent
								+ "<tr><td>"
								+ index
								+ "</td><td>"
								+ r[i].title
								+ ""
								+ r[i].firstName
								+ " "
								+ r[i].middleName
								+ " "
								+ r[i].lastName
								+ "</td><td>"
								+ r[i].userTypeMaster.userTypeName
								+ "</td><td onclick='updateUser("
								+ r[i].userId
								+ ")' class='editUserAccess'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteUserMaster("
								+ r[i].userId
								+ ")'><i class='fa fa-times fa fa-white'></i></td>";

						/*
						 * if(r[i].loginStatus==1){ divContent=divContent+"<td><input
						 * type='checkbox' class='loginStatus'
						 * id='loginStatus_"+r[i].userId+"' checked></td></tr>"; }
						 * else{ divContent=divContent+"<td><input
						 * type='checkbox' class='loginStatus'
						 * id='loginStatus_"+r[i].userId+"'></td></tr>"; }
						 */
						index++;
					}
					// <div class='setting-box clearfix'><span
					// class='setting-title pull-left'> Fixed header</span><span
					// class='setting-switch pull-right'><input type='checkbox'
					// class='js-switch' id='fixed-header' /></span></div>
					$('#userMasterTableBody').html(divContent);
					getUserAccess();
				}
			});
}

function deleteUserMaster(userId) {
	var r = confirm("Are you sure you want to delete user?");
	if (r == true) {
		jQuery.ajax({
			type : "POST",
			data : {
				"userId" : userId
			},
			url : "user/deleteUserMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				if (r == "true") {
					$('#userId').html("");
					$('#userTypeSelectTitle').val('');
					$('#firstName').val('');
					$('#middleName').val('');
					$('#lastName').val('');
					$('#userName').val('');
					$('#password').val('');
					$('#userTypeMaster').val('');
					$('#userTypeSelect').attr("disabled", "disabled");
					$('#userTypeSelect').val('');
					$('#saveUserMasterBtnDiv').show();
					$('#updateUserMasterBtnDiv').hide();
					$('#userMasterInfoDiv').hide();
					$('#centerAccSelect').val("");
					$('#testPackageListAccMngt').val("");
					listOfUserMaster();
					alert("User Deleted Successfully");
				} else {
					alert("Sorry you don't have access to delete report");
				}
			}
		});
	}
}

function updateUser(userId) {
	$('#userId').html(userId);
	$('#saveUserMasterBtnDiv').hide();
	$('#updateUserMasterBtnDiv').show();
	jQuery.ajax({
		type : "POST",
		data : {
			"userId" : userId
		},
		url : "user/getUserMasterByUserId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if (r.addBy == "false") {
				alert("Sorry you don't have access to edit user");
			} else {
				$('#userMasterInfoDiv').show();
				$('#userTypeSelectTitle').val(r.title);
				$('#firstName').val(r.firstName);
				$('#middleName').val(r.middleName);
				$('#lastName').val(r.lastName);
				$('#userName').val(r.userName);
				$('#password').val(r.newPassword);
				$('#userTypeMaster').val(r.userTypeMaster.userTypeId);
				$('#userTypeSelect').val(r.userTypeMaster.userTypeId);
				// $('#userTypeSelect').removeAttr("disabled");
				var center_id=r.centers.split(",");
				$('#centerAccSelect').val(center_id);
				$('#currentUserNameSpan').html(r.userName);
				$('#memberCode').val(r.memberCode);				
				
				$('#forDataEntryOperator').hide();
				$('#forHRUser').hide();
				if (r.userTypeMaster.userTypeId == "4" || r.userTypeMaster.userTypeId == "13") {
					//$('#forDataEntryOperator').show();
					$('#forHRUser').show();
				}
				
				if(r.tests != null){
					var test_id=r.tests.split(",");
					$('#testPackageListAccMngt').val(test_id);
				}
				else if(r.packages != null){
					var package_id=r.packages.split(",");
					$('#testPackageListAccMngt').val(package_id);
				}
			}
		}
	});
}

function updateUserMaster() {
	var userId = $('#userId').html();
	var title = $('#userTypeSelectTitle').val();
	var firstName = $('#firstName').val();
	var middleName = $('#middleName').val();
	var lastName = $('#lastName').val();
	var userName = $('#userName').val();
	var password = $('#password').val();
	var userTypeSelect = $('#userTypeMaster').val();
	var error = $('#userNameErrorSpan').attr('class');
	var centers = $('#centerAccSelect').val();
	var testPackage=$('#forAsigningTestPackage').val();
	var testPackageId=$('#testPackageListAccMngt').val();
	var memberCode=$('#memberCode').val();
	
	if(userTypeSelect == 13){
		if(memberCode=="" || memberCode==null){
			alert("Please Enter Member Code");
			return false;
		}
	}else{
		if(memberCode==""){
			memberCode="-";
		}
	}
	
	if(centers==null){
		centers=["0"];
	}
	if(testPackageId==null){
		testPackageId=["0"];
	}
	if (title == null || title == "") {
		alert("Please select title");
	} else if (firstName == null || firstName == "") {
		alert("Please write first name");
	}
	/*
	 * else if(middleName==null || middleName==""){ alert("Please write middle
	 * name"); }
	 */
	else if (lastName == null || lastName == "") {
		alert("Please write last name");
	} else if (userName == null || userName == "") {
		alert("Please write user name");
	} else if (password == null || password == "") {
		alert("Please write password");
	} else if (password.length < 8) {
		alert("Paswword must be minimum of 8 letters");
	} else if (userTypeSelect == null || userTypeSelect == "") {
		alert("Please select user type");
	} else if (error.trim() == "errorUserName_2") {
		alert("Sorry user name already exists");
	} else {
		jQuery.ajax({
			async : true,
			type : "POST",
			url : "user/updateUserMaster",
			data : {
				"title" : title,
				"firstName" : firstName,
				"middleName" : middleName,
				"lastName" : lastName,
				"userName" : userName,
				"password" : password,
				"userTypeSelect" : userTypeSelect,
				"userId" : userId,
				"centers" : centers,
				"testPackage" : testPackage,
				"testPackageId" : testPackageId,
				"memberCode" : memberCode
			},
			timeout : 1000 * 60 * 5,
			catche : false,
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#userId').html("");
				$('#userTypeSelectTitle').val('');
				$('#firstName').val('');
				$('#middleName').val('');
				$('#lastName').val('');
				$('#userName').val('');
				$('#password').val('');
				$('#userTypeMaster').val('');
				$('#userTypeSelect').attr("disabled", "disabled");
				$('#userTypeSelect').val('');
				$('#saveUserMasterBtnDiv').show();
				$('#updateUserMasterBtnDiv').hide();
				$('#userMasterInfoDiv').hide();
				$('#centerAccSelect').val("");
				$('#testPackageListAccMngt').val("");
				$('#memberCode').val("");				
				listOfUserMaster();
				alert("User Updated Successfully");
			}
		});
	}
};

$('#cancelUserMasterBtn').click(function() {
	$('#userId').html("");
	$('#userTypeSelectTitle').val('');
	$('#firstName').val('');
	$('#middleName').val('');
	$('#lastName').val('');
	$('#userName').val('');
	$('#password').val('');
	$('#userTypeMaster').val('');
	$('#userTypeSelect').attr("disabled", "disabled");
	$('#userTypeSelect').val('');
	$('#saveUserMasterBtnDiv').show();
	$('#updateUserMasterBtnDiv').hide();
	$('#userMasterInfoDiv').hide();
	$('#currentUserNameSpan').html("");
	$('#centerAccSelect').val("");
	$('#memberCode').val("");
});

$(document).on('click', '.loginStatus', function() {
	var id = $(this).attr('id');
	var userId = id.split("_");
	var loginStatus = "";
	if ($(this).prop('checked') == true) {
		loginStatus = 1;
	} else {
		loginStatus = 0;
	}
	jQuery.ajax({
		type : "POST",
		data : {
			"userId" : userId[1],
			"loginStatus" : loginStatus
		},
		url : "user/setLoginStatusUserMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			alert("Login Status updated");
		}
	});
});

function listOfCenterAccount() {
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfCentres",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent = "";
			//divContent=divContent+"<option value=''>Select</option>";
			for ( var i = 0; i < r.length; i++) {
				divContent = divContent + "<option value='" + r[i].centreId
						+ "'>" + r[i].centreName + "</option>";
			}
			$('#centerAccSelect').html(divContent);
		}
	});
}

function testPackageAccountManagement(){
	var current=$('#forAsigningTestPackage').val();
	//alert("curr"+current);
	if(current=="Test"){
		listOfTestMasterAccount();
	}
	else{
		listOfPackageMasterAccount();
	}
}

function listOfTestMasterAccount(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOftestMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			//divContent=divContent+"<option value=''>Select Test</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].testId+"'>"+r[i].testDescription+"</option>";
			}
			$('#testPackageListAccMngt').html(divContent);
		}
	});
}

function listOfPackageMasterAccount(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfPackageMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			//divContent=divContent+"<option value=''>Select Package</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].packageId+"'>"+r[i].packageDescription+"</option>";
			}
			$('#testPackageListAccMngt').html(divContent);
		}
	});
}

function searchByUserName(){
	var searchKeyword = $("#searchByUserName").val();
	$('#searchByUserId').val('');//clear Search By UserId field
	$('#searchByUserType').val('');//clear Search By UserTyped field
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
			var divContent = "";
			var index = 1;
			for ( var i = 0; i < r.length; i++) {
				divContent = divContent
						+ "<tr><td>"
						+ index
						+ "</td><td>"
						+ r[i].title
						+ ""
						+ r[i].firstName
						+ " "
						+ r[i].middleName
						+ " "
						+ r[i].lastName
						+ "</td><td>"
						+ r[i].userTypeMaster.userTypeName
						+ "</td><td onclick='updateUser("
						+ r[i].userId
						+ ")' class='editUserAccess'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteUserMaster("
						+ r[i].userId
						+ ")'><i class='fa fa-times fa fa-white'></i></td>";
				index++;
			}
			$('#userMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

/*$("#searchByUserName").autocomplete({
	 source : function(request, response) {
		 	var searchKeyword = $("#searchByUserName").val();
			$('#searchByUserId').val('');//clear Search By UserId field
			$('#searchByUserType').val('');//clear Search By UserTyped field
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
	 		getUserByUserId(id[1]);
	 	}
});
$("#searchByUserName").autocomplete("widget").addClass("fixedHeight");*/

$('#searchByUserId').keypress(function(event) {
	var userId = $('#searchByUserId').val();
	$('#searchByUserType').val('');//clear Search By Type field
	$('#searchByUserName').val('');//clear Search By Name field

	var keycode = (event.keyCode ? event.keyCode : event.which);
	if (keycode == '13') {
		if (userId == "") {
			alert("Please Enter User Id");
		} else {
			getUserByUserId(userId);
		}
	}
});

function getUserByUserId(userId) {
	jQuery.ajax({
		type : "POST",
		data : {
			"userId" : userId
		},
		url : "user/getUserMasterByUserId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var index=1;
			var divContent = "<tr><td>"
			+ index
			+ "</td><td>"
			+ r.title
			+ ""
			+ r.firstName
			+ " "
			+ r.middleName
			+ " "
			+ r.lastName
			+ "</td><td>"
			+ r.userTypeMaster.userTypeName
			+ "</td><td style='cursor: pointer;' onclick='updateUser("
			+ r.userId
			+ ")' class='editUserAccess'><i class='fa fa-pencil'></i></td><td style='cursor: pointer;' class='deleteUserAccess' onclick='deleteUserMaster("
			+ r.userId
			+ ")'><i class='fa fa-times fa fa-white'></i></td>";
			$('#userMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}
