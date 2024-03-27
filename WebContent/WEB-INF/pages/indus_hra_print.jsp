<%@page import="java.text.DateFormat"%>
<%@page import="java.io.StringReader"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="org.apache.poi.poifs.filesystem.Entry"%>
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
<title>HRA Print</title>
</head>

<%
	ClientMaster clientMaster = (ClientMaster) request
			.getAttribute("clientMaster");
	String memberId = clientMaster.getMemberId();
	if (memberId != null && memberId.equals("0")) {
		memberId = "";
	}

	HashMap<Integer, JSONArray> hashMap = (HashMap<Integer, JSONArray>) request
			.getAttribute("hashMap");

	//For todays date
	java.util.Date date = new java.util.Date();
	DateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	String todaysDate = simpleDateFormat.format(date);
%>

<body>
	<%
		try {
			ServletOutputStream outStream = response.getOutputStream();
			//OutputStream outStream = new FileOutputStream(new File(FilePath.getBasePath() + File.separator + "PrescriptionPrint.pdf"));
			response.reset();
			response.setContentType("application/pdf");

			float left = 20;
			float right = 20;
			float firstPageTop = 110;
			float top = 40;
			float bottom = 60;
			Document document = new Document(PageSize.A4, left, right,
					firstPageTop, bottom);
			PdfWriter writer = PdfWriter.getInstance(document, outStream);
			document.open();
			document.setMargins(left, right, top, bottom);

			//font
			Font topHeader = new Font(Font.FontFamily.HELVETICA, 12,
					Font.BOLD);
			Font header = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
			Font subheadertitle = new Font(Font.FontFamily.HELVETICA, 9,
					Font.BOLD);
			Font subheader = new Font(Font.FontFamily.HELVETICA, 8,
					Font.BOLD);
			Font footer = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
			header.setColor(10, 4, 2);

			Font tableheader = new Font(Font.FontFamily.HELVETICA, 10,
					Font.BOLD);
			Font tabletext = new Font(Font.FontFamily.HELVETICA, 8,
					Font.NORMAL);
			Font small = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

			FontSelector selector = new FontSelector();
			selector.addFont(subheader);

			PdfPTable HeaderTable1 = new PdfPTable(6);
			int[] headerwidth1 = { 10, 40, 40, 30, 10, 20 };
			HeaderTable1.setWidths(headerwidth1);
			HeaderTable1.setWidthPercentage(95f);
			HeaderTable1.setHorizontalAlignment(Element.ALIGN_CENTER);
			HeaderTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			// spacing
			HeaderTable1.addCell(new Phrase("", header));
			HeaderTable1.addCell(new Phrase("", header));
			HeaderTable1.addCell(new Phrase("PRE-COUNSELLING", topHeader));
			HeaderTable1.addCell(new Phrase("", header));
			HeaderTable1.addCell(new Phrase("Date:", header));
			HeaderTable1.addCell(new Phrase("" + todaysDate, header));

			document.add(HeaderTable1);
			HeaderTable1.flushContent();

			HeaderTable1.getDefaultCell().setBorder(Rectangle.BOTTOM);
			HeaderTable1.addCell(new Phrase(" ", header));
			HeaderTable1.addCell(new Phrase(" ", header));
			HeaderTable1.addCell(new Phrase(" ", header));
			HeaderTable1.addCell(new Phrase(" ", header));
			HeaderTable1.addCell(new Phrase(" ", header));
			HeaderTable1.addCell(new Phrase(" ", header));

			document.add(HeaderTable1);
			HeaderTable1.flushContent();

			PdfPTable HeaderTable2 = new PdfPTable(6);
			int[] headerwidth2 = { 20, 30, 20, 30, 20, 30 };
			HeaderTable2.setWidths(headerwidth2);
			HeaderTable2.setWidthPercentage(95f);
			HeaderTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			HeaderTable2.addCell(new Phrase("Client Name :", header));
			HeaderTable2.addCell(new Phrase(""
					+ clientMaster.getFirstName() + " "
					+ clientMaster.getMiddleName() + " "
					+ clientMaster.getLastName(), tabletext));
			HeaderTable2.addCell(new Phrase("Client Id :", header));
			HeaderTable2.addCell(new Phrase(
					"" + clientMaster.getClientId(), tabletext));
			HeaderTable2.addCell(new Phrase("Member Id :", header));
			HeaderTable2.addCell(new Phrase("" + memberId, tabletext));

			document.add(HeaderTable2);
			HeaderTable2.flushContent();

			HeaderTable2.getDefaultCell().setBorder(Rectangle.BOTTOM);
			HeaderTable2.addCell(new Phrase("", header));
			HeaderTable2.addCell(new Phrase("", header));
			HeaderTable2.addCell(new Phrase("", header));
			HeaderTable2.addCell(new Phrase("", header));
			HeaderTable2.addCell(new Phrase("", header));
			HeaderTable2.addCell(new Phrase("", header));

			document.add(HeaderTable2);
			HeaderTable2.flushContent();

			PdfPTable historyHeader = new PdfPTable(2);
			int[] historyHeaderWidth = { 130, 0 };
			historyHeader.setWidths(historyHeaderWidth);
			historyHeader.setWidthPercentage(95f);
			historyHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			historyHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			historyHeader.addCell(new Phrase("", header));
			historyHeader.addCell(new Phrase("", header));

			document.add(historyHeader);
			historyHeader.flushContent();

			historyHeader.addCell(new Phrase("", header));
			historyHeader.addCell(new Phrase("", header));

			document.add(historyHeader);
			historyHeader.flushContent();

			for (Map.Entry<Integer, JSONArray> entry : hashMap.entrySet()) {
				String hraTypeName = (String) ((JSONObject) entry
						.getValue().get(0)).get("hraTypeName");
				String hraPrintFlag = (String) ((JSONObject) entry
						.getValue().get(0)).get("hraPrintFlag");
				if (hraPrintFlag.equals("Y")) {
					historyHeader.addCell(new Phrase("" + hraTypeName,
							header));
					historyHeader.addCell(new Phrase("", header));

					for (int i = 0; i < entry.getValue().size(); i++) {
						JSONObject object = (JSONObject) entry.getValue()
								.get(i);
						String question = (String) object.get("question");
						List<OptionMaster> optionSet = (List<OptionMaster>) object
								.get("optionSet");
						String questionType = (String) object
								.get("questionType");
						String textAnswer = (String) object
								.get("textAnswer");

						historyHeader.addCell(new Phrase((i + 1) + ". "
								+ question, subheader));
						historyHeader.addCell(new Phrase("", header));

						if (optionSet.size() > 0) {
							for (int j = 0; j < optionSet.size(); j++) {

								Integer optionId = optionSet.get(j)
										.getOptionId();
								if (textAnswer
										.contains(optionId.toString())) {
									historyHeader.addCell(new Phrase(
											"-    "
													+ optionSet.get(j)
															.getOption(),
											tabletext));
									historyHeader.addCell(new Phrase("",
											header));
								}

								//for displaying all options
								/* historyHeader.addCell(new Phrase((j + 1) + "."
										+ optionSet.get(j).getOption(),
										tabletext));
								historyHeader.addCell(new Phrase("", header)); */

							}
						} else {
							historyHeader.addCell(new Phrase("-    "
									+ textAnswer, tabletext));
							historyHeader.addCell(new Phrase("", header));
						}

						historyHeader.addCell(new Phrase("", tabletext));
						historyHeader.addCell(new Phrase("", header));

					}
				}
				historyHeader.addCell(new Phrase(" ", tabletext));
				historyHeader.addCell(new Phrase(" ", header));

			}

			document.add(historyHeader);
			historyHeader.flushContent();
			
			//New Page---------------------------------------------
			document.newPage();
			historyHeader.addCell(new Phrase(
					"                                                 ---------------- For Center Doctor's Use Only ---------------- ", header));
			historyHeader.addCell(new Phrase("", header));
			historyHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);
			historyHeader.addCell(new Phrase(" ", header));
			historyHeader.addCell(new Phrase(" ", header));

			document.add(historyHeader);
			historyHeader.flushContent();
			
			HeaderTable2.addCell(new Phrase("Client Name :", header));
			HeaderTable2.addCell(new Phrase(""
					+ clientMaster.getFirstName() + " "
					+ clientMaster.getMiddleName() + " "
					+ clientMaster.getLastName(), tabletext));
			HeaderTable2.addCell(new Phrase("Client Id :", header));
			HeaderTable2.addCell(new Phrase(
					"" + clientMaster.getClientId(), tabletext));
			HeaderTable2.addCell(new Phrase("Member Id :", header));
			HeaderTable2.addCell(new Phrase("" + memberId, tabletext));

			document.add(HeaderTable2);
			HeaderTable2.flushContent();
			
			PdfPTable examinationHeader = new PdfPTable(4);
			int[] examinationHeaderWidth = { 50,40,40,40 };
			examinationHeader.setWidths(examinationHeaderWidth);
			examinationHeader.setWidthPercentage(95f);
			examinationHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			examinationHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			examinationHeader.addCell(new Phrase("Physical Examination :", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			examinationHeader.addCell(new Phrase("1. Height :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("2. Weight :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("3. General Conditions :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("4. Heart Rate :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("5. Blood Pressure :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("6. Pallor :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("7. Odema :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("8. Skin :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("9. Hair :", header));
			examinationHeader.addCell(new Phrase("_________________", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			examinationHeader.addCell(new Phrase("10. CVS :", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			//For Border line
			examinationHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			//For No Border line
			examinationHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			examinationHeader.addCell(new Phrase("11. RS :", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			//For Border line
			examinationHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			//For No Border line
			examinationHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			examinationHeader.addCell(new Phrase("12. CNS :", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			//For Border line
			examinationHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			//For No Border line
			examinationHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			examinationHeader.addCell(new Phrase("13. PA :", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			for (int i = 0; i < 2; i++) {
				examinationHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);
				examinationHeader.addCell(new Phrase(" ", header));
				examinationHeader.addCell(new Phrase(" ", header));
				examinationHeader.addCell(new Phrase(" ", header));
				examinationHeader.addCell(new Phrase(" ", header));
			}
			
			document.add(examinationHeader);
			examinationHeader.flushContent();
			
			//document.add(new Paragraph(" "));
			
			examinationHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			examinationHeader.addCell(new Phrase(" ", header));
			
			examinationHeader.addCell(new Phrase("14. Any Other Comments :", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			examinationHeader.addCell(new Phrase("", header));
			
			for (int i = 0; i < 4; i++) {
				examinationHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);
				examinationHeader.addCell(new Phrase(" ", header));
				examinationHeader.addCell(new Phrase(" ", header));
				examinationHeader.addCell(new Phrase(" ", header));
				examinationHeader.addCell(new Phrase(" ", header));
			}
			
			document.add(examinationHeader);
			examinationHeader.flushContent();

			// add a couple of blank lines
			document.add(new Paragraph(" "));
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			PdfPTable signatureHeader = new PdfPTable(2);
			int[] signatureHeaderWidth = { 100, 30 };
			signatureHeader.setWidths(signatureHeaderWidth);
			signatureHeader.setWidthPercentage(95f);
			signatureHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			signatureHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			signatureHeader.addCell(new Phrase("", tabletext));
			signatureHeader.addCell(new Phrase("Authorized Signatory",
					header));

			document.add(signatureHeader);
			signatureHeader.flushContent();
			
			document.add(new Paragraph(" "));
			
			signatureHeader.addCell(new Phrase("*Note : Upload this document to EHR", header));
			signatureHeader.addCell(new Phrase("",header));

			document.add(signatureHeader);
			signatureHeader.flushContent();

			document.close();
			outStream.close();
			outStream.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

</body>
</html>