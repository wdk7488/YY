<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

	<body>
		<%= request.getAttribute("message")==null?"":request.getAttribute("message") %>
		<html:form action="/searchGenealogy">
			userId : <html:hidden property="userId"/><html:errors property="userId"/><br/>
			表单 : <html:text property="sheetName"/><html:errors property="sheetName"/><br/>
			id : <html:text property="zid"/><html:errors property="zid"/><br/>
			姓名 : <html:text property="name"/><html:errors property="name"/><br/>
			
			母亲id : <html:text property="mother"/><html:errors property="mother"/><br/>
			
			父亲id : <html:text property="father"/><html:errors property="father"/><br/>
			
			<html:submit value="查询"/><html:cancel value="取消"/><a href="<%=basePath%>jsp/addGenealogy.jsp">添加</a>
		</html:form>
		
		
		
		<table width="80%" border="1" cellpadding="0"
		style="border-collapse: collapse; " bordercolor="#000000">
		<tr>
			<td><b>ID</b></td>
			<td><b>表单</b></td>
			<td><b>名字</b></td>
			<td><b>父亲</b></td>
			<td><b>母亲</b></td>
			<td><b>操作</b></td>
			
		</tr>
		<c:if test="${notnull==1}">
		${notnull}
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.zid}</td>
				<td>${list.sheetName}</td>
				<td>${list.name}</td>
				<td>${list.father}</td>
				<td>${list.mother}</td>
				
 				<td>
 				
 				<a href="<%=basePath%>modifyGenealogyByZid.do?zid=${list.zid}">修改</a>|
 				<a href="<%=basePath%>deleteGenealogy.do?zid=${list.zid}">删除</a>|
 				
 				
 				<a href="<%=basePath%>jsp/zupu2.jsp?zid=${list.zid}">溯源</a>
 				<!-- 
 				<a href="<%=basePath%>index.do?zid=${list.zid}">溯源</a> -->
 				
 				</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
</body>


