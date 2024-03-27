function listOfTestMaster(){
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
			divContent=divContent+"<option value=''>Select Test</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].testId+"'>"+r[i].testDescription+"</option>";
			}
			$('#testMasterSelect').html(divContent);
		}
	});
}

function listOfParameterMaster(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getParameterList",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='parameterMasterRow' id='parameter_master_"+r[i].parameterId+"'><td class='center'>"+index+"</td><td id='parameter_"+r[i].parameterId+"'>"+r[i].parameterName+"</td><td>"+r[i].testMaster.testDescription+"</td><td class='editUserAccess' onclick='getParameterMaster("+r[i].parameterId+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteParameterMaster("+r[i].parameterId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#parameterMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

$('#cancelParameterMasterBtn').click(function(){
	$('#testMasterSelect').val("");
	$('#parameterDiv').hide();
	$('#newParameters').html("");
});

//$('#saveParameterMasterBtn').click(function(){
function saveParameterMaster(){
	if($("#displayParameterDiv .error").length==0){
	var testId=$('#testMasterSelect').val();
	var parameterName=[];
	var length=$('.parameter').length+1;
	   for(var i=0;i<length;i++){
		   if($('#parameter_'+i).val()!=null && $('#parameter_'+i).val()!=""){
			   var parameterMasterId = undefined;
			   var normalValue = $("input[name='values_"+i+"']:checked").attr('class').split("_")[1];
			   var criticalLowValue = $("#criticalLowValue_"+i).val();
			   var criticalHighValue = $("#criticalHighValue_"+i).val();
			   if(criticalLowValue==""){
				   criticalLowValue = null;
			   }
			   if(criticalHighValue==""){
				   criticalHighValue = null;
			   }
			   parameterName.push($('#parameter_'+i).val()+"_"+parameterMasterId+"_"+normalValue+"_"+criticalLowValue+"_"+criticalHighValue);
		   }
	   }
	  if(parameterName!=null && parameterName!=""){
	   jQuery.ajax({
			type : "POST",
			data : {
					"testId":testId,
					"parameterName":parameterName
					},
			url : "client/saveParameterMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#testMasterSelect').val("");
				$('#parameterDiv').hide();
				listOfParameterMaster();
				alert("Parameter Saved Successfully");
			}
		});
	  }
	  else{
		  alert("Please write parameter name");
	  }
}else{
	alert("Please fill value properly");
}
};

//$('#updateParameterMasterBtn').click(function(){
function updateParameterMaster(){
	var isError = false;
	$("#parameterDiv .error").map(function(){
	  var id = $(this).parents()[1];//For getting second parent
	  var display = $(id).css('display');
	  if(display=="block"){
		  isError = true;
	  }
	});
	
	if(isError==false){
	var testId=$('#testMasterSelect').val();
	var parameterName=[];
	var length=$('.parameter').length+10;//hardcoded temporary
	   for(var i=0;i<length;i++){
		   if($('#parameter_'+i).val()!=null && $('#parameter_'+i).val()!=""){
			   var id=$('#parameter_'+i).attr("class");
			   var parameterMasterId=id.split("_");
			   var normalValue = $("input[name='values_"+i+"']:checked").attr('class').split("_")[1];
			   var criticalLowValue = $("#criticalLowValue_"+i).val();
			   var criticalHighValue = $("#criticalHighValue_"+i).val();
			   if(criticalLowValue==""){
				   criticalLowValue = null;
			   }
			   if(criticalHighValue==""){
				   criticalHighValue = null;
			   }
			   parameterName.push($('#parameter_'+i).val()+"_"+parameterMasterId[1]+"_"+normalValue+"_"+criticalLowValue+"_"+criticalHighValue);
		   }
	   }
	   jQuery.ajax({
			type : "POST",
			data : {
					"testId":testId,
					"parameterName":parameterName
					},
			url : "client/updateParameterMaster",
			error : function() {
				alert("error");
			},
			success : function(r) {
				$('#testMasterSelect').val("");
				$('#parameterDiv').hide();
				listOfParameterMaster();
				alert("Parameter Updated Successfully");
			}
		});
	}else{
		alert("Please fill value properly");
	}
};

function getParameterMaster(parameterId){
	jQuery.ajax({
		type : "POST",
		data : {"parameterId":parameterId},
		url : "client/getParameterByParameterId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addBy=="false"){
				alert("Sorry you don't have access to edit parameter");
    		}
    		else{
			$('#testMasterSelect').val(r.testMaster.testId);
			$('#parameterDiv').show();
			$('#parameter_0').val(r.parameterName);
			$('#parameter_0').attr('class','');
			$('#parameter_0').addClass("form-control parameter_"+r.parameterId);
			$('#saveParameterBtnDiv').hide();
			$('#updateParameterBtnDiv').show();
			$('#newParameters').html("");
    		}
		}
	});
}

function deleteParameterMaster(parameterId){
	var r = confirm("Are you sure to delete Parameter?");
    if (r == true) {
	  jQuery.ajax({
		type : "POST",
		data : {"parameterId":parameterId},
		url : "client/deleteParameterMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r=="true"){
				listOfParameterMaster();
				alert("Parameter Deleted Successfully");
    			}
    			else{
    				alert("Sorry you don't have access to delete report");
    			}
		}
	});
    }
}

$("#testMasterSelect").change(function() {
		var selected=$('#testMasterSelect option:selected').text();
		var testId=$('#testMasterSelect').val();
		if(selected.trim()==="Select Test"){
			$('#parameterDiv').hide();
		}
		else{
			$('#parameterDiv').show();
			jQuery.ajax({
				type : "POST",
				data : {
							"testId":testId
					    },
				url : "client/getParameterByTestId",
				error : function() {
					alert("error");
				},
				success : function(r) {
					if(r.length>0){
					var parameter="";
					$('#parameter_0').val(r[0].parameterName);
					$('#parameter_0').attr('class','');
					$('#parameter_0').addClass("form-control parameter_"+r[0].parameterId);
					if(r[0].normalValue==1){
						$("#criticalLowValue_0").val(r[0].criticalLowValue);
						$("#criticalHighValue_0").val(r[0].criticalHighValue);
						$('.normalValue_1').prop('checked','checked');
						$("#criticalValueDiv_0").show();
					}else{
						$("#criticalLowValue_0").val("");
						$("#criticalHighValue_0").val("");
						$('.normalValue_2').prop('checked','checked');
						$("#criticalValueDiv_0").hide();
					}
					for(var i=1;i<r.length;i++){
						var normalValue = r[i].normalValue;
						var criticalLowValue = r[i].criticalLowValue;
						var criticalHighValue = r[i].criticalHighValue;
						if(criticalLowValue==null){
							   criticalLowValue = "";
						   }
						   if(criticalHighValue==null){
							   criticalHighValue = "";
						   }
						   parameter=parameter+'<div id="existingParameterDiv_'+r[i].parameterId+'"><label class="control-label"> Parameter <span'
							+' class="symbol required"></span></label> <input value="'+r[i].parameterName+'" type="text" placeholder="Parameter" style="margin-top: 5px;" class="parameter form-control parameter_'+r[i].parameterId+'" id="parameter_'+i+'" name="parameter"><i id="removeParameter_'+r[i].parameterId+'" class="removeParameter removeParameter_'+r[i].parameterId+' fa fa-minus-square" onclick="removeParameter('+r[i].parameterId+','+r[i].testMaster.testId+');"></i>'
						    +'<div class="row">'
							+'<div class="col-md-3 col-xs-4">'
							+'	<label>Normal Values</label>'
							+'</div>'
							+'<form id="valuesForm_'+i+'" class="valuesForm">'
							+'	<div class="col-md-3 col-xs-4">'
							+'		<input type="radio" class="normalValue_1" name="values_'+i+'"'
							+'			value="individual" checked=""> Individual<br>'
							+'	</div>';
							if(normalValue=="2"){
								parameter=parameter+'	<div class="col-md-3 col-xs-4">'
								+'		<input type="radio" class="normalValue_2" name="values_'+i+'"'
								+'			value="general" checked=""> General<br>'
								+'	</div>';
							}else{
								parameter=parameter+'	<div class="col-md-3 col-xs-4">'
								+'		<input type="radio" class="normalValue_2" name="values_'+i+'"'
								+'			value="general"> General<br>'
								+'	</div>';
							}
							parameter=parameter+'</form>'
							+'</div>'

							if(normalValue=="1"){
								parameter=parameter+'<div class="row" id="criticalValueDiv_'+i+'">'
							+'<div class="col-md-6">'
							+'	<label class="control-label"> Critical Low Value <span'
							+'		class="symbol required"></span>'
							+'	</label> <input type="text" id="criticalLowValue_'+i+'" value="'+criticalLowValue+'" '
							+'		onkeyup=\'numericOnly(this),lowHighValidation("low",this)\' class="form-control" placeholder="Critical Low Value">'
							+'</div>'

							+'<div class="col-md-6">'
							+'	<label class="control-label"> Critical High Value <span'
							+'		class="symbol required"></span>'
							+'	</label> <input type="text" id="criticalHighValue_'+i+'" value="'+criticalHighValue+'" '
							+'		onkeyup=\'numericOnly(this),lowHighValidation("high",this)\' class="form-control" placeholder="Critical High Value">'
							+'</div>'
							+'</div>';
							}else{
								parameter=parameter+'<div class="row" id="criticalValueDiv_'+i+'" style="display:none;">'
								+'<div class="col-md-6">'
								+'	<label class="control-label"> Critical Low Value <span'
								+'		class="symbol required"></span>'
								+'	</label> <input type="text" id="criticalLowValue_'+i+'" value="'+criticalLowValue+'" '
								+'		onkeyup=\'numericOnly(this),lowHighValidation("low",this)\' class="form-control" placeholder="Critical Low Value">'
								+'</div>'

								+'<div class="col-md-6">'
								+'	<label class="control-label"> Critical High Value <span'
								+'		class="symbol required"></span>'
								+'	</label> <input type="text" id="criticalHighValue_'+i+'" value="'+criticalHighValue+'" '
								+'		onkeyup=\'numericOnly(this),lowHighValidation("high",this)\' class="form-control" placeholder="Critical High Value">'
								+'</div>'
								+'</div>';
								}
							parameter=parameter+'<hr></div>';
					 }
					   $('#newParameters').html(parameter);
					   $('#saveParameterBtnDiv').hide();
					   $('#updateParameterBtnDiv').show();
					}
					else{
						$('#newParameters').html("");
						$('#parameter_0').attr("class","");
						$('#parameter_0').addClass("parameter form-control");
						$('#parameter_0').val("");
						$("#criticalLowValue_0").val("");
						$("#criticalHighValue_0").val("");
						$('#saveParameterBtnDiv').show();
						$('#updateParameterBtnDiv').hide();
					}
				}
			});
		}
});

$('#addParameterBtn')
		.on(
				'click',
				function() {
					var i = $('.parameter').length + 1;
					$('<div id="newParameterDiv_'+i+'"><label class="control-label"> Parameter <span'
							+' class="symbol required"></span></label><input type="text" placeholder="parameter" style="margin-top: 5px;" class="parameter form-control" id="parameter_'
									+ i
									+ '" name="parameter"><i id="removeParameter_'
									+ i
									+ '" class="removeParameter fa fa-minus-square" onclick="removeParameter('
									+ i + ',null);"></i>'
									
									+'<div class="row">'
									+'<div class="col-md-3 col-xs-4">'
									+'	<label>Normal Values</label>'
									+'</div>'
									+'<form id="valuesForm_'+i+'" class="valuesForm">'
									+'	<div class="col-md-3 col-xs-4">'
									+'		<input type="radio" class="normalValue_1" name="values_'+i+'"'
									+'			value="individual" checked=""> Individual<br>'
									+'	</div>'
									+'	<div class="col-md-3 col-xs-4">'
									+'		<input type="radio" class="normalValue_2" name="values_'+i+'"'
									+'			value="general"> General<br>'
									+'	</div>'
									+'</form>'
									+'</div>'

									+'<div class="row" id="criticalValueDiv_'+i+'">'
									+'<div class="col-md-6">'
									+'	<label class="control-label"> Critical Low Value <span'
									+'		class="symbol required"></span>'
									+'	</label> <input type="text" id="criticalLowValue_'+i+'"'
									+'		onkeyup=\'numericOnly(this),lowHighValidation("low",this)\' class="form-control" placeholder="Critical Low Value">'
									+'</div>'

									+'<div class="col-md-6">'
									+'	<label class="control-label"> Critical High Value <span'
									+'		class="symbol required"></span>'
									+'	</label> <input type="text" id="criticalHighValue_'+i+'"'
									+'		onkeyup=\'numericOnly(this),lowHighValidation("high",this)\' class="form-control" placeholder="Critical High Value">'
									+'</div>'
									+'</div><hr></div>').appendTo(
							newParameters);
				});

function removeParameter(parameterId,testId){
if(parameterId!=null && parameterId!="" && testId!=null && testId!=""){
	var r = confirm("Are you sure to delete Parameter?");
    if (r == true) {
	jQuery.ajax({
		type : "POST",
		data : {"parameterId":parameterId},
		url : "client/deleteParameterMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			listOfParameterMaster();
			$('#existingParameterDiv_'+parameterId).remove();
			/*$('.parameter_'+parameterId).remove();
			$('.removeParameter_'+parameterId).remove();*/
			alert("Parameter Deleted Successfully");
		}
	});
    }
	}
	else{
			$('#newParameterDiv_'+parameterId).remove();
		   //$('#parameter_'+parameterId).remove();
		   //$('#removeParameter_'+parameterId).remove();
		   /*var length=$('.parameter').length;
		   var parameter="";
		   var index=1;
		   for(var i=0;i<length;i++){
			   parameter=parameter+'<input type="text" placeholder="Parameter" style="margin-top: 5px;" class="parameter form-control" id="parameter_'+index+'" name="parameter"><i id="removeParameter_'+index+'" class="removeParameter fa fa-minus-square" onclick="removeParameter('+index+',null);"></i>';
			   index++;
		   }
		   $('#newParameters').html(parameter);*/
	}
}

//***for parameter value page***
function getListOfCentres(){
	jQuery.ajax({
		type : "POST",
		url : "client/getListOfCentres",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select Centre</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].centreId+"'>"+r[i].centreName+"</option>";
			}
			$('#centreMasterSelect').html(divContent);
		}
	});
}

function listOfTestMasterTableBody(){
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
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr id="+r[i].testId+"><td>"+index+"</td><td class='testName_"+r[i].testId+"'>"+r[i].testDescription+"</td><td class='editUserAccess' onclick='getParameterByTestId("+r[i].testId+")'><i class='fa fa-pencil'></i></td></tr>";
				index++;
			}
			$('#testMasterTableBody').html(divContent);
		}
	});
}

function getParameterByTestId(testId){
	jQuery.ajax({
		type : "POST",
		data : {
					"testId":testId
			    },
		url : "client/getParameterByTestId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			if(r.length>0){
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr><td>"+index+"</td><td>"+r[i].parameterName+"</td><td onclick='parameterValues("+r[i].parameterId+",\""+r[i].parameterName+"\")'><i class='fa fa-pencil'></i></td></tr>";
				index++;
			}
			$('#centreParametreDiv').show();
			$('#parameterMasterValuesTableBody').html(divContent);
			}
			else{
				$('#centreParametreDiv').show();
				$('#parameterMasterValuesTableBody').html("");
			}
			$('#testNameSpan').html($('.testName_'+testId).html());
			$('#testIdSpan').html(testId);//r[0].testMaster.testId
		}
	});
}

function parameterValues(parameterId,parameterName){
	$('#parameterIdSpan').html(parameterId);
	$('#parameterDivTitle').html(parameterName);
	$('#parameterNameCenterWise').val(parameterName);
	//$('#parameterValuesModal').modal('show');
	getParameterResultsByCentreId();
}

//For center wise Parameter values save
$('#valuesForm').change(function(){
	var value = $("input[name='values']:checked").val();
	if(value==="general"){
		$('#generalValuesDiv').show();
		$('#individualValuesDiv').hide();
	}
	else{
		$('#generalValuesDiv').hide();
		$('#individualValuesDiv').show();
	}    
});

//For parameter master save
$(document).on('change', '.valuesForm', function(){
	var id = (this.id).split("_")[1];
	var value = $("input[name='values_"+id+"']:checked").val();
	if(value==="general"){
		$('#criticalValueDiv_'+id).hide();
	}
	else{
		$('#criticalValueDiv_'+id).show();
	}  
});

function listOfUnitMasterCentreWise(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "user/listOfUnitMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select Unit</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].unitId+"'>"+r[i].unitName+"</option>";
			}
			$('.unitSelectList').html(divContent);
		}
	});
}

//$("#saveParameterValuesBtn").click(function(){
function saveParameterValues(){
	$("#pleaseWait").show();
	var centreId=$('#centreMasterSelect').val();
	var testId=$('#testIdSpan').html();
	var parameterId=$('#parameterIdSpan').html();
	var parameterName=$('#parameterNameCenterWise').val();
	var parameterValueTo=$('#parameterValueTo').val();
	var parameterValues=[];
	var generalComment;
	var value = $("input[name='values']:checked").val();
	var normalValue;
	if(value==="general"){
		generalComment=$('#generalComment').val();
		normalValue=2;
		if($('#generalComment').val()==null || $('#generalComment').val()==""){
		      alert("Please write general comment");
		}
		else{
			jQuery.ajax({
				async : true,
				type : "POST",
				data : {
					"centreId":centreId,
					"testId":testId,
					"parameterId":parameterId,
					"parameterName":parameterName+"#@"+parameterValueTo,
					"parameterValues":parameterValues,
					"generalComment":generalComment,
					"normalValue":normalValue
			    },
				url : "user/saveParameterValues",
				catche : false,
				error : function() {
					$("#pleaseWait").hide();
					alert("error");
				},
				success : function(r) {
					$("#pleaseWait").hide();
					$('#parameterValuesModal').modal("hide");
					alert("Parameter Values Saved Successfully");
				}
			});
		}
	}
	else if(value==="individual"){
		var validationError=false;
		generalComment="";
		normalValue=1;
		for(var i=1;i<5;i++){
			if($('#lower_'+i).val()!=null && $('#lower_'+i).val()!=""){
				validationError=true;
				var lowerValue = $('#lower_'+i).val();
				var upperValue = $('#upper_'+i).val();
				if (lowerValue!="" && jQuery.isNumeric(lowerValue) == false) {
					alertify.error('Please enter numeric value only');
				}
				else if (upperValue!="" && jQuery.isNumeric(upperValue) == false) {
					alertify.error('Please enter numeric value only');
				}
				else if($('#upper_'+i).val()==null || $('#upper_'+i).val()==""){
				    alert("Please write upper value");
				  }
				  else if($('#unit_'+i).val()==null || $('#unit_'+i).val()==""){
				    alert("Please select unit");
				  }
				  else if($('#lower_'+i).val()!=null && $('#lower_'+i).val()!="" &&
						  $('#upper_'+i).val()!=null && $('#upper_'+i).val()!="" &&
						  $('#unit_'+i).val()!=null && $('#unit_'+i).val()!=""){
					   parameterValues.push($('#lower_'+i).val()+"_"+$('#upper_'+i).val()+"_"+$('#unit_'+i).val()+"_"+i);
					   validationError=false;
				  }
			}
		}
		if(parameterValues!=null && parameterValues!="" && validationError===false && $("#individualValuesDiv .error").length==0){
			jQuery.ajax({
				async : true,
				type : "POST",
				data : {
					"centreId":centreId,
					"testId":testId,
					"parameterId":parameterId,
					"parameterName":parameterName,
					"parameterValues":parameterValues,
					"generalComment":generalComment,
					"normalValue":normalValue
			    },
				url : "user/saveParameterValues",
				catche : false,
				error : function() {
					$("#pleaseWait").hide();
					alert("error");
				},
				success : function(r) {
					$("#pleaseWait").hide();
					$('#parameterValuesModal').modal("hide");
					alert("Parameter Values Saved Successfully");
				}
			});
		}else{
			$("#pleaseWait").hide();
			alert("Please fill values properly");
		}
	}  
};

function getParameterResultsByCentreId(){
	$("#pleaseWait").show();
	var centreId=$('#centreMasterSelect').val();
	var testId=$('#testIdSpan').html();
	var parameterId=$('#parameterIdSpan').html();
	$('#parameterValuesModal').modal('show');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"centreId":centreId,
			"testId":testId,
			"parameterId":parameterId,
	    },
		url : "user/getParameterResultsByCentreId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$("#pleaseWait").hide();
			$("#criticalLowValueDisplay").val("");
			$("#criticalHighValueDisplay").val("");
			for(var i=1;i<5;i++){
				$('#lower_'+i).val("");
				$('#upper_'+i).val("");
				$('#unit_'+i).val("");
			}
			if(r.length>0){
			for(var i=0;i<r.length;i++){
				//$('.normalValue_'+r[i].normalValue).attr('checked', true);
				if(r[i].normalValue=="1"){
					$("#criticalLowValue").val(r[i].criticalLowValue);
					$("#criticalHighValue").val(r[i].criticalHighValue);
					$("#criticalLowValueDisplay").val(r[i].criticalLowValue);
					$("#criticalHighValueDisplay").val(r[i].criticalHighValue);
					$("#criticalLowValueDiv").show();
					$("#criticalHighValueDiv").show();
					if(r[i].valueFor!=undefined){
						$('#lower_'+r[i].valueFor).val(r[i].lowerValue);
						$('#upper_'+r[i].valueFor).val(r[i].upperValue);
						$('#unit_'+r[i].valueFor).val(r[i].unitId);
					}
					if(r[i].parameterName!=undefined){
						$('#parameterNameCenterWise').val(r[i].parameterName);
					}else{
						$('#parameterNameCenterWise').val("");
					}
					$('#generalValuesDiv').hide();
					$('#individualValuesDiv').show();
					$('.normalValue_1').prop('checked', "checked");
					
					$('#generalComment').val("");
					$('#parameterValueTo').val("");
				}
				if(r[i].normalValue=="2"){
					$('#generalComment').val(r[i].generalComment);
					if(r[i].parameterName!=undefined){
						$('#parameterNameCenterWise').val((r[i].parameterName).split("#@")[0]);
						$('#parameterValueTo').val((r[i].parameterName).split("#@")[1]);
					}else{
						$('#parameterNameCenterWise').val("");
						$('#parameterValueTo').val("")
					}
					$('#generalValuesDiv').show();
					$('#individualValuesDiv').hide();
					$('.normalValue_2').prop('checked', "checked");
					$("#criticalLowValueDisplay").val("");
					$("#criticalHighValueDisplay").val("");
					$("#criticalLowValueDiv").hide();
					$("#criticalHighValueDiv").hide();
					/*for(var i=1;i<5;i++){
						$('#lower_'+i).val("");
						$('#upper_'+i).val("");
						$('#unit_'+i).val("");
					}*/
				}
			}
			}
			else{
				/*for(var i=1;i<5;i++){
					$('#lower_'+i).val("");
					$('#upper_'+i).val("");
					$('#unit_'+i).val("");
				}*/
				$('.normalValue_1').prop('checked', "checked");
				$('#generalComment').val("");
				$('#parameterValueTo').val("");
				$('#generalValuesDiv').hide();
				$('#individualValuesDiv').show();
			}
		}
	});
}

$('#centreMasterSelect').change(function(){
	var value = $('#centreMasterSelect').val();
	if(value==""){
		$('#displayTestParameterDiv').hide();
	}
	else{
		$('#displayTestParameterDiv').show();
	}    
});

function lowHighValidation(status,current){
	var currentId = $(current).attr('id');
	var value = parseFloat($(current).val());
	var splitId = ($(current).attr('id')).split("_")[1];
	var value2;
	if(status=="low"){
		value2 = parseFloat($("#criticalHighValue_"+splitId).val());
		if(isNaN(value2) || value>value2){
			alertify.error("Lower value must not be greater than Higher value");
			$('#'+currentId).css("border","1px solid red");
			$('#'+currentId).addClass("error");
			return;
		}else{
			$('#'+currentId).css("border","");
			$("#criticalHighValue_"+splitId).css("border","");
			$('#'+currentId).removeClass("error");
			$("#criticalHighValue_"+splitId).removeClass("error");
		}
	}else{
		value2 = parseFloat($("#criticalLowValue_"+splitId).val());
		if(isNaN(value2) || value<value2){
			alertify.error("Higher value must be greater than Lower value");
			$('#'+currentId).css("border","1px solid red");
			$('#'+currentId).addClass("error");
			return;
		}else{
			$('#'+currentId).css("border","");
			$("#criticalLowValue_"+splitId).css("border","");
			$('#'+currentId).removeClass("error");
			$("#criticalLowValue_"+splitId).removeClass("error");
		}
	}
}

function parameterValueValidation(status,current){
	var criticalLowValue = parseFloat($("#criticalLowValue").val());
	var criticalHighValue = parseFloat($("#criticalHighValue").val());
	var currentId = $(current).attr('id');
	var value = parseFloat($(current).val());
	var splitId = ($(current).attr('id')).split("_")[1];
	var value2;
	if(status=="low"){
		value2 = parseFloat($("#upper_"+splitId).val());
		if(value>value2){
			alertify.error("Lower value must not be greater than Higher value");
			$('#'+currentId).css("border","1px solid red");
			$('#'+currentId).addClass("error");
			return;
		}else if(value<criticalLowValue){
			alertify.error("Lower value must be greater than Critical low value");
			$('#'+currentId).css("border","1px solid red");
			$('#'+currentId).addClass("error");
			return;
		}else if(value2<criticalHighValue){
			$('#'+currentId).css("border","");
			$("#upper_"+splitId).css("border","");
			$('#'+currentId).removeClass("error");
			$("#upper_"+splitId).removeClass("error");
		}else if(isNaN(criticalLowValue) || isNaN(criticalHighValue)){
			$('#'+currentId).css("border","");
			$("#upper_"+splitId).css("border","");
			$('#'+currentId).removeClass("error");
			$("#upper_"+splitId).removeClass("error");
		}
	}else{
		value2 = parseFloat($("#lower_"+splitId).val());
		if(value<value2){
			alertify.error("Higher value must be greater than Lower value");
			$('#'+currentId).css("border","1px solid red");
			$('#'+currentId).addClass("error");
			return;
		}else if(value>criticalHighValue){
			alertify.error("Higher value must not be greater than Critical high value");
			$('#'+currentId).css("border","1px solid red");
			$('#'+currentId).addClass("error");
			return;
		}else if(value2>criticalLowValue){
			$('#'+currentId).css("border","");
			$("#lower_"+splitId).css("border","");
			$('#'+currentId).removeClass("error");
			$("#lower_"+splitId).removeClass("error");
		}else if(isNaN(criticalLowValue) || isNaN(criticalHighValue)){
			$('#'+currentId).css("border","");
			$("#lower_"+splitId).css("border","");
			$('#'+currentId).removeClass("error");
			$("#lower_"+splitId).removeClass("error");
		}
	}	
}