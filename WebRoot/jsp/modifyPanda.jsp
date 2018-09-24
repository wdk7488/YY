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
		<title>JSP for ModifyPandaForm form</title>
		<link rel = "stylesheet" type="text/css" href="css/head.css">
		
		 
	</head>
	<body><br>
	<%@ include file="head1.jsp" %>
	
		<html:form action="/modifyPanda">&nbsp;
			birthday : <html:text property="birthday" value = "${panda.birthday}" /><html:errors property="birthday"/><br/>
			sex :
			男<html:radio styleId="man"  property="sex" value="1" />
			女<html:radio styleId="woman" property="sex" value="0"/>
			未知<html:radio styleId="other" property="sex" value="2"/>
			
			
			<html:errors property="sex"/>
			
			<br/>
			
			zid : <html:text property="zid" value = "${panda.zid} "/><html:errors property="zid"/><br/>
			ancestryId : <html:text property="ancestryId" value = "${panda.ancestryId}"/><html:errors property="ancestryId"/><br/>
			name : <html:text property="name" value = "${panda.name}"/><html:errors property="name"/><br/>
			pid : <html:hidden property="pid"/><html:errors property="pid"/><br/>
			dateOfDeath : <html:text property="dateOfDeath"/><html:errors property="dateOfDeath"/><br/>
			place : <html:text property="place"/><html:errors property="place"/><br/>
			mother : <html:text property="mother" value = "${panda.mother}"/><html:errors property="mother"/><br/>
			father : <html:text property="father" value = "${panda.father}"/><html:errors property="father"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
		<script> 
		//解决struts标签的html:radio不能自动选择性别 
    			var sex = ${panda.sex};
    			if(sex == 1){
    				var man = document.getElementById("man");
    				man.checked = true;
    			}
    			else if(sex == 0){
    				
    				var woman = document.getElementById("woman");
    				woman.checked = true;
    			}
    			else{
    				var other = document.getElementById("other");
    				other.checked = true;
    			}
      //第一个radio选中   
      
  </script>
  <%@ include file="foot.jsp" %>
	</body>
</html>

