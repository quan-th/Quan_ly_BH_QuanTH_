$(function() {
	$("#birthdate")
			.datepicker(
					{
						showOn : "button",
						dateFormat : 'dd/mm/yy',
						buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
						buttonImageOnly : true
					});
});
$(function() {
	$("#startDate")
			.datepicker(
					{
						showOn : "button",
						dateFormat : 'dd/mm/yy',
						buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
						buttonImageOnly : true
					});
});
$(function() {
	$("#endDate")
			.datepicker(
					{
						showOn : "button",
						dateFormat : 'dd/mm/yy',
						buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
						buttonImageOnly : true
					});
});
$(document).ready(function() {
	$('input[type=radio][name ="choseCompany"]').change(function() {	
		if (this.value == 'ALREADY_HAVE') {
			$("#companyInfo").css("display", "table");
			$("#tbl_company004").css("display", "none");
		} else if (this.value == 'ADD_NEW_COMPANY') {
			$("#tbl_company004").css("display", "table");
			$("#companyInfo").css("display", "none");
		}
	});
});
$(document).ready(function() {
		if ($("input[name='choseCompany']:checked").val()== 'ALREADY_HAVE') {	
			$("#companyInfo").css("display", "table");
			$("#tbl_company004").css("display", "none");
		} else if ($("input[name='choseCompany']:checked").val() == 'ADD_NEW_COMPANY') {
			$("#tbl_company004").css("display", "table");
			$("#companyInfo").css("display", "none");
		}
});
$(document).ready(function() {
	$.ajax({
		type : "POST",
		url : window.location.origin + "/insurence_quanth/Register.do/loadCompany",
		data : {
			"companyId" : $("#cb_companys").val()
		},
		success : function(respone) {
			var obj = JSON.parse(respone);
			$('#lbl_companyName').text(obj.COMPANY_NAME);
			$('#lbl_companyAddress').text(obj.COMPANY_ADDRESS);
			$('#lbl_companyEmail').text(obj.COMPANY_EMAIL);
			$('#lbl_companyPhone').text(obj.COMPANY_PHONE);
		}
	});
});
$(document).ready(function() {
	$("#cb_companys").change(function() {
		$.ajax({
			type : "POST",
			url : window.location.origin + "/insurence_quanth/Register.do/loadCompany",
			data : {
				"companyId" : $(this).val()
			},
			success : function(respone) {
				var obj = JSON.parse(respone);
				$('#lbl_companyName').text(obj.COMPANY_NAME);
				$('#lbl_companyAddress').text(obj.COMPANY_ADDRESS);
				$('#lbl_companyEmail').text(obj.COMPANY_EMAIL);
				$('#lbl_companyPhone').text(obj.COMPANY_PHONE);
			}
		});
	});
});