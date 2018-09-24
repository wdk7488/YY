<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>js/jQuery.js"></script>
	<script src="<%=basePath%>js/canvas.js" charset="utf-8" ></script>
	<script src="<%=basePath%>js/zupuSonAndAncestry.js" charset="utf-8" ></script>
	<link rel = "stylesheet" type="text/css" href="<%=basePath%>css/head.css">
  </head>
  	
  <body id="${zid }" class="body_zupu">
  
  <%@ include file="head1.jsp" %>
  
  <!-- 
  <p id ="p1">111</p>
  <input type="button" id="button1" value="test" /> -->
  <input type="text" name="zid" id="zid">
  <input type="button" id="ancestryButton" onclick="showSon();" value="子女">
  <input type="button" id="sonButton" onclick="showAncestry();" value="祖先"><span>(只支持谱系号查找，需要了解上3代祖先谱系点击祖先按钮，点击黄色部分显示该熊猫的子女谱系，点击黑色耳朵显示祖宗谱系。)</span>
  <canvas id="drawing"  >浏览器不支持canvas</canvas>
  <%@ include file="foot.jsp" %>
  </body>
  <!-- 
  <script type="text/javascript">
 
  
  
		var json = '[{"ancestryId":"","birthday":{"date":10,"day":2,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1507564800000,"timezoneOffset":-480,"year":117},"dateOfDeath":null,"father":"","generation":0,"gid":0,"mother":"650","name":"","photo":"","pid":1,"place":"上海野生动物园","sex":1,"zid":"1118"},{"ancestryId":"","birthday":{"date":4,"day":3,"hours":0,"minutes":0,"month":9,"nanos":0,"seconds":0,"time":1507046400000,"timezoneOffset":-480,"year":117},"dateOfDeath":null,"father":"","generation":0,"gid":0,"mother":"625","name":"","photo":"","pid":2,"place":"上海野生动物园","sex":0,"zid":"1117"},{"ancestryId":"","birthday":{"date":21,"day":1,"hours":0,"minutes":0,"month":7,"nanos":0,"seconds":0,"time":1503244800000,"timezoneOffset":-480,"year":117},"dateOfDeath":null,"father":"802","generation":0,"gid":0,"mother":"509","name":"梦梦","photo":"","pid":3,"place":"陕西楼观台","sex":0,"zid":"1116"},{"ancestryId":"","birthday":{"date":14,"day":4,"hours":0,"minutes":0,"month":8,"nanos":0,"seconds":0,"time":1505318400000,"timezoneOffset":-480,"year":117},"dateOfDeath":null,"father":"","generation":0,"gid":0,"mother":"643","name":"","photo":"","pid":4,"place":"卧龙神树坪基地","sex":1,"zid":"1115"},{"ancestryId":"","birthday":{"date":6,"day":3,"hours":0,"minutes":0,"month":8,"nanos":0,"seconds":0,"time":1504627200000,"timezoneOffset":-480,"year":117},"dateOfDeath":null,"father":"","generation":0,"gid":0,"mother":"791","name":"","photo":"","pid":5,"place":"雅安碧峰峡","sex":1,"zid":"1114"}]';
		var obj = eval('(' + json + ')');
		//alert(json[0].zid);
		var p = document.getElementById("p1");
		var table = document.createElement('table');
		p.appendChild(table);
		var tr,td;
		for(var i=0;i < obj.length;i++){
			tr = document.createElement('tr');
			table.appendChild(tr);
			td = document.createElement('td');
			td.innerHTML= obj[i].zid;
			tr.appendChild(td);
			td = document.createElement('td');
			td.innerHTML= obj[i].name;
			tr.appendChild(td);
			td = document.createElement('td');
			td.innerHTML= obj[i].father;
			tr.appendChild(td);
			td = document.createElement('td');
			td.innerHTML= obj[i].mother;
			tr.appendChild(td);
			td = document.createElement('td');
			td.innerHTML= obj[i].place;
			tr.appendChild(td);
		}
		
	</script> -->
</html>
