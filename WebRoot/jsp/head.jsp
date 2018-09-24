<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html> 
	<head>
		<base href="<%=basePath%>">
		<title>JSP for LoginForm form</title>
		
		<link rel = "stylesheet" type="text/css" href="css/common.css">
		<link rel = "stylesheet" type="text/css" href="css/head.css">
	</head>
	<header>
    	<div class="head_div">
    		<div id="head_title">源源</div>
    		<!-- 
    		<img class = "head_img" alt="网页图标" src="">
    		 -->
    	</div>
    	<div class="head_div">
    		<ul class="head_ul">
    			<li class="head_li">首页</li>
    			<li class="head_li">找猫</li>
    			<li class="head_li">族谱</li>
    			<li class="head_li">关系</li>
    		</ul>
    	</div>
    	<div class=".head_divbody">
    		<img class= "div_img" alt="后续加个搜索功能" src="">
    	</div>
    
    </header>
    <div id = "body_div">
    	<img id="body_big_img" alt="封面" src="/SSH/image/big_img_modify.jpg">
    	<ul class="body_ul">
    			<li class="body_li">*******************</li>
    			<li class="body_li">*******************</li>
    			<li class="body_li">*******************</li>
    			<li class="body_li">*******************</li>
    		</ul>
    </div>
    <footer >
    	<div class="foot_div">
    		<ul class="foot_ul">
    			<li class="foot_li">关于我们</li>
    			<li class="foot_li">联系方式</li>
    			<li class="foot_li">版权声明</li>
    		</ul>
    		
    	</div>
    	<div class="foot_div">
    		<img alt="微信二维码" src="">
    		<img alt="ipanda二维码" src="">
    	</div>
    </footer>
</html>

