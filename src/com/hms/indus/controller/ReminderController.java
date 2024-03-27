package com.hms.indus.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.EmailRecord;
import com.hms.indus.bo.SMSRecord;
import com.hms.indus.service.ClientService;
import com.hms.indus.util.CallSmscApi;
import com.hms.indus.util.SSLEmail;

@Controller
@RequestMapping(value = "/reminder")
public class ReminderController {

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/saveReminder", method = RequestMethod.POST)
	public @ResponseBody
	String saveReminder(HttpSession session,
			@RequestParam("eventTitle") String eventTitle,
			@RequestParam("emailId") String emailId,
			@RequestParam("mobileNo") String mobileNo,
			@RequestParam("category") String category,
			@RequestParam("doctorName") String doctorName,
			@RequestParam("eventStartDate") String eventStartDate,
			@RequestParam("eventEndDate") String eventEndDate,
			@RequestParam("reminderDateTime") String reminderDateTime,
			@RequestParam("doctorDateTime") String doctorDateTime,
			@RequestParam("medicineName") String medicineName,
			@RequestParam("medicineDose") String medicineDose,
			@RequestParam("medicineDateTime") String medicineDateTime,
			@RequestParam("typeOfExercise") String typeOfExercise,
			@RequestParam("durationInMinutes") String durationInMinutes,
			@RequestParam("testName") String testName,
			@RequestParam("centreName") String centreName,
			@RequestParam("remindMeFor") String remindMeFor,
			@RequestParam("location") String location,
			@RequestParam("duration") String duration,
			@RequestParam("reminders[]") String[] reminders,
			@RequestParam("ehrReminderMasterId") String ehrReminderMasterId,
			@RequestParam("dailyRepeat") Integer dailyRepeat,
			@RequestParam("weeklyRepeat") Integer weeklyRepeat,
			@RequestParam("monthlyRepeat") Integer monthlyRepeat,
			@RequestParam("repeatBy") String repeatBy,
			@RequestParam("yearlyRepeat") Integer yearlyRepeat,
			@RequestParam("ends") String ends,
			@RequestParam("weeklyDays[]") String[] weeklyDays,
			@RequestParam("afterText") Integer afterText,
			@RequestParam("onText") String onText,
			@RequestParam("recurrencePattern") String recurrencePattern,
			@RequestParam("clientName") String clientName,
			@RequestParam("clientId") Integer clientId) {

		// System.out.println("remindes"+reminders.length);
		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy = "";
		if(session!=null && session.getAttribute("userName")!=null) {
			addedBy = (String) session.getAttribute("userName");
		} else {
			addedBy = "self";
		}
		/*Integer clientId = Integer.parseInt(session.getAttribute("clientId")
				.toString());*/
		JSONArray jsonArray = new JSONArray();
		if (recurrencePattern.equals("1")) {
			for (int i = 0; i < reminders.length; i++) {
				if (!reminders[i].equals("")) {
					JSONObject object = new JSONObject();
					object.put("addedBy", addedBy);
					object.put("addedOn", currentTime);
					object.put("clientId", clientId);
					object.put("emailId", emailId);
					object.put("mobileNo", mobileNo);
					object.put("category", category);
					object.put("eventTitle", eventTitle);
					object.put("eventStartDate", eventStartDate);
					object.put("eventEndDate", eventEndDate);
					object.put("doctorName", doctorName);
					object.put("doctorDateTime", doctorDateTime);
					object.put("medicineName", medicineName);
					object.put("medicineDose", medicineDose);
					object.put("medicineDateTime", medicineDateTime);
					object.put("typeOfExercise", typeOfExercise);
					object.put("durationInMinutes", durationInMinutes);
					object.put("testName", testName);
					object.put("centreName", centreName);
					object.put("remindMeFor", remindMeFor);
					object.put("location", location);
					object.put("duration", duration);
					object.put("reminderDateTime", reminders[i]);
					object.put("recurrencePattern", reminderDateTime);
					object.put("clientName", clientName);
					jsonArray.add(object);
				}
			}
		} else {
			// object.put("reminderMasterId", ehrReminderMasterId);
			if (ehrReminderMasterId.equals("1")) {
				// System.out.println(dailyRepeat+"$$"+ends+"$$"+afterText+"$$"+onText);
				// object.put("recurrencePattern",
				// dailyRepeat+"$$"+ends+"$$"+afterText+"$$"+onText);
				if (ends.equals("1")) {
					// Integer day=0;
					for (int i = 0; i <= 365; i = i + dailyRepeat) {
						JSONObject object = new JSONObject();
						object.put("addedBy", addedBy);
						object.put("addedOn", currentTime);
						object.put("clientId", clientId);
						object.put("emailId", emailId);
						object.put("mobileNo", mobileNo);
						object.put("category", category);
						object.put("eventTitle", eventTitle);
						object.put("doctorName", doctorName);
						object.put("doctorDateTime", doctorDateTime);
						object.put("medicineName", medicineName);
						object.put("medicineDose", medicineDose);
						object.put("medicineDateTime", medicineDateTime);
						object.put("typeOfExercise", typeOfExercise);
						object.put("durationInMinutes", durationInMinutes);
						object.put("testName", testName);
						object.put("centreName", centreName);
						object.put("remindMeFor", remindMeFor);
						object.put("location", location);
						object.put("duration", duration);
						object.put("recurrencePattern", recurrencePattern);
						object.put("clientName", clientName);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						Calendar calendar = Calendar.getInstance();
						Calendar calendar2 = Calendar.getInstance();
						try {
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.DATE, i);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.DATE, i);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						jsonArray.add(object);
					}
				} else if (ends.equals("2")) {
					Integer days = 0;
					for (int i = 0; i < afterText; i++) {
						JSONObject object = new JSONObject();
						object.put("addedBy", addedBy);
						object.put("addedOn", currentTime);
						object.put("clientId", clientId);
						object.put("emailId", emailId);
						object.put("mobileNo", mobileNo);
						object.put("category", category);
						object.put("eventTitle", eventTitle);
						object.put("doctorName", doctorName);
						object.put("doctorDateTime", doctorDateTime);
						object.put("medicineName", medicineName);
						object.put("medicineDose", medicineDose);
						object.put("medicineDateTime", medicineDateTime);
						object.put("typeOfExercise", typeOfExercise);
						object.put("durationInMinutes", durationInMinutes);
						object.put("testName", testName);
						object.put("centreName", centreName);
						object.put("remindMeFor", remindMeFor);
						object.put("location", location);
						object.put("duration", duration);
						object.put("recurrencePattern", recurrencePattern);
						object.put("clientName", clientName);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						Calendar calendar = Calendar.getInstance();
						Calendar calendar2 = Calendar.getInstance();
						try {
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.DATE, days);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.DATE, days);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
							days = days + dailyRepeat;
						} catch (ParseException e) {
							e.printStackTrace();
						}
						jsonArray.add(object);
					}
				} else {
					try {
						String startDateSplit = eventStartDate.split(" ")[0];
						String untilDate = onText.split(" ")[0];
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MM/dd/yyyy");
						Date date1 = sdf.parse(startDateSplit);
						Date date2 = sdf.parse(untilDate);
						int diffInDays = (int) ((date2.getTime() - date1
								.getTime()) / (1000 * 60 * 60 * 24));
						for (int i = 0; i <= diffInDays; i = i + dailyRepeat) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.DATE, i);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.DATE, i);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
			} else if (ehrReminderMasterId.equals("2")) {
				if (ends.equals("1")) {
					// Integer day=0;
					if (weeklyDays.length > 0) {
						String splitStartDate = eventStartDate.split(" ")[0];
						// System.out.println("splitStartDate:"+splitStartDate);
						int year = Integer
								.parseInt(splitStartDate.split("/")[2]);
						int month = Integer
								.parseInt(splitStartDate.split("/")[0]) - 1;
						int currentStartDate = Integer.parseInt(splitStartDate
								.split("/")[1]);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						if (Arrays.toString(weeklyDays).contains("SUNDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("MONDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("TUESDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("WEDNESDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("THURSDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("FRIDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("SATURDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0, inc = 1; i < 366; i += inc) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									inc = 7 * weeklyRepeat;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

					} else {
						for (int i = 0; i <= 365; i = i + weeklyRepeat) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							try {
								String startDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventStartDate));
								String endDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventEndDate));
								calendar.setTime(simpleDateFormat2
										.parse(startDate));
								calendar.add(Calendar.WEEK_OF_YEAR, i);
								String reminderDate = simpleDateFormat2
										.format(calendar.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								calendar2.setTime(simpleDateFormat2
										.parse(endDate));
								calendar2.add(Calendar.WEEK_OF_YEAR, i);
								String reminderEndDate = simpleDateFormat2
										.format(calendar2.getTime());
								object.put("eventEndDate", reminderEndDate);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							jsonArray.add(object);
						}
					}
				} else if (ends.equals("2")) {
					if (weeklyDays.length > 0) {
						String splitStartDate = eventStartDate.split(" ")[0];
						// System.out.println("splitStartDate:"+splitStartDate);
						int year = Integer
								.parseInt(splitStartDate.split("/")[2]);
						int month = Integer
								.parseInt(splitStartDate.split("/")[0]) - 1;
						int currentStartDate = Integer.parseInt(splitStartDate
								.split("/")[1]);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						if (Arrays.toString(weeklyDays).contains("SUNDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("MONDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("TUESDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("WEDNESDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("THURSDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("FRIDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

						if (Arrays.toString(weeklyDays).contains("SATURDAY")) {
							Calendar cal = new GregorianCalendar(year, month,
									currentStartDate);
							for (int i = 0; i < afterText;) {
								if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
									JSONObject object = new JSONObject();
									object.put("addedBy", addedBy);
									object.put("addedOn", currentTime);
									object.put("clientId", clientId);
									object.put("emailId", emailId);
									object.put("mobileNo", mobileNo);
									object.put("category", category);
									object.put("eventTitle", eventTitle);
									object.put("doctorName", doctorName);
									object.put("doctorDateTime", doctorDateTime);
									object.put("medicineName", medicineName);
									object.put("medicineDose", medicineDose);
									object.put("medicineDateTime",
											medicineDateTime);
									object.put("typeOfExercise", typeOfExercise);
									object.put("durationInMinutes",
											durationInMinutes);
									object.put("testName", testName);
									object.put("centreName", centreName);
									object.put("remindMeFor", remindMeFor);
									object.put("location", location);
									object.put("duration", duration);
									object.put("recurrencePattern", recurrencePattern);
									object.put("clientName", clientName);
									String reminderDate = simpleDateFormat2
											.format(cal.getTime());
									object.put("reminderDateTime", reminderDate);
									object.put("eventStartDate", reminderDate);
									Calendar calendar = Calendar.getInstance();
									try {
										calendar.setTime(simpleDateFormat2
												.parse(reminderDate));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									calendar.add(Calendar.DATE, 1);
									String reminderEndDate = simpleDateFormat2
											.format(calendar.getTime());
									object.put("eventEndDate", reminderEndDate);
									jsonArray.add(object);
									cal.add(Calendar.DAY_OF_MONTH,
											7 * weeklyRepeat);
									i++;
								} else {
									cal.add(Calendar.DAY_OF_MONTH, 1);
								}
							}
						}

					} else {
						Integer days = 0;
						for (int i = 0; i < afterText; i++) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							try {
								String startDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventStartDate));
								String endDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventEndDate));
								calendar.setTime(simpleDateFormat2
										.parse(startDate));
								calendar.add(Calendar.WEEK_OF_YEAR, days);
								String reminderDate = simpleDateFormat2
										.format(calendar.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								calendar2.setTime(simpleDateFormat2
										.parse(endDate));
								calendar2.add(Calendar.WEEK_OF_YEAR, days);
								String reminderEndDate = simpleDateFormat2
										.format(calendar2.getTime());
								object.put("eventEndDate", reminderEndDate);
								days = days + weeklyRepeat;
							} catch (ParseException e) {
								e.printStackTrace();
							}
							jsonArray.add(object);
						}
					}
				} else {
					try {
						String startDateSplit = eventStartDate.split(" ")[0];
						String untilDate = onText.split(" ")[0];
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MM/dd/yyyy");
						Date date1 = sdf.parse(startDateSplit);
						Date date2 = sdf.parse(untilDate);
						int diffInDays = (int) ((date2.getTime() - date1
								.getTime()) / (1000 * 60 * 60 * 24));
						int diffInWeeks = diffInDays / 7;
						if (weeklyDays.length > 0) {
							String splitStartDate = eventStartDate.split(" ")[0];
							// System.out.println("splitStartDate:"+splitStartDate);
							int year = Integer.parseInt(splitStartDate
									.split("/")[2]);
							int month = Integer.parseInt(splitStartDate
									.split("/")[0]) - 1;
							int currentStartDate = Integer
									.parseInt(splitStartDate.split("/")[1]);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							if (Arrays.toString(weeklyDays).contains("SUNDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

							if (Arrays.toString(weeklyDays).contains("MONDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

							if (Arrays.toString(weeklyDays).contains("TUESDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

							if (Arrays.toString(weeklyDays).contains(
									"WEDNESDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

							if (Arrays.toString(weeklyDays)
									.contains("THURSDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

							if (Arrays.toString(weeklyDays).contains("FRIDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

							if (Arrays.toString(weeklyDays)
									.contains("SATURDAY")) {
								Calendar cal = new GregorianCalendar(year,
										month, currentStartDate);
								for (int i = 0; i <= diffInWeeks;) {
									if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
										JSONObject object = new JSONObject();
										object.put("addedBy", addedBy);
										object.put("addedOn", currentTime);
										object.put("clientId", clientId);
										object.put("emailId", emailId);
										object.put("mobileNo", mobileNo);
										object.put("category", category);
										object.put("eventTitle", eventTitle);
										object.put("doctorName", doctorName);
										object.put("doctorDateTime",
												doctorDateTime);
										object.put("medicineName", medicineName);
										object.put("medicineDose", medicineDose);
										object.put("medicineDateTime",
												medicineDateTime);
										object.put("typeOfExercise",
												typeOfExercise);
										object.put("durationInMinutes",
												durationInMinutes);
										object.put("testName", testName);
										object.put("centreName", centreName);
										object.put("remindMeFor", remindMeFor);
										object.put("location", location);
										object.put("duration", duration);
										object.put("recurrencePattern", recurrencePattern);
										object.put("clientName", clientName);
										String reminderDate = simpleDateFormat2
												.format(cal.getTime());
										object.put("reminderDateTime",
												reminderDate);
										object.put("eventStartDate",
												reminderDate);
										Calendar calendar = Calendar
												.getInstance();
										try {
											calendar.setTime(simpleDateFormat2
													.parse(reminderDate));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										calendar.add(Calendar.DATE, 1);
										String reminderEndDate = simpleDateFormat2
												.format(calendar.getTime());
										object.put("eventEndDate",
												reminderEndDate);
										Date date3 = sdf.parse(reminderDate
												.split(" ")[0]);
										int checkUntilDate = (int) ((date2
												.getTime() - date3.getTime()) / (1000 * 60 * 60 * 24));
										if (checkUntilDate > 0) {
											jsonArray.add(object);
										}
										cal.add(Calendar.DAY_OF_MONTH,
												7 * weeklyRepeat);
										i++;
									} else {
										cal.add(Calendar.DAY_OF_MONTH, 1);
									}
								}
							}

						} else {
							for (int i = 0; i <= diffInWeeks; i = i
									+ weeklyRepeat) {
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US);
								Calendar calendar = Calendar.getInstance();
								Calendar calendar2 = Calendar.getInstance();
								String startDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventStartDate));
								String endDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventEndDate));
								calendar.setTime(simpleDateFormat2
										.parse(startDate));
								calendar.add(Calendar.WEEK_OF_YEAR, i);
								String reminderDate = simpleDateFormat2
										.format(calendar.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								calendar2.setTime(simpleDateFormat2
										.parse(endDate));
								calendar2.add(Calendar.WEEK_OF_YEAR, i);
								String reminderEndDate = simpleDateFormat2
										.format(calendar2.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
			} else if (ehrReminderMasterId.equals("3")) {
				System.out.println(monthlyRepeat + "$$" + repeatBy + "$$"
						+ ends + "$$" + afterText + "$$" + onText);
				if (ends.equals("1")) {
					// Integer day=0;
					if (repeatBy.equals("1")) {
						for (int i = 0; i <= 12; i = i + monthlyRepeat) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							try {
								String startDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventStartDate));
								String endDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventEndDate));
								calendar.setTime(simpleDateFormat2
										.parse(startDate));
								calendar.add(Calendar.MONTH, i);
								String reminderDate = simpleDateFormat2
										.format(calendar.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								calendar2.setTime(simpleDateFormat2
										.parse(endDate));
								calendar2.add(Calendar.MONTH, i);
								String reminderEndDate = simpleDateFormat2
										.format(calendar2.getTime());
								object.put("eventEndDate", reminderEndDate);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							jsonArray.add(object);
						}
					} else {
						String splitStartDate = eventStartDate.split(" ")[0];
						// System.out.println("splitStartDate:"+splitStartDate);
						int year = Integer
								.parseInt(splitStartDate.split("/")[2]);
						int month = Integer
								.parseInt(splitStartDate.split("/")[0]) - 1;
						int currentStartDate = Integer.parseInt(splitStartDate
								.split("/")[1]);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						Calendar cal = new GregorianCalendar(year, month,
								currentStartDate);
						int week = cal.get(Calendar.WEEK_OF_MONTH);
						// System.out.println("week:*******"+cal.get(Calendar.WEEK_OF_MONTH));
						if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK,
										Calendar.WEDNESDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
							for (int i = 0; i < 12; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

					}
				} else if (ends.equals("2")) {
					if (repeatBy.equals("1")) {
						Integer days = 0;
						for (int i = 0; i < afterText; i++) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							try {
								String startDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventStartDate));
								String endDate = new SimpleDateFormat(
										"MM/dd/yyyy hh:mm aa", Locale.US)
										.format(simpleDateFormat2
												.parse(eventEndDate));
								calendar.setTime(simpleDateFormat2
										.parse(startDate));
								calendar.add(Calendar.MONTH, days);
								String reminderDate = simpleDateFormat2
										.format(calendar.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								calendar2.setTime(simpleDateFormat2
										.parse(endDate));
								calendar2.add(Calendar.MONTH, days);
								String reminderEndDate = simpleDateFormat2
										.format(calendar2.getTime());
								object.put("eventEndDate", reminderEndDate);
								days = days + monthlyRepeat;
							} catch (ParseException e) {
								e.printStackTrace();
							}
							jsonArray.add(object);
						}
					} else {
						String splitStartDate = eventStartDate.split(" ")[0];
						// System.out.println("splitStartDate:"+splitStartDate);
						int year = Integer
								.parseInt(splitStartDate.split("/")[2]);
						int month = Integer
								.parseInt(splitStartDate.split("/")[0]) - 1;
						int currentStartDate = Integer.parseInt(splitStartDate
								.split("/")[1]);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						Calendar cal = new GregorianCalendar(year, month,
								currentStartDate);
						int week = cal.get(Calendar.WEEK_OF_MONTH);
						// System.out.println("week:*******"+cal.get(Calendar.WEEK_OF_MONTH));
						if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK,
										Calendar.WEDNESDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}

						else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
							for (int i = 0; i < afterText; i++) {
								// Calendar calendar = Calendar.getInstance();
								cal.set(year, month, currentStartDate);
								cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
								cal.set(Calendar.WEEK_OF_MONTH, week);
								month = month + monthlyRepeat;
								JSONObject object = new JSONObject();
								object.put("addedBy", addedBy);
								object.put("addedOn", currentTime);
								object.put("clientId", clientId);
								object.put("emailId", emailId);
								object.put("mobileNo", mobileNo);
								object.put("category", category);
								object.put("eventTitle", eventTitle);
								object.put("doctorName", doctorName);
								object.put("doctorDateTime", doctorDateTime);
								object.put("medicineName", medicineName);
								object.put("medicineDose", medicineDose);
								object.put("medicineDateTime", medicineDateTime);
								object.put("typeOfExercise", typeOfExercise);
								object.put("durationInMinutes",
										durationInMinutes);
								object.put("testName", testName);
								object.put("centreName", centreName);
								object.put("remindMeFor", remindMeFor);
								object.put("location", location);
								object.put("duration", duration);
								object.put("recurrencePattern", recurrencePattern);
								object.put("clientName", clientName);
								String reminderDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("reminderDateTime", reminderDate);
								object.put("eventStartDate", reminderDate);
								// Calendar calendar = Calendar.getInstance();
								try {
									cal.setTime(simpleDateFormat2
											.parse(reminderDate));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								cal.add(Calendar.DATE, 1);
								String reminderEndDate = simpleDateFormat2
										.format(cal.getTime());
								object.put("eventEndDate", reminderEndDate);
								jsonArray.add(object);
							}
						}
					}
				} else {
					String startDateSplit = eventStartDate.split(" ")[0];
					String untilDate = onText.split(" ")[0];
					SimpleDateFormat sdf = new SimpleDateFormat(
							"MM/dd/yyyy");
					Date date1 = null;
					Date date2 = null;
					try {
						date1 = sdf.parse(startDateSplit);
						date2 = sdf.parse(untilDate);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					int diffInDays = (int) ((date2.getTime() - date1
							.getTime()) / (1000 * 60 * 60 * 24));
					int diffInWeeks = diffInDays / 7;
					int diffInMonths = diffInWeeks / 4;
					if (repeatBy.equals("1")) {
					try {
						for (int i = 0; i <= diffInMonths; i = i
								+ monthlyRepeat) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.MONTH, i);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.MONTH, i);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
				else{
					String splitStartDate = eventStartDate.split(" ")[0];
					// System.out.println("splitStartDate:"+splitStartDate);
					int year = Integer
							.parseInt(splitStartDate.split("/")[2]);
					int month = Integer
							.parseInt(splitStartDate.split("/")[0]) - 1;
					int currentStartDate = Integer.parseInt(splitStartDate
							.split("/")[1]);
					SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
							"MM/dd/yyyy hh:mm aa", Locale.US);
					Calendar cal = new GregorianCalendar(year, month,
							currentStartDate);
					int week = cal.get(Calendar.WEEK_OF_MONTH);
					//System.out.println("week:*******"+cal.get(Calendar.WEEK_OF_MONTH));
					if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}

					else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}

					else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}

					else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK,
									Calendar.WEDNESDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}

					else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}

					else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}

					else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
						for (int i = 0; i < diffInMonths; i++) {
							// Calendar calendar = Calendar.getInstance();
							cal.set(year, month, currentStartDate);
							cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
							cal.set(Calendar.WEEK_OF_MONTH, week);
							month = month + monthlyRepeat;
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes",
									durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							String reminderDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							// Calendar calendar = Calendar.getInstance();
							try {
								cal.setTime(simpleDateFormat2
										.parse(reminderDate));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							cal.add(Calendar.DATE, 1);
							String reminderEndDate = simpleDateFormat2
									.format(cal.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					}
				}
				}
			} else {
				System.out.println(yearlyRepeat + "$$" + ends + "$$"
						+ afterText + "$$" + onText);
				if (ends.equals("1")) {
					// Integer day=0;
					for (int i = 0; i < 3; i = i + yearlyRepeat) {
						JSONObject object = new JSONObject();
						object.put("addedBy", addedBy);
						object.put("addedOn", currentTime);
						object.put("clientId", clientId);
						object.put("emailId", emailId);
						object.put("mobileNo", mobileNo);
						object.put("category", category);
						object.put("eventTitle", eventTitle);
						object.put("doctorName", doctorName);
						object.put("doctorDateTime", doctorDateTime);
						object.put("medicineName", medicineName);
						object.put("medicineDose", medicineDose);
						object.put("medicineDateTime", medicineDateTime);
						object.put("typeOfExercise", typeOfExercise);
						object.put("durationInMinutes", durationInMinutes);
						object.put("testName", testName);
						object.put("centreName", centreName);
						object.put("remindMeFor", remindMeFor);
						object.put("location", location);
						object.put("duration", duration);
						object.put("recurrencePattern", recurrencePattern);
						object.put("clientName", clientName);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						Calendar calendar = Calendar.getInstance();
						Calendar calendar2 = Calendar.getInstance();
						try {
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.YEAR, i);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.YEAR, i);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						jsonArray.add(object);
					}
				} else if (ends.equals("2")) {
					Integer days = 0;
					for (int i = 0; i < afterText; i++) {
						JSONObject object = new JSONObject();
						object.put("addedBy", addedBy);
						object.put("addedOn", currentTime);
						object.put("clientId", clientId);
						object.put("emailId", emailId);
						object.put("mobileNo", mobileNo);
						object.put("category", category);
						object.put("eventTitle", eventTitle);
						object.put("doctorName", doctorName);
						object.put("doctorDateTime", doctorDateTime);
						object.put("medicineName", medicineName);
						object.put("medicineDose", medicineDose);
						object.put("medicineDateTime", medicineDateTime);
						object.put("typeOfExercise", typeOfExercise);
						object.put("durationInMinutes", durationInMinutes);
						object.put("testName", testName);
						object.put("centreName", centreName);
						object.put("remindMeFor", remindMeFor);
						object.put("location", location);
						object.put("duration", duration);
						object.put("recurrencePattern", recurrencePattern);
						object.put("clientName", clientName);
						SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
								"MM/dd/yyyy hh:mm aa", Locale.US);
						Calendar calendar = Calendar.getInstance();
						Calendar calendar2 = Calendar.getInstance();
						try {
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.YEAR, days);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.YEAR, days);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
							days = days + monthlyRepeat;
						} catch (ParseException e) {
							e.printStackTrace();
						}
						jsonArray.add(object);
					}
				} else {
					try {
						String startDateSplit = eventStartDate.split(" ")[0];
						String untilDate = onText.split(" ")[0];
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MM/dd/yyyy");
						Date date1 = sdf.parse(startDateSplit);
						Date date2 = sdf.parse(untilDate);
						int diffInDays = (int) ((date2.getTime() - date1
								.getTime()) / (1000 * 60 * 60 * 24));
						int diffInWeeks = diffInDays / 7;
						int diffInMonths = diffInWeeks / 4;
						int diffInYears = diffInMonths / 12;
						for (int i = 0; i <= diffInYears; i = i + yearlyRepeat) {
							JSONObject object = new JSONObject();
							object.put("addedBy", addedBy);
							object.put("addedOn", currentTime);
							object.put("clientId", clientId);
							object.put("emailId", emailId);
							object.put("mobileNo", mobileNo);
							object.put("category", category);
							object.put("eventTitle", eventTitle);
							object.put("doctorName", doctorName);
							object.put("doctorDateTime", doctorDateTime);
							object.put("medicineName", medicineName);
							object.put("medicineDose", medicineDose);
							object.put("medicineDateTime", medicineDateTime);
							object.put("typeOfExercise", typeOfExercise);
							object.put("durationInMinutes", durationInMinutes);
							object.put("testName", testName);
							object.put("centreName", centreName);
							object.put("remindMeFor", remindMeFor);
							object.put("location", location);
							object.put("duration", duration);
							object.put("recurrencePattern", recurrencePattern);
							object.put("clientName", clientName);
							SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US);
							Calendar calendar = Calendar.getInstance();
							Calendar calendar2 = Calendar.getInstance();
							String startDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventStartDate));
							String endDate = new SimpleDateFormat(
									"MM/dd/yyyy hh:mm aa", Locale.US)
									.format(simpleDateFormat2
											.parse(eventEndDate));
							calendar.setTime(simpleDateFormat2.parse(startDate));
							calendar.add(Calendar.YEAR, i);
							String reminderDate = simpleDateFormat2
									.format(calendar.getTime());
							object.put("reminderDateTime", reminderDate);
							object.put("eventStartDate", reminderDate);
							calendar2.setTime(simpleDateFormat2.parse(endDate));
							calendar2.add(Calendar.YEAR, i);
							String reminderEndDate = simpleDateFormat2
									.format(calendar2.getTime());
							object.put("eventEndDate", reminderEndDate);
							jsonArray.add(object);
						}
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
			}
			// jsonArray.add(object);
		}
		//System.out.println("size:" + jsonArray.size());
		clientService.saveReminder(jsonArray);
		return "saved";
	}

	@RequestMapping(value = "/getPatientReminderByClientId", method = RequestMethod.POST)
	public @ResponseBody
	JSONArray getPatientReminderByClientId(
			@RequestParam("clientId") Integer clientId) {
		JSONArray jsonArray = clientService.getPatientReminder(clientId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getAllReminder", method = RequestMethod.POST)
	public @ResponseBody
	JSONArray getAllReminder() {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"MM/dd/yyyy h: a");
		String currentTime = simpleDateFormat.format(date);//"%07/16/2016 2:%PM";
		String time=currentTime.split(":")[0];
		String ampm=currentTime.split(":")[1];
		String ampmtime = ampm.replaceAll("\\s","");
		String dateTime=time+":%"+ampmtime;
		JSONArray jsonArray = clientService.getAllReminder(dateTime);
		//System.out.println(currentTime+"***Size:****" + jsonArray.size());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			String clientId = (String) object.get("clientId");
			String emailId = (String) object.get("emailId");
			String mobileNo = (String) object.get("mobileNo");
			String category = (String) object.get("category");
			String clientName = (String) object.get("clientName");
			String endDate = (String) object.get("endEvent");
			String startDate = (String) object.get("startEvent");
			String eventTitle = (String) object.get("eventTitle");
			String reminderDateTime=(String)object.get("reminderDateTime");
			String message="";
			if(category.equalsIgnoreCase("consultation")){
				String doctorName = (String) object.get("doctorName");
				String doctorDateTime = (String) object.get("doctorDateTime");
				message="Dear "+clientName+", your appointment with "+doctorName+" is scheduled from "+doctorDateTime+" to "+endDate+"";
			}
			else if(category.equalsIgnoreCase("exercise")){
				String typeOfExercise=(String)object.get("typeExercise");
				String durationInMinutes = (String) object.get("durationInMinutes");
				message="Dear "+clientName+", Gear up !! "+typeOfExercise+" for "+durationInMinutes+" minutes to be done at "+startDate+" !";
			}
			else if(category.equalsIgnoreCase("labTest")){
				String testName = (String) object.get("testName");
				String centreName = (String) object.get("centreName");
				message="Dear "+clientName+", your "+testName+" are scheduled at "+startDate+" at "+centreName+".";
			}
			else if(category.equalsIgnoreCase("medicine")){
				String medicineName = (String) object.get("medicineName");
				String medicineDose = (String) object.get("medicineDose");
				String medicineDateTime = (String) object.get("medicineDateTime");
				message="Dear "+clientName+", reminding you for "+medicineDose+" of "+medicineName+" to be taken at "+medicineDateTime+".";
			}
			else if(category.equalsIgnoreCase("others")){
				String remindMeFor = (String) object.get("remindMeFor");
				String location = (String) object.get("location");
				String duration = (String) object.get("duration");
				message="Dear "+clientName+", Just to remind you for "+remindMeFor+" at "+location+" for "+duration+" minutes scheduled from "+startDate+" to "+endDate+".";
			}
			//System.out.println(emailId+"**"+mobileNo);
			// for sending sms
			if (mobileNo != null && !mobileNo.equals("")) {
				CallSmscApi callSmscApi = new CallSmscApi();
				String[] mobile=mobileNo.split(",");
				for(int j=0;j<mobile.length;j++){
					SMSRecord smsRecord = new SMSRecord();
					smsRecord.setTopic("Reminder");
					smsRecord.setSentBy("API");
					smsRecord.setClientId(Integer.parseInt(clientId));
					smsRecord.setSmsMedicalAdvice(message);
					smsRecord.setSentTo(mobile[j]);
					callSmscApi.sendSms("+" + mobile[j] , message,smsRecord);
				}
			}
			// for sending mail
			if (emailId != null && !emailId.equals("")) {
				String mailSubject = "Reminder";
				String[] email=emailId.split(",");
				SSLEmail sendMail = new SSLEmail();
				for(int j=0;j<email.length;j++){
					try {
						EmailRecord emailRecord = new EmailRecord();
						emailRecord.setTopic("Reminder");
						emailRecord.setSentBy("API");
						emailRecord.setClientId(Integer.parseInt(clientId));
						emailRecord.setEmailSubject(mailSubject);
						emailRecord.setEmailMedicalAdvice(message);
						emailRecord.setSentTo(email[j]);
						sendMail.sendMail(email[j] , message , mailSubject, emailRecord);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return jsonArray;
	}

	@RequestMapping(value = "/deleteReminder", method = RequestMethod.POST)
	public @ResponseBody
	String deleteReminder(@RequestParam("reminderId") String reminderId,
			@RequestParam("groupId") String groupId) {
		String response = clientService.deleteReminder(reminderId, groupId);
		return response;
	}

}
