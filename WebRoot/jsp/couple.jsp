<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

		<title>JSP for CoupleForm form</title>
		<link rel = "stylesheet" type="text/css" href="css/head.css">
	</head>
	<body>
	<%@ include file="head1.jsp" %>
	<%=request.getAttribute("message")%><br>
	<div  class="div_info">
		<html:form action="/couple">
			id : <html:text property="id"/><html:errors property="id"/><br/>
			<!-- 名字查询会有重复，先不考虑实现
			otherName : <html:text property="otherName"/><html:errors property="otherName"/><br/>
			name : <html:text property="name"/><html:errors property="name"/><br/> -->
			otherId : <html:text property="otherId"/><html:errors property="otherId"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
		</div>
		<%@ include file="foot.jsp" %>
	</body>
</html>

