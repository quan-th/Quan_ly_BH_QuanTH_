<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!-- Begin vung thong tin -->
<div class="div_003">
	<form method="get" name="inputform">
		<h1>
			<spring:message code="DETAILS_INSURANCE" />
		</h1>
		<table class="tbl_input01">
			<tr>
				<td class="lbl_left"><input class="btnsubmit" type="button"
					value="<spring:message code="BACK"/>" onclick="location.href='${pageContext.servletContext.contextPath}/Search.do?userAction=back&sessionId=${SessionId}';"/></td>
			</tr>

		</table>
		<table class="tbl_details" cellpadding="10" width="100%">
			<tr>
				<td class="lbl_left003"><spring:message code="ID_INSURANCE" /></td>
				<td>${detailUser.insuranceNumber}</td>
			</tr>
			<tr>
				<td class="lbl_left003"><spring:message code="FULL_NAME" /></td>
				<td>${detailUser.username}</td>
			</tr>
			<tr>
				<td class="lbl_left003"><spring:message code="GENDER" /></td>
				<td>${detailUser.gender}</td>
			</tr>
			<tr>
				<td class="lbl_left003"><spring:message code="DOB" /></td>
				<td>${detailUser.birthdate}</td>
			</tr>
			<tr>
				<td class="lbl_left003"><spring:message code="COMPANYS" /></td>
				<td>${detailUser.company}</td>
			</tr>
			<tr>
				<td class="lbl_left003"><spring:message
						code="LOCATION_REGISTER_KCB" /></td>
				<td>${detailUser.placeOfRegister}</td>
			</tr>
			<tr>
				<td class="lbl_left003"><spring:message code="EXPIRY_INSURANCE" /></td>
				<td>${detailUser.startDate} <spring:message code="TO" />
					${detailUser.endDate}
				</td>
			</tr>
		</table>
		<table class="tbl_input02" cellpadding="10" width="43%">
			<tr>

				<td class="lbl_left"><input class="btnsubmit" type="button"
					value="<spring:message code="DELETE"/>"
					onclick="javascript:deleteUser('<spring:message code="DELETE_MESSAGE"/>',${detailUser.id});" /></td>
				<td align="center"><input class="btnsubmit" type="button"
					value="<spring:message code="UPDATE"/>" onclick="location.href='${pageContext.servletContext.contextPath}/Update.do?SessionId=${SessionId}&UserId=${detailUser.id}';"/></td>
			</tr>

		</table>
	</form>
</div>
