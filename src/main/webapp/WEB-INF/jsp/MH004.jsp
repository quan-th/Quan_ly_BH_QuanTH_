<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/ajax.js"></script>
<!-- Begin vung thong tin -->
<div class="div_004">
	<p>
		<font size="6"> <c:if test="${action == 'ACTION_REGISTER'}">
				<spring:message code="REGISTER_INSURENCE" />
			</c:if> <c:if test="${action  == 'ACTION_UPDATE'}">
				<spring:message code="UPDATE_INSURENCE" />
			</c:if>
		</font>
	</p>
	<form:form
		action="${pageContext.servletContext.contextPath}/Register.do"
		method="POST" width="100%" modelAttribute="insuranceInfo"
		name="inputform">
		<input type="hidden" name="SessionId" value="${SessionId}">
		<c:if test="${action == 'ACTION_UPDATE'}">
			<form:hidden htmlEscape="true" path="userId" />
			<form:hidden htmlEscape="true" path="username" />
			<form:hidden htmlEscape="true" path="userPassword" />
		</c:if>
		<table class="tbl_error">
			<tr>
				<td class="error"><form:errors path="insuranceNumber"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="fullname"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="gender"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="birthdate"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="companyName"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="companyAddress"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="placeOfRegister"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="startDate"></form:errors></td>
			</tr>
			<tr>
				<td class="error"><form:errors path="endDate"></form:errors></td>
			</tr>

		</table>
		<table class="tbl_insurance004">
			<tr>
				<td class="lbl_left004"><spring:message code="ID_INSURANCE" /></td>
				<td><form:input htmlEscape="true" path="insuranceNumber" class="txBox"
						type="text" width="60%"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message code="FULL_NAME" /></td>
				<td><form:input htmlEscape="true" path="fullname" class="txBox" type="text"
						width="60%" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message code="GENDER" /></td>
				<td><form:select htmlEscape="true" path="gender">
						<option value="01"><spring:message code="CB_MALE" /></option>
						<option value="02"><spring:message code="CB_FEMALE" /></option>
					</form:select></td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message code="DOB" /></td>
				<td><form:input htmlEscape="true" path="birthdate" class="txBox" type="text"
						width="60%" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"></td>
				<td align="left"><form:radiobutton htmlEscape="true" path="choseCompany"
						id="already" name="choseCompany" value="ALREADY_HAVE" /> <spring:message
						code="ALREADY_HAVE_COMPANYS" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message code="COMPANY" /></td>
				<td colspan="2"><form:select htmlEscape="true" path="companyId"
						class="DisplayItem0021" id="cb_companys" items="${companies}"
						itemValue="companyID" itemLabel="companyName">
					</form:select></td>
			</tr>
			<tr>
				<td class="lbl_left004"></td>
				<td>
					<table class="companyInfo" id="companyInfo">
						<tr>
							<td class="companyInfoLeft"><spring:message code="COMPANYS" /></td>
							<td id="lbl_companyName" class="companyInfoRight"></td>
						</tr>
						<tr>
							<td class="companyInfoLeft"><spring:message
									code="COMPANY_ADDRESS" /></td>
							<td id="lbl_companyAddress" class="companyInfoRight"></td>
						</tr>
						<tr>
							<td class="companyInfoLeft"><spring:message
									code="COMPANY_EMAIL" /></td>
							<td class="companyInfoRight"><a id="lbl_companyEmail" href=""></a></td>
						</tr>
						<tr>
							<td class="companyInfoLeft"><spring:message
									code="COMPANY_PHONE" /></td>
							<td id="lbl_companyPhone" class="companyInfoRight"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="lbl_left004"></td>
				<td align="left"><form:radiobutton htmlEscape="true" path="choseCompany"
						name="choseCompany" id="new" value="ADD_NEW_COMPANY" /> <spring:message
						code="REGISTER_NEW_COMPANYS" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"></td>
				<td>
					<table class="tbl_company004" id="tbl_company004">
						<tr>
							<td class="companyInfoLeft"><spring:message code="COMPANYS" /></td>
							<td><input type="text" name="companyName"></td>
						</tr>
						<tr>
							<td class="companyInfoLeft"><spring:message
									code="COMPANY_ADDRESS" /></td>
							<td><input type="text" name="companyAddress"></td>
						</tr>
						<tr>
							<td class="companyInfoLeft"><spring:message
									code="COMPANY_EMAIL" /></td>
							<td><input type="text" name="companyEmail"></td>
						</tr>
						<tr>
							<td class="companyInfoLeft"><spring:message
									code="COMPANY_PHONE" /></td>
							<td><input type="text" name="companyPhone"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message
						code="LOCATION_REGISTER_KCB" /></td>
				<td><form:input htmlEscape="true" path="placeOfRegister" class="txBox"
						type="text" width="60%"
						onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message
						code="START_DATE_OF_INSURANCE" /></td>
				<td><form:input htmlEscape="true" path="startDate" class="txBox" type="text"
						width="60%" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
			</tr>
			<tr>
				<td class="lbl_left004"><spring:message
						code="END_DATE_OF_INSURANCE" /></td>
				<td><form:input htmlEscape="true" path="endDate" class="txBox" type="text"
						width="60%" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
			</tr>
			<tr>

				<td align="center"><input class="btnsubmit" type="button"
					value="<spring:message code="BTN_CANCEL"/>"
					onclick="location.href='${pageContext.servletContext.contextPath}/Search.do?userAction=back&sessionId=${SessionId}';" /></td>
				<td align="left"><c:if test="${action == 'ACTION_REGISTER'}">
						<input class="btnsubmit" type="submit"
							value="<spring:message code="BTN_REGISTER"/>" />
					</c:if> <c:if test="${action == 'ACTION_UPDATE'}">
						<input class="btnsubmit" type="button"
							value="<spring:message code="BTN_UPDATE"/>"
							onclick="javascript:updateUser();" />
					</c:if></td>
			</tr>
		</table>


	</form:form>
</div>
