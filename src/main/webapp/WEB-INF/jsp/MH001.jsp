<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.servletContext.contextPath}/resources/css/style.css" type="text/css"
	rel="stylesheet">
</head>
<body>
	<div class="login">
		<form:form action="${pageContext.servletContext.contextPath}/Login" method="POST" width="100%"
			modelAttribute="userLogin">
			<div class="div_001">
				<table class="tbl_input" cellpadding="4" cellspacing="0"
					width="100%">
				
					<tr>
					<tr>
						<td class="error" colspan="2"><form:errors path="username"></form:errors></td>
					</tr>
					<tr>
						<td class="error" colspan="2"><form:errors path="password"></form:errors></td>
					</tr>
					<tr>
						<th colspan="2" align="center"><spring:message
								code="LOGIN_LABEL" /></th>
					</tr>

					<tr>
						<td class="lbl_left"><spring:message code="USER_NAME_LABEL" /></td>
						<td align="center"><form:input htmlEscape="true" path="username" class="txBox"
								type="text" width="60%"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
					</tr>
					<tr>
						<td class="lbl_left"><spring:message code="PASS_WORD_LABEL" /></td>
						<td align="center"><form:input htmlEscape="true" class="txBox" type="password"
								path="password" width="60%"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
					</tr>
					<tr>

						<td align="center" colspan="2"><input class="btnsubmit"
							type="submit" value="<spring:message code="LOGIN_BUTTON"/>" /></td>
					</tr>
				</table>
			</div>

		</form:form>
	</div>
</body>
</html>