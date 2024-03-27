function listOfCategoryMaster(){
	jQuery.ajax({
		async : true,
		type : "GET",
		url : "category/listOfCategoryMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>Select</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].categoryId+"'>"+r[i].category+"</option>";
			}
			$('#categoryMasterSelect').html(divContent);
		}
	});
}

function saveSubCategoryMaster(){
	var subCategoryMasterId=$('#subCategoryMasterId').html();
	var subCategory=$('#subCategory').val();
	var categoryId=$('#categoryMasterSelect').val();
	if(subCategory==null || subCategory==""){
		alert("Please write sub Category");
	}
	else if(categoryId==null || categoryId==""){
		alert("Please select Category");
	}
	else{
		var categoryMaster = {
			"categoryId":categoryId
		};
		
		var subCategoryMaster = {
				"subCategoryId":subCategoryMasterId,
				"subCategory":subCategory,
				"categoryMaster":categoryMaster
		};
		
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			//dataType : 'json',
			url : "subCategory/saveSubCategory",
			data : JSON.stringify(subCategoryMaster),
			/*error : function(response) {
				alert("error"+response);
			},*/
			success : function(response) {
				listOfSubCategoryMaster();
				if(subCategoryMasterId==""){
					alert("subCategory Saved Successfully");
				}else{
					alert("subCategory Updated Successfully");
				}
				clearSubCategoryMaster();
			}
		});
	}
};


function clearSubCategoryMaster(){
	$("#subCategoryMasterId").html("");
	$('#subCategory').val("");
	$('#categoryMasterSelect').val("");
	$('#saveSubCategoryBtnDiv').show();
	$('#updateSubCategoryBtnDiv').hide();
}

function listOfSubCategoryMaster(){
	jQuery.ajax({
		async : true,
		type : "GET",
		url : "subCategory/listOfSubCategoryMaster",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='subCategoryMasterRow' id='subCategoryMaster_"+r[i].subCategoryId+"'><td class='center'>"+index+"</td><td id='subCategory"+r[i].subCategoryId+"'>"+r[i].subCategory+"</td><td>"+r[i].categoryMaster.category+"</td><td class='editUserAccess' onclick='getSubCategoryMaster("+r[i].subCategoryId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteSubCategoryMaster("+r[i].subCategoryId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#subCategoryId').val(index);
			$('#subCategoryMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteSubCategoryMaster(subCategoryMasterId){
	var r = confirm("Are you sure you want to delete sub Category?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {"subCategoryMasterId":subCategoryMasterId},
		url : "subCategory/deleteSubCategory",
		error : function() {
			alert("error");
		},
		success : function(r) {
			//if(r=="true"){
				listOfSubCategoryMaster();
				alert("Sub Category Deleted Successfully");
    			/*}
    			else{
    				alert("Sorry you don't have access to delete sub Category");
    			}*/
		}
	});
    }
}

function getSubCategoryMaster(subCategoryMasterId,index){
	jQuery.ajax({
		type : "GET",
		data : {"subCategoryMasterId":subCategoryMasterId},
		url : "subCategory/getSubCategoryBySubCategoryId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit sub Category");
    		}
    		else{
			$('#saveSubCategoryBtnDiv').hide();
			$('#updateSubCategoryBtnDiv').show();
			$('#subCategory').val(r.subCategory);
			$('#subCategoryMasterId').html(r.subCategoryId);
			$('#categoryMasterSelect').val(r.categoryMaster.categoryId);
			$('#subCategoryId').val(index);
    		}
		}
	});
}
