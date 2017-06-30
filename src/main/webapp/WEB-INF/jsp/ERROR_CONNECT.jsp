<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
			<p class="errorDB">
				<spring:message code="ERROR_CONNECT" />
			</p>
			<input class="btnsubmit" type="button"
				value="<spring:message code="BTN_OK"/>"
				onclick="location.href='${pageContext.servletContext.contextPath}/Login';" /> 
	</div>
</body>
</html>