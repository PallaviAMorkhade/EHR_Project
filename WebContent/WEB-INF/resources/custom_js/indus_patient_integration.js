$('#originalClientId').keypress(function(event) {
	var clientId = $('#originalClientId').val();
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if (keycode == '13') {
		if (clientId == "") {
			alert("Please Enter client Id");
		} else {
			getClientByClientId(clientId);
		}
	}
});

function getClientByClientId(clientId) {
	jQuery.ajax({
		type : "POST",
		data : {
			clientId : clientId
		},
		url : "client/getClientMasterRecordByName",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if (r.userId != null) {
				var clientFullName = r.firstName + " " + r.middleName + " "
						+ r.lastName;
				if (r.middleName == "") {
					clientFullName = r.firstName + " " + r.lastName;
				}
				$("#originalClientName").val(clientFullName);
			} else {
				$("#originalClientName").val("");
				alert("Sorry client not found");
			}
		}
	});
}

$('#replaceableClientId')
		.keypress(
				function(event) {
					var originalClientId = $('#originalClientId').val();
					var clientId = $('#replaceableClientId').val();
					var keycode = (event.keyCode ? event.keyCode : event.which);
					if (keycode == '13') {
						if (clientId == "") {
							alert("Please Enter client Id");
						} else {
							if (originalClientId == clientId) {
								alert("Original ClientId and Replaceable ClientId can't be same.");
							} else {
								getReplaceableClientByClientId(clientId);
							}
						}
					}
				});

function getReplaceableClientByClientId(clientId) {
	jQuery
			.ajax({
				type : "POST",
				data : {
					clientId : clientId
				},
				url : "client/getClientMasterRecordByName",
				error : function() {
					alert("error");
				},
				success : function(r) {
					if (r.userId != null && r.isVerified=="N") {
						var clientFullName = r.firstName + " " + r.middleName
								+ " " + r.lastName;
						if (r.middleName == "") {
							clientFullName = r.firstName + " " + r.lastName;
						}
						//var index = $("#replaceableClientData tr").length + 1;
						var client = "<tr id='client_"
								+ r.clientId
								+ "' class='clients'>" 
								//+ "<td class='center'>"	+ index	+ "</td>" 
								+ "<td>"
								+ r.clientId
								+ "</td><td>"
								+ clientFullName
								+ "</td><td><button onclick='removeClientRow(this)'><i class='fa fa-times fa fa-white'></i></button></td></tr>";
						$("#replaceableClientData").append(client);
						$("#replaceableClientId").val("");
					} else if(r.isVerified=="Y"){
						alert("Sorry Verified Client Can't Be Merged");
					}else {
						alert("Sorry client not found");
					}
				}
			});
}

function removeClientRow(current) {
	var id = $(current).parents()[1];// For getting second parent
	var r = confirm("Are You Sure Want To Remove Client.");
	if (r == true) {
		$(id).remove();
	}
}

function mergeClient() {
	if ($("#replaceableClientData .clients").length > 0) {
		var originalClientId = $('#originalClientId').val();
		var replaceableClientIdArray = [];
		$("#replaceableClientData .clients").map(function() {
			var replaceableClientId = (this.id).split("_")[1];
			if (originalClientId != replaceableClientId) {
				replaceableClientIdArray.push(replaceableClientId);
			}
		});
		$("#pleaseWait").show();
		jQuery.ajax({
			type : "POST",
			data : {
				"originalClientId" : originalClientId,
				"replaceableClientIdArray" : replaceableClientIdArray
			},
			url : "client/mergeClient",
			error : function() {
				alert("error");
				$("#pleaseWait").hide();
			},
			success : function(r) {
				$("#pleaseWait").hide();
				$("#originalClientId").val("");
				$("#originalClientName").val("");
				$("#replaceableClientId").val("");
				$("#replaceableClientData").html("");
				alert("Client Merge Successfully.");
			}
		});
	} else {
		alert("Please select atleast one client");
	}
}