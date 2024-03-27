$(document).ready(function(){
	$("#browser").treeview({
		animated:"normal",
		persist: "cookie"
	});

	$('#browser').on('click','.samplebutton',function () {
		$(this).removeClass("samplebutton");
		var testId=$(this).attr('id');
		var data=testId.split("_");
		jQuery.ajax({
			type : "POST",
			data : {
						"testId":data[1]
				    },
			url : "client/getParameterByTestId",
			error : function() {
				alert("error");
			},
			success : function(r) {
				var branches =$("<li style='margin-left: -46px; margin-top: -25px;'><span class='folder'>+</span><ul>" 
						+"<li><table class='table table-striped table-bordered table-hover table-full-width' id='sample_1'>"
						+"<thead><tr><th>Parameter Name</th><th>Parameter Result</th><th>Normal Values</th></tr></thead>"
						+"<tbody>"
						+displayTableContent(r,data[1])
						+"</tbody></table></li>"  
						+"</ul></li>").appendTo("#"+testId);
				$("#browser").treeview({
					add: branches
				});
			}
		});
		//getParameterValuesByCenterIdAndTestId($('#centerId').html(),data[1],$('#clientMasterId').html());
		getTestResultsByClientId($('#clientMasterId').html(),data[1],$('#dataEntryClientReportLineId').html(),$('#centerId').html());
	});
});

function displayTableContent(r,testId)
{
	var index=$('.packageResult').length;
	var branches="";
		for(var i=0;i<r.length;i++)
		{
			branches=branches+"<tr><td class='parameterName_"+r[i].parameterId+"'>"+r[i].parameterName+"</td><td><textarea class='packageResult result_"+index+"' id='parameterResult_"+r[i].parameterId+"_"+testId+"' type='text' style='width: 79%;' maxlength='5000'></textarea><input type='checkbox' onclick='disabledResult("+r[i].parameterId+","+testId+")' class='naFlag_"+r[i].parameterId+"_"+testId+"'></td><td><span class='hidden' id='generalComment_"+r[i].parameterId+"'></span><span id='lowerValue_"+r[i].parameterId+"'></span>-<span id='upperValue_"+r[i].parameterId+"'></span><span id='unitName_"+r[i].parameterId+"'></span> <input type='hidden' id='criticalLowValue_"+r[i].parameterId+"' value='"+r[i].criticalLowValue+"'><input type='hidden' id='criticalHighValue_"+r[i].parameterId+"' value='"+r[i].criticalHighValue+"'></td></tr>";
			index++;
		}	
	return branches;
}
