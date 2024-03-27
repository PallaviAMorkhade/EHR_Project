<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/modernizr/modernizr.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/jquery-cookie/jquery.cookie.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/switchery/switchery.min.js"/>"></script>
		
	<script
		src="<c:url value="/indus/resources/vendor/jquery.sparkline/jquery.sparkline.min.js"/>"></script>
	<!-- end: MAIN JAVASCRIPTS -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	
	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>

	<script src="<c:url value="/indus/resources/alertify/alertify.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script>

<script src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.js"/>"></script>



		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/vendor/jquery.ui.widget.js"/>"></script>
		<!-- The Templates plugin is included to render the upload/download listings -->
		<script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
		<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
		<script src="<c:url value="/indus/resources/vendor/javascript-Load-Image/load-image.all.min.js"/>"></script>
		<!-- The Canvas to Blob plugin is included for image resizing functionality -->
		<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
		<!-- blueimp Gallery script -->
		<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
		<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.iframe-transport.js"/>"></script>
		<!-- The basic File Upload plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload.js"/>"></script>
		<!-- The File Upload processing plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-process.js"/>"></script>
		<!-- The File Upload image preview & resize plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-image.js"/>"></script>
		<!-- The File Upload audio preview plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-audio.js"/>"></script>
		<!-- The File Upload video preview plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-video.js"/>"></script>
		<!-- The File Upload validation plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-validate.js"/>"></script>
		<!-- The File Upload user interface plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-ui.js"/>"></script>
		
		<!-- The main application script -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/main.js"/>"></script>
		
		<%-- <link rel="stylesheet" media="screen" href="<c:url value="/indus/resources/vendor/jstree/themes/default/style.min.css"/>"> --%>
		
		<!-- for new jstree -->
		<%-- <link rel="stylesheet" href="<c:url value="/indus/resources/assets/css/jquery.treeview.css"/>"> --%>
		<!-- End of jstree -->
		
<!-- The template to display files available for upload -->
		<script id="template-upload" type="text/x-tmpl">
			{% for (var i=0, file; file=o.files[i]; i++) { $('#reportLength').html($('.template-upload').length+1);
				getPatientTest($('#clientMasterId').html());getListOfCentres();getPatientCenter($('#clientMasterId').html()); %}
			<tr class="template-upload fade">
			<td><Select class="testSelectList testSelect_{%=call()%}" style="width: 100px; margin-right: 15px;"></select></td>
			<td><Select class="centerSelectList centreSelect_{%=centerCount()%}" style="width: 100px; margin-right: 15px;"></select></td>			
			<td><span class="checkUpDate checkUpDate_{%=dateCount()%}"></span></td>
			<td>
			<p class="name fileName_{%=fileNameCount()%}"">{%=file.name%}</p>
			{% if (file.error) { %}
			<div><span class="label label-danger">Error</span> {%=file.error%}</div>
			{% } %}
			</td>
			<td><p class="size">{%=o.formatFileSize(file.size)%}</p></td>
			<td>
			{% if (!o.files.error) { %}
			<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
			{% } %}
			</td>
			<td></td>
			<td>
			{% if (!o.files.error && !i && !o.options.autoUpload) { %}
			<button class="hidden btn btn-success start uploadSingleReport btn-xs">
			<i class="glyphicon glyphicon-upload"></i>
			<span>Upload</span>
			</button>
			{% } %}
			{% if (!i) { %}
			<button class="btn btn-danger cancel btn-xs removeSingleReport">
			<i class="glyphicon glyphicon-trash"></i>
			<span>Remove</span>
			</button>
			{% } %}
			</td>
			</tr>
			{% } %}
		</script>
		<!-- The template to display files available for download -->
		<script id="template-download" type="text/x-tmpl">
			{% for (var i=0, file; file=o.files[i]; i++) { %}
			<tr class="template-download fade">
			<td></td>
			<td></td>
			<td>
			<span class="preview">
			{% if (file.thumbnailUrl) { %}
			<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
			{% } %}
			</span>
			</td>
			<td>
			<p class="name">
			{% if (file.url) { %}
			<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
			{% } else { %}
			<span>{%=file.name%}</span>
			{% } %}
			</p>
			</td>
			<td>
			<span class="size">{%=o.formatFileSize(file.size)%}</span>
			</td>
			<td></td>
			<td><i class="fa fa-check"></i></td>
			<td>
			{% if (file.deleteUrl) { %}
			<button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
			<i class="glyphicon glyphicon-trash"></i>
			<span>Delete</span>
			</button>
			<input type="checkbox" name="delete" value="1" class="toggle">
			{% } else { %}
			<button class="btn btn-danger cancel btn-xs removeSingleReport">
			<i class="glyphicon glyphicon-trash"></i>
			<span>Remove</span>
			</button>
			{% } %}
			</td>
			</tr>
			{% } %}
		</script>
		
		<script>
		var k=1;
		function call()
		{
			$('#k').html(k);
		 	return k++; 
		}
		
		var m=1;
		function centerCount()
		{
		 	return m++; 
		}
		
		var n=1;
		function dateCount()
		{
		 	return n++; 
		}
		
		var l=1;
		function fileNameCount()
		{
		 	return l++; 
		}
		</script>
	<!-- start: JavaScript Event Handlers for this page -->
	
	<script src="<c:url value="/indus/resources/vendor/jstree/jstree.min.js"/>"></script>
	<script src="<c:url value="/indus/resources/assets/js/ui-treeview.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_user_access_management.js"/>"></script>
	
	
	<script
		src="<c:url value="/indus/resources/assets/js/form-elements.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/maskedinput/jquery.maskedinput.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-touchspin/jquery.bootstrap-touchspin.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/autosize/autosize.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/selectFx/classie.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/selectFx/selectFx.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/select2/select2.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-timepicker/bootstrap-timepicker.min.js"/>"></script>
		
		<script src="<c:url value="/indus/resources/custom_js/indus_database.js"/>"></script>