<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
		<title>JSP for SearchPandaForm form</title>
		<link rel = "stylesheet" type="text/css" href="css/head.css">
	</head>
	<body>
	extends、<%@ include file="head1.jsp" %>
		<div  class="div_info">
		<html:form action="/searchPanda">
			
			zid : <html:text property="zid"/><html:errors property="zid"/><br/>
			name : <html:text property="name"/><html:errors property="name"/><br/>
			ansestryId : <html:text property="ansestryId"/><html:errors property="ansestryId"/><br/>
			mother : <html:text property="mother"/><html:errors property="mother"/><br/>
			father : <html:text property="father"/><html:errors property="father"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
		
		
		<table width="80%" border="1" cellpadding="0"
		style="border-collapse: collapse; " bordercolor="#000000">
		<tr>
			<td><b>ID</b></td>
			<td><b>名字</b></td>
			<td><b>父亲</b></td>
			<td><b>母亲</b></td>
			<td><b>操作</b></td>
		</tr>
		<c:if test="${notnull==1}">
		<c:forEach items="${list}" var="list">
			<tr>
				<td><a href = "/SSH/showPandaDetail.do?zid=${list.zid}">${list.zid}</a></td>
				<td>${list.name}</td>
				<td>${list.father}</td>
				<td>${list.mother}</td>
 				<td>
 				<!-- 
 				<a href="<%=basePath%>showPanda.do?zid=${list.zid}">修改</a>|
 				<a href="<%=basePath%>deletePanda.do?zid=${list.zid}">删除</a>|
 				-->
 				<a href="<%=basePath%>jsp/zupu.jsp?display=SON&zid=${list.zid}">溯源</a>
 				<!-- 
 				<a href="/SSH/index.do?zid=${list.zid}">溯源</a> 
 				
 				</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
	</div>
	<%@ include file="foot.jsp" %>
	</body>
</html>

