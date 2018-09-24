 var url = "index.do";
var panda;
 
 $(document).ready(function(){

		
 	$("#button1").click(function(){$.getJSON(url+"?more=1",function(data){
 		var img = $("div");
 		
		for(var i = 0;i<data.length;i++){
			
			img.append("<a href='/SSH/showPanda.do?zid="+data[i].zid+"'>"+data[i].zid+" </a>");
			img.html(img.html()+data[i].name);
			img.html(img.html()+data[i].place);
			img.html(img.html()+"</br>");
			
		}
		$("#p1").append(img);
 	    
 	});
  		
    
});
});

 
  		 
    $.ajax({
        type: "POST",
        url: url,
        data: {},
        dataType: "json",
        async:true,
        success:function(result) {
        	var img = $("<div></div>");
        	var a = $("<a></a>");
        	
        	for(var i = 0;i<result.length;i++){
        		if(i==0){
        			img.html(img.html()+"谱系号");
        			img.html(img.html()+"名字");
        			img.html(img.html()+"地点");
        			img.html(img.html()+"</br>");
        			
        		}
        		a.href = "/showPanda.do?zid="+result[i].zid;
        		a.text(result[i].zid);
        		img.append("<a href='/SSH/showPanda.do?zid="+result[i].zid+"'>"+result[i].zid+" </a>");
        		img.html(img.html()+result[i].name);
        		img.html(img.html()+result[i].place);
        		img.html(img.html()+"</br>");
        		//drawPanda(result[i],100*(i+1),100);
    		}
        	$("#p1").after(img);
        }
    });
    