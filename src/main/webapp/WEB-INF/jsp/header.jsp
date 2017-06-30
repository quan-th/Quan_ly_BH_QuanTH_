<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="${pageContext.servletContext.contextPath}/resources/css/style.css"
	type="text/css" rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/user.js"></script>


</head>
<body>
	<!-- Begin vung header -->
	<div class="header" align="right">
		<td><a href="${pageContext.servletContext.contextPath}/Logout.do"><spring:message
					code="LOGOUT_LINK_LABEL" /></a></td>
	</div>

	<!-- End vung header -->