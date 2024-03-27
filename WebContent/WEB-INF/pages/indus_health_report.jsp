<%@page import="com.hms.indus.bo.ParameterReportDetail"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.hms.indus.bo.TestMaster"%>
<%@page import="com.itextpdf.tool.xml.XMLWorkerHelper"%>
<%@page import="com.itextpdf.tool.xml.ElementList"%>
<%@page import="com.itextpdf.text.html.simpleparser.StyleSheet"%>
<%@page import="com.itextpdf.text.html.simpleparser.HTMLWorker"%>
<%@page import="com.hms.indus.bo.QuestionReportDetail"%>
<%@page import="com.itextpdf.text.pdf.ColumnText"%>
<%@page import="java.awt.Color"%>
<%@page import="com.itextpdf.text.Font.FontFamily"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.pdf.CMYKColor"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.io.StringReader"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@page import="com.hms.indus.bo.OptionMaster"%>
<%@page import="com.hms.indus.util.FilePath"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.itextpdf.text.FontFactory"%>
<%@page import="com.itextpdf.text.pdf.BaseFont"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.hms.indus.controller.HomeController"%>
<%@page import="com.itextpdf.text.pdf.Barcode128"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.Chunk"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.Image"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.pdf.FontSelector"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.Document"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Health Report</title>
</head>

<%
	ClientMaster clientMaster = (ClientMaster) request.getAttribute("clientMaster");
	String memberId = clientMaster.getMemberId();
	if (memberId != null && memberId.equals("0")) {
		memberId = "";
	}

	HashMap<Integer, JSONArray> hashMap = (HashMap<Integer, JSONArray>) request.getAttribute("hashMap");
	JSONArray visitList = (JSONArray) request.getAttribute("visitList");
	String assessmentDate = "";	
	HashMap<TestMaster, JSONArray> testHashMap = new HashMap<TestMaster, JSONArray>();
	for(int i = 0; i < visitList.size(); i++){
		JSONObject visitObject = (JSONObject) visitList.get(i);
		JSONArray parameterList = (JSONArray) visitObject.get("parameterList");
		assessmentDate = (String) visitObject.get("visitDate");
		for (int j = 0; j < parameterList.size(); j++) {
			JSONObject parameterObject = (JSONObject) parameterList.get(j);
			String testId = (String) parameterObject.get("testId");
			String testDescription = (String) parameterObject.get("testDescription");
			TestMaster testMaster = new TestMaster();
			testMaster.setTestId(Integer.parseInt(testId));
			testMaster.setTestDescription(testDescription);
			
			if(testHashMap.containsKey(testMaster)){
				JSONArray parameters = testHashMap.get(testMaster);
				parameters.add(parameterObject);
				testHashMap.put(testMaster, parameters);
			}else{
				JSONArray parameters = new JSONArray();
				parameters.add(parameterObject);
				testHashMap.put(testMaster, parameters);
			}
		}
	}
	
	Float hraHealthScore = (Float) request.getAttribute("hraHealthScore");
	Float parameterHealthScore = (Float) request.getAttribute("parameterHealthScore");

	//For todays date
	java.util.Date date = new java.util.Date();
	DateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	String todaysDate = simpleDateFormat.format(date);
%>

<body>
	<%
		try {
			ServletOutputStream outStream = response.getOutputStream();
			response.reset();
			response.setContentType("application/pdf");

			float left = 20;
			float right = 20;
			float firstPageTop = 20;
			float top = 40;
			float bottom = 60;
			Document document = new Document(PageSize.A4, left, right, firstPageTop, bottom);
			PdfWriter writer = PdfWriter.getInstance(document, outStream);
			document.open();
			document.setMargins(left, right, top, bottom);

			//font
			Font reportHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			Font topHeader = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
			topHeader.setColor(BaseColor.WHITE);
			Font header = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
			Font subheadertitle = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
			Font subheader = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
			Font footer = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
			header.setColor(10, 4, 2);

			Font tableheader = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
			Font tabletext = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);
			Font small = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

			FontSelector selector = new FontSelector();
			selector.addFont(subheader);
			
			Font colorText = new Font(Font.FontFamily.TIMES_ROMAN, 12);
			colorText.setColor(BaseColor.RED);

			/* Start Of Indus Logo */
			Image img = null;
			PdfPCell cell = null;
			PdfPCell headerCell = null;
			try {
				String path = getServletContext().getRealPath("WEB-INF/resources/assets/images/indus-logo-new.jpg");
				img = Image.getInstance(path);
				img.scaleAbsolute(150, 60);
				img.setAlignment(Element.ALIGN_RIGHT);
				document.add(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* End Of Indus Logo */

			PdfContentByte pdfContentByte = writer.getDirectContent();
			
			StyleSheet styleSheet = new StyleSheet();
            //styleSheet.loadTagStyle("body", "size", "90px");
            //styleSheet.loadTagStyle("p", "size", " 100px");
			HTMLWorker htmlWorker = new HTMLWorker(document);
			Paragraph paragraph = new Paragraph();
			
			//document.newPage();
			
			HashMap<Integer, JSONObject> recommendedTestHashMap = new HashMap<Integer, JSONObject>();
			
			Paragraph blankPara = new Paragraph(" ");
			
			Rectangle reportInfoRect = new Rectangle(35, 50, 365, 720);
			reportInfoRect.setBorder(Rectangle.BOX);
			reportInfoRect.setBorderWidth(1);
			reportInfoRect.setBackgroundColor(new BaseColor(29, 129, 175));
			pdfContentByte.rectangle(reportInfoRect);

			ColumnText reportInfoColumn = new ColumnText(pdfContentByte);
			reportInfoColumn.setSimpleColumn(reportInfoRect);
			
			blankPara.setSpacingAfter(220);
			reportInfoColumn.addElement(blankPara);

			Paragraph reportHeadingPara = new Paragraph("Health Report");
			reportHeadingPara.setAlignment(Element.ALIGN_RIGHT);
			reportHeadingPara.setIndentationLeft(20);
			reportHeadingPara.setIndentationRight(20);
			Font bigHeader = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.BOLD);
			reportHeadingPara.setFont(bigHeader);
			reportInfoColumn.addElement(reportHeadingPara);

			Paragraph reportDetailsPara = new Paragraph(
					"Now that you have taken the time to do this assessment, here are your"
							+ " personalized results and recommendations. The messages below are"
							+ " based on the questions you chose to answer. You may have other risks"
							+ " beyond what is covered in this assessment. Therefore, this assessment"
							+ " should not be considered a definitive assessment of your health. Be sure"
							+ " to see your doctor for recommended check-ups and health evaluations.");
			reportDetailsPara.setAlignment(Element.ALIGN_RIGHT);
			reportDetailsPara.setIndentationLeft(20);
			reportDetailsPara.setIndentationRight(20);
			reportDetailsPara.setFont(reportHeader);
			reportInfoColumn.addElement(reportDetailsPara);

			Paragraph dateAssessmentPara = new Paragraph("Date of Assessment : "+assessmentDate);
			dateAssessmentPara.setAlignment(Element.ALIGN_RIGHT);
			dateAssessmentPara.setIndentationRight(20);
			dateAssessmentPara.setFont(reportHeader);
			dateAssessmentPara.setSpacingBefore(10);
			reportInfoColumn.addElement(dateAssessmentPara);

			reportInfoColumn.go();

			Rectangle patientInfoRect = new Rectangle(370, 50, 560, 720);
			patientInfoRect.setBorder(Rectangle.BOX);
			patientInfoRect.setBorderWidth(1);
			patientInfoRect.setBackgroundColor(new BaseColor(66, 134, 244));
			pdfContentByte.rectangle(patientInfoRect);

			ColumnText patientInfoColumn = new ColumnText(pdfContentByte);
			patientInfoColumn.setSimpleColumn(patientInfoRect);
			
			blankPara.setSpacingAfter(250);
			patientInfoColumn.addElement(blankPara);

			Paragraph namePara = new Paragraph("" + clientMaster.getFirstName() + " "
					+ clientMaster.getMiddleName() + " " + clientMaster.getLastName());
			namePara.setAlignment(Element.ALIGN_LEFT);
			namePara.setIndentationLeft(10);
			namePara.setFont(reportHeader);
			patientInfoColumn.addElement(namePara);

			Paragraph agePara = new Paragraph("Age : " + clientMaster.getClientAge() + " Yrs");
			agePara.setAlignment(Element.ALIGN_LEFT);
			agePara.setIndentationLeft(10);
			agePara.setFont(reportHeader);
			patientInfoColumn.addElement(agePara);

			Paragraph genderPara = new Paragraph("Gender : " + clientMaster.getGender());
			genderPara.setAlignment(Element.ALIGN_LEFT);
			genderPara.setIndentationLeft(10);
			genderPara.setFont(reportHeader);
			patientInfoColumn.addElement(genderPara);

			Paragraph bloodGroupPara = new Paragraph("Blood Group : " + clientMaster.getBloodGroup());
			bloodGroupPara.setAlignment(Element.ALIGN_LEFT);
			bloodGroupPara.setIndentationLeft(10);
			bloodGroupPara.setFont(reportHeader);
			patientInfoColumn.addElement(bloodGroupPara);

			patientInfoColumn.go();

			

			document.newPage();

			//For Full Heading Line
			PdfPTable headerTable = new PdfPTable(1);
			int[] headerTableWidth = { 120 };
			headerTable.setWidths(headerTableWidth);
			headerTable.setWidthPercentage(95f);
			headerTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			headerTable.getDefaultCell().setPaddingBottom(7);
			headerTable.getDefaultCell().setPaddingTop(7);

			//For heading
			headerCell = new PdfPCell(new Phrase("Personal Details", topHeader));
			headerCell.setBackgroundColor(new BaseColor(36, 100, 160));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			document.add(headerTable);
			headerTable.flushContent();

			PdfPTable HeaderTable3 = new PdfPTable(4);
			int[] headerwidth3 = { 40, 50, 40, 50 };
			HeaderTable3.setWidths(headerwidth3);
			HeaderTable3.setWidthPercentage(95f);
			HeaderTable3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			HeaderTable3.getDefaultCell().setPaddingBottom(7);
			HeaderTable3.getDefaultCell().setPaddingTop(7);

			HeaderTable3.addCell(new Phrase("Name :", header));
			HeaderTable3.addCell(new Phrase("" + clientMaster.getFirstName() + " " + clientMaster.getMiddleName()
					+ " " + clientMaster.getLastName(), tabletext));

			HeaderTable3.addCell(new Phrase("Age :", header));
			HeaderTable3.addCell(new Phrase("" + clientMaster.getClientAge(), tabletext));

			HeaderTable3.addCell(new Phrase("Gender :", header));
			HeaderTable3.addCell(new Phrase("" + clientMaster.getGender(), tabletext));

			HeaderTable3.addCell(new Phrase("Blood Group :", header));
			HeaderTable3.addCell(new Phrase("" + clientMaster.getBloodGroup(), tabletext));

			HeaderTable3.addCell(new Phrase(" ", header));
			HeaderTable3.addCell(new Phrase(" ", header));
			HeaderTable3.addCell(new Phrase(" ", header));
			HeaderTable3.addCell(new Phrase(" ", header));

			document.add(HeaderTable3);
			HeaderTable3.flushContent();

			PdfPTable scaleTable = new PdfPTable(4);
			int[] scaleWidth = { 15, 30, 40, 15 };
			scaleTable.setWidths(scaleWidth);
			scaleTable.setWidthPercentage(95f);
			scaleTable.addCell(new Phrase("High Risk", header));
			scaleTable.addCell(new Phrase("Moderate Risk", header));
			scaleTable.addCell(new Phrase("Healthy", header));
			scaleTable.addCell(new Phrase("Optimum", header));

			document.add(scaleTable);
			scaleTable.flushContent();

			PdfPTable paraTable = new PdfPTable(1);
			int[] paraWidth = { 100 };
			paraTable.setWidths(paraWidth);
			paraTable.setWidthPercentage(95f);
			paraTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			paraTable.setSpacingBefore(30);
			paraTable.setSpacingAfter(30);
			paraTable.addCell(new Phrase(
					"Oh Well!!! "+clientMaster.getFirstName() + " " + clientMaster.getLastName()
					+ " , your current Wellness score of 10 (out of 100) places you in the High Risk category."));
			paraTable.addCell(new Phrase(
					"Your score suggests, you should be consulting your physician for a more detailed check-up and assessment."
							+ " But, the good news is that you cared enough about your health to get started by taking the Health Risk"
							+ " Assessment. There are things you can do to get back on track and climb up the wellness ladder! To take that"
							+ " first step, focus on incorporating the three pillars of health—eating healthy + periodic check-ups + exercising"
							+ " regularly—into your wellness regimen."));
			document.add(paraTable);
			paraTable.flushContent();

			headerCell = new PdfPCell(new Phrase("Health Summary", topHeader));
			headerCell.setBackgroundColor(new BaseColor(72, 178, 26));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			document.add(headerTable);
			headerTable.flushContent();

			PdfPTable HeaderTable4 = new PdfPTable(4);
			int[] headerWidth4 = { 30, 40, 20, 40 };
			HeaderTable4.setWidths(headerWidth4);
			HeaderTable4.setWidthPercentage(95f);
			HeaderTable4.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			HeaderTable4.getDefaultCell().setPaddingBottom(7);
			HeaderTable4.getDefaultCell().setPaddingTop(7);

			HeaderTable4.addCell(new Phrase("Height :", header));
			HeaderTable4.addCell(new Phrase("" + clientMaster.getClientHeight(), tabletext));

			HeaderTable4.addCell(new Phrase("Weight :", header));
			HeaderTable4.addCell(new Phrase("" + clientMaster.getClientWeight(), tabletext));

			HeaderTable4.addCell(new Phrase("BMI :", header));
			HeaderTable4.addCell(new Phrase("31.07", tabletext));

			HeaderTable4.addCell(new Phrase("Observation :", header));
			HeaderTable4.addCell(new Phrase("Obese Level 1", tabletext));

			HeaderTable4.addCell(new Phrase(" ", header));
			HeaderTable4.addCell(new Phrase(" ", header));
			HeaderTable4.addCell(new Phrase(" ", header));
			HeaderTable4.addCell(new Phrase(" ", header));

			document.add(HeaderTable4);
			HeaderTable4.flushContent();

			headerCell = new PdfPCell(new Phrase("Wellness Summary", topHeader));
			headerCell.setBackgroundColor(new BaseColor(206, 16, 41));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			headerTable.setSpacingAfter(15);
			document.add(headerTable);
			headerTable.flushContent();

			PdfPTable wellNessTable = new PdfPTable(5);
			int[] wellNessWidth = { 30, 20, 20, 20, 20 };
			wellNessTable.setWidths(wellNessWidth);
			wellNessTable.setWidthPercentage(95f);
			wellNessTable.getDefaultCell().setPaddingBottom(7);
			wellNessTable.getDefaultCell().setPaddingTop(7);

			wellNessTable.getDefaultCell().setBorder(Rectangle.BOX);

			cell = new PdfPCell(new Phrase("Date", header));
			cell.setBackgroundColor(new BaseColor(109, 108, 155));
			wellNessTable.addCell(cell);

			cell = new PdfPCell(new Phrase("High Risk", header));
			cell.setBackgroundColor(new BaseColor(109, 108, 155));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			wellNessTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Moderate Risk", header));
			cell.setBackgroundColor(new BaseColor(109, 108, 155));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			wellNessTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Healthy", header));
			cell.setBackgroundColor(new BaseColor(109, 108, 155));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			wellNessTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Optimum", header));
			cell.setBackgroundColor(new BaseColor(109, 108, 155));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			wellNessTable.addCell(cell);

			wellNessTable.addCell(new Phrase("07/20/2017", header));
			wellNessTable.addCell(new Phrase("10", header));
			wellNessTable.addCell(new Phrase("-", header));
			wellNessTable.addCell(new Phrase("-", header));
			wellNessTable.addCell(new Phrase("-", header));

			wellNessTable.addCell(new Phrase("07/19/2017", header));
			wellNessTable.addCell(new Phrase("-", header));
			wellNessTable.addCell(new Phrase("22", header));
			wellNessTable.addCell(new Phrase("-", header));
			wellNessTable.addCell(new Phrase("-", header));

			document.add(wellNessTable);
			wellNessTable.flushContent();

			headerCell = new PdfPCell(new Phrase("Health Assessment", topHeader));
			headerCell.setBackgroundColor(new BaseColor(125, 153, 61));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			headerTable.setSpacingBefore(25);
			document.add(headerTable);
			headerTable.flushContent();

			// Main table
			PdfPTable mainTable = new PdfPTable(2);
			mainTable.setWidthPercentage(95f);

			PdfPTable hraTable = new PdfPTable(2);
			PdfPCell hraCell = new PdfPCell();

			for (Map.Entry<Integer, JSONArray> entry : hashMap.entrySet()) {
				String hraTypeName = (String) ((JSONObject) entry.getValue().get(0)).get("hraTypeName");
				String hraPrintFlag = (String) ((JSONObject) entry.getValue().get(0)).get("hraPrintFlag");
				if (hraPrintFlag.equals("Y")) {

					hraTable = new PdfPTable(2);
					hraTable.setWidthPercentage(100f);
					hraTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					hraTable.getDefaultCell().setPaddingBottom(7);
					hraTable.getDefaultCell().setPaddingTop(7);

					cell = new PdfPCell(new Phrase("" + hraTypeName, header));
					cell.setColspan(2);
					cell.setPaddingBottom(5);
					cell.setBorder(PdfPCell.NO_BORDER);
					cell.setBackgroundColor(new BaseColor(25, 112, 61));
					hraTable.addCell(cell);

					for (int i = 0; i < entry.getValue().size(); i++) {
						JSONObject object = (JSONObject) entry.getValue().get(i);
						String question = (String) object.get("question");
						String questionDisplay = (String) object.get("questionDisplay");
						List<OptionMaster> optionSet = (List<OptionMaster>) object.get("optionSet");
						String questionType = (String) object.get("questionType");
						String textAnswer = (String) object.get("textAnswer");

						hraTable.addCell(new Phrase("" + questionDisplay, subheader));
						if (optionSet.size() > 0) {
							StringBuilder answer = new StringBuilder();
							for (int j = 0; j < optionSet.size(); j++) {
								Integer optionId = optionSet.get(j).getOptionId();
								if (textAnswer.contains(optionId.toString())) {
									answer.append(",").append(optionSet.get(j).getOption());
								}
							}
							hraTable.addCell(new Phrase("" + answer.substring(1), tabletext));
						} else {
							hraTable.addCell(new Phrase("" + textAnswer, tabletext));
						}

					}

					hraCell = new PdfPCell();
					hraCell.setBorder(PdfPCell.NO_BORDER);
					hraCell.addElement(hraTable);
					mainTable.addCell(hraCell);

				}

			}

			// Blank table important
			PdfPCell secondTableCell = new PdfPCell();
			secondTableCell.setBorder(PdfPCell.NO_BORDER);
			PdfPTable secondTable = new PdfPTable(2);
			secondTable.setWidthPercentage(100f);
			secondTableCell.addElement(secondTable);
			mainTable.addCell(secondTableCell);

			document.add(mainTable);
			mainTable.flushContent();
			
			headerCell = new PdfPCell(new Phrase("Health Analysis", topHeader));
			headerCell.setBackgroundColor(new BaseColor(89, 13, 15));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			headerTable.setSpacingBefore(25);
			document.add(headerTable);
			headerTable.flushContent();
			
			
			for (Map.Entry<Integer, JSONArray> entry : hashMap.entrySet()) {
				String hraTypeName = (String) ((JSONObject) entry.getValue().get(0)).get("hraTypeName");
				String hraPrintFlag = (String) ((JSONObject) entry.getValue().get(0)).get("hraPrintFlag");
				if (hraPrintFlag.equals("Y")) {

					hraTable = new PdfPTable(2);
					hraTable.setWidthPercentage(95f);
					hraTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					hraTable.getDefaultCell().setPaddingBottom(7);
					hraTable.getDefaultCell().setPaddingTop(7);
					hraTable.setSpacingBefore(15);

					cell = new PdfPCell(new Phrase("" + hraTypeName, header));
					cell.setColspan(2);
					cell.setPaddingBottom(5);
					cell.setBorder(PdfPCell.NO_BORDER);
					cell.setBackgroundColor(new BaseColor(101, 102, 142));
					hraTable.addCell(cell);

					for (int i = 0; i < entry.getValue().size(); i++) {
						JSONObject object = (JSONObject) entry.getValue().get(i);
						String question = (String) object.get("question");
						String questionDisplay = (String) object.get("questionDisplay");
						List<OptionMaster> optionSet = (List<OptionMaster>) object.get("optionSet");
						String questionType = (String) object.get("questionType");
						String textAnswer = (String) object.get("textAnswer");

						cell = new PdfPCell(new Phrase(" "));
						cell.setBorder(PdfPCell.NO_BORDER);
						cell.setColspan(2);
						hraTable.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + questionDisplay, header));
						cell.setPaddingBottom(5);
						cell.setBackgroundColor(new BaseColor(104, 108, 237));
						hraTable.addCell(cell);
						
						if (optionSet.size() > 0) {
							StringBuilder answer = new StringBuilder();
							//StringBuilder reportStatemnt = new StringBuilder("\n \n");
							for (int j = 0; j < optionSet.size(); j++) {
								Integer optionId = optionSet.get(j).getOptionId();
								if (textAnswer.contains(optionId.toString())) {
									answer.append(",").append(optionSet.get(j).getOption());
								}
								
								QuestionReportDetail questionReportDetail = (QuestionReportDetail)optionSet.get(j).getQuestionReportDetail();
								if(questionReportDetail!=null){
									
									//htmlWorker.parse(new StringReader(questionReportDetail.getReportStatement()));
									//reportStatemnt.append(",").append(questionReportDetail.getReportStatement());
									
									JSONObject reportDetail = new JSONObject();
									reportDetail.put("testName", questionReportDetail.getTestName());
									reportDetail.put("frequency", questionReportDetail.getFrequencyNumber()+" "+questionReportDetail.getFrequency());
									reportDetail.put("reason", questionReportDetail.getReason());
									recommendedTestHashMap.put(questionReportDetail.getTestId(), reportDetail);
									
								}
							}
							
							cell = new PdfPCell(new Phrase("" + answer.substring(1), header));
							//cell.setBorder(PdfPCell.NO_BORDER);
							//cell.setBackgroundColor(BaseColor.CYAN);
							hraTable.addCell(cell);
							
							for (int j = 0; j < optionSet.size(); j++) {
								QuestionReportDetail questionReportDetail = (QuestionReportDetail)optionSet.get(j).getQuestionReportDetail();
								if(questionReportDetail!=null){
							java.util.List<Element> elements = HTMLWorker.parseToList(new StringReader(questionReportDetail.getReportStatement()), styleSheet);
				            for (Element element : elements) {
				            	cell = new PdfPCell();
				            	cell.addElement(element);
				            	cell.setColspan(2);
				            	cell.setBorder(PdfPCell.NO_BORDER);
				            	hraTable.addCell(cell);
				            }
				            }
							}
							
							/* cell = new PdfPCell(new Phrase(" " + reportStatemnt, header));
							cell.setColspan(2);
							cell.setBorder(PdfPCell.NO_BORDER);
							hraTable.addCell(cell); */
							
						} else {
							cell = new PdfPCell(new Phrase("" + textAnswer, header));
							//cell.setBorder(PdfPCell.NO_BORDER);
							//cell.setBackgroundColor(BaseColor.CYAN);
							hraTable.addCell(cell);
							
							QuestionReportDetail questionReportDetail = (QuestionReportDetail)object.get("questionReportDetail");
							if(questionReportDetail!=null){
								//htmlWorker.parse(new StringReader(questionReportDetail.getReportStatement()));
								//cell = new PdfPCell(new Phrase("testst "+questionReportDetail.getReportStatement(), header));
				
								java.util.List<Element> elements = HTMLWorker.parseToList(new StringReader(questionReportDetail.getReportStatement()), styleSheet);
					            for (Element element : elements) {
					            	cell.addElement(element);
					            }
								cell.setColspan(2);
								cell.setBorder(PdfPCell.NO_BORDER);
								hraTable.addCell(cell);
								
								JSONObject reportDetail = new JSONObject();
								reportDetail.put("testName", questionReportDetail.getTestName());
								reportDetail.put("frequency", questionReportDetail.getFrequencyNumber()+" "+questionReportDetail.getFrequency());
								reportDetail.put("reason", questionReportDetail.getReason());
								recommendedTestHashMap.put(questionReportDetail.getTestId(), reportDetail);
								
							}
							
						}

					}

					document.add(hraTable);
					hraTable.flushContent();

				}

			}
			

			headerCell = new PdfPCell(new Phrase("Lab Parameters", topHeader));
			headerCell.setBackgroundColor(new BaseColor(168, 30, 46));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			headerTable.setSpacingBefore(25);
			headerTable.setSpacingAfter(0);
			document.add(headerTable);
			headerTable.flushContent();

			for(Entry<TestMaster, JSONArray> test : testHashMap.entrySet()){
				TestMaster testMaster = test.getKey();
				String testDescription = testMaster.getTestDescription();
				
				hraTable = new PdfPTable(2);
				hraTable.setWidthPercentage(95f);
				hraTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				hraTable.getDefaultCell().setPaddingBottom(7);
				hraTable.getDefaultCell().setPaddingTop(7);
				hraTable.setSpacingBefore(15);
				hraTable.setSpacingAfter(10);

				cell = new PdfPCell(new Phrase("" + testDescription, colorText));
				cell.setColspan(2);
				cell.setBorder(PdfPCell.NO_BORDER);
				hraTable.addCell(cell);
				
				JSONArray parameters = test.getValue();
				
				PdfPTable parameterHeader = new PdfPTable(3+visitList.size());
				parameterHeader.setWidthPercentage(95f);
				parameterHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				parameterHeader.getDefaultCell().setPaddingBottom(7);
				parameterHeader.getDefaultCell().setPaddingTop(7);
				
				parameterHeader.addCell(new Phrase("Parameter Name", header));
				for(int i = 0; i < visitList.size(); i++){
					JSONObject visitObject = (JSONObject) visitList.get(i);
					parameterHeader.addCell(new Phrase(""+visitObject.get("visitDate"), header));
				}
				parameterHeader.addCell(new Phrase("Observation", header));
				parameterHeader.addCell(new Phrase("Ref. Range", header));
				
				for(int j = 0; j < parameters.size(); j++){
					JSONObject parameterObject = (JSONObject) parameters.get(j);
					String normalValue = (String) parameterObject.get("normalValue");
					Integer visitId = (Integer) parameterObject.get("visitId");
					
					parameterHeader.addCell(new Phrase(""+parameterObject.get("parameterName"), tabletext));
					for(int i = 0; i < visitList.size(); i++){
						JSONObject visitObject = (JSONObject) visitList.get(i);
						Integer testVisitId = (Integer) visitObject.get("visitId");
						if(visitId.equals(testVisitId)){
							parameterHeader.addCell(new Phrase(""+parameterObject.get("parameterValue"), tabletext));
						}else{
							parameterHeader.addCell(new Phrase(" - ", tabletext));
						}
					}
					
					parameterHeader.addCell(new Phrase(""+parameterObject.get("status"), tabletext));
					if(normalValue.equals("2")){
						parameterHeader.addCell(new Phrase(""+parameterObject.get("generalComment"), tabletext));
					}else{
						parameterHeader.addCell(new Phrase(parameterObject.get("lowerValue")+" - "+parameterObject.get("upperValue")+" "+parameterObject.get("unitName"), tabletext));
					}
					
					ParameterReportDetail parameterReportDetail = (ParameterReportDetail)parameterObject.get("parameterReportDetail");
					if(parameterReportDetail!=null){
						
						JSONObject reportDetail = new JSONObject();
						reportDetail.put("testName", parameterReportDetail.getTestName());
						reportDetail.put("frequency", parameterReportDetail.getFrequencyNumber()+" "+parameterReportDetail.getFrequency());
						reportDetail.put("reason", parameterReportDetail.getReason());
						recommendedTestHashMap.put(parameterReportDetail.getTestId(), reportDetail);
					}
					
				}
				
				document.add(hraTable);
				hraTable.flushContent();
				
				document.add(parameterHeader);
				parameterHeader.flushContent();
				
		    }
			
			headerCell = new PdfPCell(new Phrase("Recommended Lab tests/Check-Ups", topHeader));
			headerCell.setBackgroundColor(new BaseColor(142, 14, 29));
			headerCell.setPaddingBottom(7);
			headerTable.addCell(headerCell);
			headerTable.setSpacingBefore(15);
			document.add(headerTable);
			headerTable.flushContent();
			
			paraTable.addCell(new Phrase(
					"Based on your responses and the identified areas of risks and concerns, we believe, you should undertake the following lab tests/check-ups. These tests would help rule out any risk/conditions."));
			paraTable.setSpacingBefore(0);
			paraTable.setSpacingAfter(10);
			document.add(paraTable);
			paraTable.flushContent();
			
			PdfPTable testTable = new PdfPTable(4);
			int[] testTableWidth = { 10,30,30,50 };
			testTable.setWidths(testTableWidth);
			testTable.setWidthPercentage(95f);
			testTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			//testTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			testTable.getDefaultCell().setPaddingBottom(7);
			testTable.getDefaultCell().setPaddingTop(7);
			
			testTable.addCell(new Phrase("#", header));
			testTable.addCell(new Phrase("Lab Test/Check-Ups", header));
			testTable.addCell(new Phrase("Frequency", header));
			testTable.addCell(new Phrase("Reason", header));
			
			Integer testCount = 1;
			
			for(Entry<Integer, JSONObject> recommendedTest : recommendedTestHashMap.entrySet()){
				JSONObject reportDetail = recommendedTest.getValue();
				
				testTable.addCell(new Phrase(""+testCount, tabletext));
				testTable.addCell(new Phrase(""+reportDetail.get("testName"), tabletext));
				testTable.addCell(new Phrase(""+reportDetail.get("frequency"), tabletext));
				testTable.addCell(new Phrase(""+reportDetail.get("reason"), tabletext));
				testCount++;
			}
			
			document.add(testTable);
			testTable.flushContent();
			
			document.close();
			outStream.close();
			outStream.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

</body>
</html>