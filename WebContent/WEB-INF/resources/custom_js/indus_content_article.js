function viewContentList(){
	$("#viewContentModal").modal('show');
	listOfContentMaster();
}

function uploadBtn(){
	$("#uploadContentBtn").trigger("click");
}

$("input[name='uploadType']"). click(function(){
	var fileType = $("input[name='uploadType']:checked").val();
	if(fileType=="image"){
		$("#uploadReportFile").attr("accept","image/*");
	}else if(fileType=="video"){
		$("#uploadReportFile").attr("accept","video/*");
	}else{
		$("#uploadReportFile").attr("accept",".pdf");
	}
	$("#uploadContentFiles").empty();
});

function saveContent() {
	var contentId = $('#contentMasterId').val();
	var contentTitle = $('#contentTitle').val();
	var contentDescription = CKEDITOR.instances["contentDescription"].getData();
	var contentTags = $('#contentTags').val();
	var contentFiles = [];
	$(".contentName").map(function(){
		var contentFile = {
			"fileName" : $(this).html(),
			"fileType" : $("input[name='uploadType']:checked"). val(),
			"isActive" : true
		};
		contentFiles.push(contentFile);
	});
	
	$(".removedContent").map(function(){
		var fileId = ($(this).attr("id")).split("_")[1];
		var contentFile = {
			"fileId" : fileId,
			"fileName" : $("#fileName_"+fileId).html(),
			"fileType" : $("input[name='uploadType']:checked"). val(),
			"isActive" : false
		};
		contentFiles.push(contentFile);
	});
	
	var categories = [];
	var subCategories = [];
	$(".selected").map(function(){
	  var value = $(this).attr("value");
	  if(($(this).attr("class")).indexOf("subCategory") > -1){
	    subCategories.push(value);
	  }else{
	    categories.push(value);
	  }  
	});
	
	var tagStatus = [];
	$(".tagStatusSelect").map(function(){
		  var tag = (this.id).split("_")[1];
		  var status = $(this).val();
		  var tagObject = {"tag":tag,"status":status};
		  tagStatus.push(tagObject);
	});
	
	var contentMaster = {
		"contentId" : contentId,
		"contentTitle" : contentTitle,
		"contentDescription" : contentDescription,
		"contentTags" : contentTags,
		"contentFiles" : contentFiles,
		"categories" : categories.toString(),
		"subCategories" : subCategories.toString(),
		"tagStatus" : JSON.stringify(tagStatus)
	};
	$.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		//dataType : 'json',
		url : "content/saveContent",
		data : JSON.stringify(contentMaster),
		/*error : function(response) {
			alert("error"+response);
		},*/
		success : function(response) {
			if(contentId==""){
				alert("Content Saved Successfully");
			}else{
				alert("Content Updated Successfully");
			}
			//listOfContentMaster();
			clearContentMaster();
		}
	});
};

function clearContentMaster(){
	$('#contentMasterId').val("");
	$('#contentTitle').val("");
	$("input[name='uploadType'][value='image']").prop('checked', true);
	$("#categoryBody td").removeClass("selected");
	CKEDITOR.instances["contentDescription"].setData("");
	$('#contentTags').tagsinput('removeAll');
	$("#tagStatus").html("");
	$("#uploadContentFiles").html("");
	$('#saveContentBtnDiv').show();
	$('#updateContentBtnDiv').hide();
}

function listOfContentMaster() {
	jQuery
			.ajax({
				type : "GET",
				url : "content/listOfContentMaster",
				catche : false,
				error : function() {
					alert("error");
				},
				success : function(r) {
					var divContent = "";
					var index = 1;
					for (var i = 0; i < r.length; i++) {
						divContent = divContent
								+ "<tr class='contentMasterRow' id='video_master_"
								+ r[i].contentId
								+ "'><td class='center'>"
								+ index
								+ "</td><td id='link_"
								+ r[i].contentId
								+ "'>"
								+ r[i].contentTitle
								+ "</td><td class='editUserAccess' onclick='getContentMaster("
								+ r[i].contentId
								+ ","
								+ index
								+ ")'><i class='fa fa-pencil'></i></td><td class='deleteUserAccess' onclick='deleteContentMaster("
								+ r[i].contentId
								+ ")'><i class='fa fa-times fa fa-white'></i></td></tr>";
						index++;
					}
					$('#contentId').val(index);
					$('#contentMasterTableBody').html(divContent);
					getUserAccess();
				}
			});
}

function deleteContentMaster(contentMasterId) {
	var r = confirm("Are you sure you want to delete content?");
	if (r == true) {
		jQuery.ajax({
			type : "POST",
			data : {
				"contentMasterId" : contentMasterId
			},
			url : "content/deleteContent",
			error : function() {
				alert("error");
			},
			success : function(r) {
				//if (r == "true") {
					listOfContentMaster();
					clearContentMaster();
					alert("Content Deleted Successfully");
				/*} else {
					alert("Sorry you don't have access to delete content");
				}*/
			}
		});
	}
}

function getContentMaster(contentMasterId, index) {
	jQuery.ajax({
		type : "GET",
		data : {
			"contentMasterId" : contentMasterId
		},
		url : "content/getContentByContentId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			clearContentMaster();
			$('#contentId').val(index);
			$('#contentMasterId').val(r.contentId);
			$('#contentTitle').val(r.contentTitle);
			setTimeout(() => {
				CKEDITOR.instances["contentDescription"].setData(r.contentDescription);
			}, 100);
			$('#contentTags').tagsinput('add', r.contentTags);
			for(var i=0;i<r.contentFiles.length;i++){
				var fileType = r.contentFiles[i].fileType;
				var content = '<tr id="fileId_'+r.contentFiles[i].fileId+'" class="content_'+r.contentFiles[i].fileId+'">'
				+ '<td><p id="fileName_'+r.contentFiles[i].fileId+'">'
				+ r.contentFiles[i].fileName
				+ '</p></td>'
				+ '<td><p class="size"></p> - </td>'
				+ '<td>'
				+ '<a class="btn btn-danger cancel btn-xs" onclick="removeContentFile('+r.contentFiles[i].fileId+')">Remove</a>';
				+ '</td></tr>';
				$("#uploadContentFiles").append(content);
				
				$("input[name='uploadType'][value='"+fileType+"']").prop('checked', true);
				if(fileType=="image"){
					$("#uploadReportFile").attr("accept","image/*");
				}else if(fileType=="video"){
					$("#uploadReportFile").attr("accept","video/*");
				}else{
					$("#uploadReportFile").attr("accept",".pdf");
				}
				break;//for single upload
			}
			if(r.categories!=null){
				var categories = (r.categories).split(",");
				for(var i=0;i<categories.length;i++){
					$("#category_"+categories[i]).addClass("selected");
				}
			}
			if(r.subCategories!=null){
				var subCategories = (r.subCategories).split(",");
				for(var i=0;i<subCategories.length;i++){
					$("#subCategory_"+subCategories[i]).addClass("selected");
				}
			}
			if(r.tagStatus!=null){
				var tagStatusArray = JSON.parse(r.tagStatus);
				for(var i=0;i<tagStatusArray.length;i++){
					var tag = tagStatusArray[i].tag;
					var status = tagStatusArray[i].status;
					$("#tagStatusSelect_"+tag).val(status);
				}
				$('.chosen-select').chosen().trigger("chosen:updated");
			}
			$("#viewContentModal").modal('hide');
			$('#saveContentBtnDiv').hide();
			$('#updateContentBtnDiv').show();
		}
	});
}

function removeContentFile(fileId){
	$("#fileId_"+fileId).addClass("removedContent hidden");
}

function categoryTable(){
	jQuery.ajax({
		type : "GET",
		url : "content/categoryTable",
		error : function() {
			//alert("error");
		},
		success : function(r) {
			var maxCountCategory = r.maxCountCategory;
			var categoryMasters = r.categoryMasters;
			var tableHeading = "";
			var tableBody = "";
			for(var i=0;i<categoryMasters.length;i++){
				var subCategoryMasters = categoryMasters[i].subCategories;
				tableBody = tableBody + "<tr>" +
						"<td onclick='selectCatSubCat(this)' id='category_"+categoryMasters[i].categoryId+"' class='category' value='"+categoryMasters[i].categoryId+"'><b>"+categoryMasters[i].category+"</b></td>";
				for(var j=0;j<maxCountCategory;j++){
					if(i==0){
						tableHeading = tableHeading + "<th>Sub-Category</th>";
					}
					
					if(subCategoryMasters!=null && subCategoryMasters[j]!=null){
						tableBody = tableBody + "<td onclick='selectCatSubCat(this)' id='subCategory_"+subCategoryMasters[j].subCategoryId+"' class='subCategory category_"+categoryMasters[i].categoryId+"' value='"+subCategoryMasters[j].subCategoryId+"'>"+subCategoryMasters[j].subCategory+"</td>";
					}
					else {
						tableBody = tableBody + "<td onclick='selectCatSubCat(this)'>-</td>";
					}
				}
				tableBody = tableBody + "</tr>";
			}
			$("#categoryHeading").append(tableHeading);
			$('#categoryBody').html(tableBody);
		}
	});
}

function selectCatSubCat(current) {
	var currentId = $(current).attr("id");
	var currentValue = $(current).attr("value");
	var currentClass = $(current).attr("class");
	if(currentValue!=undefined && currentValue!=""){
		$(current).toggleClass("selected");
		if($("#"+currentId).hasClass("category")) {
			if($("#"+currentId).hasClass("selected")){
				$(".category_"+currentValue).addClass("selected");
			}else{
				$(".category_"+currentValue).removeClass("selected");
			}
		}else if($("#"+currentId).hasClass("subCategory")) {
			var categoryId = (currentClass.split("_")[1]).split(" ")[0];
			if($(".category_"+categoryId).hasClass("selected")){
				$("#category_"+categoryId).addClass("selected")
			}else{
				$("#category_"+categoryId).removeClass("selected")
			}
		}
	}else{
		alert("There Is No Sub-Category!");
	}
}

//For Content Preview Page
function contentsPreview(){
	var contentMaster = {
			"contentId" : $("#contentCount").val(),//sending count
			"categories" : $("#categories").val(),
			"subCategories" : $("#subCategories").val(),
			"modifyBy" : $("#searchBy").val()
		};
	jQuery
	.ajax({
		//async:true,
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(contentMaster),
		url : "content/contentsPreview",
		//catche : false,
		/*error : function() {
			alert("error");
		},*/
		success : function(r) {
			displayContents(r);
		}
	});
}

function displayContents(r){
	var divContent = "";
	var todayDate = new Date();
	for (var i = 0; i < r.length; i++) {
		var addedOn = moment(r[i].addedOn).format('DD-MMM-YYYY HH:mm:ss');//Date conversion
		var readMore = '...<a value="'+r[i].contentId+'" onclick="readMoreContent(this)">Read More</a>';
		var contentDescription = r[i].contentDescription;
		var contentFiles = r[i].contentFiles;
		var publishMasters = r[i].publishMasters;
		var lastPublishedDate = "-";
		var upcomingPublishedDate = "-";
		var file = '<img value="'+r[i].contentId+'" onclick="readMoreContent(this)" style="height: 250px;width: 100%;" alt="" src="resources/assets/images/no-content.png">';
		if(contentFiles!=null && contentFiles.length>0){
			var fileType = contentFiles[0].fileType;
			var fileName = contentFiles[0].fileName;
			if(fileType!=null && fileType=="image"){
				file = '<img value="'+r[i].contentId+'" onclick="readMoreContent(this)" style="height: 250px;width: 100%;" alt="" src="readContentPdf?pdfFileName='+fileName+'">';
			}else if(fileType=="video"){
				file = '<video controls="" style="height: 250px;width: 100%;">'
					+'<source id="dashboardVideo" type="video/mp4" src="previewContentVideo?fileName='+fileName+'">'
					+'</source>Your browser does not support the video tag.</video>';
				
				//poster="resources/assets/images/pdf-icon.png"
			}else{
				file = '<img value="'+r[i].contentId+'" onclick="readMoreContent(this)" style="height: 250px;width: 100%;" alt="" src="resources/assets/images/pdf-icon.png">';
			}
		}
		
		if(publishMasters!=null && publishMasters.length>0){
			for(var j=0;j<publishMasters.length;j++){
				var publishDate = publishMasters[j].publishDate;
				if(todayDate>publishDate){
					lastPublishedDate = moment(publishDate).format('DD-MMM-YYYY');
				}else{
					upcomingPublishedDate = moment(publishDate).format('DD-MMM-YYYY');
				}
			}
			
		}
		
		divContent = divContent +'<div class="col-sm-6 col-md-6">'
		+'<div class="thumbnail">'
		+'<div class="caption">'
		+'<div class="row"> <div class="col-md-8">'
		+'<h3>'+r[i].contentTitle+'</h3>'
		+'</div>'
		+'<div class="col-md-4">'
		+'<p class="pull-right">'
		+'<a id="'+r[i].contentTitle+'" value="'+r[i].contentId+'" onclick="contentSelect(this)" style="margin-right: 5px;" class="btn btn-o btn-danger btn-xs contentSelect contentSelected_'+r[i].contentId+'"> Select </a>'
		+'<a value="'+r[i].contentId+'" class="btn btn-o btn-primary btn-xs" onclick="proceedSingleContent(this)"> Publish </a></p>'
		+'</div></div>'
		+'<p><b>Created Date : </b>'+addedOn+'</p>'
		+'<p style="word-wrap: break-word;"><b>Categories : </b>'+r[i].categories+'</p>'
		+'<p style="word-wrap: break-word;"><b>Sub-Categories : </b>'+r[i].subCategories+'</p>'
		+'<p><b>Last Published Date : </b>'+lastPublishedDate+'</p>'
		+'<p><b>Upcoming Publish Date : </b>'+upcomingPublishedDate+'</p>'
		+'</div>'
		+file
		+'<div class="caption">'
		+'<div class="contentDescription">'+contentDescription.substring(0, 150);
		if(contentDescription.length>150){
			divContent = divContent + readMore;
		}
		divContent = divContent +'</div>'
		+'</div></div></div>';
		$("#lastContentId").val(r[i].contentId);
	}
	var contentCount = parseInt($("#contentCount").val());
	$("#contentCount").val(contentCount + r.length);
	$('#contentPreviewDiv').append(divContent);
}

//Scroll Fetch Data
$( "#scrollContents" ).scroll(function() {
	var element = document.getElementById('scrollContents');
	if ((element.scrollHeight - element.scrollTop === element.clientHeight) && $("#lastContentId").val()!="1")
	    {
			contentsPreview();
	    }
});

function readMoreContent(current) {
	var contentId = $(current).attr("value");
	jQuery.ajax({
		type : "GET",
		data : {
			"contentMasterId" : contentId
		},
		url : "content/getContentByContentId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			$("#contentReadMoreModal").modal('show');
			//$('#contentMasterId').val(r.contentId);
			$('#contentTitleModal').html(r.contentTitle);
			$('#contentDescriptionModal').html(r.contentDescription);
			if(r.contentFiles.length>0){
				var fileType = r.contentFiles[0].fileType;
				var fileName = r.contentFiles[0].fileName;
				if(fileType!=null && fileType=="image"){
					$("#contentImage").attr('src',"readContentPdf?pdfFileName="+fileName);
					$("#contentImage").show();
					$("#contentVideoTag").hide();
					$("#viewContentPdf").hide();
				}else if(fileType=="video"){
					$('#contentVideo').attr('src',"previewContentVideo?fileName="+fileName);
					$("#contentVideoTag").load();
					$("#contentImage").hide();
					$("#contentVideoTag").show();
					$("#viewContentPdf").hide();
				}else{
					$("#viewContentPdf").attr('src',"readContentPdf?pdfFileName="+fileName);
					$("#contentImage").hide();
					$("#contentVideoTag").hide();
					$("#viewContentPdf").show();
				}
			}else{
				$("#contentImage").attr('src',"resources/assets/images/no-content.png");
				$("#contentImage").show();
				$("#contentVideoTag").hide();
				$("#viewContentPdf").hide();
			}
			//$('#contentTags').tagsinput('add', r.contentTags);
			/*if(r.categories!=null){
				var categories = (r.categories).split(",");
				for(var i=0;i<categories.length;i++){
					$("#category_"+categories[i]).addClass("selected");
				}
			}
			if(r.subCategories!=null){
				var subCategories = (r.subCategories).split(",");
				for(var i=0;i<subCategories.length;i++){
					$("#subCategory_"+subCategories[i]).addClass("selected");
				}
			}*/
		}
	});
};

function contentSelect(current) {
	var contentId = $(current).attr("value");
	$(current).toggleClass("contentSelected btn-o btn-danger btn-success");
	if ($(current).text().trim() == "Select"){
	       $(current).text("Selected")
	}else{
	       $(current).text("Select");
	}
};

function selectAllContent(){
	$(".contentSelect").addClass("contentSelected");
	$(".contentSelect").addClass("btn-success").removeClass("btn-o btn-danger");
	$(".contentSelect").text("Selected")
}

function unSelectAllContent(){
	$(".contentSelect").removeClass("contentSelected");
	$(".contentSelect").removeClass("btn-success").addClass("btn-o btn-danger");
	$(".contentSelect").text("Select")
}

function categories(){
	jQuery.ajax({
		type : "GET",
		url : "category/listOfCategoryMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>ALL</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].categoryId+"'>"+r[i].category+"</option>";
			}
			$('#categories').html(divContent);
		}
	});
}

function subCategories(){
	jQuery.ajax({
		type : "GET",
		url : "subCategory/listOfSubCategoryMaster",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>ALL</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].subCategoryId+"'>"+r[i].subCategory+"</option>";
			}
			$('#subCategories').html(divContent);
		}
	});
}

function subCategoriesByCategoryId(categoryId){
	jQuery.ajax({
		type : "GET",
		data : {
			"categoryId" : categoryId
		},
		url : "category/subCategoriesByCategoryId",
		error : function() {
			alert("error");
		},
		success : function(r) {
			var divContent="";
			divContent=divContent+"<option value=''>ALL</option>";
			for(var i=0;i<r.length;i++){
				divContent=divContent+"<option value='"+r[i].subCategoryId+"'>"+r[i].subCategory+"</option>";
			}
			$('#subCategories').html(divContent);
		}
	});
}

function searchByChange(current){
	$("#contentCount").val(0);
	$('#contentPreviewDiv').html("");
	contentsPreview();
}

function categoryChange(current){
	var categoryId = $(current).val();
	if(categoryId==""){
		//subCategories();
		$("#subCategories").html("<option value=''>ALL</option>");
		$("#subCategories").attr("disabled","disabled");
	}else{
		subCategoriesByCategoryId(categoryId);
		$("#subCategories").removeAttr("disabled","disabled");
	}
	$("#contentCount").val(0);
	$('#contentPreviewDiv').html("");
	contentsPreview();
}

function subCategoryChange(current){
	$("#contentCount").val(0);
	$('#contentPreviewDiv').html("");
	contentsPreview();
}

function proceedSingleContent(current){
	unSelectAllContent();
	var contentId = $(current).attr("value");
	$(".contentSelected_"+contentId).addClass("contentSelected");
	$(".contentSelected_"+contentId).addClass("btn-success").removeClass("btn-o btn-danger");
	$(".contentSelected_"+contentId).text("Selected")
	proceedContent();
}

function proceedContent(){
	if($(".contentSelected").length>0){
	var contentTableBody = "";
	var index = 1;
	$(".contentSelected").map(function(){
		  var contentId = $(this).attr("value");
		  var contentTitle = $(this).attr("id");
		  contentTableBody = contentTableBody + '<tr>'
		  	  +'<td class="center">'+index+'</td>'
			  +'<td id="contentTitle_'+index+'" value="'+contentId+'">'+contentTitle+'</td>'
			  +'<td><input id="sequence_'+index+'" value="'+index+'" type="text" class="sequence sequence_'+index+'" onkeyup="numericOnly(this),setSequenceClass(this)"></td></tr>';
		index++; 
	});
	$("#contentPublishModal").modal("show");
	$("#contentTableBody").html(contentTableBody);
	}else{
		alert("Please Select Content First");
	}
}

function setSequenceClass(current){
	var value = $(current).val();
	$(current).removeAttr("class");
	$(current).addClass("sequence sequence_"+value+"");
}

function submitContent(){
	var daysInteruption = $("#daysInteruption").val();
	var repeatContent =$("#repeatContent").val();
	var occurences = $("#occurences").val();
	if(occurences==""){
		occurences = 1;
	}
	var startDate = $("#startDate").val();
	if(startDate==""){
		alert("Please Select Start Date");
		return false;
	}
	var valueArray = [];
	var breakOut = false;
	var blankValue = false;
	var duplicateValue = [];
	$(".sequence").map(function(){
		  var value = parseInt($(this).val());
		  if(Number.isNaN(value)) {
			  	blankValue = true;
		        return false;
		  }else if(jQuery.inArray(value, valueArray) != -1) {
			  	duplicateValue.push(value);
			  	breakOut = true;
		        return false;
		  }
		  valueArray.push(value);
	});
	if(blankValue) {
		alert("Please Enter Sequence Number...");
		blankValue = false;
	    return false;
	}else if(breakOut) {
		alert("Sequence Number "+duplicateValue+" Is Duplicated...");
	    breakOut = false;
	    return false;
	}
	var sequentialArray = valueArray.sort(function(a, b){return a-b});
	var contents = [];
	for(var i=0;i<sequentialArray.length;i++){
	   var id = ($(".sequence_"+sequentialArray[i]).attr("id")).split("_")[1];
	   var contentTitle = $("#contentTitle_"+id).html();
	   var contentId = $("#contentTitle_"+id).attr("value");
	   var content = {"contentId":contentId,"contentTitle":contentTitle};
	   contents.push(content);
	}
	
	var index = 1;
	var contentSchedule = "";
	var count = 0;
	for(var j=0;j<occurences;j++){
	for(var i=0;i<contents.length;i++){
		var displayDate = moment(startDate, "DD-MM-YYYY");
		displayDate = moment(displayDate).format('DD-MMM-YYYY');
		contentSchedule = contentSchedule + '<tr>'
	  	  +'<td class="center">'+index+'</td>'
		  +'<td class="publishMaster" id="'+startDate+'" value="'+contents[i].contentId+'">'+contents[i].contentTitle+'</td>'
		  +'<td >'+displayDate+'</td></tr>';
		startDate = moment(startDate, "DD-MM-YYYY").add(daysInteruption,'days');
		startDate = moment(startDate).format('DD-MM-YYYY');//Date conversion
		index++; 
	}
	if(repeatContent!=""){
		startDate = $("#startDate").val();
		if(repeatContent=="daily"){
			startDate = moment(startDate, "DD-MM-YYYY").add(j+1,'days');
		}
		else if(repeatContent=="weekly"){
			startDate = moment(startDate, "DD-MM-YYYY").add(j+1,'weeks');
		}
		else if(repeatContent=="monthly"){
			startDate = moment(startDate, "DD-MM-YYYY").add(j+1,'months');
		}
		else if(repeatContent=="quarterly"){
			startDate = moment(startDate, "DD-MM-YYYY").add(count+3,'months');
			count = count + 3;
		}
		else if(repeatContent=="halfYearly"){
			startDate = moment(startDate, "DD-MM-YYYY").add(count+6,'months');
			count = count + 6;
		}
		else if(repeatContent=="yearly"){
			startDate = moment(startDate, "DD-MM-YYYY").add(j+1,'years');
		}
		startDate = moment(startDate).format('DD-MM-YYYY');//Date conversion
	}
	}
	$("#contentSchedule").html(contentSchedule);
}

function publishContent(){
	if($("#contentSchedule tr").length>0){
	var daysInteruption = $("#daysInteruption").val();
	var repeatContent =$("#repeatContent").val();
	var occurences = $("#occurences").val();
	var startDate = $("#startDate").val();
	var publishMasters = [];
	$(".publishMaster").map(function(){
		var contentId = $(this).attr("value");
		var contentMaster = {"contentId":contentId};
		var publishDate = $(this).attr("id");
		var publishMaster = {
			"publishDate" : moment(publishDate, "DD-MM-YYYY"),//convert to date
			"contentMaster" : contentMaster,
			"isActive" : true
		};
		publishMasters.push(publishMaster);
	});
	
	var publishDetail = {
		"detailId" : "",
		"daysInteruption" : daysInteruption,
		"repeatContent" : repeatContent,
		"occurences" : occurences,
		"startDate" : moment(startDate, "DD-MM-YYYY"),//convert to date
		"isActive" : true,
		"publishMasters" : publishMasters
	};
	
	$.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		//dataType : 'json',
		url : "content/publishContent",
		data : JSON.stringify(publishDetail),
		/*error : function(response) {
			alert("error"+response);
		},*/
		success : function(response) {
			clearContentPublishModal();
			alert("Content Published Successfully");
		}
	});
	}else{
		alert("Please Schedule Atleast One Content");
	}
}

function clearContentPublishModal(){
	$("#contentPublishModal").modal("hide");
	$("#daysInteruption").val("");
	$("#repeatContent").val("");
	$("#occurences").val("");
	$("#startDate").val("");
	$("#contentTableBody").html("");
	$("#contentSchedule").html("");
}

function addContentTag(itemValue) {
	var divContent = "<tr id='tagStatusRow_"+itemValue+"'><td>"+itemValue+"</td>"
		+"<td><select class='tagStatusSelect chosen-select' id='tagStatusSelect_"+itemValue+"' data-placeholder='Choose Status' multiple tabindex='4'>"
		+ "<option value='1'>Normal</option>"
		+ "<option value='2'>Abnormal</option>"
		+ "<option value='3'>Urgent</option>"
		+ "<option value='4'>Borderline</option>"
		+ "</select></td></tr>";
	$("#tagStatus").append(divContent);
	$(function() {
        $('.chosen-select').chosen();
        //$('.chosen-select-deselect').chosen({ allow_single_deselect: true });
    });
}

function removeContentTag(itemValue) {
	$("#tagStatusRow_"+itemValue).remove();
}