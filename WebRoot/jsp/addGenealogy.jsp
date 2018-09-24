<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for ModifyGenealogyForm form</title>
	</head>
	<body>
		<html:form action="/modifyGenealogy">
			zid : <html:hidden property="zid"/><html:errors property="zid"/><br/>
			userId : <html:hidden property="userId"/><html:errors property="userId"/><br/>
			sheetName : <html:text property="sheetName"/><html:errors property="sheetName"/><br/>
			name:  <html:text property="name"/><html:errors property="name"/><br/>
			sex : 
			男<html:radio  property="sex" value="1"/>
			女<html:radio property="sex" value="0"/>
			未知<html:radio property="sex" value="2"/>
			<html:errors property="sex"/><br/>
			father : <html:text property="father"/><html:errors property="father"/><br/>
			mother : <html:text property="mother"/><html:errors property="mother"/><br/>
			
			
			birthday : <html:text property="birthday"/><html:errors property="birthday"/><br/>
			dateOfDeath : <html:text property="dateOfDeath"/><html:errors property="dateOfDeath"/><br/>
			
			other : <html:text property="other"/><html:errors property="other"/><br/>
			remark : <html:textarea property="remark"/><html:errors property="remark"/><br/>
			characteristi : <html:text property="characteristi"/><html:errors property="characteristi"/><br/>
			
			photo : <html:text property="photo"/><html:errors property="photo"/><br/>
			
			resume : <html:textarea property="resume"/><html:errors property="resume"/><br/>
			
			
			
			ancestryId : <html:text property="ancestryId"/><html:errors property="ancestryId"/><br/>
			place : <html:text property="place"/><html:errors property="place"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

