<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html> 
	<head>
		<title>JSP for ModifyGenealogyForm form</title>
		
		
	</head>
	<body>
		<html:form action="/modifyGenealogy">
			 <html:hidden property="zid" value="${genealogy.zid}"/><html:errors property="zid"/><br/>
			<html:hidden property="userId" value="${genealogy.userId}"/><html:errors property="userId"/><br/>
			表单 : <html:text property="sheetName"  value="${genealogy.sheetName}"/><html:errors property="sheetName"/><br/>
			姓名:  <html:text property="name" value="${genealogy.name}"/><html:errors property="name"/><br/>
			性别 :  <!-- html:radio里不能用id  styleId可以表示id -->
			男<html:radio styleId="man"  property="sex" value="1"/>
			女<html:radio styleId="woman" property="sex" value="0"/>
			未知<html:radio styleId="unkown" property="sex" value="2"/>
			<html:errors property="sex"/><br/>
			父亲id : <html:text property="father" value="${genealogy.father}"/><html:errors property="father"/><br/>
			母亲id : <html:text property="mother"  value="${genealogy.mother}"/><html:errors property="mother"/><br/>
			
			生日 : <html:text property="birthday"  value='${genealogy.birthday}'/><html:errors property="birthday"/><br/>
			
			忌日 : <html:text property="dateOfDeath"  value='${genealogy.dateOfDeath}'/><html:errors property="dateOfDeath"/><br/>
			
			其他 : <html:text property="other"  value="${genealogy.other}"/><html:errors property="other"/><br/>
			备注 : <html:textarea property="remark"  value="${genealogy.remark}"/><html:errors property="remark"/><br/>
			性格 : <html:text property="characteristi"  value="${genealogy.characteristi}"/><html:errors property="characteristi"/><br/>
			
			照片 : <html:text property="photo"  value="${genealogy.photo}"/><html:errors property="photo"/><br/>
			
			简历 : <html:textarea property="resume"  value="${genealogy.resume}"/><html:errors property="resume"/><br/>
			
			
			
			祖先id : <html:text property="ancestryId"  value="${genealogy.ancestryId}"/><html:errors property="ancestryId"/><br/>
			现居地 : <html:text property="place"  value="${genealogy.place}"/><html:errors property="place"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
	
	<script> 
		//解决struts标签的html:radio不能自动选择性别 
		console.log(${genealogy.sex});
    			var sex = ${genealogy.sex};
    			if(sex == 1){
    				var man = document.getElementById("man");
    				console.log(man);
    				man.checked = true;
    			}
    			else if(sex == 0){
    				
    				var woman = document.getElementById("woman");
    				woman.checked = true;
    			}
    			else{
    				var other = document.getElementById("unkown");
    				other.checked = true;
    			}
      //第一个radio选中   
      
  	</script>
</html>

