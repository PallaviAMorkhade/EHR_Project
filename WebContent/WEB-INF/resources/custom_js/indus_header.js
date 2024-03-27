function changePassword(){
	var currentPassword=$('#currentPassword').val();
	var newPassword=$('#newPassword').val();
	var confirmPassword=$('#confirmPassword').val();
	var inputs = [];
	inputs.push('currentPassword=' + currentPassword);
	inputs.push('newPassword=' + newPassword);
	inputs.push('confirmPassword=' + confirmPassword);
	var str = inputs.join('&');
	if(currentPassword==""){
		alertify.error("please enter current password");
		return;
	}
	else if(newPassword=="" && confirmPassword==""){
		alertify.error("please enter current and new password");
		return;	
	}
	else if(newPassword!="" && confirmPassword!="" && newPassword.length<8 && currentPassword.length<8){
		alertify.error("Password must be minimum of 8 letters");
		return;	
	}
	else if(!(newPassword==confirmPassword)){
		alertify.error("Password doesnt match");
		return;
	}
	else if(currentPassword!="" && newPassword!="" && confirmPassword!="" && newPassword==confirmPassword){
	jQuery.ajax({
				type : "GET",
				data : str + "&reqType=AJAX",
				url : "client/changePassword",
				error : function() {
					alert("error");
				},
				success : function(r) {
					if(r==true){
						$('#changePasswordModal').modal('hide');
						alert("Password Changed Successfully..!");
						$('#currentPassword').val("");
						$('#newPassword').val("");
						$('#confirmPassword').val("");
					}
				}
			});
		}
	
	}

