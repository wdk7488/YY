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
		<script type="text/javascript" src="js/jQuery.js" charset="utf-8"></script>
		<script type="text/javascript" src="js/characteristics.js" charset="utf-8"></script>
		 <style type="text/css">
		 	
		 	.div_info {height:400px;width:400px;margin-top:30px;margin-left:10px;float:left;}
		 	.div_qiang {height:400px;width:400px;background-color:pink;float:left;margin-left:60px}
		 	.div_input {height:20px;width:400px;float:left}
		 	.div_tag {background-color:white;margin:6px;float:left}
		 </style>
	</head>
	<body id="body"><br>
	<%@ include file="head1.jsp" %>
		<div class="div_info">
			
			
			zid : <span id="zid" class = "zid"> ${panda.zid}</span> |
			name : <span name="name">${panda.name}</span>|
			
			sex :<span name=sex>${panda.sex} </span>
			
			
			<br/>
			mother : <span name="mother" >${panda.mother}</span>|
			father : <span name="father" value = "${panda.father}"><br/>
			
			ancestryId : <span name="ancestryId">${panda.ancestryId}</span><br/>
			
			pid : <span name="pid">${panda.pid}</span><br/>
			birthday : <span name="birthday" >${panda.birthday} </span><br/>
			dateOfDeath : <span name="dateOfDeath">${panda.dateOfDeath}</span><br/>
			place : <span name="place">${panda.place}</span><br/>
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

