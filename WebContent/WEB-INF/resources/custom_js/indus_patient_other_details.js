function clientUploadReport(){
	$('#uploadReportModal').modal('show');
	$('#k').html("0");
	$('.editReportPopUp').hide();
	$('.uploadReport').show();
	$('#displayReportDiv').removeClass('col-md-12').addClass('col-md-9');
	$('#reportDiv').html("");
	$('#packageNameUploadReportPopUp').html($('.packageNameSpan').html());
	$('#reportLength').html("");
	$('#savePatientReportBtn').show();
	
	$('.clientUploadReportHide').hide();
	$('.clientUploadReportShow').show();
	$('#savePatientReportBtn').hide();
	$('#packageDetails').hide();
	$('#saveSelfClientReportBtn').show();
	
	$('#addNewVisitDiv').hide();
	$('#displayReportTableBody').html("");
};

function saveSelfClientReport(){
	$('#uploadReportModal').modal('hide');
	var fileData=[];
	var length=$('.template-upload').length+1;//$('#k').html();
   // for(var i=1;i<=length;i++){
	$('.name').map(function() {
		var i = $(this).attr("class").split("_")[1];
        	var fileName=$('.fileName_'+i).html();
        	var comment=$('.comment_'+i).val();
        	/*var clientReportLineId=0;
        	if($('#clientReportLineIdEdit').html()!=null && $('#clientReportLineIdEdit').html()!=""){
        		clientReportLineId=$('#clientReportLineIdEdit').html();
        	}*/
        	if(fileName!=undefined){
        		fileData.push(fileName+"#"+$('#clientMasterId').html()+"#"+comment);
        	}
	});
    //alert("fileData is:"+fileData);
    if($('.template-upload').length>0){
    $(".uploadSingleReport").trigger("click");
    jQuery.ajax({
		async : true,
		type : "POST",
		data :{"fileData":fileData},
		url : "client/saveSelfClientReport",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				getClientSelfUploadReport($('#clientMasterId').html());
				$('#clientReportLineIdEdit').html("");
				alert("File Uploaded Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to upload report");
    			}
		}
	});
    }
    else{
    	alert("Please select atleast one file");
    }
}

function getClientSelfUploadReport(clientId) {
	var inputs = [];
	inputs.push('clientId=' + clientId);
	var str = inputs.join('&');
	jQuery
			.ajax({
				async : true,
				type : "POST",
				data : str + "&reqType=AJAX",
				url : "client/getClientSelfUploadReport",
				catche : false,
				error : function() {
					alert("error");
				},
				success : function(r) {
					var divContent="";
					if(r.length>0)
					{	
						var index=1;
						var addedOn=[];
						for(var i=0;i<r.length;i++)
						{
							var filePath = r[i].filePath;
							var avoid = filePath.split("_")[0];
							var avoid1 = filePath.split("_")[1];
							var avoided = filePath.replace(avoid,'');
							var avoided1 = avoided.replace(avoid1,'');
							var fileName = avoided1.slice(2);
							addedOn=(r[i].addedOn).split(" ");
									divContent=divContent+"<tr><td class='center'>"+index+"</td><td>"+fileName+"</td><td>"+r[i].comment+"</td><td class='reportUploader'>"+r[i].addedBy+"</td>"
									+"<td>"+addedOn[0]+"</td><td style='width: 90px;'><div>"
									+"<a value='"+r[i].clientSelfReportId+"' id='"+r[i].filePath+"' target='_blank' class='viewReport btn btn-transparent btn-xs' tooltip-placement='top' tooltip='Edit'><i class='fa fa-binoculars'></i></a>"
									/*+"<a onclick='editReport("+r[i].clientReportLineId+");' class='btn btn-transparent btn-xs editReport' tooltip-placement='top' tooltip='Edit' data-target='#uploadReportModal' data-toggle='modal'><i class='fa fa-pencil'></i></a>"*/
									+"<a onclick='deleteSelfClientReport("+r[i].clientSelfReportId+","+clientId+");' class='btn btn-transparent btn-xs tooltips' tooltip-placement='top' tooltip='Remove'><i class='fa fa-times fa fa-white'></i></a>"
									+"</div></td></tr>";
							index++;
						}
					}
					else
					{
						divContent=divContent+"<b>No Reports Available</b>";
					}	
					$("#selfUploadReportData").html(divContent);
					if($('#clientLoginUserType').html()==1){
						$('.reportUploader').hide();
					}
					else{
						$('.reportUploader').show();
					}
					getUserAccess();
				}
			});
	return true;
}

$('#selfUploadReportData').on('click', '.viewReport', function () {
	$('#viewReportModal').modal('show');
	$('#displayViewReport').attr('data',"readPdf?pdfFileName="+$(this).attr('id'));
	//var testName=$('#testName_'+$(this).attr('value')).text();
	$('#testNameViewReport').html("");
});

function deleteSelfClientReport(clientSelfReportId,clientId){
	var r = confirm("Are you sure you want to delete report?");
    if (r == true) {
    	jQuery.ajax({
    		async : true,
    		type : "POST",
    		data :{"clientSelfReportId":clientSelfReportId},
    		url : "client/deleteSelfClientReport",
    		catche : false,
    		error : function() {
    			alert("error");
    		},
    		success : function(r) {
    			if(r=="true"){
    				getClientSelfUploadReport(clientId)
    				alert("Report Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}
    		}
    	});
    } 
}

function getPatientTest(clientId){
	
}

function getListOfCentres(){

}

function getPatientCenter(clientId){

}




/*$(document).on('click','.removeSingleReport',function(){
	$('#reportLength').html($('.template-upload').length);
});

function removePatientReport(){
		$(".removeSingleReport").trigger("click");
		$('#reportLength').html("0");
};

function removeReport(){
	$('#reportDiv').html("");
}*/




