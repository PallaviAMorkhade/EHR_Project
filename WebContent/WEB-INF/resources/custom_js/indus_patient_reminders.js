function deleteReminder(current){
	var id=$(current).attr('id');
	if(id=="reminderIdDeleteBtn"){
		deleteRminderDb($('#eventIdSpan').html(),0);
	}
	else if(id=="followingReminderDeleteBtn"){
		deleteRminderDb($('#eventIdSpan').html(),$('#groupIdSpan').html());
	}
	else{
		deleteRminderDb(0,$('#groupIdSpan').html());
	}
}

function deleteRminderDb(reminderId,groupId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : {
			"reminderId" : reminderId,
			"groupId" : groupId
		},
		url : "reminder/deleteReminder",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			alertify.error("Reminder Deleted Successfully");
			loadReminderPage();
			$("body").css("overflow","auto");
		}
	});
}

function saveReminder(){
	var eventTitle=$('#eventTitle').val();
	var emailId=$('#emailId').val();
	var mobileNo=$('#mobileNo').val();
	var category=$('#categoryOption').val();
	var clientName=$('#clientName').val();
	/*var reminderDateTime=$('#reminderDateTime').val();
	var eventStartDate=$('#start-date-time').val();
	var eventEndDate=$('#end-date-time').val();*/
	
	if(eventTitle==""){
		alert("Please enter event title");
	}
	else if(clientName==""){
		alert("Please enter reminder for");
	}
	else if(emailId=="" && mobileNo==""){
		alert("Please enter email id or mobile number");
	}
	else if(category==""){
		alert("Please select category");
	}
	else if(category=="consultation"){
		var doctorName=$('#doctorName').val();
		var doctorDateTime=$('#doctorDateTime').val();
		if(doctorName==""){
			alert("Please enter doctor name");
		}
		else if(doctorDateTime==""){
			alert("Please enter doctor date and time");
		}
		else if($('#recurrencePatternSpan').html()==""){
			alert("Please Select One Time or Repeat");
		}
		else{
			//validateReminder(reminderDateTime,eventStartDate,eventEndDate);
			saveReminderDb();
		}
	}
	else if(category=="medicine"){
		var medicineName=$('#medicineName').val();
		var medicineDose=$('#medicineDose').val();
		var medicineDateTime=$('#medicineDateTime').val();
		if(medicineName==""){
			alert("Please enter medicine name");
		}
		else if(medicineDose==""){
			alert("Please enter medicine dose");
		}
		else if(medicineDateTime==""){
			alert("Please enter medicine date and time");
		}
		else if($('#recurrencePatternSpan').html()==""){
			alert("Please Select One Time or Repeat");
		}
		else{
			//validateReminder(reminderDateTime,eventStartDate,eventEndDate);
			saveReminderDb();
		}
	}
	else if(category=="exercise"){
		var typeOfExercise=$('#typeOfExercise').val();
		var durationInMinutes=$('#durationInMinutes').val();
		if(typeOfExercise==""){
			alert("Please enter type of exercise");
		}
		else if(durationInMinutes==""){
			alert("Please enter duration in minutes");
		}
		else if($('#recurrencePatternSpan').html()==""){
			alert("Please Select One Time or Repeat");
		}
		else{
			//validateReminder(reminderDateTime,eventStartDate,eventEndDate);
			saveReminderDb();
		}
	}
	else if(category=="labTest"){
		var testName=$('#testName').val();
		var centreName=$('#centreName').val();
		if(testName==""){
			alert("Please enter test name");
		}
		else if(centreName==""){
			alert("Please enter centre name");
		}
		else if($('#recurrencePatternSpan').html()==""){
			alert("Please Select One Time or Repeat");
		}
		else{
			//validateReminder(reminderDateTime,eventStartDate,eventEndDate);
			saveReminderDb();
		}
	}
	else if(category=="others"){
		var remindMeFor=$('#remindMeFor').val();
		var location=$('#location').val();
		var duration=$('#duration').val();
		if(remindMeFor==""){
			alert("Please enter remind me for");
		}
		else if(location==""){
			alert("Please enter location");
		}
		else if(duration==""){
			alert("Please enter duration");
		}
		else if($('#recurrencePatternSpan').html()==""){
			alert("Please Select One Time or Repeat");
		}
		else{
			//validateReminder(reminderDateTime,eventStartDate,eventEndDate);
			saveReminderDb();
		}
	}
}

/*function validateReminder(reminderDateTime,eventStartDate,eventEndDate){
	if(reminderDateTime==""){
		alert("Please enter reminder date and time");
	}
	else if(eventStartDate==""){
		alert("Please enter event start date");
	}
	else if(eventEndDate==""){
		alert("Please enter event end date");
	}
	else{
		saveReminderDb();
	}
}*/

function saveReminderDb(){
	//var ehrReminderMasterId=$('input[name=recurrencePattern]:checked').val();
	var inputs = [];
	inputs.push('emailId=' + $('#emailId').val());
	inputs.push('mobileNo=' + $('#mobileNo').val());
	inputs.push('category=' + $('#categoryOption').val());
	inputs.push('eventTitle=' + $('#eventTitle').val());
	inputs.push('reminderDateTime=' + $('#reminderDateTime').val());
	inputs.push('eventStartDate=' + $('#start-date-time').val());
	inputs.push('eventEndDate=' + $('#end-date-time').val());
	inputs.push('doctorName=' + $('#doctorName').val());
	inputs.push('doctorDateTime=' + $('#doctorDateTime').val());
	inputs.push('medicineName=' + $('#medicineName').val());
	inputs.push('medicineDose=' + $('#medicineDose').val());
	inputs.push('medicineDateTime=' + $('#medicineDateTime').val());
	inputs.push('typeOfExercise=' + $('#typeOfExercise').val());
	inputs.push('durationInMinutes=' + $('#durationInMinutes').val());
	inputs.push('testName=' + $('#testName').val());
	inputs.push('centreName=' + $('#centreName').val());
	inputs.push('remindMeFor=' + $('#remindMeFor').val());
	inputs.push('location=' + $('#location').val());
	inputs.push('duration=' + $('#duration').val());
	inputs.push('clientName=' + $('#clientName').val());
	inputs.push('clientId=' + $('#clientMasterId').html());
	var reminders=[];
	$('#eventReminderSummary tr').map(function(){
		reminders.push(this.id);
	});
	inputs.push('reminders[]=' + reminders);
	inputs.push('ehrReminderMasterId=' + $('#recurrenceRepeat').val());
	inputs.push('dailyRepeat=' + $('#dailyRepeat').val());
	inputs.push('weeklyRepeat=' + $('#weeklyRepeat').val());
	inputs.push('monthlyRepeat=' + $('#monthlyRepeat').val());
	inputs.push('repeatBy=' + $('input[name="repeatBy"]:checked').val());
	inputs.push('yearlyRepeat=' + $('#yearlyRepeat').val());
	inputs.push('ends=' + $('input[name="recurrenceRepeat"]:checked').val());
	inputs.push('afterText=' + $('#afterText').val());
	inputs.push('onText=' + $('#onText').val());
	var weeklyDays=[];
	$('.weeklyDays:checked').map(function(){
			weeklyDays.push(this.id);
	});
	inputs.push('weeklyDays[]=' + weeklyDays);
	inputs.push('recurrencePattern=' + $('#recurrencePatternSpan').html());
	/*inputs.push('ehrReminderMasterId=' + ehrReminderMasterId);
	//daily==1
	inputs.push('recurrencePatternDaily=' + $('input[name=recurrencePatternDaily]:checked').val());
	inputs.push('recurrencePatternDailyInput1=' + $('#recurrencePatternDailyInput1').val());
	inputs.push('recurrencePatternDailyInput2=' + $('#recurrencePatternDailyInput2').val());
	//monthly==3
	inputs.push('recurrencePatternMonthly=' + $('input[name=recurrencePatternMonthly]:checked').val());
	inputs.push('recurrencePatternMonthlyInput1=' + $('#recurrencePatternMonthlyInput1').val());
	inputs.push('recurrencePatternMonthlyInput2=' + $('#recurrencePatternMonthlyInput2').val());
	inputs.push('recurrencePatternMonthlySelect1=' + $('#recurrencePatternMonthlySelect1').val());
	inputs.push('recurrencePatternMonthlySelect2=' + $('#recurrencePatternMonthlySelect2').val());
	inputs.push('recurrencePatternMonthlyInput3=' + $('#recurrencePatternMonthlyInput3').val());
	//yearly==4
	inputs.push('recurrencePatternYearly=' + $('input[name=recurrencePatternYearly]:checked').val());
	inputs.push('recurrencePatternYearlyInput1=' + $('#recurrencePatternYearlyInput1').val());
	inputs.push('recurrencePatternYearlySelect1=' + $('#recurrencePatternYearlySelect1').val());
	inputs.push('recurrencePatternYearlyInput2=' + $('#recurrencePatternYearlyInput2').val());
	inputs.push('recurrencePatternYearlySelect2=' + $('#recurrencePatternYearlySelect2').val());
	inputs.push('recurrencePatternYearlySelect3=' + $('#recurrencePatternYearlySelect3').val());
	inputs.push('recurrencePatternYearlySelect4=' + $('#recurrencePatternYearlySelect4').val());*/
	//weekly==2
	/*var weeklyDays=[];
	$('.weeklyDays:checked').map(function(){
		weeklyDays.push(this.id);
	});
	inputs.push('weeklyDays[]=' + weeklyDays);*/
	var str = inputs.join('&');
	jQuery.ajax({
		async : true,
		type : "POST",
		data : str + "&reqType=AJAX",
		url : "reminder/saveReminder",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			alertify.error("Reminder Saved Successfully");
			loadReminderPage();
			$("body").css("overflow","auto");
		}
	});
}

function changeCategory(){
	var selected=$('#categoryOption').val();
    $('.categoryDiv').hide();
    $('#'+selected+'Div').show();
}

$( 'input[name="recurrencePattern"]:radio' ).on('click', function(e) {
    var selected=this.id;
    $('#reminderRecurrenceModal').modal();
    $('.repeatTime').hide();
    $('#'+selected+'Div').show();
    $('#startsOn').val($('#start-date-time').val().split(" ")[0]);
    if(selected=="oneTime"){
    	$('#reminderTableBody').html($('#eventReminderSummary').html());
    	$('#repeatRemTitle').html("One Time");
    }
    else{
    	//console.log("in repeat");
    	$('#repeatRemTitle').html("Repeat");
    }
});

function changeRecurrenceRepeat(){
	var selected=$('#recurrenceRepeat :selected').attr('class');
    $('.recurrenceRepeatDiv').hide();
    $('#'+selected+'Div').show();
}

function reminderSummary(){
	var reminderDateTime=$('#reminderDateTime').val();
	var reminderDate = new Date(reminderDateTime);
	var scheduleTime=$('#scheduleTime').val();
	var scheduleFormat=$('#scheduleFormat').val();
	var index=$('#reminderTableBody tr').length+1;
	if(scheduleTime!=""){
	if(scheduleFormat=="hours"){
		var dateTime = calcTime(reminderDateTime, scheduleTime);
		var reminderDisplayDate=reminderDateTime.split(" ")[0];
		var formattedDate=getFormattedDate(reminderDisplayDate);
		var time = timeFormat12Hr(dateTime);
		var divContent="<tr id='"+reminderDisplayDate+" "+time+"'><td>"+index+"</td><td>"+formattedDate+" "+time+"</td><td onclick='removeReminder(this)'><a><i class='fa fa-times fa fa-white'></i></a></td></tr>";
		$('#reminderTableBody').append(divContent);
	}
	else{
		var newdate = new Date(reminderDate);
		newdate.setDate(newdate.getDate() - scheduleTime); // minus the date
		var newDate = new Date(newdate);
		//console.log(newDate.toLocaleString()+"new date:"+newDate);
		var disDate=(newDate.toLocaleString()).split(',');
		var splitDate=disDate[0].split("/");
		var date=splitDate[0];
		var month=splitDate[1];
		var year=splitDate[2];
		var stringDate=month+"/"+date+"/"+year;
		var formattedDate=getFormattedDate(stringDate);
		var divContent="<tr id='"+stringDate+""+disDate[1]+"'><td>"+index+"</td><td>"+formattedDate+" "+disDate[1]+"</td><td onclick='removeReminder(this)'><a><i class='fa fa-times fa fa-white'></i></a></td></tr>";
		//var divContent1="<tr class='reminders' id='"+stringDate+""+disDate[1]+"'><td>"+index+"</td><td>"+formattedDate+" "+disDate[1]+"</td><td><a><i class='fa fa-times fa fa-white'></i></a></td></tr>";
		$('#reminderTableBody').append(divContent);
		//$('#eventReminderSummary').append(divContent1);
	}
	}
	else{
		alert("Please enter schedule value");
	}
}

function calcTime(date, offset) {
	var date = new Date(date);
	var addedTime = date.getHours() + parseInt(offset);
	date.setHours(addedTime);
	return date;
}

function timeFormat12Hr(date) {
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    var timeString = hours +":"+ minutes +":"+ seconds;
    var H = +timeString.substr(0, 2);
    var h = (H % 12) || 12;
    var ampm = H < 12 ? "AM" : "PM";
    timeString = h + timeString.substr(2, 3) + ampm;
    return timeString;
}

function setReminder(){
	var weekday = new Array(7);
	weekday[0]=  "SUNDAY";
	weekday[1] = "MONDAY";
	weekday[2] = "TUESDAY";
	weekday[3] = "WEDNESDAY";
	weekday[4] = "THURSDAY";
	weekday[5] = "FRIDAY";
	weekday[6] = "SATURDAY";
	var month = new Array(12);
    month[0] = "January";
    month[1] = "February";
    month[2] = "March";
    month[3] = "April";
    month[4] = "May";
    month[5] = "June";
    month[6] = "July";
    month[7] = "August";
    month[8] = "September";
    month[9] = "October";
    month[10] = "November";
    month[11] = "December";
    Date.prototype.getWeekOfMonth = function(exact) {
        var month = this.getMonth()
            , year = this.getFullYear()
            , firstWeekday = new Date(year, month, 1).getDay()
            , lastDateOfMonth = new Date(year, month + 1, 0).getDate()
            , offsetDate = this.getDate() + firstWeekday - 1
            , index = 1 // start index at 0 or 1, your choice
            , weeksInMonth = index + Math.ceil((lastDateOfMonth + firstWeekday - 7) / 7)
            , week = index + Math.floor(offsetDate / 7)
        ;
        if (exact || week < 2 + index) return week;
        return week === weeksInMonth ? index + 5 : week;
    };
	$('#reminderSummaryDiv').show();
	var divContent="";
	var selected=$('input[name=recurrencePattern]:checked').attr('id');
	$('#recurrencePatternSpan').html($('input[name=recurrencePattern]:checked').val());
	 if(selected=="oneTime"){
			$('#eventReminderSummary').html($('#reminderTableBody').html());
			$('#oneTimeReminderSummaryDiv').show();
			$('#repeatReminderSummaryDiv').hide();
	  }
	  else{
		  $('#oneTimeReminderSummaryDiv').hide();
		  $('#repeatReminderSummaryDiv').show();
		  var ends=$('input[name="recurrenceRepeat"]:checked').val();
		  var recurrenceRepeat=$('#recurrenceRepeat').val();
			if(ends=="1"){
				if(recurrenceRepeat=="1"){
					var dailyRepeat=$('#dailyRepeat').val();
					if(dailyRepeat=="1"){
						divContent=divContent+"Daily";
					}
					else{
						divContent=divContent+"Every "+dailyRepeat+" days";
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else if(recurrenceRepeat=="2"){
					var startsOn=$('#startsOn').val();
					var startWeekDate = new Date(startsOn);
					var weeklyRepeat=$('#weeklyRepeat').val();
					var weeklyDays=[];
					$('.weeklyDays:checked').map(function(){
						weeklyDays.push(this.id);
					});
					var weekDay=weekday[startWeekDate.getDay()];
					if(weeklyDays.length>0){
						if(weeklyRepeat=="1"){
							divContent=divContent+"Weekly on "+weeklyDays;
						}
						else{
							divContent=divContent+"Every "+weeklyRepeat+" weeks on "+weeklyDays;
						}
					}
					else{
					if(weeklyRepeat=="1"){
						divContent=divContent+"Weekly on "+weekDay;
					}
					else{
						divContent=divContent+"Every "+weeklyRepeat+" weeks on "+weekDay;
					}
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else if(recurrenceRepeat=="3"){
					var startsOn=$('#startsOn').val();
					var startWeekDate = new Date(startsOn);
					var monthlyRepeat=$('#monthlyRepeat').val();
					var weekOfMonth=startWeekDate.getWeekOfMonth();
					var weekDay=weekday[startWeekDate.getDay()];
					var repeatBy=$('input[name="repeatBy"]:checked').val();
					if(repeatBy=="1"){
						if(monthlyRepeat=="1"){
							divContent=divContent+"Monthly on day "+startWeekDate.getDate();
						}
						else{
							divContent=divContent+"Every "+monthlyRepeat+" months on day "+startWeekDate.getDate();
						}
					}
					else{
						if(monthlyRepeat=="1"){
							divContent=divContent+"Monthly on the "+weekOfMonth+" "+weekDay;
						}
						else{
							divContent=divContent+"Every "+monthlyRepeat+" months on the "+weekOfMonth+" "+weekDay;
						}
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else{
					var startsOn=$('#startsOn').val();
					var untilDate = new Date(startsOn);
					var displayDate=untilDate.getDate()+" "+month[untilDate.getUTCMonth()];
					var yearlyRepeat=$('#yearlyRepeat').val();
					if(yearlyRepeat=="1"){
						divContent=divContent+"	Annually on "+displayDate;
					}
					else{
						divContent=divContent+"Every "+yearlyRepeat+" years on "+displayDate;
					}
					$('#repeatReminderSummary').html(divContent);
				}
			}
			else if(ends=="2"){
				var afterText=$('#afterText').val();
				if(recurrenceRepeat=="1"){
					var dailyRepeat=$('#dailyRepeat').val();
					if(dailyRepeat=="1"){
						divContent=divContent+"Daily , "+afterText+" times";
					}
					else{
						divContent=divContent+"Every "+dailyRepeat+" days , "+afterText+" times";
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else if(recurrenceRepeat=="2"){
					var startsOn=$('#startsOn').val();
					var startWeekDate = new Date(startsOn);
					var afterText=$('#afterText').val();
					var weeklyRepeat=$('#weeklyRepeat').val();
					var weeklyDays=[];
					$('.weeklyDays:checked').map(function(){
						weeklyDays.push(this.id);
					});
					var weekDay=weekday[startWeekDate.getDay()];
					if(weeklyDays.length>0){
						if(weeklyRepeat=="1"){
							divContent=divContent+"Weekly on "+weeklyDays+", "+afterText+" times";
						}
						else{
							divContent=divContent+"Every "+weeklyRepeat+" weeks on "+weeklyDays+", "+afterText+" times";
						}
					}
					else{
					if(weeklyRepeat=="1"){
						divContent=divContent+"Weekly on "+weekDay+", "+afterText+" times";
					}
					else{
						divContent=divContent+"Every "+weeklyRepeat+" weeks on "+weekDay+" ,"+afterText+" times";
					}
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else if(recurrenceRepeat=="3"){
					var startsOn=$('#startsOn').val();
					var startWeekDate = new Date(startsOn);
					var monthlyRepeat=$('#monthlyRepeat').val();
					var weekOfMonth=startWeekDate.getWeekOfMonth();
					var weekDay=weekday[startWeekDate.getDay()];
					var repeatBy=$('input[name="repeatBy"]:checked').val();
					var afterText=$('#afterText').val();
					if(repeatBy=="1"){
						if(monthlyRepeat=="1"){
							divContent=divContent+"Monthly on day "+startWeekDate.getDate()+", "+afterText+" times";
						}
						else{
							divContent=divContent+"Every "+monthlyRepeat+" months on day "+startWeekDate.getDate()+", "+afterText+" times";
						}
					}
					else{
						if(monthlyRepeat=="1"){
							divContent=divContent+"Monthly on the "+weekOfMonth+" "+weekDay+", "+afterText+" times";
						}
						else{
							divContent=divContent+"Every "+monthlyRepeat+" months on the "+weekOfMonth+" "+weekDay+", "+afterText+" times";
						}
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else{
					var afterText=$('#afterText').val();
					var startsOn=$('#startsOn').val();
					var untilDate = new Date(startsOn);
					var displayDate=untilDate.getDate()+" "+month[untilDate.getUTCMonth()];
					var yearlyRepeat=$('#yearlyRepeat').val();
					if(yearlyRepeat=="1"){
						divContent=divContent+"	Annually on "+displayDate+", "+afterText+" times";
					}
					else{
						divContent=divContent+"Every "+yearlyRepeat+" years on "+displayDate+", "+afterText+" times";
					}
					$('#repeatReminderSummary').html(divContent);
				}
			}
			else if(ends=="3"){
				var onText=$('#onText').val();
				var untilDate = new Date(onText);
				var displayDate=untilDate.getDate()+" "+month[untilDate.getUTCMonth()]+" "+untilDate.getFullYear();
				if(recurrenceRepeat=="1"){
					var dailyRepeat=$('#dailyRepeat').val();
					if(dailyRepeat=="1"){
						divContent=divContent+"Daily, until "+displayDate;
					}
					else{
						divContent=divContent+"Every "+dailyRepeat+" days ,until "+displayDate;
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else if(recurrenceRepeat=="2"){
					var startsOn=$('#startsOn').val();
					var startWeekDate = new Date(startsOn);
					var weeklyRepeat=$('#weeklyRepeat').val();
					var weeklyDays=[];
					$('.weeklyDays:checked').map(function(){
						weeklyDays.push(this.id);
					});
					var weekDay=weekday[startWeekDate.getDay()];
					if(weeklyDays.length>0){
						if(weeklyRepeat=="1"){
							divContent=divContent+"Weekly on "+weeklyDays+", until "+displayDate;
						}
						else{
							divContent=divContent+"Every "+weeklyRepeat+" weeks on "+weeklyDays+", until "+displayDate;
						}
					}
					else{
					if(weeklyRepeat=="1"){
						divContent=divContent+"Weekly on "+weekDay+", until "+displayDate;
					}
					else{
						divContent=divContent+"Every "+weeklyRepeat+" weeks on "+weekDay+", until "+displayDate;
					}
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else if(recurrenceRepeat=="3"){
					var onText=$('#onText').val();
					var startsOn=$('#startsOn').val();
					var untilDate = new Date(onText);
					var displayDate=untilDate.getDate()+" "+month[untilDate.getUTCMonth()]+" "+untilDate.getFullYear();
					var startWeekDate = new Date(startsOn);
					var monthlyRepeat=$('#monthlyRepeat').val();
					var weekOfMonth=startWeekDate.getWeekOfMonth();
					var weekDay=weekday[startWeekDate.getDay()];
					var repeatBy=$('input[name="repeatBy"]:checked').val();
					if(repeatBy=="1"){
						if(monthlyRepeat=="1"){
							divContent=divContent+"Monthly on day "+startWeekDate.getDate()+", until "+displayDate;
						}
						else{
							divContent=divContent+"Every "+monthlyRepeat+" months on day "+startWeekDate.getDate()+", until "+displayDate;
						}
					}
					else{
						if(monthlyRepeat=="1"){
							divContent=divContent+"Monthly on the "+weekOfMonth+" "+weekDay+", until "+displayDate;
						}
						else{
							divContent=divContent+"Every "+monthlyRepeat+" months on the "+weekOfMonth+" "+weekDay+", until "+displayDate;
						}
					}
					$('#repeatReminderSummary').html(divContent);
				}
				else{
					var onText=$('#onText').val();
					var startsOn=$('#startsOn').val();
					var annualUntilDate = new Date(onText);
					var annualDisplayDate=annualUntilDate.getDate()+" "+month[annualUntilDate.getUTCMonth()]+" "+annualUntilDate.getFullYear();
					var untilDate = new Date(startsOn);
					var displayDate=untilDate.getDate()+" "+month[untilDate.getUTCMonth()];
					var yearlyRepeat=$('#yearlyRepeat').val();
					if(yearlyRepeat=="1"){
						divContent=divContent+"	Annually on "+displayDate+", until "+annualDisplayDate;
					}
					else{
						divContent=divContent+"Every "+yearlyRepeat+" years on "+displayDate+", until "+annualDisplayDate;
					}
					$('#repeatReminderSummary').html(divContent);
				}
			}
		  
	    }
	//$('#oneTime').prop('checked', false);
	//$('#repeat').prop('checked', false);
	$('#reminderRecurrenceModal').modal('hide');
}

function resetReminder(){
	$('#reminderTableBody').html("");
	//$('#eventReminderSummary').html("");
	//$('#oneTime').prop('checked', false);
	//$('#repeat').prop('checked', false);
}

function removeReminder(current){
	$(current).parent().remove();
}

function getFormattedDate(date){
        var sptdate = String(date).split("/");
        var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
        var myMonth = sptdate[0];
        var myDay = sptdate[1];
        var myYear = sptdate[2];
        var combineDatestr = myDay + " " + months[myMonth - 1] + " " + myYear;
        return combineDatestr;
}

function repeatEvery(){
	var value=$('#dailyRepeat').val();
	var divContent="Every "+value+" days";
	$('#summary').html(divContent);
}

$( 'input[name="recurrenceRepeat"]:radio' ).on('change', function(e) {
	var currentId=this.id;
	//console.log(currentId);
	if(currentId=="after"){
		var selected=$('#afterText').val();
		var value=$('#dailyRepeat').val();
		var divContent="Every "+value+" days , "+selected+" times";
		$('#summary').html(divContent);
	}
	else if(currentId="on"){
		var repeats=$('#recurrenceRepeat').val();
		var onText=$('#onText').val();
		var divContent=""+repeats+", until "+onText+"";
		$('#summary').html(divContent);
	}
	else{
		var repeats=$('#recurrenceRepeat').val();
		var divContent=repeats;
		$('#summary').html(divContent);
	}
});

/*function afterClick(){
	var selected=$('#afterText').val();
	var value=$('#dailyRepeat').val();
	var divContent="Every "+value+" days , "+selected+" times";
	$('#summary').html(divContent);
}*/

/*function onClick(){
	var repeats=$('#recurrenceRepeat').val();
	var onText=$('#onText').val();
	var divContent=""+repeats+", until "+onText+"";
	$('#summary').html(divContent);
}*/

/*function neverClick(){
	var repeats=$('#recurrenceRepeat').val();
	var divContent=repeats;
	$('#summary').html(divContent);
}*/

/*$( 'input[name="optionCategory"]:radio' ).on('change', function(e) {
var selected=this.value;
$('.categoryDiv').hide();
$('#'+selected+'Div').show();
});*/

/*function getPatientReminder(clientId){
	jQuery.ajax({
		async : true,
		type : "POST",
		data : { "clientId" : clientId },
		url : "reminder/getPatientReminderByClientId",
		catche : false,
		error : function() {
			alert("error");
		},
		success : function(r) {
			alert("list");
		}
	});
}*/