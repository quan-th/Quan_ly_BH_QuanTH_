<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!-- Begin vung dieu kien tim kiem -->
<div class="div_002">
	<form:form action="${pageContext.servletContext.contextPath}/Search.do"
		method="GET" width="100%" modelAttribute="searchingInfo"
		name="inputform">
		<h3 class="DisplayItem002">
			<spring:message code="SEARCH_LIST" />
		</h3>
		<p class="DisplayItem0021">
			<spring:message code="COMPANYS" />
		</p>
		<form:select path="companyId" class="DisplayItem0021" id="cb_companys"
			items="${companies}" itemValue="companyID" itemLabel="companyName"
			onchange="javascript:changeCompany()" htmlEscape="true">
		</form:select>

		<p class="DisplayItem002">
			<spring:message code="SEARCH_INFO" />
		</p>
		<div class="search">
			<input type="hidden" name="userAction" name="userAction"
				value="nothing">
			<form:hidden path="idNumber" id="idNumber" htmlEscape="true"/>
			<form:hidden path="currentPage" id="currentPage" htmlEscape="true"/>
			<form:hidden path="orderByName" id="orderByName" htmlEscape="true"/>
			<table class="tbl_input" cellpadding="10">
				<tr align="left">

					<td class="lbl_left"><spring:message code="USER_NAME" /></td>
					<td align="center"><form:input htmlEscape="true" id="username" path="username"
							class="txBox" type="text" width="60%"
							onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr align="left">
					<td class="lbl_left"><spring:message code="ID_INSURANCE" /></td>
					<td align="center"><form:input htmlEscape="true" id="insuranceId"
							path="insuranceId" class="txBox" type="text" width="60%"
							onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>

					<td class="lbl_left"><spring:message code="LOCATION_REGISTER" /></td>
					<td align="center"><form:input htmlEscape="true" id="placeOfRegister"
							path="placeOfRegister" class="txBox" type="text" width="60%"
							onfocus="this.style.borderColor='#0066ff';"
							onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>

					<td align="center" colspan="2"><input class="btnsubmit"
						type="button" onclick="javascript:searchUser();"
						value="<spring:message code="SEARCH_BUTTON"/>" /></td>
				</tr>
			</table>

		</div>
		<table class="btnTableMH002" width="100%">
			<tr>
				<td width="69%" align="left"><input class="btnsubmit"
					type="button" value="<spring:message code="REGISTER_BUTTON"/>"
					onclick="location.href='${pageContext.servletContext.contextPath}/Register.do?SessionId=${searchingInfo.idNumber}';" /></td>
				<td align="left"><c:if test="${fn:length(allUsers)!=0}">
						<input class="btnsubmit" type="button"
							value="<spring:message code="EXPORT_CSV_BUTTON"/>"
							onclick="javascript:exportCSV();" />
					</c:if></td>
			</tr>
		</table>
		<!-- End vung tim kiem -->
		<!-- Begin vung hien thi danh sach user -->
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
			width="100%">

			<c:choose>
				<c:when test="${empty message}">
					<tr class="tr2">
						<th align="center"><spring:message code="USER_NAME" /> <c:if
								test="${searchingInfo.orderByName == 'ASC'}">
								<a href="javascript:sorting();">▲▽ </a>

							</c:if> <c:if test="${searchingInfo.orderByName == 'DESC'}">
								<a href="javascript:sorting();">△▼</a>
							</c:if>
						<th align="center"><spring:message code="GENDER" /></th>
						<th align="center"><spring:message code="DOB" /></th>
						<th align="center"><spring:message code="ID_INSURANCE" /></th>
						<th align="center"><spring:message code="EXPIRY_DATE" /></th>
						<th align="center"><spring:message
								code="LOCATION_REGISTER_KCB" /></th>
					</tr>
					<c:forEach items="${allUsers}" var="user">
						<tr>
							<td><a
								href="${pageContext.servletContext.contextPath}/Details.do?UserId=${user.id}&SessionId=${searchingInfo.idNumber}">${user.username}</a></td>
							<td align="center">${user.gender}</td>
							<td align="center">${user.birthdate}</td>
							<td align="center">${user.insuranceNumber}</td>
							<td align="center">${user.startDate}~${user.endDate}</td>
							<td>${user.placeOfRegister}</td>
						</tr>
					</c:forEach>

				</c:when>
				<c:when test="${not empty message}">
					<td class="errMsg"><c:out value="${message}" /></td>
				</c:when>
			</c:choose>
		</table>
		<!-- End vung hien thi danh sach user -->
		<!-- Begin vung paging -->
		<c:if test="${fn:length(pages)>1}">
			<table>
				<tr>

					<td><c:if test="${pages[0]>1}">
							<a href='javascript:previous(${currentPage})'>&lt;&lt;</a>
						</c:if></td>
					<c:forEach items="${pages}" var="page">
						<td><c:if test="${page != currentPage}">
								<a href='javascript:paging(${page})'><c:out value="|${page}"></c:out></a>
							</c:if> <c:if test="${page == currentPage}">
								<c:out value="|${page}"></c:out>
							</c:if></td>
					</c:forEach>
					<td><c:if test="${pages[fn:length(pages)-1]<totalPages}">
							<a href='javascript:next(${currentPage})'>&gt;&gt;</a>
						</c:if>
			</table>
		</c:if>
	</form:form>
</div>
<!-- End vung paging -->
</body>

</html>