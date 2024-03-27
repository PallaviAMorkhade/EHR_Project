function listOfCentresRegistration(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfCentres",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select Center</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].centreId+"'>"+r[i].centreName+"</option>";
			}
			$('#centerList').html(divContent);
		}
	});
}

function listOfPackageRegistration(){
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
			divContent=divContent+"<option value=''>Select Package</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].packageId+"'>"+r[i].packageDescription+"</option>";
			}
			$('#packageList').html(divContent);
		}
	});
}

function removeBorder(current){
	if($(current).val()!="" && $(current).val()!=null){
		$(current).css("border-color","");
	}
}

$("#patientRegisterForm").submit(function(e)
{
	if($('#gender').val()==""){
		$('#gender').css("border-color","red");
		alert("Please Select Gender");
		return false;
	}
	else if($('#firstName').val()==""){
		$('#firstName').css("border-color","red");
		alert("Please Enter First Name");
		return false;
	}
	else if($('#lastName').val()==""){
		$('#lastName').css("border-color","red");
		alert("Please Enter Last Name");
		return false;
	}
	else if(($('#mobNo').val()).length>0 && ($('#mobNo').val()).length<12){
		$('#mobNo').css("border-color","red");
		alert("Mobile Number must be greater than 12 digits");
		return false;
	}
	else if($('#checkUpDate').val()==""){
		$('#checkUpDate').css("border-color","red");
		alert("Please Select Check Up Date");
		return false;
	}
	else if($('#appNo').val()==""){
		$('#appNo').css("border-color","red");
		alert("Please Enter Appointment Number");
		return false;
	}
	else if($('#apYear').val()==""){
		$('#apYear').css("border-color","red");
		alert("Please Enter Appointment Year");
		return false;
	}
	else if($('#centerList').val()==""){
		$('#centerList').css("border-color","red");
		alert("Please Select Center");
		return false;
	}
	else if($('#packageList').val()==""){
		$('#packageList').css("border-color","red");
		alert("Please Select Package");
		return false;
	}
	e.preventDefault();
	$("#pleaseWait").show();
	var inputs = [];
	inputs.push('firstName=' + $('#firstName').val());
	inputs.push('middleName=' + $('#middleName').val());
	inputs.push('lastName=' + $('#lastName').val());
	inputs.push('addressLine1=' + $('#addressLine1').val());
	inputs.push('addressLine2=' + $('#addressLine2').val());
	inputs.push('addressLine3=' + $('#addressLine3').val());
	inputs.push('hospitalRegNo=' + $('#hospitalRegNo').val());
	inputs.push('appNo=' + $('#appNo').val());
	inputs.push('apYear=' + $('#apYear').val());
	inputs.push('mobNo=' + $('#mobNo').val());
	inputs.push('emailId=' + $('#emailId').val());
	inputs.push('clientGender=' + $('#gender').val());
	inputs.push('packageId=' + $('#packageList').val());
	inputs.push('centerId=' + $('#centerList').val());
	inputs.push('checkUpDate=' + $('#checkUpDate').val());
	inputs.push('birthDate=' + $('#dateOfBirth').val());
	inputs.push('mbRelation=' + $('#mbRelation').val());
	inputs.push('memberId=' + $('#memberId').val());
	inputs.push('linkAppNo=' + $('#linkAppNo').val());
	inputs.push('userType=' + 'C');
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str,
		url : "client/clientRegister",
		catche : false,
		error : function() {
			//alert("error");
			$("#pleaseWait").hide();
		},
		success : function(r) {
			
			$("#pleaseWait").hide();
			var a=r.MB_EHR_NO;
			var dup=a.slice(0, 4);
			if(dup=="dup_"){
				alert("Client Already Register");
			}else{
				clearPatientRegistrationFrom();
				alert("Client Register Successfully");
			}
		}
	});
});

function clearPatientRegistrationFrom(){
	$('#firstName').val("");
	$('#middleName').val("");
	$('#lastName').val("");
	$('#addressLine1').val("");
	$('#addressLine2').val("");
	$('#addressLine3').val("");
	$('#hospitalRegNo').val("");
	$('#appNo').val("");
	$('#apYear').val("");
	$('#mobNo').val("");
	$('#emailId').val("");
	$('#gender').val("");
	$('#packageList').val("");
	$('#centerList').val("");
	$('#checkUpDate').val("");
	$('#dateOfBirth').val("");
	$('#mbRelation').val("");
	$('#memberId').val("");
	$('#linkAppNo').val("");
}