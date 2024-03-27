package com.hms.indus.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.ParameterMaster;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.service.CentreService;
import com.hms.indus.service.ClientService;
import com.hms.indus.service.PackageService;
import com.hms.indus.service.ParameterService;
import com.hms.indus.service.TestService;
import com.hms.indus.util.FilePath;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@Controller
@RequestMapping(value = "/ocr")
public class OcrController {

	@Autowired
	ClientService clientService;

	@Autowired
	PackageService packageService;

	@Autowired
	CentreService centreService;

	@Autowired
	TestService testService;

	@Autowired
	ParameterService parameterService;

	private final String UPLOAD_DIRECTORY = FilePath.getBasePath();

	@RequestMapping(value = "/ocrConversionAndSave", method = RequestMethod.POST)
	public @ResponseBody
	void ocrConversionAndSave(HttpSession session,
			@RequestParam("fileName") String fileName,
			@RequestParam("ocrValues") String ocrValues) {
		String[] ids = ocrValues.split("#");
		String packageId = ids[1];
		String testId = ids[0];
		String centerId = ids[2];
		String reportId = ids[3];
		String clientId = session.getAttribute("clientId").toString();
		String textFileName = fileName.substring(0, fileName.lastIndexOf("."))
				+ ".txt";
		if (!testId.equals("0")) {
			getTestAndSaveValue(testId, centerId, clientId, fileName,
					textFileName,reportId);
		} else {
			List<TestMaster> listTestMasters = packageService
					.getTestByPackageId(Integer.parseInt(packageId));
			for (TestMaster testMaster : listTestMasters) {
				testId = testMaster.getTestId().toString();
				getTestAndSaveValue(testId, centerId, clientId, fileName,
						textFileName,reportId);
			}
		}

	}

	public void getTestAndSaveValue(String testId, String centerId,
			String clientId, String fileName, String textFileName,String reportId) {
		List<ParameterMaster> parameterMasters = parameterService
				.getParameterByTestId(Integer.parseInt(testId));
		JSONArray jsonArray = new JSONArray();
		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		// String addedBy=(String)session.getAttribute("userName");
		JSONArray jsonArrayParametervalues = parameterService
				.getParameterValuesByCenterIdAndTestId(
						Integer.parseInt(centerId), Integer.parseInt(testId),
						Integer.parseInt(clientId));
		try {
			PdfReader reader = new PdfReader(UPLOAD_DIRECTORY + File.separator
					+ fileName);
			int n = reader.getNumberOfPages();
			File ocrTextFile = new File(UPLOAD_DIRECTORY + File.separator
					+ textFileName);
			FileOutputStream is = new FileOutputStream(ocrTextFile);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w = new BufferedWriter(osw);
			for (int i = 1; i <= n; i++) {
				String page = PdfTextExtractor.getTextFromPage(reader, i);
				w.write(page);
				// System.out.println("page:"+page);
				String start = null;
				for (int j = 0; j < parameterMasters.size(); j++) {
					// start=parameterMasters.get(j).getParameterName();
					String parameterId = (parameterMasters.get(j)
							.getParameterId()).toString();
					String lowerValue = null;
					String upperValue = null;
					for (int k = 0; k < jsonArrayParametervalues.size(); k++) {
						JSONObject object = (JSONObject) jsonArrayParametervalues
								.get(k);
						String id = (String) object.get("parameterId");
						if (parameterId.equals(id)) {
							start = (String) object.get("parameterName");
							lowerValue = (String) object.get("lowerValue");
							upperValue = (String) object.get("upperValue");
							break;
						} else {
							start = "";
							lowerValue = null;
							upperValue = null;
						}
					}

					String parameterValue = "";
					if (start != null && !start.equals("")) {
						parameterValue = getText(page, start);
					}
					// System.out.println("parameterValue:******"+parameterValue);
					if (!parameterValue.equals("") && parameterValue != null) {
						JSONObject object = new JSONObject();
						// System.out.println(start+"****"+parameterValue+"**"+lowerValue+"**"+upperValue);
						if (page.contains("Test Value") && lowerValue != null
								&& upperValue != null
								&& parameterValue.matches("[0-9]+")
								&& parameterValue.length() > 2) {
							if (Double.parseDouble(parameterValue) < Double
									.parseDouble(lowerValue)) {
								object.put("testResultStatus", "abnormal low");
							}
							if (Double.parseDouble(parameterValue) > Double
									.parseDouble(upperValue)) {
								object.put("testResultStatus", "abnormal high");
							}
							if (Double.parseDouble(parameterValue) >= Double
									.parseDouble(lowerValue)
									&& Double.parseDouble(parameterValue) <= Double
											.parseDouble(upperValue)) {
								object.put("testResultStatus", "normal");
							}
						}
						object.put("parameterId", parameterId);
						object.put("parameterValue", parameterValue);
						object.put("testId", testId);
						object.put("clientId", clientId);
						object.put("addedOn", currentTime);
						object.put("reportId", reportId);
						// object.put("modifyOn", currentTime);
						// object.put("addedBy", addedBy);
						// object.put("modifyBy", addedBy);
						jsonArray.add(object);
					}
				}
			}
			w.close();
		} catch (IOException e) {
			System.err.println("Problem writing to the file txt");
		}
		parameterService.saveTestResult(jsonArray);
	}

	public String getText(String page, String startText) {

		String[] text = startText.split("#@");
		int startIndex = 0;
		int endIndex = 0;
		if (text[0] != null) {
			if (page.contains(text[0])) {
				startIndex = page.indexOf(text[0]) + (text[0]).length() + 1;
			}
			endIndex = startIndex + 10;
			if (text.length > 1) {
				if (text[1] != null) {
					if (page.contains(text[1])) {
						endIndex = page.indexOf(text[1]);
					}
				}
			}

		}

		String parameterValue = "";
		if (startIndex != 0 && endIndex != 0) {
			if (page.contains("Test Value")) {
				parameterValue = page.substring(startIndex, endIndex);
				parameterValue = parameterValue.split(" ")[0];
				parameterValue = parameterValue.split(System
						.getProperty("line.separator"))[0];
				parameterValue = parameterValue.replaceAll("[^0-9.,]+","");
				//System.out.println("parameterValue:"+parameterValue);
			} else {
				parameterValue = page.substring(startIndex + 2, endIndex);
			}
		}
		return parameterValue;
	}

}
