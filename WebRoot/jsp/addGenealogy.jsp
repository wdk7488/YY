<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for ModifyGenealogyForm form</title>
	</head>
	<body>
		<html:form action="/addGenealogy">
			zid : <html:hidden property="zid"/><html:errors property="zid"/><br/>
			userId : <html:hidden property="userId"/><html:errors property="userId"/><br/>
			表单 : <html:text property="sheetName"/><html:errors property="sheetName"/><br/>
			名字:  <html:text property="name"/><html:errors property="name"/><br/>
			性别 : 
			男<html:radio  property="sex" value="1"/>
			女<html:radio property="sex" value="0"/>
			未知<html:radio property="sex" value="2"/>
			<html:errors property="sex"/><br/>
			父亲名 : <html:text property="father"/><html:errors property="father"/><br/>
			母亲名 : <html:text property="mother"/><html:errors property="mother"/><br/>
			
			
			生日 : <html:text property="birthday"/><html:errors property="birthday"/><br/>
			忌日 : <html:text property="dateOfDeath"/><html:errors property="dateOfDeath"/><br/>
			
			其他 : <html:text property="other"/><html:errors property="other"/><br/>
			备注 : <html:textarea property="remark"/><html:errors property="remark"/><br/>
			性格 : <html:text property="characteristi"/><html:errors property="characteristi"/><br/>
			
			照片 : <html:text property="photo"/><html:errors property="photo"/><br/>
			
			简历 : <html:textarea property="resume"/><html:errors property="resume"/><br/>
			
			
			
			祖先 : <html:text property="ancestryId"/><html:errors property="ancestryId"/><br/>
			现居地 : <html:text property="place"/><html:errors property="place"/><br/>
			<html:submit value="保存"/><html:cancel value="取消"/>
		</html:form>
	</body>
</html>

