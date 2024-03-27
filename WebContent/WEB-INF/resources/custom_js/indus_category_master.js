function saveCategoryMaster(){
	var categoryMasterId=$('#categoryMasterId').html();
	var category=$('#category').val();
	if(category==null || category==""){
		alert("Please Write Category");
	}
	else{
		var categoryMaster = {
			"categoryId":categoryMasterId,
			"category":category
		};
		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			//dataType : 'json',
			url : "category/saveCategory",
			data : JSON.stringify(categoryMaster),
			/*error : function(response) {
				alert("error"+response);
			},*/
			success : function(response) {
				if(categoryMasterId==""){
					alert("Category Saved Successfully");
				}else{
					alert("Category Updated Successfully");
				}
				listOfCategoryMaster();
				clearCategoryMaster();
			}
		});
	}
};

function clearCategoryMaster(){
	$('#categoryMasterId').html("");
	$('#category').val("");
	$('#saveCategoryBtnDiv').show();
	$('#updateCategoryBtnDiv').hide();
}

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
			var index=1;
			for(var i=0;i<r.length;i++)
			{
				divContent=divContent+"<tr class='categoryMasterRow' id='category_master_"+r[i].categoryId+"'><td class='center'>"+index+"</td><td id='category"+r[i].categoryId+"'>"+r[i].category+"</td><td class='editUserAccess' onclick='getCategoryMaster("+r[i].categoryId+","+index+")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteCategoryMaster("+r[i].categoryId+")'><i class='fa fa-times fa fa-white'></i></td></tr>";
				index++;
			}
			$('#categoryId').val(index);
			$('#categoryMasterTableBody').html(divContent);
			getUserAccess();
		}
	});
}

function deleteCategoryMaster(categoryMasterId){
	var r = confirm("Are you sure you want to delete Category?");
    if (r == true) {
	  jQuery.ajax({
		type : "GET",
		data : {"categoryMasterId":categoryMasterId},
		url : "category/deleteCategory",
		error : function() {
			alert("error");
		},
		success : function(r) {
			//if(r=="true"){
				listOfCategoryMaster();
				alert("Category Deleted Successfully");
    			/*}
    			else{
    				alert("Sorry you don't have access to delete category");
    			}*/
		}
	});
    }
}

function getCategoryMaster(categoryMasterId,index){
	jQuery.ajax({
		type : "GET",
		data : {"categoryMasterId":categoryMasterId},
		url : "category/getCategoryByCategoryId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			/*if(r.addedBy=="false"){
				alert("Sorry you don't have access to edit category");
    		}
    		else{*/
			$('#saveCategoryBtnDiv').hide();
			$('#updateCategoryBtnDiv').show();
			$('#category').val(r.category);
			$('#categoryMasterId').html(r.categoryId);
			$('#categoryId').val(index);
    		//}
		}
	});
}
