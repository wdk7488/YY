 var url = "getCharacterList.do?zid=";
 var url_ancestyr ="index.do?display=ANCESTRY&ancestryId=";

var list = new Array();

/**
$(window).resize(resizeCanvas);

function resizeCanvas() {

$("#drawing").attr("width", $(window).get(0).innerWidth);

$("#drawing").attr("height", $(window).get(0).innerHeight);

}*/
$(document).ready(function(){
	 showCharacteristics(url+getPram("zid"));
	 
	 
		    //console.log("onclick url("+url+")");
			//alert("onclick:"+url);  //不知道为什么list可以获取  url还是不变
		　　   // drawPandaByUrlAndList(p,url);}
});

function addCharacter(tag){
	var zid = $("#zid").text().trim();
	var t = $("#"+tag.id);
	var name = t.attr("name");
	console.log("/SSH/addCharacter.do?zid="+zid+"&character="+encodeURIComponent(name));
	location.href="/SSH/addCharacter.do?zid="+zid+"&character="+encodeURIComponent(name);
	
}

function showss(){
	var zid = $("#zid").text().trim();
	var character = $("#character").val().trim();
	console.log("/addCharacter.do?zid="+zid+"&character="+character);
	//alert(encodeURI(character));
	location.href="/SSH/addCharacter.do?zid="+zid+"&character="+encodeURIComponent(character);
	//showCharacteristics("http://localhost:8080/SSH/getCharacterList.do?zid=600");
}

function showCharacteristics(url){
	 
	$.getJSON(url,function(result){
		//alert(JSON.parse(result));
		
		if(result && result[0]){
			var tag = $(".div_tag");
			//tag.html("");
			for(var i = 0; i < result.length; i++){
				tag.append("<div id="+result[i][0]+" class='div_tag' name='"+result[i][1]+"' onclick=' addCharacter(this);'>"+result[i][1]+"("+result[i][2]+")</div>");
				
			}
			
			
		}
		
		
	});
	
}
	
  		 

 function getPram(name)
 {
      var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
      var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
      if(r!=null)return  unescape(r[2]); return null;
 }
  
        
        
        
       