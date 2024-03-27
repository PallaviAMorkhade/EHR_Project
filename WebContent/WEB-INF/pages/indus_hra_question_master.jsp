<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- end: HEAD -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/formeditor_ltr.css"/>">

<%
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<!-- sidebar -->
		<%-- <jsp:include page="indus_header.jsp"></jsp:include> --%>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
			<%-- <jsp:include page="indus_left_menu.jsp"></jsp:include> --%>
			<!-- end: TOP NAVBAR class="main-content" -->
			<div>
				<div class="wrap-content container" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Add Question Master</h4>
						<div id="form-content-editor" class="ss-form-editor">
							<!-- <div class="ss-page-tab-container">
								<div class="ss-page-tab">
									<span id="ss-form-editor-first-page-tab-text" role="heading"
										class="ss-page-tab-text">Page 1 of 1</span>
								</div>
								<div class="ss-page-tab-triangle"></div>
							</div> -->
							<div id="ss-form-title">
								<div class="ss-title-container hovering-textarea-container">
									<!-- <input type="text" value="Untitled form" name="form-title"
										id="form-title" class="jfk-textinput ss-title" placeholder=""
										dir="ltr"><label class="aria-only-help"
										for="form-title">Form Title</label> -->
									<div class="form-group" style="padding: 15px 27px 5px;">
										<label class="control-label"> HRA Type Master <span
											class="symbol required"></span>
										</label> <select class="jfk-textinput ss-formwidget-inputfield" id="hraTypeMaster"
											name="hraTypeMaster" style="width: 50%;">
										</select>
									</div>
								</div>
							</div>

							<div id="widget-container" class="mainQuestionDiv" style="display:none;overflow-y: scroll; max-height: 430px;">
								<div>
									<div class="ss-form-page">
										<div
											class="ss-formwidget-container ss-formwidget-container-editor"
											style="">
									<div id="questionDiv">		

									</div>				
											
											<div class="ss-formwidget-div" id="addNewQuestionDiv" style="display:none;">
												<div class="ss-formwidget-draghandle"></div>
												<div>
													<table role="presentation"
														class="ss-widget-nodrag goog-inline-block">
														<tbody>
															<tr>
																<th class="ss-header"><label
																	class="ss-formwidget-fieldlabel">Question Title</label></th>
																<td><input type="text" id="question" name="question"
																	placeholder="Question"
																	class="jfk-textinput ss-formwidget-inputfield"
																	dir="ltr"></td>
															</tr>
															<tr>
																<th class="ss-header"><label
																	class="ss-formwidget-fieldlabel">Question Display</label></th>
																<td><input type="text" id="questionDisplay" 
																	placeholder="Question Display"
																	class="jfk-textinput ss-formwidget-inputfield"></td>
															</tr>
															<tr>
																<th class="ss-header"><span
																	class="ss-formwidget-fieldlabel"><label
																		for=":5m.fw_tdd">Question Type</label></span></th>
																<td><select
																	class="jfk-textinput ss-formwidget-inputfield"
																	id="questionType" name="questionType">
																		<option value="">Select</option>
																		<option value="text">Text</option>
																		<option value="radio">Radio</option>
																		<option value="checkbox">Checkbox</option>
																</select> <span id=":5m.fw_cc"> </span></td>
															</tr>
														</tbody>
													</table>
													<br>

													<div class="form-group" id="optionDiv">
														 <div class="ss-header goog-inline-block" id=":fr.mli-label"></div>
														 <input type="text" placeholder="Options"
															class="option jfk-textinput magic-list-item-input" id="option_0"
															name="option"> <i id="addOptionsBtn"
															class="fa fa-plus-square"></i>
														<div id="newOptions"></div>
													</div>
													<span id="questionMasterId" class="hidden"></span>
												</div>
												
											<div id="saveQuestionBtnDiv">	
												<div class="ss-widget-nodrag goog-inline-block">
													<div class="goog-inline-block ss-header">
														<div tabindex="0" id="saveQuestionMasterBtn" onclick="saveQuestionMaster()"
															class="goog-inline-block jfk-button jfk-button-action"
															role="button" style="-moz-user-select: none;">Done</div>
													</div>
												</div>
											</div>
											
											<div id="updateQuestionBtnDiv" style="display:none;margin-left: 135px;">
												<div class="ss-widget-nodrag goog-inline-block">
													<div class="goog-inline-block ss-header">
														<div tabindex="0" id="updateQuestionMasterBtn" onclick="updateQuestionMaster()"
															class="goog-inline-block jfk-button jfk-button-action"
															role="button" style="-moz-user-select: none;">Update</div>
													</div>
												</div>
												<div class="ss-widget-nodrag goog-inline-block">
													<div class="goog-inline-block ss-header">
														<div tabindex="0" id="cancelQuestionMasterBtn"
															class="goog-inline-block jfk-button jfk-button-action"
															role="button" style="-moz-user-select: none;">Cancel</div>
													</div>
												</div>
											</div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="ss-bottom-buttons-container" class="addQuestionDiv" style="display:none;">
								<div class="ss-add-item" id="ss-add-item-button">
									<div aria-label="Add last chosen item " tabindex="0"
										class="goog-inline-block jfk-button jfk-button-standard jfk-button-collapse-right add-item-primary"
										role="button" style="-moz-user-select: none;">Add question</div>
								</div>
							</div>
						</div>
							
					</div>
					<!-- end: DYNAMIC TABLE -->

					</div>
				</div>
			</div>
		</div>

	<script src="<c:url value="/indus/resources/custom_js/indus_hra.js"/>"></script>	
	
	<script>
		jQuery(document)
				.ready(
						function() {

						});
	</script>
</body>
</html>
