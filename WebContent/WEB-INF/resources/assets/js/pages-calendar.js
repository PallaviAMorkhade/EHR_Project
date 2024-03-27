var patientReminderArray=[];
function getPatientReminder(clientId){
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
			for(var i=0;i<r.length;i++){
				/*var startDate=r[i].startEvent.split(" ")[0];
				var startDay=startDate.split("/")[1];
				var startMonth=parseInt(startDate.split("/")[0])-1;
				var startYear=startDate.split("/")[2];
				var startTime=r[i].startEvent.split(" ")[1];
				var startHour=startTime.split(":")[0];
				var startMinutes=startTime.split(":")[1];
				var endDate=r[i].endEvent.split(" ")[0];
				var endDay=endDate.split("/")[1];
				var endMonth=parseInt(endDate.split("/")[0])-1;
				var endYear=endDate.split("/")[2];
				var endTime=r[i].endEvent.split(" ")[1];
				var endHour=endTime.split(":")[0];
				var endMinutes=endTime.split(":")[1];*/
				var str={
						title: r[i].eventTitle,
						start: new Date(r[i].startEvent),//new Date(startYear, startMonth, startDay, startHour, startMinutes),
						end: new Date(r[i].endEvent),//new Date(endYear, endMonth, endDay, endHour, endMinutes),
						//className: 'event-job',
						category: r[i].category,
						allDay: false,
						eventId : r[i].reminderId,
						emailId : r[i].emailId,
						mobileNo : r[i].mobileNo,
						medicineDateTime : r[i].medicineDateTime,
						doctorDateTime : r[i].doctorDateTime,
						medicineDose : r[i].medicineDose,
						clientId : r[i].clientId,
						reminderDateTime : r[i].reminderDateTime,
						isActive : r[i].isActive,
						medicineName : r[i].medicineName,
						doctorName : r[i].doctorName,
						typeExercise : r[i].typeExercise,
						durationInMinutes : r[i].durationInMinutes,
						testName : r[i].testName,
						centreName : r[i].centreName,
						location : r[i].location,
						duration : r[i].duration,
						remindMeFor : r[i].remindMeFor,
						groupId : r[i].groupId,
						recurrencePattern : r[i].recurrencePattern,
						clientName : r[i].clientName
						//content: 'Out to design conference'
					};
				patientReminderArray.push(str);
			}
		}
	});
}

var Calendar = function() {"use strict";
	var dateToShow, calendar, demoCalendar, eventClass, eventCategory, subViewElement, subViewContent, $eventDetail;
	var defaultRange = new Object;
	defaultRange.start = moment();
	defaultRange.end = moment().add(1, 'days');
	//Calendar
	var setFullCalendarEvents = function() {
		/*var date = new Date();
		dateToShow = date;
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();*/
		
		demoCalendar = patientReminderArray;
		/*demoCalendar = [{
			title: 'Networking',
			start: new Date(y, m, d, 20, 0),
			end: new Date(y, m, d, 21, 0),
			className: 'event-job',
			category: 'job',
			allDay: false,
			content: 'Out to design conference'
		}, {
			title: 'Bootstrap Seminar',
			start: new Date(y, m, d - 5),
			end: new Date(y, m, d - 2),
			className: 'event-off-site-work',
			category: 'off-site-work',
			allDay: true
		}, {
			title: 'Lunch with Nicole',
			start: new Date(y, m, d - 3, 12, 0),
			end: new Date(y, m, d - 3, 12, 30),
			className: 'event-generic',
			category: 'generic',
			allDay: false
		}, {
			title: 'Corporate Website Redesign',
			start: new Date(y, m, d + 5),
			end: new Date(y, m, d + 10),
			className: 'event-to-do',
			category: 'to-do',
			allDay: true
		}];*/
	};
	//function to initiate Full Calendar
	var runFullCalendar = function() {
		$(".add-event").off().on("click", function() {
			eventInputDateHandler();
			$(".form-full-event #event-id").val("");
			$('.events-modal').modal();
			
			$(".navbar-static-top").css("z-index","1000");
			$("footer").css("z-index","1000");
		});
		$('.events-modal').on('hide.bs.modal', function(event) {
			$(".navbar-static-top").css("z-index","1089");
			$("footer").css("z-index","1089");
			
			$(".form-full-event #event-id").val("");
			$(".form-full-event #eventTitle").val("");
			$(".form-full-event #start-date-time").val("").data("DateTimePicker").destroy();
			$(".form-full-event #end-date-time").val("").data("DateTimePicker").destroy();
			
			//assign others field by Amol Saware 
			//$(".event-categories[value='consultation']").prop('checked', true);
			$('.categoryDiv').hide();
			//$('#consultationDiv').show();
			$('#categoryOption').val("");
			$(".form-full-event #emailId").val("");
			$(".form-full-event #mobileNo").val("");
			$(".form-full-event #reminderDateTime").val("");
			$(".form-full-event #doctorName").val("");
			$(".form-full-event #doctorDateTime").val("");
			$(".form-full-event #medicineDateTime").val("");
			$(".form-full-event #medicineDose").val("");
			$(".form-full-event #medicineName").val("");
			$(".form-full-event #typeOfExercise").val("");
			$(".form-full-event #durationInMinutes").val("");
			$(".form-full-event #testName").val("");
			$(".form-full-event #centreName").val("");
			$(".form-full-event #location").val("");
			$(".form-full-event #duration").val("");
			$(".form-full-event #remindMeFor").val("");
			$("#groupIdSpan").html("");
			$("#eventIdSpan").html("");
			$("#clientName").val("");
			$('#repeatReminderSummary').html("");
			$("#deleteReminderBtn").hide();
			$("#saveReminderBtn").show();
		});

		$('#event-categories div.event-category').each(function() {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex: 999,
				revert: true, // will cause the event to go back to its
				revertDuration: 50 //  original position after the drag
			});
		});
		/* initialize the calendar
		 -----------------------------------------------------------------*/
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		var form = '';
		$('#full-calendar').fullCalendar({
			buttonIcons: {
				prev: 'fa fa-chevron-left',
				next: 'fa fa-chevron-right'
			},
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			events: demoCalendar,
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) {// this function is called when something is dropped

				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');

				var $category = $(this).attr('data-class');
				
				// we need to copy it, so that multiple events don't have a reference to the same object

				var newEvent = new Object;
				newEvent.title = originalEventObject.title;
				newEvent.start = new Date(date);
				newEvent.end = moment(new Date(date)).add(1, 'hours');
				newEvent.allDay = true;
				newEvent.category = $category;
				newEvent.className = 'event-' + $category;

				$('#full-calendar').fullCalendar('renderEvent', newEvent, true);

				// is the "remove after drop" checkbox checked?
				if($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				eventInputDateHandler();
				$(".navbar-static-top").css("z-index","1000");
				$("footer").css("z-index","1000");
				$(".form-full-event #event-id").val("");
				$(".form-full-event #event-name").val("");
				$(".form-full-event #start-date-time").data("DateTimePicker").date(moment(start));
				$(".form-full-event #end-date-time").data("DateTimePicker").date(moment(start).add(1, 'hours'));
				//$(".event-categories[value='consultation']").prop('checked', true);
				$('.events-modal').modal();
				$("#deleteReminderBtn").hide();
				$("#saveReminderBtn").show();
			},
			eventClick: function(calEvent, jsEvent, view) {
				$(".navbar-static-top").css("z-index","1000");
				$("footer").css("z-index","1000");
				eventInputDateHandler();
				var eventId = calEvent._id;
				for(var i = 0; i < demoCalendar.length; i++) {
					if(demoCalendar[i]._id == eventId) {
						$(".form-full-event #event-id").val(demoCalendar[i].eventId);
						$(".form-full-event #eventTitle").val(demoCalendar[i].title);
						$(".form-full-event #start-date-time").data("DateTimePicker").date(moment(demoCalendar[i].start));
						$(".form-full-event #end-date-time").data("DateTimePicker").date(moment(demoCalendar[i].end));
						if(demoCalendar[i].category == "" || typeof demoCalendar[i].category == "undefined") {
							eventCategory = "Generic";
						} else {
							eventCategory = demoCalendar[i].category;
						}

						//$(".event-categories[value='" + eventCategory + "']").prop('checked', true);
						//assign others field by Amol Saware 
						$('#categoryOption').val(eventCategory);
						$('.categoryDiv').hide();
						$('#'+eventCategory+'Div').show();
						$(".form-full-event #emailId").val(demoCalendar[i].emailId);
						$(".form-full-event #mobileNo").val(demoCalendar[i].mobileNo);
						$(".form-full-event #reminderDateTime").val(demoCalendar[i].reminderDateTime);
						$(".form-full-event #doctorName").val(demoCalendar[i].doctorName);
						$(".form-full-event #doctorDateTime").val(demoCalendar[i].doctorDateTime);
						$(".form-full-event #medicineDateTime").val(demoCalendar[i].medicineDateTime);
						$(".form-full-event #medicineDose").val(demoCalendar[i].medicineDose);
						$(".form-full-event #medicineName").val(demoCalendar[i].medicineName);
						$(".form-full-event #typeOfExercise").val(demoCalendar[i].typeExercise);
						$(".form-full-event #durationInMinutes").val(demoCalendar[i].durationInMinutes);
						$(".form-full-event #testName").val(demoCalendar[i].testName);
						$(".form-full-event #centreName").val(demoCalendar[i].centreName);
						$(".form-full-event #location").val(demoCalendar[i].location);
						$(".form-full-event #duration").val(demoCalendar[i].duration);
						$(".form-full-event #remindMeFor").val(demoCalendar[i].remindMeFor);
						$("#groupIdSpan").html(demoCalendar[i].groupId);
						$("#eventIdSpan").html(demoCalendar[i].eventId);
						$("#clientName").val(demoCalendar[i].clientName);
						$('#repeatReminderSummary').html(demoCalendar[i].recurrencePattern);
						$("#deleteReminderBtn").show();
						$("#saveReminderBtn").hide();
					}
				}
				$('.events-modal').modal();
			}
		});
		demoCalendar = $("#full-calendar").fullCalendar("clientEvents");
	};

	var runFullCalendarValidation = function(el) {

		var formEvent = $('.form-full-event');
		formEvent.validate({
			errorElement: "span", // contain the error msg in a span tag
			errorClass: 'help-block',

			ignore: "",
			rules: {
				eventName: {
					minlength: 2,
					required: true
				},
				eventStartDate: {
					required: true,
					date: true
				},
				eventEndDate: {
					required: true,
					date: true
				}
			},
			messages: {
				eventName: "* Please specify the event title"

			},
			highlight: function(element) {
				$(element).closest('.help-block').removeClass('valid');
				// display OK icon
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error').find('.symbol').removeClass('ok').addClass('required');
				// add the Bootstrap error class to the control group
			},
			unhighlight: function(element) {// revert the change done by hightlight
				$(element).closest('.form-group').removeClass('has-error');
				// set error class to the control group
			},
			success: function(label, element) {
				label.addClass('help-block valid');
				// mark the current input as valid and display OK icon
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success').find('.symbol').removeClass('required').addClass('ok');
			},
			submitHandler: function(form) {
				var newEvent = new Object;
				newEvent.title = $(".form-full-event #event-name ").val();
				newEvent.start = new Date($('.form-full-event #start-date-time').val());
				newEvent.end = new Date($('.form-full-event #end-date-time').val());
				newEvent.category = $(".form-full-event .event-categories:checked").val();
				newEvent.className = 'event-' + $(".form-full-event .event-categories:checked").val();
				
				if($(".form-full-event #event-id").val() !== "") {
					el = $(".form-full-event #event-id").val();
					var actual_event = $('#full-calendar').fullCalendar('clientEvents', el);
					actual_event = actual_event[0];
					for(var i = 0; i < demoCalendar.length; i++) {
						if(demoCalendar[i]._id == el) {
							newEvent._id = el;
							var eventIndex = i;
						}
					}

					$('#full-calendar').fullCalendar('removeEvents', actual_event._id);
					$('#full-calendar').fullCalendar('renderEvent', newEvent, true);

					demoCalendar = $("#full-calendar").fullCalendar("clientEvents");

				} else {
					$('#full-calendar').fullCalendar('renderEvent', newEvent, true);
					demoCalendar = $("#full-calendar").fullCalendar("clientEvents");
				}
				$('.events-modal').modal('hide');
			}
		});
	};

	var eventInputDateHandler = function() {
		var startInput = $('#start-date-time');
		var endInput = $('#end-date-time');
		startInput.datetimepicker();
		endInput.datetimepicker();
		startInput.on("dp.change", function(e) {
			endInput.data("DateTimePicker").minDate(e.date);
		});
		endInput.on("dp.change", function(e) {
			startInput.data("DateTimePicker").maxDate(e.date);
		});
	};
	return {
		init: function() {
			setFullCalendarEvents();
			runFullCalendar();
			runFullCalendarValidation();
		}
	};
}();
