<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for RegisterForm form</title>
	</head>
	<body>
		<%=request.getAttribute("message")%><br>
		<html:form action="/register">
			username : <html:text property="username"/><html:errors property="username"/><br/>
			nicename : <html:text property="nicename"/><html:errors property="nicename"/><br/>
			password : <html:password property="password"/><html:errors property="password"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

