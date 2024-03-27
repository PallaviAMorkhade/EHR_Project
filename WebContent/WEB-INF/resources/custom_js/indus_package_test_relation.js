/**
 * Created by Kishor for manage Package and Test Relation. 
 */


function getPackageNameList(){
	jQuery.ajax({
		async : true,
		type : "POST",
		url : "client/getListOfPackageMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			$('#packageName_chosen').remove();
			var divContent="";
			divContent=divContent+"<option value='0'>Select Package</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].packageId+"' onclick=getTestFromPackageId('"+r[i].packageId+"')>"+r[i].packageDescription+" ["+r[i].packageId+"]</option>";
			}
			$('#packageName').html(divContent);
			$('.chosen-select').chosen().trigger("chosen:updated");
			$('#packageName_chosen').css("width","100%");
			getUserAccess();
		}
	});
}


function getTestNameList() {
	jQuery.ajax({
		async : false,
		type : "POST",
		url : "client/getListOftestMaster",
		catche : false,
		success : function(r) {
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
					//divContent=divContent+"<option value='0'>Select Test</option>";
					for(var i=0;i<r.length;i++)
					{
						divContent=divContent+"<option value='"+r[i].testId+"'>"+r[i].testDescription+"</option>";
					}
					$('#testName').html(divContent);
					testLeftTable(r);
				}
			});
}
	});
}


function testLeftTable(r){

	var htm = "";
	var index = 0;
	for (var i=0;i<r.length;i++) {
		htm = htm
				
				+ "<tr  id='tr"+Number(index + 1)+"' class='trs'>" 
				
				
				+"<td>"+Number(index + 1)+"</td>" 
				+"<td value="+r[i].testId+" id='testName"+Number(index + 1)+"'>"+r[i].testDescription+"</td>"
				
				+'<input id="testI'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].testId+'"></td>'
				+'<input id="testD'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].testDescription+'"></td>'
				+'<input id="testS'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].sequence+'"></td>'
				
				+'<td id="lastTd'+(index+1) +'">'
	    		+'<input id="inputCnt'+(index+1) +'" type="button" onclick="addTRtoRight('+(index+1) +','+r[i].testId+')" value=">>"></td>'
				+ "</tr>" ;
			
		index++;
	}

	$("#leftDiv").html(htm);

}

function addTRtoRight(trCnt,testId){
	
	if($('#packageName').val() > 0){
	
		var len = $("#rightDiv tr").length;

		for (var i = 1; i <= len; i++) {
			var testI = $("#testIR" + i).val();
				if (testId == testI) {
					alert("Duplicate Test Not Allowed");
					return false;
			}
		}
	
	//alert(trCnt);
	var testDescription     = $("#testD"+trCnt).val();
	var testId  =  $("#testI"+trCnt).val();
	var testSequence  =  $("#testS"+trCnt).val();
	
	
	var htm = "";
	var index = $("#rightDiv tr").length;
	
		htm = htm
				
				+ "<tr  id='tr"+Number(index + 1)+"' class='trs'>" 
				
				
				+"<td>"+Number(index + 1)+"</td>" 
				+"<td value="+testId+" id='testName"+Number(index + 1)+"'>"+testDescription+"</td>"
				
				+'<input id="testIR'+Number(index+1) +'" class="test" type="hidden" value="'+testId+'"></td>'
				+'<input id="testD'+Number(index+1) +'" class="test" type="hidden" value="'+testDescription+'"></td>'
				//+'<input id="testS'+Number(index+1) +'" class="test" type="hidden" value="'+Number(index+1)+'"></td>'
				+'<input id="ehrTestPackageId'+Number(index+1) +'" class="test" type="hidden" value="0"></td>'

				+'<td id="lastTd'+(index+1) +'">'
	    		+'<input id="testSR'+(index+1) +'" type="text"  value='+Number(index+1)+'></td>'
				+ "</tr>" ;
			
		index++;
	

	$("#rightDiv").append(htm);
	}else{
		alert("Please Select Package First");
	}
	
	}
	
function getTestFromPackageId(packId){
	var pID=$('#packageName').val();
//alert(pID);
	if(pID > 0){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"packageId" : pID
		},
		url : "client/getTestByPackageId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			//alert(r[1].testId);
			var divContent="";
			//divContent=divContent+"<option value='0'>Select Package</option>";
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<option value='"+r[i].testId+"' <input type='button' value='>>' id='inputCnt"+ (1) + "' onclick='addTRtoRight("+( 1)+")'>"+r[i].testDescription+"</option>";
			}
			$('#testName1').html(divContent);
			testRightTable(r);
		}
	});
	}else{
		alert("Please Select Package First.");
		}
	
}


function testRightTable(r){
	
	var htm = "";
	var index = 0;
	for (var i=0;i<r.length;i++) {
		htm = htm
				
				+ "<tr  id='tr"+Number(index + 1)+"' class='trs'>" 
				
				
				+"<td class='col-md-5-1 center' style='height: 21.5px;'>"+Number(index + 1)+"</td>" 
				+"<td class='col-md-5-1 center' style='height: 21.5px;'>"+r[i].testDescription+"</td>"
				
				+'<input id="testIR'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].testId+'"></td>'
				+'<input id="testD'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].testDescription+'"></td>'
				+'<input id="testS'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].sequence+'"></td>'
				+'<input id="ehrTestPackageId'+Number(index+1) +'" class="test" type="hidden" value="'+r[i].ehrTestPackageId+'"></td>'
				+"<td class='col-md-2-1 center' style='height: 21.5px;'>"
				+'<input id="testSR'+(index+1) +'" type="text" onchange="chakeDuplicateSequence('+(index+1) +')"  value='+r[i].sequence+'></td>'
				+ "</tr>" ;
			
		index++;
	}

	$("#rightDiv").html(htm);

}

function chakeDuplicateSequence(seqNo)
{
	//alert($('#testSR' + seqNo).val());
	var check = $('#testSR' + seqNo).val();
	var len = $("#rightDiv tr").length;
	for (var i = 1; i <= len; i++) {
		var testSR = $("#testSR" + i).val();
		if (i != seqNo) {
			if (check == testSR) {
				//alert(testSR + "==" + check);
				alert("Duplicate sequence not allowed");
				$('#testSR'+seqNo).val(0);
				setTimeout(function(){$('#testSR' + seqNo).focus(); }, 10);
				
				return false;
			}
		}
	}
}


function saveTestBtn(){
	
	var packageId = $('#packageName').val();
	var tableRows = $('#rightDiv tr').length;

			
	var testDetails = {
			listTest : []
	};
	
	for(var index=1;index <= tableRows;index++){
		var testDescription     = $("#testD"+index).val();
		var testId  =  $("#testIR"+index).val();
		var testSequence  =  $("#testSR"+index).val();
		var ehrTestPackageId  =  $("#ehrTestPackageId"+index).val();
			
		testDetails.listTest.push({
			testDescription 		: testDescription,
			packageId 		: packageId,
			testId		 	: testId,
			sequence 	: testSequence,
			ehrTestPackageId : ehrTestPackageId
			
			});
	}

	if(testDetails == null || testDetails == undefined){
		alert("No Records!!!");
		return false;
	}

	jQuery.ajax({
		async : false,
		type : "POST",
		data : {
			"testDetails" : JSON.stringify(testDetails),
		},
		url : "client/saveTestAgainstPackage",
		error : function() {
			alert('Network Issue!!!');
		},
		success : function(r) {
			alert(r);
			getTestFromPackageId(packageId);
			}
	});

}