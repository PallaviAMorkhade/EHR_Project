function saveFrequencyMaster(){
	var frequencyMasterId=$('#frequencyMasterId').html();
	var frequency=$('#frequency').val();
	if(frequency==null || frequency==""){
		alert("Please Write Frequency");
	}
	else{
		var frequencyMaster = {
			"frequencyId":frequencyMasterId,
			"frequency":frequency
		};
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			//dataType : 'json',
			url : "frequency/saveFrequency",
			data : JSON.stringify(frequencyMaster),
			/*error : function(response) {
				alert("error"+response);
			},*/
			success : function(response) {
				if(frequencyMasterId==""){
					alert("Frequency Saved Successfully");
				}else{
					alert("Frequency Updated Successfully");
				}
				listOfFrequencyMaster();
				clearFrequencyMaster();
			}
		});
	}
};

function clearFrequencyMaster(){
	$('#frequencyMasterId').html("");
	$('#frequency').val("");
	$('#saveFrequencyBtnDiv').show();
	$('#updateFrequencyBtnDiv').hide();
}

function listOfFrequencyMaster(){
	jQuery.ajax({
		async : true,
		type : "GET",
		url : "frequency/listOfFrequencyMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='frequencyMasterRow' id='frequency_master_"+r[i].frequencyId+"'><td class='center'>"+index+"</td><td id='frequency"+r[i].frequencyId+"'>"+r[i].frequency+"</td><td class='editUserAccess' onclick='getFrequencyMaster("+r[i].frequencyId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteFrequencyMaster("+r[i].frequencyId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#frequencyId').val(index);
			$('#frequencyMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteFrequencyMaster(frequencyMasterId){
	var r = confirm("Are you sure you want to delete Frequency?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {"frequencyMasterId":frequencyMasterId},
		url : "frequency/deleteFrequency",
		error : function() {
			alert("error");
		},
		success : function(r) {
			//if(r=="true"){
				listOfFrequencyMaster();
				alert("Frequency Deleted Successfully");
    			/*}
    			else{
    				alert("Sorry you don't have access to delete frequency");
    			}*/
		}
	});
    }
}

function getFrequencyMaster(frequencyMasterId,index){
	jQuery.ajax({
		type : "GET",
		data : {"frequencyMasterId":frequencyMasterId},
		url : "frequency/getFrequencyByFrequencyId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			/*if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit frequency");
    		}
    		else{*/
			$('#saveFrequencyBtnDiv').hide();
			$('#updateFrequencyBtnDiv').show();
			$('#frequency').val(r.frequency);
			$('#frequencyMasterId').html(r.frequencyId);
			$('#frequencyId').val(index);
    		//}
		}
	});
}
