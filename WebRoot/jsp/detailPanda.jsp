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
		<link rel = "stylesheet" type="text/css" href="<%=basePath%>css/head.css">
		<script type="text/javascript" src="<%=basePath%>js/jQuery.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>js/characteristics.js" charset="utf-8"></script>
		 <style type="text/css">
		 	
		 	
		 </style>
	</head>
	<body id="body"><br>
	<%@ include file="head1.jsp" %>
		<div class="div_info">
			
			
			谱系号 : <span id="zid" class = "zid"> ${panda.zid}</span> |
			名字 : <span name="name">${panda.name}</span>|
			
			性别 :<span name=sex>${panda.sex} </span>
			
			
			<br/>
			母亲谱系号 : <span name="mother" >${panda.mother}</span>|
			父亲谱系号 : <span name="father" >${panda.father}</span><br/>
			
			祖宗 : <span name="ancestryId">${panda.ancestryId}</span><br/>
			
			pid : <span name="pid">${panda.pid}</span><br/>
			生日 : <span name="birthday" >${panda.birthday} </span><br/>
			出生地 : <span name="place">${panda.place}</span><br/>
			现状 : <span name="dateOfDeath" >${panda.photo}</span><br/>
			
			</div>
			
			
			<div class="div_qiang" >
				<div class="div_input" ><input id="character"  type="text" /><input id="addCharact" type="button" onclick="showss();" value="添加标签"/></div>
				<sqan style="display:none">特点、外号:</sqan>
				<div class="div_tag"></div>
				   
				
				
			</div>
		
		<script charset="utf-8"> 
		//解决struts标签的html:radio不能自动选择性别 
		
		
    			
      //第一个radio选中   
      
  </script>
  <%@ include file="foot.jsp" %>
	</body>
</html>

