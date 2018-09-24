<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for ShowYYForm form</title>
	</head>
	<body>
		<html:form action="/showYY">
			输入心上熊
			生日 : <html:text property="birthday"/><html:errors property="birthday"/>
			其他 : <html:text property="nother"/><html:errors property="nother"/>
			名字 : <html:text property="name"/><html:errors property="name"/>
			谱系号 : <html:text property="zid2"/><html:errors property="zid2"/>
			<html:submit/><html:cancel/>
		</html:form><br/>
		<pre>
		<%=request.getAttribute("message")%><br/>
		</pre>
		
		
	</body>
</html>

