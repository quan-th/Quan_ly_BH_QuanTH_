function getInputForm() {
	var frm = document.inputform;
	return frm;
}
function sorting() {
	var frm = getInputForm();
	frm.action = "/Search.do";
	frm.userAction.value = "search";
	if (frm.orderByName.value == "ASC") {
		frm.orderByName.value = "DESC";
	} else {
		frm.orderByName.value = "ASC";
	}
	frm.submit();
}
function paging(number) {
	var frm = getInputForm();
	frm.action = "/Search.do";
	frm.userAction.value = "search";
	frm.currentPage.value = number;	
	frm.submit();
}
function previous(number) {
	var frm = getInputForm();
	frm.action = "/Search.do";
	frm.userAction.value = "search";
	frm.currentPage.value = number - 1;
	frm.submit();
}
function next(number) {
	var frm = getInputForm();
	frm.action = "/Search.do";
	frm.userAction.value = "search";
	frm.currentPage.value = number + 1;
	frm.submit();
}
function submitButton() {
	var frm = getInputForm();
	if ("edit" != frm.userAction.value) {
		frm.userAction.value = "submitAction";
	} else if ("edit" == frm.userAction.value) {
		frm.userAction.value = "comfirmEdit";
	}
	frm.submit();
}

function searchUser() {
	var frm = getInputForm();
	frm.action = "/Search.do";
	frm.userAction.value = "search";
	frm.submit();
}
function updateUser() {
	var frm = getInputForm();
	frm.action = "/Update.do";
	frm.submit();
}
function deleteUser(deleteMessage, id) {
	var frm = getInputForm();
	var r = confirm(deleteMessage);
	if (r == true) {
		window.location.href = window.location.origin
		+ "/Delete.do?userId=" + id;
	} else {
		return;
	}
}
function exportCSV() {
	var frm = getInputForm();
	frm.action = "/Search.do/CSV";
	frm.submit();
}
function changeCompany() {
	var frm = getInputForm();
	frm.userAction.value = "search";
	frm.username.value = "";
	frm.insuranceId.value = "";
	frm.placeOfRegister.value = "";
	frm.currentPage.value = "1";
	frm.orderByName.value = "ASC";
	frm.submit();
}
